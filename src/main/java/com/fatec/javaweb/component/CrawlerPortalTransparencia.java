package com.fatec.javaweb.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.javaweb.model.DetalheServidorPublico;
import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.model.Salario.Salario;

public class CrawlerPortalTransparencia {

	private final String URL_INFO_MIN = "http://www.licitacao.pmmc.com.br/Transparencia/vencimentos2";
	private final String URL_INFO_MAX = "http://www.licitacao.pmmc.com.br/Transparencia/detalhamento?rgf=";

	private final ObjectMapper objectMapper = new ObjectMapper();

	private String TempoDecorridoUltimaAcao = "";
	private Long inicioTimer;
	private Long fimTimer;

	public ConjuntoServidoresJson getConjuntoServidores() throws IOException {

		iniciaTimer();
		String jsonServidorInfoMin = Jsoup.connect(URL_INFO_MIN).ignoreContentType(true).execute().body();
		paraTimer();

		return objectMapper.readValue(jsonServidorInfoMin, ConjuntoServidoresJson.class);

	}

	public List<ServidorPublico> getServidoresPublicos() throws IOException {

		List<ServidorPublico> listaServidoresPublicos = new ArrayList<>();
		iniciaTimer();
		for (ServidorInfoMinJson servidorInfoMin : getConjuntoServidores().getServidores()) {
			String rgf = servidorInfoMin.getRgf();
			String jsonServidorInfoMax = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute()
					.body();

			ServidorInfoMaxJson servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMaxJson.class);
			listaServidoresPublicos.add(montaServidorPublico(servidorInfoMin, servidor));
		}
		paraTimer();
		return listaServidoresPublicos;

	}

	public List<ServidorPublico> getServidorPublicos(int quantidadeServidores) throws IOException {
		List<ServidorPublico> listaServidoresPublicos = new ArrayList<>();
		iniciaTimer();
		List<ServidorInfoMinJson> servidores = getConjuntoServidores().getServidores();
		for (int i = 0; i < quantidadeServidores; i++) {
			
			String rgf = servidores.get(i).getRgf();
			String jsonServidorInfoMax = Jsoup.connect(URL_INFO_MAX + rgf).timeout(0).ignoreContentType(true).execute()
					.body();
			
			ServidorInfoMaxJson servidor = objectMapper.readValue(jsonServidorInfoMax, ServidorInfoMaxJson.class);
			listaServidoresPublicos.add(montaServidorPublico(servidores.get(i), servidor));
		}
		
		
		paraTimer();
		return listaServidoresPublicos;
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
		servidorPublico.setDetalhes(Arrays.asList(detalhes));

		return servidorPublico;
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
