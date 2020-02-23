package com.fatec.javaweb.model;

import java.util.List;

public class ConjuntoServidores implements BaseModel {

	List<ServidorInfoMin> servidores;

	String referencia;
	
	

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<ServidorInfoMin> getServidores() {
		return servidores;
	}

	public void setServidores(List<ServidorInfoMin> servidores) {
		this.servidores = servidores;
	}

	@Override
	public String toString() {
		return "ConjuntoServidores [servidores=" + servidores + ", referencia=" + referencia + "]";
	}

}
