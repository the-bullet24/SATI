package CosapiSoft.SARAWebBanking;

/**
 *  CString.java
 *  Copyright (c) 2000, CosapiSoft - SARABank
 *  Todos los derechos reservados.
 *  
 *  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
 *  software.  Este software se proporciona COMO ES, sin garantia de ningun
 *  tipo de su funcionamiento y en ningun caso sera el autor responsable de
 *  daños o perjuicios que se deriven del mal uso del software,  aun cuando
 *  este haya sido notificado de la posibilidad de dicho daño.
 * 
 *   Compilador: JDK 1.1.6
 *        Autor: SARABank
 *     Creacion: Enero 2000
 *      Versión: 1.0
 *
 *--------------------------------------------------------------------------
 *  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
 *  que pueden ser incorporados en cualquier momento, sin avisar.
 *--------------------------------------------------------------------------
 */
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.StringTokenizer;
/**
 * <P>
 * Contiene funciones muy útiles para el manejo de Cadenas.
 * <P>
 * Ejemplo. <BR>
 *
 * 	CString cstring = new CString("Texto de Prueba"); <BR>
 * 	System.out.println("CString : " + cstring.toString()); <BR>
 * 	cstring.reverse(); <BR>
 * 	System.out.println("Reverse : " + cstring.toString()); <BR>
 * 	cstring.deleteHeadUntil('e'); <BR>
 * 	System.out.println("DeleteHeadUntil('e') : " + cstring.toString()); <BR>
 * 	cstring.deleteHeadUntilNot('d'); <BR>
 * 	System.out.println("DeleteHeadUntilNot('d') : " + cstring.toString()); <BR>
 * 	cstring.add(" continua..."); <BR>
 * 	System.out.println("Add : " + cstring.toString()); <BR>
 * 	cstring.insertParenthesis(); <BR>
 * 	System.out.println("insertParenthesis() : " + cstring.toString()); <BR>
 * 	// <BR>
 * 	System.out.println(CString.toCommaFormattedString(1545512225)); <BR>
 * 	System.out.println(CString.toHexString(1222556446)); <BR>
 * 	System.out.println(CString.toStringHtml("La suma : 2 + 3 = 4, está equivocada? - &")); <BR>
 * 	// <BR>
 * 	System.out.println(CString.compactLeft("Ejemplo de compactación de cadena", 15)); <BR>
 * 	System.out.println(CString.compactRight("Ejemplo de compactación de cadena", 15)); <BR>
 * 	System.out.println(CString.completeToBeginWithNChars("21554", 10, '0')); <BR>
 * 	System.out.println(CString.completeToEndWithNChars("21554", 10, '0')); <BR>
 * 	System.out.println(CString.fillToBeginWithNChars("12345795", 5, '0')); <BR>
 * 	System.out.println(CString.fillToEndWithNChars("12345795", 5, '0')); <BR>
 * 	System.out.println(CString.deleteAll("This method was created in VisualAge", 'a')); <BR>
 * 	System.out.println(CString.deleteAll("This method was created in VisualAge", new char[] {'i', 'e', 's'})); <BR>
 * 	// <BR>
 * 	byte b[] = "This method was created in VisualAge".getBytes(); <BR>
 * 	String hexStr = CString.toHexString(b); <BR>
 * 	System.out.println("----"); <BR>
 * 	System.out.println(hexStr); <BR>
 * 	System.out.println("----"); <BR>
 * 	System.out.println(CString.toStringOfHex(hexStr)); <BR>
 * <P>
 * Salida. <BR>
 * CString : Texto de Prueba <BR>
 * Reverse : abeurP ed otxeT <BR>
 * DeleteHeadUntil('e') : eurP ed otxeT <BR>
 * DeleteHeadUntilNot('d') : eurP ed otxeT <BR>
 * Add : eurP ed otxeT continua... <BR>
 * insertParenthesis() : (eurP ed otxeT continua...) <BR>
 * 1,545,512,225 <BR>
 * 48DE BB1E <BR>
 * La%2Bsuma%2B:%2B2%2B%2B%2B3%2B%3D%2B4,%2Best%E1%2Bequivocada%3F%2B-%2B%26 <BR>
 * ...ón de cadena <BR>
 * Ejemplo de c... <BR>
 * 0000021554 <BR>
 * 2155400000 <BR>
 * 0000012345795 <BR>
 * 1234579500000 <BR>
 * This method ws creted in VisulAge <BR>
 * Th mthod wa cratd n VualAg <BR>
 * ---- <BR>
 * 54686973206D6574686F6420776173206372656174656420696E2056697375616C416765 <BR>
 * ---- <BR>
 * This method was created in VisualAge <BR>
 *  
 */
public class CString {
	/**
 * Metodo para convertir numeros a letras
 * Jorge Yepez Reyes
 * 24/07/2000
 */
public static String toNumberString(String numero) {
	String resultfinal = "";
	java.util.Vector decenas = new java.util.Vector();
	decenas.addElement("veinti");
	decenas.addElement("treinti");
	decenas.addElement("cuarenti");
	decenas.addElement("cincuenti");
	decenas.addElement("sesenti");
	decenas.addElement("setenti");
	decenas.addElement("ochenti");
	decenas.addElement("noventi");
	java.util.Vector centenas = new java.util.Vector();
	centenas.addElement("cien");
	centenas.addElement("doscientos");
	centenas.addElement("trescientos");
	centenas.addElement("cuatrocientos");
	centenas.addElement("quinientos");
	centenas.addElement("seiscientos");
	centenas.addElement("setecientos");
	centenas.addElement("ochocientos");
	centenas.addElement("novecientos");
	java.util.Vector especiales = new java.util.Vector();
	especiales.addElement("cien");
	especiales.addElement("un");
	especiales.addElement("dos");
	especiales.addElement("tres");
	especiales.addElement("cuatro");
	especiales.addElement("cinco");
	especiales.addElement("seis");
	especiales.addElement("siete");
	especiales.addElement("ocho");
	especiales.addElement("nueve");
	especiales.addElement("diez");
	especiales.addElement("once");
	especiales.addElement("doce");
	especiales.addElement("trece");
	especiales.addElement("catorce");
	especiales.addElement("quince");
	especiales.addElement("dieciseis");
	especiales.addElement("diecisiete");
	especiales.addElement("dieciocho");
	especiales.addElement("decinueve");
	numero = CFormat.formatImporte(numero);
	String decimal = "";
	if (numero.indexOf(".") > 0) {
		decimal = numero.substring(numero.indexOf(".") + 1);
		numero = numero.substring(0, numero.indexOf("."));
	}
	else
		decimal = "00";
	String temp = "";
	int veces = numero.length() / 3;
	int res = numero.length() % 3;
	if (res == 0)
		veces--;
	for (int j = veces; j >= 0; j--) {
		String result = "";
		String word = "";
		if (j == 0)
			word = "";
		if (j == 1)
			word = "mil";
		if (j == 2)
			word = "millon";
		if (j == 3)
			word = "mil";
		if (j == 4)
			word = "billon";
		if (j == 5)
			word = "mil";
		int tmp = numero.length() % 3;
		if (tmp == 0)
			temp = numero.substring(0, 3);
		else
			temp = numero.substring(0, tmp);
		if (temp.length() == 1)
			temp = "00" + temp;
		if (temp.length() == 2)
			temp = "0" + temp;
		if (Integer.valueOf(temp.substring(1)).intValue() > 19) {
			if (Integer.valueOf(temp.substring(2)).intValue() == 0)
				if (Integer.valueOf(temp.substring(1, 2)).intValue() == 2)
					result = decenas.elementAt(Integer.valueOf(temp.substring(1, 2)).intValue() - 2).toString().substring(0, decenas.elementAt(Integer.valueOf(temp.substring(1, 2)).intValue() - 2).toString().length() - 1) + "e" + result;
				else
					result = decenas.elementAt(Integer.valueOf(temp.substring(1, 2)).intValue() - 2).toString().substring(0, decenas.elementAt(Integer.valueOf(temp.substring(1, 2)).intValue() - 2).toString().length() - 1) + "a" + result + " ";
			else {
				result = decenas.elementAt(Integer.valueOf(temp.substring(1, 2)).intValue() - 2).toString() + result;
				result = result + especiales.elementAt(Integer.valueOf(temp.substring(2)).intValue()).toString() + " ";
			}
		}
		else
			if (Integer.valueOf(temp.substring(1)).intValue() != 0)
				if (!(j % 2 != 0 && temp.substring(1).equals("01")))
					result = result + especiales.elementAt(Integer.valueOf(temp.substring(1)).intValue()).toString() + " ";
		if (Integer.valueOf(temp.substring(0, 1)).intValue() != 0)
			if (Integer.valueOf(temp.substring(0, 1)).intValue() == 1)
				if (Integer.valueOf(temp.substring(1)).intValue() != 0)
					result = centenas.elementAt(Integer.valueOf(temp.substring(0, 1)).intValue() - 1).toString() + "to " + result;
				else
					result = centenas.elementAt(Integer.valueOf(temp.substring(0, 1)).intValue() - 1).toString() + " " + result;
			else
				result = centenas.elementAt(Integer.valueOf(temp.substring(0, 1)).intValue() - 1).toString() + " " + result;
		if (Integer.valueOf(temp).intValue() != 0) {
			if (j != 0) {
				if (j % 2 == 0) {
					if (Integer.valueOf(temp.substring(0, 3)).intValue() == 1)
						resultfinal = resultfinal + result + word + " ";
					else
						resultfinal = resultfinal + result + word + "es ";
				}
				else
					resultfinal = resultfinal + result + word + " ";
			}
			else
				resultfinal = resultfinal + result + word + " ";
		}
		if (numero.length() > 3)
			if (tmp == 0)
				numero = numero.substring(3);
			else
				numero = numero.substring(tmp);
	}
	//System.out.println(resultfinal.trim() + " PESOS CON " + decimal + "/100");
	return resultfinal.trim() + " PESOS CON " + decimal + "/100";
}

	public String string;
	private static final char[] hexDidget = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
/**
 * Asigna un valor por defecto
 */
public CString() {
	string = "";
}
/**
 * @since JDK1.1
 */
public CString(byte bytes[]) {
	string = new String(bytes);
}
/**
 * @since JDK1.1
 */
public CString(byte bytes[], int offset, int length) {
	string = new String(bytes, offset, length);
}
/**
 * @since JDK1.1
 */
public CString(byte bytes[], int offset, int length, String enc) throws UnsupportedEncodingException {
	string = new String(bytes, offset, length, enc);
}
/**
 * @since JDK1.1
 */
public CString(byte bytes[], String enc) throws UnsupportedEncodingException {
	string = new String(bytes, enc);
}
/**
 * @since JDK1.1
 */
public CString(char value[]) {
	string = new String(value);
}
/**
 * @since JDK1.1
 */
public CString(char value[], int offset, int count) {
	string = new String(value, offset, count);
}
/**
 *
 */
public CString(CString value) {
	string = value.toString();
}
/**
 * 
 */
public CString(String value) {
	string = (string == null) ? "" : string;
	string = value;
}
/**
 * @since JDK1.1
 */
public CString(StringBuffer buffer) {
	string = new String(buffer);
}
/**
 * Agrega un caracter.
 * @param c char
 */
public void add(char c) {
	string += String.valueOf(c);
}
/**
 * Agrega un CString.
 * @param s com.cosapisoft.util.string.CString
 */
public void add(CString s) {
	string += s.toString();
}
/**
 * Agrega un String.
 * @param s java.lang.String
 */
public void add(String s) {
	string += s;
}
/**
 * Agrega un caracter.
 * @param c char
 * <p>
 * Igual que el método <code>add(char c)</code>.
 */
public void addChar(char c) {
	add(c);
}
/**
 * Agrega "n" caracteres "c".
 * @param n int
 * @param c char
 */
public void addNChars(int n, char c) {
	StringBuffer result = new StringBuffer(string);
	for (int i = 0; i < n; ++i)
		result.append(c);
	string = result.toString();
}
/**
 * Agrega un caracter.
 * @param c char
 * <p>
 * Igual que el método <code>add(char c)</code>.
 */
public void append(char c) {
	add(c);
}
/**
 * Agrega un CString.
 * @param s com.cosapisoft.util.string.CString
 * <p>
 * Igual que el método <code>add(CString s)</code>.
 */
public void append(CString s) {
	add(s);
}
/**
 * Agrega un String.
 * @param s java.lang.String
 * <p>
 * Igual que el método <code>add(String s)</code>.
 */
public void append(String s) {
	add(s);
}
/**
   * Appends a character.
   * <p>
   * Does the same as the method <code>add(char c)</code>.
   */
public void appendChar(char c) {
	add(c);
}
/**
   * Appends n c characters.
   * <p>
   * Does the same as the method <code>addNChars(n, c)</code>.
   */
public void appendNChars(int n, char c) {
	addNChars(n, c);
}
/**
 * Acomoda el CString, eliminando:
 * - Todos los espacios en blanco al inicio y final del CString;
 * - Todos los espacios en blanco antes de un espacio en blanco,
 * coma (,) o periodo ('.', ';').
 */
public void arrange() {
	deleteHeadUntilNot(' ');
	deleteTailUntilNot(' ');
	StringBuffer result = new StringBuffer();
	int len = string.length();
	if (len == 0)
		return;
	char c1 = string.charAt(0), c2;
	for (int i = 1; i < len; ++i) {
		c2 = string.charAt(i);
		if (!((c1 == ' ') && ((c2 == ' ') || (c2 == ',') || (c2 == '.'))))
			result.append(c1);
		c1 = c2;
	}
	result.append(c1);
	string = result.toString();
}
public char charAt(int index) {
	if(string == null || string.equals("") || string.length() >= index || string.length() < 0) return '\0';
	return string.charAt(index);
}
/**
   * Calls <code>check(..,..,..)</code> with:<br>
   * - case sensitivity on;<br>
   * - comma as separator char.
   */
public int check(CString invalidValues) {
	return check(invalidValues, ',', false);
}
/**
   * Calls <code>check(..,..,..)</code> with case sensitivity on.
   */
public int check(CString invalidValues, char separatorChar) {
	return check(invalidValues, separatorChar, false);
}
/**
   * Our CString's value is consired invalid when equal
   * to one of the values specified in invalidValues.
   * <p>
   * The values in invalidValues are separated by separatorChar.<br>
   * When ignoreCase is set to true, all comparisions between our CString
   * and the invalid values are case insensitive.
   *
   * @return -1 if our value is valid
   * or the position of the invalid value inside the invalidValues CString.
   */
public int check(CString invalidValues, char separatorChar, boolean ignoreCase) {
	String curValue; // current value inside invalidValues
	int n, numTokens;
	StringTokenizer st = new StringTokenizer(invalidValues.toString(), String.valueOf(separatorChar));
	numTokens = st.countTokens();
	for (n = 0; n < numTokens; n++) {
		curValue = st.nextToken();
		if ((!ignoreCase) && (string.equals(curValue)))
			// case sensitive
			{ // our string is invalid
			return n; // return the position of the invalid value
		} else
			if (ignoreCase && (string.equalsIgnoreCase(curValue)))
				// case insensitive
				{ // our string is invalid
				return n; // return the position of the invalid value
			}
	}
	return -1; // our string is valid
}
/**
   * Clears our CString.
   */
public void clear() {
	string = "";
}
/**
   * Compacts our CString if it is larger than pos.
   * <p>
   * A compacted string has the form: "..." + <i>shortened string</i>.
   */
public void compact(int pos) {
	if (string.length() > pos) {
		String tmpStr = string.substring(string.length() + 3 - pos, string.length());
		string = "..." + tmpStr;
	}
}
/**
 * Compacta la cadena "cad" por la derecha si tiene una longitud mayor a "length".
 * <p>
 * La cadena compactada tiene la forma de: <i>cadena cortada</i> + "..." 
 * @return java.lang.String
 * @param cad java.lang.String Cadena a compactar
 * @param length int Longitud de la cadena compactada
 */
public static String compact(String cad, int length) {
	cad = (cad == null) ? "" : cad.trim();
	if (cad.length() > length && length > 10) {
		StringBuffer str = new StringBuffer(cad.substring(0, length - 3));
		str.append("...");
		return str.toString();
	}
	return cad;
}
/**
 * Compacta la cadena "cad" por la izquierda si tiene una longitud mayor a "length".
 * <p>
 * La cadena compactada tiene la forma de: "..." + <i>cadena cortada</i> 
 * @return java.lang.String
 * @param cad java.lang.String Cadena a compactar
 * @param length int Longitud de la cadena compactada
 */
public static String compactLeft(String cad, int length) {
	cad = (cad == null) ? "" : cad.trim();
	if (cad.length() > length && length > 10) {
		StringBuffer str = new StringBuffer("...");
		str.append(cad.substring(cad.length() - length + 3));
		return str.toString();
	}
	return cad;
}
/**
 * Compacta la cadena "cad" por la derecha si tiene una longitud mayor a "length".
 * <p>
 * La cadena compactada tiene la forma de: <i>cadena cortada</i> + "..." 
 * @return java.lang.String
 * @param cad java.lang.String Cadena a compactar
 * @param length int Longitud de la cadena compactada
 */
public static String compactRight(String cad, int length) {
	return compact(cad, length);
}
public int compareTo(CString anotherString) {
	return string.compareTo(anotherString.toString());
}
/**
 * Completa la parte izquierda de la cadena "string" con "c" caracteres
 * hasta completar una longitud igual a "longMax".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser rellenada con caracteres
 * @param longMax int Longitud máxima de la cadena a retornar
 * @param c char Caracter a repetir
 */
public static String completeToBeginWithNChars(String string, int longMax, char c) {
	string = (string == null) ? "" : string;
	StringBuffer result = new StringBuffer("");
	int n = longMax - string.length();
	for (; n > 0; n--)
		result.append(c);
	string = result.toString() + string;
	return string;
}
/**
 * Completa la parte derecha de la cadena "string" con "c" caracteres
 * hasta completar una longitud igual a "longMax".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser rellenada con caracteres
 * @param longMax int Longitud máxima de la cadena a retornar
 * @param c char Caracter a repetir
 */
public static String completeToEndWithNChars(String string, int longMax, char c) {
	string = (string == null) ? "" : string;
	StringBuffer result = new StringBuffer("");
	int n = longMax - string.length();
	for (; n > 0; n--)
		result.append(c);
	string += result.toString();
	return string;
}
public CString concat(String str) {
	return valueOf(string.concat(str));
}
public static CString copyValueOf(char data[]) {
	return copyValueOf(data, 0, data.length);
}
public static CString copyValueOf(char data[], int offset, int count) {
	char str[] = new char[count];
	System.arraycopy(data, offset, str, 0, count);
	return new CString(str);
}
/**
   * Returns the number of occurrences of character c in our CString.
   */
public int countOccurences(char c) {
	int count = 0, len = string.length();
	for (int i = 0; i < len; ++i)
		if (string.charAt(i) == c)
			count++;
	return count;
}
/**
   * Returns the number of occurrences of CString s in our CString.
   */
public int countOccurences(CString s) {
	return (countOccurences(s.toString()));
}
/**
   * Returns the number of occurrences of String s in our CString.
   */
public int countOccurences(String s) {
	int count = 0, n = 0, pos;
	while ((pos = string.indexOf(s, n)) != -1) {
		n = pos + s.length();
		count++;
	}
	return count;
}
/**
   * Deletes all c characters.
   */
public void deleteAll(char c) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (string.charAt(i) != c)
			result.append(string.charAt(i));
	string = result.toString();
}
/**
   * Deletes all characters that occur in CString s.
   */
public void deleteAll(CString s) {
	deleteAll(s.toString());
}
/**
   * Deletes all characters that occur in String s.
   */
public void deleteAll(String s) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) == -1)
			result.append(string.charAt(i));
	string = result.toString();
}
/**
 * Elimina todos los caracteres encontrados en el arreglo "c" de la cadena "string".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser modificada.
 * @param c char [] Caracteres a eliminar
 */
public static String deleteAll(String string, char c[]) {
	return CString.deleteAll(string, new String(c));
}
/**
 * Elimina todos los caracteres "c" de la cadena "string".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser modificada.
 * @param c char Caracter a eliminar
 */
public static String deleteAll(String string, char c) {
	return deleteAll(string, "" + c);
}
/**
 * Elimina todos los caracteres encontrados en la cadena "s" de la cadena "string".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser modificada.
 * @param s java.lang.String Caracteres a eliminar
 */
public static String deleteAll(String string, String s) {
	StringBuffer result = new StringBuffer("");
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) == -1)
			result.append(string.charAt(i));
	return result.toString();
}
/**
   * Deletes all characters but those in CString s.
   */
public void deleteAllBut(CString s) {
	deleteAllBut(s.toString());
}
/**
   * Deletes all characters but those in String s.
   */
public void deleteAllBut(String s) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) != -1)
			result.append(string.charAt(i));
	string = result.toString();
}
/**
   * Deletes all newline characters.
   */
public void deleteAllNewlines() {
	deleteAll('\n');
}
/**
   * Deletes all quotes.
   */
public void deleteAllQuotes() {
	deleteAll('\"');
}
/**
   * Deletes all spaces.
   */
public void deleteAllSpaces() {
	deleteAll(' ');
}
/**
   * Deletes the first character.
   * Does the same as the method <code>deleteFirstChar()</code>.
   */
public void deleteChar() {
	deleteFirstChar();
}
/**
   * Deletes the character at the position pos.
   */
public void deleteChar(int pos) {
	deleteNChars(1, pos);
}
/**
   * Deletes the character at the position pos if it is equal to c.
   */
public void deleteCharIf(int pos, char c) {
	if (charAt(pos) == c)
		deleteChar(pos);
}
/**
   * Deletes the first character.
   */
public void deleteFirstChar() {
	deleteNChars(1, 0);
}
/**
   * Deletes the first character if equal to c.
   */
public void deleteFirstCharIf(char c) {
	if (firstChar() == c && firstChar() != '\0')
		deleteFirstChar();
}
/**
   * Deletes first n characters.
   * Does the same as the method <code>deleteNChars(int n)</code>.
   */
public void deleteFirstNChars(int n) {
	deleteNChars(n);
}
/**
   * Deletes characters until character c is found.
   * Starts from the beginning of our CString.
   */
public void deleteHeadUntil(char c) {
	while (firstChar() != c && firstChar() != '\0')
		deleteFirstChar();
}
/**
   * Deletes characters until a different character than c is found.
   * Starts from the beginning of our CString.
   */
public void deleteHeadUntilNot(char c) {
	while (firstChar() == c && firstChar() != '\0')
		deleteFirstChar();
}
/**
   * Deletes the last character.
   */
public void deleteLastChar() {
	deleteLastNChars(1);
}
/**
   * Deletes the last character if it is equal to c.
   */
public void deleteLastCharIf(char c) {
	if (lastChar() == c && lastChar() != '\0')
		deleteLastChar();
}
/**
   * Deletes the last character if newline.
   */
public void deleteLastCharIfNewline() {
	if (lastChar() == '\n' && lastChar() != '\0')
		deleteLastChar();
}
/**
   * Deletes last n characters.
   */
public void deleteLastNChars(int n) {
	String tmpStr = string.substring(0, string.length() - n);
	string = tmpStr;
}
/**
   * Deletes the first n characters
   * By other words, deletes n characters starting at the first position.
   */
public void deleteNChars(int n) {
	deleteNChars(n, 0);
}
/**
   * Deletes n characters starting at the position pos.
   */
public void deleteNChars(int n, int pos) {
	String tmpStr1 = "";
	if (string.length() > pos && pos > 0)
		tmpStr1 = string.substring(0, pos);
	String tmpStr2 = "";
	if (string.length() > pos + n && pos >= 0 && n > 0)
		tmpStr2 = string.substring(pos + n, string.length());
	string = tmpStr1 + tmpStr2;
}
/**
   * If our ZString has a '(' at the beginning and a ')' at the end
   * then these two parenthesis are deleted and a <i>true</i> value is returned.
   */
public boolean deleteParenthesis() {
	if (!hasParenthesis())
		return false;
	deleteFirstChar();
	deleteLastChar();
	return true;
}
/**
   * If our ZString has a quote at the beginning and another one at the end
   * then the two quotes are deleted and a true value is returned.
   */
public boolean deleteQuotes() {
	if (!hasQuotes())
		return false;
	deleteFirstChar();
	deleteLastChar();
	return true;
}
/**
   * Deletes characters until character c is found.
   * Starts from the end of our ZString.
   */
public void deleteTailUntil(char c) {
	while (lastChar() != c && lastChar() != '\0')
		deleteLastChar();
}
/**
   * Deletes characters until a different character than c is found.
   * Starts from the end of our ZString.
   */
public void deleteTailUntilNot(char c) {
	while (lastChar() == c && lastChar() != '\0')
		deleteLastChar();
}
public boolean endsWith(CString suffix) {
	return string.endsWith(suffix.toString());
}
public boolean equals(Object anObject) {
	return string.equals(anObject);
}
public boolean equalsIgnoreCase(CString anotherString) {
	return string.equalsIgnoreCase(anotherString.toString());
}
/**
 * Agrega "n" caracteres "c" a la parte izquierda de la cadena "string".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser rellenada con caracteres
 * @param n int Nro. de caracteres a rellenar
 * @param c char Caracter a repetir
 */
public static String fillToBeginWithNChars(String string, int n, char c) {
	string = (string == null) ? "" : string;
	StringBuffer result = new StringBuffer("");
	for (; n > 0; n--)
		result.append(c);
	string = result.toString() + string;
	return string;
}
/**
 * Agrega "n" caracteres "c" a la parte derecha de la cadena "string".
 * @return java.lang.String
 * @param string java.lang.String Cadena a ser rellenada con caracteres
 * @param n int Nro. de caracteres a rellenar
 * @param c char Caracter a repetir
 */
public static String fillToEndWithNChars(String string, int n, char c) {
	string = (string == null) ? "" : string;
	StringBuffer result = new StringBuffer("");
	for (; n > 0; n--)
		result.append(c);
	string += result.toString();
	return string;
}
/**
   * Fills our CString with c characters.
   * Deletes the original CString's text! Maintains its size.
   */
public void fillWithChars(char c) {
	fillWithNChars(string.length(), c);
}
/**
   * Fills our CString with n c characters.
   * Deletes the original CString's text!
   */
public void fillWithNChars(int n, char c) {
	StringBuffer result = new StringBuffer();
	for (; n > 0; n--)
		result.append(c);
	string = result.toString();
}
/**
   * Returns the position of a digit in our ZString.
   *
   * @return -1 if no digits found.
   */
public int findADigit() {
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (Character.isDigit(string.charAt(i)))
			return i;
	return -1;
}
/**
   * Returns the position of anything but a digit in our CString.
   *
   * @return -1 if only digits found.
   */
public int findButADigit() {
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (!Character.isDigit(string.charAt(i)))
			return i;
	return -1;
}
/**
   * Returns the position of the first occurence of character c.
   * <p>
   * Starts the search at the position pos.
   *
   * @return -1 if no c characters found.
   */
public int findNearestCharAt(char c, int pos) {
	int leftPos = findNearestCharAtLeft(c, pos), rightPos = findNearestCharAtRight(c, pos);
	return (((pos - leftPos) <= (rightPos - pos)) ? leftPos : rightPos);
}
/**
   * Returns the position of the first occurence of character c.
   * <p>
   * Starts the search at the beginning of our ZString.
   *
   * @return -1 if no c characters found.
   */
public int findNearestCharAtBeginning(char c) {
	return (string.indexOf(c));
}
/**
   * Returns the position of the first occurence of character c.
   * <p>
   * Starts the search at the end of our ZString.
   *
   * @return -1 if no c characters found.
   */
public int findNearestCharAtEnd(char c) {
	return (findNearestCharAtLeft(c, string.length() - 1));
}
/**
   * Returns the position of the first occurence of character c.
   * <p>
   * Starts the search at the position pos and going to the left.
   *
   * @return -1 if no c characters found.
   */
public int findNearestCharAtLeft(char c, int pos) {
	for (int n = pos; n >= 0; n--)
		if (string.charAt(n) == c)
			return n;
	return -1;
}
/**
   * Returns the position of the first occurence of character c.
   * <p>
   * Starts the search at the position pos and going to the right.
   *
   * @return -1 if no c characters found.
   */
public int findNearestCharAtRight(char c, int pos) {
	int len = string.length();
	for (int n = pos; n < len; n++)
		if (string.charAt(n) == c)
			return n;
	return -1;
}
/**
   * Returns the position of the first character different than c.
   * <p>
   * Starts the search at the position pos.
   *
   * @return -1 if only c characters found.
   */
public int findNearestDifferentCharAt(char c, int pos) {
	int leftPos = findNearestDifferentCharAtLeft(c, pos), rightPos = findNearestDifferentCharAtRight(c, pos);
	return (((pos - leftPos) <= (rightPos - pos)) ? leftPos : rightPos);
}
/**
   * Returns the position of the first character different than c.
   * <p>
   * Starts the search at the beginning of our ZString.
   *
   * @return -1 if only c characters found.
   */
public int findNearestDifferentCharAtBeginning(char c) {
	return (findNearestDifferentCharAtRight(c, 0));
}
/**
   * Returns the position of the first character different than c.
   * <p>
   * Starts the search at the end of our ZString.
   *
   * @return -1 if only c characters found.
   */
public int findNearestDifferentCharAtEnd(char c) {
	return (findNearestDifferentCharAtLeft(c, string.length() - 1));
}
/**
   * Returns the position of the first character different than c.
   * <p>
   * Starts the search at the position pos and going to the left.
   *
   * @return -1 if only c characters found.
   */
public int findNearestDifferentCharAtLeft(char c, int pos) {
	for (int n = pos; n >= 0; n--)
		if (string.charAt(n) != c)
			return n;
	return -1;
}
/**
   * Returns the position of the first character different than c.
   * <p>
   * Starts the search at the position pos and going to the right.
   *
   * @return -1 if only c characters found.
   */
public int findNearestDifferentCharAtRight(char c, int pos) {
	int len = string.length();
	for (int i = pos; i < len; ++i)
		if (string.charAt(i) != c)
			return i;
	return -1;
}
/**
   * Returns the first character of our ZString.
   *
   * @return '\0' if our ZString is empty.
   */
public char firstChar() {
	if (string.length() > 0)
		return string.charAt(0);
	else
		return '\0';
}
/**
   * Returns the first half of our CString.
   */
public CString firstHalf() {
	return new CString(string.substring(0, string.length() / 2));
}
/**
   * Returns the first n characters.
   */
public CString firstNChars(int n) {
	if(n < 1 || n > string.length())
		return new CString("");
	if(n == string.length())
		return new CString(string);
	return new CString(string.substring(0, n));
}
/**
 * @since JDK1.1
 */
public byte[] getBytes() {
	return string.getBytes();
}
/**
 * @deprecated This method does not properly convert characters into bytes.
 *             As of JDK version 1.1, the preferred way to do this is via
 *             the <code>getBytes()</code> methods that take a
 *             character-encoding name, or that use the
 *             platform's default encoding.
 */
public byte[] getBytes(String enc) throws UnsupportedEncodingException {
	return string.getBytes(enc);
}
public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin) {
	string.getChars(srcBegin, srcEnd, dst, dstBegin);
}
/**
   * Returns a String with all email addresses found.
   */
public String getEmailAddresses() {
	boolean atFound = false, dotFound = false;
	int startPos = 0, atPos = 0;
	String line = string + " ", emailAddresses = "";
	for (int i = 0; i < line.length(); i++) {
		switch (line.charAt(i)) {
			// possible separators
			case '\u005b' : // left square bracket
			case '\u005d' : // right square bracket
			case '\u007b' : // left curly bracket
			case '\u007c' : // vertical line
			case '\u007d' : // right curly bracket
			case '\u00a0' : // no-break space
			case '\u00a6' : // broken bar
			case '\u00a9' : // copyright sign
			case '\u00ab' : // left guillemot
			case '\u00ae' : // registered trade mark sign
			case '\u00b6' : // paragraph sign
			case '\u00b7' : // middle dot
			case '\u00bb' : // right guillemot
			case '<' :
			case '>' :
			case ' ' :
			case '(' :
			case ')' :
			case '/' :
			case ',' :
			case ';' :
			case ':' :
			case '\b' : // backspace
			case '\f' : // form feed
			case '\n' : // newline
			case '\r' : // carriage return
			case '\t' : // tab
			case '\\' : // backslash
			case '\"' : // double quote
			case '\'' : // single quote
				if (atFound && dotFound) {
					// may be an email address
					emailAddresses += line.substring(startPos, i);
					emailAddresses += "\n";
				}

				// start again from the next position
				startPos = i + 1;
				atFound = dotFound = false;
				break;
			case '.' :
				// good...
				dotFound = true;
				break;
			case '@' :
				if (atFound)
					startPos = atPos + 1; // hum...

				atPos = i;
				atFound = true;
				break;
		}
	}
	return emailAddresses;
}
public int hashCode() {
	return string.hashCode();
}
/**
   * Returns <i>true</i> if our ZString has a '(' at the beginning and a ')' at the end.
   */
public boolean hasParenthesis() {
	String tmpStr = string;
	return (firstChar() == '(') && (lastChar() == ')');
}
/**
   * Returns <i>true</i> if our ZString has two quotes - one at the beginning
   * and another at the end.
   */
public boolean hasQuotes() {
	String tmpStr = string;
	return (firstChar() == '\"') && (lastChar() == '\"');
}
/**
   * Returns <code>true</code> if our ZString holds email addresses.
   */
public boolean holdsAnEmailAddress() {
	String emailAddresses = getEmailAddresses();
	return (emailAddresses.length() > 0);
}
public int indexOf(int ch) {
	return string.indexOf(ch);
}
public int indexOf(int ch, int fromIndex) {
	return string.indexOf(ch, fromIndex);
}
public int indexOf(String str) {
	return string.indexOf(str);
}
public int indexOf(String str, int fromIndex) {
	return string.indexOf(str, fromIndex);
}
/**
   * Returns the index within our ZString of the first occurrence of the specified substring.
   * <p>
   * If the boolean ignoreCase is true, upper case characters are considered equivalent
   * to lower case characters.
   *
   * @param str  The substring to search for.
   *
   * @return -1 if the index is not found.
   */
public int indexOf(boolean ignoreCase, String str) {
	return indexOf(ignoreCase, str, 0);
}
/**
   * Returns the index within our ZString of the first occurrence of the specified substring.
   * <p>
   * Starts the search at fromIndex.<br>
   * If the boolean ignoreCase is true, upper case characters are considered equivalent
   * to lower case characters.
   *
   * @param str        The substring to search for.
   * @param fromIndex  The index to start the search from.
   *
   * @return -1 if the index is not found.
   */
public int indexOf(boolean ignoreCase, String str, int fromIndex) {
	int strPos = 0;
	for (int n = fromIndex; n < string.length(); n++) {
		if ((string.charAt(n) == str.charAt(strPos)) || (ignoreCase && (Character.toLowerCase(string.charAt(n)) == Character.toLowerCase(str.charAt(strPos))))) {
			if (strPos == (str.length() - 1))
				return (n - strPos);
			strPos++;
		} else
			strPos = 0;
	}
	return -1;
}
/**
   * Inserts the character c at the first position.
   */
public void insert(char c) {
	insert(c, 0);
}
/**
   * Inserts the character c at the position pos.
   */
public void insert(char c, int pos) {
	insert(String.valueOf(c), pos);
}
/**
   * Inserts the CString s at the first position.
   */
public void insert(CString s) {
	insert(s, 0);
}
/**
   * Inserts the CString s at the position pos.
   */
public void insert(CString s, int pos) {
	insert(s.toString(), pos);
}
/**
   * Inserts the String s at the first position.
   */
public void insert(String s) {
	insert(s, 0);
}
/**
   * Inserts the String s at the position pos.
   */
public void insert(String s, int pos) {
	if(pos < 0 || pos >= string.length()) return;
	String tmpStr1 = string.substring(0, pos);
	String tmpStr2 = string.substring(pos, string.length());
	string = tmpStr1 + s + tmpStr2;
}
/**
   * Inserts the c character at the first position.
   * <p>
   * Does the same as the method <code>insert(char c)</code>.
   */
public void insertChar(char c) {
	insert(c);
}
/**
   * Inserts the character c at the position pos.
   * <p>
   * Does the same as the method <code>insert(char c, int pos)</code>.
   */
public void insertChar(char c, int pos) {
	insert(c, pos);
}
/**
   * Inserts n c characters at the first position.
   */
public void insertNChars(int n, char c) {
	insertNChars(n, c, 0);
}
/**
   * Inserts n c characters at the position pos.
   */
public void insertNChars(int n, char c, int pos) {
	for (int i = 0; i < n; i++)
		insertChar(c, pos);
}
/**
   * Inserts the newline character at the first position.
   */
public void insertNewlineChar() {
	insertNewlineChar(0);
}
/**
   * Inserts the newline character at the position pos.
   */
public void insertNewlineChar(int pos) {
	insert("\n", pos);
}
/**
   * Inserts a '(' before our ZString and a ')' after it.
   */
public void insertParenthesis() {
	String tmpStr = string;
	string = "(" + tmpStr + ")";
}
/**
   * Inserts a quote at the beginning of our ZString and another one at the end.
   */
public void insertQuotes() {
	String tmpStr = string;
	string = "\"" + tmpStr + "\"";
}
public CString intern() {
	return valueOf(string.intern());
}
/**
 * Retorna true (verdadero) si el valor de "value" es TRUE, true, FALSE, false
 * @return boolean
 * @param value java.lang.String
 */
public static boolean isBoolean(String value) {
	if (!isEmptyOrNull(value)) {
		value = value.toLowerCase();
		if ((value.equals("true")) || (value.equals("false")))
			return (true);
	}
	return (false);
}
/**
   * Returns <i>true</i> if there is a c character at the position pos.
   */
public boolean isCharAt(char c, int pos) {
	return (string.charAt(pos) == c);
}
/**
 * Retorna true (verdadero) si el valor de "value" es numérico y en punto flotante
 * @return boolean
 * @param value java.lang.String
 */
public static boolean isDouble(String value) {
	try {
		if (value.charAt(0) == '-') {
			Double.valueOf(value.substring(1)).doubleValue();
		} else {
			Double.valueOf(value).doubleValue();
		}
	} catch (Exception e) {
		return (false);
	}
	return (true);
}
/**
 * Retorna true (verdadero) si el valor de "string" es NULL o de longitud cero
 * @return boolean
 * @param string java.lang.String
 */
public static boolean isEmptyOrNull(String string) {
	return ((string == null) || (string.length() == 0));
}
/**
 * Retorna true (verdadero) si el valor de "one" es igual a "other".
 * Ademas evalua si tienen valores nulos.
 * @return boolean
 * @param one java.lang.String
 * @param other java.lang.String
 */
public static boolean isEqualTo(String one, String other) {
	if (one == null) {
		return (other == null);
	}
	return ((other != null) && (one.equals(other)));
}
/**
 * Retorna true (verdadero) si el valor de "value" es numérico
 * @return boolean
 * @param value java.lang.String
 */
public static boolean isInteger(String value) {
	try {
		if (value.charAt(0) == '-') {
			Integer.parseInt(value.substring(1));
		} else {
			Integer.parseInt(value);
		}
	} catch (Exception e) {
		return (false);
	}
	return (true);
}
/**
   * Returns <i>true</i> if there is a newline character at the position pos.
   */
public boolean isNewlineAt(int pos) {
	return isCharAt('\n', pos);
}
/**
   * Returns <i>true</i> if there is a quote at the position pos.
   */
public boolean isQuoteAt(int pos) {
	return isCharAt('\"', pos);
}
/**
   * Returns <i>true</i> if there is a space at the position pos.
   */
public boolean isSpaceAt(int pos) {
	return isCharAt(' ', pos);
}
/**
   * Returns the last character of our ZString.
   *
   * @return '\0' if our ZString is empty.
   */
public char lastChar() {
	if (string.length() > 0)
		return string.charAt(string.length() - 1);
	else
		return '\0';
}
public int lastIndexOf(int ch) {
	return string.lastIndexOf(ch);
}
public int lastIndexOf(int ch, int fromIndex) {
	return string.lastIndexOf(ch, fromIndex);
}
public int lastIndexOf(String str) {
	return string.lastIndexOf(str);
}
public int lastIndexOf(String str, int fromIndex) {
	return string.lastIndexOf(str, fromIndex);
}
/**
   * Returns the index within our ZString of the last occurrence of the specified substring.
   * <p>
   * Upper case characters are considered equivalent to lower case characters.
   * <p>
   * Our ZString is searched backwards starting at fromIndex.
   *
   * @param str        The substring to search for.
   * @param fromIndex  The index to start the search from.
   *
   * @return -1 if the index is not found.
   */
public int lastIndexOf(boolean ignoreCase, String str) {
	return lastIndexOf(ignoreCase, str, string.length() - 1);
}
/**
   * Returns the index within our ZString of the last occurrence of the specified substring.
   * <p>
   * If the boolean ignoreCase is true, upper case characters are considered equivalent
   * to lower case characters.
   * <p>
   * Our ZString is searched backwards starting at fromIndex.
   *
   * @param str        The substring to search for.
   * @param fromIndex  The index to start the search from.
   *
   * @return -1 if the index is not found.
   */
public int lastIndexOf(boolean ignoreCase, String str, int fromIndex) {
	int strInitPos = str.length() - 1, strPos = strInitPos;
	for (int n = fromIndex; n >= 0; n--) {
		if ((string.charAt(n) == str.charAt(strPos)) || (ignoreCase && (Character.toLowerCase(string.charAt(n)) == Character.toLowerCase(str.charAt(strPos))))) {
			if (strPos == 0)
				return (n - strPos);
			strPos--;
		} else
			strPos = strInitPos;
	}
	return -1;
}
/**
   * Returns the last n characters.
   */
public CString lastNChars(int n) {
	return new CString(string.substring(string.length() - n, string.length()));
}
public int length() {
	return string.length();
}
/**
 * Retorna true (verdadero) si la diferencia del valor de "a" y de "b" es menor
 * que el 10% del Max(a,b).
 * @return boolean
 * @param a double
 * @param b double
 */
public static final boolean lessThan10PercentDiff(double a, double b) {
	double maxDiff = Math.abs(Math.max(a, b) * 0.10);
	//System.out.println("maxDiff = " + maxDiff + ", a = " + a + ", b = " + b);
	if ((a - b > maxDiff) || (b - a > maxDiff))
		return (false);
	return (true);
}
/**
 * This method was created in VisualAge.
 * @param args java.lang.String[]
 */
public static void main(String args[]) {
	CString cstring = new CString("Texto de Prueba");
	// System.out.println("CString : " + cstring.toString());
	cstring.reverse();
	// System.out.println("Reverse : " + cstring.toString());
	cstring.deleteHeadUntil('e');
	// System.out.println("DeleteHeadUntil('e') : " + cstring.toString());
	cstring.deleteHeadUntilNot('d');
	// System.out.println("DeleteHeadUntilNot('d') : " + cstring.toString());
	cstring.add(" continua...");
	// System.out.println("Add : " + cstring.toString());
	cstring.insertParenthesis();
	// System.out.println("insertParenthesis() : " + cstring.toString());
	//
	// System.out.println(CString.toCommaFormattedString(1545512225));
	// System.out.println(CString.toHexString(1222556446));
	// System.out.println(CString.toHtmlString("La suma : 2 + 3 = 4, está equivocada? - &"));
	//
	// System.out.println(CString.compactLeft("Ejemplo de compactación de cadena", 15));
	// System.out.println(CString.compactRight("Ejemplo de compactación de cadena", 15));
	// System.out.println(CString.completeToBeginWithNChars("21554", 10, '0'));
	// System.out.println(CString.completeToEndWithNChars("21554", 10, '0'));
	// System.out.println(CString.fillToBeginWithNChars("12345795", 5, '0'));
	// System.out.println(CString.fillToEndWithNChars("12345795", 5, '0'));
	// System.out.println(CString.deleteAll("This method was created in VisualAge", 'a'));
	// System.out.println(CString.deleteAll("This method was created in VisualAge", new char[] {'i', 'e', 's'}));
	//
	byte b[] = "This method was created in VisualAge".getBytes();
	String hexStr = CString.toHexString(b);
	// System.out.println("----");
	// System.out.println(hexStr);
	// System.out.println("----");
	// System.out.println(CString.toStringOfHex(hexStr));

	//System.out.println(Integer.valueOf("10", 16).intValue());
	//System.out.println(Integer.parseInt("10", 16));
}
/**
   * Deletes the character c from the end of our ZString.
   * <p>
   * Does the same as the method <code>subtract(char c)</code>.
   */
public void minus(char c) {
	subtract(c);
}
/**
   * Deletes the CString s from the end of our CString.
   * <p>
   * Does the same as the method <code>subtract(CString s)</code>.
   */
public void minus(CString s) {
	subtract(s);
}
/**
   * Deletes the String s from the end of our ZString.
   * <p>
   * Does the same as the method <code>subtract(String s)</code>.
   */
public void minus(String s) {
	subtract(s);
}
/**
   * Deletes the character c from the end of our ZString.
   * <p>
   * Does the same as the method <code>subtract(char c)</code>.
   */
public void minusChar(char c) {
	subtract(c);
}
/**
   * Returns n characters starting at the position pos.
   */
public CString nChars(int n, int pos) {
	return new CString(string.substring(pos, pos + n));
}
/**
 * Retorna el nro de ocurrencias del caracter "ch" en la cadena "str".
 * @return int
 * @param str java.lang.String
 * @param ch char
 */
public static int numOccurancesOf(String str, char ch) {
	if (isEmptyOrNull(str))
		return 0;
	return (numOccurancesOf(str, ch, 0, str.length()));
}
/**
 * Retorna el nro de ocurrencias del caracter "ch" en la cadena "str", 
 * empezando desde la posición "fromIndex".
 * @return int
 * @param str java.lang.String
 * @param ch char
 * @param fromIndex int Posición inicial
 */
public static int numOccurancesOf(String str, char ch, int fromIndex) {
	if (isEmptyOrNull(str))
		return 0;
	return numOccurancesOf(str, ch, fromIndex, str.length());
}
/**
 * Retorna el nro de ocurrencias del caracter "ch" en la cadena "str", 
 * empezando desde la posición "fromIndex" hasta "endIndex".
 * @return int
 * @param str java.lang.String
 * @param ch char
 * @param fromIndex int Posición inicial
 * @param endIndex int Posición final
 */
public static int numOccurancesOf(String str, char ch, int fromIndex, int endIndex) {
	if (isEmptyOrNull(str))
		return 0;
	int index;
	int num = 0;
	while ((index = str.indexOf(ch, fromIndex)) != -1) {
		fromIndex = index + 1;
		if (index >= endIndex)
			return (num);
		++num;
	}
	return (num);
}
public static java.awt.Color parseColorString(String colorString) {
	if (colorString == null)
		return java.awt.Color.black;
	if (colorString.equalsIgnoreCase("black"))
		return java.awt.Color.black;
	if (colorString.equalsIgnoreCase("blue"))
		return java.awt.Color.blue;
	if (colorString.equalsIgnoreCase("white"))
		return java.awt.Color.white;
	if (colorString.equalsIgnoreCase("yellow"))
		return java.awt.Color.yellow;
	if (colorString.equalsIgnoreCase("gray"))
		return java.awt.Color.gray;
	if (colorString.equalsIgnoreCase("cyan"))
		return java.awt.Color.cyan;
	if (colorString.equalsIgnoreCase("magenta"))
		return java.awt.Color.magenta;
	if (colorString.equalsIgnoreCase("orange"))
		return java.awt.Color.orange;
	if (colorString.equalsIgnoreCase("pink"))
		return java.awt.Color.pink;
	if (colorString.equalsIgnoreCase("green"))
		return java.awt.Color.green;
	if (colorString.equalsIgnoreCase("red"))
		return java.awt.Color.red;
	try {
		if (colorString.indexOf('#') == 0)
			colorString = colorString.replace('#', ' ').trim();
		if (colorString.length() == 6) {
			int R = Integer.valueOf(colorString.substring(0, 2), 16).intValue();
			int G = Integer.valueOf(colorString.substring(2, 4), 16).intValue();
			int B = Integer.valueOf(colorString.substring(4, 6), 16).intValue();
			return new java.awt.Color(R, G, B);
		}
		return java.awt.Color.black;
	}
	catch (Exception e) {
		return java.awt.Color.black;
	}
}
/**
   * Appends a character.
   * <p>
   * Does the same as the method <code>add(char c)</code>.
   */
public void plus(char c) {
	add(c);
}
/**
   * Appends a CString.
   * <p>
   * Does the same as the method <code>add(CString s)</code>.
   */
public void plus(CString s) {
	add(s);
}
/**
   * Appends a String.
   * <p>
   * Does the same as the method <code>add(String s)</code>.
   */
public void plus(String s) {
	add(s);
}
/**
   * Appends a character.
   * <p>
   * Does the same as the method <code>add(char c)</code>.
   */
public void plusChar(char c) {
	add(c);
}
/**
   * Appends n c characters.
   * <p>
   * Does the same as the method <code>addNChars(n, c)</code>.
   */
public void plusNChars(int n, char c) {
	addNChars(n, c);
}
public boolean regionMatches(int toffset, CString other, int ooffset, int len) {
	return string.regionMatches(toffset, other.toString(), ooffset, len);
}
public boolean regionMatches(boolean ignoreCase, int toffset, CString other, int ooffset, int len) {
	return string.regionMatches(ignoreCase, toffset, other.toString(), ooffset, len);
}
public CString replace(char oldChar, char newChar) {
	return valueOf(string.replace(oldChar, newChar));
}
/**
 * Reemplaza el caracter "c1" por el caracter "c2".
 * @return java.lang.String
 * @param str java.lang.String
 * @param c1 char
 * @param c2 char
 */
public static String replaceChar(String str, char c1, char c2) {
	return replaceChar(str, c1, "" + c2);
}
/**
 * Reemplaza el caracter "c1" por cadena "s2".
 * @return java.lang.String
 * @param str java.lang.String
 * @param c1 char
 * @param s2 java.lang.String
 */
public static String replaceChar(String str, char c1, String s2) {
	str = (str == null) ? "" : str;
	s2 = (s2 == null) ? "" : s2;
	StringBuffer strB = new StringBuffer("");
	for (int i = 0; i < str.length(); i++) {
		if (str.charAt(i) == c1)
			strB.append(s2);
		else
			strB.append(str.charAt(i));
	}
	return strB.toString();
}
/**
 * Reemplaza la cadena "s1" por el caracter "c2".
 * @return java.lang.String
 * @param str java.lang.String
 * @param s1 java.lang.String
 * @param c2 char
 */
public static String replaceString(String str, String s1, char c2) {
	return replaceString(str, s1, "" + c2);
}
/**
 * Reemplaza la cadena "s1" por la cadena "s2".
 * @return java.lang.String
 * @param str java.lang.String
 * @param s1 java.lang.String
 * @param s2 java.lang.String
 */
public static String replaceString(String str, String s1, String s2) {
	str = (str == null) ? "" : str;
	s1 = (s1 == null) ? "" : s1;
	s2 = (s2 == null) ? "" : s2;
	if (s1.equals(""))
		return str;
	int pos = str.indexOf(s1);
	while (pos >= 0) {
		if (pos > 0) {
			str = str.substring(0, pos) + s2 + str.substring(pos + s1.length());
		}
		else {
			str = "" + s2 + str.substring(s1.length());
		}
		pos = str.indexOf(s1);
	}
	return str;
}
/**
   * Reverses our ZString.
   */
public void reverse() {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = len - 1; i >= 0; i--)
		result.append(string.charAt(i));
	string = result.toString();
}
/**
   * Rotates all characters to the left.
   */
public void rotateLeft() {
	char c = firstChar();
	deleteFirstChar();
	plus(c);
}
/**
   * Rotates all characters to the right.
   */
public void rotateRight() {
	char c = lastChar();
	deleteLastChar();
	insert(c, 0);
}
/**
 * Retorna el valor redondeado de "value"
 * @return long
 * @param value double
 */
public static long round(double value) {
	if (value > 0)
		return ((long) (value + 0.5));
	return ((long) (value - 0.5));
}
/**
   * Returns the second half of our CString.
   */
public CString secondHalf() {
	return new CString(string.substring(string.length() / 2, string.length()));
}
/**
   * Gives a new value to our ZString.
   */
public void set(char c) {
	string = String.valueOf(c);
}
/**
   * Gives a new value to our CString.
   */
public void set(CString s) {
	string = s.toString();
}
/**
   * Gives a new value to our ZString.
   */
public void set(String s) {
	string = s;
}
/**
   * Shifts all characters to the left.
   */
public void shiftLeft() {
	deleteFirstChar();
}
/**
   * Shifts all characters to the left and inserts the specified character
   * at the rightmost position - the last position.
   */
public void shiftLeft(char c) {
	deleteFirstChar();
	plus(c);
}
/**
   * Shifts all characters to the right.
   */
public void shiftRight() {
	deleteLastChar();
}
/**
   * Shifts all characters to the right and inserts the specified character
   * at the leftmost position - the first position.
   */
public void shiftRight(char c) {
	deleteLastChar();
	insert(c, 0);
}
/**
   * Shortens our CString if it is larger than pos.
   */
public void shorten(int pos) {
	if (string.length() > pos) {
		String tmpStr = string.substring(string.length() - pos, string.length());
		string = tmpStr;
	}
}
/**
 * Ordena alfabeticamente el arreglo "list"
 * @param list java.lang.String[]
 */
public static void sortAlphabetically(String[] list) {
	boolean unsorted = true;
	while (unsorted) {
		unsorted = false;
		for (int i = 0; i < list.length - 1; ++i) {
			String str1 = list[i];
			if (str1.compareTo(list[i + 1]) > 0) {
				list[i] = list[i + 1];
				list[i + 1] = str1;
				unsorted = true;
			}
		}
	}
}
public boolean startsWith(CString prefix) {
	return string.startsWith(prefix.toString());
}
public boolean startsWith(CString prefix, int toffset) {
	return string.startsWith(prefix.toString(), toffset);
}
public CString substring(int beginIndex) {
	return valueOf(string.substring(beginIndex));
}
public CString substring(int beginIndex, int endIndex) {
	return valueOf(string.substring(beginIndex, endIndex));
}
/**
   * Deletes characters until character c is found.
   * Starts from the end of our CString.
   */
public String substringHeadUntil(char c) {
	StringBuffer buffer = new StringBuffer("");
	while (firstChar() != c && firstChar() != '\0') {
		buffer.append(firstChar());
		deleteFirstChar();
	}
	return buffer.toString();
}
/**
   * Deletes characters until character c is found.
   * Starts from the end of our CString.
   */
public String substringHeadUntilNot(char c) {
	StringBuffer buffer = new StringBuffer("");
	while (firstChar() == c && firstChar() != '\0') {
		buffer.append(firstChar());
		deleteFirstChar();
	}
	return buffer.toString();
}
/**
   * Deletes characters until character c is found.
   * Starts from the end of our CString.
   */
public String substringTailUntil(char c) {
	StringBuffer buffer = new StringBuffer("");
	while (lastChar() != c && lastChar() != '\0'){
		buffer.append(lastChar());
		deleteLastChar();
	}
	return buffer.toString();
}
/**
   * Deletes characters until character c is found.
   * Starts from the end of our CString.
   */
public String substringTailUntilNot(char c) {
	StringBuffer buffer = new StringBuffer("");
	while (lastChar() == c && lastChar() != '\0'){
		buffer.append(lastChar());
		deleteLastChar();
	}
	return buffer.toString();
}
/**
   * Deletes the character c from the end of our ZString.
   */
public void subtract(char c) {
	deleteLastCharIf(c);
}
/**
   * Deletes the CString s from the end of our CString.
   */
public void subtract(CString s) {
	subtract(s.toString());
}
/**
   * Deletes the String s from the end of our ZString.
   */
public void subtract(String s) {
	if (string.endsWith(s))
		deleteLastNChars(s.length());
}
/**
   * Deletes the character c from the end of our ZString.
   * <p>
   * Does the same as the method <code>subtract(char c)</code>.
   */
public void subtractChar(char c) {
	subtract(c);
}
/**
 * Retorna el valor booleano de "value"
 * @return boolean
 * @param value java.lang.String
 */
public static boolean toBoolean(String value) {
	if (isEmptyOrNull(value))
		return false;
	return (Boolean.valueOf(value).booleanValue());
}
/**
 * Convierte un cadena de hexadecimales "valueHex" a un arreglo de bytes.
 * @return byte[]
 * @param valueHex java.lang.String
 */
public static byte[] toBytesOfHex(String valueHex) {
	return (toStringOfHex(valueHex).getBytes());
}
public char[] toCharArray() {
	return string.toCharArray();
}
public static String toCommaFormattedString(int value) {
	String str = new String();
	String digits;
	boolean hasDigits = false;
	if (value > 1000000000) {
		str += String.valueOf(value / 1000000000);
		str += ",";
		hasDigits = true;
	}
	if (value > 1000000) {
		digits = String.valueOf((value % 1000000000) / 1000000);
		while (hasDigits && (digits.length() < 3))
			digits = "0" + digits;
		str += digits + ",";
		hasDigits = true;
	}
	if (value > 1000) {
		digits = String.valueOf((value % 1000000) / 1000);
		while ((hasDigits) && (digits.length() < 3))
			digits = "0" + digits;
		str += digits + ",";
		hasDigits = true;
	}
	digits = String.valueOf(value % 1000);
	while ((hasDigits) && (digits.length() < 3))
		digits = "0" + digits;
	str += digits;
	return (str);
}
public static String toCommaFormattedString(long value) {
	String str = new String();
	String digits;
	boolean hasDigits = false;
	if (value > 1000000000000000L) {
		str += String.valueOf(value / 1000000000000000L);
		str += ",";
		hasDigits = true;
	}
	if (value > 1000000000000L) {
		digits = String.valueOf(value / 1000000000000L);
		while ((hasDigits) && (digits.length() < 3))
			digits = "0" + digits;
		hasDigits = true;
	}
	if (value > 1000000000) {
		digits = String.valueOf(value / 1000000000);
		while ((hasDigits) && (digits.length() < 3))
			digits = "0" + digits;
		str += digits + ",";
		hasDigits = true;
	}
	if (value > 1000000) {
		digits = String.valueOf((value % 1000000000) / 1000000);
		while ((hasDigits) && (digits.length() < 3))
			digits = "0" + digits;
		str += digits + ",";
		hasDigits = true;
	}
	if (value > 1000) {
		digits = String.valueOf((value % 1000000) / 1000);
		while ((hasDigits) && (digits.length() < 3))
			digits = "0" + digits;
		str += digits + ",";
		hasDigits = true;
	}
	digits = String.valueOf(value % 1000);
	while ((hasDigits) && (digits.length() < 3))
		digits = "0" + digits;
	str += digits;
	return (str);
}
public CString toCString() {
	return this;
}
/**
 * Retorna la fecha actual (dd/mm/yyyy)
 * @return java.lang.String
 */
public static String toDate() {
	return toDate("dd/MM/yyyy");
}
/**
 * Retorna la fecha actual, cuyo formato esta dado por "format".
 * @return java.lang.String
 * @param format java.lang.String
 */
public static String toDate(String format) {
	java.util.Date fecha = new java.util.Date();
	java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(format);
	return formato.format(fecha);
}
/**
 * Convierte el valor de "value" a un dato tipo double.
 * @return double
 * @param value java.lang.String
 */
public static double toDouble(String value) {
	return (toDouble(value, 0.0));
}
/**
 * Convierte el valor de "value" a un dato tipo double.
 * Si el dato es no numérico entonces devuelve el valor por defecto
 * dado en "defaultValue".
 * @return double
 * @param value java.lang.String
 * @param defaultValue double
 */
public static double toDouble(String value, double defaultValue) {
	double doubleVal = defaultValue;
	try {
		if (value.charAt(0) == '-') {
			doubleVal = Double.valueOf(value.substring(1)).doubleValue();
			doubleVal = -doubleVal;
		} else {
			doubleVal = Double.valueOf(value).doubleValue();
		}
	} catch (Exception e) {
	}
	return (doubleVal);
}
/**
 * Convierte el valor de "value" a un dato tipo float.
 * Si el valor es no numérico devuelve cero
 * @return float
 * @param value java.lang.String
 */
public static float toFloat(String value) {
	float floatVal = 0f;
	try {
		if (value.charAt(0) == '-') {
			floatVal = Float.valueOf(value.substring(1)).floatValue();
			floatVal = -floatVal;
		}
		else {
			floatVal = Float.valueOf(value).floatValue();
		}
	}
	catch (Exception e) {
	}
	return (floatVal);
}
/**
 * Convierte un arreglo de bytes a una cadena de hexadecimales.
 * @return java.lang.String
 * @param values byte[]
 */
public static String toHexString(byte[] values) {
	StringBuffer buf = new StringBuffer(values.length * 2);
	buf.setLength(values.length * 2);
	int index = 0;
	int ptr = 0;
	while (index < values.length) {
		buf.setCharAt(ptr++, hexDidget[ (values[index] & 0xf0) >> 4]);
		buf.setCharAt(ptr++, hexDidget[values[index] & 0x0f]);
		++index;
	}
	return (buf.toString());
}
/**
 * Convierte un nro entero a su correspondiente nro hexadecimal.
 * @return java.lang.String
 * @param val int
 */
public static String toHexString(int val) {
	StringBuffer buf = new StringBuffer(10);
	buf.setLength(9);
	int index = 8;
	while (index >= 0) {
		buf.setCharAt(index--, hexDidget[val & 0x0f]);
		val >>= 4;
		buf.setCharAt(index--, hexDidget[val & 0x0f]);
		val >>= 4;
		buf.setCharAt(index--, hexDidget[val & 0x0f]);
		val >>= 4;
		buf.setCharAt(index--, hexDidget[val & 0x0f]);
		val >>= 4;
		if (index > 0)
			buf.setCharAt(index--, ' ');
	}
	return (buf.toString());
}
/**
 * Convierte los caracteres especiales de la cadena "message"
 * a una cadena que pueda ser utilizada en páginas HTML, JSP, ASP, SERVLET, etc.
 * @return java.lang.String
 * @param message java.lang.String
 */
public static String toHtmlString(String message) {
	message = replaceChar(message, 'á', "%E1");
	message = replaceChar(message, 'é', "%E9");
	message = replaceChar(message, 'í', "%ED");
	message = replaceChar(message, 'ó', "%F3");
	message = replaceChar(message, 'ú', "%FA");
	message = replaceChar(message, 'ñ', "%F1");
	message = replaceChar(message, 'Ñ', "%D1");
	message = replaceChar(message, 'Á', "%C1");
	message = replaceChar(message, 'É', "%C9");
	message = replaceChar(message, 'Í', "%CD");
	message = replaceChar(message, 'Ò', "%D3");
	message = replaceChar(message, 'Ù', "%DA");
	message = replaceChar(message, ' ', "+");
	message = replaceChar(message, '&', "%26");
	message = replaceChar(message, '=', "%3D");
	message = replaceChar(message, '+', "%2B");
	message = replaceChar(message, '?', "%3F");
	return message;
}
/**
 * Convierte el valor de "value" a un dato tipo int.
 * Si el dato es no numérico entonces devuelve el valor cero.
 * @return int
 * @param value java.lang.String
 */
public static int toInteger(String value) throws IllegalArgumentException {
	return (toInteger(value, 0));
}
/**
 * Convierte el valor de "value" a un dato tipo int.
 * Si el dato es no numérico entonces devuelve el valor por defecto
 * dado en "defaultValue".
 * @return int
 * @param value java.lang.String
 * @param defaultValue int
 */
public static int toInteger(String value, int defaultValue) {
	int intVal = defaultValue;
	try {
		if (value.charAt(0) == '-') {
			intVal = Integer.parseInt(value.substring(1));
			intVal = -intVal;
		}
		else {
			intVal = Integer.parseInt(value);
		}
	}
	catch (IllegalArgumentException e) {
	}
	return (intVal);
}
/**
 * Convierte el valor de "value" a un dato tipo long.
 * Si el dato es no numérico entonces devuelve el valor cero.
 * @return long
 * @param value java.lang.String
 */
public static long toLong(String value) {
	return (toLong(value, 0));
}
/**
 * Convierte el valor de "value" a un dato tipo long.
 * Si el dato es no numérico entonces devuelve el valor por defecto
 * dado en "defaultValue".
 * @return long
 * @param value java.lang.String
 * @param defaultValue long
 */
public static long toLong(String value, long defaultValue) {
	long longVal = defaultValue;
	try {
		if (value.charAt(0) == '-') {
			longVal = Long.parseLong(value.substring(1));
			longVal = -longVal;
		}
		else {
			longVal = Long.parseLong(value);
		}
	}
	catch (IllegalArgumentException e) {
	}
	return (longVal);
}
public CString toLowerCase() {
	return valueOf(string.toLowerCase());
}
/**
 * @since JDK1.1
 */
public CString toLowerCase(Locale locale) {
	return valueOf(string.toLowerCase(locale));
}
/**
   * Converts to lower case all c characters.
   */
public void toLowerCaseAll(char c) {
	toLowerCaseAll(String.valueOf(c));
}
/**
   * Converts to lower case all c characters
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toLowerCaseAll(char c, Locale locale) {
	toLowerCaseAll(String.valueOf(c), locale);
}
/**
   * Converts to lower case all characters that occur in CString s.
   */
public void toLowerCaseAll(CString s) {
	toLowerCaseAll(s.toString());
}
/**
   * Converts to lower case all characters that occur in CString s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toLowerCaseAll(CString s, Locale locale) {
	toLowerCaseAll(s.toString(), locale);
}
/**
   * Converts to lower case all characters that occur in String s.
   */
public void toLowerCaseAll(String s) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) == -1)
			result.append(string.charAt(i));
		else
			result.append(Character.toLowerCase(string.charAt(i)));
	string = result.toString();
}
/**
   * Converts to lower case the characters in this string that occur in String s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toLowerCaseAll(String s, Locale locale) {
	StringBuffer result = new StringBuffer();
	int i, len = string.length();
	if (locale.getLanguage().equals("tr")) {
		// special loop for Turkey
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) == -1)
				result.append(string.charAt(i));
			else {
				char ch = string.charAt(i);
				if (ch == 'I') {
					result.append('\u0131'); // dotless small i
					continue;
				}
				if (ch == '\u0130')
					// dotted I
					{
					result.append('i'); // dotted i
					continue;
				}
				result.append(Character.toLowerCase(ch));
			}
		}
	} else {
		// normal loop
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) == -1)
				result.append(string.charAt(i));
			else
				result.append(Character.toLowerCase(string.charAt(i)));
		}
	}
	string = result.toString();
}
/**
   * Converts to lower case all but c characters.
   */
public void toLowerCaseAllBut(char c) {
	toLowerCaseAllBut(String.valueOf(c));
}
/**
   * Converts to lower case all but c characters
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toLowerCaseAllBut(char c, Locale locale) {
	toLowerCaseAllBut(String.valueOf(c), locale);
}
/**
   * Converts to lower case all characters but those in CString s.
   */
public void toLowerCaseAllBut(CString s) {
	toLowerCaseAllBut(s.toString());
}
/**
   * Converts to lower case all characters but those in CString s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toLowerCaseAllBut(CString s, Locale locale) {
	toLowerCaseAllBut(s.toString(), locale);
}
/**
   * Converts to lower case all characters but those in String s.
   */
public void toLowerCaseAllBut(String s) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) != -1)
			result.append(string.charAt(i));
		else
			result.append(Character.toLowerCase(string.charAt(i)));
	string = result.toString();
}
/**
   * Converts to lower case all characters but those in String s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toLowerCaseAllBut(String s, Locale locale) {
	StringBuffer result = new StringBuffer();
	int i, len = string.length();
	if (locale.getLanguage().equals("tr")) {
		// special loop for Turkey
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) != -1)
				result.append(string.charAt(i));
			else {
				char ch = string.charAt(i);
				if (ch == 'I') {
					result.append('\u0131'); // dotless small i
					continue;
				}
				if (ch == '\u0130')
					// dotted I
					{
					result.append('i'); // dotted i
					continue;
				}
				result.append(Character.toLowerCase(ch));
			}
		}
	} else {
		// normal loop
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) != -1)
				result.append(string.charAt(i));
			else
				result.append(Character.toLowerCase(string.charAt(i)));
		}
	}
	string = result.toString();
}
public static String toShortString(double value) throws IllegalArgumentException {
	return (toShortString(value, 3));
}
// JDK 1.0.2 style strings
public static String toShortString(double value, int numberOfSignificantDigits) throws IllegalArgumentException {
	String exponentStr = "";
	String str = String.valueOf(value);
	int exponentIndex = str.indexOf("E");
	if (exponentIndex != -1) {
		exponentStr = str.substring(exponentIndex);
		int exponent = toInteger(exponentStr);
		if (exponent < -numberOfSignificantDigits)
			return ("0");
		numberOfSignificantDigits = numberOfSignificantDigits - 2;
		str = str.substring(0, exponentIndex - 1);
	}
	int index = str.indexOf('.');
	if (index != -1) {
		++numberOfSignificantDigits;
		int len = str.length();
		if (index + numberOfSignificantDigits > len)
			index = len - numberOfSignificantDigits;
		str = str.substring(0, index + numberOfSignificantDigits);
		len = str.length();
		if ((str.charAt(len - 1) == '0') && (str.charAt(len - 2) == '.')) {
			str = str.substring(0, len - 2);
		}
	}
	return (str + exponentStr);
}
public String toString() {
	return string;
}
public static String toString(char value) {
	return (String.valueOf(value));
}
public static String toString(double value) {
	return (String.valueOf(value));
}
public static String toString(float value) {
	return (String.valueOf(value));
}
public static String toString(int value) {
	return (String.valueOf(value));
}
public static String toString(long value) {
	return (String.valueOf(value));
}
public static String toString(boolean value) {
	return (String.valueOf(value));
}
/**
 * Convierte un cadena de hexadecimales "valueHex" a un cadena standard.
 * @return java.lang.String
 * @param valueHex java.lang.String
 */
public static String toStringOfHex(String valueHex) {
	if (isEmptyOrNull(valueHex))
		return "";
	StringBuffer buf = new StringBuffer("");
	for (int j = 0; j < valueHex.length(); j += 2) {
		buf.append((char) Integer.parseInt(valueHex.substring(j, j + 2), 16));
	}
	return (buf.toString());
}
/**
 * Convierte los caracteres especiales de la cadena HTML "strHtml"
 * a una cadena que pueda ser utilizada en un bean.
 * @return java.lang.String
 * @param strHtml java.lang.String
 */
public static String toStringOfHtml(String strHtml) {
	strHtml = replaceString(strHtml, "%E1", 'á');
	strHtml = replaceString(strHtml, "%E9", 'é');
	strHtml = replaceString(strHtml, "%ED", 'í');
	strHtml = replaceString(strHtml, "%F3", 'ó');
	strHtml = replaceString(strHtml, "%FA", 'ú');
	strHtml = replaceString(strHtml, "%F1", 'ñ');
	strHtml = replaceString(strHtml, "%D1", 'Ñ');
	strHtml = replaceString(strHtml, "%C1", 'Á');
	strHtml = replaceString(strHtml, "%C9", 'É');
	strHtml = replaceString(strHtml, "%CD", 'Í');
	strHtml = replaceString(strHtml, "%D3", 'Ò');
	strHtml = replaceString(strHtml, "%DA", 'Ù');
	strHtml = replaceString(strHtml, "+", ' ');
	strHtml = replaceString(strHtml, "%26", '&');
	strHtml = replaceString(strHtml, "%3D", '=');
	strHtml = replaceString(strHtml, "%2B", '+');
	strHtml = replaceString(strHtml, "%3F", '?');
	return strHtml;
}
/**
   * Returns return time current.
   */
public static String toTime() {
	java.util.Date hora = new java.util.Date();
	java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("HH:mm:ss");
	return formato.format(hora);
}
public CString toUpperCase() {
	return valueOf(string.toUpperCase());
}
/**
 * @since JDK1.1
 */
public CString toUpperCase(Locale locale) {
	return valueOf(string.toUpperCase(locale));
}
/**
   * Converts to upper case all c characters.
   */
public void toUpperCaseAll(char c) {
	toUpperCaseAll(String.valueOf(c));
}
/**
   * Converts to upper case all c characters
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toUpperCaseAll(char c, Locale locale) {
	toUpperCaseAll(String.valueOf(c), locale);
}
/**
   * Converts to upper case all characters that occur in CString s.
   */
public void toUpperCaseAll(CString s) {
	toUpperCaseAll(s.toString());
}
/**
   * Converts to upper case all characters that occur in CString s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toUpperCaseAll(CString s, Locale locale) {
	toUpperCaseAll(s.toString(), locale);
}
/**
   * Converts to upper case all characters that occur in String s.
   */
public void toUpperCaseAll(String s) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) == -1)
			result.append(string.charAt(i));
		else
			result.append(Character.toUpperCase(string.charAt(i)));
	string = result.toString();
}
/**
   * Converts to upper case the characters in this string that occur in String s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toUpperCaseAll(String s, Locale locale) {
	StringBuffer result = new StringBuffer();
	int i, len = string.length();
	if (locale.getLanguage().equals("tr")) {
		// special loop for Turkey
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) == -1)
				result.append(string.charAt(i));
			else {
				char ch = string.charAt(i);
				if (ch == 'i') {
					result.append('\u0130'); // dotted cap i
					continue;
				}
				if (ch == '\u0131')
					// dotless i
					{
					result.append('I'); // cap I
					continue;
				}
				if (ch == '\u00DF')
					// sharp s
					{
					result.append("SS");
					continue;
				}
				result.append(Character.toUpperCase(ch));
			}
		}
	} else {
		// normal loop
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) == -1)
				result.append(string.charAt(i));
			else {
				char ch = string.charAt(i);
				if (ch == '\u00DF')
					// sharp s
					{
					result.append("SS");
					continue;
				}
				result.append(Character.toUpperCase(ch));
			}
		}
	}
	string = result.toString();
}
/**
   * Converts to upper case all but c characters.
   */
public void toUpperCaseAllBut(char c) {
	toUpperCaseAllBut(String.valueOf(c));
}
/**
   * Converts to upper case all but c characters
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toUpperCaseAllBut(char c, Locale locale) {
	toUpperCaseAllBut(String.valueOf(c), locale);
}
/**
   * Converts to upper case all characters but those in CString s.
   */
public void toUpperCaseAllBut(CString s) {
	toUpperCaseAllBut(s.toString());
}
/**
   * Converts to upper case all characters but those in CString s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toUpperCaseAllBut(CString s, Locale locale) {
	toUpperCaseAllBut(s.toString(), locale);
}
/**
   * Converts to upper case all characters but those in String s.
   */
public void toUpperCaseAllBut(String s) {
	StringBuffer result = new StringBuffer();
	int len = string.length();
	for (int i = 0; i < len; ++i)
		if (s.indexOf(string.charAt(i)) != -1)
			result.append(string.charAt(i));
		else
			result.append(Character.toUpperCase(string.charAt(i)));
	string = result.toString();
}
/**
   * Converts to upper case all characters but those in String s
   * using the rules of the given locale.
   * @since JDK1.1
   */
public void toUpperCaseAllBut(String s, Locale locale) {
	StringBuffer result = new StringBuffer();
	int i, len = string.length();
	if (locale.getLanguage().equals("tr")) {
		// special loop for Turkey
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) != -1)
				result.append(string.charAt(i));
			else {
				char ch = string.charAt(i);
				if (ch == 'i') {
					result.append('\u0130'); // dotted cap i
					continue;
				}
				if (ch == '\u0131')
					// dotless i
					{
					result.append('I'); // cap I
					continue;
				}
				if (ch == '\u00DF')
					// sharp s
					{
					result.append("SS");
					continue;
				}
				result.append(Character.toUpperCase(ch));
			}
		}
	} else {
		// normal loop
		for (i = 0; i < len; ++i) {
			if (s.indexOf(string.charAt(i)) != -1)
				result.append(string.charAt(i));
			else {
				char ch = string.charAt(i);
				if (ch == '\u00DF')
					// sharp s
					{
					result.append("SS");
					continue;
				}
				result.append(Character.toUpperCase(ch));
			}
		}
	}
	string = result.toString();
}
public CString trim() {
	return valueOf(string.trim());
}
/**
   * Calls <code>validate(..,..,..,..)</code> with:<br>
   * - case sensitivity on;<br>
   * - comma as separator char;<br>
   * - empty string as default value.
   */
public int validate(CString invalidValues) {
	CString tmpStr = new CString("");
	return validate(invalidValues, tmpStr, ',', false);
}
/**
   * Calls <code>validate(..,..,..,..)</code> with:<br>
   * - case sensitivity on;<br>
   * - comma as separator char.
   */
public int validate(CString invalidValues, CString defaultValue) {
	return validate(invalidValues, defaultValue, ',', false);
}
/**
   * Calls <code>validate(..,..,..,..)</code> with case sensitivity on.
   */
public int validate(CString invalidValues, CString defaultValue, char separatorChar) {
	return validate(invalidValues, defaultValue, separatorChar, false);
}
/**
   * Our CString's value:<br>
   * - is consired invalid when equal to one of the values specified in invalidValues;<br>
   * - remains unchanged if consired valid;<br>
   * - changes to defaultValue if consired invalid.
   * <p>
   * The values in invalidValues are separated by separatorChar.<br>
   * When ignoreCase is set to true, comparisions between our CString and invalid values
   * are case insensitive.
   *
   * @return -1 if our value is valid
   * or the position of the invalid value inside the invalidValues CString.
   */
public int validate(CString invalidValues, CString defaultValue, char separatorChar, boolean ignoreCase) {
	String curValue; // current value inside invalidValues
	int n, numTokens;
	StringTokenizer st = new StringTokenizer(invalidValues.toString(), String.valueOf(separatorChar));
	numTokens = st.countTokens();
	for (n = 0; n < numTokens; n++) {
		curValue = st.nextToken();
		if ((!ignoreCase) && (string.equals(curValue)))
			// case sensitive
			{ // our string is invalid
			string = defaultValue.toString();
			return n; // return the position of the invalid value
		} else
			if (ignoreCase && (string.equalsIgnoreCase(curValue)))
				// case insensitive
				{ // our string is invalid
				string = defaultValue.toString();
				return n; // return the position of the invalid value
			}
	}
	return -1; // our string is valid
}
public static CString valueOf(char data[]) {
	return new CString(data);
}
public static CString valueOf(char data[], int offset, int count) {
	return new CString(data, offset, count);
}
public static CString valueOf(char c) {
	char data[] = {c};
	return new CString(data);
}
public static CString valueOf(double d) {
	return new CString(Double.toString(d));
}
public static CString valueOf(float f) {
	return new CString(Float.toString(f));
}
public static CString valueOf(int i) {
	return new CString(Integer.toString(i, 10));
}
public static CString valueOf(long l) {
	return new CString(Long.toString(l, 10));
}
public static CString valueOf(Object obj) {
	return (obj == null) ? new CString("null") : new CString(obj.toString());
}
public static CString valueOf(String s) {
	return new CString(s);
}
public static CString valueOf(boolean b) {
	return b ? new CString("true") : new CString("false");
}
}