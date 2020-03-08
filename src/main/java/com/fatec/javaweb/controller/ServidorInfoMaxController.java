package com.fatec.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatec.javaweb.model.DetalheServidorPublico;
import com.fatec.javaweb.repository.DetalheServidorPublicoRepository;
import com.fatec.javaweb.repository.ServidorPublicoRepository;

@Controller
@RequestMapping("/servidor")
public class ServidorInfoMaxController {
	@Autowired
	DetalheServidorPublicoRepository detalheServidorPublicorepository;
	
	@Autowired
	ServidorPublicoRepository servidorPublicorepository;

	@GetMapping(value = "/livre/busca/nome/{nome}")
	@ResponseBody
	public List<DetalheServidorPublico> buscarServidorPorNome(@PathVariable(required = false, value = "nome") String nome) {
		return detalheServidorPublicorepository.findByNome(nome);

	}
	@GetMapping(value = "/livre/todos")
	@ResponseBody
	public List<DetalheServidorPublico> buscarTodos() {
		return detalheServidorPublicorepository.findAll();
		
	}
	
	@GetMapping(value = "/livre/busca/id/{id}")
	@ResponseBody
	public DetalheServidorPublico buscarServidorPorNome(@PathVariable(required = false, value = "id") Long id) {
		return detalheServidorPublicorepository.findById(id).get();
		
	}

	

}
