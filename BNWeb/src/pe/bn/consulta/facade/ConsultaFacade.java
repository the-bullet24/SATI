/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.facade;

import javax.servlet.http.HttpServletRequest;

import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface ConsultaFacade extends ConsultaWAPFacade{
	
	//public Prestamo getPrestamo(String transaccion, String nroCuenta, String remoteAddress) throws Exception;
	
	//public Prestamo getCalendarioPrestamo(String transaccion, String nroCuenta, String remoteAddress) throws Exception;
	
	public Object consultar(String codigoConsulta,String nroCuenta, String codigoMoneda, String nroPrestamo, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	public Object consultarCtaCte(CuentaImpl ctaImp,int nPagina, String codigoConsulta, String nroCuenta, String codigoMoneda,String DatProI,String DatProF,String CodTrx,String cNumDoc, String flgPagina, String remoteAddress, Usuario usuario, HttpServletRequest request) throws Exception;
	public void getVerificaCta(String remoteAddress, String codTipoTrx, String numeroCuenta, String numDocUsuario) throws Exception;
}