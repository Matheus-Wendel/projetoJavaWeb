package com.fatec.javaweb.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fatec.javaweb.model.Salario.Salario;

@Entity
public class DetalheServidorPublico implements BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String cargo;

	private String nome;

	private String regime;

	@OneToMany
	@JoinColumn(name = "detalhe_id")
	@Cascade(CascadeType.ALL)
	private List<Salario> salario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Salario> getSalario() {
		return salario;
	}

	public void setSalario(List<Salario> salario) {
		this.salario = salario;
	}

}
