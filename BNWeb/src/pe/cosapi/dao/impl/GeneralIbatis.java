/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cosapisoft.sarawebbranch.server.GeneralParameters;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOAbstract;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.dao.GeneralDAO;
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
public class GeneralIbatis extends DAOAbstract implements GeneralDAO{
	private static LoggerSati log3 = LoggerSati.getInstance(GeneralIbatis.class.getName());
	
	
	public List getAgenciasSoles()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getAgenciasSoles");
	}
	public List getAgenciasDolares()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getAgenciasDolares");
	}
	public List getDepartamentos1()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getDepartamentos1");
	}
	
	
	public List getEsquema() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getEsquema");
	}

	public List getDiccionario() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getDiccionario");
	}
	
	public List getControlTransaccion() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getControlTransaccion");
	}

	public List getParametro() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getParametro");
	}

	public List getMensajeria() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getMensajeria");
	}
	
	public List getMensajeriaApp() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getMensajeriaApp");
	}
	
	public List getMensajesHost() throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getMensajesHost");
	}
	
	public  MsgComunication[] getMsgComunication(String trx) throws Exception{
		List lstMsgComunication = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListMsgComunication",trx); 
		return  (MsgComunication[])lstMsgComunication.toArray(new MsgComunication[0]);
	}
	
	public Banner getBannerByDate(String tipopersona,Timestamp fecha) throws Exception{
		Map input = new HashMap(); 
		input.put("tipopersona", tipopersona);
		input.put("fecha", fecha);
		return (Banner)getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getBannerByDate", input);
	}
	
	public Branch getBranch(String codbra) throws Exception{
		return 
	(Branch)getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getBranchById", codbra);
	}
	
	public List getEstilo()throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getEstilo");
	}
	
	public MasterTransaction getTransactionById(String codTrx) throws Exception{
		return (MasterTransaction)getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getTransactionById",codTrx);
	}
	
	public MasterLimitsImpl getLimitsById(String tipPersona,String codTrx) throws Exception{
		Map input = new HashMap();
		input.put("tipoPersona", tipPersona);
		input.put("codTrx", codTrx);
		return (MasterLimitsImpl)getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getLimitsById",input);
	}
	
	public  List getListDetalleAyuda(String codAyuda) throws Exception{
		
	
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleAyuda",codAyuda);
	
		return lstDetalleAyuda;
	}
	
	public  List getListDetalleAyudaOrden(String codAyuda) throws Exception{
		
		
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleAyudaOrden",codAyuda);
		
		return lstDetalleAyuda;
	}
	
	public  List getListDetallePais() throws Exception{
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetallePais");
		return lstDetalleAyuda;
	}
	
	public  List getListDetalleDepartamento() throws Exception{
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleDepartamento");
		return lstDetalleAyuda;
	}
			
	public  List getListDetalleProvincia(String codAyuda) throws Exception{
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleProvincia",codAyuda);
		return lstDetalleAyuda;
	}
	public  List getListDetalleDistrito(String codAyuda) throws Exception{
		
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleDistrito",codAyuda);
		
		return lstDetalleAyuda;
	}
	
	public  List getListDetalleDepartamentoCore() throws Exception{
	
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleDepartamentoCore");
		return lstDetalleAyuda;
	}
			
	public  List getListDetalleProvinciaCore(String codAyuda) throws Exception{
	
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleProvinciaCore",codAyuda);
		return lstDetalleAyuda;
	}
	public  List getListDetalleDistritoCore(String codAyuda) throws Exception{
	
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleDistritoCore",codAyuda);
		
		return lstDetalleAyuda;
	}
	
	public  String getDescripcionUbigeo(String codAyuda) throws Exception{
		
		String value = (String) getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getDescripcionUbigeo",codAyuda);
		
		return value;
	}
	
	public  List getListDetalleLocalidad(String codAyuda) throws Exception{
		
	List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleLocalidad",codAyuda);
		
		return lstDetalleAyuda;
	}
	
	
	public  List getListDetalleAyudaDiscado(String codAyuda) throws Exception{
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleAyudaDiscado",codAyuda);
		return lstDetalleAyuda;
	}
	
	public  List getListDetalleAyudaHijo(String codAyuda, String codHijo) throws Exception{
		Map input = new HashMap();
		input.put("value",codAyuda);	
		input.put("codHijo",codHijo);
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleAyudaHijo",input);
		return lstDetalleAyuda;
	}
	
	public List getDepartamentos() throws Exception{
		Map input = new HashMap();
		input.put("codigo",Constante.COD_HLP_DEPARTAMENTO);
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getDepartamentos",input);
	}
	
	public List getAgencias(String codigoDepartamento) throws Exception{
		Map input = new HashMap();
		input.put("codigo",Constante.COD_HLP_AGENCIA);
		input.put("codigoDepartamento",codigoDepartamento);
		return getSqlMapClientTemplate().queryForList("sqlMapGeneral.getAgencias",input);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#getComboDetalleHlp(java.lang.String, java.lang.String)
	 */
	public List getComboDetalleHlp(String codAyuda) throws Exception {
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlp",codAyuda);
		return lstDetalleAyuda;
	}
	
	public List getComboDetalleHlpOrden(String codAyuda) throws Exception {
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlpOrden",codAyuda);
		return lstDetalleAyuda;
	}
	
	public List getComboDetHlp(String codAyuda) throws Exception {
		List lstDetAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlp",codAyuda);
		return lstDetAyuda;
	}
	
	public List getComboDetHlpOrden(String codAyuda) throws Exception {
		List lstDetAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlpOrden",codAyuda);
		return lstDetAyuda;
	}
	
	public List getFlagTransaccion(String codAyuda) throws Exception {
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getFlagTransaccion",codAyuda);
		return lstDetalleAyuda;
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#getComboAyudaHijo(java.lang.String, java.lang.String)
	 */
	public List getComboAyudaHijo(String codAyuda) throws Exception {
	
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboAyudaHijo",codAyuda);
		return lstDetalleAyuda;
	}
	public List getComboAyudaHijoOrden(String codAyuda) throws Exception {
	
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboAyudaHijoOrden",codAyuda);
		return lstDetalleAyuda;
	}
	
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#getComboDetalleHlp(java.lang.String, java.lang.String)
	 */
	
	public List getComboDetHlpTasa(String codAyuda) throws Exception {
		List lstDetAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlpTasa",codAyuda);
		return lstDetAyuda;
	}
	
	public List getComboDetalleHlpHijo(String codAyuda, String codHijo) {
				
		Map input = new HashMap();
		input.put("codAyuda",codAyuda);
		input.put("codHijo",codHijo);
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlpHijo",input);
			
		return lstDetalleAyuda;
	}
	
	
	public List getComboDetalleHlpHijoMod(String codAyuda, String codHijo) {
		Map input = new HashMap();
		input.put("codAyuda",codAyuda);
		input.put("codHijo",codHijo);
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlpHijoMod",input);
		return lstDetalleAyuda;
	}
	
	public List getComboDetalleHlpHijoCodhlp(String codAyuda, String codHijo) {
		Map input = new HashMap();
		input.put("codAyuda",codAyuda);
		input.put("codHijo",codHijo);
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getComboDetalleHlpHijoCodhlp",input);
		return lstDetalleAyuda;
	}
	
	public ComboUtil getObjDetalleHlp(String codAyuda, String codHijo) throws Exception{
		Map input = new HashMap();
		//if(Constante.VER_LOG) System.out.println("codAyuda:"+codAyuda);
		//if(Constante.VER_LOG) System.out.println("codHijo:"+codHijo);
		input.put("codAyuda",codAyuda);
		input.put("codHijo",codHijo);
		ComboUtil objDetalleAyuda = (ComboUtil)getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getObjDetHlp",input);
		return objDetalleAyuda;
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#getJournalDiccionario()
	 */
	public List getJournalDiccionario() {
		List lista = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getJournalDiccionario");
		return lista;
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#getJournalDiccionario(java.lang.String)
	 */
	public List getJournalDiccionario(String transaccion) {
		Map input = new HashMap();
		input.put("value",transaccion);		
		List lista = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getJournalDiccionario",input);
		return lista;
	}
	public  List getListDetAyuda(String codAyuda) throws Exception{
		List lstDetAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetAyuda",codAyuda);
		return lstDetAyuda;
	}
	
	public boolean getEstadoAplicacion() throws Exception{
		boolean sw = false;
		
		String value = (String) getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getEstadoAplicacion");
	
		if("0".equals(value))
			sw= true;
			
		return sw;
	}
	public String getDescripcionLocalidad(String codAyuda, String codCiudad) throws Exception{
		Map input = new HashMap();
		input.put("codAyuda",codAyuda);
		input.put("codCiudad",codCiudad);
		String value = (String) getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getDescripcionLocalidad",input);
		return value;
	}
	
	
	public boolean getVerificarHorario(String transaccion) throws Exception{
		Connection conn =  this.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String fecha = ObjectUtil.timestampToString(new Timestamp(new Date().getTime()),"dd/MM/yyyy HH:mm:ss");
			
			
			String consulta = 	"select DISTINCT t.codtra AS codigo" +
					"		  from PV_SWB.ttrahor t" +
					"			 where NOT EXISTS" +
					"			 (SELECT DISTINCT 1 AS IDX FROM PV_SWB.ttrahor WHERE codtra = ?)" +
					"			    OR (TO_CHAR(TO_DATE(?,'DD/MM/YYYY hh24:mi:ss'), 'd') = t.coddia" +
					"					and t.codtra = ? and" +
					"			       (TO_DATE('01/01/1980 ' || TO_CHAR(TO_DATE(?,'DD/MM/YYYY hh24:mi:ss'), 'hh24:mi:ss')," +
					"			                'dd/mm/yyyy hh24:mi:ss') between" +
					"			       TO_DATE('01/01/1980 ' || TO_CHAR(t.horini, 'hh24:mi:ss')," +
					"			                'dd/mm/yyyy hh24:mi:ss') and" +
					"			       TO_DATE('01/01/1980 ' || TO_CHAR(t.horfin, 'hh24:mi:ss')," +
					"			                'dd/mm/yyyy hh24:mi:ss')) )";
			
			//System.out.println("CONSULTA-->"+consulta);
		
			ps =  conn.prepareStatement(consulta);
			int pos =0;
			ps.setString(++pos,transaccion);
			ps.setString(++pos,fecha);
			ps.setString(++pos,transaccion);
			ps.setString(++pos,fecha);
			rs = ps.executeQuery();
			
			if(rs.next())
				
				return true;
		} catch (ParseException e) {
			
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (SQLException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
			conn.close();
		}
		return false;
	}

    /* (sin Javadoc)
     * @see pe.cosapi.dao.GeneralDAO#getLimit(pe.cosapi.domain.impl.LimitImpl)
     */
    public LimitImpl getLimit(LimitImpl limit) throws Exception{
        return (LimitImpl)getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getLimit",limit); 
    }

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.HorarioDAO#setHorarioInicioDia(java.lang.String)
	 */
	public void setHorarioInicioDia(String hora) throws Exception {
		//Actualiza el horario de Inicio de  Dia automático
		getSqlMapClientTemplate().update("sqlMapGeneral.setHoraInicioDia",hora);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#setFlagBloqueoAgenciaVirtual(boolean)
	 */
	public void setFlagBloqueoAgenciaVirtual(String flag) throws Exception {
		//1=Bloqueado 0=Desbloqueado
		getSqlMapClientTemplate().update("sqlMapGeneral.setBloqueoAgenciaVirtual",flag);
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#setFlagBloqueoAgenciaVirtual(boolean)
	 */
	public void setEliminaDatosDuplicados() throws Exception {
		//1=Bloqueado 0=Desbloqueado
		getSqlMapClientTemplate().delete("sqlMapGeneral.setEliminaDuplicados");
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#setFlagBloqueoAgenciaVirtual(boolean)
	 */
	public void setMueveDiarioElectronico() throws Exception {
		//1=Bloqueado 0=Desbloqueado
		getSqlMapClientTemplate().insert("sqlMapGeneral.setActualizaDiarioHistorico");
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#setFlagBloqueoAgenciaVirtual(boolean)
	 */
	public void setEliminaDiarioElectronico() throws Exception {
		//1=Bloqueado 0=Desbloqueado
		getSqlMapClientTemplate().delete("sqlMapGeneral.setBorraDiarioElectronico");
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.GeneralDAO#getClaves()
	 */
	public GeneralParameters getClaves() throws Exception {
		// TODO Apéndice de método generado automáticamente
		GeneralParameters value = (GeneralParameters) getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getClaves");
		return value;
	}
	
	public  List getListDetalleConcepto(String codAyuda, String codHijo) throws Exception{
		Map input = new HashMap();
		
		
		input.put("codAyuda",codAyuda);
		input.put("codHijo",codHijo);
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapGeneral.getListDetalleConcepto",input);
		return lstDetalleAyuda;
	}
	
    public String getDiasMigracion(String codhlp) throws Exception{
        Map input = new HashMap();
        input.put("codhlp",codhlp);
        String value = (String) getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getDiasMigracion",input);
        return value;
    }
    
    public String getUrlPrestamos(String codhlp, String numseq) throws Exception{
        Map input = new HashMap();
        input.put("codhlp",codhlp);
        input.put("numseq", numseq);
        String value = (String) getSqlMapClientTemplate().queryForObject("sqlMapGeneral.getUrlPrestamos",input);
        return value;
    }
    
}
