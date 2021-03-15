<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>관리자</title>

</head>
<body>


<%@ include file="header.jsp" %>
<hr>
<%@ include file="menu.jsp" %>
<hr>
<div class="content">
<c:set value="${cntreg }" var="reg" />
<c:set value="${cntdel }" var="del" />
	<p> 회원등록 건수 : ${reg }</p>
	<p> 회원탈퇴요청 건수 : ${del }</p>
</div>
<hr>
<%@ include file="footer.jsp" %>

</body>
</html>