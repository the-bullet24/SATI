/*
 * Creado el 22/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao;

import java.sql.Timestamp;
import java.util.List;

import com.cosapisoft.sarawebbranch.server.GeneralParameters;

import pe.cosapi.common.ComboUtil;
import pe.cosapi.domain.Banner;
import pe.cosapi.domain.Branch;
import pe.cosapi.domain.MasterTransaction;
import pe.cosapi.domain.MsgComunication;
import pe.cosapi.domain.impl.LimitImpl;
import pe.cosapi.domain.impl.MasterLimitsImpl;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface GeneralDAO {
	public abstract List getEsquema() throws Exception;

	public abstract List getDiccionario() throws Exception;

	public abstract List getControlTransaccion() throws Exception;

	public abstract List getParametro() throws Exception;
	
	public abstract GeneralParameters getClaves() throws Exception;

	public abstract List getMensajeria() throws Exception;
	
	public abstract List getMensajeriaApp() throws Exception;
	
	public abstract List getMensajesHost() throws Exception;
	
	public abstract String getDescripcionLocalidad(String codAyuda, String codCiudad) throws Exception;

	public abstract MsgComunication[] getMsgComunication(String trx)
			throws Exception;

	public abstract Banner getBannerByDate(String tipopersona, Timestamp fecha)
			throws Exception;

	public abstract Branch getBranch(String codbra) throws Exception;

	public abstract List getEstilo() throws Exception;

	public abstract MasterTransaction getTransactionById(String codTrx)
			throws Exception;

	public abstract MasterLimitsImpl getLimitsById(String tipPersona,
			String codTrx) throws Exception;

	public abstract List getListDetalleAyuda(String codAyuda) throws Exception;
	
	public abstract List getListDetalleAyudaOrden(String codAyuda) throws Exception;
	
	public abstract List getListDetallePais() throws Exception;
	
	public abstract List getListDetalleDepartamento() throws Exception;
	public abstract List getListDetalleProvincia(String codAyuda) throws Exception;
	public abstract List getListDetalleDistrito(String codAyuda) throws Exception;
	
	public abstract List getListDetalleDepartamentoCore() throws Exception;
	public abstract List getListDetalleProvinciaCore(String codAyuda) throws Exception;
	public abstract List getListDetalleDistritoCore(String codAyuda) throws Exception;
	
	public abstract String getDescripcionUbigeo(String codAyuda) throws Exception;
	
	public abstract List getListDetalleLocalidad(String codAyuda) throws Exception;
	
	public abstract List getListDetalleAyudaDiscado(String codAyuda) throws Exception;
	
	public abstract List getListDetalleAyudaHijo(String codAyuda, String codHijo) throws Exception;

	public abstract List getDepartamentos() throws Exception;
	
	public abstract List getAgencias(String codigoDepartamento)
			throws Exception;

	public abstract List getComboDetalleHlp(String codAyuda) throws Exception ;
	
	public abstract List getComboDetalleHlpOrden(String codAyuda) throws Exception ;
	
	public abstract List getComboDetHlp(String codAyuda) throws Exception ;
	
	public abstract List getComboDetHlpOrden(String codAyuda) throws Exception ;

	public abstract List getComboDetalleHlpHijo(String codAyuda, String codHijo);
	
	public abstract List getComboDetalleHlpHijoCodhlp(String codAyuda, String codHijo);
	
	public abstract List getComboDetalleHlpHijoMod(String codAyuda, String codHijo);
	
	public abstract List getFlagTransaccion(String codAyuda) throws Exception ;
	
	public abstract List getJournalDiccionario();

	public abstract List getJournalDiccionario(String transaccion);
	
	public abstract List getComboAyudaHijo(String codAyuda) throws Exception;
	
	public abstract List getComboAyudaHijoOrden(String codAyuda) throws Exception;
	
	public abstract List getComboDetHlpTasa(String codAyuda) throws Exception ;
	
	public abstract boolean getEstadoAplicacion() throws Exception;
		
	public abstract ComboUtil getObjDetalleHlp(String codAyuda, String codHijo) throws Exception;
	
	public abstract boolean getVerificarHorario(String transaccion) throws Exception;

  
    public abstract LimitImpl getLimit(LimitImpl limit)throws Exception;
    
    public void setHorarioInicioDia(String hora) throws Exception;
    
    public void setFlagBloqueoAgenciaVirtual(String flag) throws Exception;
    
	public abstract List getAgenciasSoles() throws Exception;
	public abstract List getAgenciasDolares() throws Exception;
	
	public abstract List getDepartamentos1()throws Exception;
	
	public abstract List getListDetalleConcepto(String codAyuda, String codHijo) throws Exception;
	
    public abstract String getDiasMigracion(String codhlp) throws Exception;
    
    public abstract String getUrlPrestamos(String codhlp, String numseq) throws Exception;
	
}