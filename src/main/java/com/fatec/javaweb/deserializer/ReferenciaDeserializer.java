package com.fatec.javaweb.deserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ReferenciaDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {


		DateTimeFormatter formatadorReferencia = DateTimeFormatter.ofPattern("MMM/yyyy", new Locale("pt", "BR"));

		YearMonth mesAnoReferencia = YearMonth.parse(p.getValueAsString().toLowerCase(),
				formatadorReferencia);
		LocalDate dataReferencia = mesAnoReferencia.atDay(1);

		return dataReferencia;
	}

}
