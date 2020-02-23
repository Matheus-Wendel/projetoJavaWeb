package com.fatec.javaweb.desrializer;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class RendimentoDeserializer extends JsonDeserializer<Double>{
	@Override
	public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
	    Number number;
	    Double d = null;
		try {
			number = format.parse(p.getValueAsString());
			 d = number.doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

}
