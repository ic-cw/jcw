<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/cw.css">
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>

<%@ include file="../header.jsp" %>
<%@ include file="../menu.jsp" %>



<div class="content" style="width:100%; padding:100px;">
  <div class="main">  
	<div class="card">
	    <h2>■ 회원수정</h2>	
	    <hr> 
	    <div style="margin-top:50px;">
<%-- 	    <c:set value="${member}" var="dto" /> --%>
			
			<label>아이디</label><br>
			${member.id}
			<br><br>
						
			<label>이름</label><br>
			${member.name } 
			( 
				<c:choose>
					<c:when test="${member.gender eq 'M' }">
					남자
					</c:when>
					<c:when test="${member.gender eq 'F' }">
					여자
					</c:when>
					<c:otherwise>
					사람이 아닙니다.
					</c:otherwise>
				</c:choose>
				
					
			)
			<br><br>
					
			<label>이메일</label><br>
			${member.email } 		
			<br><br>					
			
			<fieldset style="width:500px;">			
			<legend>취미</legend>
			
			 <label>취미</label>
<%--          <c:forEach var="h" items="${hobby}"> --%>
<%--                <c:forEach var="myhobby" items="${member.hobby}"> --%>
<%--                   <c:if test="${myhobby == h.codeVal }">${h.codeName}</c:if> --%>
                  
<%--                </c:forEach>             --%>
<%--          </c:forEach> --%>
			
			
			<c:forEach var="h" items="${hobby }" >
					<c:forEach var="myhobby" items="${member.hobbyName}">
						<c:if test="${myhobby == h.codeVal }"> ${h.codeName} </c:if>
					</c:forEach>																			
				</c:forEach>	
			</fieldset>			
			<br><br> 
			
			<label>주소</label><br>
			(${member.post }) <br>
			${member.addr1 } ${member.addr2 } ${member.addr3 }
			<br><br>
			
			<label>자기소개</label><br>
		 	${member.intro }
			<br>
		 	
		</div>    
	</div>	
  </div>	
</div>


<%@ include file="../modal.jsp" %>
<%@ include file="../footer.jsp" %>

</body>
</html>