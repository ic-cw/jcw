package com.cosmos.controller.user;



import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.cosmos.dto.*;
import com.cosmos.service.*;


@Controller
//매핑에서 공통된 정보는 리퀘스트 매핑으로 빼준다
@RequestMapping(value="/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	
//	@Autowired
//	private ReplyService replyService;
	
	
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("board", boardService.getBoardList());
	}
	
	@GetMapping("regForm")
	public void regForm() {
	}
	
	@PostMapping("register")
	public String register(Board board, MultipartFile files, HttpSession sess, Model model) {
//		log.info("제목 : " + board.getTitle());
//		log.info("내용 : " + board.getContent());
//		
//		log.info("첨부파일 이름 : " + files.getOriginalFilename());
//		log.info("첨부파일 크기 : " + files.getSize() );
//		log.info("첨부파일 타입 : " + files.getContentType());
		
		board.setId((String)sess.getAttribute("sess_id"));
		
		boardService.insertBoard(board, files, sess);
//		if(rs > 0) {
//			model.addAttribute(board);
//		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("view")
	public void view(@ModelAttribute("bno") int bno, Model model) {
		log.info(boardService.getBoard(bno).getUpfile().getSaveFileName().toString());
		model.addAttribute("board", boardService.getBoard(bno));
		
	}
	
	@PostMapping("modify")
	public String modify(Board board, RedirectAttributes rttr, MultipartFile files, HttpSession sess) {
		boardService.updateBoard(board);
		if(files != null && files.getSize() >0) {
			fileService.fileUpload(files, sess, board.getBno());
		}
		rttr.addFlashAttribute("bno", board.getBno());
		return "redirect:/board/view";
	}
	
	@PostMapping("modifyForm")
	public void modifyForm(Board board, UploadFile file, Model model) {
		model.addAttribute("board", board);
		model.addAttribute("uploadFile", file);
	}
	
	@PostMapping("boardDel")
	public String boardDel(int bno, HttpSession sess, String savefilename, MultipartFile files) {
		if(files != null) {
			String fileUploadPath = sess.getServletContext().getRealPath("/resources/upload/");
			fileService.fileDelete(bno);
			fileService.fileDel(fileUploadPath, savefilename);
		}
		boardService.deleteBoard(bno);
		return "redirect:/board/list";
	}
//       
//	//get이나 post방식으로 던지면 doAction으로 가게끔
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doAction(request, response);
//	}
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doAction(request, response);
//	}
//
//	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
//		//게시판 리스트 출력
//		if(cmd.equals("boardList.bo")) {
//			ArrayList<Board> b = board.getBoardList();
//			request.setAttribute("board", b);
//			goView(request, response, "/board/boardList.jsp");
//			
//		} else if(cmd.equals("boardRegForm.bo")) {
////			ArrayList<Board> b = board.getBoardList();
////			request.setAttribute("board", b);
//			goView(request, response, "/board/boardRegForm.jsp");
//			
//		} else if(cmd.equals("boardRegProc.bo")) {
//			int bno = board.insertBoard(request, response);
//			
//			Board b2 = board.getBoard(bno);
//
//			request.setAttribute("board", b2);
//			goView(request, response, "/board/boardDetailView.jsp");
//			
//		} else if(cmd.equals("boardDetailView.bo")) {
//			int bno = Integer.parseInt(request.getParameter("bno"));
//			System.out.println(bno);
//			
//			Board b2 = board.getBoard(bno);
//			request.setAttribute("board", b2);
//			goView(request, response, "/board/boardDetailView.jsp");
//		} else if(cmd.equals("boardModifyForm.bo")) {
//			Board b = setBoard(request);
//			request.setAttribute("board", b);
//			
//			goView(request, response, "/board/boardModifyForm.jsp");
//		} else if(cmd.equals("UpdateBoard.bo")) {
//			int bno = board.updateBoard(request, response);			
//			
//			Board b2= board.getBoard(bno);
//			
//			request.setAttribute("board", b2);
//			goView(request, response, "/board/boardDetailView.jsp");
//		} else if(cmd.equals("boardDel.bo")) {
////			int bno = Integer.parseInt(request.getParameter("bno"));
////	         
////	        int rs = board.deleteBoard(bno);
////	        
////	        request.setAttribute("delstat", rs);
////	        System.out.println("삭제여부 : " + rs);
////	        goView(request, response, "boardList.bo");		
//			String filename = request.getParameter("filename");
//			int rs = board.deleteBoard(Integer.parseInt(request.getParameter("bno")));
//			
//			if(rs > 0) {
//				String fileUploadPath = request.getSession().getServletContext().getRealPath("/upload");
//				String saveFileName = request.getParameter("savefilename");
//				fileService.fileDel(fileUploadPath, saveFileName);
//			}
//			
//			request.setAttribute("delstat", rs);
//			System.out.println("삭제여부:" + rs);
//			goView(request, response, "boardList.bo");
//		}
//	}
	

}
