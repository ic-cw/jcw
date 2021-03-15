package com.cosmos.service.admin;

import java.util.*;

import javax.servlet.http.*;

import com.cosmos.dao.admin.*;
import com.cosmos.dto.*;


public class MemberServiceImple implements MemberService {
	//필드
	MemberDao mdao = new MemberDao();
	
	

	public Member setMember(HttpServletRequest request) {
		Member m = new Member();
		m.setId(request.getParameter("id"));
		m.setPw(request.getParameter("pw"));
		m.setName(request.getParameter("name"));
		m.setGender(request.getParameter("gender"));
		m.setPost(request.getParameter("post"));
		m.setAddr1(request.getParameter("addr1"));
		m.setAddr2(request.getParameter("addr2"));
		m.setAddr3(request.getParameter("addr3"));
		String email = request.getParameter("eid") + "@" + request.getParameter("domain");
		m.setEmail(email);
		m.setIntro(request.getParameter("intro"));
		
		String[] hobby = request.getParameterValues("hobby");
		m.setHobby(hobby);
		
//		CodeDao c = new CodeDao();
//		Code[] code = c.getCodeList("hobby");
//		String[] hobbyName = new String[hobby.length];
//		
//		for(int i=0; i<hobby.length ; i++) {
//			for(int j=0; j<code.length; j++)
//			if(code[j].getCodeVal().equals(hobby[i])) {
//				hobbyName[i] = code[j].getCodeName();
//				break;
//			}
//		}
//		m.setHobbyName(hobbyName);
		return m;
		
	}


	
	@Override
   public Map<String, String> login(String id, String pw) {
	  return mdao.loginPr(id, pw);
   }

	@Override
	public Map<String, String> cntnum() {
		return mdao.setCount();
	}

	@Override
	public ArrayList<Member> listMember() {
		return mdao.listMemberdao();
	}
	
	@Override
	public ArrayList<Member> listMemberSearch(String user, String u_name) {
		return mdao.listMemberSearchdao(user, u_name);
		
	}


	@Override
	public ArrayList<Member> listDelMember() {
		return mdao.listDelMemberdao();
	}

	@Override
	public ArrayList<Member> listDelMemberSearch(String d_date) {
		return mdao.listDelMemberSearchdao(d_date);
	}

}
