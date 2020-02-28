package com.fatec.javaweb.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.ServidorInfoMaxDeserializer;

import lombok.Data;
@Data
@JsonDeserialize(using = ServidorInfoMaxDeserializer.class)
public class ServidorInfoMax implements BaseModel {

	private Long id;
	
	
	private String cargo;
	
	private String nome;
	private String regime;
	private List<ComponenteFolhaPagamento> rendimentos;
	private List<ComponenteFolhaPagamento> descontos;
	private List<ComponenteFolhaPagamento> totais;
	private List<ComponenteFolhaPagamento> outros;


}
