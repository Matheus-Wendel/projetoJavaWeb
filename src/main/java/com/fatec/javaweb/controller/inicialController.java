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

	@GetMapping(value = "/cadastrar")
	@ResponseBody
	public String serializacao() throws IOException {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();
		ConjuntoServidoresJson conjuntoServidores;

		conjuntoServidores = crawler.getConjuntoServidores();
		
		
		for (ServidorInfoMinJson servidorInfoMin : conjuntoServidores.getServidores()) {
			int i = 0;
			servidorPublicoRepository.save(crawler.getServidorPublico(servidorInfoMin));
			System.err.println(("Inserido " + servidorInfoMin.getNome()));
			if(i==50) {
				break;
			}
			
		}
		
		return "feito";

	}
}
