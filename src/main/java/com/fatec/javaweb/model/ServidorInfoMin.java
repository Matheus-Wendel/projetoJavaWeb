package com.fatec.javaweb.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.RendimentoDeserializer;

public class ServidorInfoMin implements BaseModel {

	

	private String rgf;
	private String nome;
	private String cargo;
	@JsonDeserialize(using = RendimentoDeserializer.class)
	private Double rendimentos;

	
	
	
	public ServidorInfoMin() {
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

	public void setRendimentos(Double rendimento) {
		this.rendimentos = rendimento;
	}
	

	public String getRgf() {
		return rgf;
	}

	public void setRgf(String rgf) {
		this.rgf = rgf;
	}

	@Override
	public String toString() {
		return "ServidoresInfoMin [rgf=" + rgf + ", nome=" + nome + ", cargo=" + cargo + ", rendimentos=" + rendimentos + "]";
	}

	
}
