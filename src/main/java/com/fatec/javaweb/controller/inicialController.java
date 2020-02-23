package com.fatec.javaweb.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.javaweb.model.ConjuntoServidores;
import com.fatec.javaweb.service.CrawlerPortalTransparencia;

@Controller
@RequestMapping("")
public class inicialController {
	
	@GetMapping(value = "")
	public ModelAndView inicial() {
		ModelAndView mv = new ModelAndView("inicial/index");
		return mv;
	}
	@GetMapping(value = "/nomeESalario")
	@ResponseBody
	public ConjuntoServidores TesteCrawler() {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();
		
		try {
			return crawler.getConjuntoServidores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConjuntoServidores();
	}

}
