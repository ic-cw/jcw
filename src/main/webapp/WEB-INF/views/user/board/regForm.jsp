<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/cw.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/formCheck.js"></script>

</head>
<body onload="night_mode()">

<%@ include file="../menu.jsp" %>
<%@ include file="../header.jsp" %>

<div class="content" style="width:100%">
		<div class="myid">
			<h2> 게시판 등록</h2>
			<form name="board" method="post" enctype="multipart/form-data" action="/board/register">
				<input type="text" name="title" placeholder="제목" maxlength="255" required>
				<br>
				<textarea name="content" placeholder="내용"></textarea>	
				<input type="file" name="files" >
				<br>
				<input style="float:right;" type="submit" value="등록">
			</form>
		</div>
</div>
<%@ include file="../modal.jsp" %>
<%@ include file="../footer.jsp" %>
</body>
</html>