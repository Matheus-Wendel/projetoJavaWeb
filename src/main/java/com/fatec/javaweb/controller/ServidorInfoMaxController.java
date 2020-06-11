package com.fatec.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatec.javaweb.model.DetalheServidorPublico;
import com.fatec.javaweb.model.ServidorPublico;
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
	public List<ServidorPublico> buscarServidorPorNome(@PathVariable(required = false, value = "nome") String nome) {
		return servidorPublicorepository.findByNome(nome);

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
	@GetMapping(value = "/livre/busca/salarioMaior/{salario}")
	@ResponseBody
	public List<ServidorPublico> buscarServidorPorSalarioMaiorQue(@PathVariable(required = true, value = "salario") Double salario) {
		return servidorPublicorepository.buscaPorSalarioMaiorQue(salario);
		
	}
	@GetMapping(value = "/livre/busca/salarioMenor/{salario}")
	@ResponseBody
	public List<ServidorPublico> buscarServidorPorSalarioMenorQue(@PathVariable(required = true, value = "salario") Double salario) {
		return servidorPublicorepository.buscaPorSalarioMaiorQue(salario);
		
	}

	

}
