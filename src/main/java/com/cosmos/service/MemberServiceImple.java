package com.cosmos.service;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.cosmos.dao.*;
import com.cosmos.dto.*;

@Service
public class MemberServiceImple implements MemberService {
	@Autowired
	MemberDao mdao;
	
	@Override
	public int insert(Member member) {	
		
		String email =  member.getEid() + "@" + member.getDomain();
		member.setEmail(email);
		
		return mdao.insertMember(member);
	}

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
		
		CodeDao c = new CodeDao();
		Code[] code = c.getCodeList("hobby");
		String[] hobbyName = new String[hobby.length];
		
		for(int i=0; i<hobby.length ; i++) {
			for(int j=0; j<code.length; j++)
			if(code[j].getCodeVal().equals(hobby[i])) {
				hobbyName[i] = code[j].getCodeName();
				break;
			}
		}
		m.setHobbyName(hobbyName);
		return m;
		
	}
	@Override
	public int update(Member member) {
		
		String email =  member.getEid() + "@" + member.getDomain();
		member.setEmail(email);
	
		return mdao.updateMember(member);
	}

	@Override
	public int delete(String id) {
		return mdao.deleteMember(id);
	}

	@Override
	   public Member[] select() {
	      
	      return mdao.getMemberList();
	   }
	@Override
	   public Map<String, String> login(Member member) {
		  
		  return mdao.loginPr(member);
	   }

	@Override
	public int idCheck(String id) {
		
		return mdao.idCheck(id);
	}

	@Override
	public Member selectMember(String id) {
		
		return mdao.memberList(id);
	}



}
