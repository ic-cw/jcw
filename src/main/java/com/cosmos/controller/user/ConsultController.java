package com.cosmos.controller.user;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cosmos.dto.*;
import com.cosmos.service.*;


@WebServlet(urlPatterns = {"*.ce"})
public class ConsultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final ConsultService cs = new ConsultServiceImple();
       
    public ConsultController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		System.out.println("uri = " + cmd);
		
		String viewPage = null;
		
		if(cmd.equals("consult.ce")) {
			//상담신청폼 출력
			goView(request, response, "/sangdam/reg_consult.jsp");
			
		} else if(cmd.equals("consultReg.ce")) {
			//상담신청 처리요청
			//seqno로 i를 가져옴
			int i = cs.insert(request);
			Consult c = cs.setConsult(request);
			request.setAttribute("consult", c);
			goView(request, response, "/sangdam/reg_consult.jsp?isConRegSuccess=1");
		} 
	}
	
	private void goView(HttpServletRequest request, HttpServletResponse response, String viewPage) throws ServletException, IOException {
		//디스패쳐로 페이지 불러오기
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	
	
	

}

