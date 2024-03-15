/*
 * Creado el 19/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.dao;

import java.math.BigDecimal;
import java.util.List;

import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.cosapi.domain.Cuenta;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoDAO {
	public abstract BigDecimal getImporteTasa(PagoTasasImpl pago) throws Exception;
	public abstract List getListaImportesTasa(String codTributo, Cuenta cuenta) throws Exception;
	public abstract List getListaImportesTasaDetalle(String codTributo,String detalle) throws Exception;
	public abstract List getListaDistritos(String codTributo) throws Exception;
	public abstract List getListaDistritosJudiciales() throws Exception;
	public abstract List getListaDependencias(String distrito) throws Exception;	
	public abstract List getListaImporteTasaEducativa(String codTributo) throws Exception;
}