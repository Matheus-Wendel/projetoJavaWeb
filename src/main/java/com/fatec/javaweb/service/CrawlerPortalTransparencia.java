package com.fatec.javaweb.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.ReferenciaDeserializer;
import com.fatec.javaweb.model.ConjuntoServidores;
import com.fatec.javaweb.model.ServidorInfoMax;
import com.fatec.javaweb.model.ServidorInfoMin;
import com.fatec.javaweb.model.Salario.Desconto;
import com.fatec.javaweb.model.Salario.Outro;
import com.fatec.javaweb.model.Salario.Rendimento;
import com.fatec.javaweb.model.Salario.Salario;
import com.fatec.javaweb.model.Salario.Total;

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

			ServidorInfoMaxJson servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMaxJson.class);
			ListaServidorInfoMax.add(servidor.converterParaServidorInfoMax());
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

			ServidorInfoMaxJson servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMaxJson.class);
			ListaServidorInfoMax.add(servidor.converterParaServidorInfoMax());
		}
		paraTimer();
		return ListaServidorInfoMax;

	}

	public List<ServidorInfoMax> getServidoresInfoMax(List<ServidorInfoMin> listaServidoresInfoMin,
			int quantidadeServidores) throws IOException {

		List<ServidorInfoMax> ListaServidorInfoMax = new ArrayList<>();
		iniciaTimer();
		for (int i = 0; i < quantidadeServidores; i++) {
			String rgf = listaServidoresInfoMin.get(i).getRgf();
			String jsonServidorInfoMax = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute()
					.body();
			ServidorInfoMaxJson servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMaxJson.class);
			ListaServidorInfoMax.add(servidor.converterParaServidorInfoMax());
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
		ServidorInfoMaxJson servidorInfoMaxJson = objectMapper.readValue(jsonServidorInfoMax,
				ServidorInfoMaxJson.class);

		return servidorInfoMaxJson.converterParaServidorInfoMax();
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

	static class ServidorInfoMaxJson {

		private String cargo;

		private String nome;
		private String regime;

		@JsonDeserialize(using = ReferenciaDeserializer.class)
		private LocalDate referencia;

		private List<Rendimento> rendimentos;
		private List<Desconto> descontos;
		private List<Total> totais;
		private List<Outro> outros;

		public ServidorInfoMax converterParaServidorInfoMax() {

			Salario salario = new Salario();
			ServidorInfoMax servidor = new ServidorInfoMax();
			List<Salario> listaSalario = new ArrayList<>();

			salario.setDataReferencia(referencia);
			salario.setRendimentos(rendimentos);
			salario.setDescontos(descontos);
			salario.setOutros(outros);
			salario.setTotais(totais);

			
			listaSalario.add(salario);

			servidor.setSalario(listaSalario);

			servidor.setCargo(cargo);
			servidor.setNome(nome);
			servidor.setRegime(regime);

			return servidor;
		}

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getRegime() {
			return regime;
		}

		public void setRegime(String regime) {
			this.regime = regime;
		}

		public LocalDate getReferencia() {
			return referencia;
		}

		public void setReferencia(LocalDate referencia) {
			this.referencia = referencia;
		}

		public List<Rendimento> getRendimentos() {
			return rendimentos;
		}

		public void setRendimentos(List<Rendimento> rendimentos) {
			this.rendimentos = rendimentos;
		}

		public List<Desconto> getDescontos() {
			return descontos;
		}

		public void setDescontos(List<Desconto> descontos) {
			this.descontos = descontos;
		}

		public List<Total> getTotais() {
			return totais;
		}

		public void setTotais(List<Total> totais) {
			this.totais = totais;
		}

		public List<Outro> getOutros() {
			return outros;
		}

		public void setOutros(List<Outro> outros) {
			this.outros = outros;
		}

	}

}
