/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.login.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.login.domain.Menu;
import pe.cosapi.domain.Usuario;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface LoginFacade extends Serializable {
	public Usuario autenticarDni(String codTransaccion, String codigoComp , Vector valores) throws Exception;
	public Usuario autenticar(String codTransaccion, String codigoComp , Vector valores, Vector cuentas, String tipoDoc, String numDoc) throws Exception;
	public String validar(String transaccion,String cuenta,String tipoDoc,String numDoc,String fecha,Usuario usuario,HttpServletRequest request) throws Exception ;
	public Menu[] getMenuByCode(String codigoPersona,String codigoTarjeta) throws Exception ;
	public Menu[] getMenuByCode1(String codigoPersona,String codigoTarjeta,String ip) throws Exception ;
	public Menu[] getMenuByCodeOtros(String codigoPersona,String codigoTarjeta) throws Exception ;
	public List getValidaImagenMV(String numTarMV) throws Exception ;
	public void getAfiliaImagenMV(String cNumTar, String numImagenMV, String cNumSec, String cCodCat,String ip,String estado) throws Exception ;
	public void getIntentosImagenMV(String cNumTar, int numIntDia, String ip) throws Exception ;
	public void getUltimoIngresoMV(String cNumTar,String ip) throws Exception ;
	public void getCambioEstado(String numeroSec, String estado) throws Exception ;
	public List getValidaExiste  (String numTarMV) throws Exception ;
	public void getFechaIngreso(String cNumTar, String cNumSec, String ip) throws Exception ;
	public void getUltimoIngresoFecha(String cNumTar,String ip) throws Exception ;
	
	public String getNextSec() throws Exception ;
	public String getNextSecIngreso() throws Exception ;
	
	public Usuario autenticarAfilInternet(String codTransaccion, Vector valores, Vector cuentas) throws Exception;
}