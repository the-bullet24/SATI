/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.consulta.domain.Prestamo;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.consulta.facade.ConsultaFacade;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Itf;
import pe.cosapi.domain.LimitePrestamo;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.ItfImpl;
import pe.cosapi.domain.impl.LimitePrestamoImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.TipoCambioImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConsultaFacadeImpl implements ConsultaFacade {
	private static LoggerSati log3 = LoggerSati.getInstance(ConsultaFacadeImpl.class.getName());
    //Logger log = Logger.getLogger(ConsultaFacadeImpl.class);

	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaFacade#consultar(java.lang.String, java.lang.String, java.lang.String, pe.cosapi.domain.Usuario)
	 */
	public Object consultar(String codigoConsulta, String nroCuenta, String codigoMoneda, String nroPrestamo, String remoteAddress, Usuario usuario, HttpServletRequest request) throws Exception{

//		System.out.println("consultar>>>>>>>>>>>>>>>");
		String transaccion = "CL01"; 

		Transaction t = new Transaction(transaccion);
		
		ConstanteSesion.WAP_CTA_CTE = nroCuenta;
						
		Vector valores = new Vector();
		Vector valor = new Vector();
		// valor.addElement("transaccion");
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		// valor.addElement("hidCuenta");
		valor.addElement("txtNumCta");
		valor.addElement(nroCuenta);
		valores.add(valor);
		
		if(codigoConsulta.equals("01") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("2");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("1100");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("txtCtaCargo");
			valor.addElement("000000000000000");
			valores.add(valor);
			
		} else if(codigoConsulta.equals("01") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("4");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("8100");
			valores.add(valor);
			
			codigoConsulta = "14";
			
			valor = new Vector();
			valor.addElement("txtCtaCargo");
			valor.addElement("000000000000000");
			valores.add(valor);
		} else if(codigoConsulta.equals("05") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("2");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("0100");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("txtCtaCargo");
			valor.addElement(nroCuenta);
			valores.add(valor);
			
		} else if(codigoConsulta.equals("05") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("4");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("6100");
			valores.add(valor);
			
			codigoConsulta = "18";
			
			valor = new Vector();
			valor.addElement("txtCtaCargo");
			valor.addElement(nroCuenta);
			valores.add(valor);
		} 
		
		else if(codigoConsulta.equals("02") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("1923");
			valores.add(valor);
		} else if(codigoConsulta.equals("02") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("8923");
			valores.add(valor);
			codigoConsulta = "15";
		} 
		else if(codigoConsulta.equals("03") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("");
			valores.add(valor);
		} else if(codigoConsulta.equals("03") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("");
			valores.add(valor);
			codigoConsulta = "16";
		}
		
		else if(codigoConsulta.equals("12") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("2510");
			valores.add(valor);
		} else if(codigoConsulta.equals("12") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("2610");
			valores.add(valor);
			codigoConsulta = "17";
		}
		
		else if(codigoConsulta.equals("06") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("0124");
			valores.add(valor);
		} else if(codigoConsulta.equals("06") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("6124");
			valores.add(valor);
			codigoConsulta = "21";
		}
		
		else if(codigoConsulta.equals("07") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("0125");
			valores.add(valor);
		} else if(codigoConsulta.equals("07") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("6125");
			valores.add(valor);
			codigoConsulta = "22";
		}
		
		
		else if(codigoConsulta.equals("13") && codigoMoneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("2523");
			valores.add(valor);
		} else if(codigoConsulta.equals("13") && codigoMoneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("2623");
			valores.add(valor);
			codigoConsulta = "23";
		} else if(codigoConsulta.equals("09") || codigoConsulta.equals("10")){ //Saldos y Calendario de Préstamos
			valor = new Vector();
		
			valor.addElement("txtNumPtmo");
			valor.addElement(nroCuenta.concat(nroPrestamo));
			valores.add(valor);
		} 

		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement(codigoConsulta);
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
		/*t.loadVectors();
		Vector resultado = t.getMascVector();*/
		
		int anioItf = ObjectUtil.getYear();
		anioItf = anioItf - 1;
		
		//if(Constante.VER_LOG) System.out.println("Año de consulta ITF - anioItf:"+anioItf);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeDiferenciaSaldo",((Vector)aplicacion.getMensajePorCodigo("CL01","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeSaldoAhorro",((Vector)aplicacion.getMensajePorCodigo("CL01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeHorario",((Vector)aplicacion.getMensajePorCodigo("PP00","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCCI",((Vector)aplicacion.getMensajePorCodigo("CL01","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeItf",((Vector)aplicacion.getMensajePorCodigo("CL01","ITF01")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeTarjetaDeCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC003")).elementAt(2).toString());

		if(codigoConsulta.equals("01") || codigoConsulta.equals("14")){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.setMonedaProducto(codigoMoneda);
			cuentaImpl.getSaldoAhorros(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt = new GeneralTest();
			gt.setVariables(cuentaImpl.getVariables());
			gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_AHORROS,cuentaImpl, request);
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			return (Cuenta)cuentaImpl;
		} 
		else if(codigoConsulta.equals("02")||codigoConsulta.equals("15")){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.setMonedaProducto(codigoMoneda);
			cuentaImpl.setNumeroProducto(nroCuenta);
			cuentaImpl.getUltimosMovimientos(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_AHORROS,cuentaImpl,request); 
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			return (Cuenta)cuentaImpl;
		}
		else if(codigoConsulta.equals("03")||codigoConsulta.equals("16")){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.setNumeroProducto(nroCuenta);
			cuentaImpl.getConsultaCci(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_CCI,cuentaImpl, request);
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)cuentaImpl;			
		}
		else if(codigoConsulta.equals("04")){			
			ItfImpl itf = new ItfImpl(nroCuenta,anioItf+"01");
			itf.setNombre(usuario.getNombre());
			Map map = UtilAction.cargarVar(request,itf);
			OperacionImpl.setVariables(map);
			
			return (Itf)itf;			
		}
		else if(codigoConsulta.equals("05") || codigoConsulta.equals("18")){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.setMonedaProducto(codigoMoneda);
			cuentaImpl.getSaldoCtaCte(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_CTACTE,cuentaImpl,request);
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)cuentaImpl; 
		}
		else if(codigoConsulta.equals("06") || codigoConsulta.equals("21") ){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.getUltimosMovimientosCorriente(t,usuario, 1);
			
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_CORRIENTE,cuentaImpl,request);
			
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)cuentaImpl;
		}
		
		else if(codigoConsulta.equals("13") || codigoConsulta.equals("23") ){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.setNumeroProducto(nroCuenta);
			cuentaImpl.getUltimosMovimientosCTS(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_CTS,cuentaImpl,request);			
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)cuentaImpl;
		}
		
		else if(codigoConsulta.equals("07") || codigoConsulta.equals("22")  ){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.setNumeroProducto(nroCuenta);
			cuentaImpl.getConsultaCtaCteCci(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_CTACTECCI,cuentaImpl,request);			
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)cuentaImpl;			
		}
		else if(codigoConsulta.equals("08")){
			ItfImpl itf = new ItfImpl(nroCuenta,anioItf+"01");
			itf.setNombre(usuario.getNombre());
			Map map = UtilAction.cargarVar(request,itf);
			OperacionImpl.setVariables(map);
			
			return (Itf)itf;			
		}
		else if(codigoConsulta.equals("09")){
			PrestamoImpl prestamo = new PrestamoImpl();
			prestamo.getPrestamoActual(t,usuario);
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_PRESTAMO,prestamo,request);			
			if(prestamo.getArrayRuleException()!=null)
			    throw prestamo.getArrayRuleException();	
			
			Map map = UtilAction.cargarVar(request,prestamo);
			OperacionImpl.setVariables(map);
			
			return (Prestamo)prestamo;
		} 
		else if(codigoConsulta.equals("10")){
			PrestamoImpl prestamoImpl = new PrestamoImpl();
			prestamoImpl.getCalendarioPagos(t,usuario);
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_CALENDARIO_PAGO,prestamoImpl,request);			
			if(prestamoImpl.getArrayRuleException()!=null)
			    throw prestamoImpl.getArrayRuleException();	

			Map map = UtilAction.cargarVar(request,prestamoImpl);
			OperacionImpl.setVariables(map);
			
			return (Prestamo)prestamoImpl;
		}
		
		if(codigoConsulta.equals("17")||codigoConsulta.equals("12")){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.getSaldoCTS(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_CTS,cuentaImpl,request);				
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)cuentaImpl;
		}
		
		return null;
	
	}
	
	/*
	 *  (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getVerificaCta(String remoteAddress)
	 */

	public Object consultarCtaCte(CuentaImpl ctaImp, int nPagina, String codigoConsulta, String nroCuenta, String codigoMoneda,String DatProI,String DatProF,String CodTrx,String cNumDoc, String flgPagina,String remoteAddress, Usuario usuario, HttpServletRequest request) throws Exception{

//		System.out.println("consultarCtaCte>>>>>>>>>>>>>>>>>>>");
		String transaccion = "CL02"; 
		
		if (codigoMoneda.trim().equals("PEI")) {
			transaccion = "CL02";
		} else {
			transaccion = "CL03";
		}
	
		CuentaImpl cuentaImpl = new CuentaImpl();

		Transaction t = new Transaction(transaccion);
		
		ConstanteSesion.WAP_CTA_CTE = nroCuenta;
		
		// Concatenar VALORES
		ArrayList tmpValor = ctaImp.getFechaInicialAnt();
		
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(DatProI);
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
					tmpValor.add(DatProI);
				}
			}
		}
		ctaImp.setFechaInicialAnt(tmpValor);
		
		tmpValor = ctaImp.getFechaFinAnt();
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(DatProF);
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(DatProF);
				} 
			}
		}
		ctaImp.setFechaFinAnt(tmpValor);
		
	
		tmpValor = ctaImp.getChequeAnt();
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(cNumDoc);
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(cNumDoc);
				} 
			}
		}
		ctaImp.setChequeAnt(tmpValor);
		
		tmpValor = ctaImp.getTrxCtaCteAnt();
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(CodTrx);
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(CodTrx);
				} 
			}
		}
		ctaImp.setTrxCtaCteAnt(tmpValor);
		
		tmpValor = ctaImp.getCorroriAnt();
		if (ctaImp.getCorrOri() == null) {
			ctaImp.setCorrOri("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getCorrOri());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getCorrOri());
				} 
			}
		}
		ctaImp.setCorroriAnt(tmpValor);

		tmpValor = ctaImp.getTranoriAnt();
		if (ctaImp.getTranOri() == null) {
			ctaImp.setTranOri("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getTranOri());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getTranOri());
				} 
			}
		}
		ctaImp.setTranoriAnt(tmpValor);
		
	
		tmpValor = ctaImp.getCanaloriAnt();
		if (ctaImp.getCanalOri() == null) {
			ctaImp.setCanalOri("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getCanalOri());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getCanalOri());
				} 
			}
		}
		ctaImp.setCanaloriAnt(tmpValor);
		
		tmpValor = ctaImp.getCajerooriAnt();
		if (ctaImp.getCajeroOri() == null) {
			ctaImp.setCajeroOri("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getCajeroOri());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getCajeroOri());
				} 
			}
		}
		ctaImp.setCajerooriAnt(tmpValor);
		
		tmpValor = ctaImp.getJrnaoriAnt();
		if (ctaImp.getJrnaOri() == null) {
			ctaImp.setJrnaOri("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getJrnaOri());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getJrnaOri());
				} 	
			}
		}
		ctaImp.setJrnaoriAnt(tmpValor);

		tmpValor = ctaImp.getIdenarchivoAnt();
		if (ctaImp.getIdenArchivo() == null) {
			ctaImp.setIdenArchivo("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getIdenArchivo());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getIdenArchivo());
				} 
			}
		}
		ctaImp.setIdenarchivoAnt(tmpValor);
		
		tmpValor = ctaImp.getCheqoriAnt();
		if (ctaImp.getCheqOri() == null) {
			ctaImp.setCheqOri("");
		}
		if (tmpValor == null){
			tmpValor = new ArrayList();
			tmpValor.add(ctaImp.getCheqOri());
		} else{
			if (flgPagina.trim().equals("2")) {
				if (nPagina > tmpValor.size()){
				tmpValor.add(ctaImp.getCheqOri());
				} 
			}
		}
		ctaImp.setCheqoriAnt(tmpValor);
		/**
		System.out.println("FechaInicialAnt:"+ctaImp.getFechaInicialAnt());
		System.out.println("FechaFinAnt:"+ctaImp.getFechaFinAnt());
		System.out.println("ChequeAnt:"+ctaImp.getChequeAnt());
		System.out.println("TrxCtaCteAnt:"+ctaImp.getTrxCtaCteAnt());
		System.out.println("CorroriAnt:"+ctaImp.getCorroriAnt());
		System.out.println("TranoriAnt:"+ctaImp.getTranoriAnt());
		System.out.println("CanaloriAnt:"+ctaImp.getCanaloriAnt());
		System.out.println("CajerooriAnt:"+ctaImp.getCajerooriAnt());
		System.out.println("JrnaoriAnt:"+ctaImp.getJrnaoriAnt());
		System.out.println("IdenarchivoAnt:"+ctaImp.getIdenarchivoAnt());
		System.out.println("CheqoriAnt:"+ctaImp.getCheqoriAnt());
		**/
	
		Vector valores = new Vector();
		Vector valor = new Vector();
		//valor.addElement(flgPagina);
		

		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNumCta");
		valor.addElement(nroCuenta);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtFINI");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getFechaInicialAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(DatProI);
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("txtFFIN");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getFechaFinAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(DatProF);
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("txtTRAN");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getTrxCtaCteAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(CodTrx);
			valores.add(valor);
		}
	
		valor = new Vector();
		valor.addElement("txtCHEQ");	
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getChequeAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(cNumDoc);
			valores.add(valor);
		}		
		
		valor = new Vector();
		valor.addElement("txtCorrOri");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getCorroriAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getCorrOri());
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("txtTranOri");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getTranoriAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getTranOri());
			valores.add(valor);
		}
	
		valor = new Vector();
		valor.addElement("txtCanalOri");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getCanaloriAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getCanalOri());
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("txtCajeroOri");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getCajerooriAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getCajeroOri());
			valores.add(valor);
		}

		valor = new Vector();
		valor.addElement("txtJrnaOri");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getJrnaoriAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getJrnaOri());
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("txtArchivo");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getIdenarchivoAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getIdenArchivo());
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("txtCheqOri");
		if (flgPagina.trim().equals("1")) {
			valor.addElement(ctaImp.getCheqoriAnt().get(nPagina-1).toString());
			valores.add(valor);
		} else {
			valor.addElement(ctaImp.getCheqOri());
			valores.add(valor);
		}
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement(codigoConsulta);
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
		/*t.loadVectors();
		Vector resultado = t.getMascVector();*/
		
		int anioItf = ObjectUtil.getYear();
		anioItf = anioItf - 1;
		
		//if(Constante.VER_LOG) System.out.println("Año de consulta ITF - anioItf:"+anioItf);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeDiferenciaSaldo",((Vector)aplicacion.getMensajePorCodigo("CL01","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeSaldoAhorro",((Vector)aplicacion.getMensajePorCodigo("CL01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeHorario",((Vector)aplicacion.getMensajePorCodigo("PP00","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCCI",((Vector)aplicacion.getMensajePorCodigo("CL01","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeItf",((Vector)aplicacion.getMensajePorCodigo("CL01","ITF01")).elementAt(2).toString());

		if(codigoConsulta.equals("06") || codigoConsulta.equals("21") ){
			ctaImp.getUltimosMovimientosCorriente(t,usuario, nPagina);
			
			//@DPS
			if(ctaImp.getArrayRuleException()!=null){
				throw ctaImp.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_CORRIENTE,ctaImp,request);
			
			Map map = UtilAction.cargarVar(request,ctaImp);
			OperacionImpl.setVariables(map);
			
			return (Cuenta)ctaImp;
		}
		
		return null;
  
	}

	public void getVerificaCta(String remoteAddress, String codTipoTrx, String numeroCuenta, String numDocUsuario) throws Exception {

		String transaccion = "LG03"; 

		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		
		valor.addElement("validaNroCta");
		valor.addElement(numeroCuenta);
		valores.add(valor);
		
		valor = new Vector();
		
		valor.addElement("tipoConsulta");
		valor.addElement(codTipoTrx);
		valores.add(valor);
		
		valor = new Vector();
		
		valor.addElement("tipoDocumento");
		valor.addElement("01");
		valores.add(valor);
		
		valor = new Vector();
		
		valor.addElement("nroDocumento");
		valor.addElement(numDocUsuario);
		valores.add(valor);
		
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
		
		UsuarioImpl valida = new UsuarioImpl();
		valida.setCanal(Constante.CANAL_MOVIL);
		valida.ValidaCtaCte(t);
	}
	
	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getPrestamo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Prestamo getPrestamo(Usuario usuario, String remoteAddress, HttpServletRequest request) throws Exception {

		String transaccion = "CL01"; 

		Transaction t = new Transaction(transaccion);
		CuentaImpl cuenta = null;
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
		}
		
		if(cuenta==null)
			throw new ArrayRuleException("El cliente no posee una cuenta de ahorros en soles");
		
		Vector valores = new Vector();
		Vector valor = new Vector();
		// valor.addElement("transaccion");
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		// valor.addElement("hidCuenta");
		valor.addElement("txtNumCta");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("09");
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
		PrestamoImpl prestamo = new PrestamoImpl(); 
		prestamo.setCanal(Constante.CANAL_MOVIL);
		prestamo.getPrestamoActual(t,usuario);
		
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_PRESTAMO,prestamo,request);			
		if(prestamo.getArrayRuleException()!=null)
		    throw prestamo.getArrayRuleException();			
		return (Prestamo)ObjectUtil.formatearObjetoWAP(prestamo);
		
	}

	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getSaldoCuentaAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Cuenta getSaldoCuentaAhorro(Usuario usuario, String remoteAddress) throws Exception {
		
		CuentaImpl cuenta = null;
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
		}
		
		if(cuenta==null)
			throw new ArrayRuleException("El cliente no posee una cuenta de ahorros en soles");
		
		
		return (Cuenta)ObjectUtil.formatearObjetoWAP(cuenta);
	}

	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getNumeroCCI(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Cuenta getNumeroCCI(Usuario usuario, String remoteAddress, HttpServletRequest request) throws Exception {

		String transaccion = "CL01"; 

		Transaction t = new Transaction(transaccion);
		CuentaImpl cuenta = null;
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
		}
		
		// Para Validar que sea Soles en la consulta
		cuenta.setMonedaProducto("PEI");
		
		if(cuenta==null)
			throw new ArrayRuleException("El cliente no posee una cuenta de ahorros en soles");
		
		Vector valores = new Vector();
		Vector valor = new Vector();
		// valor.addElement("transaccion");
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		// valor.addElement("hidCuenta");
		valor.addElement("txtNumCta");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		ConstanteSesion.WAP_CTA_CTE =cuenta.getNumeroProducto();
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("03");
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
		cuenta.setCanal(Constante.CANAL_MOVIL);
		cuenta.getConsultaCci(t, usuario);
		//@DPS
		if(cuenta.getArrayRuleException()!=null){
			throw cuenta.getArrayRuleException();
		}
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_CCI,cuenta, request);		
		return (Cuenta)ObjectUtil.formatearObjetoWAP(cuenta);
	}

	
	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getSaldoCuentaCorriente(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Cuenta getSaldoCuentaCorriente(Usuario usuario,String nroCuenta,String remoteAddress, HttpServletRequest request) throws Exception{
		String transaccion = "CL01"; 
		//System.out.println("ENTRO A SALDOCUENTACORRIENTE WAP + "+ usuario);
		Transaction t = new Transaction(transaccion);

		ConstanteSesion.WAP_CTA_CTE = nroCuenta;
		
		Vector valores = new Vector();
		Vector valor = new Vector();
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);

		valor = new Vector();
		valor.addElement("txtNumCta");
		valor.addElement(nroCuenta);
		valores.add(valor);
		
		
		String codigoConsulta = "05";
		
		CuentaImpl cuenta = (CuentaImpl)usuario.getMapCuentas().get(nroCuenta);
		for(int j=0;j<usuario.getCuentas().size();j++){
			
			CuentaImpl cta= (CuentaImpl) usuario.getCuentas().get(j);
			//System.out.println("cuenta "+ j + " : "+cta.getNumeroProducto());
			if(cta.getEsCuentaCorriente() && cta.getNumeroProducto().equals(nroCuenta.trim())){
				cuenta=cta;
				//System.out.println("ENcontro la cuenta "+cta.getNumeroProducto());
				break;
			}
		}
		
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("2");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("0100");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("txtCtaCargo");
			valor.addElement(nroCuenta);
			valores.add(valor);
			
		}
		else{
			valor = new Vector();
			valor.addElement("txtTipoAfectacion");
			valor.addElement("4");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("txtNumTran");
			valor.addElement("6100");
			valores.add(valor);
			
			codigoConsulta = "18";
			
			valor = new Vector();
			valor.addElement("txtCtaCargo");
			valor.addElement(nroCuenta);
			valores.add(valor);			
		}

		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement(codigoConsulta);
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
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeDiferenciaSaldo",((Vector)aplicacion.getMensajePorCodigo("CL01","00002")).elementAt(2).toString());
		cuenta.getSaldoCtaCte(t,usuario);
		//@DPS
		if(cuenta.getArrayRuleException()!=null){
			throw cuenta.getArrayRuleException();
		}
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_CTACTE,cuenta,request);
		cuenta.setCanal(Constante.CANAL_MOVIL);
		return (Cuenta)ObjectUtil.formatearObjetoWAP(cuenta);
		
		
	}

	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getNumeroCCIAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Cuenta getNumeroCCI(String tipoCuenta, String nroDNI, String password,String nroCuenta, String remoteAddress) throws Exception {
		try{
			new BigDecimal(nroDNI);
		}
		catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de DNI debe ser numérico");
		}
		if(password==null||password.length()!=6){
			throw new ArrayRuleException(ConstanteError.GENERICO,"La clave de la tarjeta debe tener 6 dígitos");
		}
		if(nroCuenta==null||nroCuenta.length()==0){
			throw new ArrayRuleException(ConstanteError.GENERICO,"Ingrese el número de cuenta");
		}		
		CuentaImpl cuenta = new CuentaImpl();
		
		cuenta.setNroCuentaCci(nroCuenta);
		cuenta.setCanal(Constante.CANAL_MOVIL);
		return (Cuenta)ObjectUtil.formatearObjetoWAP(cuenta);
	}

	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getLimitePrestamo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LimitePrestamo getLimitePrestamo(String tipoTarjeta, String nrotarjeta, String password, String remoteAddress) throws Exception {
		/*
		try{
			new BigDecimal(nrotarjeta);
		}
		catch(Exception e){
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de tarjeta debe ser numérico");
		}
		if(password==null||password.length()!=4){
			throw new ArrayRuleException(ConstanteError.GENERICO,"La clave de la tarjeta debe tener 4 dígitos");
		}*/		
		LimitePrestamoImpl limite = new LimitePrestamoImpl("",null,null,null);
		return (LimitePrestamo)ObjectUtil.formatearObjetoWAP(limite);
	}
	
	 
	public TipoCambio getTipoCambio(String remoteAddress) throws Exception {

		String transaccion = "CL01"; 

		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		
		valor.addElement("txtNumCta");
		valor.addElement("000000000000000");
		valores.add(valor);
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("20");
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
		
		TipoCambioImpl tipoCambio = new TipoCambioImpl();
		tipoCambio.setCanal(Constante.CANAL_MOVIL);
		tipoCambio.getTipoCambio(t);
		return (TipoCambio)tipoCambio;
	}

	/* (sin Javadoc)
	 * @see pe.bn.consulta.facade.ConsultaWAPFacade#getCuentasCorrientes(pe.cosapi.domain.Usuario)
	 */
	public List getCuentasCorrientes(Usuario usuario) throws Exception {
		List cuentas = usuario.getCuentas();
		List cc = new ArrayList();
		for (Iterator iter = cuentas.iterator(); iter.hasNext();) {
			CuentaImpl cuenta = (CuentaImpl) iter.next();
			if(cuenta.getEsCuentaCorriente()){
				cc.add(cuenta);
			}			
		}
		return cc;
	}
	 
 
} 
