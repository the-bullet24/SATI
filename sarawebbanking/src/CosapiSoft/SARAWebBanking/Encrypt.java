package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class Encrypt {
/**
 * Encrypt constructor comment.
 */
public Encrypt() {
	super();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public static String encrypt(String text, String llave, String llave2) {
	//Encripta
	java.text.DecimalFormat df = new java.text.DecimalFormat("000");
	int mnCont = 0;
	String msTemporal = "";
	String msEncFinal = "";
	for (int i = 0; i < text.length(); i++)
		//I = 1 To Len(msCadena)
		{
		msTemporal = msTemporal + df.format(text.charAt(i) ^ llave.charAt(mnCont));
		mnCont = mnCont + 1;
		if (mnCont == 17)
			mnCont = 0;
	}
	mnCont = 0;
	int mnAux = 0;
	for (int i = 0; i < msTemporal.length(); ++i)
		//I = 1 To Len(msTemporal)
		{
		if (mnCont == text.length() * 3)
			//     Then
			{
			//'If mnCont = 511 Then
			//Exit For
			break;
		}
		msEncFinal = msEncFinal + df.format(Integer.valueOf(msTemporal.substring(mnCont, mnCont + 3)).intValue() ^ llave2.charAt(mnAux));
		mnCont = mnCont + 3;
		mnAux = mnAux + 1;
		if (mnAux == 5)
			mnAux = 0;
	}
	// System.out.println(msEncFinal);

	//     'If mnCont > 120 Then
	//     If mnCont = Len(msCadena) * 3 + 1 Then
	//     'If mnCont = 511 Then
	//        Exit For
	//     End If
	//     msEncFinal = msEncFinal & Format$(Mid(msTemporal, mnCont, 3) Xor Asc(Mid$(msSecuLlave, mnAux, 1)), "000")
	//     mnCont = mnCont + 3
	//     mnAux = mnAux + 1
	//     If mnAux = 6 Then mnAux = 1
	// Next I
	// Encripta = msEncFinal
	return msEncFinal;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param cadena java.lang.String
 */
public final static String encryptEmpleado(String cadena) {
	String origen = "MURCIELAGO0123456789", destino = "0123456789MURCIELAGO";
	String retorno = "";
	boolean lEntro = false;
	char cOrigen[] = origen.toCharArray();
	char cDestino[] = destino.toCharArray();
	char xx[] = cadena.toCharArray();
	for (int i = 0; i < xx.length; i++) {
		lEntro = false;
		for (int a = 0; a < cOrigen.length; a++) {
			if (cOrigen[a] == xx[i]) {
				lEntro = true;
				retorno = retorno + cDestino[a];
			}
		}
		if (!lEntro)
			retorno = retorno + xx[i];
	}
	return retorno;
}
/**
 * This method was created in VisualAge.
 * @param args java.lang.String[]
 */
public static void main(String args[]) {
	Encrypt e = new Encrypt();
	String clave = "";
	int i = 1;
	while (i < 7) {
		clave = "00000" + i++;
		String dato = e.encrypt(clave, "PLER4&/!1BA97c&-|", "HQ89S");
		// System.out.println("Clave : " + clave + " -> Clave Encrip. : " + dato);
		// System.out.println("Clave encrip. : " + dato + " -> Clave Unencrip. : " + e.unencrypt(dato, "PLER4&/!1BA97c&-|", "HQ89S"));
	}
	//
	//	String dato = e.encrypt(clave, "$%23#0-'\n", "523");
	//	System.out.println("Clave : " + clave + " -> Clave Encrip. : " + dato);
	//	System.out.println("Clave encrip. : " + dato + " -> Clave Unencrip. : " + e.unencrypt(dato, "$%23#0-'\n", clave));
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 * @param data java.util.Vector
 */
public static String unencrypt(String dic, String llave1, String llave2) {
	int mnCont = 0;
	int mnAux = 0;
	java.text.DecimalFormat df = new java.text.DecimalFormat("000");
	String msTemporal = "";
	String msDesencripta = "";
	for (int i = 0; i < dic.length(); ++i)
		
		//I = 1 To Len(msCadena)
		{
		if (mnCont >= dic.length())
			break;
		msTemporal = msTemporal + df.format((llave2.charAt(mnAux) ^ Integer.valueOf(dic.substring(mnCont, mnCont + 3)).intValue()));
		mnCont = mnCont + 3;
		mnAux = mnAux + 1;
		if (mnAux == 5)
			mnAux = 0;
	}
	mnAux = 0;
	mnCont = 0;
	String msDesFinal = "";
	for (int i = 0; i < msTemporal.length(); ++i) {
		if (mnCont >= dic.length())
			break;
		msDesFinal = msDesFinal + ((char) (llave1.charAt(mnAux) ^ Integer.valueOf(msTemporal.substring(mnCont, mnCont + 3)).intValue()));
		mnCont = mnCont + 3;
		mnAux = mnAux + 1;
		if (mnAux == 17)
			mnAux = 0;
	}
	msDesencripta = msDesencripta + msDesFinal;
	return msDesencripta;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param dic java.lang.String
 * @param data java.util.Vector
 */
public String unencrypt(String dic, String llave1, String llave2, java.util.Vector data, java.util.Vector cuentas) {
	String dic1 = dic;
	for (int i = 0; i < data.size(); ++i) {
		if (((java.util.Vector) data.elementAt(i)).elementAt(1).equals(dic)) {
			dic = ((java.util.Vector) data.elementAt(i)).elementAt(6).toString();
		}
	}
	int mnCont = 0;
	int mnAux = 0;
	java.text.DecimalFormat df = new java.text.DecimalFormat("000");
	String msTemporal = "";
	String msDesencripta = "";
	for (int i = 0; i < dic.length(); ++i)
		//I = 1 To Len(msCadena)
		{
		if (mnCont >= dic.length())
			break;
		msTemporal = msTemporal + df.format((llave2.charAt(mnAux) ^ Integer.valueOf(dic.substring(mnCont, mnCont + 3)).intValue()));
		mnCont = mnCont + 3;
		mnAux = mnAux + 1;
		if (mnAux == 5)
			mnAux = 0;
	}
	mnAux = 0;
	mnCont = 0;
	String msDesFinal = "";
	for (int i = 0; i < msTemporal.length(); ++i) {
		if (mnCont >= dic.length())
			break;
		msDesFinal = msDesFinal + ((char) (llave1.charAt(mnAux) ^ Integer.valueOf(msTemporal.substring(mnCont, mnCont + 3)).intValue()));
		mnCont = mnCont + 3;
		mnAux = mnAux + 1;
		if (mnAux == 17)
			mnAux = 0;
	}
	msDesencripta = msDesencripta + msDesFinal;
	for (int i = 0; i < data.size(); ++i) {
		if (((java.util.Vector) data.elementAt(i)).elementAt(1).equals(dic1)) {
			((java.util.Vector) data.elementAt(i)).setElementAt(msDesencripta, 6);
		}
	}
	return "";
}
}