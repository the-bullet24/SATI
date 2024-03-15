/*
 * Creado el 14/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.telegiro.facade;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.telegiro.domain.Telegiro;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
import pe.cosapi.domain.Agencia;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface TelegiroFacade {

	public Telegiro getTelegiro(String transaccion,Usuario usuario, Cuenta cuenta, Afiliacion afiliacion, String importe ,Agencia agencia, String remoteAddress) throws Exception;

	public Telegiro pagarTelegiro(String transaccion, TelegiroImpl telegiro, String remoteAddress, Usuario usuario, HttpServletRequest request) throws Exception;
}
