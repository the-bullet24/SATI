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

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoFacturaWS;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Factura;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.FacturaImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoFacturaWSImpl extends PagoImpl implements Serializable, PagoFacturaWS{
	private static LoggerSati log3 = LoggerSati.getInstance(PagoFacturaWSImpl.class.getName());
	
	private String hora;
	private String fecha;
	private Cuenta cuenta;
	private FacturaImpl factura;
	private String nroReferencia;
	private String codEntidad;
	private String nomEntidad;
	private String nroChequeo;
	private String cliente;
	private String direccion;
	private String distrito;
	private String moneda;
	private String glosas;
	private String concepto;
	private String codBanco;
	private String codServicio;
	private String numRefEntidad;
	
	private BigDecimal importePago;
	private BigDecimal interes;
	private List facturas;
	private Afiliacion afiliacion;
	private String ctaFormateada;
	
	
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
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
	
	public void getPagoOnline(Transaction t, Usuario usuario) throws Exception{
		this.facturas = new ArrayList();
		Vector resutado = procesar(t);
		Vector recibs= new Vector();
		HashMap mapaRecibos = new HashMap();
		List idsl = new ArrayList();
		String txtConcepto     ="";
		
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			//String codProducto = this.numeroProducto.substring(0,2);
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setCliente(elemento.elementAt(6).toString().trim().replace("#", "Ñ"));
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setGlosas(elemento.elementAt(6).toString().trim());
			}
			
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
			    if (elemento.elementAt(6)!= null){
					String tramaGenerica =  elemento.elementAt(6).toString();

					for(int j = 0; j< tramaGenerica.length();){
					    FacturaImpl factura = new FacturaImpl();
					    if (j+223 <= tramaGenerica.length()){
			                String tramita = tramaGenerica.substring(j,j=j+223);
			              
			                if ((!tramita.substring(0,2).equals("  "))){

				                try{
				                
				                	String txtNroSec    = tramita.substring(0,2);
				                	String txtNroRef    = tramita.substring(3,23); 
				                	String txtNroCuota  = tramita.substring(24,28);
					                String txtCodCli    = tramita.substring(29,49);
					                String txtFecVen    = tramita.substring(50,60);
					                String txtMoneda    = tramita.substring(61,64);
					                String txtImporte   = tramita.substring(65,80);
					                String txtImpCuota  = tramita.substring(83,98).toString();
					                String txtImpMinimo = tramita.substring(98,113).toString();
					                String txtInteres   = tramita.substring(113,128).toString();
					                String txtInterCom  = tramita.substring(128,143).trim();
					                String txtMora      = tramita.substring(143,158).toString();
					                String txtGasto     = tramita.substring(158,173).toString();
					                
					                if(tramita.length()> 173){
					                txtConcepto     = tramita.substring(176,222).trim().toString();	
					                	
					                }
					             
					                
					                txtImporte = ObjectUtil.replaceChar(txtImporte,',',"");
					                txtImporte = ObjectUtil.replaceChar(txtImporte,'.',"");
					                
					                factura.setSecuencia(txtNroSec);
					                factura.setNumRecibo(txtNroRef);
					                factura.setCuota(txtNroCuota);
					                factura.setCodClient(txtCodCli);
					                factura.setFecha(ObjectUtil.tramaToTimestamp(txtFecVen.substring(6, 10)+txtFecVen.substring(3, 5)+txtFecVen.substring(0, 2)));
					                factura.setMoneda(txtMoneda);
					                factura.setImporte(ObjectUtil.tramaToBigDecimal(txtImporte.trim(),2,""));
					                factura.setImpCuota(ObjectUtil.tramaToBigDecimal(txtImpCuota.trim(),2,""));
					                factura.setImpMinimo(ObjectUtil.tramaToBigDecimal(txtImpMinimo.trim(),2,""));
					                factura.setInteres(ObjectUtil.tramaToBigDecimal(txtInteres.trim(),2,""));
					                factura.setInteresCom(ObjectUtil.tramaToBigDecimal(txtInterCom.trim(),2,""));
					                //factura.setComision(ObjectUtil.tramaToBigDecimal(txtImporte.trim(),2,""));
					                factura.setMora(ObjectUtil.tramaToBigDecimal(txtMora.trim(),2,""));
					                factura.setGasto(ObjectUtil.tramaToBigDecimal(txtGasto.trim(),2,""));
					                factura.setConcepto(txtConcepto);
					                
					                factura.setCodMoneda("");
				                    
				                    this.facturas.add((Factura)factura);
				                    
				                }catch (Exception e) {
				                	e.printStackTrace();
				                	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Consulta_Pago_Online");
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
	
	/*
	 * METODO QUE ENVIA LA TRAMA Y SEPARA LOS DATOS RECIBIDOS EN EL PAGO DE FACTURAS
	 * AUTOR: GUR
	 * FECHA: 03/09/2010
	 * getPagoRecarga(Transaction t, Usuario usuario)
	 */
	
	public void getPagoFacturaOnline(Transaction t, Usuario usuario) throws Exception{
		
				
		String importeSinFormato = ObjectUtil.replaceChar(this.getImporte(),',',"");
		
		importeSinFormato = ObjectUtil.replaceChar(importeSinFormato,'.',"");
		
		BigDecimal importeX = ObjectUtil.tramaToBigDecimal(importeSinFormato);
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),importeX);
		
		Vector mascara = procesar(t);
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			try {
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					setImportePago(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					setComision(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					setItf(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					setNroOperacion(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					setImporte(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					setCodBanco(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					setCodServicio(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
					setNumRefEntidad(elemento.elementAt(6).toString().trim());
				}
				
			}catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Pago_Factura_Online");
                break;
            }
		}
		
		//System.out.println("Nro. Operacion INC: "+getNroOperacion());
		
		t.setObjeto(this);
						
		
		if(this.codEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			
			generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA_PARCIAL);
		}
		
		
		else{
			
			if(this.codEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_AMAG)){
				
				generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA_MOD);
			}
			else if(this.codEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
				
				generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA_WS_CLARO);
			}
			else{
				generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA_WS);
			}
			
			
		}
	
		
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
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
	 * @return Devuelve facturas.
	 */
	public List getFacturas() {
		return facturas;
	}
	/**
	 * @param facturas El facturas a establecer.
	 */
	public void setFacturas(List facturas) {
		this.facturas = facturas;
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
	 * @return Devuelve factura.
	 */
	public FacturaImpl getFactura() {
		return factura;
	}
	/**
	 * @param factura El factura a establecer.
	 */
	public void setFactura(FacturaImpl factura) {
		this.factura = factura;
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
	 * @return Returns the codBanco.
	 */
	public String getCodBanco() {
		return codBanco;
	}
	/**
	 * @param codBanco The codBanco to set.
	 */
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
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
	/**
	 * @return Returns the numRefEntidad.
	 */
	public String getNumRefEntidad() {
		return numRefEntidad;
	}
	/**
	 * @param numRefEntidad The numRefEntidad to set.
	 */
	public void setNumRefEntidad(String numRefEntidad) {
		this.numRefEntidad = numRefEntidad;
	}
}
