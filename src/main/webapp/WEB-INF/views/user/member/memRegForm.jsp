<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="/css/cw.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/formCheck.js"></script>
<script src="/js/exam.js"></script>
<script src="/js/search_address.js"></script>
</head>
<body onload="night_mode()">


<%@ include file="../menu.jsp" %>
<%@ include file="../header.jsp" %>


<div class="content" style="width:100%">
<!-- 	<div class="main"> -->
		<div class="myid">
			<form name="memReg" method="post" action="/member/register" onsubmit="return memberFormCheck()">
				<fieldset>
				<legend> 회원가입 </legend>
				<input id="namename" type="text" name="id" maxlength="15" placeholder="아이디" onchange="idCheck2('/')"  required>
				<p id="idCheckMsg" style="font-color:red"></p>
				<input type="hidden" name="idCheck" id="isIdCheck">
<%-- 				<button onclick="idCheck('<%=ROOT %>')"> 아이디 중복검사</button> --%>
				<br>
				<input type="password" name="pw" maxlength="15" placeholder="패스워드" ><br>
				<p> * 최소 5자리 ~ 15자리 </p>
				<p id="pw_error"> 패스워드가 맞지 않습니다. </p> 
				<input type="text" name="name" placeholder="이름" required><br>
				<fieldset style="width:50%">
				<legend> 성별 </legend>
				<input type="radio" name="gender" value="M">남자
				<input type="radio" name="gender" value="F">여자<br>
				<span id="gender_error" style="color:red;">  </span>
				</fieldset>
				<input type="text" name="eid" placeholder="이메일아이디">
				@
				<input type="text" name="domain" placeholder="도메인">
				<select name="sel_domain" onchange="inputDomain()">
				  <option value="">직접 입력</option>
				  <option value="naver.com">naver.com</option>
				  <option value="hanmail.net">hanmail.net</option>
				  <option value="gmail.com">gmail.com</option>
				  <option value="daum.net">daum.net</option>
				</select>
				<span id="domain_a"></span>
				<fieldset style="width:50%">
				<legend>취미</legend>
					<input type="checkbox" name="hobby" value="1"> 테니스
					<input type="checkbox" name="hobby" value="2"> 야구
					<input type="checkbox" name="hobby" value="3"> 축구
<!-- 					<input type="checkbox" name="hobby" value="collect"> 수집 -->
<!-- 					<input type="checkbox" name="hobby" value="diy"> DIY -->
					<span id="chimi"> **취미를 선택하세요 </span>
				</fieldset>
				<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				<input type="text" name="post" size="5" readonly id="postcode">
				<input type="button" value="우편번호 찾기" onclick="search_address()"><br>
				<input type="text" name="addr1" placeholder="주소" id="address" readonly>
				<input type="text" name="addr3" placeholder="참고항목" id=extraAddress readonly>
				<input type="text" name="addr2" placeholder="상세주소 입력" id="detailAddress"><br>
				<textarea style="width:70%" name="intro" rows="6" cols="100" placeholder="자기소개(100자 이내)"></textarea><br>
				<input type="submit" value="등록">
				<input type="reset" value="초기화">
				</fieldset>
			</form>
		</div>
<!-- 	</div> -->
	
<!-- 	<div class="side"> -->
<!-- 		<p> About me</p> -->
<!-- 	</div> -->
</div>

<%@ include file="../modal.jsp" %>
<%@ include file="../footer.jsp" %>
</body>
</html>