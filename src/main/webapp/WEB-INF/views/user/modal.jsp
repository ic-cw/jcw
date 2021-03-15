<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <script>
 function formCheck(){
		var pw = document.forms["login"]["pw"].value;
		var id = document.forms["login"]["id"].value;

		console.log('패스워드길이:' + pw.length);
		console.log('아이디 길이 :' + id.length);
		//아이디, 비밀번호가 최소 5자리 이상이면 넘어가게
		
//		if(id.length < 1){
//			document.getElementById('login_msg').innerHTML 
//			= "<font color='red'>아이디가 일치하지 않습니다.</font>";
//			return false;
//		}
		
		if(pw.length < 5){
			document.getElementById('login_msg').innerHTML 
			= "<font color='red'>비밀번호가 일치하지 않습니다.</font>";
//			alert('패스워드가 일치하지 않습니다.')
			return false;
		}
	}

	function show_Me(){
		document.getElementById('modal').style.display = 'block';
	}

	function hide_Me(){
		document.getElementById('modal').style.display = 'none';
	}

	function loginCheck(){
		if(login == 1){
			document.getElementById('login').style.display = 'none';
//			document.getElementById('logOut').style.display = 'inline';
		}
		else{
//			document.getElementById('login').style.display = 'inline';
			document.getElementById('logOut').style.display = 'none';
		}
		
	}
 
 </script>
 <div id="modal">
	<div id="xbox">
		<div class="pop_header">
			<div class="pop_close">
				<a href="#"  onclick="hide_Me()" style="text-decoration:none; color:white; font-size:150%; float:right;">x</a>
			</div>
			<div class="pop_login"></div>
		</div>
		
		<div style="position:absolute; top:10%;	left:22%;">
		<form name="login" method="post" action="/member/login" onsubmit="return formCheck()">
			<div class="pop_content2">
				<input type ="text" name ="id" placeholder="	    I  D"  maxlength="15"
						value="<% if(request.getParameter("id") !=null) out.print(request.getParameter("id")); %>" 
						required><br>
				<input type ="password" name ="pw" placeholder="	P A S S W O R D" maxlength="15"><br>
				<p ID="login_msg"></p>
				<p style="color:red" ID="msg"></p>
			</div>
			<div style="position:absolute; top:100%; left:30%;">
				<input type = "submit" value ="로그인"  class="login_btn">
						
			</div>
		</form>
			<a href="member/memRegForm" style="margin-left:36%; text-decoration:none; color:white;">[회원가입]</a>
<!-- 			 <a href="#" >[ID/PW 찾기]</a> -->
		</div>
	</div>
</div>