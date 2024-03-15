/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.transferencia.facade;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface TransferenciaFacade {
	
	public Transferencia getTransferencia(String transaccion, Cuenta cuenta, Afiliacion afiliacion, Usuario usuario, String moneda, String monto,String ip) throws Exception;
	
	public Transferencia pagarTransferencia(String transaccion ,TransferenciaImpl transferencia, Usuario usuario ,String remoteAddress,HttpServletRequest request) throws Exception;
	
	public void getTitularTransferencia(String transaccion, Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception;
	
	public Transferencia getTitularTransferenciaIB(String cboMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception;
	
	public void getTransferenciaCtaUob(String transaccion,Afiliacion afiliacion, String remoteAddress) throws Exception;
	
	public Transferencia transferirInterBanco(String moneda, Transferencia transferencia, Usuario usuario ,String remoteAddress, HttpServletRequest request)throws Exception;
	
	public Transferencia transferirInterBancoLinea(String moneda, Transferencia transferencia, Usuario usuario ,String remoteAddress, HttpServletRequest request)throws Exception;
	
	public Transferencia transferirInterBancoCelLinea(String moneda, Transferencia transferencia, Usuario usuario ,String remoteAddress, HttpServletRequest request)throws Exception;
	
	public Transferencia getOtroTitularTransferenciaIB(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception;
	
	public Transferencia getConsultaTransferenciaIBLinea(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario , String cciOrigen, String remoteAddress) throws Exception;
	
	public Transferencia getConsultaTransferenciaCelIBLinea(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String cciOrigen,String entidadCCE, String remoteAddress) throws Exception;

	//Metodos para transferencias inmediatas (Interoperabilidad)
	
	public Transferencia consultaBarridoIB(String numCelular, Usuario usuario ,String remoteAddress) throws Exception;
	public Transferencia consultaClienteIB(String cic, Usuario usuario ,String remoteAddress) throws Exception;
	public Transferencia registrarContactoIB(Cuenta cuenta, Usuario usuario ,String remoteAddress) throws Exception;
	

} 