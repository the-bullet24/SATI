/*

 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.facade;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.consulta.dao.impl.MontoImpl;
import pe.bn.consulta.domain.Monto;
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
public interface SeleccionMontoFacade extends Serializable {

	
	public Monto guardarMontoMaximo(String transaccion, Monto monto, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
}