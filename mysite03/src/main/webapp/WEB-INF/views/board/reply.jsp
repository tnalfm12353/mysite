<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(board.content, newLine, "<br/>")}
							</div>
						</td>
					</tr>
				</table>
			</div>
			<c:if test="${param.status !=null}">
				<div style="font-size:large; font-weight:bold; color:#ff6060; text-align:center;">${param.status }</div>
			</c:if>
			<br/>
			<div id="board" style="border:1px solid black; box-shadow:0 5px 16px 5px rgb(0 0 0 / 20%); ">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board?">
					<input type = "hidden" name = "a" value="reply">
					<input type = "hidden" name = "id" value="${board.id }"/>
					<input type = "hidden" name = "group" value="${board.groupId }">
					<input type = "hidden" name = "order" value="${board.orderId }">
					<input type = "hidden" name = "depth" value="${board.depth }">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">답글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=view&id=${board.id}">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navi.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>