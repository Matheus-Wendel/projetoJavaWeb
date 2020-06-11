package com.fatec.javaweb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatec.javaweb.component.CrawlerCameraMunicipal;
import com.fatec.javaweb.model.leis.ProjetoLei;
import com.fatec.javaweb.repository.leis.ProjetoLeiRepository;

@Controller
@RequestMapping("/teste")
public class testeController {

	@Autowired
	ProjetoLeiRepository projetoLeiRepository;
	
	@GetMapping(value = "/abreJson")
	@ResponseBody
	public String TesteAbrirDocumento() throws IOException {
		CrawlerCameraMunicipal crawler = new CrawlerCameraMunicipal();
		
		List<ProjetoLei> abreArquivo = crawler.abreArquivo();
		projetoLeiRepository.saveAll(abreArquivo);
		
		
		return "teste";
	}
	@GetMapping(value = "/dobanco")
	@ResponseBody
	public List<ProjetoLei> getAll() throws IOException {
		
		return projetoLeiRepository.findAll();
		
		
	}
	
}
