package com.fatec.javaweb.model.Salario;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Salario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate dataReferencia;
	
	@OneToMany
	@JoinColumn(name = "salario_id")
	@Cascade(CascadeType.ALL)
	private List<Desconto> descontos;
	@OneToMany
	@JoinColumn(name = "salario_id")
	@Cascade(CascadeType.ALL)
	private List<Outro> outros;
	@OneToMany
	@JoinColumn(name = "salario_id")
	@Cascade(CascadeType.ALL)
	private List<Rendimento> rendimentos;
	@OneToMany
	@JoinColumn(name = "salario_id")
	@Cascade(CascadeType.ALL)
	private List<Total> Totais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(LocalDate dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public List<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<Desconto> descontos) {
		this.descontos = descontos;
	}

	public List<Outro> getOutros() {
		return outros;
	}

	public void setOutros(List<Outro> outros) {
		this.outros = outros;
	}

	public List<Rendimento> getRendimentos() {
		return rendimentos;
	}

	public void setRendimentos(List<Rendimento> rendimentos) {
		this.rendimentos = rendimentos;
	}

	public List<Total> getTotais() {
		return Totais;
	}

	public void setTotais(List<Total> totais) {
		Totais = totais;
	}

}
