/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.login.facade.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.consulta.domain.Prestamo;
import pe.bn.login.domain.Menu;
import pe.bn.login.domain.impl.MenuImpl;
import pe.bn.login.facade.LoginFacade;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class LoginFacadeImpl implements LoginFacade {
	//Logger log = Logger.getLogger(LoginFacadeImpl.class);
	/* (sin Javadoc)
	 * @see pe.bn.login.facade.LoginFacade#autenticar(java.lang.String, java.util.Vector, java.util.Vector)
	 */
	public Usuario autenticar(String codTransaccion, String codCompany, Vector valores, Vector cuentas, String tipoDoc, String numDoc) throws Exception {
//	    System.out.println("codTransaccion : " + codTransaccion);
//	    System.out.println("valores : " + valores);
//	    System.out.println("cuentas : " + cuentas);
		
		Transaction t;
		UsuarioImpl usuario;
		if(codTransaccion.substring(0,4).equals("LG06")){
			t = new Transaction(codTransaccion.substring(0, 4));
		    String tipoPersona= (valores.elementAt(1).toString());
			usuario = new UsuarioImpl(codTransaccion,codCompany,valores,cuentas,t);
			usuario.setTipoDocumento(tipoDoc);
			usuario.setNumDocumento(numDoc);
			
		}else{
			t = new Transaction(codTransaccion);
		    String tipoPersona= (valores.elementAt(1).toString());
			usuario = new UsuarioImpl(codTransaccion,codCompany,valores,cuentas,t);
			usuario.setTipoDocumento(tipoDoc);
			usuario.setNumDocumento(numDoc);
		}

		GeneralTest gt = new GeneralTest();
		gt.generarLog(t,usuario,null,usuario);
		if(usuario.getArrayRuleException()!=null)
			throw usuario.getArrayRuleException();		
		return (Usuario)usuario;
	}
	
	public Usuario autenticarDni(String codTransaccion, String codCompany, Vector valores) throws Exception {
	    Transaction t = new Transaction(codTransaccion);
		UsuarioImpl usuario = new UsuarioImpl(codTransaccion,codCompany,valores,t);
		//GeneralTest gt = new GeneralTest();
		//gt.generarLog(t,usuario,null,usuario);
		if(usuario.getArrayRuleException()!=null)
			throw usuario.getArrayRuleException();		
		return (Usuario)usuario;
	}
	
	public Usuario autenticarAfilInternet(String codTransaccion, Vector valores, Vector cuentas) throws Exception {
	 
	    Transaction t = new Transaction(codTransaccion);
	    String tipoPersona= (valores.elementAt(1).toString());
	    
		UsuarioImpl autenticar = new UsuarioImpl();
			
	
		return (Usuario)autenticar.autenticarAfilInternet(codTransaccion, valores, cuentas, t);
	}
	
	
	public String validar(String transaccion,String cuenta,String tipoDoc,String numDoc,String fecha,Usuario usuario,HttpServletRequest request) throws Exception {
		
		Transaction t = new Transaction(transaccion);
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
		
		
		
		val = new Vector();
		val.addElement("numCuenta");
		val.addElement(cuenta);
		valores.addElement(val);	
		
		val = new Vector();
		val.addElement("fechaNac");
		val.addElement(fecha);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("tipoDoc");
		val.addElement(tipoDoc);
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("numDoc");
		val.addElement(numDoc);
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
		ctas.addElement(v);
		t.setValues(valores);
		t.setCuentas(ctas);	
		UsuarioImpl validarDatosPersonales = new UsuarioImpl();
		
		return validarDatosPersonales.getValidarDatosPersonales(t,valores,ctas,usuario);
		
		
		
		
	}
	public Menu[] getMenuByCode(String codigoPersona,String codigoTarjeta) throws Exception {
		MenuImpl menu = new MenuImpl();
		Menu[] arrMenu = menu.getMenuByCode(codigoPersona,codigoTarjeta);
		if (arrMenu.length==0)
			return null;
		
		return arrMenu;
	}
	
	public Menu[] getMenuByCode1(String codigoPersona,String codigoTarjeta, String ip) throws Exception {
		MenuImpl menu = new MenuImpl();
		Menu[] arrMenu = menu.getMenuByCode1(codigoPersona,codigoTarjeta,ip);
		if (arrMenu.length==0)
			return null;
		
		return arrMenu;
	}
	
	
	public Menu[] getMenuByCodeOtros(String codigoPersona,String codigoTarjeta) throws Exception {
		MenuImpl menu = new MenuImpl();
		Menu[] arrMenu = menu.getMenuByCodeOtros(codigoPersona,codigoTarjeta);
		if (arrMenu.length==0)
			return null;
		
		return arrMenu;
	}
	
	public List getValidaImagenMV(String numTarMV) throws Exception {
	    return DAOFactory.getLoginImpl().getValidaImagen(numTarMV);
	}
	
	public void getAfiliaImagenMV(String cNumTar, String numImagenMV, String cNumSec, String cCodCat, String ip, String estado) throws Exception {
		DAOFactory.getLoginImpl().getAfiliaImagen(cNumTar, numImagenMV, cNumSec, cCodCat, ip, estado);
	}
	
	public void getIntentosImagenMV(String cNumTar, int numIntDia, String ip) throws Exception {
		DAOFactory.getLoginImpl().getIntentosImagen(cNumTar, numIntDia,ip);
	}
	public void getCambioEstado(String numeroSec, String estado) throws Exception {
		DAOFactory.getLoginImpl().getCambioEstado(numeroSec, estado);
	}
		
	
	public void getUltimoIngresoMV(String cNumTar,String ip) throws Exception {
		DAOFactory.getLoginImpl().getUltimoIngreso(cNumTar,ip);
	}
	
	public String getNextSec() throws Exception {
		return DAOFactory.getLoginImpl().getNextSec();
	}
	
	public String getNextSecIngreso() throws Exception {
		return DAOFactory.getLoginImpl().getNextSecFech();
	}
	
	public List getValidaExiste  (String numTarMV) throws Exception {
	    return DAOFactory.getLoginImpl().getValidaExiste(numTarMV);
	}
	
	public void getFechaIngreso  (String cNumTar, String cNumSec, String ip) throws Exception {
	  DAOFactory.getLoginImpl().getFechaIngreso(cNumTar,cNumSec,ip);
	}
	
	public void getUltimoIngresoFecha  (String cNumTar,String ip) throws Exception {
	    DAOFactory.getLoginImpl().getUltimoIngresoFecha(cNumTar,ip);
	}
	
	
}
