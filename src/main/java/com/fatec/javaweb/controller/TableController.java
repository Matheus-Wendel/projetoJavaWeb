package com.fatec.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dados")
public class TableController {
	
	@GetMapping("")
	public ModelAndView getTabela() {
		ModelAndView mv = new ModelAndView("demo/tabela");
		return mv;
	}

}
