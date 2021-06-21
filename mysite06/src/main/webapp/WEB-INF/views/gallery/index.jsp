<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%pageContext.setAttribute( "newLine", "\n" );%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/lightbox.css" rel="stylesheet" type="text/css">
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/lightbox.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function(){
	// 업로드 다이알로그
	var dialogUpload = $( "#dialog-upload-form" ).dialog({
		autoOpen: false,
		height: 280,
		width: 300,
		modal: true,
		buttons: {
			"업로드": function() {
				$( "#dialog-upload-form form" ).submit();
				$( this ).dialog( "close" );
			},
			"취소" : function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( "#dialog-upload-form form" ).get(0).reset();	
		}
	});
		
	$("#upload-image").click( function(event) {
		event.preventDefault();
		dialogUpload.dialog( "open" );
	});
});	
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h1 style="background-image: url('${pageContext.request.contextPath }/assets/images/gallery.png');">갤러리</h1>
					<c:if test="${user.role == 'ADMIN' }">
						<a href="" id="upload-image">이미지 올리기</a>
					</c:if>
				</div>
				<ul>
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im1.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im1.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/1"
								class="del-button"
								style="background-image: url('${pageContext.request.contextPath }/assets/images/delete-image.png');"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im2.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im2.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im3.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im3.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/3"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im4.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im4.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/4"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
	
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im5.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im5.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/5"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im6.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im6.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/6"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im7.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im7.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/7"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im8.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im8.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/8"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im9.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im9.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/9"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im10.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im10.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/10"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im11.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im11.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/11"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im12.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im12.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/12"
								class="del-button"
								title="삭제">삭제</a>
						</li>																														
		
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im13.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im13.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/13"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im14.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im14.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/14"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im15.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im15.jpg')">&nbsp;</a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/15"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<c:forEach items="${galleries }" var="gallery">
							<li>
								<a	href="${pageContext.request.contextPath }${gallery.url}"
									data-lightbox="gallery"
									class="image"
									style="background-image:url('${pageContext.request.contextPath }${gallery.url}')">&nbsp;</a>
								<c:if test="${user.role == 'ADMIN' }">	
									<a	href="${pageContext.request.contextPath }/gallery/delete/${gallery.id}"
										class="del-button"
										style="background-image: url('${pageContext.request.contextPath }/assets/images/delete-image.png');"
										title="삭제">삭제</a>
								</c:if>
							</li>
						</c:forEach>																																				
				</ul>	
			</div>
			<c:if test="${user.role == 'ADMIN' }">
				<div id="dialog-upload-form" title="이미지 업로드" style="display:none">
	  				<p class="validateTips normal">이미지와 간단한 코멘트를 입력해 주세요.</p>
	  				<form action="${pageContext.request.contextPath }/gallery/upload" 
	  					  method="post"
	  					  enctype="multipart/form-data">
						<label>코멘트</label>
						<input type="text" id="input-comments" name="comment" value="">
						<label>이미지</label>
						<input type="file" id="input-file" name="file">
						<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
	  				</form>
				</div>
			</c:if>
		</div>
		<c:import url="/WEB-INF/views/includes/navi.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>