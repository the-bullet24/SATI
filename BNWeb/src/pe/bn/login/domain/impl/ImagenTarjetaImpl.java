/*
 * Created on 06/10/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.login.domain.impl;

import java.io.Serializable;

import pe.bn.login.domain.ImagenTarjeta;


/**
 * @author 2424012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ImagenTarjetaImpl implements ImagenTarjeta, Serializable{
	private String fechaIng;
	private String ultFecha;
	private String numTarImg;
	private String nomValorImg;
	private String numIntento;
	private String numIntDia;
	private String numSec;
	private String codCat;
	private String ipCrea;
	private String ipUltimo;
	private String stdoImagen;
	
	/**
	public ImagenTarjetaImpl(String numTarjeta) throws Exception{
		//ImagenTarjeta imagen = DAOFactory.getItfDAO().getItfAnual(this);
		ImagenTarjeta imagen  = DAOFactory.getImagenTarjetaImpl().getValidaImagen(numTarjeta);
	}
	**/
	
	
	/**
	 * @return Returns the fechaIng.
	 */
	public String getFechaIng() {
		return fechaIng;
	}
	/**
	 * @param fechaIng The fechaIng to set.
	 */
	public void setFechaIng(String fechaIng) {
		this.fechaIng = fechaIng;
	}
	/**
	 * @return Returns the nomValorImg.
	 */
	public String getNomValorImg() {
		return nomValorImg;
	}
	/**
	 * @param nomValorImg The nomValorImg to set.
	 */
	public void setNomValorImg(String nomValorImg) {
		this.nomValorImg = nomValorImg;
	}
	/**
	 * @return Returns the numIntento.
	 */
	public String getNumIntento() {
		return numIntento;
	}
	/**
	 * @param numIntento The numIntento to set.
	 */
	public void setNumIntento(String numIntento) {
		this.numIntento = numIntento;
	}
	/**
	 * @return Returns the numTarImg.
	 */
	public String getNumTarImg() {
		return numTarImg;
	}
	/**
	 * @param numTarImg The numTarImg to set.
	 */
	public void setNumTarImg(String numTarImg) {
		this.numTarImg = numTarImg;
	}
	/**
	 * @return Returns the ultFecha.
	 */
	public String getUltFecha() {
		return ultFecha;
	}
	/**
	 * @param ultFecha The ultFecha to set.
	 */
	public void setUltFecha(String ultFecha) {
		this.ultFecha = ultFecha;
	}
	/**
	 * @return Returns the numIntento.
	 */
	public String getNumIntDia() {
		return numIntDia;
	}
	/**
	 * @param numIntento The numIntento to set.
	 */
	public void setNumIntDia(String numIntDia) {
		this.numIntDia = numIntDia;
	}
	/**
	 * @return Returns the numIntento.
	 */
	public String getNumSec() {
		return numSec;
	}
	/**
	 * @param numIntento The numIntento to set.
	 */
	public void setNumSec(String numSec) {
		this.numSec = numSec;
	}
	
	
	/**
	 * @return Returns the codCat.
	 */
	public String getCodCat() {
		return codCat;
	}
	/**
	 * @param codCat The codCat to set.
	 */
	public void setCodCat(String codCat) {
		this.codCat = codCat;
	}
}
