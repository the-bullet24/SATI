/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Itf;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ItfImpl extends OperacionImpl  implements Itf,Serializable {
	private String cuenta;
	private String periodo;
	private String servicio;
	private String tpersona;
	private String razon;
	private String documento;
	private String direccion;
	private String moneda;
	private String cargos;
	private String abonos;
	private String devoluciones;
	private String retencion;	
	private String total;
	private String fecha;
	private String tdocumento;
	private String nombre;
	private String nombremoneda;
	private String cuentaformateada;
	private String mensajeItf;
	private String hora;
	
	/**
	 * 
	 */
	public ItfImpl() {

		// TODO Apendice de constructor generado automáticamente
	}
	
	public ItfImpl(String cuenta, String periodo) throws Exception{

		this.cuenta = cuenta;
		this.periodo = periodo;
		Itf itf = DAOFactory.getItfDAO().getItfAnual(this);
		if(itf!=null){
			this.servicio = itf.getServicio();
			this.tpersona = itf.getTpersona();
			this.razon = itf.getRazon();
			this.documento = itf.getDocumento();
			this.direccion = itf.getDireccion();
			this.moneda = itf.getMoneda();
			this.cargos = ObjectUtil.formatearMonto(ObjectUtil.deFormatearMonto(itf.getCargos()));
			this.abonos = ObjectUtil.formatearMonto(ObjectUtil.deFormatearMonto(itf.getAbonos()));
			this.devoluciones = ObjectUtil.formatearMonto(ObjectUtil.deFormatearMonto(itf.getDevoluciones()));
			this.fecha = ObjectUtil.getFechaActual();
			this.hora = ObjectUtil.getHora()+":"+ObjectUtil.getMinuto()+":"+ObjectUtil.getSegundo();
			this.retencion = ObjectUtil.formatearMonto(ObjectUtil.deFormatearMonto(itf.getRetencion()));
			this.tdocumento = itf.getTdocumento();
			this.total = ObjectUtil.formatearMonto(ObjectUtil.deFormatearMonto(itf.getTotal()));
			if(itf.getMoneda().equals(Constante.MONEDA_SOL)){
				this.nombremoneda=Constante.NOMBRE_MONEDA_SOL;
			}
			if(itf.getMoneda().equals(Constante.MONEDA_DOLAR)){
				this.nombremoneda=Constante.NOMBRE_MONEDA_DOLAR;
			}
			this.cuentaformateada=ObjectUtil.formatearCuenta(itf.getCuenta(),Constante.FORMATO_CUENTA);
		}
		else{
			throw new ArrayRuleException(ConstanteError.GENERICO,"No existe ITF para la cuenta "+cuenta+" en el periodo actual");
		}

	}
	

	/**
	 * @return Devuelve abonos.
	 */
	public String getAbonos() {
		return abonos;
	}
	/**
	 * @return Devuelve cargos.
	 */
	public String getCargos() {
		return cargos;
	}
	/**
	 * @return Devuelve cuenta.
	 */
	public String getCuenta() {
		return cuenta;
	}
	/**
	 * @return Devuelve devoluciones.
	 */
	public String getDevoluciones() {
		return devoluciones;
	}
	/**
	 * @return Devuelve direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @return Devuelve documento.
	 */
	public String getDocumento() {
		return documento;
	}
	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @return Devuelve moneda.
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @return Devuelve periodo.
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @return Devuelve razon.
	 */
	public String getRazon() {
		return razon;
	}
	/**
	 * @return Devuelve servicio.
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @return Devuelve tdocumento.
	 */
	public String getTdocumento() {
		return tdocumento;
	}
	/**
	 * @return Devuelve tpersona.
	 */
	public String getTpersona() {
		return tpersona;
	}
	/**
	 * @return Devuelve retencion.
	 */
	public String getRetencion() {
		return retencion;
	}
	/**
	 * @param retencion El retencion a establecer.
	 */
	public void setRetencion(String retencion) {
		this.retencion = retencion;
	}
	/**
	 * @return Devuelve total.
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total El total a establecer.
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @param abonos El abonos a establecer.
	 */
	public void setAbonos(String abonos) {
		this.abonos = abonos;
	}
	/**
	 * @param cargos El cargos a establecer.
	 */
	public void setCargos(String cargos) {
		this.cargos = cargos;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @param devoluciones El devoluciones a establecer.
	 */
	public void setDevoluciones(String devoluciones) {
		this.devoluciones = devoluciones;
	}
	/**
	 * @param direccion El direccion a establecer.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @param documento El documento a establecer.
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @param moneda El moneda a establecer.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @param periodo El periodo a establecer.
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @param razon El razon a establecer.
	 */
	public void setRazon(String razon) {
		this.razon = razon;
	}
	/**
	 * @param servicio El servicio a establecer.
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	/**
	 * @param tdocumento El tdocumento a establecer.
	 */
	public void setTdocumento(String tdocumento) {
		this.tdocumento = tdocumento;
	}
	/**
	 * @param tpersona El tpersona a establecer.
	 */
	public void setTpersona(String tpersona) {
		this.tpersona = tpersona;
	}
	/**
	 * @return Devuelve nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre El nombre a establecer.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return Devuelve nombremoneda.
	 */
	public String getNombremoneda() {
		return nombremoneda;
	}
	/**
	 * @param nombremoneda El nombremoneda a establecer.
	 */
	public void setNombremoneda(String nombremoneda) {
		this.nombremoneda = nombremoneda;
	}
	/**
	 * @return Devuelve cuentaforamteada.
	 */
	public String getCuentaformateada() {
		return cuentaformateada;
	}
	/**
	 * @param cuentaforamteada El cuentaforamteada a establecer.
	 */
	public void setCuentaformateada(String cuentaformateada) {
		this.cuentaformateada = cuentaformateada;
	}
	
	public String getMensajeItf() {
		return mensajeItf;
	}
	/**
	 * @param cuentaforamteada El cuentaforamteada a establecer.
	 */
	public void setMensajeItf(String mensajeItf) {
		this.mensajeItf = mensajeItf;
	}
	/**
	 * @return Returns the hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora The hora to set.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
}
