/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tcredito.facade;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.login.domain.Menu;
import pe.bn.tcredito.domain.Formato;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.bn.tcredito.domain.Pago;
import pe.bn.tcredito.domain.impl.PagoImpl;

/**
 * @author Mily Dolores B.
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface TarjetaCreditoFacade extends Serializable {

	public  void encontrarFormato2(final String numDocumento,final String periodo, final OutputStream contentStream) throws Exception;
	
	public List buscarEstadoCuenta(String numDocumento) throws Exception;
	
	public  void encontrarEstadoFtp(final String usuario,final String clave,final String servidor,final String remotePath, final OutputStream contentStream) throws Exception;
	public CuentaImpl consultar(String transaccion,String tipoConsulta,String nroTarjeta, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;

	public Pago pagarTCredito(String transaccion,String tipoConsulta,PagoImpl pago, String remoteAddress, Usuario usuario,String tipo,HttpServletRequest request) throws Exception;
}