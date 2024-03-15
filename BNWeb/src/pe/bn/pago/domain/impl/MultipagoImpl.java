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
import pe.bn.pago.domain.DepositoJudicial;
import pe.bn.pago.domain.Multipago;
import pe.bn.pago.domain.PagoFactura;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Afiliaciones;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Recibo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AfiliacionesImpl;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.ReciboImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author Mily Dolores Bustamante
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MultipagoImpl extends PagoImpl implements Serializable, Multipago{
	private static LoggerSati log3 = LoggerSati.getInstance(MultipagoImpl.class.getName());
	
	private String hora;
	private String fecha;
	private String numero;
	private String numUnico;
	private String tipoDocumento;
	private String numDocumento;
	private String descMoneda;
	private String codMoneda;
	private String descTipoDocumento;
	private String codServicio;
	private String desServicio;
	private String fechaCaducidad;
	private String solicitante;
	private String codEstado;
	private String desEstado;
	private String vigencia;
	private Cuenta cuenta;
	private String numRegistros;
	
	
	//Array de Registros
	private List registro;
	private String txtTicket;
	private String txtEntidad;
	private String txtImporte;
	


	public String getTxtEntidad() {
		return txtEntidad;
	}

	public void setTxtEntidad(String txtEntidad) {
		this.txtEntidad = txtEntidad;
	}

	public String getTxtImporte() {
		return txtImporte;
	}

	public void setTxtImporte(String txtImporte) {
		this.txtImporte = txtImporte;
	}

	public String getTxtTicket() {
		return txtTicket;
	}

	public void setTxtTicket(String txtTicket) {
		this.txtTicket = txtTicket;
	}

	public List getRegistro() {
		return registro;
	}

	public void setRegistro(List registro) {
		this.registro = registro;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public String getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}

	public String getCodMoneda() {
		return codMoneda;
	}

	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	public String getDescMoneda() {
		return descMoneda;
	}

	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}

	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}

	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNumUnico() {
		return numUnico;
	}

	public void setNumUnico(String numUnico) {
		this.numUnico = numUnico;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void consultar(Transaction t, Usuario usuario) throws Exception{
	
	
		Vector resutado = procesar1(t);
		this.registro = new ArrayList();
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNumUnico(element.elementAt(6).toString().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setCodEstado(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setCodMoneda(element.elementAt(6).toString().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setSolicitante(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setVigencia(ObjectUtil.getYYYYMMDDFormateada(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setNumRegistros(""+Integer.parseInt(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).equals(Constante.OUTPUT_STRING_8)){
			    if (element.elementAt(6)!= null){
					String tramaGenerica =  element.elementAt(6).toString();
															
					for(int j = 0; j< tramaGenerica.length();){
						
					    MultipagoImpl detalle = new MultipagoImpl();
					  
					    if (j+28 <= tramaGenerica.length()){
			                String tramita = tramaGenerica.substring(j,j=j+28);
			                
			          			              
			                if ((!tramita.trim().equals(""))){

				                try{
				                					                	
				                	String txtTicket  = tramita.substring(0,10); 
				                	String txtEntidad = tramita.substring(11,13);
				                	String txtImporte	   = tramita.substring(14,28);
					               
				                	detalle.setTxtTicket(txtTicket);
				                	detalle.setTxtEntidad(txtEntidad);
				                	detalle.setTxtImporte(""+ObjectUtil.tramaToBigDecimal(txtImporte));
					                
//				                	System.out.println("txtTicket:"+txtTicket);
//				                	System.out.println("txtEntidad:"+txtEntidad);
//				                	System.out.println("txtImporte:"+txtImporte);
					               
					            	this.registro.add((Multipago)detalle);
					           
				                    
				                }catch (Exception e) {
				                	e.printStackTrace();
	                                break;
	                            
					    	}
			              }
					    }    

					}
					
			    }
			}
		
			
		}
		
		t.setObjeto(this);
		//generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA);		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	public void pago(Transaction t, Usuario usuario) throws Exception{
		
	
		String importeSinFormato = ObjectUtil.replaceChar(this.getImporte(),',',"");
		importeSinFormato = ObjectUtil.replaceChar(importeSinFormato,'.',"");
		BigDecimal importeX = ObjectUtil.tramaToBigDecimal(importeSinFormato);
		FacadeFactory.getGeneralFacade().validarLimiteImporte(this.getCuenta(),importeX);
		
		Vector resutado = procesar(t);
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
	
		}
		
		t.setObjeto(this);
		//generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA);		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}

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



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDesServicio() {
		return desServicio;
	}

	public void setDesServicio(String desServicio) {
		this.desServicio = desServicio;
	}

	public String getNumRegistros() {
		return numRegistros;
	}

	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	

}
