package com.fatec.javaweb.component;

import java.util.List;

import com.fatec.javaweb.model.BaseModel;

public class ConjuntoServidoresJson implements BaseModel {

	private List<ServidorInfoMinJson> servidores;

	private String referencia;

	public List<ServidorInfoMinJson> getServidores() {
		return servidores;
	}

	public void setServidores(List<ServidorInfoMinJson> servidores) {
		this.servidores = servidores;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public String toString() {
		return "ConjuntoServidoresJson [servidores=" + servidores + ", referencia=" + referencia + "]";
	}

}
