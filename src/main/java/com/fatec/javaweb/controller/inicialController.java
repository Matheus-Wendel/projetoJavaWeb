package com.fatec.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	@PostMapping(value = "/testeJack")
	@ResponseBody
	public String serializacao() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(servidorPublicoRepository.findAll().get(0));

	}
}
