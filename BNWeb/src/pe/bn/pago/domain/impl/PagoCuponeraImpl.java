/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoCuponera;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.ReciboImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoCuponeraImpl extends PagoImpl implements Serializable, PagoCuponera{
	private String hora;
	private String fecha;
	private Cuenta cuenta;
	private ReciboImpl recibo;
	private String nroReferencia;
	private String codEntidad;
	private String codServicio;
	private String nomEntidad;
	private String nroChequeo;
	private String cliente;
	private String direccion;
	private String distrito;
	private String moneda;
	
	private BigDecimal importePago;
	private BigDecimal interes;
	private List recibos;
	private Afiliacion afiliacion;
	
	
	/**
	 * @return Returns the importe.
	 */
	public BigDecimal getImportePago() {
		return importePago;
	}
	/**
	 * @param importe The importe to set.
	 */
	public void setImportePago(BigDecimal importePago) {
		this.importePago = importePago;
	}
	/**
	 * @return Returns the interes.
	 */
	public BigDecimal getInteres() {
		return interes;
	}
	/**
	 * @param interes The interes to set.
	 */
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	/**
	 * @return Returns the moneda.
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda The moneda to set.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	/**
	 * @return Devuelve cuenta.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Devuelve fechaEmisión.
	 */
	/**
	 * @return Devuelve nroReferencia.
	 */
	public String getNroReferencia() {
		return nroReferencia;
	}
	/**
	 * @param nroReferencia El nroReferencia a establecer.
	 */
	public void setNroReferencia(String nroReferencia) {
		this.nroReferencia = nroReferencia;
	}
	
	/**
	 * @return Devuelve codEntidad.
	 */
	public String getCodEntidad() {
		return codEntidad;
	}
	/**
	 * @param codEntidad El codEntidad a establecer.
	 */
	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}
	
	/**
	 * @return Devuelve nomEntidad.
	 */
	public String getNomEntidad() {
		return nomEntidad;
	}
	/**
	 * @param codEntidad El codEntidad a establecer.
	 */
	public void setNomEntidad(String nomEntidad) {
		this.nomEntidad = nomEntidad;
	}
	
	public void getPago(Transaction t, Usuario usuario) throws Exception{
		this.recibos = new ArrayList();
		Vector resutado = procesar(t);
		Vector recibs= new Vector();
		HashMap mapaRecibos = new HashMap();
		List idsl = new ArrayList();
		
		ReciboImpl recibo1 = new ReciboImpl();
		ReciboImpl recibo2 = new ReciboImpl();
		ReciboImpl recibo3 = new ReciboImpl();
		ReciboImpl recibo4 = new ReciboImpl();
		ReciboImpl recibo5 = new ReciboImpl();
		
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();

			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setCliente(element.elementAt(6).toString().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setMoneda(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setImportePago(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setInteres(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setComp(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setGasto(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				recibo1.setNumRecibo(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				Timestamp fecha =  ObjectUtil.tramaTimestampddmmyyyy(element.elementAt(6).toString().trim());
				recibo1.setFecha(fecha);
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				recibo1.setImporte(new BigDecimal(element.elementAt(6).toString().replace(",", "").trim()));
				recibs.add(recibo1);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				if (!element.elementAt(6).toString().trim().equals("0000")){
					recibo2.setNumRecibo(element.elementAt(6).toString().trim());
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
				if (!element.elementAt(6).toString().trim().equals("")){
					recibo2.setFecha(ObjectUtil.tramaTimestampddmmyyyy(element.elementAt(6).toString().trim()));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				if (!element.elementAt(6).toString().trim().equals(".00")){
					recibo2.setImporte(new BigDecimal(element.elementAt(6).toString().trim()));
					recibs.add(recibo2);
				}
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
				if (!element.elementAt(6).toString().trim().equals("0000")){
					recibo3.setNumRecibo(element.elementAt(6).toString().trim());
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
				if (!element.elementAt(6).toString().trim().equals("")){
					recibo3.setFecha(ObjectUtil.tramaTimestampddmmyyyy(element.elementAt(6).toString().trim()));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_16)){
				if (!element.elementAt(6).toString().trim().equals(".00")){
					recibo3.setImporte(new BigDecimal(element.elementAt(6).toString().trim()));
					recibs.add(recibo3);
				}
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
				if (!element.elementAt(6).toString().trim().equals("0000")){
					recibo4.setNumRecibo(element.elementAt(6).toString().trim());
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
				if (!element.elementAt(6).toString().trim().equals("")){
					recibo4.setFecha(ObjectUtil.tramaTimestampddmmyyyy(element.elementAt(6).toString().trim()));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_19)){
				if (!element.elementAt(6).toString().trim().equals(".00")){
					recibo4.setImporte(new BigDecimal(element.elementAt(6).toString().trim()));
					recibs.add(recibo4);
				}
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_20)){
				if (!element.elementAt(6).toString().trim().equals("0000")){
					recibo5.setNumRecibo(element.elementAt(6).toString().trim());
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_21)){
				if (!element.elementAt(6).toString().trim().equals("")){
					recibo5.setFecha(ObjectUtil.tramaTimestampddmmyyyy(element.elementAt(6).toString().trim()));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_22)){
				if (!element.elementAt(6).toString().trim().equals(".00")){
					recibo5.setImporte(new BigDecimal(element.elementAt(6).toString().trim()));
					recibs.add(recibo5);
				}
			}
			
			
			//Inicio 2019-M0400
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_23)){
				
				
				String importe = element.elementAt(6).toString().replace(",", "").trim().substring(0, 15);
				String mora = element.elementAt(6).toString().replace(",", "").trim().substring(15, 30);
				String importeTotal = element.elementAt(6).toString().replace(",", "").trim().substring(30, 45);
				
				importe = ObjectUtil.replaceChar(importe,',',"");
				importe = ObjectUtil.replaceChar(importe,'.',"");
				
				mora = ObjectUtil.replaceChar(mora,',',"");
				mora = ObjectUtil.replaceChar(mora,'.',"");
				
				importeTotal = ObjectUtil.replaceChar(importeTotal,',',"");
				importeTotal = ObjectUtil.replaceChar(importeTotal,'.',"");
			
				recibo1.setMora(ObjectUtil.tramaToBigDecimal(mora, 2));
				recibo1.setImporteTotal(ObjectUtil.tramaToBigDecimal(importeTotal, 2));
			}
			//Fin 2019-M0400
		}

		for(int i = 0 ; i < recibs.size(); i++){
			ReciboImpl rcb=(ReciboImpl)recibs.elementAt(i);	
			this.recibos.add(rcb);
		}
		if(getArrayRuleException()!=null) 
			throw getArrayRuleException();
	}

	public void pagar(Transaction t, Usuario usuario) throws Exception{
		String importeSinFormato = ObjectUtil.replaceChar(this.getRecibo().getImporte(),',',"");
		importeSinFormato = ObjectUtil.replaceChar(importeSinFormato,'.',"");
		BigDecimal importeX = ObjectUtil.tramaToBigDecimal(importeSinFormato);
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),importeX);
		Vector resutado = procesar(t);

		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			//Inicio 2019-M0400
			//if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				this.setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			//Fin 2019-M0400
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				setNroOperacion(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setTotal(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setMora(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setComp(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setGasto(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_PAGO_CUPONERA);		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	/**
	 * @return Devuelve nroChequeo.
	 */
	public String getNroChequeo() {
		return nroChequeo;
	}
	/**
	 * @param nroChequeo El nroChequeo a establecer.
	 */
	public void setNroChequeo(String nroChequeo) {
		this.nroChequeo = nroChequeo;
	}
	/**
	 * @return Devuelve recibos.
	 */
	public List getRecibos() {
		return recibos;
	}
	/**
	 * @param recibos El recibos a establecer.
	 */
	public void setRecibos(List recibos) {
		this.recibos = recibos;
	}
	/**
	 * @return Devuelve cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente El cliente a establecer.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Devuelve direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion El direccion a establecer.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return Devuelve distrito.
	 */
	public String getDistrito() {
		return distrito;
	}
	/**
	 * @param distrito El distrito a establecer.
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	/**
	 * @return Devuelve recibo.
	 */
	public ReciboImpl getRecibo() {
		return recibo;
	}
	/**
	 * @param recibo El recibo a establecer.
	 */
	public void setRecibo(ReciboImpl recibo) {
		this.recibo = recibo;
	}
	/**
	 * @return Devuelve afiliacion.
	 */
	public Afiliacion getAfiliacion() {
		return afiliacion;
	}
	/**
	 * @param afiliacion El afiliacion a establecer.
	 */
	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}
	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Devuelve hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora El hora a establecer.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
	
	/**
	 * @return Returns the codServicio.
	 */
	public String getCodServicio() {
		return codServicio;
	}
	/**
	 * @param codServicio The codServicio to set.
	 */
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
}
