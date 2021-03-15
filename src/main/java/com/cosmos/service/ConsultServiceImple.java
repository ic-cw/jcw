package com.cosmos.service;

import javax.servlet.http.*;

import com.cosmos.dao.*;
import com.cosmos.dto.*;


public class ConsultServiceImple implements ConsultService {

	//필드
	ConsultDao cdao = new ConsultDao();
	@Override
	public int insert(HttpServletRequest request) {
		return cdao.insertConsult(setConsult(request));
	}

	@Override
	public Consult update(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteConsult() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Consult setConsult(HttpServletRequest request) {
		Consult c = new Consult();
		c.setId((String)request.getSession().getAttribute("sess_id"));
		c.setName(request.getParameter("name"));
		c.setGender(request.getParameter("gender"));
		c.setP_num(request.getParameter("p_num"));
		c.setPostn(request.getParameter("post"));
		c.setAddr1(request.getParameter("addr1"));
		c.setAddr2(request.getParameter("addr2"));
		c.setAddr3(request.getParameter("addr3"));
		c.setC_intro(request.getParameter("intro"));
		
		return c;
	}


	

}
