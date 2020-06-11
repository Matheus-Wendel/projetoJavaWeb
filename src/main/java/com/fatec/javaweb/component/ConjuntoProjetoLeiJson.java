package com.fatec.javaweb.component;

import java.util.List;

import com.fatec.javaweb.model.leis.DecretoLegislativo;
import com.fatec.javaweb.model.leis.LeiComplementar;
import com.fatec.javaweb.model.leis.LeiOrdinaria;
import com.fatec.javaweb.model.leis.LeiOrganica;
import com.fatec.javaweb.model.leis.Resolucao;

public class ConjuntoProjetoLeiJson {

	private List<LeiOrdinaria> leiOrdinaria;
	private List<LeiComplementar> leiComplementar;
	private List<LeiOrganica> leiOrganica;
	private List<DecretoLegislativo> decretoLegislativo;
	private List<Resolucao> resolucao;

	
	
	public List<LeiComplementar> getLeiComplementar() {
		return leiComplementar;
	}

	public void setLeiComplementar(List<LeiComplementar> leiComplementar) {
		this.leiComplementar = leiComplementar;
	}

	public List<LeiOrganica> getLeiOrganica() {
		return leiOrganica;
	}

	public void setLeiOrganica(List<LeiOrganica> leiOrganica) {
		this.leiOrganica = leiOrganica;
	}

	public List<DecretoLegislativo> getDecretoLegislativo() {
		return decretoLegislativo;
	}

	public void setDecretoLegislativo(List<DecretoLegislativo> decretoLegislativo) {
		this.decretoLegislativo = decretoLegislativo;
	}

	public List<Resolucao> getResolucao() {
		return resolucao;
	}

	public void setResolucao(List<Resolucao> resolucao) {
		this.resolucao = resolucao;
	}

	public List<LeiOrdinaria> getLeiOrdinaria() {
		return leiOrdinaria;
	}

	public void setLeiOrdinaria(List<LeiOrdinaria> leiOrdinaria) {
		this.leiOrdinaria = leiOrdinaria;
	}
	

}
