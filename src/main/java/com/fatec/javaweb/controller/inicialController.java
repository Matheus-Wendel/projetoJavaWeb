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
import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.repository.ServidorPublicoRepository;

@Controller
@RequestMapping("")
public class inicialController {
	@Autowired
	ServidorPublicoRepository servidorPublicoRepository;
	
	
	@GetMapping(value = "")
	public ModelAndView inicial() {
		ModelAndView mv = new ModelAndView("inicial/index");
		return mv;
	}
	@GetMapping(value = "/nomeESalario")
	@ResponseBody
	public ConjuntoServidoresJson TesteCrawler() throws IOException {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();
		
		List<ServidorPublico> servidores = crawler.getServidorPublicos(10);
		for (int i = 0; i < 10; i++) {
			servidorPublicoRepository.save(servidores.get(i));
			System.err.println(i);
		}
		try {
			return crawler.getConjuntoServidores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConjuntoServidoresJson();
		
	}

	@GetMapping(value = "/doBanco")
	@ResponseBody
	public List<ServidorPublico> listaInseridosNoBanco() {
		return servidorPublicoRepository.findAll();
	}
	@GetMapping(value = "/administrativo")
	@ResponseBody
	public String adm() {
		return "kdaksdljaksdjalksjdl";
	}
}
