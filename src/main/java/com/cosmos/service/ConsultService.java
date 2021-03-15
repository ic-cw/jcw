package com.cosmos.service;

import javax.servlet.http.*;

import com.cosmos.dto.*;


public interface ConsultService {
	//상담신청 등록
	int insert(HttpServletRequest request);
	
	//상담신청 수정
	Consult update(HttpServletRequest request);
	
	//상담신청 조회
	Consult setConsult(HttpServletRequest request);
	
	//상담신청 삭제
	int deleteConsult();
}
