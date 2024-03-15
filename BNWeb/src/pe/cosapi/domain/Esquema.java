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
public interface Esquema extends Serializable {
	/**
	 * @return Devuelve codAlt.
	 */
	public abstract String getCodAlt();

	/**
	 * @param codAlt El codAlt a establecer.
	 */
	public abstract void setCodAlt(String codAlt);

	/**
	 * @return Devuelve codCla.
	 */
	public abstract String getCodCla();

	/**
	 * @param codCla El codCla a establecer.
	 */
	public abstract void setCodCla(String codCla);

	/**
	 * @return Devuelve codClaAlt.
	 */
	public abstract String getCodClaAlt();

	/**
	 * @param codClaAlt El codClaAlt a establecer.
	 */
	public abstract void setCodClaAlt(String codClaAlt);

	/**
	 * @return Devuelve codFas.
	 */
	public abstract String getCodFas();

	/**
	 * @param codFas El codFas a establecer.
	 */
	public abstract void setCodFas(String codFas);

	/**
	 * @return Devuelve codMet.
	 */
	public abstract String getCodMet();

	/**
	 * @param codMet El codMet a establecer.
	 */
	public abstract void setCodMet(String codMet);

	/**
	 * @return Devuelve codMetAlt.
	 */
	public abstract String getCodMetAlt();

	/**
	 * @param codMetAlt El codMetAlt a establecer.
	 */
	public abstract void setCodMetAlt(String codMetAlt);

	/**
	 * @return Devuelve codTra.
	 */
	public abstract String getCodTra();

	/**
	 * @param codTra El codTra a establecer.
	 */
	public abstract void setCodTra(String codTra);

	/**
	 * @return Devuelve numSeq.
	 */
	public abstract String getNumSeq();

	/**
	 * @param numSeq El numSeq a establecer.
	 */
	public abstract void setNumSeq(String numSeq);

	/**
	 * @return Devuelve numSeqArg.
	 */
	public abstract String getNumSeqArg();

	/**
	 * @param numSeqArg El numSeqArg a establecer.
	 */
	public abstract void setNumSeqArg(String numSeqArg);

	/**
	 * @return Devuelve txtArg.
	 */
	public abstract String getTxtArg();

	/**
	 * @param txtArg El txtArg a establecer.
	 */
	public abstract void setTxtArg(String txtArg);

	/**
	 * @return Devuelve txtCla.
	 */
	public abstract String getTxtCla();

	/**
	 * @param txtCla El txtCla a establecer.
	 */
	public abstract void setTxtCla(String txtCla);

	/**
	 * @return Devuelve txtMet.
	 */
	public abstract String getTxtMet();

	/**
	 * @param txtMet El txtMet a establecer.
	 */
	public abstract void setTxtMet(String txtMet);

	/**
	 * @return Devuelve txtTra.
	 */
	public abstract String getTxtTra();

	/**
	 * @param txtTra El txtTra a establecer.
	 */
	public abstract void setTxtTra(String txtTra);
}