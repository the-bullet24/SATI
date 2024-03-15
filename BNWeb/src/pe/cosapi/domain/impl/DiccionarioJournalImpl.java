/*
 * Creado el 03/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;

import pe.cosapi.domain.DiccionarioJournal;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DiccionarioJournalImpl implements DiccionarioJournal, Serializable {
	private String idTransaccion;
	private String campoJournal;
	private String codigoDiccionario;
	
	/**
	 * @return Devuelve campoJournal.
	 */
	public String getCampoJournal() {
		return campoJournal;
	}
	/**
	 * @param campoJournal El campoJournal a establecer.
	 */
	public void setCampoJournal(String campoJournal) {
		this.campoJournal = campoJournal;
	}
	/**
	 * @return Devuelve codigoDiccionario.
	 */
	public String getCodigoDiccionario() {
		return codigoDiccionario;
	}
	/**
	 * @param codigoDiccionario El codigoDiccionario a establecer.
	 */
	public void setCodigoDiccionario(String codigoDiccionario) {
		this.codigoDiccionario = codigoDiccionario;
	}
	/**
	 * @return Devuelve idTransaccion.
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}
	/**
	 * @param idTransaccion El idTransaccion a establecer.
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
}
