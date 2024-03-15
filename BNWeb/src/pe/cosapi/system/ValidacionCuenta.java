/*
 * Fecha 05/07/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.system;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;


public class ValidacionCuenta {
    
    public static void main(String[] args) {
        //boolean flag = validateAccountMod11("08006901493",11);
        ValidacionCuenta valid_ = new ValidacionCuenta();
        boolean flag = valid_.validateAccountMod11("54000030093",11);
        //if(Constante.VER_LOG)
        //System.out.println("flag:"+flag);
        
    }
    
    public  boolean validateAccountMod11(String cta, int lon_cta) {
    	/*
    	 20060228 DPS Se cambia rutina de digito de checkeo (para BNacion) 
    	 */
    		if (cta == null)
    			return false;
    		cta = cta.trim();
    		
    		for (int i = 0; i < cta.length(); i++)
    			if (!ObjectUtil.isInteger("" + cta.charAt(i)))
    				return false;
    		//@01 D1
    		//return digitCheckMod11BcoComercio(cta) == Integer.valueOf(cta.substring(cta.length()-1)).intValue();
    		//@01 A...
    		int check = 0;
    		//int lon_cta = 11
    		int fac = 2;
    		int acum = 0;
    		int res = 0;
    		
    		//aseguro ceros a la izquierda 
    		cta = "00000000000" + cta;
    		cta = cta.substring(cta.length()-lon_cta,cta.length()); //right(lon_cta)
    		
    		acum = Integer.valueOf("" + cta.charAt(lon_cta -1)).intValue(); //Con "" convierte a String
    		for (int pos = lon_cta - 2; pos >= 0; pos--){
    			if (fac > 7) {
    				fac = 2;
    			}
    			int dig = Integer.valueOf("" + cta.charAt(pos)).intValue(); //Con "" convierte a String
    			acum +=(fac * dig);
    			fac++;
    		}

    		res = acum % 11;
    		if (res != 0){
    			acum = Integer.valueOf("" + cta.charAt(lon_cta -1)).intValue(); //Con "" convierte a String
    			for (int pos = lon_cta - 3; pos >= 1; pos--){
    				if (fac > 7) {
    					fac = 2;
    				}
    				int dig = Integer.valueOf("" + cta.charAt(pos)).intValue(); //Con "" convierte a String
    				acum +=(fac * dig);
    				fac++;
    			}
    			res = acum % 11;
    		}
    		return (res==0);	
    }
    
    
    public  boolean validateCCI(String sCCI) {
    	int acum = 0;
    	int res = 0; 
    	int subTotal = 0;

    	//String sCCI = "00219110510675403651";
    	String sBancoPlaza = sCCI.substring(0, 6);
    	String sNumCuenta = sCCI.substring(6, 18);
    	int digChequeoBancoPlaza = Integer.valueOf("" + sCCI.charAt(18)).intValue();

    	for (int i = 1; i <= sBancoPlaza.length(); i=i+2){ //Posiciones Impares
    		subTotal = Integer.valueOf("" + sBancoPlaza.charAt(i)).intValue() * 2; //Con "" convierte a String
    		int dec = subTotal / 10; //decenas 
    		int uni = subTotal % 10; //unidades
    		acum = acum + dec + uni;
    	}

    	for (int i = 0; i < sBancoPlaza.length(); i=i+2){ //Posiciones Pares
    		acum = acum + Integer.valueOf("" + sBancoPlaza.charAt(i)).intValue();
    	}

    	res = acum % 10;
    	if (res > 0){
    		res = 10 - res;
    	}
    	
    	if (res != digChequeoBancoPlaza){
    		return false;
    	}
    	
    	if (!validateAccountMod10(sNumCuenta, 12, Integer.parseInt(sCCI.substring(19))))
    		return false;		
    	
    	return true;
    }
    
    public  boolean validateAccountMod10(String cta, int lon_cta, int digChequeo) {
    	/* Creado por: DPS
    	 * Fecha     : 2007/01/09
    	 */
    	int acum = 0;
    	int subTotal = 0;
    	int res = 0;
    	if (cta == null)
    		return false;
    	cta = cta.trim();

    	for (int i = 1; i <= cta.length(); i=i+2){ //Posiciones Impares
    		subTotal = Integer.valueOf(""+cta.charAt(i)).intValue() * 2; //Con "" convierte a String
    		int dec = subTotal / 10; //decenas 
    		int uni = subTotal % 10; //unidades
    		acum = acum + dec + uni;
    	}

    	for (int i = 0; i < cta.length(); i=i+2){ //Posiciones Pares
    		acum = acum + Integer.valueOf(""+cta.charAt(i)).intValue();
    	}

    	res = acum % 10;
    	if (res > 0){
    		res = 10 - res;
    	}
    	return (res == digChequeo);
    }
    
}
