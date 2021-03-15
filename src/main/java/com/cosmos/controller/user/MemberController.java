package com.cosmos.controller.user;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.cosmos.dto.*;
import com.cosmos.service.*;


@Controller
public class MemberController {
	
	//의존성 주입 Spring bean으로 관리
	@Autowired
	private MemberService ms;
	
	@Autowired
	CodeService cs;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@PostMapping("member/login")
	public String login(Member member, HttpSession sess, Model model) {
		log.info("Member id : " + member.getId());
		log.info("Member pw : " + member.getPw());
		
		Map<String,String> map = ms.login(member);
		
		String viewPage = null;
		
		switch(map.get("stat")) {
		case "ok" : 
			//로그인성공, 세션값설정
			sess.setAttribute("sess_id", member.getId());
			sess.setAttribute("sess_name", map.get("name"));
			viewPage = "redirect:/";
			break;
		default : 
			//id없는 경우, 패스워드 불일치
			model.addAttribute("loginStat", "loginFail");
			viewPage = "home";
		}
		
		return viewPage;
	}
	
	@GetMapping("member/logOut")
	public String logOut(HttpSession sess) {
		
		sess.invalidate();
	    return "redirect:/";
	}
	
	@GetMapping("member/myPage")
	public void myPage(HttpSession sess, Model model) {
		Member m = ms.selectMember(sess.getAttribute("sess_id").toString());
		model.addAttribute(m);
		model.addAttribute("hobby", cs.getCodeList("hobby"));
	}
	
	@PostMapping("member/modify")
	public String modify(Member member, HttpSession sess, Model model) {
		int rs = ms.update(member);
		
		if(rs > 0) {
			model.addAttribute(member);
			model.addAttribute("hobby", cs.getCodeList("hobby"));
		}		
		return "member/memView";
	}
	
	@GetMapping("member/goOut")
	public String goOut(HttpSession sess) {
		
		ms.delete(sess.getAttribute("sess_id").toString());
		
		//sess.invalidate();
		//return "redirect:/";
		return "redirect:/member/logout";
	}
	
	@GetMapping("member/memRegForm")
	public void memRegForm() {		
	}
	
	@PostMapping("member/idDoubleCheck")
	public ResponseEntity<String> idDoubleCheck(@RequestParam("idstr") String id) {
		log.info("idDoubleCheck called... id : " + id);
		String rs = Integer.toString(ms.idCheck(id));
		return new ResponseEntity<String>(rs, HttpStatus.OK);
	}
	
	@PostMapping("member/register")
	public String register(Member member, RedirectAttributes rttr) {
		ms.insert(member);	
		rttr.addFlashAttribute("msg", 1);
		return "redirect:/";
	}
	
//	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
//
//		response.setContentType("text/html; charset=utf-8");
//		request.setCharacterEncoding("utf-8");
//		
////		//프린트라이터로 직접 문자열써주기
////		PrintWriter w = response.getWriter();
////		w.append("<HTML><BODY><H2>반갑습니다.정채우입니다.</H2></BODY></HTML>");
//		
//		PrintWriter out = response.getWriter();
//		
//		String uri = request.getRequestURI();
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);
//		
//		System.out.println("uri = " + cmd);
//		
//		String viewPage = null;
//		
//		if(cmd.equals("memReg.me")) {
//			//회원등록폼 출력
//			viewPage = "/member/memRegForm.jsp";
//			
//			goView(request, response, viewPage);
//			
//		} else if(cmd.equals("memInsert.me")) {
//			//회원등록 처리요청
//			ms.insert(request);
//			response.sendRedirect("/home.jsp?isMemRegSuccess=1");
//			
////			request.setAttribute("isMemRegSuccess",1);
////			goView(request, response, "/");
//			
//		} else if(cmd.equals("idCheck.me")) {
//			//id중복검사
//			String id = request.getParameter("idstr");
//			int rs = ms.idCheck(id);
//			out.print(Integer.toString(rs));
//			System.out.println(rs);
//		} else if(cmd.equals("logOut.me")) {
//			//세션파괴하고 루트부르기
//			HttpSession sess = request.getSession();
//			sess.invalidate();
//			response.sendRedirect("/");
//		} else if(cmd.equals("myPage.me")) {
//			Member m = ms.selectMember(request.getSession().getAttribute("sess_id").toString());
//			request.setAttribute("member", m);
//			
//			CodeService code = new CodeServiceImple();
//			request.setAttribute("hobby", code.getCodeList("hobby"));
//			
//			goView(request, response, "/WEB-INF/views/user/member/myPage.jsp");
//		} else if(cmd.equals("memberModify.me")) {
//			//회원 수정처리
//			Member m = ms.update(request);
//			
//			request.setAttribute("member", m);
//			
//			goView(request, response, "/member/memView.jsp");
//		}
//	}
	
	private void goView(HttpServletRequest request, HttpServletResponse response, String viewPage) throws ServletException, IOException {
		//디스패쳐로 페이지 불러오기
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	
	
	

}
