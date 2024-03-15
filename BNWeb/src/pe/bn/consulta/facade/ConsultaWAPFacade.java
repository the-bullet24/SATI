/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pe.bn.consulta.domain.Prestamo;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.LimitePrestamo;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface ConsultaWAPFacade {
	public Prestamo getPrestamo(Usuario usuario,String remoteAddress, HttpServletRequest req) throws Exception;
	public Cuenta getSaldoCuentaAhorro(Usuario usuario,String remoteAddress) throws Exception;
	public Cuenta getNumeroCCI(Usuario usuario,String remoteAddress,HttpServletRequest req) throws Exception;
	public List getCuentasCorrientes(Usuario usuario) throws Exception;
	public Cuenta getSaldoCuentaCorriente(Usuario usuario,String nroCuenta,String remoteAddress,HttpServletRequest req) throws Exception;
	public Cuenta getNumeroCCI(String tipoCuenta,String nroDNI,String password,String nroCuenta,String remoteAddress) throws Exception;
	public LimitePrestamo getLimitePrestamo(String tipoTarjeta,String nrotarjeta,String password,String remoteAddress) throws Exception;
	public TipoCambio getTipoCambio(String remoteAddress) throws Exception;
}
