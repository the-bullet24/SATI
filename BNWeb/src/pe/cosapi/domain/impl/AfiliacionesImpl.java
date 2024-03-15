/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import pe.cosapi.domain.Afiliaciones;
import pe.cosapi.domain.Recibo;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

/**
 * @author 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AfiliacionesImpl implements Afiliaciones, Serializable,Cloneable {
	
	
	private String empresa;
	private String numSuministro;
	private String servicio;
	private String tipoDoc;
	private String nroDoc;
	private String tipoTel;
	private String nroTel;
	private String email;
	private String via;
	private String fecha;
	private String tope;
	private String maximo;
		
	
	private String empresaMostrar;
	private String serviciomostrar;
	private String flagEstado;
	private String flagMostrarEstado;
	
	
	//Medio de envio
	
	private String secuencia;
	private String tipoDireccion;
	private String itemDireccion;
	private String direccion;
	
	public String getEmpresa() {
		return empresa;
	}



	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}



	public String getNumSuministro() {
		return numSuministro;
	}



	public void setNumSuministro(String numSuministro) {
		this.numSuministro = numSuministro;
	}



	public String getServicio() {
		return servicio;
	}



	public void setServicio(String servicio) {
		this.servicio = servicio;
	}





	public String getEmpresaMostrar() {
		return empresaMostrar;
	}



	public void setEmpresaMostrar(String empresaMostrar) {
		this.empresaMostrar = empresaMostrar;
	}



	public String getServiciomostrar() {
		return serviciomostrar;
	}



	public void setServiciomostrar(String serviciomostrar) {
		this.serviciomostrar = serviciomostrar;
	}



	public String getFlagEstado() {
		return flagEstado;
	}



	public void setFlagEstado(String flagEstado) {
		this.flagEstado = flagEstado;
	}

		


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNroDoc() {
		return nroDoc;
	}



	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}



	public String getNroTel() {
		return nroTel;
	}



	public void setNroTel(String nroTel) {
		this.nroTel = nroTel;
	}



	public String getTipoDoc() {
		return tipoDoc;
	}



	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}



	public String getTipoTel() {
		return tipoTel;
	}



	public void setTipoTel(String tipoTel) {
		this.tipoTel = tipoTel;
	}



	public String getVia() {
		return via;
	}



	public void setVia(String via) {
		this.via = via;
	}

	
	


	public String getFlagMostrarEstado() {
		return flagMostrarEstado;
	}



	public void setFlagMostrarEstado(String flagMostrarEstado) {
		this.flagMostrarEstado = flagMostrarEstado;
	}

	


	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	



	public String getMaximo() {
		return maximo;
	}



	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}



	public String getTope() {
		return tope;
	}



	public void setTope(String tope) {
		this.tope = tope;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getItemDireccion() {
		return itemDireccion;
	}



	public void setItemDireccion(String itemDireccion) {
		this.itemDireccion = itemDireccion;
	}



	public String getSecuencia() {
		return secuencia;
	}



	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}



	public String getTipoDireccion() {
		return tipoDireccion;
	}



	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}



	/* (sin Javadoc)
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
}
