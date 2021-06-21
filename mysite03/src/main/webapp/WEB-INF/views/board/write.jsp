<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form:form
					modelAttribute="boardVo" 
					class="board-form" 
					method="post" 
					action="${pageContext.request.contextPath }/board/write">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><form:input path="title" /></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<form:textarea path="content"/>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<spring:hasBindErrors name="boardVo">
							<p style="font-size:large; font-weight:bold; color:#f00;">
								<form:errors path="title" />
								<form:errors path="content" />
								<spring:message code="board.errorMessage"/>
							</p>
						</spring:hasBindErrors>
						<a href="${pageContext.request.contextPath }/board?page=${param.page}&kwd=${param.kwd}">취소</a>
						<input type="submit" value="등록">
					</div>
				</form:form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navi.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>