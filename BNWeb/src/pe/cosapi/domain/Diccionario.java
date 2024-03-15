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
public interface Diccionario extends Serializable {
	/**
	 * @return Devuelve codDic.
	 */
	public abstract String getCodDic();

	/**
	 * @param codDic El codDic a establecer.
	 */
	public abstract void setCodDic(String codDic);

	/**
	 * @return Devuelve ideDic.
	 */
	public abstract String getIdeDic();

	/**
	 * @param ideDic El ideDic a establecer.
	 */
	public abstract void setIdeDic(String ideDic);

	/**
	 * @return Devuelve numLon.
	 */
	public abstract String getNumLon();

	/**
	 * @param numLon El numLon a establecer.
	 */
	public abstract void setNumLon(String numLon);

	/**
	 * @return Devuelve tipDat.
	 */
	public abstract String getTipDat();

	/**
	 * @param tipDat El tipDat a establecer.
	 */
	public abstract void setTipDat(String tipDat);

	/**
	 * @return Devuelve txtDem.
	 */
	public abstract String getTxtDem();

	/**
	 * @param txtDem El txtDem a establecer.
	 */
	public abstract void setTxtDem(String txtDem);

	/**
	 * @return Devuelve txtDes.
	 */
	public abstract String getTxtDes();

	/**
	 * @param txtDes El txtDes a establecer.
	 */
	public abstract void setTxtDes(String txtDes);

	/**
	 * @return Devuelve txtFncVal.
	 */
	public abstract String getTxtFncVal();

	/**
	 * @param txtFncVal El txtFncVal a establecer.
	 */
	public abstract void setTxtFncVal(String txtFncVal);

	/**
	 * @return Devuelve txtFor.
	 */
	public abstract String getTxtFor();

	/**
	 * @param txtFor El txtFor a establecer.
	 */
	public abstract void setTxtFor(String txtFor);

	/**
	 * @return Devuelve txtHlp.
	 */
	public abstract String getTxtHlp();

	/**
	 * @param txtHlp El txtHlp a establecer.
	 */
	public abstract void setTxtHlp(String txtHlp);
}