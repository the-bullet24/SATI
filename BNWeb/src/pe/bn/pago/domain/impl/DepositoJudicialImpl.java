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
 * @author Mily Dolores Bustamante
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DepositoJudicialImpl extends PagoImpl implements Serializable, DepositoJudicial{
	private static LoggerSati log3 = LoggerSati.getInstance(DepositoJudicialImpl.class.getName());
	
	private String hora;
	private String fecha;
	private String numero;
	private String numUnico;
	private String nombreDemandado1;
	private String nombreDemandado2;
	private String nombreDemandante1;
	private String nombreDemandante2;
	private String nombreCorte1;
	private String nombreCorte2;
	private String nombreJuzgado;
	private String nombreDelito;
	private String codJuzgadoPJ;
	private String codUnicoExp;
	private String tipoDocumento;
	private String numDocumento;
	private String nombreBeneficiario1;
	private String nombreBeneficiario2;
	private String descMoneda;
	private String importeLiq;
	private String codMoneda;
	private String tipoPersona;
	private String descTipoDocumento;
	
	

	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}



	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}



	public String getCodMoneda() {
		return codMoneda;
	}



	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}



	



	public String getImporteLiq() {
		return importeLiq;
	}



	public void setImporteLiq(String importeLiq) {
		this.importeLiq = importeLiq;
	}



	public String getTipoPersona() {
		return tipoPersona;
	}



	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}



	public String getCodJuzgadoPJ() {
		return codJuzgadoPJ;
	}



	public void setCodJuzgadoPJ(String codJuzgadoPJ) {
		this.codJuzgadoPJ = codJuzgadoPJ;
	}



	public String getCodUnicoExp() {
		return codUnicoExp;
	}



	public void setCodUnicoExp(String codUnicoExp) {
		this.codUnicoExp = codUnicoExp;
	}



	public String getDescMoneda() {
		return descMoneda;
	}



	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}



	public String getNombreBeneficiario1() {
		return nombreBeneficiario1;
	}



	public void setNombreBeneficiario1(String nombreBeneficiario1) {
		this.nombreBeneficiario1 = nombreBeneficiario1;
	}



	public String getNombreBeneficiario2() {
		return nombreBeneficiario2;
	}



	public void setNombreBeneficiario2(String nombreBeneficiario2) {
		this.nombreBeneficiario2 = nombreBeneficiario2;
	}



	public String getNombreCorte1() {
		return nombreCorte1;
	}



	public void setNombreCorte1(String nombreCorte1) {
		this.nombreCorte1 = nombreCorte1;
	}



	public String getNombreCorte2() {
		return nombreCorte2;
	}



	public void setNombreCorte2(String nombreCorte2) {
		this.nombreCorte2 = nombreCorte2;
	}



	public String getNombreDelito() {
		return nombreDelito;
	}



	public void setNombreDelito(String nombreDelito) {
		this.nombreDelito = nombreDelito;
	}



	public String getNombreDemandado1() {
		return nombreDemandado1;
	}



	public void setNombreDemandado1(String nombreDemandado1) {
		this.nombreDemandado1 = nombreDemandado1;
	}



	public String getNombreDemandado2() {
		return nombreDemandado2;
	}



	public void setNombreDemandado2(String nombreDemandado2) {
		this.nombreDemandado2 = nombreDemandado2;
	}



	public String getNombreDemandante1() {
		return nombreDemandante1;
	}



	public void setNombreDemandante1(String nombreDemandante1) {
		this.nombreDemandante1 = nombreDemandante1;
	}



	public String getNombreDemandante2() {
		return nombreDemandante2;
	}



	public void setNombreDemandante2(String nombreDemandante2) {
		this.nombreDemandante2 = nombreDemandante2;
	}



	public String getNombreJuzgado() {
		return nombreJuzgado;
	}



	public void setNombreJuzgado(String nombreJuzgado) {
		this.nombreJuzgado = nombreJuzgado;
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
	
	
		Vector resutado = procesar(t);
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNumUnico(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setNombreDemandado1(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setNombreDemandado2(element.elementAt(6).toString().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setNombreDemandante1(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setNombreDemandante2(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setNombreCorte1(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setNombreCorte2(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setNombreJuzgado(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				this.setNombreDelito(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				this.setCodJuzgadoPJ(element.elementAt(6).toString().trim());
			}
	
		}
		
		t.setObjeto(this);
		//generarLog(t,usuario,Constante.REFRENDO_PAGO_FACTURA);		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	public void consultarLiquidacion(Transaction t, Usuario usuario) throws Exception{
		
		
		Vector resutado = procesar(t);
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNumUnico(element.elementAt(6).toString().trim());
			}
//			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
//				this.setNombreDemandado1(element.elementAt(6).toString().trim());
//			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setTipoDocumento(element.elementAt(6).toString().trim());
			}
//			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setNumDocumento(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setNombreBeneficiario1(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setNombreBeneficiario2(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setDescMoneda(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				
				this.setImporteLiq(""+ObjectUtil.tramaToBigDecimal1(element.elementAt(6).toString().trim(),2));
				//System.out.println("this.setImporte:"+this.getImporte());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				this.setTipoDocumento(""+Integer.parseInt(element.elementAt(6).toString().trim()));
				this.setDescTipoDocumento(ObjectUtil.getTipoDocumento(Integer.parseInt(element.elementAt(6).toString().trim())));
				
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
	

}
