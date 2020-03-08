package com.fatec.javaweb.component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.RendimentoDeserializer;

public class ServidorInfoMinJson {

	private String rgf;
	private String nome;
	private String cargo;

	@JsonDeserialize(using = RendimentoDeserializer.class)
	private Double rendimentos;

	public String getRgf() {
		return rgf;
	}

	public void setRgf(String rgf) {
		this.rgf = rgf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getRendimentos() {
		return rendimentos;
	}

	public void setRendimentos(Double rendimentos) {
		this.rendimentos = rendimentos;
	}

}
