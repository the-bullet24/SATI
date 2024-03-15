/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tcredito.facade.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


import pe.bn.consulta.domain.Prestamo;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.login.domain.Menu;
import pe.bn.login.domain.impl.MenuImpl;
import pe.bn.login.facade.LoginFacade;
import pe.bn.tcredito.domain.Formato;
import pe.bn.tcredito.domain.Pago;
import pe.bn.tcredito.domain.impl.PagoImpl;
import pe.bn.tcredito.facade.TarjetaCreditoFacade;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Itf;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.ItfImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;


/**
 * @author Mily Dolores B.
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TarjetaCreditoFacadeImpl implements TarjetaCreditoFacade {

	

	
	public void encontrarFormato2  (final String numDocumento,final String periodo, final OutputStream contentStream) throws Exception {
		    DAOFactory.getTarjetaCreditoDAO().encontrarFormato2(numDocumento,periodo,contentStream);
		}
	
	public List buscarEstadoCuenta(String numDocumento) throws Exception{
		
		 	return DAOFactory.getTarjetaCreditoDAO().buscarEstadoCuenta(numDocumento);
	}
	
	public void encontrarEstadoFtp  ( final String usuario,final String clave,final String servidor,final String remotePath, final OutputStream contentStream) throws Exception {
	    DAOFactory.getTarjetaCreditoDAO().encontrarEstadoFtp(usuario,clave,servidor,remotePath,contentStream);
	}
	
	public CuentaImpl consultar(String transaccion,String tipoConsulta,String nroTarjeta, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception{

		
		Transaction t = new Transaction(transaccion);
		
		Vector valores = new Vector();
		Vector valor = new Vector();
	
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtAccion");
		valor.addElement(tipoConsulta);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNumTarjeta");
		valor.addElement(nroTarjeta);
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
		
		
	
		BNAplicacion aplicacion = BNAplicacion.getInstance();

		
		request.getSession().setAttribute("mensajeTarjetaCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC005")).elementAt(2).toString());
		request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeDiferenciaSaldo",((Vector)aplicacion.getMensajePorCodigo("CL01","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeSaldoAhorro",((Vector)aplicacion.getMensajePorCodigo("CL01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeHorario",((Vector)aplicacion.getMensajePorCodigo("PP00","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCCI",((Vector)aplicacion.getMensajePorCodigo("CL01","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeItf",((Vector)aplicacion.getMensajePorCodigo("CL01","ITF01")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeTarjetaDeCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC003")).elementAt(2).toString());
		request.setAttribute("mensajeClaveDinamica",((Vector)aplicacion.getMensajePorCodigo("CL01","CD001")).elementAt(2).toString());
		if(transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_SALDO) ){
			CuentaImpl cuentaImpl = new CuentaImpl();
			cuentaImpl.getSaldoTCredito(t,usuario);
			//@DPS
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt = new GeneralTest();
			gt.setVariables(cuentaImpl.getVariables());
			gt.generarLog2(t,usuario,Constante.REFRENDO_SALDO_TARJETA_CREDITO,cuentaImpl, request);
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			return cuentaImpl;
		} 
		else if(transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_MOV) ){
			CuentaImpl cuentaImpl = new CuentaImpl();
			
			cuentaImpl.getUltimosMovimientosTCredito(t,usuario);
			
			if(cuentaImpl.getArrayRuleException()!=null){
				throw cuentaImpl.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			gt.generarLog2(t,usuario,Constante.REFRENDO_MOVIM_TARJETA_CREDITO,cuentaImpl,request); 
			Map map = UtilAction.cargarVar(request,cuentaImpl);
			OperacionImpl.setVariables(map);
			return cuentaImpl;
		}

		return null;
	
	}

	public Pago pagarTCredito(String transaccion,String tipoConsulta,PagoImpl pago, String remoteAddress, Usuario usuario, String tipo,HttpServletRequest request) throws Exception{
	
		Transaction t = new Transaction(transaccion);
		
		Vector valores = new Vector();
		Vector valor = new Vector();
	
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNroCuenta");
		valor.addElement(pago.getCuentaCargada().getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtMonto");
		valor.addElement(ObjectUtil.formatearMontoTrama(pago.getMontoPagado()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtAccion");
		valor.addElement(tipoConsulta);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNroTarCre");
		valor.addElement(pago.getCuentaCredito().getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNroTarDeb");
		valor.addElement(pago.getNroCuentaAhorros());
		valores.add(valor);
						
		
		int result;
		BigDecimal importeMinFact = new BigDecimal(pago.getDatos().getMontoPagoMinimo().replace(",", ""));
		BigDecimal importePagado = new BigDecimal(pago.getMontoPagado().replace(",", ""));
		
	
		result = importeMinFact.compareTo(importePagado);
		if (result == -1){
		
			valor = new Vector();
			valor.addElement("txtFlagRP");
			valor.addElement(Constante.TARJETA_CREDITO_CONST_REDUC_PLAZO);
			valores.add(valor);
		}
		
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
			
		BNAplicacion aplicacion = BNAplicacion.getInstance();
			
			pago.pagarTCredito(t,usuario);

			pago.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
			pago.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
			pago.setTarjetaFormateada(ObjectUtil.ocultarTarjetaCredito(pago.getCuentaCredito().getNumeroProducto(),Constante.FORMATO_TARJETA));
			if(pago.getArrayRuleException()!=null){
				throw pago.getArrayRuleException();
			}
			GeneralTest gt= new GeneralTest();
			if(tipo.equals(Constante.TC_TIPO_SERVICIO_TITULAR)){
				gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TARJETA_CREDITO_BN,pago,request); 
			}else{
				gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TARJETA_CREDITO_BN_OTROS,pago,request); 
			}
			
			
			Map map = UtilAction.cargarVar(request,pago);
			OperacionImpl.setVariables(map);
			return (Pago)pago;
		
	
	
	}
}
