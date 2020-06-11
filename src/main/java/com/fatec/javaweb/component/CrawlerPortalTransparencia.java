package com.fatec.javaweb.component;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.javaweb.model.DetalheServidorPublico;
import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.model.Salario.Salario;

public class CrawlerPortalTransparencia {

	private final String URL_INFO_MIN = "http://www.licitacao.pmmc.com.br/Transparencia/vencimentos2";
	private final String URL_INFO_MAX = "http://www.licitacao.pmmc.com.br/Transparencia/detalhamento?rgf=";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public ConjuntoServidoresJson getConjuntoServidores() throws IOException {
		String jsonServidorInfoMin = Jsoup.connect(URL_INFO_MIN).ignoreContentType(true).execute().body();
		return objectMapper.readValue(jsonServidorInfoMin, ConjuntoServidoresJson.class);

	}

	public ServidorInfoMaxJson servidoresInfoMaxJson(String rgf) throws IOException {
		String json = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute().body();
		return objectMapper.readValue(json, ServidorInfoMaxJson.class);
	}


	public ServidorPublico getServidorPublico(ServidorInfoMinJson servidorInfoMin) throws IOException {

		ServidorInfoMaxJson servidor = servidoresInfoMaxJson(servidorInfoMin.getRgf());
		return montaServidorPublico(servidorInfoMin, servidor);
	}


	public ServidorPublico montaServidorPublico(ServidorInfoMinJson servidorJson, ServidorInfoMaxJson detalhesJson) {

		Salario salario = new Salario();

		salario.setDataReferencia(detalhesJson.getReferencia());
		salario.setDescontos(detalhesJson.getDescontos());
		salario.setOutros(detalhesJson.getOutros());
		salario.setRendimentos(detalhesJson.getRendimentos());
		salario.setTotais(detalhesJson.getTotais());

		DetalheServidorPublico detalhes = new DetalheServidorPublico();

		detalhes.setCargo(detalhesJson.getCargo());
		detalhes.setNome(detalhesJson.getNome());
		detalhes.setRegime(detalhesJson.getRegime());

		detalhes.setSalario(Arrays.asList(salario));

		ServidorPublico servidorPublico = new ServidorPublico();
		servidorPublico.setCargo(servidorJson.getCargo());
		servidorPublico.setNome(servidorJson.getNome());
		servidorPublico.setRendimentos(servidorJson.getRendimentos());
		servidorPublico.setRgf(servidorJson.getRgf());
		servidorPublico.setDetalhes(detalhes);

		return servidorPublico;
	}

}
