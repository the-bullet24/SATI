/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoFactura;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Recibo;
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
public class PagoFacturaImpl extends PagoImpl implements Serializable, PagoFactura{
	private static LoggerSati log3 = LoggerSati.getInstance(PagoFacturaImpl.class.getName());
	
	private String hora;
	private String fecha;
	private Cuenta cuenta;
	private ReciboImpl recibo;
	private String nroReferencia;
	private String codEntidad;
	private String nomEntidad;
	private String codServicio;
	private String nroChequeo;
	private String cliente;
	private String direccion;
	private String distrito;
	private String moneda;
	private String glosas;
	
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
		BigDecimal cero = new BigDecimal(0.00);
		
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			
			Vector elemento = (Vector) iter.next();
			
			//String codProducto = this.numeroProducto.substring(0,2);

			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){//801
				this.setCliente(elemento.elementAt(6).toString().trim());
			}
			
			
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){//800
			    if (elemento.elementAt(6)!= null){
					String tramaGenerica =  elemento.elementAt(6).toString();

					for(int j = 0; j< tramaGenerica.length();){
					    ReciboImpl recibo = new ReciboImpl();
					    if (j+120 <= tramaGenerica.length()){
			                String tramita = tramaGenerica.substring(j,j=j+120);
			                if ((!tramita.substring(0,2).equals("00")) || (!tramita.substring(0,2).equals("  "))){

				                try{
				                	String txtSecuencia  = tramita.substring(0,2); 
				                	String txtCodCli = tramita.substring(2,32);
				                	//String txtCodCli  = tramita.substring(75,85);
					                String txtFecha  = tramita.substring(32,42);
					                					                
					                String txtMoneda = tramita.substring(42,45);
					                String txtImporte  = tramita.substring(45,60);
					                String txtTotalEdit	= tramita.substring(60,75);
					                //String txtCodCli  = tramita.substring(75,85);
					                
					                String txtCodReg = tramita.substring(85,95);
					                String txtCodMoneda  = tramita.substring(95,98);
					                String txtInteres = tramita.substring(98,105).trim();
					                String txtTotal    = tramita.substring(105,120);
					                txtTotal = ObjectUtil.replaceChar(txtTotal,',',"");
					                txtTotal = ObjectUtil.replaceChar(txtTotal,'.',"");
					                
					                if(txtMoneda.contains(" "))
					                	txtMoneda = ObjectUtil.replaceChar(txtMoneda,' ',"");
					                
					                if(txtTotal.contains(" "))
					                	txtTotal = ObjectUtil.replaceChar(txtTotal,' ',"");
					                
					                if(txtFecha.contains(" "))
					                	txtFecha = ObjectUtil.replaceChar(txtFecha,' ',"");
					                
					                
					                recibo.setSecuencia(txtSecuencia);
					                
					                //recibo.setGlosa(txtNombre);
					                //recibo.setFecha(ObjectUtil.tramaToTimestamp(txtFecha.substring(6, 10)+txtFecha.substring(3, 5)+txtFecha.substring(0, 2)));
					                if(txtFecha.contains("/"))
					                	recibo.setFecha(ObjectUtil.tramaToTimestamp(txtFecha.substring(6, 10)+txtFecha.substring(3, 5)+txtFecha.substring(0, 2)));
					                else
					                	recibo.setFecha(null);
					                
					                if(!txtMoneda.equals("S/"))
					                	recibo.setMoneda(null);
					                else
					                	recibo.setMoneda(txtMoneda);
					                
					                if(StringUtils.isNotEmpty(txtTotal)){
					                	if(Integer.parseInt(txtTotal)==0){
						                	recibo.setImporte(cero);
						                }else{
						                	recibo.setImporte(ObjectUtil.tramaToBigDecimal(txtTotal,2,""));
						                }
					                }else{
					                	recibo.setImporte(cero);
					                }
					                
					                recibo.setNumRecibo(txtCodCli);
					                
					                recibo.setConcepto(txtCodReg);
				                    
					                if(!recibo.getImporte().equals("0.00")){
					                	this.recibos.add((Recibo)recibo);
					                }
				                    
				                    
				                }catch (Exception e) {
				                	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Consulta_Pago_Factura");
	                                break;
	                            }
					    	}
					    }    
			            else{
			                //puchito
			                String puchito = tramaGenerica.substring(j,tramaGenerica.length());
			                //if(Constante.VER_LOG) log.warn("puchito:"+puchito);
			                break;
			            }
					}
					
			    }else {
	      
			    }
			}
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
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
				setNroOperacion(element.elementAt(6).toString().trim());
			}
			
			/*
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setFecha(element.elementAt(6).toString().trim());
			}
			*/
			/*
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setTotal(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			*/
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setMoneda(element.elementAt(6).toString().trim());
			}

			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				this.setGlosas(element.elementAt(6).toString().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				this.setTotal(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA);		
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
	 * @return Returns the glosa.
	 */
	public String getGlosas() {
		return glosas;
	}
	/**
	 * @param glosa The glosa to set.
	 * 
	 */
	public void setGlosas(String glosas) {
		this.glosas = glosas;
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
