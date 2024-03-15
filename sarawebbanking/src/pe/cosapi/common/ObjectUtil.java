package pe.cosapi.common;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class ObjectUtil {
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
		
	public static String replaceIlegalCharacterToSendMail(String cadenaPadre){
		String[] ilegalStrings= {"&nbsp;","á","é","í","ó","ú","ñ","Á","É","Í","Ó","Ú","Ñ","?"};
		String[] legalStrings= {"","&aacute;","&eacute;","&iacute;","&oacute;","&uacute;","&ntilde;","&Aacute;","&Eacute;","&Iacute;","&Oacute;","&Uacute;","&Ntilde;",""};
		int existe=-1;
		for(int i=0;i<ilegalStrings.length;i++){
			existe=cadenaPadre.indexOf(ilegalStrings[i]);
			 if(existe!=-1){
			 	cadenaPadre=cadenaPadre.replaceAll(ilegalStrings[i],legalStrings[i]);
			 }
		}
		return cadenaPadre;
	}
	
    public static String formatearNumber(BigDecimal importe, int nDecimales) {
		//Preparo un objeto para realizar el formateo
		NumberFormat formateo = NumberFormat.getNumberInstance();
		formateo.setMaximumFractionDigits(nDecimales);
		formateo.setMinimumFractionDigits(nDecimales);
		return formateo.format(importe);
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
			return false;
		}
		
	}
	
	public static boolean isDecimal(String cadena){
		try{
			new BigDecimal(cadena);
			return true;
		}
		catch(Exception e){
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
		monto = monto.setScale(2);
		return ObjectUtil.replaceChar(String.valueOf(monto),'.',"");
	}
	
	
	public static String formatearMontoTrama(String montoC){		
		if(montoC == null)
			return null;
		BigDecimal monto_ = new BigDecimal(ObjectUtil.replaceChar(montoC,',',""));
		monto_ = monto_.setScale(2);
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
					if(k-4<=j){
						cf+=cuenta.charAt(j);
					}
					else{
						cf+='X';
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
	
	public static String fechaToTramaYYYYMMDD(String fechaTrama){
		String trama= replaceChar(fechaTrama,'/',"");
	    String dia  = trama.substring(0,2);
	    String mes  = trama.substring(2,4);
		String anio = trama.substring(4,8);
		
		return anio+mes+dia;
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
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(decimales);
		return bd;
	}	
	
	public static List getComboVacio(){
		List lstCombo = new ArrayList();
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
	
	public static void getParametrosRequest(HttpServletRequest request){
		// System.out.println("imprimiendo valores de request");
		// System.out.println("******************************");
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
				// System.out.println(cadena.toString());					
			}

		}
		// System.out.println("******************************");
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
    public static byte positiveIntToByte( int i )
    {
        if ( i > 127 )
        {
            i -= 256;
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
		else if (tipoDoc.equals("002")||tipoDoc.equals("003")||tipoDoc.equals("008"))
		    doc = "01";
		else if (tipoDoc.equals("004"))
		    doc = "05";
		else if (tipoDoc.equals("006"))
		    doc = "06";
		else if (tipoDoc.equals("007"))
		    doc = "04";
		return doc;
    }
    
    public static String getCodigoTRX(){
        String codeTRX = new StringBuffer().append(getYear()).append(getProcessMonth()).append(getProcessDay()).append(getHHMMSS()).toString();
        return codeTRX;
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
    
}
