<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" ></script>
<script type="text/javascript">
$(()=>{
	
	btn = $('#btn-check');
	btn.click(()=>{
		var email = $("#email").val();
		if(email == ""){
			return ;
		}
		$.ajax({
			url : "/mysite03/user/api/checkemail?email=" + email,
			type: "get",
			dataType : "json",
			success : response => {
				console.log(response);
				if(response.result != "success"){
					console.error(response.message);
					return ; 
				}
				
				if(response.data){
					alert("존재하는 이메일 입니다. 다른 이메일을 사용하세요!");
					$("#email").val("");
					$("#email").focus();
					return ;
				}
				btn.hide();
				$('#img-check').show();
			},
			error : ({xhr, status, e})=>{
				console.error(status,e);
			}
		})
	})
})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form:form
					modelAttribute="userVo"
				 	id="join-form" 
				 	name="joinForm" 
				 	method="post" 
				 	action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					
					<!-- 스프링 태그 -->
					<spring:hasBindErrors name="userVo">
						<p style="color:#f00; text-align:left">
							<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message code="${errors.getFieldError('name').codes[0]}" />
							</c:if>
						</p>
					</spring:hasBindErrors>
					
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="btn-check" type="button" value="중복체크">
					<spring:hasBindErrors name="userVo">
					<p style="color:#f00; text-align:left">
						<c:if test="${errors.hasFieldErrors('email') }">
							<strong>${errors.getFieldError('email').defaultMessage}</strong>
						</c:if>
					</p>
					</spring:hasBindErrors>
					<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="display:none; vertical-align: bottom;">
					<label class="block-label"><spring:message code="user.join.label.password"></spring:message> </label>
					<input name="password" type="password" value="">
					<spring:hasBindErrors name="userVo">
					<p style="color:#f00; text-align:left">
						<c:if test="${errors.hasFieldErrors('password') }">
							<strong>${errors.getFieldError('password').defaultMessage}</strong>
						</c:if>
					</p>
					</spring:hasBindErrors>
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navi.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>