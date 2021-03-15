package com.cosmos.service;

import java.util.*;

import javax.servlet.http.*;

import com.cosmos.dto.*;

public interface MemberService {
	
	//회원등록
	int insert(Member member);
	
	//회원수정
	int update(Member member);
	
	//회원삭제
	int delete(String id);
	
	//회원조회
	Member[] select();
	
	
	//로그인
	Map<String, String> login(Member member);

	//중복체크
	int idCheck(String id);

	//1인회원조회
	Member selectMember(String id);
}
