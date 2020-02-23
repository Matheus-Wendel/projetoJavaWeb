package com.fatec.javaweb.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.desrializer.RendimentoDeserializer;

public class ComponenteFolhaPagamento implements BaseModel {
	private String nome;

	@JsonDeserialize(using = RendimentoDeserializer.class)
	private Double valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ComponeteFolhaPagamento [nome=" + nome + ", valor=" + valor + "]";
	}

}
