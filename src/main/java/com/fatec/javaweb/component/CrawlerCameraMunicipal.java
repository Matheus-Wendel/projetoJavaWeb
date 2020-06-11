package com.fatec.javaweb.component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.javaweb.model.leis.ProjetoLei;

public class CrawlerCameraMunicipal {

	public List<ProjetoLei> abreArquivo() throws IOException {
		
		
		Resource resource = new ClassPathResource("projetos.json");

		File file = resource.getFile();

		ObjectMapper objectMapper = new ObjectMapper();

		ConjuntoProjetoLeiJson lista = objectMapper.readValue(file, ConjuntoProjetoLeiJson.class);

		List<ProjetoLei> listaProjetoLei =new ArrayList<>();
		
//		listaProjetoLei.addAll(lista.getDecretoLegislativo());
//		listaProjetoLei.addAll(lista.getLeiComplementar());
		listaProjetoLei.addAll(lista.getLeiOrdinaria());
//		listaProjetoLei.addAll(lista.getLeiOrganica());
//		listaProjetoLei.addAll(lista.getResolucao());

		return listaProjetoLei;
	}
	
	
}

