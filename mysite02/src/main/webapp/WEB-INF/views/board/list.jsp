<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?" method="post">
					<input type="hidden" name="a" value="search" />
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }"></c:set>				
					<c:forEach items="${list }" var="vo" varStatus="status">
					<tr>
						<td>${count - status.index }</td>
						<td style="text-align:left; padding-left:${vo.depth * 20}px">
							<c:if test="${vo.depth != 0 }"> 
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'/>
							</c:if>
							<a href="${pageContext.request.contextPath }/board?a=view&id=${vo.id}">${vo.title }</a>
						</td>
						<td>${vo.userName }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<td>
							<c:if test="${vo.userId == authUser.id }">
								<a href="${pageContext.request.contextPath }/board?a=delete&id=${vo.id}" class="del">삭제</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:forEach begin="${pages.firstPage }" end="${pages.lastPage }" step="1" varStatus="status">
							<c:choose>
								<c:when test="${(status.count-1) + pages.firstPage == pages.currentPage }">
									<li class="selected"><a href="${pageContext.request.contextPath }/board?page=${(status.count-1) + pages.firstPage }">${(status.count-1) + pages.firstPage }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/board?page=${(status.count-1) + pages.firstPage }">${(status.count-1) + pages.firstPage }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navi.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>