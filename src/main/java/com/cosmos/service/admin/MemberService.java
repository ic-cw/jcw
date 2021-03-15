package com.cosmos.service.admin;

import java.util.*;

import javax.servlet.http.*;

import com.cosmos.dto.*;


public interface MemberService {
	

	//로그인
	Map<String, String> login(String id, String pw);

	
	//회원리스트 출력
	ArrayList<Member> listMember();
	
	//회원리스트 검색 출력
	ArrayList<Member> listMemberSearch(String user, String u_name);
	
	//탈퇴리스트 출력
	ArrayList<Member> listDelMember();

	//탈퇴리스트 검색출력
	ArrayList<Member> listDelMemberSearch(String d_date);

	//건수 출력
	Map<String, String> cntnum();
	
}
