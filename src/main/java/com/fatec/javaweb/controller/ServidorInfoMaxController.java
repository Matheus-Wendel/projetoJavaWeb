package com.fatec.javaweb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatec.javaweb.model.ServidorInfoMax;
import com.fatec.javaweb.model.ServidorInfoMin;
import com.fatec.javaweb.repository.ServidorInfoMaxRepository;
import com.fatec.javaweb.service.CrawlerPortalTransparencia;

@Controller
@RequestMapping("/servidor")
public class ServidorInfoMaxController {
	@Autowired
	ServidorInfoMaxRepository repository;

	@GetMapping(value = "/livre/busca/nome/{nome}")
	@ResponseBody
	public List<ServidorInfoMax> buscarServidorPorNome(@PathVariable(required = false, value = "nome") String nome) {
		return repository.findByNome(nome);

	}
	@GetMapping(value = "/livre/todos")
	@ResponseBody
	public List<ServidorInfoMax> buscarTodos() {
		return repository.findAll();
		
	}
	
	@GetMapping(value = "/livre/busca/id/{id}")
	@ResponseBody
	public ServidorInfoMax buscarServidorPorNome(@PathVariable(required = false, value = "id") Long id) {
		return repository.findById(id).get();
		
	}

	@GetMapping(value = "/livre/inserir")
	@ResponseBody
	public String insereTesteNoBanco() throws IOException {

		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();

		List<ServidorInfoMin> servidores = crawler.getConjuntoServidores().getServidores();

		List<ServidorInfoMax> ListaservidoresInfoMax = crawler.getServidoresInfoMax(servidores, 50);

		repository.saveAll(ListaservidoresInfoMax);

		return "Dados inseridos no Banco";

	}

}
