package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;

import CosapiSoft.SARAWebManager.AdministracionDeAgencias;

/**
 * # -> 0-9
 * A -> a-zA-Z
 * & -> a-zA-Z0-9
 * @ -> 0-9\ba-zA-Z y otros Caracteres
 */
public class CFormat {
	// Justificacion de rellenado de caracter a una cadena
	public static final int LEFT = -1;
	public static final int CENTER = 0;
	public static final int RIGHT = 1;
	// Strategies
	public static final int NONE = 0; // caracteres ascii (VACIO)
	public static final int ABSOLUTE = 1; // mascara caracter(tipo password)
	public static final int ALPHAFETIC = 2; // letter only   (AAAAAAAAA)
	public static final int ALPHANUMERIC = 3; // letters and numbers  (&&&&&&&&&&&)
	public static final int NUMERIC = 4; // 1231234, digits only (#######)
	public static final int NUMERIC_DEC_SIGNO = 14; // -123123.4, digits only (S######0.00)
	public static final int NUMERIC_DEC = 15; // 123123.4, digits only (######0.00)
	public static final int ZERO = 5; // 00001231234, digits only (000000000) rellenados con ceros
	public static final int NUMERIC_MILLAR = 6; // 12,123,432  (##,###,###)
	public static final int NUMERIC_MILLAR_SIGNO = 7; // -12,121,321  (S##,###,###)
	public static final int NUMERIC_MILLAR_DEC = 8; // 123,123.12  (##,###,##0.00)
	public static final int NUMERIC_MILLAR_DEC_SIGNO = 9; // -12,123,123.12  (S##,###,##0.00)
	public static final int DATE = 10; // 00/00/0000 or 0000/00/00 (dd/mm/yyyy or yyyy/mm/dd)
	public static final int CUENTA = 11; // (###-#######-# or 000-0000000-0)
	public static final int TIME = 12; // 00:00:00 (hh:mm:ss)
	public static final int OTHER = 13; // (Definido por el usuario)
	private int keyCharStrategy; // Strategy a usar
	private String format = ""; // Format the strategy to use 
	private int longFormat = 0; // length the format
	private String text = "";
	private String textInicial = "";
	private int longText = 0;
	private String strError = "";
	private String name = "Campo :";
public CFormat() {
	this("", "");
}
public CFormat(String format) {
	this(format, "");
}
public CFormat(String format, String text) {
	this.setFormat(format);
	this.setText(text);
	this.strError = "";
}
public String changeFormat() {
	String dato = "";
	strError = "";
	if (!isValidoText())
		return "";
	switch (this.keyCharStrategy) {
		case NONE :
			dato = this.changeToNone();
			break;
		case CUENTA :
			dato = this.changeToCuenta();
			break;
		case DATE :
			dato = this.changeToDate();
			break;
		case TIME :
			dato = this.changeToTime();
			break;
		case ALPHAFETIC :
			dato = this.changeToAlphabetic();
			break;
		case ALPHANUMERIC :
			dato = this.changeToAlphaNumeric();
			break;
		case NUMERIC :
			dato = this.changeToNumeric();
			break;
		case NUMERIC_DEC :
			dato = this.changeToNumericDec();
			break;
		case NUMERIC_DEC_SIGNO :
			dato = this.changeToNumericDecSigno();
			break;
		case NUMERIC_MILLAR :
			dato = this.changeToNumericMillar();
			break;
		case NUMERIC_MILLAR_SIGNO :
			dato = this.changeToNumericMillarSigno();
			break;
		case NUMERIC_MILLAR_DEC :
			dato = this.changeToNumericMillarDec();
			break;
		case NUMERIC_MILLAR_DEC_SIGNO :
			dato = this.changeToNumericMillarDecSigno();
			break;
		case ZERO :
			dato = this.changeToZeros();
			break;
		case OTHER :
			dato = this.changeToOther();
			break;
		default :
			dato = getText();
	}
	setText(dato);
	return dato;
}
private String changeToAlphabetic() {
	String strDato = getText(), entera = "";
	strDato = deleteChar(' ', strDato);
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	longDato = strDato.length();
	for (int j = 0; j < longDato; j++) {
		if (Character.isLetter(strDato.charAt(j)))
			entera += strDato.charAt(j);
	}
	return (entera);
}
private String changeToAlphaNumeric() {
	String strDato = getText(), entera = "";
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	longDato = strDato.length();
	for (int j = 0; j < longDato; j++) {
		char caracter = strDato.charAt(j);
		if (Character.isLetterOrDigit(caracter) || caracter == ' ')
			entera += caracter;
	}
	return (entera);
}
private String changeToCuenta() {
	String auxDato = "", strDato = deleteChar("- ", getText());
	String auxFormat = format;
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	longDato = strDato.length();
	if (isContained('0', auxFormat) || isContained('&', auxFormat)) {
		auxFormat = auxFormat.replace('0', '#');
		auxFormat = auxFormat.replace('&', '#');
	}
	for (int i = longDato - 1, j = longFormat - 1; i >= 0 && j >= 0; j--) {
		if (auxFormat.charAt(j) == '#' && Character.isDigit(strDato.charAt(i)))
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == '-' && strDato.charAt(i) == '-')
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == '-' && Character.isDigit(strDato.charAt(i)))
			auxDato = "-" + auxDato;
		else if (auxFormat.charAt(j) == '0' && strDato.charAt(i) == '-')
			auxDato = "0" + auxDato;
	}
	if (auxDato.length() < longFormat) {
		int xx = longFormat - auxDato.length() - 1;
		for (int i = xx; i >= 0; i--) {
			if (auxFormat.charAt(i) == '#')
				auxDato = "0" + auxDato;
			else
				auxDato = "-" + auxDato;
		}
	}
	return (auxDato);
}
private String changeToDate() {
	setText(getText().trim());
	String auxDato = "", parte = "", strDato = getText();
	int i, j, k = 0, longDato = getText().length();
	char cf = 'a', cd = 'a';
	String cformat = format;
	if (isContained('0', cformat)) {
		if (cformat.indexOf('/') == 2)
			cformat = "dd/MM/yyyy";
		else
			cformat = "yyyy/MM/dd";
	}
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	java.util.Date fecha = new java.util.Date();
	java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
	if (cformat.charAt(0) == 'y')
		formato = new java.text.SimpleDateFormat("yyyy/MM/dd");
	String fechaActual = formato.format(fecha), dayActual, monthActual, yearActual;
	if (cformat.charAt(0) == 'd') {
		dayActual = fechaActual.substring(i = cformat.indexOf('d'), i + 2);
		monthActual = fechaActual.substring(i = cformat.indexOf('M'), i + 2);
		yearActual = fechaActual.substring(i = cformat.indexOf('y'));
	}
	else {
		dayActual = fechaActual.substring(i = cformat.indexOf('d'));
		monthActual = fechaActual.substring(i = cformat.indexOf('M'), i + 2);
		yearActual = fechaActual.substring(i = cformat.indexOf('y'), i + 4);
	}
	if (cuentaChar('/', strDato) == longDato || getText().equals(""))
		return fechaActual;
	for (i = 0, j = 0; i < longDato && j < longFormat; j++) {
		cd = strDato.charAt(i);
		cf = Character.toUpperCase(cformat.charAt(j));
		if ((cf == 'D' || cf == 'M' || cf == 'Y') && Character.isDigit(cd)) {
			parte += cd;
			i++;
			k++;
		}
		else
			if (cf == '/' && cd == '/' && k % 2 == 0) {
				auxDato += parte + "/";
				k = 0;
				parte = "";
				i++;
			}
			else
				if ((cf == 'D' || cf == 'M') && cd == '/' && (k % 2 != 0 || k == 0)) {
					parte = "0" + parte;
					k++;
				}
				else
					if ((cf == 'Y') && cd == '/' && k < 2) {
						parte = "0" + parte;
						k++;
					}
					else
						if ((cf == '0' || cf == 'Y') && cd == '/') {
							parte = yearActual.substring(0, 2) + parte;
							k = 0;
							j += 1;
						}
						else
							if (cf == '/' && Character.isDigit(cd) && k % 2 == 0) {
								auxDato += parte + "/";
								k = 0;
								parte = "";
							}
	}
	if ((cf == 'Y' || cf == 'M' || cf == 'D') && k == 1) {
		parte = "0" + parte;
		k++;
	}
	if (cformat.charAt(0) == 'd') {
		if (cf == 'D' && k == 2)
			parte += "/" + monthActual + "/" + yearActual;
		else
			if (cf == 'M' && k == 2)
				parte += "/" + yearActual;
			else
				if (cf == 'Y' && k == 2)
					parte = yearActual.substring(0, 2) + parte;
				else
					if (cf == 'Y' && k == 3)
						parte = yearActual.substring(0, 1) + parte;
	}
	else {
		if (cf == 'M' && k == 2)
			parte += "/" + dayActual;
		else
			if (cf == 'Y' && k == 2)
				parte = yearActual.substring(0, 2) + parte + "/" + monthActual + "/" + dayActual;
			else
				if (cf == 'Y' && k == 3)
					parte = yearActual.substring(0, 1) + parte + "/" + monthActual + "/" + dayActual;
				else
					if (cf == 'Y' && k == 4)
						parte = parte + "/" + monthActual + "/" + dayActual;
	}
	auxDato += parte;
	setText(auxDato);
	return (auxDato);
}
private String changeToNone() {
	String strDato = getText();
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	setText(strDato);
	return (strDato);
}
private String changeToNumeric() {
	setText(getText().trim());
	String strDato = getText(), entera = "";
	strDato = deleteChar(",. ", strDato);
	strDato = deleteChar('0', strDato, CFormat.LEFT);
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	longDato = strDato.length();
	if (longDato == 0)
		return "0";
	for (int j = 0; j < longDato; j++) {
		if (Character.isDigit(strDato.charAt(j)))
			entera += strDato.charAt(j);
	}
	return (entera);
}
private String changeToNumericDec() {
	setText(getText().trim());
	String strDato = getText();
	int fdec = cuentaChar('0', format) - 1;
	strDato = deleteChar(", ", strDato);
	int longDato = strDato.length();
	if (longDato == 0)
		return "0." + fillChar("", '0', 0, fdec);
	int pos = -1;
	if (cuentaChar('.', strDato) > 0)
		pos = strDato.indexOf('.');
	String decimal = "", entera = "";
	if (pos >= 0) {
		decimal = strDato.substring(pos + 1);
		entera = strDato.substring(0, pos);
	}
	else
		entera = strDato;
	int auxLongFormat = format.length();
	longFormat = format.indexOf('.');
	setText(entera);
	entera = changeToNumeric();
	longFormat = auxLongFormat;
	decimal = deleteChar('.', decimal);
	int ndec = decimal.length();
	if (fdec > ndec)
		decimal = fillChar(decimal, '0', ndec, fdec - ndec);
	else
		decimal = decimal.substring(0, fdec);
	strDato = entera + "." + decimal;
	return strDato;
}
private String changeToNumericDecSigno() {
	setText(getText().trim());
	String strDato = getText();
	strDato = deleteChar(' ', strDato);
	int longDato = strDato.length();
	if (strDato.equals(""))
		return "0";
	if (strDato.charAt(0) == '-') {
		strDato = deleteChar('-', strDato);
		setText(strDato);
		strDato = changeToNumericDec();
		if (strDato.equals("0"))
			return strDato;
		strDato = "-" + strDato;
		setText(strDato);
		return strDato;
	}
	strDato = deleteChar('-', strDato);
	setText(strDato);
	strDato = changeToNumericDec();
	return strDato;
}
private String changeToNumericMillar() {
	setText(getText().trim());
	String strDato = getText();
	String entera = "";
	strDato = deleteChar(", ", strDato);
	strDato = deleteChar('0', strDato, CFormat.LEFT);
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	longDato = strDato.length();
	if (cuentaChar(',', strDato) == longDato || longDato == 0)
		return "0";
	for (int j = longDato - 1, cont = 0; j >= 0; j--) {
		if (Character.isDigit(strDato.charAt(j))) {
			entera = "" + strDato.charAt(j) + entera;
			cont++;
		}
		if (cont % 3 == 0 && j > 0 && cont > 0) {
			cont = 0;
			entera = "," + entera;
		}
	}
	return (entera);
}
private String changeToNumericMillarDec() {
	setText(getText().trim());
	String strDato = getText();
	int fdec = cuentaChar('0', format) - 1;
	strDato = deleteChar(", ", strDato);
	int longDato = strDato.length();
	if (longDato == 0)
		return "0." + fillChar("", '0', 0, fdec);
	int pos = -1;
	if (cuentaChar('.', strDato) > 0)
		pos = strDato.indexOf('.');
	String decimal = "", entera = "";
	if (pos >= 0) {
		decimal = strDato.substring(pos + 1);
		entera = strDato.substring(0, pos);
	}
	else
		entera = strDato;
	int auxLongFormat = format.length();
	longFormat = format.indexOf('.');
	setText(entera);
	entera = changeToNumericMillar();
	longFormat = auxLongFormat;
	decimal = deleteChar('.', decimal);
	int ndec = decimal.length();
	if (fdec > ndec)
		decimal = fillChar(decimal, '0', ndec, fdec - ndec);
	else
		decimal = decimal.substring(0, fdec);
	strDato = entera + "." + decimal;
	return strDato;
}
private String changeToNumericMillarDecSigno() {
	setText(getText().trim());
	String strDato = getText();
	strDato = deleteChar(' ', strDato);
	int longDato = strDato.length();
	if (strDato.equals(""))
		return "0";
	if (strDato.charAt(0) == '-') {
		strDato = deleteChar('-', strDato);
		setText(strDato);
		strDato = changeToNumericMillarDec();
		int fdec = cuentaChar('0', format) - 1;
		String decimal = "0." + fillChar("", '0', fdec);
		if (decimal.equals("0."))
			decimal += "0";
		if (strDato.equals(decimal))
			return strDato;
		strDato = "-" + strDato;
		setText(strDato);
		return strDato;
	}
	strDato = deleteChar('-', strDato);
	setText(strDato);
	strDato = changeToNumericMillarDec();
	return strDato;
}
private String changeToNumericMillarSigno() {
	setText(getText().trim());
	String strDato = getText();
	strDato = deleteChar(' ', strDato);
	int longDato = strDato.length();
	if (strDato.equals(""))
		return "0";
	if (strDato.charAt(0) == '-') {
		strDato = deleteChar('-', strDato);
		setText(strDato);
		strDato = changeToNumericMillar();
		if (strDato.equals("0"))
			return strDato;
		return "-" + strDato;
	}
	strDato = deleteChar('-', strDato);
	setText(strDato);
	strDato = changeToNumericMillar();
	return strDato;
}
private String changeToOther() {
	String auxDato = "";
	String strDato = deleteChar("- ", getText());
	String auxFormat = format;
	int longDato = strDato.length();
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	longDato = strDato.length();
	for (int i = longDato - 1, j = longFormat - 1; i >= 0 && j >= 0; j--) {
		if ((auxFormat.charAt(j) == '#' || auxFormat.charAt(j) == '9' || auxFormat.charAt(j) == '0') && Character.isDigit(strDato.charAt(i)))
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == '&' && Character.isLetterOrDigit(strDato.charAt(i)))
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == 'A' && Character.isLetter(strDato.charAt(i)))
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == '-' && strDato.charAt(i) == '-')
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == ':' && strDato.charAt(i) == ':')
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if (auxFormat.charAt(j) == '/' && strDato.charAt(i) == '/')
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else if ((auxFormat.charAt(j) == '-' || auxFormat.charAt(j) == ':' || auxFormat.charAt(j) == '/') && Character.isLetterOrDigit(strDato.charAt(i)))
			auxDato = "" + auxFormat.charAt(j) + auxDato;
		else if ((auxFormat.charAt(j) == ',' || auxFormat.charAt(j) == '.') && Character.isDigit(strDato.charAt(i)))
			auxDato = "" + auxFormat.charAt(j) + auxDato;
		else if ((auxFormat.charAt(j) == '#' || auxFormat.charAt(j) == '9' || auxFormat.charAt(j) == '0') && (strDato.charAt(j) == '-' || strDato.charAt(j) == '/'))
			auxDato = "" + auxFormat.charAt(j) + auxDato;
		else if (auxFormat.charAt(j) == '@')
			auxDato = "" + strDato.charAt(i--) + auxDato;
		else
			i--;
	}
	return (auxDato);
}
private String changeToTime() {
	setText(getText().trim());
	String auxDato = "";
	String parte = "";
	String strDato = getText();
	int i, j, k = 0;
	int longDato = strDato.length();
	char cf = '*', cd = '*';
	String cformat = format;
	if (isContained('0', format))
		format = "hh:mm:ss";
	if (longDato > longFormat)
		strDato = strDato.substring(0, longFormat);
	java.util.Date hora = new java.util.Date();
	java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("HH:mm:ss");
	String horaActual = formato.format(hora);
	String hourActual = horaActual.substring(0, 2);
	String minuteActual = horaActual.substring(3, 5);
	String secondsActual = horaActual.substring(6);
	if (cuentaChar(':', strDato) == longDato)
		return horaActual;
	for (i = 0, j = 0; i < longDato && j < longFormat; j++) {
		cd = strDato.charAt(i);
		cf = format.charAt(j);
		if ((cf == 's' || cf == 'm' || cf == 'h') && Character.isDigit(cd)) {
			parte += cd;
			k++;
			i++;
		}
		else
			if (cf == ':' && cd == ':' && k == 2) {
				auxDato += parte + ":";
				k = 0;
				i++;
				parte = "";
			}
			else
				if (cf == ':' && Character.isDigit(cd) && k == 2) {
					auxDato += parte + ":";
					k = 0;
					parte = "";
				}
				else
					if ((cf == 's' || cf == 'm' || cf == 'h') && cd == ':' && k != 2) {
						parte = "0" + parte;
						k++;
					}
	}
	if (cf == '*' && cd == '*')
		parte = hourActual + ":" + minuteActual + ":" + secondsActual;
	else {
		if ((cf == 'h' || cf == 'm' || cf == 's') && k == 1) {
			parte = "0" + parte;
			k++;
		}
		if (cf == 'h' && k == 2)
			parte += ":" + minuteActual + ":" + secondsActual;
		else {
			if (cf == 'm' && k == 2) {
				parte += ":" + secondsActual;
			}
		}
	}
	auxDato += parte;
	return (auxDato);
}
private String changeToZeros() {
	setText(getText().trim());
	String auxDato = changeToNumeric();
	int longDato = auxDato.length();
	return fillChar(auxDato, '0', 0, longFormat - longDato);
}
public static final int cuentaChar(char caracter,String cadena) {
	int cont=0;
	for(int i=0;i<cadena.length();i++)
	   if(cadena.charAt(i)==caracter) cont++;
	return cont;
}
public static final int cuentaChar(String caracteres, String cadena) {
	int cont = 0;
	for (int i = 0; i < caracteres.length(); i++)
		cont += cuentaChar(caracteres.charAt(i), cadena);
	return cont;
}
public static final String deleteChar(char caracter, String cadena) {
	String auxCadena = "";
	if (cadena == null)
		return "";
	if (!cadena.equals(""))
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) != caracter)
				auxCadena += cadena.charAt(i);
		}
	return auxCadena;
}
public static final String deleteChar(char caracter, String cadena, int alineacion) {
	if (cadena == null)
		return "";
	String auxCadena = "";
	int i = 0;
	switch (alineacion) {
		case CFormat.LEFT :
			for (i = 0; i < cadena.length(); i++) {
				if (cadena.charAt(i) != caracter) {
					auxCadena = cadena.substring(i);
					break;
				}
			}
			break;
		case CFormat.RIGHT :
			for (i = cadena.length() - 1; i >= 0; i--) {
				if (cadena.charAt(i) != caracter) {
					if (i == 0)
						auxCadena += cadena.charAt(i);
					else
						if (i == cadena.length() - 1)
							auxCadena = cadena.substring(0);
						else
							auxCadena = cadena.substring(0, i + 1);
					break;
				}
			}
			break;
		default :
			auxCadena = cadena;
			break;
	}
	return auxCadena;
}
public static final String deleteChar(String caracteres, String cadena) {
	String auxCadena = cadena;
	for (int i = 0; i < caracteres.length(); i++)
		auxCadena = deleteChar(caracteres.charAt(i), auxCadena);
	return auxCadena;
}
// Completa la cadena con el caracter especificado, de acuerdo a la longitud de la cadena pedida
// OJO: 
// ejm1.
// cadena = "85"
// caracter = '0'
// longitud = 5             - 5 -
// La cadena devuelta es : "00085"
// ejm2.
// cadena = "Alex"
// caracter = '@'
// longitud = 15            ----- 15 -----
// La cadena devuelta es : "@@@@@@@@@@Alex"
// cadena = "1234"
// caracter = '0'
// longitud = 3             -3-
// La cadena devuelta es : "234"
public static final String fillChar(String cadena,char caracter,int longitud) {
	if(cadena==null) cadena="";
	if(cadena.length()>longitud) return cadena.substring(cadena.length()-longitud);
	return fillChar(cadena,caracter,0,longitud-cadena.length()); 
}
public static final String fillChar(String cadena,char caracter,int inicio,int longitud) {
	String cadena1="",cadena2="";
	if(cadena!=null && !cadena.equals("")){
		if(inicio>0 && inicio<cadena.length()){
			cadena1=cadena.substring(0,inicio);
			cadena2=cadena.substring(inicio);
		}else if(inicio<=0) cadena2=cadena;
		else {
			cadena1=cadena;
			while(cadena1.length()<inicio) cadena1+=" ";
		}
	}
	for(int i=0;i<longitud;i++)
		cadena1+=caracter;
		
	return cadena1+cadena2; 
}
public static final String fillChar(String cad, int alineacion, char caracter, int longCadena) {
	cad = (cad == null) ? "" : cad;
	String aux = "";
	switch (alineacion) {
		case CFormat.LEFT :
			if (longCadena < cad.length())
				return CFormat.left(cad, longCadena);
			return fillChar(cad, caracter, longCadena);
		case CFormat.CENTER :
			if (longCadena < cad.length())
				return CFormat.left(cad, longCadena);
			aux = CFormat.fillChar("", caracter, 0, longCadena - cad.length());
			if (aux.equals(""))
				return cad;
			int pos = (longCadena - cad.length()) / 2;
			if (pos > 0)
				return aux.substring(0, pos) + cad + aux.substring(pos);
			return cad + aux;
		default :
			if (longCadena < cad.length())
				return CFormat.right(cad, longCadena);
			aux = CFormat.fillChar("", caracter, 0, longCadena - cad.length());
			return cad + aux;
	}
}
public static final String formatImporte(String importe) {
	boolean bnum = isNumeric(importe);
	int pos = -1;
	if (bnum) {
		if (isContained('E', importe)) {
			pos = importe.indexOf('E');
			String dec = importe.substring(pos);
			if (dec.length() > 1) {
				dec = dec.substring(1);
				int pot = new Integer(dec).intValue();
				importe = importe.substring(0, pos);
				pos = -1;
				if (isContained('.', importe))
					pos = importe.indexOf('.');
				String entera = "", decimal = "00";
				if (pos > 0) {
					entera = importe.substring(0, pos);
					decimal = importe.substring(pos + 1);
				}
				else
					if (importe.length() > 1)
						decimal = importe.substring(pos + 1);
				if (pot > 0) {
					if (decimal.length() > pot) {
						entera += decimal.substring(0, pot);
						decimal = decimal.substring(pot);
					}
					else {
						entera += decimal;
						for (int i = 0; i < pot - decimal.length(); i++)
							entera += "0";
						decimal = "0";
					}
				}
				else {
					pot = -pot;
					if (entera.length() > pot) {
						decimal = entera.substring(entera.length() - pot) + decimal;
						entera = entera.substring(0, entera.length() - pot);
					}
					else {
						decimal = entera + decimal;
						for (int i = 0; i < pot - entera.length(); i++)
							decimal = "0" + decimal;
						entera = "0";
					}
				}
				importe = entera + "." + decimal;
			}
		}
	}
	/*	if (isContained('.', importe))
	pos = importe.indexOf('.');
	String entera = importe, decimal = "";
	if (pos > 0) {
	entera = CFormat.mid(importe, 0, pos);
	decimal = CFormat.mid(importe,pos + 1);
	}
	importe="";
	for (int i = 0; i < entera.length(); i++) {
	if(entera.charAt(i)!='0') break;
	importe+=entera.charAt(i);
	}
	importe+="."+decimal;
	for (int i = decimal.length()-1; i >=0; i--) {
	if(decimal.charAt(i)!='0') break;
	importe=CFormat.mid(importe,0,importe.length()-1);
	}*/
	return importe;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String formatValue() {
	if (textInicial.equals(""))
		return "";
	return changeFormat();
}
public String getCuenta() {
	if (this.keyCharStrategy != CUENTA)
		return "";
	String strCuenta = deleteChar('-', getText());
	strCuenta = fillChar(strCuenta, '0', 0, 18 - strCuenta.length());
	return strCuenta;
}
public static final String getDateActual(String date, String strFormato) {
	java.util.Date fecha = new java.util.Date();
	String fDate = "";
	java.text.SimpleDateFormat formato;
	if (strFormato.charAt(0) == 'd' || strFormato.charAt(0) == 'D') {
		formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
		fDate = "00/00/0000";
	}
	else {
		formato = new java.text.SimpleDateFormat("yyyy/MM/dd");
		fDate = "0000/00/00";
	}
	if (date == null || date.equals(""))
		return formato.format(fecha);
	if (date.equals("0") && (strFormato.charAt(0) == 'y' || strFormato.charAt(0) == 'Y'))
		return "0000/00/00";
	if (date.equals("0") && (strFormato.charAt(0) == 'd' || strFormato.charAt(0) == 'D'))
		return "00/00/0000";
	return date;
}
public String getError() {
	return strError;
}
public String getFormat() {
	return format;
}
public static final int getInt(String string) {
	if (string == null || string.equals(""))
		return 0;
	if (!isNumeric(string))
		return 0;
	return new Integer(string).intValue();
}
public int getLongFormat() {
	if (this.keyCharStrategy == NONE || this.getFormat().equals(""))
		return 255;
	return this.getFormat().length();
}
public String getName() {
	return name;
}
public int getStrategy() {
	return keyCharStrategy;
}
public static final String getString(String string) {
	if (string == null)
		return "";
	return string;
}
public String getText() {
	return text;
}
public static final String getTimeActual(String time) {
	java.util.Date hora = new java.util.Date();
	java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("HH:mm:ss");
	if (time.equals("") || time == null)
		return formato.format(hora);
	return time;
}
public static final String getTimeStamp(String timestamp, String format) {
	if (timestamp == null || timestamp.equals("0")) {
		if (format.charAt(0) == 'y' || format.charAt(0) == 'Y')
			return "0000-00-00-00.00.00";
		return "00-00-0000-00.00.00";
	}
	java.util.Date now = new java.util.Date();
	java.text.SimpleDateFormat formato1 = new java.text.SimpleDateFormat("HH:mm:ss"), formato2;
	if (format.charAt(0) == 'y' || format.charAt(0) == 'Y')
		formato2 = new java.text.SimpleDateFormat("yyyy/MM/dd");
	else
		formato2 = new java.text.SimpleDateFormat("dd/MM/yyyy");
	if (timestamp.equals("") || timestamp == null)
		timestamp = formato2.format(now);
	else
		timestamp = timestamp.replace('/', '-');
	String formato = timestamp;
	formato += "-" + formato1.format(now);
	formato = formato.replace(':', '.');
	return formato;
}
public double getValue() {
	if (this.keyCharStrategy == NUMERIC_MILLAR || this.keyCharStrategy == NUMERIC_MILLAR_DEC || this.keyCharStrategy == NUMERIC_MILLAR_DEC_SIGNO || this.keyCharStrategy == NUMERIC_MILLAR_SIGNO) {
		double num = new Double(deleteChar(", ", getText())).doubleValue();
		return num;
	}
	return Double.NEGATIVE_INFINITY;
}
public static final boolean isContained(char caracter, String string) {
	if (string == null)
		return false;
	for (int i = 0; i < string.length(); i++)
		if (caracter == string.charAt(i))
			return true;
	return false;
}
public static final boolean isContained(String caracteres, String string) {
	if (caracteres == null)
		return false;
	for (int i = 0; i < caracteres.length(); i++)
		if (!isContained(caracteres.charAt(i), string))
			return false;
	return true;
}
// Verifica si una cadena es numerica
public static final boolean isNumeric(String valor) {
	if (valor == null || valor.equals(""))
		return false;
	if (valor.equals("E"))
		return false;
	if (isContained('E', valor)) {
		if (!isContained('.', valor))
			return false;
		if (valor.length() < 4)
			return false; // 0.0E0
		int pos = valor.indexOf('E');
		String dec = valor.substring(pos);
		if (dec.length() == 1)
			return false; // 1.230E
		if (isContained('.', dec))
			return false; // 1.33E1.2
		if (cuentaChar('-', dec) > 1)
			return false;
	}
	if (cuentaChar('.', valor) > 1)
		return false;
	if (cuentaChar('-', valor) > 2)
		return false;
	for (int i = 0; i < valor.length(); i++) {
		if (!(Character.isDigit(valor.charAt(i)) || valor.charAt(i) == 'E' || valor.charAt(i) == '.' || valor.charAt(i) == '-' || valor.charAt(i) == '+'))
			return false;
	}
	return true;
}
public static final boolean validarImporte(String valor) {
	int ihour = valor.trim().indexOf(".");
	if(ihour==-1){
		if(!isNumeric(valor)){
			return false;
		}
	}
	else if(ihour==(valor.trim().length()-1)){
		return false;
	}
	else{
		String ente = (valor.substring(0,valor.lastIndexOf(".")));
		String deci = (valor.substring(valor.lastIndexOf(".")));
		if(!isNumeric(ente) || !isNumeric(deci)){
			return false;
		}
	}
	return true;
}

public boolean isValidoChar(char caracter) {
	boolean valido = false;
	if ((keyCharStrategy == NONE || keyCharStrategy == ABSOLUTE) && caracter != '\n')
		valido = true;
	else if (keyCharStrategy == CUENTA && (Character.isDigit(caracter) || caracter == '-'))
		valido = true;
	else if (keyCharStrategy == DATE && (Character.isDigit(caracter) || caracter == '/'))
		valido = true;
	else if (keyCharStrategy == TIME && (Character.isDigit(caracter) || caracter == ':'))
		valido = true;
	else if (keyCharStrategy == NUMERIC_MILLAR_DEC_SIGNO && (Character.isDigit(caracter) || caracter == '-' || caracter == '.' || caracter == ','))
		valido = true;
	else if (keyCharStrategy == NUMERIC_MILLAR_SIGNO && (Character.isDigit(caracter) || caracter == ',' || caracter == '-'))
		valido = true;
	else if (keyCharStrategy == NUMERIC_MILLAR_DEC && (Character.isDigit(caracter) || caracter == ',' || caracter == '.'))
		valido = true;
	else if (keyCharStrategy == NUMERIC_MILLAR && (Character.isDigit(caracter) || caracter == ','))
		valido = true;
	else if (keyCharStrategy == NUMERIC_DEC && (Character.isDigit(caracter) || caracter == '.'))
		valido = true;
	else if (keyCharStrategy == NUMERIC_DEC_SIGNO && (Character.isDigit(caracter) || caracter == '.' || caracter == '-'))
		valido = true;
	else if ((keyCharStrategy == ZERO || keyCharStrategy == NUMERIC) && Character.isDigit(caracter))
		valido = true;
	else if (keyCharStrategy == ALPHAFETIC && Character.isLetter(caracter))
		valido = true;
	else if (keyCharStrategy == ALPHANUMERIC && (Character.isLetterOrDigit(caracter) || caracter == ' '))
		valido = true;
	else if (keyCharStrategy == OTHER)
		valido = true;
	return valido;
}
public boolean isValidoDate() {
	String strDato = getText();
	String day = "";
	String month = "";
	String year = "";
	if (format.charAt(0) == 'd' || format.charAt(0) == 'D') {
		day = strDato.substring(0, 2);
		month = strDato.substring(3, 5);
		year = strDato.substring(6);
		//if(Constante.VER_LOG)
		//System.out.println("dia.."+day);
		//if(Constante.VER_LOG)
		//System.out.println("mes.."+month);
		//if(Constante.VER_LOG)
		//System.out.println("año.."+year);
	} else {
		year = strDato.substring(0, 4);
		month = strDato.substring(5, 7);
		day = strDato.substring(8);
	}
	int d = Character.getNumericValue(day.charAt(0)) * 10 + Character.getNumericValue(day.charAt(1));
	int m = Character.getNumericValue(month.charAt(0)) * 10 + Character.getNumericValue(month.charAt(1));
	int y = 0;
	for (int i = 0; i < year.length(); i++)
		y = y * 10 + Character.getNumericValue(year.charAt(i));
	int meses[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	if (m < 1 || m > 12) {
		this.strError += "El mes " + m + " está Incorrecto \n";
		return false;
	}
	// año bisiesto
	if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) {
		if (m == 2 && d > 29) {
			this.strError += "El Dia " + d + " está errado porque el año es bisiesto \n";
			return false;
		}
	} else
		if (d > meses[m] || d < 1) {
			this.strError += "El Dia " + d + " está incorrecto \n";
			return false;
		} else
			if (y < 1900 || y > 3000) {
				this.strError += "El Año " + y + " está incorrecto porque es menor que 1900 \n";
				return false;
			}
	return true;
}
public boolean isValidoDato() {
	strError = "";
	if (!isValidoText()) {
		strError += "En " + name + " \n Texto NO corresponde con el formato \n";
		return false;
	}
	setText(changeFormat());
	if (this.keyCharStrategy == CUENTA)
		return (isValidoDigitoChequeo());
	else if (this.keyCharStrategy == DATE)
		return (isValidoDate());
	else if (this.keyCharStrategy == TIME)
		return (isValidoTime());
	return true;
}
private boolean isValidoDigitoChequeo() {
	String strCuenta = getText();
	strCuenta = deleteChar('-', strCuenta);
	if (strCuenta == null)
		return false;
	if (strCuenta.length() < 11)
		return false;
	// nro de cuenta en pantalla aaabbbbbbbc            aaab bbbbbb c
	// nro de cuenta interno abbbccccccd                abbb cccccc d
	String msCuenta = strCuenta.substring(4, 10), // cccccc
	mnDigitoVerificador = strCuenta.substring(10), // d
	msPonderador = "137137";
	int mnCalculo = 0;
	for (int i = 0; i < msCuenta.length(); i++) {
		int j = Character.getNumericValue(msCuenta.charAt(i));
		int k = Character.getNumericValue(msPonderador.charAt(i));
		mnCalculo += j * k;
	}
	mnCalculo = 10 - (mnCalculo % 10);
	if (mnCalculo >= 10)
		mnCalculo = 9;
	if (!mnDigitoVerificador.equals(new Integer(mnCalculo).toString())) {
		this.strError += "En " + this.name + " \n Digito de Verificación incorrecto \n";
		return false;
	}
	return true;
}
public boolean isValidoText() {
	setText(this.text);
	for (int i = 0; i < this.longText; i++) {
		if (!isValidoChar(text.charAt(i))) {
			this.strError = "Caracter inválido : '" + text.charAt(i) + "'";
			return false;
		}
	}
	return true;
}
public static final boolean ValidoAN(String cadena) {
	for (int i = 0; i < cadena.length(); i++) {
		if (!validarCharAN(cadena.charAt(i))) {
			return false;
		}
	}
	return true;
}
public static final boolean validarCharAN(char caracter) {
	if (!Character.isLetterOrDigit(caracter)) {
			return false;
		}
	
	return true;
}
public boolean isValidoTime() {
	String strDato = getText();
	int ihour = strDato.indexOf(":");
	int iminute = strDato.indexOf(":", ihour + 1);
	String tiempo = strDato, hour = strDato.substring(0, 2);
	String minute = strDato.substring(3, 5);
	String second = strDato.substring(6);
	int h = Character.getNumericValue(hour.charAt(0)) * 10 + Character.getNumericValue(hour.charAt(1)), m = Character.getNumericValue(minute.charAt(0)) * 10 + Character.getNumericValue(minute.charAt(1)), s = Character.getNumericValue(second.charAt(0)) * 10 + Character.getNumericValue(second.charAt(1));
	if (h > 23) {
		this.strError += " Número de horas incorrecto, debe ser menor de 23 \n";
		return false;
	} else {
		if (m > 59) {
			this.strError += " Número de minutos incorrecto, debe ser menor que 60 \n";
			return false;
		} else {
			if (s > 59) {
				this.strError += " Número de segundos incorrecto, debe ser menor que 60 \n";
				return false;
			}
		}
	}
	return true;
}
public static final boolean isTime(String strDato) {
	int ihour = strDato.indexOf(":");
	int iminute = strDato.indexOf(":", ihour + 1);
	if(strDato.length()!=8){
		return false;
	}
	else if(ihour==-1 || iminute==-1){
		return false;
	}
	else{
		String tiempo = strDato, hour = strDato.substring(0, 2);
		String minute = strDato.substring(3, 5);
		String second = strDato.substring(6);
		int h = Character.getNumericValue(hour.charAt(0)) * 10 + Character.getNumericValue(hour.charAt(1)), m = Character.getNumericValue(minute.charAt(0)) * 10 + Character.getNumericValue(minute.charAt(1)), s = Character.getNumericValue(second.charAt(0)) * 10 + Character.getNumericValue(second.charAt(1));
		if (h > 23) {
			return false;
		} 
		else {
		if (m > 59) {
			return false;
		} 
		else {
			if (s > 59) {
				return false;
			}
		}
		}
	}
	return true;
}
public static final boolean isTimemayor(String strDato1,String strDato2) {
		String hour1 = strDato1.substring(0, 2);
		String minute1 = strDato1.substring(3, 5);
		String hour2 = strDato2.substring(0, 2);
		String minute2 = strDato2.substring(3, 5);
		String seg1 = strDato2.substring(0, 2);
		String seg2 = strDato2.substring(3, 5);
		String time1=hour1+minute1+seg1;
		String time2=hour2+minute2+seg2;
		if(Constante.VER_LOG) System.out.println(time1);
		// System.out.println("time 1..."+time1);
		int t1=Integer.parseInt(time1);
		int t2=Integer.parseInt(time2);
		if(t1>t2)
			return false;
	return true;
}
public static final boolean isImage(String strDato) {
	int punto = strDato.indexOf(".");
	// System.out.println("lugar del puntito...."+punto);
	// System.out.println("tamaño de la cadena...."+strDato.length());
	int a=punto+1;
	if(punto==-1){
		return false;
	}
	if(punto==0){
		return false;
	}
	if(strDato.length()==a){
		return false;
	}
	String extensiones_permitidas[] = {".gif", ".jpg", ".jpeg", ".swf"}; 
	String extension1 = (strDato.substring(strDato.lastIndexOf("."))).toLowerCase(); 
	boolean permitida = false; 
	for (int i = 0; i < extensiones_permitidas.length; i++) { 
	    if (extensiones_permitidas[i].equals(extension1)) { 
	         permitida = true; 
	         break; 
	         } 
	} 
	return permitida;
}
// Devuelve longitud caracteres del la izquierda de la cadena
public static final String left(String cadena, int longitud) {
	String auxCadena = "";
	cadena = (cadena == null) ? "" : cadena;
	if (longitud <= 0)
		return "";
	if (longitud >= cadena.length())
		return cadena;
	auxCadena = cadena.substring(0, longitud);
	return auxCadena;
}
// Quita los espacios en blanco de la cadena, pero de la parte izquierda de la cadena
public static final String ltrim(String cadena) {
	String auxCadena = "";
	if (cadena == null)
		return "";
	auxCadena = cadena;
	int i = 0;
	while (i < cadena.length() - 1 && auxCadena.charAt(0) == ' ') {
		if (auxCadena.length() > 1)
			auxCadena = auxCadena.substring(1);
		else
			auxCadena = "";
		i++;
	}
	return auxCadena;
}
/**
 * This method was created in VisualAge.
 * @param args java.lang.String[]
 */
public static void main(String args[]) {
	CFormat f = new CFormat();
	f.setFormat("00/00/0000");
	f.setText("01012000");
	// System.out.println(f.changeFormat());
	f.setFormat("00:00:00");
	f.setText("");
	// System.out.println(f.changeFormat());
}
public static final String mid(String cadena, int start) {
	cadena = (cadena == null) ? "" : cadena;
	return mid(cadena, start, cadena.length());
}
// Extrae a partir de la posicion start, longitud caracteres de la cadena
public static final String mid(String cadena, int start, int longitud) {
	cadena = (cadena == null) ? "" : cadena;
	if (cadena.equals("") || start < 0 || longitud <= 0)
		return "";
	if (longitud + start >= cadena.length() && start < cadena.length())
		return cadena.substring(start);
	if (longitud + start < cadena.length())
		return cadena.substring(start, start + longitud);
	return "";
}
public static final String reverse(String cadena) {
	String auxCadena = "";
	if (cadena == null)
		return "";
	for (int i = cadena.length() - 1; i >= 0; i--)
		auxCadena += cadena.charAt(i);
	return auxCadena;
}
// Devuelve longitud caracteres del la derecha de la cadena
public static final String right(String cadena, int longitud) {
	String auxCadena = reverse(cadena);
	auxCadena = left(auxCadena, longitud);
	auxCadena = reverse(auxCadena);
	return auxCadena;
}
// Quita los espacios en blanco de la cadena, pero de la parte derecha de la cadena
public static final String rtrim(String cadena) {
	String auxCadena = "";
	if (cadena == null)
		return "";
	auxCadena = reverse(cadena);
	auxCadena = ltrim(auxCadena);
	auxCadena = reverse(auxCadena);
	return auxCadena;
}
public static final int searchChar(char caracter, String cadena) {
	return searchChar(caracter, cadena, 0);
}
public static final int searchChar(char caracter, String cadena,int inicio) {
	if (cadena == null)
		return -1;
	for(int i=inicio;i<cadena.length();i++)
		if(cadena.charAt(i)==caracter) return i;
	return -1;
}
public static final int searchChar(String caracteres, String cadena, int inicio) {
	if (cadena == null || caracteres == null)
		return -1;
	for (int i = inicio; i < cadena.length(); i++) {
		if (isContained(cadena.charAt(i), caracteres))
			return i;
	}
	return -1;
}
public void setFormat(String formato) {
	if (formato.equals("") || formato == null) {
		keyCharStrategy = NONE;
		format = "";
		longFormat = 255;
		return;
	}
	format = formato;
	longFormat = format.length();
	if (format.equals(""))
		return;
	else if (isContained("&-", format) || isContained("#-", format) || isContained("0-", format))
		keyCharStrategy = CUENTA;
	else if (isContained("dMy/", format) || isContained("dmy/", format) || isContained("DMY/", format) || isContained("0/", format)){
		keyCharStrategy = DATE;
		int pos = format.indexOf('/');
		if(pos <= 2)
			format = "00/00/0000";
		else
			format = "0000/00/00";
	}
	else if (isContained("hMs:", format) || isContained("hms:", format) || isContained("HMS:", format) || isContained("0:", format))
		keyCharStrategy = TIME;
	
	else if (isContained("S#0,.", format))
		keyCharStrategy = NUMERIC_MILLAR_DEC_SIGNO;
	
	else if (isContained("S#0.", format))
		keyCharStrategy = NUMERIC_DEC_SIGNO;
	else if (isContained("#0,.", format))
		keyCharStrategy = NUMERIC_MILLAR_DEC;
	else if (isContained("S#,", format))
		keyCharStrategy = NUMERIC_MILLAR_SIGNO;
	else if (isContained("#0.", format))
		keyCharStrategy = NUMERIC_DEC;
	else if (isContained("#,", format))
		keyCharStrategy = NUMERIC_MILLAR;
	else if (isContained('#', format))
		keyCharStrategy = NUMERIC;
	else if (isContained('0', format))
		keyCharStrategy = ZERO;
	else if (isContained('A', format))
		keyCharStrategy = ALPHAFETIC;
	else if (isContained('&', format))
		keyCharStrategy = ALPHANUMERIC;
	else 
		keyCharStrategy = OTHER;
}
public void setKeyChangeStrategy(int strategy) {
	keyCharStrategy = strategy;
}
public void setName(String nombre) {
	if (nombre == null || nombre.equals(""))
		nombre = "Campo :";
	this.name = nombre;
}
public void setText(String text) {
	if (text == null)
		text = "";
	textInicial = text;
	if (this.getStrategy() == CFormat.NUMERIC || this.getStrategy() == CFormat.NUMERIC_MILLAR || this.getStrategy() == CFormat.NUMERIC_MILLAR_DEC || this.getStrategy() == CFormat.NUMERIC_MILLAR_DEC_SIGNO || this.getStrategy() == CFormat.NUMERIC_MILLAR_SIGNO) {
		if (text.equals(""))
			text = "0";
	}
	this.text = formatImporte(text);
	longText = text.length();
}
// Quita los espacios en blanco de la cadena, pero de la parte izquierda y derecha de la cadena
public static final String trim(String cadena) {
	if (cadena == null)
		return "";
	return cadena.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String unformatValue() {
	if (textInicial.equals(""))
		return "";
	if (keyCharStrategy == OTHER || keyCharStrategy == NONE || keyCharStrategy == ABSOLUTE) {
		return textInicial;
	}
	if (keyCharStrategy == ZERO) {
		CString str = new CString(getText());
		str.deleteHeadUntilNot('0');
		return str.toString();
	}
	if (keyCharStrategy == DATE) {
		return deleteChar('/', getText());
	}
	if (keyCharStrategy == TIME) {
		return deleteChar(':', getText());
	}
	if (keyCharStrategy == CUENTA) {
		return deleteChar('-', getText());
	}
	if (keyCharStrategy == NUMERIC || keyCharStrategy == NUMERIC_DEC || keyCharStrategy == NUMERIC_DEC_SIGNO || keyCharStrategy == ZERO || keyCharStrategy == ALPHAFETIC || keyCharStrategy == ALPHANUMERIC) {
		return getText();
	}
	if (keyCharStrategy == NUMERIC_MILLAR || keyCharStrategy == NUMERIC_MILLAR_DEC || keyCharStrategy == NUMERIC_MILLAR_DEC_SIGNO || keyCharStrategy == NUMERIC_MILLAR_SIGNO) {
		return deleteChar(',', getText());
	}
	return "";
}
public static final boolean isHexad(String dato)
{
	// System.out.println("entro......"+dato);
	String permitidas[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	int t=0;
		String michi=dato.substring(0,1);
		// System.out.println("michi=....."+michi+"-");
		if (dato.equals("")) { 
			// System.out.println("entró al nada");
			return false;
		}
		else if(dato.length()!=7 || !michi.equals("#")){
			// System.out.println("nrod e caracteres");
			return false;
		}
		else{
			// System.out.println("1 saco el dato:"+dato);
			String dato1 = (dato.substring(1)).toUpperCase();
			// System.out.println("saco el dato:"+dato1);
			for(int k = -1; k < 5; k++){
				String letra=dato1.substring(k+1,k+2);
				if(Constante.VER_LOG) System.out.println("aki saca letra "+letra);
				for(int j = 0; j < permitidas.length; j++) { 
					if(Constante.VER_LOG) System.out.println("permitida..."+permitidas[j]);
					if (letra.equals(permitidas[j])) {
						if(Constante.VER_LOG) System.out.println("entro al for");
						t=t+1;
					}
					
				}
				
			}
			
			if (t!=6) { 
				return false;
			}
		} 
	
	
return true;
}

}