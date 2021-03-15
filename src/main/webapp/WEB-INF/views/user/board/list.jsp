<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/cw.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/formCheck.js"></script>
</head>
<body>

<%@ include file="../menu.jsp" %>
<%@ include file="../header.jsp" %>

<div class="content" style="width:100%">
	<div class="myid" style="margin:10%">
	<h3 style="height:50px;"> 게시판(BOARD) </h3>
	<c:if test="${sess_name != null }">
	<button class="blue" onclick="location.href='/board/regForm'"> 등록 </button>
	</c:if>
	<p id="board_msg" style="text-align:center; height:50px;"></p>
			<script>
			if(${dto.bno}== 0){
				document.getElementById('board_msg').innerHTML = '등록된 게시물이 없습니다.' 
			}
			</script>
		<table style="width:100%">
			<thead>
				<tr>
					<th>순번</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${board }" var="dto">
				<tr onclick="location.href='/board/view?bno=${dto.bno}'">	
					<td>${dto.bno}</td>
					<td>${dto.title}</td>
					<td>${dto.count}</td>
					<td>${dto.wdate}</td>
					<td>${dto.id}</td>
				</tr>
				</c:forEach>
			</tbody>		
		</table>
	</div>
</div>
<%@ include file="../modal.jsp" %>
<%@ include file="../footer.jsp" %>
<div>
	<input type="hidden" name="delresult" value="${delstat }" id="delresult" /> 
</div>
<script>
	window.onload = function(){
		if(document.getElementById("delresult").value == 1){
			alert("데이터가 삭제되었습니다.");
		}
	}
</script>
</body>
</html>