/*
 * Creado el 14/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.telegiro.domain;

import java.io.Serializable;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.cosapi.domain.Agencia;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Operacion;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Telegiro extends Operacion, Serializable{
	
	public Afiliacion getAfiliacion();

	public Cuenta getCuenta();
	
	public Agencia getAgencia();
	
	public String getNroGiro();
	
	public String getClaveGiro();
}
