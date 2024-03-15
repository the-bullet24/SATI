/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Mensajeria extends Serializable {
	/**
	 * @return Devuelve codGrp.
	 */
	public abstract String getCodGrp();

	/**
	 * @param codGrp El codGrp a establecer.
	 */
	public abstract void setCodGrp(String codGrp);

	/**
	 * @return Devuelve codMsg.
	 */
	public abstract String getCodMsg();

	/**
	 * @param codMsg El codMsg a establecer.
	 */
	public abstract void setCodMsg(String codMsg);

	/**
	 * @return Devuelve desMsg.
	 */
	public abstract String getDesMsg();

	/**
	 * @param desMsg El desMsg a establecer.
	 */
	public abstract void setDesMsg(String desMsg);

	/**
	 * @return Devuelve ideMsg.
	 */
	public abstract String getIdeMsg();

	/**
	 * @param ideMsg El ideMsg a establecer.
	 */
	public abstract void setIdeMsg(String ideMsg);
}