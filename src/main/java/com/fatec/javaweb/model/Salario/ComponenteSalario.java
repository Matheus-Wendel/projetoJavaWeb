package com.fatec.javaweb.model.Salario;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.RendimentoDeserializer;
import com.fatec.javaweb.model.BaseModel;

@MappedSuperclass
public abstract class ComponenteSalario implements BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	@JsonDeserialize(using = RendimentoDeserializer.class)
	private Double valor;

	public Long getId() {
		return id;
	}

	//comentario
	public void setId(Long id) {
		this.id = id;
	}

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

	
	
}
