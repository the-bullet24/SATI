/*
 * Creado el 14/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.telegiro.facade.impl;

import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.telegiro.domain.Telegiro;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
import pe.bn.telegiro.facade.TelegiroFacade;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Agencia;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TelegiroFacadeImpl implements TelegiroFacade {
	BigDecimal total = new BigDecimal("0");
	/* (sin Javadoc)
	 * @see pe.bn.telegiro.facade.TelegiroFacade#getTelegiro(pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Telegiro getTelegiro(String transaccion,Usuario usuario,Cuenta cuenta, Afiliacion afiliacion, String importe, Agencia agencia, String remoteAddress) throws Exception {
		
		String cTransCodComision = "TL00";
		if (cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL) ) {
			cTransCodComision = "TL00";
		} else {
			cTransCodComision = "TL03";
		}
		
		Transaction t = new Transaction(cTransCodComision);
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
		
//		val = new Vector();
//		val.addElement("oficinaDestino");
//		val.addElement(agencia.getCodigo());
//		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoPersonaBeneficiario");
		val.addElement("1");
		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoDocumentoBeneficiario");
		val.addElement(afiliacion.getTipoDocumento());
		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoPersonaRemitente");
		val.addElement(usuario.getTipoPersona());
		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoDocumentoRemitente");
		val.addElement(afiliacion.getTipoDocumento());
		valores.addElement(val);		

		val = new Vector();
		val.addElement("importe");
		val.addElement(ObjectUtil.formatearMontoTrama(importe));
		valores.addElement(val);		
		
		val = new Vector();
		if (cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL) ) {
			val.addElement("nroTrx");
			val.addElement("7501");
			valores.addElement(val);
		} else {
			val.addElement("nroTrx");
			val.addElement("7801");
			valores.addElement(val);
		}
		
		int totLongitud = afiliacion.getBeneficiario().length();
		
		val = new Vector();
		val.addElement("apellidoBeneficiario");
		//val.addElement(ObjectUtil.cortarCadena(afiliacion.getObjBenef().getApellidoPaterno()+" "+afiliacion.getObjBenef().getApellidoMaterno(),30));
		if (totLongitud < 30){
		    val.addElement(ObjectUtil.cortarCadena(afiliacion.getBeneficiario(),totLongitud));
		} else {
		    val.addElement(ObjectUtil.cortarCadena(afiliacion.getBeneficiario(),30));
		}
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("nombreBeneficiario");
		//val.addElement(ObjectUtil.cortarCadena(afiliacion.getObjBenef().getNombre(),30));
		
		if (totLongitud < 60 && totLongitud > 30 ) {
		    val.addElement(afiliacion.getBeneficiario().trim().substring(30, totLongitud));
		} else if (totLongitud > 60 ) {
		        val.addElement(afiliacion.getBeneficiario().trim().substring(30, 60));
		} else {
		    val.addElement(" ");
		}

		valores.addElement(val);
		
		String remitente = usuario.getNombre();
		
		val = new Vector();
		val.addElement("apellidoRemitente");
		val.addElement(ObjectUtil.cortarCadena(remitente,30));
		valores.addElement(val);		
		
		val = new Vector();
		val.addElement("nombreRemitente");
		if(remitente.length()>30){
			val.addElement(ObjectUtil.cortarCadena(remitente.substring(30),30));
		}
		else{
			val.addElement("");
		}		
		valores.addElement(val);		

		
		val = new Vector();
		val.addElement("documentoBeneficiario");
		
		String docBen = "";
		
		if(afiliacion.getObjBenef().getNumeroDocumento()==null){
			 docBen="";
		}
		else{
			 docBen= afiliacion.getObjBenef().getNumeroDocumento();
		}
		val.addElement(docBen+afiliacion.getNroDocumento());
		valores.addElement(val);		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		
		t.setValues(valores);
		t.setCuentas(ctas);
		
		return (Telegiro)new TelegiroImpl(t,cuenta, afiliacion, new BigDecimal(importe),agencia,usuario);
	}

	/* (sin Javadoc)
	 * @see pe.bn.telegiro.facade.TelegiroFacade#pagarTelegiro(pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Telegiro pagarTelegiro(String transaccion, TelegiroImpl telegiro, String remoteAddress, Usuario usuario, HttpServletRequest request) throws Exception {
		
		String cTransCod = "";
		if (telegiro.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL) ) {
			cTransCod = "TL01";
		} else {
			cTransCod = "TL02";
		}
		
		//Transaction t = new Transaction(transaccion);
		Transaction t = new Transaction(cTransCod);
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(cTransCod);
		//val.addElement(transaccion);
		valores.addElement(val);		

		val = new Vector();
		val.addElement("cuentaOrigen");
		val.addElement(telegiro.getCuenta().getNumeroProducto());
		valores.addElement(val);		

		val = new Vector();
		val.addElement("importe");
		val.addElement(ObjectUtil.formatearMontoTrama(telegiro.getImporte()));

		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoPersonaBeneficiario");
		val.addElement("01");
		valores.addElement(val);		
		
		val = new Vector();
		val.addElement("tipoDocumentoBeneficiario");
		val.addElement(telegiro.getAfiliacion().getCodigoServicio());
		valores.addElement(val);

		val = new Vector();
		val.addElement("tipoPersonaRemitente");
		val.addElement(usuario.getTipoPersona());
		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoDocumentoRemitente");
		val.addElement(telegiro.getAfiliacion().getTipoDocumento());
		valores.addElement(val);		

		if (telegiro.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL) ) {
			val = new Vector();
			val.addElement("nroTrx");
			val.addElement("7520");
			valores.addElement(val);
		} else {
			val = new Vector();
			val.addElement("nroTrx");
			val.addElement("7820");
			valores.addElement(val);
		}
		
		if (telegiro.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL) ) {
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);		
		} else {
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);
			
		}
//		val = new Vector();
//		val.addElement("oficinaDestino");
//		val.addElement(telegiro.getAgencia().getCodigo());
//		valores.addElement(val);		

		val = new Vector();
		val.addElement("comisionBN");
		val.addElement("000000000000000");
		valores.addElement(val);		

		val = new Vector();
		val.addElement("ITFBN");
		val.addElement("000000000000000");
		valores.addElement(val);		

		val = new Vector();
		val.addElement("comisionGiro");
		val.addElement(ObjectUtil.formatearMontoTrama(telegiro.getComision()));
		valores.addElement(val);		

		val = new Vector();
		val.addElement("ITFGiro");
		val.addElement(ObjectUtil.formatearMontoTrama(telegiro.getItf()));
		valores.addElement(val);		

		val = new Vector();
		val.addElement("apellidoBeneficiario");
		val.addElement(ObjectUtil.cortarCadena(telegiro.getAfiliacion().getObjBenef().getApellidoPaterno().toUpperCase()+" "+telegiro.getAfiliacion().getObjBenef().getApellidoMaterno().toUpperCase(),30));
		valores.addElement(val);		
		
		val = new Vector();
		val.addElement("nombreBeneficiario");
		val.addElement(ObjectUtil.cortarCadena(telegiro.getAfiliacion().getObjBenef().getNombre().toUpperCase(),30));
		valores.addElement(val);		
		
		String remitente = usuario.getNombre().toUpperCase();
		
		val = new Vector();
		val.addElement("apellidoRemitente");
		val.addElement(ObjectUtil.cortarCadena(remitente,30));
		valores.addElement(val);		
		
		val = new Vector();
		val.addElement("nombreRemitente");
		if(remitente.length()>30){
			val.addElement(ObjectUtil.cortarCadena(remitente.substring(30),30));
		}
		else{
			val.addElement("");
		}
		valores.addElement(val);		

		
		val = new Vector();
		val.addElement("nroDocs");
		val.addElement(ObjectUtil.setearCaracterRight(telegiro.getAfiliacion().getNumeroServicio(), ' ', 15 - telegiro.getAfiliacion().getNumeroServicio().length())+ObjectUtil.setearCaracterRight(telegiro.getAfiliacion().getNroDocumento(), ' ', 15 - telegiro.getAfiliacion().getNroDocumento().length()));
		valores.addElement(val);		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		
		t.setValues(valores);
		t.setCuentas(ctas);
		//		 TODO esta para es para validar el Limite de Operacion.
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(ObjectUtil.deFormatearMonto(telegiro.getImporte()));
		/**
		 * Comentado por Solicitud de Canales Virtuales
		 * Fecha : 10/04/2012
		 */
		//mont=mont.add(ObjectUtil.deFormatearMonto(telegiro.getComision()));
		//mont=mont.add(ObjectUtil.deFormatearMonto(telegiro.getItf()));
		
		String totalLimite = ObjectUtil.replaceChar(mont.toString(), '.', "");
	    totalLimite = ObjectUtil.replaceChar(totalLimite, ',', "");
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,telegiro.getCuenta(),ObjectUtil.tramaToBigDecimal(totalLimite.toString()));
		telegiro.pagar(t,usuario);

		GeneralTest gt= new GeneralTest();
		if (telegiro.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL) ) {
			gt.generarLog2(t,usuario,Constante.REFRENDO_TELEGIRO_MN,telegiro,request);
		}else{
			gt.generarLog2(t,usuario,Constante.REFRENDO_TELEGIRO_ME,telegiro,request);
		}
		
		if(telegiro.getArrayRuleException()!=null){
		    throw telegiro.getArrayRuleException();
		}
		
		return telegiro;
	}
 
}
