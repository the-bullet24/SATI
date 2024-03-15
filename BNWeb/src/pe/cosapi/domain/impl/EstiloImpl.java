/*
 * Creado el 07/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;

import pe.cosapi.domain.Estilo;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EstiloImpl implements Estilo, Serializable {
	private String id;
	private String background;
	private String title;
	private String subtitle;
	private String row_1;
	private String row_2;
	
	/**
	 * @return Devuelve background.
	 */
	public String getBackground() {
		return background;
	}
	/**
	 * @param background El background a establecer.
	 */
	public void setBackground(String background) {
		this.background = background;
	}
	/**
	 * @return Devuelve id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id El id a establecer.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Devuelve row_1.
	 */
	public String getRow_1() {
		return row_1;
	}
	/**
	 * @param row_1 El row_1 a establecer.
	 */
	public void setRow_1(String row_1) {
		this.row_1 = row_1;
	}
	/**
	 * @return Devuelve row_2.
	 */
	public String getRow_2() {
		return row_2;
	}
	/**
	 * @param row_2 El row_2 a establecer.
	 */
	public void setRow_2(String row_2) {
		this.row_2 = row_2;
	}
	/**
	 * @return Devuelve subtitle.
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle El subtitle a establecer.
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return Devuelve title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title El title a establecer.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
