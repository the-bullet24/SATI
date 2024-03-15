
package pe.bn.consulta.facade.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.util.FileCopyUtils;



import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.consulta.domain.Prestamo;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.consulta.facade.ConsultaFacade;
import pe.bn.consulta.facade.PrestamoFacade;
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



public class PrestamoFacadeImpl implements PrestamoFacade {
	private static LoggerSati log3 = LoggerSati.getInstance(PrestamoFacadeImpl.class.getName());
  
	public PrestamoImpl simularPrestamo(PrestamoImpl prestamo, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception{

		String transaccion = "PM02"; 

		Transaction t = new Transaction(transaccion);
		
		Vector valores = new Vector();
		Vector valor = new Vector();
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoOperacion");
		valor.addElement(prestamo.getTipoOperacion());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoPrestamo");
		valor.addElement(prestamo.getTipoPrestamo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(prestamo.getCuenta());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoEnvio");
		valor.addElement(prestamo.getTipoEnvio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("monto");
		
		valor.addElement(prestamo.getMontoSolicitado().concat("00"));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuotas");
		valor.addElement(prestamo.getNroCuotas());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroTarjeta");
		valor.addElement(prestamo.getTarjeta());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("periodoGracia");
		valor.addElement(prestamo.getFlagPeriodoGracia());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cuotaProt");
		valor.addElement(prestamo.getFlagCuotaProtegida());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("endoso");
		valor.addElement(prestamo.getFlagEndoso());
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
		
	
		PrestamoImpl prestamoImpl = new PrestamoImpl();
		prestamoImpl.setNroCuotasDes(prestamo.getNroCuotas().concat(Constante.PRESTAMO_CONST_DES_PLAZO));
		prestamoImpl.setFlagCuotaProtegida(prestamo.getFlagCuotaProtegida());
		prestamoImpl.setFlagPeriodoGracia(prestamo.getFlagPeriodoGracia());
		prestamoImpl.setFlagEndoso(prestamo.getFlagEndoso());
		prestamoImpl.setTipoOperacion(prestamo.getTipoOperacion());
		prestamoImpl.setTipoPrestamo(prestamo.getTipoPrestamo());
		prestamoImpl.setNroCuotas(prestamo.getNroCuotas());
		prestamoImpl.setTipoEnvio(prestamo.getTipoEnvio());
		prestamoImpl.setTarjeta(prestamo.getTarjeta());
		prestamoImpl.setMontoPrestamo(prestamo.getMontoSolicitado());
		prestamoImpl.setMontoSolicitado(prestamo.getMontoSolicitado());
		prestamoImpl.setCuenta(prestamo.getCuenta());
		//t.setObjeto(prestamoImpl);
		prestamoImpl.simularPrestamo(t, usuario);
	
		if(prestamoImpl.getArrayRuleException()!=null){
			throw prestamoImpl.getArrayRuleException();
		}
		prestamoImpl.setDiaPago(prestamoImpl.getDiaPago().concat(Constante.PRESTAMO_CONST_DES_DIA_PAGO));
			
		if(prestamo.getFlagPeriodoGracia().equals("S")){
			prestamoImpl.setDesPeriodoGracia("Si");
		}else{
			prestamoImpl.setDesPeriodoGracia("No");
		}
	
		return (PrestamoImpl)prestamoImpl;
	
	}
	
	public PrestamoImpl confirmarPrestamo(PrestamoImpl prestamo,  String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception{

		String transaccion = "PM03"; 

		Transaction t = new Transaction(transaccion);
								
		Vector valores = new Vector();
		Vector valor = new Vector();
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cuenta");
		valor.addElement(prestamo.getCuenta());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoPrestamo");
		valor.addElement(prestamo.getTipoPrestamo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("monto");
		valor.addElement(prestamo.getMontoSolicitado().concat("00"));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoEnvio1");
		valor.addElement(prestamo.getTipoEnvio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoEnvio2");
		valor.addElement(prestamo.getTipoEnvio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuotas");
		valor.addElement(prestamo.getNroCuotas());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoTransaccion");
		valor.addElement("3012");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cargo");
		valor.addElement("7");
		valores.add(valor);
		
		
		valores.add(valor);
		valor = new Vector();
		valor.addElement("tipoOperacion");
		valor.addElement("1");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroTarjeta");
		valor.addElement(prestamo.getTarjeta());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("periodoGracia");
		valor.addElement(prestamo.getFlagPeriodoGracia());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cuotaProt");
		valor.addElement(prestamo.getFlagCuotaProtegida());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("endoso");
		valor.addElement(prestamo.getFlagEndoso());
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
	
		PrestamoImpl prestamoImpl = new PrestamoImpl();
		
		prestamoImpl.setNroCuotasDes(prestamo.getNroCuotas().concat(Constante.PRESTAMO_CONST_DES_PLAZO));
		prestamoImpl.setMontoPrestamo(prestamo.getMontoPrestamo());
		prestamoImpl.setSaldoNeto(prestamo.getSaldoNeto());
		prestamoImpl.setMontoCuota(prestamo.getMontoCuota());
		prestamoImpl.setSaldoDeuda(prestamo.getSaldoDeuda());
		prestamoImpl.setMontoCuotaProtegida(prestamo.getMontoCuotaProtegida());
		prestamoImpl.setSegDesgravamen(prestamo.getSegDesgravamen());
		prestamoImpl.setNroCuotas(prestamo.getNroCuotas());
		prestamoImpl.setFechaVenCuota(prestamo.getFechaVenCuota());
		prestamoImpl.setFechaVenPrestamo(prestamo.getFechaVenPrestamo());
		prestamoImpl.setTasa(prestamo.getTasa());
		prestamoImpl.setTcea(prestamo.getTcea());
		prestamoImpl.setCuenta(prestamo.getCuenta());
		prestamoImpl.setDiaPago(prestamo.getDiaPago());
		prestamoImpl.setCuentaFormateada(ObjectUtil.formatearCuenta(prestamoImpl.getCuenta(),Constante.FORMATO_CUENTA));
		if(prestamo.getFlagPeriodoGracia().equals("S")){
			prestamoImpl.setDesPeriodoGracia("Si");
		}else{
			prestamoImpl.setDesPeriodoGracia("No");
		}
		
		prestamoImpl.confimarPrestamo(t, usuario);
		if(prestamoImpl.getArrayRuleException()!=null){
			throw prestamoImpl.getArrayRuleException();
		}
	
				
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_PRESTAMO_RENOVACION,prestamoImpl,request);			
	
		return prestamoImpl;
	
	}
	
	public void descargarDocumento( final InputStream documentoStream,final OutputStream contentStream) throws Exception {
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1024];
		
		int n = 0;
		while((n= documentoStream.read(buf))>=0){
			baos.write(buf,0,n);
		}
		documentoStream.close();

		//Enviar el OuputStream
		byte[] bytes = baos.toByteArray();
		FileCopyUtils.copy(bytes,contentStream);	
		contentStream.flush();
		contentStream.close();					
			
	
		}
	
 
} 
