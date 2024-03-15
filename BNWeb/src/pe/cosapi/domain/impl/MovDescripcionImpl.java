/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import pe.cosapi.domain.MovDescripcion;
import pe.cosapi.domain.Movimiento;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MovDescripcionImpl implements MovDescripcion, Serializable {

	String codigo;
	String descripcion;
	public String getCodigo() {
		// TODO Apendice de metodo generado automaticamente
		return this.codigo.trim();
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.domain.MovDescripcion#getDescripcion()
	 */
	public String getDescripcion() {
		// TODO Apendice de metodo generado automaticamente
		return this.descripcion.trim();
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.domain.MovDescripcion#setCodigo(java.lang.String)
	 */
	public void setCodigo(String codigo) {
		// TODO Apendice de metodo generado automaticamente
		this.codigo=codigo;
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.domain.MovDescripcion#setDescripcion(java.lang.String)
	 */
	public void setDescripcion(String descripcion) {
		// TODO Apendice de metodo generado automaticamente
		this.descripcion=descripcion;
	}
	
}
