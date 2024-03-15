/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;

import pe.cosapi.domain.Diccionario;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DiccionarioImpl implements Diccionario, Serializable {

	private String codDic;
	private String txtDes;
	private String numLon;
	private String ideDic;
	private String txtFor;
	private String tipDat;
	private String txtFncVal;
	private String txtHlp;
	private String txtDem;
	
	
	/**
	 * @return Devuelve codDic.
	 */
	public String getCodDic() {
		return codDic;
	}
	/**
	 * @param codDic El codDic a establecer.
	 */
	public void setCodDic(String codDic) {
		this.codDic = codDic;
	}
	/**
	 * @return Devuelve ideDic.
	 */
	public String getIdeDic() {
		return ideDic;
	}
	/**
	 * @param ideDic El ideDic a establecer.
	 */
	public void setIdeDic(String ideDic) {
		this.ideDic = ideDic;
	}
	/**
	 * @return Devuelve numLon.
	 */
	public String getNumLon() {
		return numLon;
	}
	/**
	 * @param numLon El numLon a establecer.
	 */
	public void setNumLon(String numLon) {
		this.numLon = numLon;
	}
	/**
	 * @return Devuelve tipDat.
	 */
	public String getTipDat() {
		return tipDat;
	}
	/**
	 * @param tipDat El tipDat a establecer.
	 */
	public void setTipDat(String tipDat) {
		this.tipDat = tipDat;
	}
	/**
	 * @return Devuelve txtDem.
	 */
	public String getTxtDem() {
		return txtDem;
	}
	/**
	 * @param txtDem El txtDem a establecer.
	 */
	public void setTxtDem(String txtDem) {
		this.txtDem = txtDem;
	}
	/**
	 * @return Devuelve txtDes.
	 */
	public String getTxtDes() {
		return txtDes;
	}
	/**
	 * @param txtDes El txtDes a establecer.
	 */
	public void setTxtDes(String txtDes) {
		this.txtDes = txtDes;
	}
	/**
	 * @return Devuelve txtFncVal.
	 */
	public String getTxtFncVal() {
		return txtFncVal;
	}
	/**
	 * @param txtFncVal El txtFncVal a establecer.
	 */
	public void setTxtFncVal(String txtFncVal) {
		this.txtFncVal = txtFncVal;
	}
	/**
	 * @return Devuelve txtFor.
	 */
	public String getTxtFor() {
		return txtFor;
	}
	/**
	 * @param txtFor El txtFor a establecer.
	 */
	public void setTxtFor(String txtFor) {
		this.txtFor = txtFor;
	}
	/**
	 * @return Devuelve txtHlp.
	 */
	public String getTxtHlp() {
		return txtHlp;
	}
	/**
	 * @param txtHlp El txtHlp a establecer.
	 */
	public void setTxtHlp(String txtHlp) {
		this.txtHlp = txtHlp;
	}
}
