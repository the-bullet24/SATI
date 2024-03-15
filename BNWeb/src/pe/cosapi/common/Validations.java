package pe.cosapi.common;

import java.util.Vector;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * This type was created in VisualAge.
 */
public class Validations {
	private static LoggerSati log3 = LoggerSati.getInstance(Validations.class.getName());
	
	private String errorGenerico;
	
	private BNAplicacion aplicacion; 
/** 
 * Validations constructor comment.
 */
public Validations() {
	super();
	aplicacion = BNAplicacion.getInstance();
	errorGenerico = ConstanteError.GENERICO;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param account java.lang.String
 */
public String checkAccountFormat(String account, Vector masc,Vector cuentas) throws Exception{
	String temp="";
	int a=0;
	
	
	for (int i=0;i<masc.size();++i)
	{
		if (((Vector)masc.elementAt(i)).elementAt(1).equals(account))
			{
				temp=((Vector)masc.elementAt(i)).elementAt(6).toString();
				a=i;
			}
	}

	while (temp.indexOf("-")>0)
	{
		temp=temp.substring(0,temp.indexOf("-"))+temp.substring(temp.indexOf("-")+1);
	}
	
	temp=temp.substring(0,4)+"-"+temp.substring(4,8)+"-"+temp.substring(8,12)+"-"+temp.substring(12,16);
	((Vector)masc.elementAt(a)).addElement(temp);
	return temp;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param value java.lang.String
 */
public String checkValueFormat(String value, Vector masc,Vector cuentas) throws Exception{
	String temp="";
	int a=0;
	for (int i=0;i<masc.size();++i)
	{
		if (((Vector)masc.elementAt(i)).elementAt(1).equals(value))
			{
				temp=((Vector)masc.elementAt(i)).elementAt(6).toString();
				a=i;
			}
	}
	java.text.DecimalFormat df=new java.text.DecimalFormat("¤###,###,###,###,###,###",new java.text.DecimalFormatSymbols(java.util.Locale.US));
	
	((Vector)masc.elementAt(a)).addElement(df.format(new Long(temp)));
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dat1 java.lang.String
 * @param dat2 java.lang.String
 * @param data Vector
 */
public String compareStrings(String dat1, String dat2, Vector data,Vector cuentas) {
	try{
	if (!Transaction.searchValuebyDicc(dat1,data).equals(Transaction.searchValuebyDicc(dat2,data)))
	{
		return "ERROR:_Los_valores_de_los_campos:_"+Transaction.searchDescriptionbyDicc(dat1,data).replace(' ','_')+"_y_"+Transaction.searchDescriptionbyDicc(dat2,data).replace(' ','_')+"~07419";
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	}
	return "";
}
/**
 * Compara 2 cadenas y retorna un error si son iguales.
 * @return java.lang.String
 * @param dat1 java.lang.String
 * @param dat2 java.lang.String
 * @param data Vector
 */
public String compareStringsEquals(String dat1, String dat2, Vector data,Vector cuentas) {
	try{
	if (Transaction.searchValuebyDicc(dat1,data).equals(Transaction.searchValuebyDicc(dat2,data)))
	{
		return "ERROR:_Los_valores_de_los_campos:_"+Transaction.searchDescriptionbyDicc(dat1,data).replace(' ','_')+"_y_"+Transaction.searchDescriptionbyDicc(dat2,data).replace(' ','_')+"_deben_ser_diferentes~";
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String formatData(String dat1, Vector data, Vector cuentas) {
	try {
		for (int i = 0; i < data.size(); i++) {
			String dato = ((Vector) data.elementAt(i)).elementAt(6).toString();
			String form = ((Vector) data.elementAt(i)).elementAt(5).toString();
			if (form.indexOf("#") > 0)
				 ((Vector) data.elementAt(i)).addElement(formatNumber(dato, form, data, cuentas));
			else {
				if (form.indexOf("-") > 0)
					 ((Vector) data.elementAt(i)).addElement(formatProd(dato, form, data, cuentas));
				else {
					((Vector) data.elementAt(i)).addElement(dato);
				}
			}
		}
	} catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String formatNumber(String dat,String format, Vector data,Vector cuentas) {
			try{
				int temp=0;
				if (format.indexOf(".")>0)
				temp=2;
				java.text.DecimalFormat df1=new java.text.DecimalFormat(format);
				java.text.NumberFormat df=java.text.DecimalFormat.getCurrencyInstance(java.util.Locale.US);
				//df.setDecimalFormatSymbols(new java.text.DecimalFormatSymbols(java.util.Locale.ENGLISH));
				df.setMaximumFractionDigits(temp);
				return df.format(new Long(dat).longValue());
			}
			catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
	return "";
		}
/**
 * Formatea el Producto a 9999-9999-9999-9999
 * @return java.lang.String
 * @param dic java.lang.String
 *
 * @01      YMC 20001207 Corrección del error cuando el producto no trae Datos
 */
public String formatProd(String dat, String format, Vector data, Vector cuentas) {
	if (dat.length()==12)
	dat="0000"+dat;
	
	String resultado = "";

		// @01 A02
	if (dat.length() < 5)
		return resultado;

	try {
		int posicion = 0;
		for (int i = 0; i < format.length(); i++) {
			if (format.substring(i, i + 1).equals("#")) {
				resultado = resultado + dat.substring(posicion, posicion + 1);
				posicion++;
			} else {
				resultado = resultado + format.substring(i, i + 1);
			}
		}
		//return resultado;
	} catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		System.out.println("Format product = "+e.getMessage());
	}
	return resultado;
	//return dat.substring(0,4)+"-"+dat.substring(4,8)+"-"+dat.substring(8,12)+"-"+dat.substring(12) ;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public static String unformat(String dat1) {
	String temp="";
	try {
		char a[]=dat1.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (Character.isDigit(a[i]) || a[i]=='.')
				temp=temp+a[i];
		}
	} catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		System.out.println("Validations.unformat: "+e.getMessage());
	}
	return temp;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public static String unformat1(String dat1) {
	String temp="";
	try {
		char a[]=dat1.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (Character.isDigit(a[i]))
				temp=temp+a[i];
		}
	} catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		System.out.println("Validations.unformat1: "+e.getMessage());
	}
	return temp;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String validateAccount(String dic,Vector data,Vector cuentas) {
	try{
	if (dic.equals("0") || dic.equals(""))
	{
		for (int j=0;j<data.size();++j)
		{
			String form=((Vector)data.elementAt(j)).elementAt(5).toString();
			String dato=((Vector)data.elementAt(j)).elementAt(6).toString();
			if (form.indexOf("-")>0)
			{
				if (dato.length()!=16)
					//return String.valueOf(form.indexOf("-"));
					return "ERROR:_El_campo_"+((Vector)data.elementAt(j)).elementAt(2).toString().replace(' ','_')+"~07146";


				String extract=dato.substring(0,dato.length()-1);
				int cont=0;
				int num=0;
				int sw=0;
				for (int i=0;i<extract.length();++i)
				{
					if (sw==0)
						num=Integer.valueOf(extract.substring(i,i+1)).intValue()*2;
					else
						num=Integer.valueOf(extract.substring(i,i+1)).intValue()*1;
					if (new Integer(num).toString().length()==2)
						num=Integer.valueOf(new Integer(num).toString().substring(0,1)).intValue()+Integer.valueOf(new Integer(num).toString().substring(1)).intValue();
					cont=cont+num;
					if (sw==0) sw=1;
						else sw=0;
				}
				int temp1=Integer.valueOf(new Integer(cont+10).toString().substring(0,1)+"0").intValue();
				int aux=0;
				if (String.valueOf(temp1-cont).length()==2)
					aux=Integer.valueOf(String.valueOf(temp1-cont).substring(1)).intValue();
				else
					aux=temp1-cont;
		
				if (Integer.valueOf(dato.substring(dato.length()-1)).intValue()!=aux)
					return "ERROR:_El_campo_"+((Vector)data.elementAt(Transaction.searchIndexbyDicc(dic,data))).elementAt(2).toString().replace(' ','_')+"~07147";

			}

					
		}
	}
	else
	{
		if (Transaction.searchValuebyDicc(dic,data).toString().length()!=12)
					//return "ERROR_valor:"+dic;
					return "ERROR:_El_campo_"+((Vector)data.elementAt(Transaction.searchIndexbyDicc(dic,data))).elementAt(2).toString().replace(' ','_')+"~07146";
		String cuenta="0000"+Transaction.searchValuebyDicc(dic,data).toString();
		String extract=cuenta.substring(4,cuenta.length()-1);
		int cont=0;
		int num=0;
		int sw=0;
		for (int i=0;i<extract.length();++i)
		{
			if (sw==0)
				num=Integer.valueOf(extract.substring(i,i+1)).intValue()*2;
			else
				num=Integer.valueOf(extract.substring(i,i+1)).intValue()*1;
			if (new Integer(num).toString().length()==2)
				num=Integer.valueOf(new Integer(num).toString().substring(0,1)).intValue()+Integer.valueOf(new Integer(num).toString().substring(1)).intValue();
			cont=cont+num;
			if (sw==0) sw=1;
			else sw=0;
		}
		int temp1=Integer.valueOf(new Integer(cont+10).toString().substring(0,1)+"0").intValue();
		int aux=0;
		if (String.valueOf(temp1-cont).length()==2)
			aux=Integer.valueOf(String.valueOf(temp1-cont).substring(1)).intValue();
		else
			aux=temp1-cont;
		
		if (Integer.valueOf(cuenta.substring(cuenta.length()-1)).intValue()!=aux)
			return "ERROR:_El_campo_"+((Vector)data.elementAt(Transaction.searchIndexbyDicc(dic,data))).elementAt(2).toString().replace(' ','_')+"~07147";
	}
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	}
	return "";
	
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String validateAmount(String dic,Vector data,Vector cuentas) {
return "";
}
/**
 * This method was created in VisualAge.
 */
public String validateBlankSpaces(String dic,Vector data,Vector cuentas){
	for (int i=0;i<data.size();++i)
	{
		try{
		if (((Vector)data.elementAt(i)).elementAt(7).equals("0"))
		{
			if (((Vector)data.elementAt(i)).elementAt(6).toString().length()<1)
			{
			 	String message="ERROR: El campo "+((Vector)data.elementAt(i)).elementAt(2).toString()+"~07132";
			 	data.removeElementAt(i);
			 	return message;
			}
		}
		}
		catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			 	String message="ERROR:_El_campo_"+((Vector)data.elementAt(i)).elementAt(2).toString().replace(' ','_')+"~07132";
			 	data.removeElementAt(i);
			 	return message;
			
			}
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String validateDocument(String dic,Vector data,Vector cuentas) {
	for (int i=0;i<data.size();++i)
	{
		if (((Vector)data.elementAt(i)).elementAt(1).equals(dic))
		{
			char a[]=((Vector)data.elementAt(i)).elementAt(6).toString().toCharArray();
			for(int j=0;j<a.length;++j)
			{
				if (!Character.isDigit(a[j]))
					{
						//return "ERROR:_El_campo_"+((Vector)data.elementAt(i)).elementAt(2).toString().replace(' ','_')+"_,solo_acepta_digitos";
						return "ERROR:_El_campo_"+((Vector)data.elementAt(i)).elementAt(2).toString().replace(' ','_')+"~07100";
					}
			}
		}
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String validateModule11(String dic,Vector data,Vector cuentas) {
	try{
		String cuenta=Transaction.searchValuebyDicc(dic,data).toString();
		String cuentaInv="";
		int mnSuma=0;
		int mnDigito=0;
		int longi=cuenta.length();
		if (longi<16)
			for (int i=0;i<16-longi;++i)
				cuenta="0"+cuenta;
		for (int i=1;i<16;++i)
			cuentaInv=cuentaInv+cuenta.substring(15-i, 15-i+1);

		for (int i=2;i<8;i++)
			mnSuma=mnSuma+(Integer.valueOf(cuentaInv.substring(i-2, i-2+1)).intValue()*i);			
			
		for (int i=2;i<7;i++)
			mnSuma=mnSuma+(Integer.valueOf(cuentaInv.substring(i+4, i+4+1)).intValue()*i);

		mnDigito=(11-(mnSuma % 11));

		switch(mnDigito){
		case 10:
			return "ERROR: ~07147";
			
		
		case 11:
			if (Integer.valueOf(cuenta.substring(15)).intValue()!=0)
				return "ERROR: ~07147";
			break;
		default:
			if (Integer.valueOf(cuenta.substring(15)).intValue()!=mnDigito)
				return "ERROR: ~07147";
			break;
		}
				
			
		}
	catch(Exception e){System.out.println("ERROR: "+e.getMessage());
	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	}
	return "";
	
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String validateNumber(String dic,Vector data,Vector cuentas) throws Exception{	
	for (int i=0;i<data.size();++i){
		if (((Vector)data.elementAt(i)).elementAt(8).equals("N"))	{			
			char a[]=((Vector)data.elementAt(i)).elementAt(6).toString().toCharArray();			
			for(int j=0;j<a.length;++j){
				if (!Character.isDigit(a[j])){	
					String mensaje = new StringBuffer(" ERROR: El campo "+((Vector)data.elementAt(i)).elementAt(3).toString()).append(" ").append((String)aplicacion.getMensajePorCodigo("07","07100").get(2)).toString();
					throw new ArrayRuleException(this.errorGenerico,mensaje);
				}
			}
		}
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 */
public String validatePassword(String dic, Vector data, Vector cuentas) {
	try {
		if (dic.equals("012") || dic.equals("013") || dic.equals("014")) {
			String dat = Transaction.searchValuebyDicc(dic, data).toString();
			char a[] = dat.toCharArray();
			char digitoIni = a[0];
			int nConta = 0;
			for (int j = 0; j < a.length; ++j) {
				if (!Character.isLetterOrDigit(a[j])) 
					{					
					//if (!(a[j] == '*') && !(a[j] == '!') && !(a[j] == ' ') && !(a[j] == '-') && !(a[j] == '_') && !(a[j] == '$') && !(a[j] == '%')) {
						return "ERROR: El campo clave, no acepta el caracter: " + a[j] + " ~";
					//}
				}
				if (j>0 && a[j]==digitoIni)
				   nConta ++;
			}
			if (dat.length() < 4) {
				return "ERROR:El campo clave, debe ser de por lo menos 4 caracteres ~ ";
			}
			if (nConta == a.length-1)
			   return "ERROR : El campo clave debe tener los digitos distintos ~ ";
		}
		if (dic.equals("019")) {
			if (Transaction.searchValuebyDicc(dic, data).toString().length() < 4) {
				return "ERROR: El campo clave, debe ser de por lo menos 4 caracteres ~";
			}
		}
	} catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 * @param data Vector
 * @param data1 Vector
 */
public String validateReference(String dicc, Vector data, Vector data1) {
	try{
		if (Transaction.searchValuebyDicc(dicc,data).toString().length()<1)
		return "ERROR: El campo Número de Referencia debe contener algún valor ~ ";
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	} 
	return "";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dicc java.lang.String
 * @param data Vector
 * @param data1 Vector
 */
public String validateReferenceCali(String dicc, Vector data, Vector data1) {
	try{
		if (Transaction.searchValuebyDicc(dicc,data).toString().length()<1)
		   return "ERROR: El campo Número de Factura debe contener algún valor ~ ";

		if (Transaction.searchValuebyDicc(dicc,data).toString().length()<11)
		   return "ERROR: El campo Número de Factura debe contener 11 caracteres ~ ";

		if (Transaction.searchValuebyDicc(dicc,data).toString().length()>11)
		   return "ERROR: El campo Número de Factura debe contener como máximo 11 caracteres ~ ";

		// DEJA EL NUMERO CON 10 CARACTERES ... A PETICION DEL BANCO
		Transaction.putValuebyDicc(Transaction.searchValuebyDicc(dicc,data).toString().substring(0,10),dicc ,data);
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	} 
	return "";
}

public String validateLength(String dic,Vector data,Vector cuentas) throws Exception{	
	for(int i = 0 ; i < data.size(); i++){
		Vector sub = (Vector) data.elementAt(i);
		if(sub.elementAt(0).equals(dic)){
			int longitudRequerida = Integer.parseInt((String)sub.elementAt(4));
			int longitud = ((String)sub.elementAt(6)).length();
			if(longitud!=longitudRequerida){
				String campo = (String)sub.elementAt(3);
				throw new ArrayRuleException(ConstanteError.MODULO_LOGIN_LONGITUD_REQUERIDA,campo,String.valueOf(longitudRequerida));
			}
		}
	}
	return "";
}


}