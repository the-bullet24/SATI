/*
 * Created on 30/01/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Hashtable;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import pe.cosapi.common.Constante;

//import com.cosapisoft.sarawebbranch.awt.applets.forms.MaskCenter;

public class Crypto {
	private static String encoding = "CP037";
    public static SecretKeyFactory skfDesPad;
    public static SecretKeyFactory skfDesNoPad;
    public static SecretKeyFactory skfCifPad;
    public static SecretKeyFactory skfCifNoPad;
    public static String sClaveParaClaves;
    
    public Crypto() {
    }
    
public static void main ( String[] args ) throws UnsupportedEncodingException{
//FFFFF5FFFFF5FFFFF50CCC01F000000000000000000000000000000000000000000000       
//11111F22222F33333FE111F2F000000000000000000000000000000000000000000000 
//texto = "f1f1f1f1f15ff2f2f2f2f25ff3f3f3f3f35f0ec1c1c10f12ff";
//texto = "c4c5d4d6e2e3d9c1c3c9d6d540c4c540c5d5c3d9c9d7e3c1d4c9c5d5e3d6";

//String ret = "9d37450b7db9903329d0fca503c8e4722c96fbdcd1ba4805df3addd8b87d";//encripta(texto,claveA);
//ret = "3decae4b951cd824c49858c34348fa713c49190d5266fb56ed";

//String rev = desencripta(ret,claveA);
//System.out.println("mi cadena final en hexa es : "+rev);
//texto = "f0f0f0f0f0f0f0f0f0f0f1f1f1f1f1f0f0f0f0f0f0f0f0f0f0f3f3f3f3f3c1c1c1404040404040404040404040404040404040404040404040404040f0f6f6f1";
//texto = "f0f0f0f0f0f0f0f0f0f0f6f6f6f6f6f0f0f0f0f0f0f0f0f7f7f6f7f6f7f6c1d3c6c1d6d4c5c7c1404040404040404040404040404040404040404040f0f6f6f1";
//String cad = getMac(texto,claveMac);
//System.out.println("mi cadena final en hexa es : "+cad);   

//String nom = "alex valencia";
//String hexnom = ToHexa(nom.getBytes());
//String hexnom = "f1f1f1f1f15ff2f2f2f2f25ff3f3f3f3f35f0ec1c1c10f12ff";
//System.out.println("cadena original en hexa es : "+hexnom);          
/*
String cad = encripta3DESede2Key(hexnom,"9d37450b7db99033","4805df3addd8b87d");
System.out.println("cadena encriptada con 3des es  : "+cad);   
cad = desencripta3DESede2Key(cad,"0821ADB21B0DE766","BEB089C946C1824E");
System.out.println("cadena desencriptada con 3des es  : "+cad);   
cad = encriptaDES(hexnom,"9d37450b7db99033");
System.out.println("cadena encriptada con des es  : "+cad);   
cad = desencriptaDES(cad,"9d37450b7db99033");
System.out.println("cadena desencriptada con 3des es  : "+cad);   
cad = getMac(nom,claveMac);
System.out.println("mi mac final en hexa es : "+cad);   
*/
/*
String cad = encripta3DESede2Key(hexnom,"0821ADB21B0DE766","BEB089C946C1824E");
System.out.println("cadena encriptada con 3des es  : "+cad);   
cad = desencripta3DESede2Key(cad,"0821ADB21B0DE766","BEB089C946C1824E");
System.out.println("cadena desencriptada con 3des es  : "+cad);   
cad = encriptaDES(hexnom,"0821ADB21B0DE766");
System.out.println("cadena encriptada con des es  : "+cad);   
cad = desencriptaDES(cad,"0821ADB21B0DE766");
System.out.println("cadena desencriptada con 3des es  : "+cad);   
*/
String claveDES_A = "0E58FB8BEF028262";
//String claveDES_B = "BEB089C946C1824E";
/*
String textoDES = "11111" + (char)172 + "22222" + (char)172 + "33333" + (char)172 + (char)14 + "AAA" + (char)15 + (char)18 + (char)255;
byte textoDESEnEbcdic[] = AsciiToEbcdic(new String(textoDES));
String textoDESEnHexa = Crypto.ToHexa(textoDESEnEbcdic);
String cadDES = Crypto.encriptaDES(textoDESEnHexa, claveDES_A);
System.out.println("cadena encriptada con des es : " + cadDES);   
String cad1 = Crypto.desencriptaDES(cadDES, claveDES_A);
System.out.println("cadena desencriptada con des es : " + cad1);   
String textoDESEnAscii = EbcdicToAscii(Crypto.ToAscii2(cad1));
System.out.println("cadena desencriptada con 3des en Ascii es : " + textoDESEnAscii);
*/

char cDelimDatoNumerico = 95;
char cFinTrama = 255;

StringBuffer lbody = new StringBuffer();
lbody.append(new String(AsciiToEbcdic("0001")));
lbody.append(cDelimDatoNumerico);
lbody.append(cFinTrama);

//String sTextoPlano = lbody.toString().getBytes();
//byte bTextoPlanoEnEbcdic[] = AsciiToEbcdic(lbody.toString().getBytes());
String sTextoPlanoEnHexa = Crypto.ToHexa(lbody.toString().getBytes());
sTextoPlanoEnHexa = "f0f0f0f0f0f3f2f7f6f7f05ff0f0f1f1f2f2f3f35ff1f0f0f0f0f0f0f05ff05ff05ff05ff05ff05ff05ff0f7f0f05fff";
//sTextoPlanoEnHexa = "f0f0f0f0f0f3f2f7f6f7f05ff0f0f1f1f2f2f3f35ff1f0f0f0f0f0f0f05ff05ff05ff05ff05ff05ff05ff0f7f05fff";
// System.out.println("Trama ebcdic en Hexa: " + sTextoPlanoEnHexa);
/*
// System.out.println("Texto plano a encriptar es : " + sTextoPlanoEnHexa);
//Encripta con 3DES
String sTextoEn3DES = Crypto.encripta3DESede2Key(sTextoPlanoEnHexa, claveDES_A, claveDES_B);
System.out.println("cadena encriptada con 3des es : " + sTextoEn3DES);   
*/
//Encripta con DES
String sTextoEnDES = Crypto.encriptaDES(sTextoPlanoEnHexa, claveDES_A);
// System.out.println("cadena encriptada con des es : " + sTextoEnDES); 
//byte[] bTextoEnDESEnAscii = ToAscii2(sTextoEnDES); 

//s = Crypto.ToHexa(bTextoEnDESEnAscii);
// System.out.println("Trama desencriptada DES en Hexa: " + Crypto.desencriptaDES(sTextoEnDES, claveDES_A));

/*
//Texto Desencriptado desde 3DES
sTextoPlanoEnHexa = Crypto.desencripta3DESede2Key(sTextoEn3DES, claveDES_A, claveDES_B);
System.out.println("cadena desencriptada desde 3des es  : " + sTextoPlanoEnHexa);   

//Texto Desencriptado con 3DES
sTextoPlanoEnHexa = Crypto.desencriptaDES(sTextoEnDES, claveDES_A);
System.out.println("cadena desencriptada desde des es  : " + sTextoPlanoEnHexa);   


//String texto3DESEnAscii = EbcdicToAscii(Crypto.ToAscii2(cad1));
//System.out.println("cadena desencriptada con 3des en Ascii es : " + texto3DESEnAscii);

String textoMAC = "000000000066666000000007767676ALFAOMEGA                     0661";
byte textoMACEnHexa[] = AsciiToEbcdic(textoMAC);
String textoMACEnHexa1 = Crypto.ToHexa(textoMACEnHexa);
String macHexa = Crypto.getMac(textoMACEnHexa1, claveMac);
System.out.println("mi mac final en hexa es : " + macHexa);   
byte[] macAscii = Crypto.ToAscii2(macHexa);
System.out.println("mi mac final en ascii es : " + new String(macAscii));
*/

}

/** Cifra arreglo de bytes b (en ascii), usando llave (en ascii) y sin aplicar padding
 *  usando algoritmo DES
 */
private static byte[] cifrabloqueDesNoPad(byte[] b,byte[] llave){
  byte cifrado[] = null;  
  try {
      
      Key key = null;
//      SecretKeyFactory skfDesNoPad = SecretKeyFactory.getInstance("DES");
//      SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
      Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
      skfDesNoPad = SecretKeyFactory.getInstance("DES");
//      MaskCenter.getFrameBranch().skfDesNoPad = SecretKeyFactory.getInstance("DES");
//      key = MaskCenter.getFrameBranch().skfDesNoPad.generateSecret(new DESKeySpec(llave));
      key = skfDesNoPad.generateSecret(new DESKeySpec(llave));
      cipher.init(Cipher.ENCRYPT_MODE, key);
      
      cifrado = cipher.doFinal(b);
      return cifrado;
      }
  catch(Exception e){
  		//System.out.println("Metodo cifrabloqueDES : "+e.getMessage());
  	}  
  return cifrado;
}

/** Cifra arreglo de bytes b (en ascii), usando llave (en ascii) y aplicando padding
 *  PKCS5Padding y algoritmo DES y modo ECB
 */
private static byte[] cifrabloqueDesPad(byte[] b,String llave){

  byte cifrado[] = null;  
  try {
      
      Key key = null;
//      SecretKeyFactory skfDesPad = SecretKeyFactory.getInstance("DES");
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      skfDesPad = SecretKeyFactory.getInstance("DES");
      key = skfDesPad.generateSecret(new DESKeySpec(llave.getBytes()));
      cipher.init(Cipher.ENCRYPT_MODE, key);
      cifrado = cipher.doFinal(b);
      return cifrado;
      }
  catch(Exception e){if(Constante.VER_LOG)System.out.println("Metodo cifrabloqueDES : "+e.getMessage());}  
  return cifrado;
}

/** Descifra arreglo de bytes b (en ascii), usando llave (en ascii) y sin aplicar padding
 *  usando algoritmo DES.
 */
private static byte[] descifrabloqueDesNoPad(byte[] b,byte[] llave)
{
  byte descifrado[] = null;  
  try {
      
      Key key = null;
//      SecretKeyFactory skfCifNoPad = SecretKeyFactory.getInstance("DES");
      Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
      skfCifNoPad = SecretKeyFactory.getInstance("DES");
      key = skfCifNoPad.generateSecret(new DESKeySpec(llave));
      cipher.init(Cipher.DECRYPT_MODE, key);
      descifrado = cipher.doFinal(b);
      return descifrado;
      }
  catch(Exception e){if(Constante.VER_LOG)System.out.println("Metodo descifrabloqueDES : "+e.getMessage());}  
  return descifrado;
}
/** Aplica XOR a los arreglos b1,b2 devolviendo arreglo con el XOR
 * y de la misma longitud que los primeros
 */
private static byte[] getxor(byte[] b1, byte[] b2){

    int n = b1.length;
    byte[] b3 = new byte[n];
    for(int k=1;k<=n;k++)
    {
          b3[k-1] = (byte)(b1[k-1] ^ b2[k-1]); 
    }
    return b3;
}
/** Devuelve los n primeros bytes del arreglo b.
 *  tomandolos de izquierda a derecha.
 */
public static byte[] getbytes(byte[] b,int n)
{
    byte[] c = new byte[n];
    for(int i=1;i<=n;i++)
    {
        c[n-i]=b[n-i];
    }
    
    return c;
}
/** Metodo al cual se le pasa com parametro la cadena en hexadecimal, y el nro
 * de bloque que se desea obtener (la cadena se convierte a ascii, y se obtiene
 * bloque de 8 bytes de la posicion indicada. 
 * 
 */
private static byte[] bloque(String cadena, int bloque){

  byte[] texto; 
  texto = ToAscii2(cadena); 
    
  byte[] b = null;
  int n = (bloque*8)- texto.length; 
  if(n > 0)  {
  	b = new byte[8-n];   
  }else if(n<=0 ){
    b = new byte[8];   
        }
  
  if(n<=0){
  	for(int i=1;i<=8;i++){
  		b[i-1] = texto[(bloque-1)*8+(i-1)];  
  	}
  }else{
  	for(int i=1;i<=(8-n);i++){
  		b[i-1] = texto[(bloque-1)*8+(i-1)];  
  	}
  }
  return b;
}
/** Se obtiene el bloque de 8 bytes de cadena (cadena esta en hexadecimal), si el ultimo bloque
 * es menor a 8 bytes, se completa con ceros por el lado derecho (en los digitos menos significativos ) 
 */
private static byte[] bloqueMac(String cadena, int bloque){

  int cero = 0;
  byte[] texto; 
  texto = ToAscii2(cadena); 
    
  byte[] b = new byte[8];
  int n = (bloque*8)- texto.length; 
  
  if(n<=0)
  {
     for(int i=1;i<=8;i++)
     {
       b[i-1] = texto[(bloque-1)*8+(i-1)];  
     }
  }
  else
  {
     for(int j=1;j<=n;j++)   
     {
      b[j-1] = (byte)cero;   
     }
     for(int i=1;i<=(8-n);i++)
     {
      b[i-1+n] = texto[(bloque-1)*8+(i-1)];  
     }
  }
  return b;
}

/** Metodo al cual se le pasa com parametro la cadena en hexadecimal, y la llave
 * en hexadecimal y devuelve un string con la cadena encriptada usando DES en Hexadecimal.
 * 
 */
public static String encriptaDES(String cadena, String llave){
  String retorno = "";
  byte[] texto = ToAscii2(cadena);
  byte[] claveA = ToAscii2(llave);
  int n = 0;
  int m = 0;
  int l = 0;
   
  n = (texto.length / 8);
  m = (texto.length - 8*n);
  n--;
  if(m > 0){n++;}
  l = texto.length;
  
  if(l < 8){
        byte[] a = ToAscii2("0000000000000000");
        byte[] d = cifrabloqueDesPad(a,new String(claveA));
        byte[] x = new byte[l];
        x = getbytes(d,l);
        byte z[] = getxor(x,texto);
        String cad2 = ToHexa(z);
        return cad2;  
  }
  
  try {
  	byte cifrado[];
    byte cifrado2[];
    byte[] inicial;
    inicial = bloque(cadena,1);
  
    cifrado = cifrabloqueDesNoPad(inicial,claveA);
    retorno = retorno + ToHexa(cifrado);
    cifrado2 = cifrado;  
  
	if(n==0){          
	      //aqui va cuando longitud ascii a evaluar es menor a 8 bytes        
	}else if(n > 0){
		for(int i=1;i<=n;i++){
	        if(i==n){
	        	if(m==0){
	        		cifrado2 = getxor(cifrado, bloque(cadena, i+1));
	        		cifrado = cifrabloqueDesNoPad(cifrado2, claveA);   
	        		retorno = retorno + ToHexa(cifrado);
	        		return retorno;
	        	}
	            cifrado = cifrabloqueDesNoPad(cifrado,claveA); 
	        }   
	        byte[] c = bloque(cadena,i+1);
	        byte cipfinal[] = {};
	        if(c.length == 8){
	        	cifrado2 = getxor(cifrado,c);           
        		cipfinal = cifrabloqueDesNoPad(cifrado2,claveA);
	        }else{
	        	byte[] cc = getbytes(cifrado,c.length);   
	        	cifrado2 = getxor(cc,c);    
	        	retorno = retorno + ToHexa(cifrado2);
	        	return retorno;
	        }        
	        cifrado = cipfinal;
	        retorno = retorno+ToHexa(cipfinal);
	    }     
	 }  
  }catch(Exception e){
  	if(Constante.VER_LOG)
  	System.out.println("Metodo encripta : "+e.getMessage());
  }
  return retorno;  
}


/** Metodo al cual se le pasa com parametro la cadena en hexadecimal, y la llave
 * en hexadecimal y devuelve un string con la cadena desencriptada usando DES en Hexadecimal.
 * 
 */
public static String desencriptaDES(String cadena,String llave){

  String retorno = "";
  
  byte[] texto; 
  byte[] claveA;
  int n = 0;
  int m = 0;
  int l = 0;
  texto = ToAscii2(cadena);  
  claveA = ToAscii2(llave);
  
  n = (texto.length / 8);
  m = (texto.length - 8*n);
  n--;
  if(m > 0){n++;}
  l = texto.length;
  
   if(l < 8)
  {
        byte[] a = ToAscii2("0000000000000000");
        byte[] d = cifrabloqueDesPad(a,new String(claveA));
        byte[] x = new byte[l];
        x = getbytes(d,l);
        byte z[] = getxor(x,texto);
        String cad2 = ToHexa(z);
        return cad2;  
  }
  
  try {
      
       byte cifrado[];
       byte cifrado2[];
       byte[] inicial;
       inicial = bloque(cadena,1);
       cifrado = descifrabloqueDesNoPad(inicial,claveA);
       
       retorno = retorno + ToHexa(cifrado);
       cifrado2 = cifrado;  
  
  if(n==0) 
  {          
          
  }
  else if(n > 0) 
  {
      
      for(int i=1;i<=n;i++)
      {
          if(i==n) 
          { 
             byte[] d;
             byte[] e;
            
             if(m==0)
             {
               cifrado = descifrabloqueDesNoPad(bloque(cadena,i+1),claveA);   
               cifrado2 = getxor(cifrado,bloque(cadena,i));
               retorno = retorno + ToHexa(cifrado2);
               return retorno;
             }
             else
             {
               cifrado = cifrabloqueDesNoPad(bloque(cadena,i),claveA);   
               d = getbytes(cifrado,bloque(cadena,i+1).length); 
               e = getxor(d,bloque(cadena,i+1));
               retorno = retorno + ToHexa(e);
               return retorno;
             }
            
          }   
            
          byte[] c = bloque(cadena,i+1);
          byte[] cipfinal;        
          if(c.length == 8)
          {
            cipfinal = descifrabloqueDesNoPad(c,claveA);
            cifrado2 = getxor(cipfinal,bloque(cadena,i));           
            retorno = retorno + ToHexa(cifrado2);
          }
          else
          {
            
            byte[] cc = getbytes(cifrado,c.length);   
            cifrado2 = getxor(cc,c);    
            retorno = retorno + ToHexa(cifrado2);
            return retorno;
          }         
          cifrado = cipfinal;                          
      }     
  }  
  }
  catch(Exception e){if(Constante.VER_LOG)System.out.println("Metodo desencripta : "+e.getMessage());}
  
  return retorno;  
}

public static byte[] desencriptaTramaDES(byte[] bDatos, String sClave){
	
	String textoEncriptadoDES = Crypto.ToHexa(bDatos);
	String cad = Crypto.desencriptaDES(textoEncriptadoDES, sClave);	
	byte[] trama = Crypto.ToAscii2(cad);
	
	return trama;
}

public static byte[] desencriptaTrama3DES(byte[] bDatos, String sClave_A, String sClave_B){
	
	String textoEncriptadoDES = Crypto.ToHexa(bDatos);
	String cad = Crypto.desencripta3DESede2Key(textoEncriptadoDES, sClave_A, sClave_B);	
	byte[] trama = Crypto.ToAscii2(cad);
	
	return trama;
}

/** Convierte el arreglo de bytes a Hexadecimal, lo devuelve como String
 * 
 */
public static String ToHexa(byte[] in) {

    String ret = "";
    if (in != null){
		int size = in.length;
		if (size != 0){
			for (int i = 0; i < size; i++){
				String sss = Integer.toHexString(in[i]);
				if (in[i] < 0){
	 			    ret += sss.substring(6,8);
				}else{
	                if (in[i] <= 15)
	                	sss = "0" + sss;
					ret += sss;
			    }
			}
		}
	}
	return ret;
}

/** Convierte la cadena en Hexadecimal, a su representacion en Ascci pero como arreglo de bytes.
 *
 */
 public static byte[] ToAscii2(String hex) {
    String cad = "";
    String hex2 = hex;
    
    int n = hex.length();
    byte[] bb = new byte[n/2];
    int m = 0;
    n = n/2;
    for(int i = 1; i <= n; i++){
    	cad = hex2.substring(0,2);
    	hex2 = hex2.substring(2);
    	m = Integer.parseInt(cad,16);  
    	bb[i-1] = (byte)m;         
    }          
    return bb;
}

 /** 
 *	Convierte la cadena Hexadecimal, a su representacion en Ebcdic. Solo para 0-9 y A-F. Se retorna como String.
 */
 public static String ToEbcdic(String hex) {
    Hashtable hashHexaEbcdic = new Hashtable();
    hashHexaEbcdic.put("F0", "0");hashHexaEbcdic.put("F1", "1");hashHexaEbcdic.put("F2", "2");hashHexaEbcdic.put("F3", "3");hashHexaEbcdic.put("F4", "4");
    hashHexaEbcdic.put("F5", "5");hashHexaEbcdic.put("F6", "6");hashHexaEbcdic.put("F7", "7");hashHexaEbcdic.put("F8", "8");hashHexaEbcdic.put("F9", "9");
    hashHexaEbcdic.put("C1", "A");hashHexaEbcdic.put("C2", "B");hashHexaEbcdic.put("C3", "C");hashHexaEbcdic.put("C4", "D");hashHexaEbcdic.put("C5", "E");
    hashHexaEbcdic.put("C6", "F");hashHexaEbcdic.put("40", " ");
    
    int iLen = hex.length();
    String sEbcdic = "";
    hex = hex.toUpperCase();
    
    for(int i = 0; i <= iLen-1; i++){
    	sEbcdic += hashHexaEbcdic.get(hex.substring(i, i+2)).toString();
    	i++;
    }          
    return sEbcdic;
}
/** Metodo que ingresando como parametros la cadena en Hexadecimal, y la llave
  *  en  Hexadecimal, aplica el estandar Ansi X9.9-1 y halla la Mac y la devuelve
  *  en Hexadecimal.
  */
public static String getMac(String cadena, String llave){

  String retorno = "";
  byte[] texto = ToAscii2(cadena);
  byte[] claveA = ToAscii2(llave);
  int n = 0;
  int m = 0;
  int l = 0;
   
  n = (texto.length / 8);
  m = (texto.length - 8*n);
  l = texto.length;
  byte[] b1 = null;
  byte[] b2 = null;
  
  try {
      
       if(m==0)
       {
           for(int i=1;i<=n;i++)
           {
            if(i==1)
            {    
               b1 = bloqueMac(cadena,i);
            }
            
            b2 = cifrabloqueDesNoPad(b1,claveA);
            if(i < n)
            {
             b1 = getxor(b2,bloqueMac(cadena,i+1));
            }
            else
            {
             b1 = getbytes(b2,4);
             retorno = ToHexa(b1);
             return retorno;             
            }    
               
           }           
       }
       else
       {
        n++;
           for(int i=1;i<=n;i++)
           {
             if(i==1)
             {    
               b1 = bloqueMac(cadena,i);
             }
            
             b2 = cifrabloqueDesNoPad(b1,claveA);
             if(i < n)
             {
              b1 = getxor(b2,bloqueMac(cadena,i+1));
             }
             else
             {
              b1 = getbytes(b2,4);
              retorno = ToHexa(b1);
              return retorno;             
             }              
           }                     
       }
  }
  catch(Exception e){if(Constante.VER_LOG)System.out.println("Metodo getMac : "+e.getMessage());}
  return retorno;      
}

/** Metodo que ingresando como parametros cadena, la cual esta en hexadecimal y es de tipo 
 *  String, y las llaves A y B, ambas en hexadecimal, me aplica el algoritmo 3DESede
 *  devolviendome la cadena encriptada en su representacion Hexadecimal.
 */
public static String encripta3DESede2Key(String cadena,String llave1,String llave2){
  String retorno="";  
  String cad = "";
  
    retorno = encriptaDES(cadena,llave1);
    cad = desencriptaDES(retorno,llave2);
    retorno = encriptaDES(cad,llave1);
    
  return retorno;
}

/** Metodo que ingresando como parametros cadena, la cual esta en hexadecimal y es de tipo 
 *  String, y las llaves A y B, ambas en hexadecimal, me aplica el algoritmo 3DESede
 *  devolviendome la cadena desencriptada en su representacion Hexadecimal.
 */
public static String desencripta3DESede2Key(String cadena,String llave1,String llave2){
    String retorno="";
    String cad = "";
    retorno = desencriptaDES(cadena,llave1);
    cad = encriptaDES(retorno,llave2);
    retorno = desencriptaDES(cad,llave1);
    
    return retorno;
}

/** Metodo al cual le ingreso una cadena en EBCDIC como String, y me devuelve un arreglo de bytes
 *  el cual es su representacion a ASCII.
 *
 */
public static byte[] AsciiToEbcdic(String ebcdic){
 byte[] cad = null;    
 try {
    return ebcdic.getBytes(encoding);    
 }catch(Exception e){
 	if(Constante.VER_LOG)
 	System.out.println("AsciiToEbcdic: "+e.getMessage());}
 return cad;
}

public static String EbcdicToAscii(byte[] ascii ){
 byte[] rpta;
 try {
 	ByteArrayOutputStream bRptaEBCDIC = new ByteArrayOutputStream(ascii.length);
 	ByteArrayOutputStream bRptaASCII = new ByteArrayOutputStream(ascii.length);
 	
 	int inicioDatos = -1;
 	int finDatos = -1;
 	int inicioNoDatos = -1;
 	boolean buscandoInicioDatos = true;
 	for (int i=0; i<= ascii.length - 1; i++){
 		if (buscandoInicioDatos){
 			if ((ascii[i] < -1) || (ascii[i] > 31)){
 				if (inicioNoDatos > -1){
	 				bRptaASCII.write(ascii, inicioNoDatos, i-inicioNoDatos);
	 				inicioNoDatos = -1;
 				}
 				inicioDatos = i;
 				buscandoInicioDatos = false; 
 			}else{
 				if (inicioNoDatos == -1){
 					inicioNoDatos = i;
 				}
 			}
 		}else{
 			if ((ascii[i] >= -1) && (ascii[i] <= 31)){
 				finDatos = i;
 				bRptaEBCDIC.reset();
 				bRptaEBCDIC.write(ascii, inicioDatos, finDatos-inicioDatos);
 				bRptaASCII.write( new String(bRptaEBCDIC.toByteArray(), encoding).getBytes());
 				buscandoInicioDatos = true; 
 				i--;
 			}else{
 				if (i == ascii.length - 1){
	 				bRptaEBCDIC.reset();
	 				bRptaEBCDIC.write(ascii, inicioDatos, i-inicioDatos);
	 				bRptaASCII.write( new String(bRptaEBCDIC.toByteArray(), encoding).getBytes());
 				}
 			}
 		}
 	}
 	return bRptaASCII.toString();
 }
 catch(Exception e){if(Constante.VER_LOG)System.out.println("AsciiToEbcdic : "+e.getMessage());}
 return ""; 
}
}

