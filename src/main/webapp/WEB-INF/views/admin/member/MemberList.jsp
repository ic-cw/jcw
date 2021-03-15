<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="../header.jsp" %>
<hr>
<%@ include file="../menu.jsp" %>
<hr>

<div class="content" style="width:100%">
	<div class="myid" style="margin:10%">
	<h3 style="height:50px;"> ■ 회원리스트 </h3>
	<p id="m_meg"> 인원수: ${fn:length(member)}명</p>
		<form action="memSearch.ame" name="radioSearch">
              <input type="radio" value="0" name="radiogaga" 
              <c:if test="${grade== 0}">checked</c:if>>일반회원
              <input type="radio" value="1" name="radiogaga"
              <c:if test="${grade== 1}">checked</c:if>>운영자
              <input type="radio" value="2" name="radiogaga"
              <c:if test="${grade== 2}">checked</c:if>>관리자
              <input type="text" placeholder="이름을 입력해주세요." name="u_name"
              value="${name }">
           	 <input type="submit" value="검색"/>
        </form>
		<c:set var="cnt" value="${fn:length(member) }"/>
        <p id="m_meg"> 인원수: ${cnt }명</p>
		<table style="width:100%">
			<thead>
				<tr>
					<th>순번</th>
					<th>id</th>
					<th>이름</th>
					<th>성별</th>
					<th>등급</th>
					<th>가입일자</th>
					<th>탈퇴요청일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${member }" var="dto">
				<c:set var="cnt1" value="${cnt1-1 }"/>
					<tr>	
						<td>${cnt1 + cnt+1}</td>
						<td>${dto.id}</td>
						<td>${dto.name}</td>
						<td>${dto.gender}</td>
						<td>${dto.grade}</td>
						<td>${dto.wdate}</td>
						<td>${dto.deldate}</td>
					</tr>
				</c:forEach>
			</tbody>		
		</table>
	</div>
</div>
<hr>
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