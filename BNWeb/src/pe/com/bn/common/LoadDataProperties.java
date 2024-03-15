package pe.com.bn.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import pe.cosapi.common.ConstanteSesion;

/*
 * Singleton para carga de properties(Datos del cliente)
 * */

public class LoadDataProperties {
	private static LoadDataProperties instance = null;
    private ResourceBundle rb;
    HashMap<String,String> mapaOT = new HashMap<String, String>();
   
    protected LoadDataProperties() throws IOException{
        rb = ResourceBundle.getBundle("softoken");
        mapaOT.put(ConstanteSesion.CODIGO_OPERADOR_MOVISTAR,ConstanteSesion.DESCRIPCION_OPERADOR_MOVISTAR);
        mapaOT.put(ConstanteSesion.CODIGO_OPERADOR_CLARO,ConstanteSesion.DESCRIPCION_OPERADOR_CLARO);
        mapaOT.put(ConstanteSesion.CODIGO_OPERADOR_ENTEL,ConstanteSesion.DESCRIPCION_OPERADOR_ENTEL);
        mapaOT.put(ConstanteSesion.CODIGO_OPERADOR_BITEL,ConstanteSesion.DESCRIPCION_OPERADOR_BITEL);
    }

    /*Instancia Unica*/
    public static LoadDataProperties getInstance() {
        if(instance == null) {
            try {
                instance = new LoadDataProperties();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return instance;
    }
    
    public String getValue(String key) {
    	return rb.getString(key);
    }
    
    public String getValueFromMapaOT(String key) {
    	return mapaOT.get(key);
    }
}
