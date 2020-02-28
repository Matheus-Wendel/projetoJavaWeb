package com.fatec.javaweb.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.RendimentoDeserializer;

import lombok.Data;
@Data
public class ComponenteFolhaPagamento implements BaseModel {
	private String nome;

	@JsonDeserialize(using = RendimentoDeserializer.class)
	private Double valor;
	
	
	
	
}
