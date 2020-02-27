package com.fatec.javaweb.model;

import java.util.List;

public class ServidorInfoMax implements BaseModel {

	private String referencia;
	private String cargo;
	private String nome;
	private String regime;
	private List<ComponenteFolhaPagamento> rendimentos;
	private List<ComponenteFolhaPagamento> descontos;
	private List<ComponenteFolhaPagamento> totais;
	private List<ComponenteFolhaPagamento> outros;

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

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

	public List<ComponenteFolhaPagamento> getRendimentos() {
		return rendimentos;
	}

	public void setRendimentos(List<ComponenteFolhaPagamento> rendimentos) {
		this.rendimentos = rendimentos;
	}

	public List<ComponenteFolhaPagamento> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<ComponenteFolhaPagamento> descontos) {
		this.descontos = descontos;
	}

	public List<ComponenteFolhaPagamento> getTotais() {
		return totais;
	}

	public void setTotais(List<ComponenteFolhaPagamento> totais) {
		this.totais = totais;
	}

	public List<ComponenteFolhaPagamento> getOutros() {
		return outros;
	}

	public void setOutros(List<ComponenteFolhaPagamento> outros) {
		this.outros = outros;
	}

	@Override
	public String toString() {
		return "ServidoresInfoMax [referencia=" + referencia + ", cargo=" + cargo + ", nome=" + nome + ", regime="
				+ regime + ", rendimentos=" + rendimentos + ", descontos=" + descontos + ", totais=" + totais
				+ ", outros=" + outros + "]";
	}

}
