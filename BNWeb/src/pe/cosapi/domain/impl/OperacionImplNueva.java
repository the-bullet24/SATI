/*
 * Creado el 20/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.pago.domain.impl.PagoPrestamoMultiredImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.telegiro.action.TelegiroAction;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
//@DPS import pe.cosapi.common.Refrendo;
import pe.cosapi.domain.Operacion;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class OperacionImplNueva implements Operacion, Serializable {
	private static LoggerSati log3 = LoggerSati.getInstance(OperacionImplNueva.class.getName());
	//Logger log = Logger.getLogger(OperacionImpl.class); 
	
	Long nroLog;
	
	private static  Map variablesGlobales;
	
	private String fecha;

	private String hora;
	
	private String nroOperacion;
	
	private String tipoToken;
	
	protected static final String OPERACION = "321";
		 	
	private Transaction transaction;
	
	private Usuario usuario;
	
	private ArrayRuleException arrayRuleException;

//@DPS	private Refrendo refrendo;
	
	private String canal = "0";
	
	
	public boolean grabarOperacionHost = true;
	
	/**
	 * 
	 */
	public OperacionImplNueva() {

	}
	

	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @return Devuelve hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @return Devuelve nroOperacion.
	 */
	public String getNroOperacion() {
		return nroOperacion;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @param hora El hora a establecer.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getTipoToken() {
		return tipoToken;
	}
	
	public void setTipoToken(String tipoToken) {
		this.tipoToken = tipoToken;
	}

	public void setNroOperacion(String nroOperacion){
		this.nroOperacion = nroOperacion;
	}
	
	public void setNroOperacion() {		

		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;
		Date data;
		//Seteamos la fecha
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = new Date(fechaActual.getTime());
		fecha = formatter.format(data);

		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		hora = formatter.format(data);

	}

	/**
	 * @return Devuelve refrendo.
	 */
//@DPS
//	public Refrendo getRefrendo() {
//		return refrendo;
//	}
	/**
	 * @param refrendo El refrendo a establecer.
	 */
//@DPS
//	public void setRefrendo(Refrendo refrendo) {
//		this.refrendo = refrendo;
//	}
	/**
	 * @return Devuelve transaction.
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * @param transaction El transaction a establecer.
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	/**
	 * @return Devuelve usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario El usuario a establecer.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return Devuelve arrayRuleException.
	 */
	public ArrayRuleException getArrayRuleException() {
		return arrayRuleException;
	}
	/**
	 * @param arrayRuleException El arrayRuleException a establecer.
	 */
	public void setArrayRuleException(
			ArrayRuleException webServicesException) {
		this.arrayRuleException = webServicesException;
	}
			
	public Vector procesar(Transaction t) throws Exception{
				
		ArrayRuleException wse = null;
		this.setArrayRuleException(wse);
				
		nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
		String numLog = String.valueOf(nroLog);
		t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
		JournalImpl journal = new JournalImpl();
		journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
		//Seteamos la fecha
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("dd/MM/yyyy");			
		Date data = new Date(fechaActual.getTime());
		fecha = formatter.format(data);
		
		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		hora = formatter.format(data);
		
		this.setFecha(fecha);
		this.setHora(hora);
		//journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setDatpro(fechaActual);
		journal.setHorpro(hora);
		journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
		
	
		//if((journal.getCodtrahst() != null && journal.getCodtrahst() != "")||  (journal.getCodtra() != null &&journal.getCodtra() != ""))						
		//{
			DAOFactory.getSaraWebLogImpl().insertarJournal(journal);
		//}
		
		try{
			
			t.loadVectors();
						
		}
		catch(InvocationTargetException ite){
			
			
			log3.error(ite,Constante.ERR_LOGICA_NEGOCIO,"");
			Throwable te = ite.getTargetException().getCause();			
			
			if(te == null) 
				te = ite.getTargetException();
			if (te instanceof ArrayRuleException){
				this.setArrayRuleException((ArrayRuleException)te);
			}
			else{
				throw new Exception();
			}
		}
		catch(ArrayRuleException e){
			
			if(t.values!=null){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,t.values.toString());
			}else{
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
			
			this.setArrayRuleException(e);
		}
		catch(Exception e1){
			
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e1;
		}		
		return t.getMascVector();
	}
	
	public Vector procesar1(Transaction t) throws Exception{
		ArrayRuleException wse = null;
		this.setArrayRuleException(wse);
		
		nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
		String numLog = String.valueOf(nroLog);
		t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
		JournalImpl journal = new JournalImpl();
		journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
		//Seteamos la fecha
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("dd/MM/yyyy");			
		Date data = new Date(fechaActual.getTime());
		fecha = formatter.format(data);
		
		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		hora = formatter.format(data);
		
		this.setFecha(fecha);
		this.setHora(hora);
		//journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setDatpro(fechaActual);
		journal.setHorpro(hora);
		journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
					
		//DAOFactory.getSaraWebLogImpl().insertarJournal(journal);
		
		try{
			t.loadVectors();
		}
		catch(InvocationTargetException ite){
			log3.error(ite,Constante.ERR_LOGICA_NEGOCIO,"");
			Throwable te = ite.getTargetException().getCause();			
			
			if(te == null) 
				te = ite.getTargetException();
			if (te instanceof ArrayRuleException){
				this.setArrayRuleException((ArrayRuleException)te);
			}
			else{
				throw new Exception();
			}
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			this.setArrayRuleException(e);
		}
		catch(Exception e1){
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e1;
		}		
		return t.getMascVector();
	}


	/**
	 * @return Devuelve canal.
	 */
	public String getCanal() {
		return canal;
	}
	/**
	 * @param canal El canal a establecer.
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	/**
	 * @return Returns the variables.
	 */
	public Map getVariables() {
		return variablesGlobales;
	}
	
	/**
	 * @param variables The variables to set.
	 */
	public static void setVariables(Map variables) {
		variablesGlobales = variables;
	}
	
	
    public void setNroLog(Long nroLog) {
		this.nroLog = nroLog;
	}


	public Long getNroLog() {
        return nroLog;
    }
}
