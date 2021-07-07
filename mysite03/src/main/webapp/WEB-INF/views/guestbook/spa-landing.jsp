<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/guestbook.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<script>
	/* guestbook application based on jQuery */
	/*
		과제 ex1: 리스트
				- id 기준의 리스트를 부분적(5개씩) 가져와서 리스트 렌더링
				- 버튼 이벤트 구현 -> 스크롤 이벤트로 바꾼다.
				- id 기준으로 동적 쿼리를 레포지토리에 구현한다.
				- 렌더링 참고 /ch08/test/gb/ex1
		과제 ex2: 메세지 등록(add)
				- validation 하기 (message dialog plugin 사용)
				- form submit 막기
				- 데이터 하나를 렌더링(prepend)
				- 렌더링 참고 /ch08/test/gb/ex2
		과제 ex3: 메세지 삭제(delete)
				- a tag의 기본동작 막기
				- live event
				- 비밀번호 받을 수 있는 폼 기반 message dialog plugin 사용
				- 응답에 대해 해당 li를 삭제
				- 비밀번호가 틀린 경우(삭제실패, no=0) 사용자한테 알려주는 UI
				- 삭제 성공한 경우(no > 0), data-no = 10 인 li element를 삭제.
				- 렌더링 참고 /ch08/test/gb/ex3
	*/
	const fetch = (id) =>{
		(id == undefined) && (id = 0);
		$.ajax({
			url: "${pageContext.request.contextPath}/guestbook/api/list/"+id,
			dataType: "json",  	
			type: "get",
			success: function(response) {
				response.data.forEach((vo)=>{
				html = 
					"<li data-no='" + vo.id +"'>"+
					"<strong>" + vo.name + "</strong>" + 
					"<p>" + vo.message + "</p>" +
					"<strong></strong> <a href='' data-no='"+ vo.id +"'>삭제</a></li>"
					$("#list-guestbook").append(html);
				});
			}
		});
	}
	
	const add = (vo) =>{
		console.log(vo);
		$.ajax({
			url: "${pageContext.request.contextPath}/guestbook/api/add",
			async: true,
			dataType: "json",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(vo),
			success: function(response) {
				var vo = response.data;
				
				html =
					"<li data-no='" + vo.id + "'>" + 
						"<strong>" + vo.name + "</strong>" +
						"<p>" + vo.message + "</p>" +
						"<strong></strong>" + 
						"<a href='' data-no='" + vo.id + "'>삭제</a>" + 
					"</li>";
					
				$("#list-guestbook").prepend(html);	
			}
		});
	}
	
	const remove = (id,password) =>{
		$.ajax({
			url: "${pageContext.request.contextPath}/guestbook/api/delete/"+id,
			dataType: "json",  	
			type: "post",		
			data: "password=" + password,
			success: function(response) {
				if(response.result != "success") {
					response.error(response.message);
					return;
				}
				
				if(response.data == -1){
					//비밀번호가 틀린경우.
					$(".validateTips.error").show();
					$("#password-delete")
						.val("")
						.focus();
				}else{
					$("#list-guestbook li[data-no=" + response.data +"]").remove();
					$("#dialog-delete-form").dialog("close");
				}
			}
		});
	} 
	
	fetch();
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
					
				</ul>
				<div style="margin: 20px 0 0 0">
					<button id="btn-fetch">다음 가져오기</button>
				</div>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navi.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>