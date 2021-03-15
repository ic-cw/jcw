<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/cw.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="admin_formCheck.js"></script>
<script src="admin_test.js"></script>

</head>
<body>
<input type="hidden" id="loginRes" value="<c:out value="${loginStat}"/>">

<script>
window.onload = function(){
	if(document.getElementById("loginRes").value == "loginFail"){
        alert("일치하는 회원 정보가 없습니다.");
     }
}

</script>

<div class="pop_content">
	<h3 style="text-align:center;">관리시스템</h3>
	<form class="login_popup" name="login" method="post" action="login.ame" onsubmit="return formCheck()">
		<div style="height:100px; width:600px;  transform:translate(-15%, 0%);">
			<input type="text" name="id" maxlength="10" placeholder="아이디" 
			style="height:30px; width:300px; margin-left:130px;" required>
			<input type ="password" name ="pw" placeholder="비밀번호" maxlength="10" 
			style="height:30px; width:300px; margin-left:130px;">
			<p class="pw_msg" ID="pw_msg"></p>
		</div>
		<div>
			<input type = "submit" value ="로그인"  class="login_btn" 
			style="height:30px; width:300px; background-color:#ff8c1a;">		
			<a id="comeon" href="#"></a>	
		</div>
	</form>
</div>

</body>
</html>