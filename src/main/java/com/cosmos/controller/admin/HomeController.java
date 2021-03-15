package com.cosmos.controller.admin;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	   public String home() {
	      log.info("Welcome admin!");      
	      return "home";
	}

}
