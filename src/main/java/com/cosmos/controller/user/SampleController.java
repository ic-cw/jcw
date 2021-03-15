package com.cosmos.controller.user;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.cosmos.dto.*;

@Controller
public class SampleController {

	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")
	public String doA() {
		log.info("doA called...");
		
		return "sample/doA";
	}

	@RequestMapping("doB")
	public String doB(@ModelAttribute("msg") String msg, Model model) {
		log.info("doB called...{}",msg);
		
		msg += "반갑습니다.";
		
		Member m = new Member();
		m.setId("chae");
		m.setName("정채우");
		
		log.info(msg);
		model.addAttribute("msg",msg);
		model.addAttribute("member",m);
		return "sample/doB";
	}
	@RequestMapping("doC")
	public String doC(RedirectAttributes rttr) {
//		log.info("doC called...");
		System.out.println("doC called...");
		
		rttr.addFlashAttribute("msg","who am i??");
		return "redirect:/doD";
	}
	@RequestMapping("doD")
	public String doD(Model model, @ModelAttribute("msg") String msg) {
//		log.info("doC called...");
		System.out.println("doD called..." + msg);
		model.addAttribute("msg", msg);
		
		return "sample/doD";
	}
	@RequestMapping("doE")
	public ModelAndView doE() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sample/doList");
		mv.addObject("msg", "this is here!!");
		
		return mv;
	}
	@RequestMapping("doJSON")
	public @ResponseBody Board doJSON() {
		
		Board b = new Board();
		b.setTitle("별헤는 밤");
		b.setContent("별1, 별2, 별3");
		b.setId("chae");
		
		return b;
	}
}
