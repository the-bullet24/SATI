/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.facade.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tempuri.GenerateRequest;
import org.tempuri.GenerateResponse;
import org.tempuri.PinblockServiceSoapProxy;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.tarjeta.facade.TarjetaFacade;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.common.Funciones;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;
import pe.cosapi.system.Key4Digits;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TarjetaFacadeImpl implements TarjetaFacade{
    
	BNAplicacion bn = BNAplicacion.getInstance();
	/* (sin Javadoc)
	 * @see pe.bn.tarjeta.facade.TarjetaFacade#generarClaveInternet(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Tarjeta generarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr) throws Exception {

		if(!claveInternet.equals(claveInternet_)){
			//if(Constante.VER_LOG)logger.info("MENSAJEEEEEEEEE-->"+bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		}
			
			
		
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
		
		String clave4 = usuario.getTarjeta().getClave();
		

		
		Key4Digits key4Gen = new Key4Digits(); 
		claveTarjeta =  key4Gen.encriptar(claveTarjeta);
		
	
		FacadeFactory.getGeneralFacade().processGenerationKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.generarClave(t,usuario);		
		return (Tarjeta)tarjeta;
		
	}
	
	public Tarjeta generarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {

//		if(!claveInternet.equals(claveInternet_)){
//			//if(Constante.VER_LOG)logger.info("MENSAJEEEEEEEEE-->"+bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
//			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
//		}
//			
			
		
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
		
		String clave4 = usuario.getTarjeta().getClave();
		

		
		Key4Digits key4Gen = new Key4Digits(); 
		claveTarjeta =  key4Gen.encriptar(claveTarjeta);
		
	
		FacadeFactory.getGeneralFacade().processGenerationKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.generarClave(t,usuario);	
		
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog3(t,usuario,Constante.REFRENDO_GENERACION_CLAVE_INTERNET,tarjeta, request);
		return (Tarjeta)tarjeta;
		
	}
	
	public Tarjeta generarNuevaClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {
		
		Transaction t = new Transaction("GC05");
		
		Vector val;
		Vector valores = new Vector();
			
		val = new Vector();
		val.addElement("fechaNacimiento");
		val.addElement(usuario.getFechaNacimiento());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("codTransaccion");
		val.addElement("1102");
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("tipoDocumento");
		val.addElement(usuario.getTipoDocumento());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("nroDocumento");
		val.addElement(usuario.getNumDocumento());
		valores.addElement(val);

		val = new Vector();
		
		val.addElement("nroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("pinTarjeta");
		val.addElement(usuario.getTarjeta().getClave());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("codOperacion");
		val.addElement(Constante.WCOPERATION_GENERATION_KEY6);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("encrip4");
		val.addElement(claveTarjeta);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("encrip1");
		val.addElement(claveInternet);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("encrip2");
		val.addElement(claveInternet_);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
			
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
			
		//FacadeFactory.getGeneralFacade().processGenerationKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		String tran =Constante.CAMBIO_CLAVE_INTERNET_NUEVO;	
		tarjeta.generarClaveInternetAfiliacion(t, usuario, tran, tarjeta, request);	
			
		return (Tarjeta)tarjeta;
				
	}
	
public Tarjeta generarNuevaClaveOlvidoInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {

		Transaction t = new Transaction("GC05");
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("fechaNacimiento");
		val.addElement(usuario.getFechaNacimiento());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("codTransaccion");
		val.addElement("1102");
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("tipoDocumento");
		val.addElement(usuario.getTipoDocumento());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("nroDocumento");
		val.addElement(usuario.getNumDocumento());
		valores.addElement(val);

		val = new Vector();
		val.addElement("nroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("pinTarjeta");
		val.addElement(usuario.getTarjeta().getClave());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("codOperacion");
		val.addElement(Constante.WCOPERATION_GENERATION_OLVIDO_KEY6);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("encrip4");
		val.addElement(claveTarjeta);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("encrip1");
		val.addElement(claveInternet);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("encrip2");
		val.addElement(claveInternet_);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
			
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
			
		//FacadeFactory.getGeneralFacade().processGenerationKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		//MGL-LOG
					
		tarjeta.generarClaveInternetOlvido(t, usuario, transaccion, tarjeta, request);	
				
		
		return (Tarjeta)tarjeta;
		
	}
	
public Tarjeta generarOlvidoClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {

		if(!claveInternet.equals(claveInternet_)){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		}
			
			
		
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
		
		String clave4 = usuario.getTarjeta().getClave();
		

		
		Key4Digits key4Gen = new Key4Digits(); 
		claveTarjeta =  key4Gen.encriptar(claveTarjeta);
		
	
		FacadeFactory.getGeneralFacade().processGenerationOlvidoKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.generarClave(t,usuario);	
		
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog3(t,usuario,Constante.REFRENDO_OLVIDO_CLAVE_INTERNET,tarjeta, request);
		return (Tarjeta)tarjeta;
		
	}

	public Tarjeta generarExpiraClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {
	
		if(!claveInternet.equals(claveInternet_)){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		}
			
			
		
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
		
		String clave4 = usuario.getTarjeta().getClave();
		
	
		
		Key4Digits key4Gen = new Key4Digits(); 
		claveTarjeta =  key4Gen.encriptar(claveTarjeta);
		
	
		FacadeFactory.getGeneralFacade().processGenerationOlvidoKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.generarClave(t,usuario);	
		
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog3(t,usuario,Constante.REFRENDO_OLVIDO_CLAVE_INTERNET,tarjeta, request);
		return (Tarjeta)tarjeta;
		
	}


	
public Tarjeta generarClaveAfiliacion(Usuario usuario,  String claveVal, String claveVal_, String remoteAddr) throws Exception{
		
		Transaction t = new Transaction(Constante.GENERACION_CLAVE_INTERNET);
		if(!claveVal.equals(claveVal_)){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		}
			
	
		
		Vector val;
		Vector valores = new Vector();
		
	

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
	
		val = new Vector();
		val.addElement("claveVal");
		val.addElement(claveVal);
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
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
	
	
		//FacadeFactory.getGeneralFacade().processGenerationKey6DigitsAhorro(usuario.getTarjeta().getNumero(),claveTarjeta,claveInternet,claveInternet_);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		
		//tarjeta.generarClave(t,usuario);		
		return (Tarjeta)tarjeta;
	
	
	}

	public Tarjeta cambiarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {
		if(!claveInternet.equals(claveInternet_))
			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		
		transaccion = Constante.CLAVE_INTERNET_CAMBIO;
		Transaction t = new Transaction(transaccion);
		
		
		Vector val;
		Vector valores = new Vector();
		
		
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		/**AHORRO
		 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros
		 * @param wPINEncript   : Clave de 4 digitos sin Encriptar
		 * @param nuevaClave  	: Nueva clave de 6 digitos 
		 * @param claveActual 	: clave actual de 6 Digitos.(clave que sera modificada)
		 * @throws Exception    
		 */
		String numTarjeta = usuario.getTarjeta().getClave();
	
		//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),claveTarjeta,claveTarjeta);
		FacadeFactory.getGeneralFacade().processChangeKey6DigitsAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),claveTarjeta,claveInternet);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.cambiarClave(t,usuario);
		
		
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog4(t,usuario,null,tarjeta, request);
		
		return (Tarjeta)tarjeta;
	}
	
	public Tarjeta desafiliarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception {
		if(!claveInternet.equals(claveInternet_))
			throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		
//		System.out.println();
		
		Transaction t = new Transaction(transaccion);
		transaccion = "CD00";
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		/**AHORRO
		 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros
		 * @param wPINEncript   : Clave de 4 digitos sin Encriptar
		 * @param nuevaClave  	: Nueva clave de 6 digitos 
		 * @param claveActual 	: clave actual de 6 Digitos.(clave que sera modificada)
		 * @throws Exception    
		 */
		String numTarjeta = usuario.getTarjeta().getClave();
			
		//if(Constante.VER_LOG)System.out.println("Numero de Tarjeta:"+usuario.getTarjeta().getNumero());
		//if(Constante.VER_LOG)System.out.println("numTarjeta:"+numTarjeta);
		//if(Constante.VER_LOG)System.out.println("claveTarjeta:"+claveTarjeta);
		//if(Constante.VER_LOG)System.out.println("claveInternet:"+claveInternet);			

		//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),claveTarjeta,claveTarjeta);
		FacadeFactory.getGeneralFacade().processDesafiliaKey6DigitsAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),claveTarjeta,claveInternet);
		
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.desafiliarClave(t,usuario);		
		
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog4(t,usuario,null,tarjeta, request);
		
		return (Tarjeta)tarjeta;

	}
	
	public Tarjeta desafiliarClaveSeis(String cNumTarjeta, String cClave, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr) throws Exception {
		if(!claveInternet.equals(claveInternet_))
		throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
		
		Transaction t = new Transaction(transaccion);
		
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("txtNumeroTarjeta");
		val.addElement(cNumTarjeta);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtClaveTarjeta");
		val.addElement(claveTarjeta);
		valores.addElement(val);
	
		val = new Vector();
		val.addElement("txtClaveInternet");
		val.addElement(claveInternet);
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		/**AHORRO
		 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros
		 * @param wPINEncript   : Clave de 4 digitos sin Encriptar
		 * @param nuevaClave  	: Nueva clave de 6 digitos 
		 * @param claveActual 	: clave actual de 6 Digitos.(clave que sera modificada)
		 * @throws Exception    
		 */

		FacadeFactory.getGeneralFacade().processValidationCardAhorro(cNumTarjeta,cClave,claveTarjeta,claveTarjeta);
		FacadeFactory.getGeneralFacade().processDesafiliaKey6DigitsAhorro(cNumTarjeta,cClave,claveTarjeta,claveInternet);
		
		TarjetaImpl tarjeta = new TarjetaImpl(cNumTarjeta);
		//tarjeta.desafiliarClave(t,usuario);		
		return (Tarjeta)tarjeta;

	}
	
	public Tarjeta bloquearTarjeta(Usuario usuario, String transaccion, String remoteAddr, HttpServletRequest request) throws Exception {
		
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		val = new Vector();
		val.addElement("nroTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
			
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.setNroOperacion();
		tarjeta.setTarjetaOculta(ObjectUtil.ocultarCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA));
		tarjeta.bloquear(t,usuario);
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_BLOQUE_TARJETA,tarjeta, request);
		if(tarjeta.getArrayRuleException()!=null){
		    throw tarjeta.getArrayRuleException();
		}
		return (Tarjeta)tarjeta;
	}
	
	public Tarjeta bloquearTarjetaDni(Usuario usuario, String transaccion, String remoteAddr, HttpServletRequest request) throws Exception {
		
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);

		
		val = new Vector();
		val.addElement("numTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("nroPin");
		val.addElement(usuario.getTarjeta().getClave());
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
		v.addElement(remoteAddr);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);		
			
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		
		tarjeta.setNroOperacion();
		tarjeta.setTarjetaOculta(ObjectUtil.ocultarCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA));
		
		tarjeta.bloquearDni(t,usuario);
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_BLOQUE_TARJETA,tarjeta, request);
		if(tarjeta.getArrayRuleException()!=null){
		    throw tarjeta.getArrayRuleException();
		}
		return (Tarjeta)tarjeta;
	}
	
	
	public Tarjeta getCambiarClaveInterna(String codTra, Usuario usuario, String claveAnterior, String claveNueva, HttpServletRequest request) throws Exception {
		
		   System.out.println("::::getCambiarClave::::");
		   String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_PINBLOCK_V2);
		   
		   PinblockServiceSoapProxy proxy = new PinblockServiceSoapProxy();
		   proxy.setEndpoint(url);
		   
		   /* clave antigua */
		   /*ini*/
		   GenerateRequest gRequestAnte = new GenerateRequest();
		   GenerateResponse gResponseAnte = new GenerateResponse();
		   
		   gRequestAnte.setCardNumber(usuario.getTarjeta().getNumero());
		   gRequestAnte.setPinNew(claveAnterior);
		   gRequestAnte.setPinConfirm(claveAnterior);
		   
		   gResponseAnte = proxy.generate(gRequestAnte);
		   
		   /*fin*/
		   
		   /*Clave nueva*/
		   /*ini*/
		   GenerateRequest gRequestNew = new GenerateRequest();
		   GenerateResponse gResponseNew = new GenerateResponse();
		   
		   gRequestNew.setCardNumber(usuario.getTarjeta().getNumero());
		   gRequestNew.setPinNew(claveNueva);
		   gRequestNew.setPinConfirm(claveNueva);
		   
		   gResponseNew = proxy.generate(gRequestNew);
		   /*fin*/
		
		   String codTransaccion = ""; 
			
			Vector valores 	= new Vector();
			Vector val 		= new Vector();
			
			String opt 				= "";
			String tipTransferencia = "1";
			String tipAfectacion    = "2";
						
			
//		    val = ObjectUtil.getVectorComponent("fechaNacimiento",usuario.getFechaNacimiento());
//			valores.addElement(val);
		    
//			codTransaccion = "1102";
//			val = ObjectUtil.getVectorComponent("codTransaccion",codTransaccion);
//			valores.addElement(val);
			
//			val = ObjectUtil.getVectorComponent("tipoDocumento",usuario.getTipoDocumento());
//			valores.addElement(val);
			
//			val = ObjectUtil.getVectorComponent("nroDocumento",Funciones.llenarCerosAlaIzquierdaV2(usuario.getNumDocumento().trim(), 12) );
//			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("nroTarjeta",usuario.getTarjeta().getNumero());
			valores.addElement(val);			
			
			val = ObjectUtil.getVectorComponent("pinTarjeta","0000");
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("codOperacion","350");
			valores.addElement(val);
			
//			val = ObjectUtil.getVectorComponent("encrip4","");
//			valores.addElement(val);
			
						
			val = ObjectUtil.getVectorComponent("encrip1",gResponseNew.getPinEncryptBlock2());
			valores.addElement(val);
			
			
			val = ObjectUtil.getVectorComponent("encrip2",gResponseAnte.getPinEncryptBlock2());
			valores.addElement(val);
			
		
			val = ObjectUtil.getVectorComponent("encrip5",gResponseNew.getPinEncryptBlock1());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("encrip6",gResponseAnte.getPinEncryptBlock1());
			valores.addElement(val);
			
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
			v.addElement(request.getRemoteAddr());
			ctas.addElement(v);		
			
			Transaction t = new Transaction(codTra);
			t.setValues(valores);
			t.setCuentas(ctas);	
			
			TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
			
			tarjeta.setNroOperacion();
			tarjeta.setTarjetaOculta(ObjectUtil.ocultarCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA));
			
			tarjeta.cambiarClaveCaducidad(t,usuario,tarjeta, request);
			
//			GeneralTest gt= new GeneralTest();
//			gt.generarLog3(t,usuario,Constante.REFRENDO_CAMBIO_CLAVE,tarjeta, request);
//			if(tarjeta.getArrayRuleException()!=null){
//			    throw tarjeta.getArrayRuleException();
//			}
			return (Tarjeta)tarjeta;		   
		 
		}
	
	
	public Tarjeta getCambiarClaveExterna(String codTra, Usuario usuario, String claveAnterior, String claveNueva, HttpServletRequest request) throws Exception {
		
		   System.out.println("::::getCambiarClave::::");
		   String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_PINBLOCK_V2);
		   
		   
		   String passwordDesencrip="";
		   String passwordEncripClave6="";
		   Key4Digits keyGen = new Key4Digits(); 
		   passwordDesencrip = keyGen.desencriptar(usuario.getTarjeta().getClave());
		   passwordEncripClave6 = keyGen.encriptar(passwordDesencrip.concat("000000000000"));
			
		   
		   System.out.println("passwordDesencrip:::"+passwordDesencrip);
		   
		   System.out.println("passwordEncripClave6:::"+passwordEncripClave6);
		   
		   
		   
		   PinblockServiceSoapProxy proxy = new PinblockServiceSoapProxy();
		   proxy.setEndpoint(url);
		   
		   /* clave antigua */
		   /*ini*/
		   GenerateRequest gRequestAnte = new GenerateRequest();
		   GenerateResponse gResponseAnte = new GenerateResponse();
		   
		   gRequestAnte.setCardNumber(usuario.getTarjeta().getNumero());
		   gRequestAnte.setPinNew(claveAnterior);
		   gRequestAnte.setPinConfirm(claveAnterior);
		   
		   gResponseAnte = proxy.generate(gRequestAnte);
		   
		   /*fin*/
		   
		   /*Clave nueva*/
		   /*ini*/
		   GenerateRequest gRequestNew = new GenerateRequest();
		   GenerateResponse gResponseNew = new GenerateResponse();
		   
		   gRequestNew.setCardNumber(usuario.getTarjeta().getNumero());
		   gRequestNew.setPinNew(claveNueva);
		   gRequestNew.setPinConfirm(claveNueva);
		   
		   gResponseNew = proxy.generate(gRequestNew);
		   /*fin*/
		
		   String codTransaccion = ""; 
			
			Vector valores 	= new Vector();
			Vector val 		= new Vector();
			
			String opt 				= "";
			String tipTransferencia = "1";
			String tipAfectacion    = "2";
						
			
		    val = ObjectUtil.getVectorComponent("fechaNacimiento",usuario.getFechaNacimiento());
			valores.addElement(val);
		    
			codTransaccion = "1102";
			val = ObjectUtil.getVectorComponent("codTransaccion",codTransaccion);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("tipoDocumento",usuario.getTipoDocumento());
			valores.addElement(val);
			
			//val = ObjectUtil.getVectorComponent("nroDocumento",Funciones.llenarCerosAlaIzquierdaV2(usuario.getNumDocumento().trim(), 12) );
			val = ObjectUtil.getVectorComponent("nroDocumento",usuario.getNumDocumento().trim());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("nroTarjeta",usuario.getTarjeta().getNumero());
			valores.addElement(val);			
			
			val = ObjectUtil.getVectorComponent("pinTarjeta",usuario.getTarjeta().getClave());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("codOperacion","320");
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("encrip4",passwordEncripClave6);
			valores.addElement(val);
			
						
			val = ObjectUtil.getVectorComponent("encrip1",gResponseNew.getPinEncryptBlock2());
			valores.addElement(val);
			
			
			val = ObjectUtil.getVectorComponent("encrip2",gResponseAnte.getPinEncryptBlock2());
			valores.addElement(val);
			
		
			val = ObjectUtil.getVectorComponent("encrip5",gResponseNew.getPinEncryptBlock1());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("encrip6",gResponseAnte.getPinEncryptBlock1());
			valores.addElement(val);
			
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
			v.addElement(request.getRemoteAddr());
			ctas.addElement(v);		
			
			Transaction t = new Transaction(codTra);
			t.setValues(valores);
			t.setCuentas(ctas);
			
			TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
			
			tarjeta.setNroOperacion();
			tarjeta.setTarjetaOculta(ObjectUtil.ocultarCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA));
			
			usuario.setCodigoCic("0");
			usuario.getTarjeta().setTipo("03");
			tarjeta.cambiarClaveCaducidad(t,usuario,tarjeta, request);
			
			GeneralTest gt= new GeneralTest();
			//gt.generarLog2(t,usuario,Constante.REFRENDO_BLOQUE_TARJETA,tarjeta, request);
//			if(tarjeta.getArrayRuleException()!=null){
//			    throw tarjeta.getArrayRuleException();
//			}
			return (Tarjeta)tarjeta;		   
		 
		}

	

}