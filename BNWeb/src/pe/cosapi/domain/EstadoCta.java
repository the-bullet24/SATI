/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface EstadoCta {
	/**
	 * @return Devuelve fproceso.
	 */
	public abstract String getFproceso();

	/**
	 * @return Devuelve ncuenta.
	 */
	public abstract String getNcuenta();

	/**
	 * @return Devuelve codCliente.
	 */
	public abstract String getCodCliente();

	/**
	 * @return Devuelve nombre1cliente.
	 */
	public abstract String getNombre1Cliente();

	/**
	 * @return Devuelve nombre1nliente.
	 */
	public abstract String getNombre2Cliente();

	/**
	 * @return Devuelve nombrecuenta1.
	 */
	public abstract String getNombreCuenta1();

	/**
	 * @return Devuelve nombrecuenta2.
	 */
	public abstract String getNombreCuenta2();

	/**
	 * @return Devuelve codgrupo.
	 */
	public abstract String getCodGrupo();

	/**
	 * @return Devuelve dir1distribucion.
	 */
	public abstract String getDir1Distribucion();

	/**
	 * @return Devuelve dir1distribucion.
	 */
	public abstract String getDir2Distribucion();

	/**
	 * @return Devuelve nombregrupo1.
	 */
	public abstract String getNombreGrupo1();

	/**
	 * @return Devuelve nombregrupo2.
	 */
	public abstract String getNombreGrupo2();

	/**
	 * @return Devuelve ctransaccion.
	 */
	public abstract String getCtransaccion();
	
	/**
	 * @return Devuelve nsecuencia.
	 */
	public abstract String getNsecuencia();
	
	/**
	 * @return Devuelve nsecuenciaHead.
	 */
	public abstract String getNsecuenciaHead();
	
	/**
	 * @return Devuelve nsecuenciaresum.
	 */
	public abstract String getNsecuenciaResum();
	
	/**
	 * @return Devuelve tregistro.
	 */
	public abstract String getTregistro();
	
	/**
	 * @return Devuelve fprocTransaccion.
	 */
	public abstract String getFprocTransaccion();
	
	/**
	 * @return Devuelve ndocumento.
	 */
	public abstract String getNdocumento();
	
	/**
	 * @return Devuelve simporte.
	 */
	public abstract String getSimporte();
	
	/**
	 * @return Devuelve cdepTransaccion.
	 */
	public abstract String getCdepTransaccion();
	
	/**
	 * @return Devuelve chkCert.
	 */
	public abstract String getChkCert();
	
	/**
	 * @return Devuelve salddia.
	 */
	public abstract String getSaldDia();
	
	/**
	 * @return Devuelve sigsalddia.
	 */
	public abstract String getSigSaldDia();
	
	/**
	 * @return Devuelve codorigtran.
	 */
	public abstract String getCodOrigTran();
	
		/**
	 * @return Devuelve saldultestemitido SALDULTESTEMITIDO.
	 */
	public abstract String getSaldUltEstEmitido();
	
	/**
	 * @return Devuelve futlestEmitido.
	 */
	public abstract String getFutlEstEmitido();
	
	/**
	 * @return Devuelve ncopiaEstado.
	 */
	public abstract String getNcopiaEstado();
	
	/**
	 * @return Devuelve sigultestado.
	 */
	public abstract String getSigUltEstado();
	
	/**
	 * @return Devuelve tsaldo.
	 */
	public abstract String getTsaldo();
	
	/**
	 * @return Devuelve noperaciones.
	 */
	public abstract String getNoperaciones();
	
	/**
	 * @return Devuelve totCargo.
	 */
	public abstract String getTotCargo();
	
	/**
	 * @return Devuelve totAbono.
	 */
	public abstract String getTotAbono();
	
	/**
	 * @return Devuelve saldContabFinal.
	 */
	public abstract String getSaldContabFinal();
	
	/**
	 * @return Devuelve signoSaldContabFinal.
	 */
	public abstract String getSignoSaldContabFinal();
	
	/**
	 * @return Devuelve periodo.
	 */
	public abstract String getPeriodo();
	
}