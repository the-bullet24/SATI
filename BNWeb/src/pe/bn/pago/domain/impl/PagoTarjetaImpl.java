/*
 * Creado el 13/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoTarjeta;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoTarjetaImpl extends PagoImplNueva implements PagoTarjeta, Serializable{

	private Afiliacion afiliacion;
	private Cuenta cuenta;
	private String moneda;
	private String tipoPlaza;
	private String moneda_;
	private String numTransferencia;
	private BigDecimal 	comisionIB;
	private BigDecimal 	comisionIBConvertido;
	private BigDecimal 	importeNeto;
	private String flagRefrendo;
	/*MGL*/
	private String cuentaPropia;
	
	

	//private String 		beneficiario;
	
	
	public BigDecimal getImporteNeto() {
		return importeNeto;
	}
	public void setImporteNeto(BigDecimal importeNeto) {
		this.importeNeto = importeNeto;
	}
	public BigDecimal getComisionIBConvertido() {
		return comisionIBConvertido;
	}
	public void setComisionIBConvertido(BigDecimal comisionIBConvertido) {
		this.comisionIBConvertido = comisionIBConvertido;
	}
	public BigDecimal getComisionIB() {
		return comisionIB;
	}
	public void setComisionIB(BigDecimal comisionIB) {
		this.comisionIB = comisionIB;
	}
	public String getNumTransferencia() {
		return numTransferencia;
	}
	public void setNumTransferencia(String numTransferencia) {
		this.numTransferencia = numTransferencia;
	}
	public String getTipoPlaza() {
		return tipoPlaza;
	}
	public void setTipoPlaza(String tipoPlaza) {
		this.tipoPlaza = tipoPlaza;
	}
	public String getMoneda_() {
		return moneda_;
	}
	public void setMoneda_(String moneda_) {
		this.moneda_ = moneda_;
	}	
	
	
	public String getCuentaPropia() {
		return cuentaPropia;
	}
	public void setCuentaPropia(String cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}
	//	public String getBeneficiario() {
//		return beneficiario;
//	}
//	public void setBeneficiario(String beneficiario) {
//		this.beneficiario = beneficiario;
//	}
	public PagoTarjetaImpl() {
		
	}
	public PagoTarjetaImpl(Transaction t ,Cuenta cuenta, Afiliacion afiliacion, String moneda, BigDecimal importe) throws Exception{

		Vector resultado = procesar(t);
		this.cuenta = cuenta;
		this.afiliacion = afiliacion;
		this.moneda = moneda;
		setImporte(importe);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				//Cuenta de cargo
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				setImporteAlCambio(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				setTotal(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				
			}
			
		}
		
		if(getArrayRuleException()!=null)throw getArrayRuleException();
	}
	
	public  void getPagoTarjetaEnLinea (Transaction t ,Cuenta cuenta, Afiliacion afiliacion, String moneda, BigDecimal importe) throws Exception{

		Vector resultado = procesar1(t);
		this.cuenta = cuenta;
		this.afiliacion = afiliacion;
		this.moneda = moneda;
		String nombre ="";
		String appaterno ="";
		String apmaterno ="";
		
		setImporte(importe);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				//Cuenta de cargo
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				setImporteAlCambio(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
			
				setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
				
				//System.out.println("getItf():"+getItf());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				setTotal(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				setTipoPlaza(ObjectUtil.tramaToString(element.elementAt(6).toString()));
				
				//System.out.println("getTipoPlaza():"+getTipoPlaza());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				setComisionIB(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
				
				//System.out.println("setComisionIB():"+getComisionIB());
			}
			
			/*MGL*/
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){				
				setCuentaPropia(element.elementAt(6).toString().trim());
				System.out.println("getCuentaPropia():::"+getCuentaPropia().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				afiliacion.setBeneficiario(element.elementAt(6).toString().trim());
				setAfiliacion(afiliacion);
				//System.out.println("getBeneficiario():"+afiliacion.getBeneficiario());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				afiliacion.setTipoDocumentoBenef(element.elementAt(6).toString());
				setAfiliacion(afiliacion);
				//System.out.println("getTipoDocumentoBenef():"+afiliacion.getTipoDocumentoBenef().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
				afiliacion.setNroDocumentoBenef(element.elementAt(6).toString());
				setAfiliacion(afiliacion);
				//System.out.println("getNroDocumentoBenef():"+afiliacion.getNroDocumentoBenef().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
				setComisionIBConvertido(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
				//System.out.println("getComisionIBConvertido():"+getComisionIBConvertido());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){				
				afiliacion.setIdentificadorCCE1(element.elementAt(6).toString().trim());	
				setAfiliacion(afiliacion);
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_16)){							
				afiliacion.setIdentificadorCCE2(element.elementAt(6).toString().trim());
				setAfiliacion(afiliacion);
//				System.out.println("afil.getIdentificadorCCE1():"+afil.getIdentificadorCCE2());		
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
				   
				appaterno = element.elementAt(6).toString().trim();
//			    System.out.println("appaterno:"+appaterno);
			 
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_19)){
				   
				apmaterno = element.elementAt(6).toString().trim();
//			    System.out.println("apmaterno:"+apmaterno);
			 
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_20)){
				   
				nombre = element.elementAt(6).toString().trim();
//			    System.out.println("nombre:"+nombre);
			 
			}
			afiliacion.setBeneficiario(appaterno+" "+apmaterno+" "+nombre);
		}
		
		if(getArrayRuleException()!=null)throw getArrayRuleException();
	}
	
	/**
	 * @param afiliacion El afiliacion a establecer.
	 */
	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @param moneda El moneda a establecer.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return Devuelve afiliacion.
	 */
	public Afiliacion getAfiliacion() {
		return afiliacion;
	}
	/**
	 * @return Devuelve cuenta.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}
	/**
	 * @return Devuelve moneda.
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * 
	 * @param transaccion
	 * @param datos
	 * @param cuentas
	 * @param usuario
	 * @throws Exception
	 */
	public void pagar(Transaction t, Usuario usuario) throws Exception{
		/**
		 * Comentado por solicitud de Canales Virtuales (LP)
		 * Fecha : 10/04/2012
		 */
	    //String totalLimite = ObjectUtil.replaceChar(this.getTotal(), '.', "");
	    //totalLimite = ObjectUtil.replaceChar(totalLimite, ',', "");
	    
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(ObjectUtil.deFormatearMonto(this.getTotal()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(this.getComision()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(this.getItf()));
	    
	    //FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),mont);
	    
		Vector resultado = procesar(t);
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				setNroOperacion(element.elementAt(6).toString());
			}
		}
		t.setObjeto(this);
		
	}
	
	public void pagarEnLinea(Transaction t, Usuario usuario) throws Exception{
		
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(ObjectUtil.deFormatearMonto(this.getTotal()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(this.getComision()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(this.getItf()));
	    
	    //FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),mont);
	    
		Vector resultado = procesar(t);
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				//
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
				setNumTransferencia(element.elementAt(6).toString());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
				setNroOperacion(element.elementAt(6).toString());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_16)){
	 			
			    setFlagRefrendo( element.elementAt(6).toString().trim());
			}
		}
		t.setObjeto(this);
		
	}

	public String getSimboloMoneda(){
		if(moneda==null)
			return null;		
		if(moneda.equals(Constante.MONEDA_SOL))
			return Constante.SIMBOLO_MONEDA_SOL;
		if(moneda.equals(Constante.MONEDA_DOLAR))
			return Constante.SIMBOLO_MONEDA_DOLAR;
		return null;
	}

	public String getNombreMoneda(){
		if(moneda == null)
			return null;
		else if(moneda.equals(Constante.MONEDA_DOLAR))
			return Constante.NOMBRE_MONEDA_DOLAR;		
		else if (moneda.equals(Constante.MONEDA_SOL))
			return Constante.NOMBRE_MONEDA_SOL;
		return null;
	}
	
	public boolean getEsMismaMoneda(){
		if(this.cuenta.getMonedaProducto().equals(this.moneda))
			return true;
		return false;
	}
	public String getFlagRefrendo() {
		return flagRefrendo;
	}
	public void setFlagRefrendo(String flagRefrendo) {
		this.flagRefrendo = flagRefrendo;
	}

}