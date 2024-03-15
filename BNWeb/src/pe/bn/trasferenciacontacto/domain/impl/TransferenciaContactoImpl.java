package pe.bn.trasferenciacontacto.domain.impl;

import java.io.Serializable;

import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.cosapi.domain.Cuenta;

public class TransferenciaContactoImpl implements  TransferenciaContacto {
	
	private Cuenta 	cuentaOrigen;
	private String cuenta;
	private String numOperacion;
	
	private String fechaAfiliacion;
	private String tipoDocumento;
	private String numDocumento;
	private String canal;
	private String numeroCelularContacto;
	private String nombreClienteContacto;
	private String nroCuentaContacto;
	private String cciContacto;
	private String estadoAfil;
	private String codigoErr;
	private String codigoEntidad;
	private String descEntidad;
	private String codMotivoDesf;
	private String comentarioDesf;
	private String numeroCelularCambio;
	private String fechaDesafiliacion;
	private String descripMotivoDesf;
	private String operadorCelularCambio;
	private String indCambioCelular;
	private String indTransferenciaContacto;
	
	
	
	public String getIndTransferenciaContacto() {
		return indTransferenciaContacto;
	}
	public String setIndTransferenciaContacto(String indTransferenciaContacto) {
		return this.indTransferenciaContacto = indTransferenciaContacto;
	}
	public String getIndCambioCelular() {
		return indCambioCelular;
	}
	public String setIndCambioCelular(String indCambioCelular) {
		return this.indCambioCelular = indCambioCelular;
	}
	public String getOperadorCelularCambio() {
		return operadorCelularCambio;
	}
	public String setOperadorCelularCambio(String operadorCelularCambio) {
		return this.operadorCelularCambio = operadorCelularCambio;
	}
	public String getDescripMotivoDesf() {
		return descripMotivoDesf;
	}
	public String setDescripMotivoDesf(String descripMotivoDesf) {
		return this.descripMotivoDesf = descripMotivoDesf;
	}
	public String getFechaDesafiliacion() {
		return fechaDesafiliacion;
	}
	public String setFechaDesafiliacion(String fechaDesafiliacion) {
		return this.fechaDesafiliacion = fechaDesafiliacion;
	}
	public String getNumeroCelularCambio() {
		return numeroCelularCambio;
	}
	public String setNumeroCelularCambio(String numeroCelularCambio) {
		return this.numeroCelularCambio = numeroCelularCambio;
	}
	public String getCodMotivoDesf() {
		return codMotivoDesf;
	}
	public String setCodMotivoDesf(String codMotivoDesf) {
		return this.codMotivoDesf = codMotivoDesf;
	}
	public String getComentarioDesf() {
		return comentarioDesf;
	}
	public String setComentarioDesf(String comentarioDesf) {
		return this.comentarioDesf = comentarioDesf;
	}
	public String getCodigoEntidad() {
		return codigoEntidad;
	}
	public String setCodigoEntidad(String codigoEntidad) {
		return this.codigoEntidad = codigoEntidad;
	}
	public String getDescEntidad() {
		return descEntidad;
	}
	public String setDescEntidad(String descEntidad) {
		return this.descEntidad = descEntidad;
	}
	public String getCodigoErr() {
		return codigoErr;
	}
	public String setCodigoErr(String codigoErr) {
		return this.codigoErr = codigoErr;
	}
	public String getEstadoAfil() {
		return estadoAfil;
	}
	public String setEstadoAfil(String estadoAfil) {
		return this.estadoAfil = estadoAfil;
	}
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public Cuenta setCuentaOrigen(Cuenta cuentaOrigen) {
	return 	this.cuentaOrigen = cuentaOrigen;
	}
	public String getCuenta() {
		return cuenta;
	}
	public String setCuenta(String cuenta) {
	return this.cuenta = cuenta;
	}
	public String getNumOperacion() {
		return numOperacion;
	}
	public String setNumOperacion(String numOperacion) {
		 return this.numOperacion = numOperacion;
	}
	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}
	public String setFechaAfiliacion(String fechaAfiliacion) {
		return this.fechaAfiliacion = fechaAfiliacion;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getNumeroCelularContacto() {
		return numeroCelularContacto;
	}
	public String setNumeroCelularContacto(String numeroCelularContacto) {
		return this.numeroCelularContacto = numeroCelularContacto;
	}
	public String getNombreClienteContacto() {
		return nombreClienteContacto;
	}
	public String setNombreClienteContacto(String nombreClienteContacto) {
		return this.nombreClienteContacto = nombreClienteContacto;
	}
	public String getNroCuentaContacto() {
		return nroCuentaContacto;
	}
	public String setNroCuentaContacto(String nroCuentaContacto) {
		return this.nroCuentaContacto = nroCuentaContacto;
	}
	public String getCciContacto() {
		return cciContacto;
	}
	public String setCciContacto(String cciContacto) {
		return this.cciContacto = cciContacto;
	}
	
	
	

}
