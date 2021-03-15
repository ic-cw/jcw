<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="navigator">
			<a href="/" > Home </a>
			<a href="/#map1" > 오시는길 </a>
			<a href="/board/list" > 게시판 </a>
			<a href="/#gongzi" > 공지사항 </a>
			<a href="/" > 상담신청 </a>
			<a href="/" > 활동사진 </a>
	<!-- 	   히든이랑 none의 차이: 히든은 자리는 남아있고 보여지지만 않는다 -->
	<!-- 	   none은 자리까지 없애버린다. -->
	</div>
	<div class="nav2">
			<c:set value="${sess_name }" var="sessName" />
			<c:if test="${sessName != NULL }">
			
			<span style="color:black; float:right;">${sessName}님 반갑습니다.</span>
			<br>
			<span id="logOut">
				<a href="/member/myPage" 
				style="background-color:green; color:white; text-decoration:none; text-align:center; display:block; height:30px;"> 마이페이지 </a>
				<a href="/member/logOut" 
				style="background-color:green; color:white; text-decoration:none; text-align:center; display:block; height:30px;" > LogOut </a>
			</span>
			</c:if>
			
			<br>
			<c:if test="${sessName == NULL }">
			<span id="login">
				<a href="#" onclick="show_Me()"  
				style="background-color:green; color:white; text-decoration:none; text-align:center; display:block; height:30px;"> Login </a>
			</span>
			</c:if>

	</div>
</div>
