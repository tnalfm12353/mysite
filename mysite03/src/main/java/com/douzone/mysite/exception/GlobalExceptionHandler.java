package com.douzone.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;


@ControllerAdvice
public class GlobalExceptionHandler {

	private final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
		// 1. logging
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		/**
		 *  1. appender
		 *     file appender / log-mysite/exception.log
		 *     10M (Archiving 정책)
		 *     1-10 (rolling)
		 *     - console appender
		 *  
		 *  2. logger - com.douzone.mysite.exception, level(error), appender (console+file)
		 *     logger - Root, level(debug) , console.appender
		 */
		LOGGER.error(errors.toString());
		// 2. 요청 구분하기 (html? json?)
		// 만약, JSON 요청인 경우이면 request header의 Accept에 application/json
		// 만약, HTML 요청인 경우이면 request header의 Accept에 text/html
		String accept = request.getHeader("Accept");
		if(accept.matches(".*application/json.*")) {
			//3. json 응답
			response.setStatus(HttpServletResponse.SC_OK);
			JsonResult result = JsonResult.fail(errors.toString());
			String jsonString = new ObjectMapper().writeValueAsString(result);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("UTF-8"));
			os.close();
		} else {
			// 3. 사과페이지 가기(정상 종료)
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception").forward(request, response);
		}
	}
}
