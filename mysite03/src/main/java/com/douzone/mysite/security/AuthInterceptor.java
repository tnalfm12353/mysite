package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// defaultServletHandler가 처리하는 경우 ( 정적 자원 접근 )
			
			return true;
		}
		// casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// Handler Method에 @Auth 없을 경우
		if(auth == null) {
			return true;
		}
		
		// @Auth가 붙어 있기 때문에 인증(Authentication) 여부 확인
		return true;
	}

	
	
}
