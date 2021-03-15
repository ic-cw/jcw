package com.cosmos.service;

import javax.servlet.http.*;

import com.cosmos.dto.*;


public interface ReplyService {
	//댓글 등록
	int insertReply(HttpServletRequest request, HttpServletResponse response);
	
	//댓글수정
	Reply updateReply(HttpServletRequest request);
	
	//댓글 삭제
	int deleteReply(int reply_no);
	
	//댓글 조회

}
