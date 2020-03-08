package com.fatec.javaweb.component;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.javaweb.deserializer.ReferenciaDeserializer;
import com.fatec.javaweb.model.Salario.Desconto;
import com.fatec.javaweb.model.Salario.Outro;
import com.fatec.javaweb.model.Salario.Rendimento;
import com.fatec.javaweb.model.Salario.Total;

public class ServidorInfoMaxJson {

	private String cargo;
	private String nome;
	private String regime;

	@JsonDeserialize(using = ReferenciaDeserializer.class)
	private LocalDate referencia;

	private List<Rendimento> rendimentos;
	private List<Desconto> descontos;
	private List<Total> totais;
	private List<Outro> outros;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public LocalDate getReferencia() {
		return referencia;
	}

	public void setReferencia(LocalDate referencia) {
		this.referencia = referencia;
	}

	public List<Rendimento> getRendimentos() {
		return rendimentos;
	}

	public void setRendimentos(List<Rendimento> rendimentos) {
		this.rendimentos = rendimentos;
	}

	public List<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<Desconto> descontos) {
		this.descontos = descontos;
	}

	public List<Total> getTotais() {
		return totais;
	}

	public void setTotais(List<Total> totais) {
		this.totais = totais;
	}

	public List<Outro> getOutros() {
		return outros;
	}

	public void setOutros(List<Outro> outros) {
		this.outros = outros;
	}

}