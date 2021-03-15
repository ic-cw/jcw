/**
 * 
 */

//시간이 5시 넘으면 배경색 검정(야간모드)
function night_mode(){
	var h = new Date();
	
//	alert('현재시간 : ' + h.getHours());
	if(h.getHours() >= 17 && h.getHours() <=7){
		document.body.style.backgroundColor = 'black';
	}
	else if(h.getHours){
		document.body.style.backgroundColor = 'white';
	}
	loginCheck();
}
