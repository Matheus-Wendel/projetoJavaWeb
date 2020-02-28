package com.fatec.javaweb.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fatec.javaweb.model.ServidorInfoMax;

public class ServidorInfoMaxDeserializer extends JsonDeserializer<ServidorInfoMax>{

	@Override
	public ServidorInfoMax deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node =  p.getCodec().readTree(p);
		node.get("referencia");
		return new ServidorInfoMax();
	}

}
