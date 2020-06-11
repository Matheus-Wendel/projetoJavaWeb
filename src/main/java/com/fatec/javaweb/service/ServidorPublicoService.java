package com.fatec.javaweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.javaweb.component.ConjuntoServidoresJson;
import com.fatec.javaweb.component.CrawlerPortalTransparencia;
import com.fatec.javaweb.component.ServidorInfoMinJson;
import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.repository.ServidorPublicoRepository;

@Service
public class ServidorPublicoService {
	
	@Autowired
	ServidorPublicoRepository servidorPublicoRepository;
	
	public List<ServidorPublico> listaInseridosNoBanco() {
		return servidorPublicoRepository.findAll();
	}
	
	
	public void preencher(int n) throws IOException {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();
		ConjuntoServidoresJson conjuntoServidores;
		conjuntoServidores = crawler.getConjuntoServidores();

		int i=0;
		
		for (ServidorInfoMinJson servidorInfoMin : conjuntoServidores.getServidores()) {
			servidorPublicoRepository.save(crawler.getServidorPublico(servidorInfoMin));
			System.err.println(("Inserido " + servidorInfoMin.getNome()));
			i++;
			if(i==n) {
				break;
			}
		}
		
	}
	
	
	

}
