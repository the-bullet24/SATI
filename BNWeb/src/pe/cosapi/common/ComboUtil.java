package pe.cosapi.common;

import java.io.Serializable;


public class ComboUtil implements Serializable{
	private String codigo;
	private String descripcion;
	
	public ComboUtil(){
		
	}
	
	public ComboUtil(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * @return Devuelve codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo El codigo a establecer.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Devuelve descripcion.
	 */
	public String getDescripcion() {
		
		//String nomData = HTMLEncode(descripcion);
		//return nomData;
		return descripcion;
	}
	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
