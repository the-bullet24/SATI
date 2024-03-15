/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.facade;

import javax.servlet.http.HttpServletRequest;

import pe.bn.tarjeta.domain.Tarjeta;
import pe.cosapi.domain.Usuario;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface TarjetaFacade {
	
	public Tarjeta generarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr) throws Exception;
	
	public Tarjeta generarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr,HttpServletRequest request) throws Exception;
	
	public Tarjeta generarNuevaClaveOlvidoInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr,HttpServletRequest request) throws Exception;
	
	public Tarjeta generarNuevaClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr,HttpServletRequest request) throws Exception;
	
	public Tarjeta generarOlvidoClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr,HttpServletRequest request) throws Exception;
	
	public Tarjeta generarExpiraClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr,HttpServletRequest request) throws Exception;
		
	public Tarjeta cambiarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception;
	
	public Tarjeta desafiliarClaveInternet(Usuario usuario, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr, HttpServletRequest request) throws Exception;
	
	public Tarjeta desafiliarClaveSeis(String cNumTarjeta, String cClave, String transaccion, String claveTarjeta, String claveInternet, String claveInternet_, String remoteAddr) throws Exception;
	
	public Tarjeta bloquearTarjeta(Usuario usuario, String transaccion, String remoteAddr,HttpServletRequest request) throws Exception;
	
	public Tarjeta bloquearTarjetaDni(Usuario usuario, String transaccion, String remoteAddr,HttpServletRequest request) throws Exception;
	
	public Tarjeta generarClaveAfiliacion(Usuario usuario,  String claveVal, String claveVal_, String remoteAddr) throws Exception;
	
	/*MGL*/
	public Tarjeta getCambiarClaveInterna(String codTra, Usuario usuario, String claveAnterior, String claveNueva, HttpServletRequest request) throws Exception;
	
	public Tarjeta getCambiarClaveExterna(String codTra, Usuario usuario, String claveAnterior, String claveNueva, HttpServletRequest request) throws Exception;
	
}
