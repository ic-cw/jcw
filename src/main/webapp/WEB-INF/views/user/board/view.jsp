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
<script>
function checkYesNo(){
	return confirm("정말 삭제하시겠습니까?");
}
function show_rreg(){
	document.getElementById('reply_mod').style.display = 'block';
}
</script>
<title>게시판</title>
<script src="/js/formCheck.js"></script>
</head>
<body>

<%@ include file="../menu.jsp" %>
<%@ include file="../header.jsp" %>

<div class="content" style="width:100%">
	<h2> 게시판(BOARD) </h2>
	<div class="myid">
		<table style="width:100%">
			<tr>
				<td style="text-align:left">제목 : ${board.title}</td>
				<td style="text-align:right">조회수 : ${board.count}</td>								
			</tr>
			<tr>
				<td style="text-align:left">작성자 : ${board.id}</td>						
				<td style="text-align:right">${board.wdate}</td>					
			</tr>
			<tr>
				<td colspan="2" style="text-align:left; height:300px;">
				${board.content}
				<br>
				</td>
			</tr>
			<c:if test="${board.upfile.fileName != null}">
			<tr>
			<c:set value="${board.upfile.fileType }" var="ftype" />
			<c:set value="${fn:substring(ftype,0,fn:indexOf(ftype,'/'))}" var="type" />
				
				<td colspan="2" style="text-align:left"> 첨부파일 : 	
				
				<form name="filedown" method="post" action="/download">		
					<input type="hidden" name="savefilename" value="${board.upfile.saveFileName}">
					<input type="hidden" name="filepath" value="${board.upfile.filePath}">	
					
<%-- 					<c:if test="${type eq 'image' }"> --%>
<%-- 					</c:if>	 --%>
						<img width="100px" height="100px" src="/upload/${board.upfile.saveFileName }" />
																						
					${board.upfile.fileName } (사이즈: ${board.upfile.fileSize }) 		
					<input type="submit" value="다운로드" >								
				</form>
				</td>
				
			</tr>	
			</c:if>
		</table>
		<c:if test="${board.id == sess_id }">
			<form name="boardModifybtn" method="post" action="/board/modifyForm">
	            <input type="hidden" name="bno" value="${board.bno}">
	            <input type="hidden" name="title" value="${board.title}">
	            <input type="hidden" name="content" value="${board.content}">
	            <input type="hidden" name="count" value="${board.count}">
	            <input type="hidden" name="wdate" value="${board.wdate}">
	            <input type="hidden" name="writer" value="${board.id}">
	            
	            <input type="hidden" name="fileNo" value="${board.upfile.fileNo}">
				<input type="hidden" name="saveFileName" value="${board.upfile.saveFileName }">
				<input type="hidden" name="fileName" value="${board.upfile.fileName}">
				<input type="hidden" name="fileSize" value="${board.upfile.fileSize}">
				<input type="hidden" name="fileType" value="${board.upfile.fileType}">
				
	            <input type="submit" class="button" value="수정">
	         </form>
	         
	         <form name="boardDelbtn" method="post" action="boardDel" onsubmit="return checkYesNo()">

	            <input type="hidden" name="bno" value="${board.bno}">
	            <input type="hidden" name="savefilename" value="${board.upfile.saveFileName}">
	            <input type="submit" style="float:right" value="삭제">
	         </form>
         </c:if>
	</div>

<!-- 뭔가 있었음 근데 못하겠음 -->

	
</div>
<%@ include file="../modal.jsp" %>
<%@ include file="../footer.jsp" %>



<script>


function repModComplete(root_path, reply_sno) {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function(){
		if(x.readyState === 4){
			if(x.status === 200){
				if(x.responseText.trim() === "0"){
				
					alert("댓글 수정에 실패하였습니다.");
				
				} else{
					document.location.reload();
								
				}
			} else{
				console.log('에러코드 : ' + x.status);
			}
		}
	};
	//매개변수 3개 지정 (1)get post방식 (2)서버에 어떤 문서를 요청할것인가
	//(3)비동기식인지 동기식인지(아작스는 비동기식이 거의 디폴트 트루)
	//받는건 responseText가 받는다(http의 바디를 받는다.)
	x.open("post", root_path + "/board/repModifyProc.jsp", true);
	//포스트 방식일때는 넣어준다(헤더정보)
	x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	x.send("sno=" + reply_sno + "&content=" + document.forms["repForm2"]["rep_content1"].value);
}

function repDelete(root_path, reply_sno) {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function(){
		if(x.readyState === 4){
			if(x.status === 200){
				if(x.responseText.trim() === "0"){
				
					alert("댓글 삭제에 실패하였습니다.");
				
				} else{
					
					//댓글삭제할거냐고 물어보는거 넣어야함
					alert("댓글이 삭제되었습니다.");
					document.location.reload();
								
				}
			} else{
				console.log('에러코드 : ' + x.status);
			}
		}
	};
	x.open("post", root_path + "/board/repDelete.jsp", true);
	x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	x.send("sno=" + reply_sno);
}


</script>
</body>
</html>
