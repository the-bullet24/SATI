/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de código
 */
package pe.cosapi.domain.impl;

import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.EstadoCta;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EstadoCtaImpl implements EstadoCta {
	private String periodo;
	private String fproceso;
	private String ncuenta;
	private String codCliente;
	private String nombre1Cliente;
	private String nombre2Cliente;
	private String nombreCuenta1;
	private String nombreCuenta2;
	private String codGrupo;
	private String dir1Distribucion;
	private String dir2Distribucion;
	private String nombreGrupo1;
	private String nombreGrupo2;	
	private String ctransaccion;
	private String nsecuencia;
	private String nsecuenciaHead;
	private String nsecuenciaResum;
	private String tregistro;
	private String fprocTransaccion;
	private String ndocumento;
	private String simporte;
	private String cdepTransaccion;
	private String chkCert;
	private String saldDia;
	private String sigSaldDia;
	private String codOrigTran;
	private String saldUltEstEmitido;
	private String futlEstEmitido;
	private String ncopiaEstado;
	private String sigUltEstado;
	private String tsaldo;
	private String noperaciones;
	private String totCargo;
	private String totAbono;
	private String saldContabFinal;
	private String signoSaldContabFinal;
	
	
	/**
	 * 
	 */
	public EstadoCtaImpl() {

		// TODO Apendice de constructor generado automáticamente
	}
	
	public EstadoCtaImpl(String ncuenta, String periodo) throws Exception{
		this.ncuenta = ncuenta;
		this.periodo = periodo;
		EstadoCta estadoCta = DAOFactory.getEstadoCtaDAO().getEstadoConsulta(this);
		if(estadoCta!=null){
			this.fproceso = estadoCta.getFproceso();
			this.ncuenta = estadoCta.getNcuenta();
			this.codCliente = estadoCta.getCodCliente();
			this.nombre1Cliente = estadoCta.getNombre1Cliente();
			this.nombre2Cliente = estadoCta.getNombre2Cliente();
			this.nombreCuenta1 = estadoCta.getNombreCuenta1();
			this.nombreCuenta2 = estadoCta.getNombreCuenta2();
			this.codGrupo = estadoCta.getCodGrupo();
			this.dir1Distribucion = estadoCta.getDir1Distribucion();
			this.dir2Distribucion = estadoCta.getDir2Distribucion();
			this.nombreGrupo1 = estadoCta.getNombreGrupo1();
			this.nombreGrupo2 = estadoCta.getNombreGrupo2();
			this.ctransaccion = estadoCta.getCtransaccion();
			this.nsecuencia = estadoCta.getNsecuencia();
			this.nsecuenciaHead = estadoCta.getNsecuenciaHead();
			this.nsecuenciaResum = estadoCta.getNsecuenciaResum();
			this.tregistro = estadoCta.getTregistro();
			this.fprocTransaccion = estadoCta.getFprocTransaccion();
			this.ndocumento = estadoCta.getNdocumento();
			this.simporte = estadoCta.getSimporte();
			this.cdepTransaccion = estadoCta.getCdepTransaccion();
			this.chkCert = estadoCta.getChkCert();
			this.saldDia = estadoCta.getSaldDia();
			this.sigSaldDia = estadoCta.getSigSaldDia();
			this.codOrigTran = estadoCta.getCodOrigTran();
			this.saldUltEstEmitido = estadoCta.getSaldUltEstEmitido();
			this.futlEstEmitido = estadoCta.getFutlEstEmitido();
			this.ncopiaEstado = estadoCta.getNcopiaEstado();
			this.sigUltEstado = estadoCta.getSigUltEstado();
			this.tsaldo = estadoCta.getTsaldo();
			this.noperaciones = estadoCta.getNoperaciones();
			this.totCargo = estadoCta.getTotCargo();
			this.totAbono = estadoCta.getTotAbono();
			this.saldContabFinal = estadoCta.getSaldContabFinal();
			this.signoSaldContabFinal = estadoCta.getSignoSaldContabFinal();
			
		}
		else{
			throw new ArrayRuleException(ConstanteError.GENERICO,"No existe Estado de Cuenta para la cuenta "+ncuenta+" en el periodo actual");
		}

	}

			
	/**
	 * @return Devuelve cdepTransaccion.
	 */
	public String getCdepTransaccion() {
		return cdepTransaccion;
	}
	/**
	 * @param cdepTransaccion El cdepTransaccion a establecer.
	 */
	public void setCdepTransaccion(String cdepTransaccion) {
		this.cdepTransaccion = cdepTransaccion;
	}
	/**
	 * @return Devuelve chkCert.
	 */
	public String getChkCert() {
		return chkCert;
	}
	/**
	 * @param chkCert El chkCert a establecer.
	 */
	public void setChkCert(String chkCert) {
		this.chkCert = chkCert;
	}
	/**
	 * @return Devuelve codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente El codCliente a establecer.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	/**
	 * @return Devuelve codGrupo.
	 */
	public String getCodGrupo() {
		return codGrupo;
	}
	/**
	 * @param codGrupo El codGrupo a establecer.
	 */
	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}
	/**
	 * @return Devuelve codOrigTran.
	 */
	public String getCodOrigTran() {
		return codOrigTran;
	}
	/**
	 * @param codOrigTran El codOrigTran a establecer.
	 */
	public void setCodOrigTran(String codOrigTran) {
		this.codOrigTran = codOrigTran;
	}
	/**
	 * @return Devuelve ctransaccion.
	 */
	public String getCtransaccion() {
		return ctransaccion;
	}
	/**
	 * @param ctransaccion El ctransaccion a establecer.
	 */
	public void setCtransaccion(String ctransaccion) {
		this.ctransaccion = ctransaccion;
	}
	/**
	 * @return Devuelve dir1Distribucion.
	 */
	public String getDir1Distribucion() {
		return dir1Distribucion;
	}
	/**
	 * @param dir1Distribucion El dir1Distribucion a establecer.
	 */
	public void setDir1Distribucion(String dir1Distribucion) {
		this.dir1Distribucion = dir1Distribucion;
	}
	/**
	 * @return Devuelve dir2Distribucion.
	 */
	public String getDir2Distribucion() {
		return dir2Distribucion;
	}
	/**
	 * @param dir2Distribucion El dir2Distribucion a establecer.
	 */
	public void setDir2Distribucion(String dir2Distribucion) {
		this.dir2Distribucion = dir2Distribucion;
	}
	/**
	 * @return Devuelve fproceso.
	 */
	public String getFproceso() {
		return fproceso;
	}
	/**
	 * @param fproceso El fproceso a establecer.
	 */
	public void setFproceso(String fproceso) {
		this.fproceso = fproceso;
	}
	/**
	 * @return Devuelve fprocTransaccion.
	 */
	public String getFprocTransaccion() {
		return fprocTransaccion;
	}
	/**
	 * @param fprocTransaccion El fprocTransaccion a establecer.
	 */
	public void setFprocTransaccion(String fprocTransaccion) {
		this.fprocTransaccion = fprocTransaccion;
	}
	/**
	 * @return Devuelve futlEstEmitido.
	 */
	public String getFutlEstEmitido() {
		return futlEstEmitido;
	}
	/**
	 * @param futlEstEmitido El futlEstEmitido a establecer.
	 */
	public void setFutlEstEmitido(String futlEstEmitido) {
		this.futlEstEmitido = futlEstEmitido;
	}
	/**
	 * @return Devuelve ncopiaEstado.
	 */
	public String getNcopiaEstado() {
		return ncopiaEstado;
	}
	/**
	 * @param ncopiaEstado El ncopiaEstado a establecer.
	 */
	public void setNcopiaEstado(String ncopiaEstado) {
		this.ncopiaEstado = ncopiaEstado;
	}
	/**
	 * @return Devuelve ncuenta.
	 */
	public String getNcuenta() {
		return ncuenta;
	}
	/**
	 * @param ncuenta El ncuenta a establecer.
	 */
	public void setNcuenta(String ncuenta) {
		this.ncuenta = ncuenta;
	}
	/**
	 * @return Devuelve ndocumento.
	 */
	public String getNdocumento() {
		return ndocumento;
	}
	/**
	 * @param ndocumento El ndocumento a establecer.
	 */
	public void setNdocumento(String ndocumento) {
		this.ndocumento = ndocumento;
	}
	/**
	 * @return Devuelve nombre1Cliente.
	 */
	public String getNombre1Cliente() {
		return nombre1Cliente;
	}
	/**
	 * @param nombre1Cliente El nombre1Cliente a establecer.
	 */
	public void setNombre1Cliente(String nombre1Cliente) {
		this.nombre1Cliente = nombre1Cliente;
	}
	/**
	 * @return Devuelve nombre2Cliente.
	 */
	public String getNombre2Cliente() {
		return nombre2Cliente;
	}
	/**
	 * @param nombre2Cliente El nombre2Cliente a establecer.
	 */
	public void setNombre2Cliente(String nombre2Cliente) {
		this.nombre2Cliente = nombre2Cliente;
	}
	/**
	 * @return Devuelve nombreCuenta1.
	 */
	public String getNombreCuenta1() {
		return nombreCuenta1;
	}
	/**
	 * @param nombreCuenta1 El nombreCuenta1 a establecer.
	 */
	public void setNombreCuenta1(String nombreCuenta1) {
		this.nombreCuenta1 = nombreCuenta1;
	}
	/**
	 * @return Devuelve nombreCuenta2.
	 */
	public String getNombreCuenta2() {
		return nombreCuenta2;
	}
	/**
	 * @param nombreCuenta2 El nombreCuenta2 a establecer.
	 */
	public void setNombreCuenta2(String nombreCuenta2) {
		this.nombreCuenta2 = nombreCuenta2;
	}
	/**
	 * @return Devuelve nombreGrupo1.
	 */
	public String getNombreGrupo1() {
		return nombreGrupo1;
	}
	/**
	 * @param nombreGrupo1 El nombreGrupo1 a establecer.
	 */
	public void setNombreGrupo1(String nombreGrupo1) {
		this.nombreGrupo1 = nombreGrupo1;
	}
	/**
	 * @return Devuelve nombreGrupo2.
	 */
	public String getNombreGrupo2() {
		return nombreGrupo2;
	}
	/**
	 * @param nombreGrupo2 El nombreGrupo2 a establecer.
	 */
	public void setNombreGrupo2(String nombreGrupo2) {
		this.nombreGrupo2 = nombreGrupo2;
	}
	/**
	 * @return Devuelve noperaciones.
	 */
	public String getNoperaciones() {
		return noperaciones;
	}
	/**
	 * @param noperaciones El noperaciones a establecer.
	 */
	public void setNoperaciones(String noperaciones) {
		this.noperaciones = noperaciones;
	}
	/**
	 * @return Devuelve nsecuencia.
	 */
	public String getNsecuencia() {
		return nsecuencia;
	}
	/**
	 * @param nsecuencia El nsecuencia a establecer.
	 */
	public void setNsecuencia(String nsecuencia) {
		this.nsecuencia = nsecuencia;
	}
	/**
	 * @return Devuelve nsecuenciaHead.
	 */
	public String getNsecuenciaHead() {
		return nsecuenciaHead;
	}
	/**
	 * @param nsecuenciaHead El nsecuenciaHead a establecer.
	 */
	public void setNsecuenciaHead(String nsecuenciaHead) {
		this.nsecuenciaHead = nsecuenciaHead;
	}
	/**
	 * @return Devuelve nsecuenciaResum.
	 */
	public String getNsecuenciaResum() {
		return nsecuenciaResum;
	}
	/**
	 * @param nsecuenciaResum El nsecuenciaResum a establecer.
	 */
	public void setNsecuenciaResum(String nsecuenciaResum) {
		this.nsecuenciaResum = nsecuenciaResum;
	}
	/**
	 * @return Devuelve periodo.
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo El periodo a establecer.
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return Devuelve saldContabFinal.
	 */
	public String getSaldContabFinal() {
		return saldContabFinal;
	}
	/**
	 * @param saldContabFinal El saldContabFinal a establecer.
	 */
	public void setSaldContabFinal(String saldContabFinal) {
		this.saldContabFinal = saldContabFinal;
	}
	/**
	 * @return Devuelve saldDia.
	 */
	public String getSaldDia() {
		return saldDia;
	}
	/**
	 * @param saldDia El saldDia a establecer.
	 */
	public void setSaldDia(String saldDia) {
		this.saldDia = saldDia;
	}
	/**
	 * @return Devuelve saldUltEstEmitido.
	 */
	public String getSaldUltEstEmitido() {
		return saldUltEstEmitido;
	}
	/**
	 * @param saldUltEstEmitido El saldUltEstEmitido a establecer.
	 */
	public void setSaldUltEstEmitido(String saldUltEstEmitido) {
		this.saldUltEstEmitido = saldUltEstEmitido;
	}
	/**
	 * @return Devuelve signoSaldContabFinal.
	 */
	public String getSignoSaldContabFinal() {
		return signoSaldContabFinal;
	}
	/**
	 * @param signoSaldContabFinal El signoSaldContabFinal a establecer.
	 */
	public void setSignoSaldContabFinal(String signoSaldContabFinal) {
		this.signoSaldContabFinal = signoSaldContabFinal;
	}
	/**
	 * @return Devuelve sigSaldDia.
	 */
	public String getSigSaldDia() {
		return sigSaldDia;
	}
	/**
	 * @param sigSaldDia El sigSaldDia a establecer.
	 */
	public void setSigSaldDia(String sigSaldDia) {
		this.sigSaldDia = sigSaldDia;
	}
	/**
	 * @return Devuelve sigUltEstado.
	 */
	public String getSigUltEstado() {
		return sigUltEstado;
	}
	/**
	 * @param sigUltEstado El sigUltEstado a establecer.
	 */
	public void setSigUltEstado(String sigUltEstado) {
		this.sigUltEstado = sigUltEstado;
	}
	/**
	 * @return Devuelve simporte.
	 */
	public String getSimporte() {
		return simporte;
	}
	/**
	 * @param simporte El simporte a establecer.
	 */
	public void setSimporte(String simporte) {
		this.simporte = simporte;
	}
	/**
	 * @return Devuelve totAbono.
	 */
	public String getTotAbono() {
		return totAbono;
	}
	/**
	 * @param totAbono El totAbono a establecer.
	 */
	public void setTotAbono(String totAbono) {
		this.totAbono = totAbono;
	}
	/**
	 * @return Devuelve totCargo.
	 */
	public String getTotCargo() {
		return totCargo;
	}
	/**
	 * @param totCargo El totCargo a establecer.
	 */
	public void setTotCargo(String totCargo) {
		this.totCargo = totCargo;
	}
	/**
	 * @return Devuelve tregistro.
	 */
	public String getTregistro() {
		return tregistro;
	}
	/**
	 * @param tregistro El tregistro a establecer.
	 */
	public void setTregistro(String tregistro) {
		this.tregistro = tregistro;
	}
	/**
	 * @return Devuelve tsaldo.
	 */
	public String getTsaldo() {
		return tsaldo;
	}
	/**
	 * @param tsaldo El tsaldo a establecer.
	 */
	public void setTsaldo(String tsaldo) {
		this.tsaldo = tsaldo;
	}
}
