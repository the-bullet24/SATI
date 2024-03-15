/*
 * Creado el 26/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.domain.impl;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.GeneracionLogResultado;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.GeneracionLogImpl;
import pe.cosapi.domain.impl.JournalImpl;
import pe.cosapi.domain.impl.OperacionImplNueva;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TarjetaImpl extends OperacionImplNueva implements Tarjeta, Serializable {
	
	private String numero;
	private String tarjetaOculta;
	private String tipo;
	private boolean asociado;
	private String clave;
	private String fecha;
	private String hora;
	private String codigo;
	//Add MDB - Claves Dinamicas
	private String codigoOperacion;
	private String idAfiliacion;
	private String claveAfil;
	
	
	private String fechaLogica;
	private String fechaCronologica;
	private String horaCronologica;
	
	
	public TarjetaImpl() {
	}
	
	public TarjetaImpl(String nroTarjeta) {
		this.numero = nroTarjeta;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;
		Date data;

		//Seteamos la fecha
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = new Date(fechaActual.getTime());
		this.fecha = formatter.format(data);

		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		this.hora = formatter.format(data);
	}
	
	public void generarClave(Transaction t, Usuario usuario) throws Exception{
		//procesar(t);
		//generarLog(t,usuario,Constante.REFRENDO_GENERACION_CLAVE);
	}
	
	public void cambiarClave(Transaction t, Usuario usuario) throws Exception{
		//procesar(t);
		//generarLog(t,usuario,Constante.REFRENDO_CAMBIO_CLAVE);
	}
	
	public void cambiarClaveCaducidad(Transaction t, Usuario usuario, TarjetaImpl tarjeta, HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajecambiopie",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecambiocabecera",((Vector)aplicacion.getMensajePorCodigo("GC02","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecambioatte",((Vector)aplicacion.getMensajePorCodigo("GC02","00005")).elementAt(2).toString());
		
		
		int vInsertLog = procesoInsertarLog(t,usuario, request,t.transaction);
		String fchLogica = "";
		String fchCrono = "";
		String hraCrono = "";
				
		Vector mascara = procesar(t);
		
		TarjetaImpl tarjetaImpl = new TarjetaImpl();
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setFechaLogica(elemento.elementAt(6).toString().trim());
				fchLogica=(elemento.elementAt(6).toString().trim());
				System.out.println("fchLogica:"+fchLogica);
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    this.setFechaCronologica(elemento.elementAt(6).toString().trim());
			    fchCrono=(elemento.elementAt(6).toString().trim());
			    System.out.println("fchCrono:"+fchCrono);
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    this.setHoraCronologica(elemento.elementAt(6).toString().trim());
			    hraCrono=(elemento.elementAt(6).toString().trim());
			    System.out.println("hraCrono:"+hraCrono);
			}
			
		}
		t.setObjeto(this);
						
		GeneralTest gt= new GeneralTest();
				
		/*grabdo de log anyerior*/
//		GeneralTest gt = new GeneralTest();
//		gt.setVariables(tarjeta.getVariables());
//		gt.generarLog4(t,usuario,null,tarjeta, request);
		
		if(usuario.getCodigoCic()=="0"){
			gt.generarLog3(t,usuario,Constante.REFRENDO_EXPIRACION_CLAVE_INTERNET,tarjeta, request);
			
		}else{
					
			gt.generarLog7(t,usuario,Constante.REFRENDO_CAMBIO_CLAVE,tarjeta, request);
			
		}
						
		System.out.println("t.getTransactionHost():::"+t.getTransactionHost());
					
		String codApp="";
		String codRet="";
		String flagLog="";
		String consta="";
		
		System.out.println("t.getTransactionHost():::"+t.getTransactionHost());
		System.out.println("ConstanteSesion.HOST_COD_APP:::"+ConstanteSesion.HOST_COD_APP);
		System.out.println("ConstanteSesion.HOST_COD_MSG:::"+ConstanteSesion.HOST_COD_MSG);
		System.out.println("ConstanteSesion.CONSTANCIA_LOG:::"+ConstanteSesion.CONSTANCIA_LOG);
		
		if(ConstanteSesion.FLAG_ESTADO_LOG=="1"){			
			codApp=ConstanteSesion.HOST_COD_APP;
			codRet=ConstanteSesion.HOST_COD_MSG;
			flagLog=ConstanteSesion.FLAG_ESTADO_LOG;
			consta=ConstanteSesion.CONSTANCIA_LOG;
			
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fchLogica=sdf.format(fechaActual);
			fchCrono=sdf.format(fechaActual);
			
			
		}else{
			flagLog=ConstanteSesion.FLAG_ESTADO_LOG;
			consta=ConstanteSesion.CONSTANCIA_LOG;
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fchLogica=sdf.format(fechaActual);
			fchCrono=sdf.format(fechaActual);
			
		}
		
		this.procesoActualizarLog(vInsertLog, t, usuario, codApp, codRet, flagLog, consta, fchLogica,fchCrono,hraCrono);
		
		if(getArrayRuleException()!=null){
		
			throw getArrayRuleException();
					
		}
	}
	
	public void generarClaveInternetOlvido(Transaction t, Usuario usuario, String transaccion, TarjetaImpl tarjeta, HttpServletRequest request) throws Exception{
		
		/************************** insertar log *********************************/
		usuario.setCodigoCic("0");
		usuario.getTarjeta().setTipo("03");
		
		int vInsertLog = this.procesoInsertarLog(t,usuario, request,Constante.CAMBIO_CLAVE_INTERNET_OLVIDO);
		
		/*********************************************************************/
		
	
		Vector mascara = procesar(t);
		//System.out.println("Entro a Bloqueo de Tarjeta");
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
//			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
//				this.setFecha(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
//			    this.setHora(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
//			    this.setCodigo(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
//			    this.setNroOperacion(elemento.elementAt(6).toString());
//			}
		}
		t.setObjeto(this);
		
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog6(t,usuario,Constante.REFRENDO_OLVIDO_CLAVE_INTERNET,tarjeta, request,Constante.CAMBIO_CLAVE_INTERNET_OLVIDO);
		
		/********************* actualizar en log ***********************/
		String fchLogica = "";
		String fchCrono = "";
		String hraCrono = "";
		String codApp="";
		String codRet="";
		String flagLog="";
		String consta="";
				
		if(ConstanteSesion.FLAG_ESTADO_LOG=="1"){			
			codApp=ConstanteSesion.HOST_COD_APP;
			codRet=ConstanteSesion.HOST_COD_MSG;
			flagLog=ConstanteSesion.FLAG_ESTADO_LOG;
			consta=ConstanteSesion.CONSTANCIA_LOG;
			
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fchLogica=sdf.format(fechaActual);
			fchCrono=sdf.format(fechaActual);
		}else{
			flagLog=ConstanteSesion.FLAG_ESTADO_LOG;
			consta=ConstanteSesion.CONSTANCIA_LOG;
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fchLogica=sdf.format(fechaActual);
			fchCrono=sdf.format(fechaActual);
			
		}
		
		tarjeta.procesoActualizarLog(vInsertLog, t, usuario, codApp, codRet, flagLog, consta, fchLogica,fchCrono,hraCrono);
		
		if(getArrayRuleException()!=null){
			
			throw getArrayRuleException();
					
		}
	}
	
public void generarClaveInternetAfiliacion(Transaction t, Usuario usuario, String transaccion, TarjetaImpl tarjeta, HttpServletRequest request) throws Exception{
		
		/************************** insertar log *********************************/
		usuario.setCodigoCic("0");
		usuario.getTarjeta().setTipo("03");
		
		int vInsertLog = this.procesoInsertarLog(t,usuario, request,Constante.CAMBIO_CLAVE_INTERNET_NUEVO);
		
		/*********************************************************************/
	
		Vector mascara = procesar(t);
		//System.out.println("Entro a Bloqueo de Tarjeta");
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
//			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
//				this.setFecha(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
//			    this.setHora(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
//			    this.setCodigo(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
//			    this.setNroOperacion(elemento.elementAt(6).toString());
//			}
		}
		t.setObjeto(this);
					
		GeneralTest gt = new GeneralTest();
		gt.setVariables(tarjeta.getVariables());
		gt.generarLog6(t,usuario,Constante.REFRENDO_GENERACION_CLAVE_INTERNET,tarjeta, request,Constante.CAMBIO_CLAVE_INTERNET_NUEVO);
		
		/********************* actualizar en log ***********************/
		String fchLogica = "";
		String fchCrono = "";
		String hraCrono = "";
		String codApp="";
		String codRet="";
		String flagLog="";
		String consta="";
				
		if(ConstanteSesion.FLAG_ESTADO_LOG=="1"){			
			codApp=ConstanteSesion.HOST_COD_APP;
			codRet=ConstanteSesion.HOST_COD_MSG;
			flagLog=ConstanteSesion.FLAG_ESTADO_LOG;
			consta=ConstanteSesion.CONSTANCIA_LOG;
			
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fchLogica=sdf.format(fechaActual);
			fchCrono=sdf.format(fechaActual);
		}else{
			flagLog=ConstanteSesion.FLAG_ESTADO_LOG;
			consta=ConstanteSesion.CONSTANCIA_LOG;
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fchLogica=sdf.format(fechaActual);
			fchCrono=sdf.format(fechaActual);
			
		}
		
		tarjeta.procesoActualizarLog(vInsertLog, t, usuario, codApp, codRet, flagLog, consta, fchLogica,fchCrono,hraCrono);
		
		if(getArrayRuleException()!=null){
			
			throw getArrayRuleException();
					
		}
	}
	
	public int procesoInsertarLog(Transaction t, Usuario usuario, HttpServletRequest request, String transa){
		
		int valor=0;
		
		try {
			ParametrosElemSegTDC elementos = null;
	
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
	
			GeneracionLogImpl generacionLogImpl = new GeneracionLogImpl();
			GeneracionLogResultado a = null;
			long b = 0;
			long c = 0;
					
			Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
			generacionLogImpl.setP_F32_FECHA_CRONO_OP(fechaActual);
			generacionLogImpl.setP_F32_CANAL("WEB");
			generacionLogImpl.setP_F32_IP_DIRECCION(request.getRemoteAddr());
			generacionLogImpl.setP_F32_MAC("");
			
			generacionLogImpl.setP_F32_NUM_TRANS(transa);//t.transaction
			
			generacionLogImpl.setP_F32_CIC_CLTE(Long.valueOf(usuario.getCodigoCic()));
			generacionLogImpl.setP_F32_TIPO_DOCUMENTO(usuario.getTipoDocumento());
			generacionLogImpl.setP_F32_NUM_DOCUMENTO(usuario.getNumDocumento());
			generacionLogImpl.setP_F32_TIPO_TARJETA(usuario.getTarjeta().getTipo());
			generacionLogImpl.setP_F32_NUM_TARJETA(usuario.getTarjeta().getNumero());
			
			generacionLogImpl.setP_F32_ESTADO("");			
			generacionLogImpl.setP_F32_FLAG_ERROR("");
					
			generacionLogImpl.setP_F32_COD_APP("");
			generacionLogImpl.setP_F32_COD_RET("");
			
			generacionLogImpl.setP_F32_TOKEN("");	
			
			generacionLogImpl.setP_F32_EMAIL_OUT(usuario.getEmail());
			generacionLogImpl.setP_F32_BENEF_OUT(usuario.getNombre());
			
			String tipoToken="";
			
			
			
			if(elementos == null){
				tipoToken="";
				
			}else{
				if(elementos.getTipoElementoSeguridad().equals("5")){
					tipoToken="1";
				}else if(elementos.getTipoElementoSeguridad().equals("6")){
					tipoToken="2";
				}else{
					tipoToken="";
				}
			}
			
			
			generacionLogImpl.setP_F32_TIPO_TOKEN(tipoToken);//1 fisico 2 cdd
			
			generacionLogImpl.setP_F32_NUMLOG(c);
			generacionLogImpl.setPc_coderr("");
			generacionLogImpl.setPc_msgerr("");			
		
			valor = DAOFactory.getGeneracionLogDAO().insertLog(generacionLogImpl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valor;
	}
	
	public void procesoActualizarLog(int valor, Transaction t, Usuario usuario, String codApp, String codRet, String flagLog, String consta, String fchLogica, String fchCrono, String hraCrono){
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			GeneracionLogImpl actualizaLogImpl = new GeneracionLogImpl();
			actualizaLogImpl.setP_F32_NUMLOG((long)valor);
			
			actualizaLogImpl.setP_F32_FECHA_CRONO_OP(sdf.parse(fchCrono));
		
			
			actualizaLogImpl.setP_F32_FLAG_ERROR(flagLog); //0 ok - 1 mal
			
			actualizaLogImpl.setP_F32_COD_APP(codApp);
			actualizaLogImpl.setP_F32_COD_RET(codRet);
			
			actualizaLogImpl.setP_F32_BENEF_OUT(usuario.getNombre());
			actualizaLogImpl.setP_F32_FECHA_LOGICA(fchLogica);
			actualizaLogImpl.setP_F32_CONSTANCIA_SATI(consta);
			
			actualizaLogImpl.setPc_coderr("");
			actualizaLogImpl.setPc_msgerr("");
			
			DAOFactory.getGeneracionLogDAO().updateLog(actualizaLogImpl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void desafiliarClave(Transaction t, Usuario usuario) throws Exception{
		//procesar(t);
		//generarLog(t,usuario,Constante.REFRENDO_DESAFILIA_CLAVE);
	}
	
	public void bloquear(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		//System.out.println("Entro a Bloqueo de Tarjeta");
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setFecha(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    this.setHora(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    this.setCodigo(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			    this.setNroOperacion(elemento.elementAt(6).toString());
			}
		}
		t.setObjeto(this);
		//generarLog(t,usuario,Constante.REFRENDO_BLOQUE_TARJETA);
		//if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	public void generarClaveInternet(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		//System.out.println("Entro a Bloqueo de Tarjeta");
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
//			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
//				this.setFecha(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
//			    this.setHora(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
//			    this.setCodigo(elemento.elementAt(6).toString().trim());
//			}
//			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
//			    this.setNroOperacion(elemento.elementAt(6).toString());
//			}
		}
		t.setObjeto(this);
		//generarLog(t,usuario,Constante.REFRENDO_BLOQUE_TARJETA);
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	public void bloquearDni(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		//System.out.println("Entro a Bloqueo de Tarjeta");
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setFecha(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    this.setHora(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    this.setCodigo(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			    this.setNroOperacion(elemento.elementAt(6).toString());
			}
		}
		t.setObjeto(this);
		//generarLog(t,usuario,Constante.REFRENDO_BLOQUE_TARJETA);
		//if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	
	
	/**
	 * @return Devuelve asociado.
	 */
	public boolean isAsociado() {
		return asociado;
	}
	/**
	 * @param asociado El asociado a establecer.
	 */
	public void setAsociado(boolean asociado) {
		this.asociado = asociado;
	}
	/**
	 * @return Devuelve numero.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero El numero a establecer.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return Devuelve tipo.
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo El tipo a establecer.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return Devuelve clave.
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave El clave a establecer.
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getFecha() {
        return fecha;
    }
	
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
	/**
	 * @return Devuelve tarjetaOculta.
	 */
	public String getTarjetaOculta() {
		return tarjetaOculta;
	}
	/**
	 * @param tarjetaOculta El tarjetaOculta a establecer.
	 */
	public void setTarjetaOculta(String tarjetaOculta) {
		this.tarjetaOculta = tarjetaOculta;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getClaveAfil() {
		return claveAfil;
	}

	public void setClaveAfil(String claveAfil) {
		this.claveAfil = claveAfil;
	}

	public String getIdAfiliacion() {
		return idAfiliacion;
	}

	public void setIdAfiliacion(String idAfiliacion) {
		this.idAfiliacion = idAfiliacion;
	}

	public String getFechaLogica() {
		return fechaLogica;
	}

	public void setFechaLogica(String fechaLogica) {
		this.fechaLogica = fechaLogica;
	}

	public String getFechaCronologica() {
		return fechaCronologica;
	}

	public void setFechaCronologica(String fechaCronologica) {
		this.fechaCronologica = fechaCronologica;
	}

	public String getHoraCronologica() {
		return horaCronologica;
	}

	public void setHoraCronologica(String horaCronologica) {
		this.horaCronologica = horaCronologica;
	}
	
	
	
}