package com.fatec.javaweb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.javaweb.component.ConjuntoServidoresJson;
import com.fatec.javaweb.component.CrawlerPortalTransparencia;
import com.fatec.javaweb.component.ServidorInfoMinJson;
import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.repository.ServidorPublicoRepository;
import com.fatec.javaweb.service.ServidorPublicoService;

@Controller
@RequestMapping("")
public class inicialController {
	
	
	@GetMapping(value = "")
	public ModelAndView inicial() {
		ModelAndView mv = new ModelAndView("inicial/index");
		return mv;
	}


	

	@GetMapping(value = "/administrativo")
	@ResponseBody
	public String adm() {
		return "kdaksdljaksdjalksjdl";
	}

	
}
