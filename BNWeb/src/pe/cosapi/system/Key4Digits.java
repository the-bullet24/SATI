/**-----------------------------------------------------------------|            	 					|
|   Componente: Key4Digits.java      								|
|	Def: Clase encargada de Proveer metodos para 					|
|		encriptacion y desencriptacion de la clave de 				|
|		4 digitos.													|
|-----------------------------------------------------------------**/

package pe.cosapi.system;

import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;


public class Key4Digits {
	private static LoggerSati log3 = LoggerSati.getInstance(Key4Digits.class.getName());
	
	/**
	 * Metodo encargado de Encriptar la cadena de cuatro digitos 
	 */
	public String encriptar(String texto){
		return core(Constante.KEY4_DIGITS ,texto ,1);
	}
	
	/**
	 * Metodo encargado de Desencriptar la cadena de cuatro digitos 
	 */
	public String desencriptar(String texto){
		return core(Constante.KEY4_DIGITS ,texto ,2);
	}
	
	private String  core(String userKey , String  text ,int action){

		  String userKeyX;
		  int temp;
		  int i;
		  int j = 0;
		  int userkeyascii2x = 0;
		  int n;
		  int textascii2x = 0;
		  
		  String[]  userkeyasciis = null; 
	      String rtn 				= "" ; 
	      int[] 	userkeyasciis2 	= null;
	      String[] 	textasciis  	= null;
	      int[] 	textasciis2 	= null;
			
		  String[]  a = getArrayData();
		  
		  n = userKey.length();
		  
		  userkeyasciis = new String[n];
		  
		  for(i=0;i<n;i++){
		  	userkeyasciis[i] = String.valueOf(userKey.charAt(i)); 
		  }
		  
		  userkeyasciis2 = new int[n-1];
		  
		  for (i=0;i<n-1;i++){
			  	for (j=0;j<85;j++){
			  		if (userkeyasciis[i].equals(a[j])){
			  			userkeyascii2x = j;
			  		}
			  	}
			  	userkeyasciis2[i] = userkeyascii2x;
		  }
		  
		  textasciis = new String[text.length()];
		  
		  for(i=0;i<text.length();i++){
		  	textasciis[i] = String.valueOf(text.charAt(i));
		  }
		  
		  textasciis2 = new int[text.length()];
		  for(i=0;i<text.length();i++){
		  	for(j=0;j<85;j++){
		  		if (textasciis[i].equals(a[j])){
		  			textascii2x = j;
		  		}
		  	}
		  	textasciis2[i] = textascii2x;
		  }
		  
		  if (action == 1){
		  	for(i=0;i<text.length();i++){
		  		if (j+1 >= n-1)
		  			j = 0;
		  		else{
		  			int k = j+1;
		  			j = k;
		  		}
		  		temp = textasciis2[i] + userkeyasciis2[j];
		  		temp = temp+1; 
		  		if(temp > 84){
		  			temp = temp - 85;
		  		}
		        rtn += a[temp];
		  	}
		  }else if(action == 2){
		  	for(i=0;i<text.length();i++){
		  		if (j+1 >= n-1)
		  			j = 0;
		  		else{
		  			int k = j+1;
		  			j = k;
		  		}
		  		temp = textasciis2[i] - userkeyasciis2[j];
		  		temp =temp - 1;
		  		if(temp <= 0){
		  			temp = temp + 85;
		  		}
		  		rtn += a[temp];
		  	}
		  }
		return rtn;
	}
	
	private String[] getArrayData(){
		  String[]  a = new String[85];
		  
		  a[0] = "\\"; 	    a[20] = "t";     	 			 a[40] = "."; 	    a[60] = "B"; 	 a[80] = "V"; 
		  a[1] = "a";       a[21] = "u";         			 a[41] = "/";       a[61] = "C";     a[81] = "W"; 
		  a[2] = "b";       a[22] = "v";         			 a[42] = "0";       a[62] = "D";     a[82] = "X"; 
		  a[3] = "c";       a[23] = "w";         			 a[43] = "1";       a[63] = "E";     a[83] = "Y"; 
		  a[4] = "d";       a[24] = "x";         			 a[44] = "2";       a[64] = "F";     a[84] = "Z"; 
		  a[5] = "e";       a[25] = "y";         			 a[45] = "3";       a[65] = "G"; 
		  a[6] = "f";       a[26] = "z";         			 a[46] = "4";       a[66] = "H"; 
		  a[7] = "g";       a[27] = "_";         			 a[47] = "5";       a[67] = "I"; 
		  a[8] = "h";       a[28] = " ";         			 a[48] = "6";       a[68] = "J"; 
		  a[9] = "i";      a[29] = String.valueOf((char)34); a[49] = "7";       a[69] = "K"; 
		  a[10] = "j";      a[30] = "$";         			 a[50] = "8";       a[70] = "L"; 
		  a[11] = "k";      a[31] = "%";         			 a[51] = "9";       a[71] = "M"; 
		  a[12] = "l";      a[32] = "&";         			 a[52] = ":";       a[72] = "N"; 
		  a[13] = "m";      a[33] = "'";         			 a[53] = ";";       a[73] = "O"; 
		  a[14] = "n";      a[34] = "[";         			 a[54] = "<";       a[74] = "P"; 
		  a[15] = "o";      a[35] = "]";         			 a[55] = "=";       a[75] = "Q"; 
		  a[16] = "p";      a[36] = "*";         			 a[56] = ">";       a[76] = "R"; 
		  a[17] = "q";      a[37] = "+";         			 a[57] = "?";       a[77] = "S"; 
		  a[18] = "r";      a[38] = ",";         			 a[58] = "@";       a[78] = "T"; 
		  a[19] = "s";      a[39] = "-";         			 a[59] = "A";       a[79] = "U"; 
		  return a;
	}
}
