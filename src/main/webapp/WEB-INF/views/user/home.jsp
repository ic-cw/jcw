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
<title>가가호호</title>
<script src="/js/exam.js"></script>
<script src="/js/search_address.js"></script>

</head>
<body onload="night_mode()">

<%@ include file="menu.jsp" %>
<%@ include file="header.jsp" %>


<hr style="margin-top:20px">
<div id="gongzi">
	<div class="cw_faq">
		<h1> 공지사항</h1>
		<div class="myid">
			<div style="float:left;"> 건 </div>
				<p id="board_msg" style="text-align:center; height:50px;"></p>
				
				<table style="width:100%;">
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
							<th>작성일자</th>
							<th>조회수</th>
						</tr>
					</thead>
					
				</table>			
		</div>
	</div>
</div>

<hr style="margin-top:20px">

<div>
	<h1 id="map1"> 오시는 길 </h1>
</div>

<div id="map">
	<!-- * 카카오맵 - 지도퍼가기 -->
<!-- 1. 지도 노드 -->
<div id="daumRoughmapContainer1608194282129" class="root_daum_roughmap root_daum_roughmap_landing"></div>

<!--
	2. 설치 스크립트
	* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
-->
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

<!-- 3. 실행 스크립트 -->
<script charset="UTF-8">
	new daum.roughmap.Lander({
		"timestamp" : "1608194282129",
		"key" : "23ieg",
		"mapWidth" : "640",
		"mapHeight" : "360"
	}).render();
</script>
</div>	

<hr style="margin-top:20px">
<c:out value="${isMemRegSuccess}" />
<input type="text" id="resMsg" value="<%=request.getParameter("isMemRegSuccess") %>">
<input type="hidden" id="LoginRes" value="<c:out value="${loginStat}" />">
 <script>
window.onload = function(){
	if(document.getElementById("resMsg").value == "1"){
		alert("회원이 등록되었습니다.");
		document.getElementById("modal").style.display = "block";
	}
	if(document.getElementById("LoginRes").value == "loginFail"){
		document.getElementById("modal").style.display = "block";
		document.getElementById("msg").innerHTML = "아이디가 없습니다.";
		document.forms[login][pw].focus();
		//패스워드 입력칸에 커서 깜박깜빡
	}
}
</script>

<%@ include file="modal.jsp" %>
<%@ include file="footer.jsp" %>

</body>
</html>