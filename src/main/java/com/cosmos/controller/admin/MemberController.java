package com.cosmos.controller.admin;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cosmos.dto.*;
import com.cosmos.service.admin.*;


@WebServlet(urlPatterns = {"*.ame"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final MemberService ms = new MemberServiceImple();
       
    public MemberController() {
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
		
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		System.out.println("uri = " + cmd);
		
		String viewPage = null;
		
		if(cmd.equals("memReg.ame")) {
			//회원등록폼 출력
			viewPage = "/member/memRegForm.jsp";
			
			goView(request, response, viewPage);
			
		}  else if(cmd.equals("login.ame")) {
			//로그인처리
	
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			Map<String, String> rs = ms.login(id,pw);
			switch(rs.get("stat")) {
				case "ok" : 
					//로그인성공, 세션값설정
					HttpSession sess = request.getSession();
					sess.setAttribute("sess_id", id);
					sess.setAttribute("sess_name", rs.get("name"));
					response.sendRedirect("cntnum.ame");
					break;
				default : 
					//id없는 경우, 패스워드 불일치
					request.setAttribute("loginStat", "loginFail");
					//고쳐야함
					goView(request,response,"index.jsp");
			}
		}  else if(cmd.equals("cntnum.ame")) {
			
			Map<String, String> rs = ms.cntnum();
			System.out.println(rs);
			request.setAttribute("cntreg", rs.get("reg"));
			request.setAttribute("cntdel", rs.get("del"));
			goView(request,response,"admin_main.jsp");
			
			}  else if(cmd.equals("memList.me")) {
			ArrayList<Member> m = ms.listMember();
			request.setAttribute("member", m);
			goView(request, response, "member/MemberList.jsp");
			
		} else if(cmd.equals("memSearch.ame")) {
			String user = request.getParameter("radiogaga");
			String u_name = request.getParameter("u_name");
			ArrayList<Member> m = ms.listMemberSearch(user, u_name);
			request.setAttribute("member", m);
			request.setAttribute("grade", user);
			request.setAttribute("name", u_name);
			goView(request, response, "member/MemberList.jsp");
			
		} else if(cmd.equals("memDel.ame")) {
			ArrayList<Member> m = ms.listDelMember();
			request.setAttribute("member", m);
			goView(request, response, "member/MemDelList.jsp");
			
		} else if(cmd.equals("memDelSearch.ame")) {
			String d_date = request.getParameter("d_date");
			ArrayList<Member> m = ms.listDelMemberSearch(d_date);
			request.setAttribute("member", m);
			request.setAttribute("deldate", d_date);
			goView(request, response, "member/MemDelList.jsp");
			
		} else if(cmd.equals("memDelAll.ame")) {
			
			goView(request, response, "member/MemDelList.jsp");
		}
			
	}
	
	private void goView(HttpServletRequest request, HttpServletResponse response, String viewPage) throws ServletException, IOException {
		//디스패쳐로 페이지 불러오기
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	
	
	

}
