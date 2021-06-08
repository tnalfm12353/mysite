package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

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
		// Handler Method에 @Auth 없을 경우 Type에 붙어 있는지 확인한다.
		if(auth == null) {
			auth = handlerMethod.getBean().getClass().getAnnotation(Auth.class);
		}
		
		// Type이나 Method 둘 다 @Auth가 적용이 되어 있지 않은 경우 
		if(auth == null) {
			return true;
		}
		// @Auth가 붙어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		// 권한(Authorization) 체크를 위해서 @Auth의 role 가져오기.
		String role = auth.role();
		String authRole = authUser.getRole();
		
		if("ADMIN".equals(role)) {
			if(authRole.equals(role)) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}
		return true;
	}
}
