package com.fatec.javaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.RendimentoDeserializer;

@Entity
public class ServidorPublico implements BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String rgf;
	private String nome;
	private String cargo;

	@JsonDeserialize(using = RendimentoDeserializer.class)
	private Double rendimentos;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sevidor")
	@Cascade(CascadeType.ALL)
	private DetalheServidorPublico detalhes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DetalheServidorPublico getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(DetalheServidorPublico detalhes) {
		this.detalhes = detalhes;
	}

	public ServidorPublico() {
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
		return "ServidoresInfoMin [rgf=" + rgf + ", nome=" + nome + ", cargo=" + cargo + ", rendimentos=" + rendimentos
				+ "]";
	}

}
