package com.fatec.javaweb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.javaweb.ServidorInfoMinDAO;
import com.fatec.javaweb.model.ConjuntoServidores;
import com.fatec.javaweb.model.ServidorInfoMax;
import com.fatec.javaweb.model.ServidorInfoMin;
import com.fatec.javaweb.service.CrawlerPortalTransparencia;

@Controller
@RequestMapping("")
public class inicialController {
	
	@Autowired
	ServidorInfoMinDAO servidorInfoMinDAO;
	
	
	
	@GetMapping(value = "")
	public ModelAndView inicial() {
		ModelAndView mv = new ModelAndView("inicial/index");
		return mv;
	}
	@GetMapping(value = "/nomeESalario")
	@ResponseBody
	public ConjuntoServidores TesteCrawler() throws IOException {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();
		
		List<ServidorInfoMin> servidores = crawler.getConjuntoServidores().getServidores();
		for (int i = 0; i < 10; i++) {
			servidorInfoMinDAO.save(servidores.get(i));
			System.err.println(i);
		}
		try {
			return crawler.getConjuntoServidores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConjuntoServidores();
		
	}
	@GetMapping(value = "/rgf/{rgf}")
	@ResponseBody
	public ServidorInfoMax TesteCrawler(@PathVariable(name= "rgf") String rgf) {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();
		ServidorInfoMin min = new ServidorInfoMin();
		
		min.setRgf(rgf);
		
		try {
			return crawler.getServidoresInfoMax(min);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ServidorInfoMax();
	}

	@GetMapping(value = "/doBanco")
	@ResponseBody
	public List<ServidorInfoMin> listaInseridosNoBanco() {
		return servidorInfoMinDAO.findAll();
	}
}
