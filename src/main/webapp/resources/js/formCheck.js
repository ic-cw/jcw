/**
 * 
 */

function inputDomain(){
	var sel = document.forms["memReg"]["sel_domain"].value;
//	alert(sel);
	document.forms["memReg"]["domain"].value = sel;
	
	if(sel != ""){
		document.forms["memReg"]["domain"].readOnly = true;
	}
	else{
		document.forms["memReg"]["domain"].readOnly = false;
	}
}

function memberFormCheck(){
	var cwcw = document.forms["memReg"]["pw"].value
	
	//비번체크
	if(cwcw.length < 5 || cwcw.length > 15){
		document.getElementById("pw_error").style.display = "block";
		document.getElementById("pw_error").style.color = "red"
		return false;
	}
	
	//이메일체크
	if(document.forms["memReg"]["domain"].value.length != 0 
	&& document.forms["memReg"]["eid"].value == ""){
		document.getElementById("domain_a").innerText 
		= "** 이메일아이디를 입력하세요";
		return false;
	}
	//성별체크
	if(document.forms["memReg"]["gender"].value == ""){
		document.getElementById("gender_error").innerText 
		= "** 성별을 입력하세요";
		
		return false;
	}
	//취미체크, 최소1개 선택
	var hobby_length = document.forms["memReg"]["hobby"].length;
	var count = 0;
	console.log("취미갯수: " + hobby_length);
	for(i = 0 ; i < hobby_length ; i++){
		//console.log("변수 i값 : " + i)
		if(document.forms["memReg"]["hobby"][i].checked){
			return count++;
			break;
			
		}
	}
	if(count==0){
		document.getElementById("chimi").style.display = "inline";
		return false;
	}
	
}

function consultFormCheck(){
	
	
	//성별체크
	if(document.forms["consultReg"]["gender"].value == ""){
		document.getElementById("gender_error").innerText 
		= "** 성별을 입력하세요";
		
		return false;
	}
	//전화번호 체크
	if(document.forms["consultReg"]["p_num"].value == ""){
		document.getElementById("pnum_error").innerText 
		= "** 전화번호를 입력하세요";
		
		return false;
	}
	
}

function idCheck2(aaa){
	var idid = document.forms["memReg"]["id"].value;
	if(idid.length == 0){
		alert('아이디를 입력하세요');
		return;
	}
	var x = new XMLHttpRequest();
	
	x.onreadystatechange = function(){
		if(x.readyState === 4){
			if(x.status === 200){
				var msg = document.getElementById("idCheckMsg");
				
				document.getElementById("isIdCheck").value = x.responseText.trim();			
				if(x.responseText.trim() === "0"){
					msg.innerHTML = "사용가능한 아이디입니다."
				}
				else{
					msg.innerHTML = "중복된 아이디입니다."
				}
			}
			else{
			console.log('에러코드 : ' + x.status);
			}
		}
	};
	x.open("post", "member/idDoubleCheck", true);
	x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	x.send("idstr=" + idid);
}

