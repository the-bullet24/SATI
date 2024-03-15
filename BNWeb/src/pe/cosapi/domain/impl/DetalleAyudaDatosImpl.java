package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.util.List;


import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.DetalleAyudaDatos;

public class DetalleAyudaDatosImpl implements DetalleAyudaDatos, Serializable {
	
	private String 	codigoAyuda;
	private Long    numeroSequencial;
	private String 	descripcionDetalle;
	private String 	codigoDetalleAyuda;
	private String 	codigoDetalleAyuda1;
	
	public DetalleAyudaDatosImpl() {
		super();
	}
	
	public  List getListDetalleAyuda(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleAyuda(codAyuda);
	}
	
	public  List getListDetalleAyudaOrden(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleAyudaOrden(codAyuda);
	}
	
	
	public  List getListDetalleAyudaDiscado(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleAyudaDiscado(codAyuda);
	}
	
	public  List getListDetalleAyudaHijo(String codAyuda, String codHijo) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleAyudaHijo(codAyuda, codHijo);
	}
	
	public  List getListDetallePais() throws Exception{
		return DAOFactory.getGeneraDAO().getListDetallePais();
	}
	
	public  List getListDetalleDepartamento() throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleDepartamento();
	}
	
	public  List getListDetalleProvincia(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleProvincia(codAyuda);
	}
	public  List getListDetalleDistrito(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleDistrito(codAyuda);
	}
	
	public  List getListDetalleDepartamentoCore() throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleDepartamentoCore();
	}
	
	public  List getListDetalleProvinciaCore(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleProvinciaCore(codAyuda);
	}
	public  List getListDetalleDistritoCore(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleDistritoCore(codAyuda);
	}
	
	public  List getListDetalleLocalidad(String codAyuda) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleLocalidad(codAyuda);
	}
	
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#getCodigoAyuda()
	 */
	public String getCodigoAyuda() {
		return codigoAyuda;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#setCodigoAyuda(java.lang.String)
	 */
	public void setCodigoAyuda(String codigoAyuda) {
		this.codigoAyuda = codigoAyuda;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#getCodigoDetalleAyuda()
	 */
	public String getCodigoDetalleAyuda() {
		return codigoDetalleAyuda;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#setCodigoDetalleAyuda(java.lang.String)
	 */
	public void setCodigoDetalleAyuda(String codigoDetalleAyuda) {
		this.codigoDetalleAyuda = codigoDetalleAyuda;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#getDescripcionDetalle()
	 */
	public String getDescripcionDetalle() {
		return descripcionDetalle;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#setDescripcionDetalle(java.lang.String)
	 */
	public void setDescripcionDetalle(String descripcionDetalle) {
		this.descripcionDetalle = descripcionDetalle;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#getNumeroSequencial()
	 */
	public Long getNumeroSequencial() {
		return numeroSequencial;
	}

	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.DetalleAyudaDatos#setNumeroSequencial(java.lang.Long)
	 */
	public void setNumeroSequencial(Long numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}
	
	
	/**
	 * @return Returns the codigoDetalleAyuda1.
	 */
	public String getCodigoDetalleAyuda1() {
		return codigoDetalleAyuda1;
	}
	/**
	 * @param codigoDetalleAyuda1 The codigoDetalleAyuda1 to set.
	 */
	public void setCodigoDetalleAyuda1(String codigoDetalleAyuda1) {
		this.codigoDetalleAyuda1 = codigoDetalleAyuda1;
	}
	
	public  List getListDetalleConcepto(String codAyuda,String codHijo) throws Exception{
		return DAOFactory.getGeneraDAO().getListDetalleConcepto(codAyuda,codHijo);
	}
}
