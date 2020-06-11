package com.fatec.javaweb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.service.ServidorPublicoService;

@Controller
@RequestMapping("/servidorPublico")
public class ServidorPublicoController {
	
	@Autowired
	ServidorPublicoService servidorPublicoService;

	@GetMapping(value = "/todos")
	@ResponseBody
	public List<ServidorPublico> listaInseridosNoBanco() {
		return servidorPublicoService.listaInseridosNoBanco(); 
	}
	
	@GetMapping("/preencher/{n}")
	public ResponseEntity<Void> preencher(@PathVariable (name = "n") Integer n) throws IOException{
		servidorPublicoService.preencher(n);
		return ResponseEntity.ok().build();
	}
	
}
