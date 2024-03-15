package pe.cosapi.domain.impl;

import java.io.Serializable;

import pe.cosapi.domain.AtriTransferencia;

public class AtriTransferenciaImpl implements AtriTransferencia, Serializable{

	private String estadoOperaciones;
	
	
	public String getEstadoOperaciones() {
		return estadoOperaciones;
	}
	public void setEstadoOperaciones(String estadoOperaciones) {
		this.estadoOperaciones = estadoOperaciones;
	}

}
