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

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.pago.domain.impl.PagoCuponeraImpl;
import pe.bn.pago.domain.impl.PagoFacturaImpl;
import pe.bn.pago.domain.impl.PagoFacturaWSImpl;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.pago.domain.impl.PagoPrestamoMultiredImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.Refrendo;
import pe.cosapi.domain.Operacion;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class OperacionImpl implements Operacion, Serializable {
	private static LoggerSati log3 = LoggerSati.getInstance(OperacionImpl.class.getName());
	
	//Logger log = Logger.getLogger(OperacionImpl.class); 
	
	Long nroLog;
	
	private static  Map variablesGlobales;
	
	private String fecha;

	private String hora;
	
	private String nroOperacion;
	
	private String nroTransaccion;
	
	private String tipoToken;
	
	
	protected static final String OPERACION = "321";
		 	
	private Transaction transaction;
	
	private Usuario usuario;
	
	private ArrayRuleException arrayRuleException;

	private Refrendo refrendo;
	
	private String canal = "0";
	
	public boolean grabarOperacionHost = true;
	
	private String fechaOperacion;
	
	/**
	 * 
	 */
	public OperacionImpl() {

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
	public String getTipoToken() {
		return tipoToken;
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
	
	public void setTipoToken(String tipoToken) {
		this.tipoToken = tipoToken;
	}
	
	public void setNroOperacion(String nroOperacion){
		this.nroOperacion = nroOperacion;
	}

	/**
	 * @return Returns the nroTransaccion.
	 */
	public String getNroTransaccion() {
		return nroTransaccion;
	}
	/**
	 * @param nroTransaccion The nroTransaccion to set.
	 */
	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
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
	public Refrendo getRefrendo() {
		return refrendo;
	}
	/**
	 * @param refrendo El refrendo a establecer.
	 */
	public void setRefrendo(Refrendo refrendo) {
		this.refrendo = refrendo;
	}
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
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
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
		
	public void generarLog(Transaction t, Usuario usuario, String plantilla) throws Exception{
	    
		
		Vector resultado = t.getMascVector();
		
		String ip	= "desconocido";
		Vector datos = t.getCuentas();
		
		for (Iterator iter = datos.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
				ip = (String) iterator.next();
				
			}
		}
		 
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;

		
		this.setTransaction(t);
		this.setUsuario(usuario);
				
		Map map = new HashMap();
		if(variablesGlobales!=null){
		map.putAll(this.getVariables());
		map.put("param",this);
		map.put("usuario",usuario);
		}
		else{
			map.put("param",this);
			map.put("usuario",usuario);
		}
		
		
		List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(t.getTransaction());
		
		Map parametros = new HashMap();
		for (Iterator it = lista.iterator(); it.hasNext();) {
			DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
			for (Iterator iter = resultado.iterator(); iter.hasNext();) {
				Vector elemento = (Vector) iter.next();
				if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
					parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
				}
			}			
		}
		
		JournalImpl journal = new JournalImpl();
		
		String numLog = String.valueOf(this.nroLog);
		journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
		System.out.println("t.getTransaction():"+t.getTransaction()); 
		journal.setCodtra(t.getTransaction());
		journal.setCodtrahst(t.getTransactionHost());
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setTypper(usuario.getTipoPersona());
		journal.setCipadr(ip);
		journal.setNumope(this.getNroOperacion());
		journal.setFlgerror(ConstanteSesion.HOST_ERROR);
		journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
		
		
		if (ConstanteSesion.HOST_ERROR.equals("0")){///////////
						
			if(usuario.getClaveDinamica()!= null && usuario.getClaveDinamica().getTipoElementoSeguridadFinal()!=null){
				
				if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					journal.setMsghst("OK:CLAVE6,TARJETA DE COORDENADAS");
				}
				else{
					if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
					
						journal.setMsghst("OK:CLAVE6,TOKEN");
					}
				}
				
				
		
							
			}else{
				
				journal.setMsghst("OK:N°TARJ,CLAVE6");
			}
			
			
		}
		
		journal.setFlgcom(usuario.getTipoIngreso());
		
		ConstanteSesion.HOST_ERROR="0";
		ConstanteSesion.HOST_ERROR_MSG="";
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
			journal.setCoddoc("0001");
			journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
			journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
			if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
				journal.setNumprdsrc(usuario.getTarjeta().getNumero());
			else
				journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
		}

		try {
		    if (t.getObjeto() != null){
			    if (t.getObjeto() instanceof TransferenciaImpl) {
	                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
	                if (object.isInterbancaria()) {
//MGL
	                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
	                	journal.setAmotra(importeLimite);
	                	//log.warn("importe getAmotra......"+journal.getAmotra());
	                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	                	journal.setAmotxn(importeLimite);
	                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
	                	//log.warn("importe journal.getBaltra......"+journal.getBaltra());
	                	//System.out.println("object.getCuentaOrigen().getMonedaProducto().toString():"+object.getCuentaOrigen().getMonedaProducto().toString());
	                	//String moneda=object.getCuentaOrigen().getMonedaProducto().toString().substring(0,2);
	                	//System.out.println("object.getSimboloMonedaImpNetoCargo():"+object.getSimboloMonedaImpNetoCargo());
	                	String moneda=object.getSimboloMonedaImpNetoCargo().trim();
	                	
	                	journal.setNumref("99999999");
	                	
	                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
	                	    journal.setCodcur("604");
	 	                }
	 	                else{
	 	                	journal.setCodcur("840");
	 	                }
	                	
	                }
	                else{
	                	//log.warn("entró a bancaria");
	                	String importeLimite = ObjectUtil.replaceChar(object.getImporteNeto().toString(), ',', "");
	                	journal.setAmotra(importeLimite);
	                	//log.warn("importe getAmotra......"+journal.getAmotra());
	                	importeLimite = ObjectUtil.replaceChar(object.getImporteCargo().toString(), ',', "");
	                	journal.setAmotxn(importeLimite);
	                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	                	//log.warn("importe journal.getBaltra......"+journal.getBaltra());
	                	//String moneda=object.getCuentaOrigen().getMonedaProducto().toString().substring(0,2);
	                	String moneda=object.getSimboloMonedaImpNetoCargo();
	 	                if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
	 	                    journal.setCodcur("604");
	 	                }
	 	                else{
	 	                	journal.setCodcur("840");
	 	                }
	                   	                  
	                }
	            }else if (t.getObjeto() instanceof TelegiroImpl) {
	                TelegiroImpl object = (TelegiroImpl) t.getObjeto();
	                String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
            		journal.setAmotra(importeLimite);
            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
            		journal.setAmotxn(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	                  journal.setNrocta(object.getCuenta().getNumeroProducto());
	                  journal.setCoddoc(object.getAfiliacion().getCodigoServicio());
	                  journal.setNumdoc(object.getAfiliacion().getNumeroServicio());
	                  journal.setNumref(object.getAgencia().getCodigo());
	                  if(journal.getCodtrahst().equals("7520")){
	                  	
	                	journal.setCodcur("604");
	                  }else{
	                	journal.setCodcur("840");
	                  }
	                  journal.setNomben(object.getAfiliacion().getBeneficiario());
	                  //journal.setBaltra(object.getTotal());
	            }
	            else if (t.getObjeto() instanceof PagoSedapalImpl) {
	            	PagoSedapalImpl object = (PagoSedapalImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getRecibo().getImporte().toString(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	             	journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getCuentaFormateada(),'-',""));
	            	journal.setNumref(object.getNroSuministro());
	            	journal.setCoddoc(object.getAfiliacion().getTipoDocumento());
	                journal.setNumdoc(object.getAfiliacion().getNroDocumento());
	            	journal.setNomben(object.getCliente());
	            	journal.setCodent(object.getCodEntidad());
	             	journal.setCodser(object.getCodServicio());
	             	journal.setCodcur("604");
	            }		    
	            else if (t.getObjeto() instanceof PagoTasasImpl) {
	            	PagoTasasImpl object = (PagoTasasImpl) t.getObjeto();
	            	if(object.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)){
	            	    String importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	            		journal.setAmotra(importeLimite);
	            		journal.setAmotxn(importeLimite);
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setCodcur("604");
		                
	            	}
	            	else if(object.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
	            	    String importeLimite = ObjectUtil.replaceChar(object.getImporteAlCambio().toString(), ',', "");
	            		journal.setAmotra(importeLimite);
	            		journal.setAmotxn(importeLimite);
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            	    journal.setCodcur("840");
	            	}
	            	journal.setNumdoc(object.getDocumento().getNumero());
	            	journal.setCoddoc(object.getDocumento().getCodigo());
	            	journal.setNumref(object.getTributo().getCodigo());
	            	journal.setNrocta(object.getCuenta().getNumeroProducto());
	            	
	            }
	            else if (t.getObjeto() instanceof PagoTelefonoImpl) {
	            	PagoTelefonoImpl object = (PagoTelefonoImpl) t.getObjeto();
	            	journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getNumeroProducto(),'-',""));
	            	String moneda=ObjectUtil.replaceChar(object.getCuenta().getNumeroProducto(),'-',"").substring(0,2);
	            	if(object.getImporteSol()!=null && !object.getImporteSol().toString().trim().equals("") && object.getImporteDol()!=null && !object.getImporteDol().toString().trim().equals("")){
	            		if(moneda.equals("04") || moneda.equals("00")){
	            			journal.setAmotra(ObjectUtil.replaceChar(object.getImporteSol().toString(), ',', ""));
	            			journal.setAmotxn(ObjectUtil.replaceChar(object.getImporteSol().toString(), ',', ""));
	            			journal.setBaltra(ObjectUtil.replaceChar(object.getImporteDol().toString(), ',', ""));
	            			//log.warn("dolares............"+journal.getAmotra());
	            			//log.warn("soles............"+journal.getBaltra());
	            			journal.setCodcur("604");
	            		}
	            	
	            		else if(moneda.equals("06") || moneda.equals("08")){
	            			journal.setAmotra(ObjectUtil.replaceChar(object.getImporteDol().toString(), ',', ""));
	            			journal.setAmotxn(ObjectUtil.replaceChar(object.getImporteDol().toString(), ',', ""));
	            			journal.setBaltra(ObjectUtil.replaceChar(object.getImporteSol().toString(), ',', ""));
	            			//log.warn("dolares d............"+journal.getBaltra());
	            			//log.warn("soles d............"+journal.getAmotra());
	            			 journal.setCodcur("840");
	            		}
	            	}
	            	else{
	            		journal.setAmotra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setAmotxn(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		//log.warn("misma moneda............"+journal.getAmotra());
	            		 journal.setCodcur(object.getCodMoneda());
	            	}
	            	
	                journal.setNumref(object.getAfiliacion().getNumeroServicio());
	                journal.setCoddoc(object.getAfiliacion().getTipoDocumento());
	                journal.setNumdoc(object.getAfiliacion().getNroDocumento());
	                journal.setNomben(object.getNomAbonado());
	            	journal.setCodent(object.getCodEntidad());
	             	journal.setCodser(object.getCodServEnt());

	            }
	            else if (t.getObjeto() instanceof PagoPrestamoMultiredImpl) {
	            	PagoPrestamoMultiredImpl object = (PagoPrestamoMultiredImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	                journal.setNumref(object.getNroPrestamo());
	                journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getNumeroProducto(),'-',""));
	                String moneda=journal.getNrocta().substring(0,2);
	                if(moneda.equals("04") || moneda.equals("00")){
	                journal.setCodcur("604");
	                }
	                else{
	                	journal.setCodcur("840");
	                }
	            }
	            else if (t.getObjeto() instanceof PagoTarjetaImpl) {
	            	PagoTarjetaImpl object = (PagoTarjetaImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getTotal(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setAmotxn(ObjectUtil.replaceChar(object.getImporteAlCambio().toString(), ',', ""));
	                journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	                journal.setNrocta(object.getCuenta().getNumeroProducto());
	                journal.setNumprdtar(object.getAfiliacion().getNumeroServicio());
	                journal.setNumdoc("");
	                if (object.getCuenta().getMonedaProducto().trim().equals("PEI") ){
	                    journal.setCodcur("604");
	                } else {
	                    journal.setCodcur("840");
	                }
	                
	            }else if (t.getObjeto() instanceof PrestamoImpl) {
	            	PrestamoImpl object = (PrestamoImpl) t.getObjeto();
	                String cCtaAct = ObjectUtil.formatearCuenta(object.getCuenta(),Constante.FORMATO_CUENTA);
	                journal.setNrocta(cCtaAct);
	                String importeLimite = ObjectUtil.replaceChar(object.getMontoPrestamo().toString(), ',', "");
	            	journal.setAmotra(importeLimite);
	            	journal.setAmotxn(importeLimite);
	            	journal.setBaltra(ObjectUtil.replaceChar(object.getMontoPrestamo().toString(), ',', ""));
	            	journal.setCodcur("604");
	            	journal.setNumref(object.getNroPrestamo());
	                //journal.setNrocta(object.getCuenta());
	            }
	            else if (t.getObjeto() instanceof CuentaImpl) {
	            	CuentaImpl object = (CuentaImpl) t.getObjeto();
	            	if(object.getNroCuentaCci()!=null){
	            		journal.setNumprdtar(object.getNroCuentaCci());
	            	}
	                //journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
	            	journal.setNrocta(object.getCuentaFormateada());
	                journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
	            }
	            else if (t.getObjeto() instanceof TarjetaImpl) {
	            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
	            	journal.setNumref(object.getCodigo());
	            }
	            else if (t.getObjeto() instanceof PagoCuponeraImpl) {
	            	PagoCuponeraImpl object = (PagoCuponeraImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getRecibo().getImporte().toString(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	             	journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getCuentaFormateada(),'-',""));
	            	journal.setNumref(object.getNroReferencia());
	            	journal.setCoddoc(object.getAfiliacion().getTipoDocumento());
	                journal.setNumdoc(object.getAfiliacion().getNroDocumento());
	            	journal.setNomben(object.getCliente());
	            	journal.setCodent(object.getCodEntidad());
	             	journal.setCodser(object.getCodServicio());
	             	journal.setCodcur("604");
	            } else if (t.getObjeto() instanceof PagoFacturaImpl) {
	            	PagoFacturaImpl object = (PagoFacturaImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getRecibo().getImporte().toString(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	             	journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getCuentaFormateada(),'-',""));
	            	journal.setNumref(object.getNroReferencia());
	            	journal.setCoddoc(object.getAfiliacion().getTipoDocumento());
	                journal.setNumdoc(object.getAfiliacion().getNroDocumento());
	            	journal.setNomben(object.getCliente());
	             	journal.setCodcur("604");
	             	journal.setCodent(object.getCodEntidad());
	             	journal.setCodser(object.getCodServicio());
	            } else if (t.getObjeto() instanceof PagoFacturaWSImpl) {
	            	PagoFacturaWSImpl object = (PagoFacturaWSImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getFactura().getImporte().toString(), ',', "");
	            	journal.setCodtra("PF01");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	             	journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getCuentaFormateada(),'-',""));
	            	journal.setNumref(object.getNroReferencia());
	            	journal.setCoddoc("");
	                journal.setNumdoc("");
	            	journal.setNomben(object.getCliente());
	             	journal.setCodcur("604");
	             	journal.setCodent(object.getCodEntidad());
	             	journal.setCodser(object.getCodServicio());
	             	
	             	            
		      } else if (t.getObjeto() instanceof AfiliacionImpl) {
		    	  AfiliacionImpl object = (AfiliacionImpl) t.getObjeto();
	            	//String importeLimite = ObjectUtil.replaceChar(object.getFactura().getImporte().toString(), ',', "");
		    	 
		    	  //	System.out.println("t.getTransaction():"+ t.getTransaction());
	            	journal.setCodtra(object.getTipoAfiliacion());
	                journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getCuentaFormateada(),'-',""));
	            	//journal.setNumref(object.getN);
	            	journal.setCoddoc(""); 
	                journal.setNumdoc("");
	            	//journal.setNomben(object.getCliente());
	             	//journal.setCodcur("604");
	             	//journal.setCodent(object.getCodEntidad());
	             	//journal.setCodser(object.getCodServicio());
	            }
	            
			}
        } catch (Exception e) {
        	
        	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
        }
		
		
		
		if(this.getArrayRuleException()!=null)
			journal.setMsgerror("");
		
		if(plantilla!=null&&this.getArrayRuleException()==null){
			this.setRefrendo(new Refrendo(plantilla));
			if (t.getObjeto() instanceof PagoTelefonoImpl){
				map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
			}	
			this.getRefrendo().procesar(map);
			journal.setConstancia(this.getRefrendo().getResultado());
		}
		
		Class cls = journal.getClass(); 
		//Datos que vienen de la configuracion del journal
		for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
		    String key = (String)iter.next();
		    Object obj = parametros.get(key);
		    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
		    if(obj!=null){
			    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
				method.invoke(journal, new Object[]{obj});
		    }
		}
		
		journal.setFlgcha(this.canal);
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		//journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
		//Constante.WAP_CTA_CTE="";
		//Constante.WAP_DNI_CTA_CTE="";
		if(t.getTransactionHost().trim().equals("0112"))
			journal.setNrocta("");
		if(t.getTransactionHost().trim().equals("1162")){
		    String cCtaSinF = (ObjectUtil.replaceChar(journal.getNrocta(),'-',""));
			journal.setNrocta(ObjectUtil.formatearCuenta(cCtaSinF,Constante.FORMATO_CUENTA));
		}
		if(t.getTransaction().trim().equals("LG01"))
			journal.setNrocta("");
		
		ConstanteSesion.WAP_NUM_TAR="";
		DAOFactory.getSaraWebLogImpl().actualizarJournal(journal);
		
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
	
    public Long getNroLog() {
        return nroLog;
    }


	public String getFechaOperacion() {
		return fechaOperacion;
	}


	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
}
