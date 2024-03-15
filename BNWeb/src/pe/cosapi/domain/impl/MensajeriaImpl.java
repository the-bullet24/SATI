/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;

import pe.cosapi.domain.Mensajeria;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MensajeriaImpl implements Mensajeria, Serializable {
	private String codGrp; 
	private String codMsg;
	private String desMsg; 
	private String ideMsg;

	/**
	 * @return Devuelve codGrp.
	 */
	public String getCodGrp() {
		return codGrp;
	}
	/**
	 * @param codGrp El codGrp a establecer.
	 */
	public void setCodGrp(String codGrp) {
		this.codGrp = codGrp;
	}
	/**
	 * @return Devuelve codMsg.
	 */
	public String getCodMsg() {
		return codMsg;
	}
	/**
	 * @param codMsg El codMsg a establecer.
	 */
	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}
	/**
	 * @return Devuelve desMsg.
	 */
	public String getDesMsg() {
		return desMsg;
	}
	/**
	 * @param desMsg El desMsg a establecer.
	 */
	public void setDesMsg(String desMsg) {
		this.desMsg = desMsg;
	}
	/**
	 * @return Devuelve ideMsg.
	 */
	public String getIdeMsg() {
		return ideMsg;
	}
	/**
	 * @param ideMsg El ideMsg a establecer.
	 */
	public void setIdeMsg(String ideMsg) {
		this.ideMsg = ideMsg;
	}
}
