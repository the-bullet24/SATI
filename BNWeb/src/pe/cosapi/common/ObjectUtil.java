package pe.cosapi.common;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DateFormat; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPException;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.domain.impl.LimitePrestamoImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.GeneratorKeys;

public class ObjectUtil {
	
	private static LoggerSati log3 = LoggerSati.getInstance(ObjectUtil.class.getName());
	
	public static final ResourceBundle resource = ResourceBundle.getBundle("pe.bn.resources.mensajes");
	public static String getHHMMSSformateado(String horatrama){
		if(horatrama.length()<6){
			horatrama="0"+horatrama;
		}
		String horas= horatrama.substring(0,2);
		String minutos  = horatrama.substring(2,4); 
		String segundos  = horatrama.substring(4,6);
		return horas+":"+minutos+":"+segundos;
	}
	public static String getYYYYMMDDFormateada(String fechaTrama){
		String anio = fechaTrama.substring(0,4);
		String mes  = fechaTrama.substring(4,6); 
		String dia  = fechaTrama.substring(6,8);
		return dia+"/"+mes+"/" + anio;
	}
	
	public static String[] getArrayStrings(String cadena,String delim){
		if (isStringBlank(cadena))
			return new String[0];
		
		StringTokenizer st = new StringTokenizer(cadena,delim);
		String[] array = new String[st.countTokens()];
		int indice = 0;
		while (st.hasMoreTokens()) {
			array[indice] = st.nextToken();
			indice++;
		}
		return array;
	}
	
	public static boolean isStringBlank(String cadena){
		boolean b = false;
		if (cadena == null || cadena.trim().length()==0)
			b = true;
		
		return b;
	}
	
	public static boolean isNumeric(String cadena){
		
		try{
			new Long(cadena);
			return true;
		}
		catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			return false;
		}
		
	}
	
	public static boolean isNumeric1(String cadena){
		
		try{
			new Long(cadena);
			return true;
		}
		catch(NumberFormatException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			return false;
		}
		
	}
	
	public static boolean isDecimal(String cadena){
		try{
			new BigDecimal(cadena);
			return true;
		}
		catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			return false;
		}
	}

	
	public static Vector getVectorbyDicc(String dicc,Vector data) {
		Vector vResultado = null;		
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			vResultado = (Vector) iterator.next();
			if (vResultado.elementAt(0).toString().equals(dicc)){				
				return vResultado; 
			}
		}
		return null;
	}
	
	public static int getYear(){
		Calendar cal = Calendar.getInstance();
		int anio = cal.get(Calendar.YEAR); 
		return anio;
	}
	
	public static int getDay(){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH); 
		return day;
	}
	
	public static int getMonth(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1; 
		return month;
	}
	
	public static String getProcessMonth(){
		int month = getMonth();
		if (String.valueOf(month).length()==1){
			return new StringBuffer("0").append(month).toString();
		}
		return String.valueOf(month);
	}

	public static String formatearCuenta(String cuenta,String formato){
		//tomando en cuenta de que el separador es el simbolo '-'
		if(formato.length()>cuenta.length()){
			String cf = ""; 
			for(int i = 0,j=0 ; i < formato.length();i++){
				if(formato.charAt(i)=='-'){
					cf+='-';
				}
				else{
					cf+=cuenta.charAt(j);
					j++;
				}
			}
			return cf;
		}
		return cuenta;
	}

	public static String formatearMonto(BigDecimal monto){		
		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
		
	}
	
	public static BigDecimal deFormatearMonto(String monto){
		if(monto == null)
			return null;
		BigDecimal monto_ = new BigDecimal(ObjectUtil.replaceChar(monto,',',""));
		return monto_;
		
	}

	public static String formatearMontoTrama(BigDecimal monto){		
		monto = monto.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		return ObjectUtil.replaceChar(String.valueOf(monto),'.',"");
	}
	
	public static String formatearMontoTrama(String montoC){		
		if(montoC == null)
			return null;
		BigDecimal monto_ = new BigDecimal(ObjectUtil.replaceChar(montoC,',',""));
		monto_ = monto_.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		return ObjectUtil.replaceChar(String.valueOf(monto_),'.',"");
	}
	
	
	public static String formatearMonto(BigDecimal monto, String formato){		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat(formato);
		formateador.setDecimalFormatSymbols(simbolos);		
		return formateador.format(monto);		
		
	}

	public static String ocultarCuenta(String cuenta,String formato){
		//tomando en cuenta de que el separador es el simbolo '-'
		if(formato.length()>cuenta.length()){
			String cf = ""; 
			for(int i = 0,j=0,k=cuenta.length() ; i < formato.length();i++){
				if(formato.charAt(i)=='-'){
					cf+='-';
				}
				else{
					if(j<4){
						cf+=cuenta.charAt(j);
					}
					else if(k-4<=j){
						cf+=cuenta.charAt(j);
					}
					else{
						cf+='*';
					}
						
					j++;
				}
			}
			return cf;
		}
		return cuenta;
	}
	public static String ocultarTarjetaCredito(String cuenta,String formato){
		//tomando en cuenta de que el separador es el simbolo '-'
		if(formato.length()>cuenta.length()){
			String cf = ""; 
			for(int i = 0,j=0,k=cuenta.length() ; i < formato.length();i++){
				if(formato.charAt(i)=='-'){
					cf+='-';
				}
				else{
					if(j<0){
						cf+=cuenta.charAt(j);
					}
					else if(k-4<=j){
						cf+=cuenta.charAt(j);
					}
					else{
						cf+='*';
					}
						
					j++;
				}
			}
			return cf;
		}
		return cuenta;
	}
	
	/**
	 * Metodo encargado de entregarte un objeto calendar seteando los siguientes atributos
	 * @param year, año ejem:1996
	 * @param month mes:01
	 * @param date dia:19
	 * @param hour hora 
	 * @param minute
	 */
	public static Calendar getCalendar(int year,int month,int date,int hour,int minute){
		month = month -1;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date, hour, minute);
		return cal;
	}
	
	public static Calendar getCalendar(int year,int month,int day){
		month = month -1;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal;
	}
	
	public static String getProcessDay() {
		int day = getDay();
		if (String.valueOf(day).length()==1){
			return new StringBuffer("0").append(day).toString();
		}
		return String.valueOf(day);
	}
	
	/**
	 * Remplazara la cadena desde la posicion  posini-1 hasta posfin-1
	 * ejemplo.
	 * cadena = 12345678 funcion (cadena,4,"**",6)
	 * cadenaFinal = 123**678
	 */
	public static String replaceString(String cadena,int posini,String cadenaReplace,int posfin){
		String tramitaTemp = "";
		tramitaTemp = cadena.substring(0, posini)+cadenaReplace+cadena.substring(posfin, cadena.length());
		return tramitaTemp;
	}
	

	public static String replaceChar(String cadena,char car1,String car2){
		
		
		if(cadena.lastIndexOf(car1)==-1) 
			return cadena;
		String tramitaTemp = "";
		for(int i = 0 ; i < cadena.length(); i++){
			if(cadena.charAt(i)== car1 ){
				tramitaTemp = tramitaTemp + car2;
			}
			else{
				tramitaTemp = tramitaTemp + cadena.charAt(i);
			}
		}
		return tramitaTemp;
	}
	
	public static String setearCaracterLeft(String cadena,char c,int num){
		String temp = "";
		if (cadena==null)
			cadena = "";
		
		for (int i=0;i<num;i++)
			temp+=c;
		temp+=cadena;
		
		return temp;
	}
	
	public static String setearCaracterRight(String cadena,char c,int num){
		String temp = "";
		
		if (cadena==null)
			cadena = "";
		
		for (int i=0;i<num;i++)
			temp+=c;
		cadena+=temp;
		return cadena;
	}

	public static String setearEspacioRight(String cadena,int num){
		String temp = "";
		
		if (cadena==null)
			cadena = "";
		
		
		for (int i=0;i<num-cadena.length();i++)
			temp+=" ";
		cadena+=temp;
		return cadena;
	}
	public static Timestamp getFechaTimestamp(String anio,String mes,String dia) {
		int year  = Integer.parseInt(anio);
		int month = Integer.parseInt(mes);
		int day   = Integer.parseInt(dia);
		Calendar cal = getCalendar(year,month,day);
		Timestamp t = new Timestamp(cal.getTime().getTime());
		return t;
	}
	
	public static Vector getVectorComponent(String nombreComponete,String valueComponent){
		Vector vector_ = new Vector();
		vector_.addElement(nombreComponete);
		vector_.addElement(valueComponent);
		return vector_;
	}
	
	public static Timestamp tramaToTimestamp(String fechaTrama){
		String anio = fechaTrama.substring(0,4);
		String mes  = fechaTrama.substring(4,6); 
		String dia  = fechaTrama.substring(6,8); 
		int year  = Integer.parseInt(anio);
		int month = Integer.parseInt(mes);
		int day   = Integer.parseInt(dia);
		Calendar cal = getCalendar(year,month,day);
		Timestamp t = new Timestamp(cal.getTime().getTime());
		return t;
	}
		
	public static Timestamp tramaToTimestampddmmyyyy(String fechaTrama){
	    String dia  = fechaTrama.substring(0,2);
	    String mes  = fechaTrama.substring(2,4);
		String anio = fechaTrama.substring(4,8);
		
		int year  = Integer.parseInt(anio);
		int month = Integer.parseInt(mes);
		int day   = Integer.parseInt(dia);
		Calendar cal = getCalendar(year,month,day);
		Timestamp t = new Timestamp(cal.getTime().getTime());
		return t;
	}
	
	public static Timestamp tramaTimestampddmmyyyy(String fechaTrama){
	    String dia  = fechaTrama.substring(0,2);
	    String mes  = fechaTrama.substring(3,5);
		String anio = fechaTrama.substring(6,10);
		
		int year  = Integer.parseInt(anio);
		int month = Integer.parseInt(mes);
		int day   = Integer.parseInt(dia);
		Calendar cal = getCalendar(year,month,day);
		Timestamp t = new Timestamp(cal.getTime().getTime());
		return t;
	}
	
	public static Timestamp tramaToTimestampFormateado(String s) throws ParseException{
		Timestamp outDate = null;
		if(s != null && s.length() == 10)
		{
			long time = new SimpleDateFormat("dd/MM/yyyy").parse(s).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;				
	}

	public static String timestampToString(Timestamp t, String formato ) throws ParseException{
		SimpleDateFormat format = 
            new SimpleDateFormat(formato);
      String fecha = (format.format(new Date(t.getTime())));
      return fecha;
		
	}
	
	
	public static String timestampToTramaAAAAMMDD(Timestamp fecha) throws ParseException{
		if(fecha != null){
			 return (fecha.getYear()+1900)+""+ObjectUtil.setearCaracterLeft((fecha.getMonth()+1)+"",'0',2-(((fecha.getMonth()+1)+"").length()))+""+ObjectUtil.setearCaracterLeft((fecha.getDate())+"",'0',2-(((fecha.getDate())+"").length()));
		}
		
		return null;				
	}
		
	public static String timestampToTramaDDMMAAAA(Timestamp fecha) throws ParseException{
		if(fecha != null){
			 return ObjectUtil.setearCaracterLeft((fecha.getDate())+"",'0',2-(((fecha.getDate())+"").length()))+""+ObjectUtil.setearCaracterLeft((fecha.getMonth()+1)+"",'0',2-(((fecha.getMonth()+1)+"").length()))+(fecha.getYear()+1900);
		}
		
		return null;				
	}

	public static Timestamp tramaToTimestamp(String s, String format) throws ParseException{
		Timestamp outDate = null;
		if(s != null && s.length() == 10)
		{
			long time = new SimpleDateFormat(format).parse(s).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;				
	}

	public static BigDecimal tramaToBigDecimal(String numeroTrama){		
		
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		numeroTrama = numeroTrama.substring(0,13)+"."+numeroTrama.substring(13);
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(3);
		return bd;
	}
	
public static String tramaToString(String numeroTrama){		
		
	String respuesta="";
		if(numeroTrama==null)
			return respuesta;
		
		if(Integer.parseInt(numeroTrama)==0)
			
			return respuesta;
	
		else{
			return ""+Integer.parseInt(numeroTrama);
		}
	}

public static String tramaToString1(String numeroTrama){		
	
	String respuesta="";
		if(numeroTrama==null)
			return respuesta;
		
		if(Long.parseLong(numeroTrama)==0)
			
			return respuesta;
	
		else{
			return ""+Long.parseLong(numeroTrama);
		}
	}
		
	public static BigDecimal tramaToBigDecimal(String numeroTrama, int decimales){			
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		int enteros = 15 - decimales; 
		numeroTrama = numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(decimales);
		return bd;
	}	
	
	public static BigDecimal tramaToBigDecimal1(String numeroTrama, int decimales){			
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		int enteros = 15 - decimales; 
		numeroTrama = numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
	
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(decimales);
	
		return bd;
	}	
	
	public static BigDecimal tramaToBigDecimalMov(String numeroTrama, int decimales){
	    String simbolo = numeroTrama.substring(numeroTrama.length()-1,numeroTrama.length());
	    numeroTrama = numeroTrama.substring(0,numeroTrama.length()-1);
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		int enteros = 15 - decimales; 
		if(!simbolo.equals("-")){
			numeroTrama = numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
		}
		else{
			numeroTrama = "-"+numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
		}
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(decimales);
		return bd;
	}	
	
	public static BigDecimal tramaToBigDecimal(String numeroTrama, int decimales, String simbolo){			
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		int enteros = 15 - decimales; 
		if(!simbolo.equals("-")){
			numeroTrama = numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
		}
		else{
			numeroTrama = "-"+numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
		}
		BigDecimal monto = new BigDecimal(""+numeroTrama);
		BigDecimal bd = new BigDecimal("0.00");
		bd = bd.add(monto);
		//bd.setScale(decimales);
		return bd;
	}	
	
	
	
	public static List getComboVacio(){
		List lstCombo = new ArrayList();
		ComboUtil cmb = new ComboUtil();
		cmb.setCodigo("");
		cmb.setDescripcion(Constante.SELECCIONE);
		lstCombo.set(0,cmb);
		return lstCombo;
	}
	
	public static Vector getVectorBlankCuentas(HttpServletRequest request){
		Vector v = new Vector();
		
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(request.getRemoteAddr());
		return v;
	}
	
	public static Vector getVectorBlankCuentas(String remoteAddress){
		Vector v = new Vector();
		
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		return v;
	}
	
	public static Vector getVectorBlankCuentas(String remoteAddress,String opt){
		Vector v = new Vector();
		
		v.addElement(opt);
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		return v;
	}
	
	
	public static String ASCII2UTF8(String cadenaASCII) throws Exception{
		if(cadenaASCII==null)
			return null;
		char[] caracteres = cadenaASCII.toCharArray();
		byte[] bytes = cadenaASCII.getBytes();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < caracteres.length; i++) {
			String carASCII = String.valueOf(caracteres[i]);
			String carUTF 	= new String(new byte[]{bytes[i]},"UTF-8");
			if(carASCII.equals(carUTF)){
				sb.append(carASCII);
			}
			else{
				int car = (int)caracteres[i];				
				sb.append("&#x").append(new String(Integer.toHexString(car)).toUpperCase()).append(";");				
				//sb.append("&#").append((int)caracteres[i]);
			}
		}
		return sb.toString();
	}
	
	public static String cortarCadena(String cadena, int posiciones){
		if(cadena.length()>posiciones){
			return cadena.substring(0,posiciones); 
		}
		return cadena;
	}
	
	public static Object formatearObjetoWAP(Object o) throws Exception{
		if(o instanceof PrestamoImpl){
			PrestamoImpl prestamo = (PrestamoImpl)o;
			String cliente = prestamo.getCliente();
			prestamo.setCliente(ObjectUtil.ASCII2UTF8(cliente));
			o = prestamo;
		}
		else if(o instanceof UsuarioImpl){
			UsuarioImpl usuario = (UsuarioImpl)o;
			String nombre = usuario.getNombre();
			usuario.setNombre(ObjectUtil.ASCII2UTF8(nombre));
			o = usuario;
		}
		else if(o instanceof LimitePrestamoImpl){
			LimitePrestamoImpl limitePrestamoImpl = (LimitePrestamoImpl)o;
			String leyenda = limitePrestamoImpl.getLeyenda();
			limitePrestamoImpl.setLeyenda(ObjectUtil.ASCII2UTF8(leyenda));
			o = limitePrestamoImpl;
		}
		else if(o instanceof String){
			return ObjectUtil.ASCII2UTF8((String)o);
		}
		return o;
	}
	
	public static void parameter2Atributte(HttpServletRequest request,String nombre){
		request.setAttribute(nombre,request.getParameter(nombre));
	}
	
	public static String getFechaActual(){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		Timestamp t = new java.sql.Timestamp(cal.getTime().getTime()); 					 			
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	public static String getFechaActual2(){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		Timestamp t = new java.sql.Timestamp(cal.getTime().getTime()); 					 			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}	

	
	public static String getMensajeError(Object e){
		ResourceBundle resource = ResourceBundle.getBundle("pe.bn.resources.mensajes");

		if( e instanceof InvocationTargetException) {
			Throwable te = ((InvocationTargetException)e).getTargetException().getCause();			
			if(te == null) 
				te = ((InvocationTargetException)e).getTargetException();			
			if (te instanceof ArrayRuleException){
				ActionError[] arrAE = ((ArrayRuleException)te).getActionErrorsArray();				
				for (int i = 0; i < arrAE.length; i++){
					MessageFormat message = new MessageFormat(resource.getString(arrAE[i].getKey()));
					String respuesta = message.format(arrAE[i].getValues());
					respuesta = respuesta.substring(4,respuesta.length()-5);
					return respuesta;				 
				}
			}
			else if(te instanceof SOAPException){				
				ActionErrors errors = new ActionErrors();
				String error = "error inesperado, intente de nuevo";				
				try{
					error = BNAplicacion.getInstance().getMensajePorCodigo(ConstanteError.CODIGO_GRUPO_ERROR_WEB_SERVICE,"01524").elementAt(2).toString();
				}
				catch (Exception e1) {
					log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
				}
				return error;			
			}
			else{
				return "error inesperado, intente de nuevo";
				//return ((Exception)e).getMessage();
			}			
		}
		else if(e instanceof ArrayRuleException) {
			
			ActionError[] arrAE = ((ArrayRuleException)e).getActionErrorsArray();
			for (int i = 0; i < arrAE.length; i++){
				MessageFormat message = new MessageFormat(resource.getString(arrAE[i].getKey()));
				String respuesta = message.format(arrAE[i].getValues());
				respuesta = respuesta.substring(4,respuesta.length()-5);
				return respuesta;				 
			}	
		}
		else{
			return "error inesperado, intente de nuevo";
		}
		
		return "error inesperado, intente de nuevo";
		
	}
	
	public static String getMensajeErrorWAP(Object e){
		try{
			return (String)ObjectUtil.formatearObjetoWAP(ObjectUtil.getMensajeError(e));
		}
		catch(Exception i){
			log3.error(i,Constante.ERR_LOGICA_NEGOCIO,"");
			return "Error de la codificacion del error";
		}
	}

	public static void getParametrosRequest(HttpServletRequest request){
		if (request != null) {
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();

				StringBuffer cadena = new StringBuffer(paramName + " : ");

				String[] paramValues = request
						.getParameterValues(paramName);

				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() == 0){
						cadena.append("null");
					}							
					else{
						cadena.append(paramValue);
					}						
				} else {
					for (int i = 0; i < paramValues.length; i++) {
						cadena.append(paramValues[i] + ", ");
					}
				}								
			}

		}
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
    public static int byteToPositiveInt( byte b )
    {
        int i = b;
        if ( i < 0 )
        {
            i += 256;
        }
        return i;
    }	
	
    /**
     * 
     * @param i
     * @return
     */
    public static byte positiveIntToByte( int i ){
        if ( i > 127 ){
            i = i - 256;
        }
        return (byte) i;
    }
    
    public static String getCodMonedaxCuenta(String cuenta){
    	String codigo = cuenta.substring(0,2);
    	String result = "";
    	if (Constante.COD_CUENTA_CTS_ME.equals(codigo)){
    		result = Constante.MONEDA_DOLAR;
    	}else if(Constante.COD_CUENTA_CTS_MN.equals(codigo)){
    		result = Constante.MONEDA_SOL;
    	}else if(Constante.COD_CUENTA_AHORROS_ME.equals(codigo)){
    		result = Constante.MONEDA_DOLAR;
    	}else if(Constante.COD_CUENTA_AHORROS_MN.equals(codigo)){
    		result = Constante.MONEDA_SOL;
    	}else if(Constante.COD_CUENTA_CORRIENTE_ME.equals(codigo)){
    		result = Constante.MONEDA_DOLAR;
    	}else if(Constante.COD_CUENTA_CORRIENTE_MN.equals(codigo)){
    		result = Constante.MONEDA_SOL;
    	}
    	return result;
    }

    public static String procesarLongitud(int longitud) {
		String strTemp = String.valueOf(longitud);
		strTemp = setearCaracterLeft(strTemp,'0',(4-strTemp.length()));
		return strTemp;
	}
    
    public static Timestamp stringToTimestamp(String dia,String mes,String anio) throws ParseException{
		Timestamp outDate = null;
		String sTemp = dia +"/" + mes + "/" + anio;
		if( !isStringBlank(sTemp) ){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			simpleDateFormat.setLenient(false);
			long time = simpleDateFormat.parse(sTemp).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}
	
    public static Timestamp stringTramaToTimestamp(String trama) throws ParseException{
    	String anio = trama.substring(0,4);
		String mes  = trama.substring(4,6); 
		String dia  = trama.substring(6,8); 
		
		String TramaTemp = dia+"/"+mes+"/"+anio;
		
		Timestamp outDate = null;
		if( !isStringBlank(TramaTemp) ){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			simpleDateFormat.setLenient(false);
			long time = simpleDateFormat.parse(TramaTemp).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}
    
    public static String getCodDocBeneficiarioTIB(String tipoDoc){
        String doc = null;
        if (tipoDoc.equals("001"))
		    doc = "02";
		else if (tipoDoc.equals("002")||tipoDoc.equals("008"))
		    doc = "01";
		else if (tipoDoc.equals("003")||tipoDoc.equals("004"))
		    doc = "05";
		else if (tipoDoc.equals("006"))
		    doc = "06";
		else if (tipoDoc.equals("007")||tipoDoc.equals("005"))
		    doc = "04";
		return doc;
    }
    
    public static String getNombreMes(int mes){
    	mes = mes + 1;
    	switch(mes){
			case 1:
				return "Enero";				
			case 2:
				return "Febrero";
			case 3: 
				return "Marzo";
			case 4: 
				return "Abril";
			case 5: 
				return "Mayo";
			case 6: 
				return "Junio";
			case 7: 
				return "Julio";
			case 8: 
				return "Agosto";
			case 9: 
				return "Setiembre";
			case 10: 
				return "Octubre";
			case 11: 
				return "Noviembre";
			case 12: 
				return "Diciembre";
			default:
				return " ";
    	}
    }
    
    public static String getFechaBanco(){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		Timestamp ts = new java.sql.Timestamp(cal.getTime().getTime());
		String fecha = String.valueOf(ts.getDate());
		fecha = setearCaracterLeft(fecha,'0',2-fecha.length())+" "+getNombreMes(ts.getMonth())+" "+(ts.getYear()+1900);
    	return fecha;
    }
    
    public static String getHHMMSS(){
		Calendar cal = Calendar.getInstance();
		String sMinute 	= "";
		String sHora 	= "";
		String sSecond 	= "";
		
		int minute  = cal.get(Calendar.MINUTE);
		int hora_of_day = cal.get(Calendar.HOUR_OF_DAY);
		int second = cal.get(Calendar.SECOND);
		
		if ( String.valueOf(minute).length()==1 )
			sMinute = new StringBuffer("0").append(minute).toString();
		else 
			sMinute = String.valueOf(minute);
		
		if ( String.valueOf(minute).length()==1 )
			sHora = new StringBuffer("0").append(hora_of_day).toString();
		else 
			sHora = String.valueOf(hora_of_day);
		
		if ( String.valueOf(second).length()==1 )
			sSecond = new StringBuffer("0").append(second).toString();
		else 
			sSecond = String.valueOf(second);
		
		return new StringBuffer(sHora).append(sMinute).append(sSecond).toString();
	}
    public static String getHHMMSSformateado(){
		Calendar cal = Calendar.getInstance();
		String sMinute 	= "";
		String sHora 	= "";
		String sSecond 	= "";
		
		int minute  = cal.get(Calendar.MINUTE)*1;
		int hora_of_day = cal.get(Calendar.HOUR_OF_DAY)*1;
		int second = cal.get(Calendar.SECOND)*1;
		
		if ( String.valueOf(minute).length()==1 )
			sMinute = new StringBuffer("0").append(minute).toString();
		else 
			sMinute = String.valueOf(minute);
		
		if ( String.valueOf(hora_of_day).length()==1 )
			sHora = new StringBuffer("0").append(hora_of_day).toString();
		else 
			sHora = String.valueOf(hora_of_day);
		
		if ( String.valueOf(second).length()==1 )
			sSecond = new StringBuffer("0").append(second).toString();
		else 
			sSecond = String.valueOf(second);
		
		return sHora+":"+sMinute+":"+sSecond;
	}
    
    /**
     * Metod que verifica si la fecha esta en un rango de fecha
     * @return
     */
    public static boolean getRangoFecha(java.util.Date dFechaMenor, java.util.Date dFechaMayor, java.util.Date dFechaActual){
        boolean valida = false;
        java.util.Calendar cal = java.util.Calendar.getInstance();
		Timestamp t = new java.sql.Timestamp(cal.getTime().getTime());
		
		//System.out.println("***--- dFechaMenor:"+dFechaMenor);
		//System.out.println("***--- dFechaMayor:"+dFechaMayor);
		//System.out.println("***--- dFechaActual:"+dFechaActual);
        
		if (dFechaActual.after(dFechaMenor) && dFechaActual.before(dFechaMayor)) {
		    valida = true;
		}
        
        return valida;
    }
    
    public static boolean getExisteCodigo(String codErrorDD){
        boolean fExiste = false;
        String[] arregloCodigo = new String[8];
        arregloCodigo[0] = "DD3024"; 
        arregloCodigo[1] = "DD3002";
        arregloCodigo[2] = "DD3007";
        arregloCodigo[3] = "DD3060";
        arregloCodigo[4] = "DD3064";
        arregloCodigo[5] = "DD3065";
        arregloCodigo[6] = "DD3066";
        arregloCodigo[7] = "DD3071";
        
        for (int i = 0; i < arregloCodigo.length; i ++) {
            if  (codErrorDD.equals(arregloCodigo[i])){
                fExiste = true;
            }
        }
        return fExiste;
    }
    
    /**
     * Metodo que te devuelve el codigo de transaccion del web service que es diferente al codigo de trx enviado a HOST
     * @return
     */
    public static String getCodigoTRX(){
      String codeTRX = new StringBuffer().append(getYear()).append(getProcessMonth()).append(getProcessDay()).append(getHHMMSS()).toString();
      return codeTRX;
    }
    
    public static String getHora(){
        Calendar cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        String temp = String.valueOf(hora);
        if (temp.length()==1)
            temp = "0"+temp;
        return temp;
    }
    
    public static String getMinuto(){
        Calendar cal = Calendar.getInstance();
        int minuto = cal.get(Calendar.MINUTE);
        String temp = String.valueOf(minuto);
        if (temp.length()==1)
            temp = "0"+temp;
        return temp;
    }
    
    public static String getSegundo(){
        Calendar cal = Calendar.getInstance();
        int segundo = cal.get(Calendar.SECOND);
        String temp = String.valueOf(segundo);
        if (temp.length()==1)
            temp = "0"+temp;
        return temp;
    }
    
    public static boolean isInteger(String value) {
    	try {
    		if (value.charAt(0) == '-') {
    			Integer.parseInt(value.substring(1));
    		} else {
    			Integer.parseInt(value);
    		}
    	} catch (Exception e) {
    		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
    		return (false);
    	}
    	return (true);
    }
    public static String replaceIlegalCharacterToSendMail(String cadena){
		char[] ilegalcharacters= {'á','é','í','ó','ú','ñ','Á','É','Í','Ó','Ú','Ñ'};
		String[] legalcharacters= {"&aacute;","&eacute;","&iacute;","&oacute;","&uacute;","&ntilde;","&Aacute;","&Eacute;","&Iacute;","&Oacute;","&Uacute;","&Ntilde;"};
		String c="", cadenalegal="";
		
		for(int i=0; i<cadena.length();i++){
			c= "" + cadena.charAt(i);
			for(int j=0;j<ilegalcharacters.length;j++){
				if(c.equals(""+ilegalcharacters[j])){
					c=legalcharacters[j];
					break;
				}
			}
			cadenalegal+=c;
		}
		return cadenalegal;
	}
    
    public static String tramaEnBlanco(String cadena){		
		
    	String respuesta="";
    		if(cadena==null)
    			return respuesta;
    		
    		if(cadena.trim().equals(""))
    			{respuesta = "---";
    			return respuesta;
    		}
    		else{
    			if(Long.parseLong(cadena) == 0){
    				respuesta = "---";
        			return respuesta;
    			}
    			else{
    				respuesta = cadena;
        			return respuesta;
    			}
    			
    			}
    	
    	
    	}
    
   public static String convierteDiscadoTel(String cadena){		
			  
    	String respuesta="";
    	
    		if(cadena.substring(0,2).equals("01")){
    		 	respuesta = cadena.substring(0,2) +"-"+cadena.substring(2);
    			return respuesta;
    		}
    	
    		else{
    				if(cadena == "---"){
    					respuesta = cadena;
    					return respuesta;	
    				}
    				else{
    					respuesta = cadena.substring(0,3) +"-"+cadena.substring(3);
            			return respuesta;	
    				}
    				
    			
    			}
    	
   }
    	
   public static String getTipoDocumento(int mes){
  
   	switch(mes){
			case 1:
				return "DNI";				
			case 2:
				return "RUC";
			case 3: 
				return "FF.PP.";
			case 4: 
				return "FF.AA.";
			case 5: 
				return "PASAPORTE";
			case 6: 
				return "CARNET EXTRANJERIA";
		
			default:
				return " ";
   	}
   }
   
   public static String convierteMayusculaPriLetra(String cadena){		
		  
	   String direccion1 = cadena.substring(0,1).toUpperCase();
	   String direccion2 = cadena.substring(1).toLowerCase();
	   
	   String dirConcatenada = direccion1+direccion2;
	
   	char[] caracteres = dirConcatenada.toCharArray();
   	
    for (int i = 0; i < cadena.length()- 2; i++)   
    {
 
    	if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')     
    		// Reemplazamos     
    		caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
    }
    

   return new String(caracteres);
    
   	}
   
   public static BigDecimal tramaToPorcentaje(String numeroTrama){		
		
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		numeroTrama = numeroTrama.substring(0,8)+"."+numeroTrama.substring(8);
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(8);
		return bd;
	}
   
   public static BigDecimal tramaToPorcentajeDesgravamen(String numeroTrama){		
		
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = ObjectUtil.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		numeroTrama = numeroTrama.substring(0,8)+"."+numeroTrama.substring(8);
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(8);
		return bd.multiply(new BigDecimal(100));
	}
   
   public static Timestamp tramaToTimestampyyyymmdd(String fechaTrama){
		  
		String anio = fechaTrama.substring(0,4);
		String mes  = fechaTrama.substring(4,6);
		String dia  = fechaTrama.substring(6,8);
		 
		
		int year  = Integer.parseInt(anio);
		int month = Integer.parseInt(mes);
		int day   = Integer.parseInt(dia);
		Calendar cal = getCalendar(year,month,day);
		Timestamp t = new Timestamp(cal.getTime().getTime());
		return t;
	}
   
   public static Timestamp stringTramaToTimestampConver(String trama) throws ParseException{
	    
		
		String TramaTemp = trama;
		
		Timestamp outDate = null;
		if( !isStringBlank(TramaTemp) ){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			simpleDateFormat.setLenient(false);
			long time = simpleDateFormat.parse(TramaTemp).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}
   
   public static  String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		String  valorClave="";
		try {
			GeneratorKeys gen = new GeneratorKeys();
			Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
			valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		} catch (RuntimeException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    e.printStackTrace();
		}
		return valorClave;
	}
   
   //SOLO SE UTILIZA PARA CADUCIDAD DEBIO A QUE EXISTE MAS DE DOS TECLADOS
   public static  String  getClaveDesencriptadaCDD(String passEncript, HttpServletRequest request){
		String  valorClave="";
		try {
			GeneratorKeys gen = new GeneratorKeys();
			Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
			valorClave = gen.getClave(mapa,request.getParameter("hdnValueCDD"),passEncript);
		} catch (RuntimeException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    e.printStackTrace();
		}
		return valorClave;
	}
}
