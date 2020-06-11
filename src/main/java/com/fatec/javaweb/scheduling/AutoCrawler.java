package com.fatec.javaweb.scheduling;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.javaweb.component.ConjuntoServidoresJson;
import com.fatec.javaweb.component.CrawlerPortalTransparencia;
import com.fatec.javaweb.component.ServidorInfoMinJson;
import com.fatec.javaweb.model.ServidorPublico;
import com.fatec.javaweb.model.Salario.Salario;
import com.fatec.javaweb.repository.ServidorPublicoRepository;

@Component
public class AutoCrawler {
	@Autowired
	ServidorPublicoRepository servidorPublicoRepository;

	@Transactional
	@Scheduled(fixedDelay  = 500000000,initialDelay = 500000000)
	public void reportCurrentTime() {
		CrawlerPortalTransparencia crawler = new CrawlerPortalTransparencia();

		ConjuntoServidoresJson conjuntoServidores;

		try {

			conjuntoServidores = crawler.getConjuntoServidores();
			LocalDate dataReferencia = converteReferencia(conjuntoServidores.getReferencia());

			for (ServidorInfoMinJson servidorInfoMin : conjuntoServidores.getServidores()) {

				Optional<ServidorPublico> servidorOptional = servidorPublicoRepository
						.findByRgf(servidorInfoMin.getRgf());
				if (servidorOptional.isPresent()) {

					ServidorPublico servidorPublico = servidorOptional.get();
					List<Salario> salarioServidor = servidorPublico.getDetalhes().getSalario();
					List<LocalDate> datasSalarios = salarioServidor.stream().map(s -> s.getDataReferencia())
							.collect(Collectors.toList());

					if (!datasSalarios.contains(dataReferencia)) {
						ServidorPublico servidorCrawler = crawler.getServidorPublico(servidorInfoMin);
						servidorPublico.getDetalhes().getSalario().addAll(servidorCrawler.getDetalhes().getSalario());

						servidorPublicoRepository.save(servidorPublico);

						System.err.println(("atualizado " + servidorInfoMin.getNome()));

					} else {

						System.err.println(("pulado " + servidorInfoMin.getNome()));
					}
				} else {

					System.err.println(("Inserido " + servidorInfoMin.getNome()));
					servidorPublicoRepository.save(crawler.getServidorPublico(servidorInfoMin));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public LocalDate converteReferencia(String data) {
		DateTimeFormatter formatadorReferencia = DateTimeFormatter.ofPattern("MMM/yyyy", new Locale("pt", "BR"));

		YearMonth mesAnoReferencia = YearMonth.parse(data.toLowerCase(), formatadorReferencia);
		LocalDate dataReferencia = mesAnoReferencia.atDay(1);
		return dataReferencia;
	}
}
