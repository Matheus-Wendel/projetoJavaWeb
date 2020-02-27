package com.fatec.javaweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.javaweb.model.ConjuntoServidores;
import com.fatec.javaweb.model.ServidorInfoMax;
import com.fatec.javaweb.model.ServidorInfoMin;

public class CrawlerPortalTransparencia {

	private final String URL_INFO_MIN = "http://www.licitacao.pmmc.com.br/Transparencia/vencimentos2";
	private final String URL_INFO_MAX = "http://www.licitacao.pmmc.com.br/Transparencia/detalhamento?rgf=";

	private final ObjectMapper objectMapper = new ObjectMapper();

	private String TempoDecorridoUltimaAcao = "";
	private Long inicioTimer;
	private Long fimTimer;

	public ConjuntoServidores getConjuntoServidores() throws IOException {

		iniciaTimer();
		String jsonServidorInfoMin = Jsoup.connect(URL_INFO_MIN).ignoreContentType(true).execute().body();
		paraTimer();

		return objectMapper.readValue(jsonServidorInfoMin, ConjuntoServidores.class);

	}

	public List<ServidorInfoMax> getServidoresInfoMax() throws IOException {

		List<ServidorInfoMax> ListaServidorInfoMax = new ArrayList<>();
		iniciaTimer();
		for (ServidorInfoMin servidorInfoMin : getConjuntoServidores().getServidores()) {
			String rgf = servidorInfoMin.getRgf();
			String jsonServidorInfoMax = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute()
					.body();

			ServidorInfoMax servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMax.class);
			ListaServidorInfoMax.add(servidor);
		}
		paraTimer();
		return ListaServidorInfoMax;

	}

	public List<ServidorInfoMax> getServidoresInfoMax(List<ServidorInfoMin> listaServidoresInfoMin) throws IOException {

		List<ServidorInfoMax> ListaServidorInfoMax = new ArrayList<>();
		iniciaTimer();
		for (ServidorInfoMin servidorInfoMin : listaServidoresInfoMin) {
			String rgf = servidorInfoMin.getRgf();
			String jsonServidorInfoMax = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute()
					.body();

			ServidorInfoMax servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMax.class);
			ListaServidorInfoMax.add(servidor);
		}
		paraTimer();
		return ListaServidorInfoMax;

	}

	public ServidorInfoMax getServidoresInfoMax(ServidorInfoMin servidorInfoMin) throws IOException {

		String rgf = servidorInfoMin.getRgf();
		iniciaTimer();
		String jsonServidorInfoMax = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute()
				.body();

		paraTimer();
		return objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMax.class);

	}

	private void iniciaTimer() {
		this.inicioTimer = System.currentTimeMillis();
	}

	private void paraTimer() {
		this.fimTimer = System.currentTimeMillis() - inicioTimer;

		this.TempoDecorridoUltimaAcao = String.format("%02d:%02d:%02d %02d", TimeUnit.MILLISECONDS.toHours(fimTimer),
				TimeUnit.MILLISECONDS.toMinutes(fimTimer) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(fimTimer) % TimeUnit.MINUTES.toSeconds(1),
				TimeUnit.MILLISECONDS.toMillis(fimTimer) % TimeUnit.SECONDS.toMillis(1));

	}

	public String getTempoDecorridoUltimaAcao() {
		return TempoDecorridoUltimaAcao;
	}

	public void setTempoDecorridoUltimaAcao(String tempoDecorridoUltimaAcao) {
		TempoDecorridoUltimaAcao = tempoDecorridoUltimaAcao;
	}

}
