package pe.bn.listener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pe.com.bn.comp.cryto.service.BNClaveSegura;
import pe.com.bn.comp.ws.bean.GrupoParametro;
import pe.com.bn.comp.ws.bean.ReturnProceso;
import pe.com.bn.comp.ws.bean.SistemaParametro;
import pe.com.bn.comp.ws.service.ParametroInterfazProxy;

public class Parametro
{
  

  public static synchronized HashMap getInstance() {
    if ((ConstanteParametros.BN_LIST_PARAMETRO == null) || 
      (ConstanteParametros.BN_LIST_PARAMETRO.size() < 1)) {
      makeInstance();
    }
    Object obj = ConstanteParametros.BN_LIST_PARAMETRO.get("HAST_DATA");
    if (obj == null) {
      makeInstance();
    }
    String proceso = (String)obj;
    if (!(proceso.equals(ConstanteParametros.BN_RESULT_PROCESO_OK))) {
      makeInstance();
    }
    return ConstanteParametros.BN_LIST_PARAMETRO;
  }

  private static synchronized void makeInstance()
  {
    ResourceBundle rb = ResourceBundle.getBundle("parametro");
    HashMap parametros = null;

    SistemaParametro sistemaParametro = null;
       
	String keyPath = rb.getString("bn.claveSegura.keyPath");
	String keyName = rb.getString("bn.claveSegura.keyName");
	String sistema = rb.getString("bn.claveSegura.sistema");
	String cuenta = rb.getString("bn.claveSegura.cuenta");
	String semillaKey = rb.getString("bn.claveSegura.semillaKey");
	String usuario = rb.getString("bn.claveSegura.usuario");

    ParametroInterfazProxy proxi = new ParametroInterfazProxy();

    String path = keyPath + "/" + keyName;
    byte[] clave = (byte[])null;
    byte[] bytesEncoded = (byte[])null;
    try
    {
      clave = BNClaveSegura.encrypt(path, semillaKey);
     
      File file = new File(path);
      byte[] fileContent = Files.readAllBytes(file.toPath());
      
     bytesEncoded = Base64.encodeBase64(fileContent);
      
      
    } catch (Exception e) {
      //log3.error(e, null, "", "");
    	e.printStackTrace();
    }

    if (clave != null)
    {
      try {
        sistemaParametro = proxi.datoParametroService(sistema, cuenta, clave, usuario);
              
        System.out.println("sistemaParametro.getProceso().getCodigo():"+ sistemaParametro.getProceso().getCodigo());
        System.out.println("sistemaParametro.getProceso().getDescripcion():"+ sistemaParametro.getProceso().getDescripcion());
        
       
     
      } catch (Exception e) {
        e.printStackTrace();
      }

      if ((sistemaParametro != null) && ("00000".equals(sistemaParametro.getProceso().getCodigo()))) {
        parametros = new HashMap();

        for (int i = 0; i < sistemaParametro.getGrupoParametro().length; ++i)
        {
        	
          for (int j = 0; j < sistemaParametro.getGrupoParametro()[i].getParametro().length; ++j)
          {
            String param = sistemaParametro.getGrupoParametro()[i].getParametro()[j].getAliasParam();
         
          
            if (param != null) {
              String valor = sistemaParametro.getGrupoParametro()[i].getParametro()[j].getValorParam();
              System.out.println("BNWEB-VALOR:"+valor);
              if (valor == null) valor = "";
              parametros.put(param, valor);
            }
          }
        }
        parametros.put("HAST_DATA", ConstanteParametros.BN_RESULT_PROCESO_OK);
      }
      else if (sistemaParametro != null) {
        //log3.debug(null, "MSGNO -004 SERVICE PARAMETRO:" + sistemaParametro.getProceso().getDescripcion(), "1");
      }
      else {
       
    	  System.out.println("BNWeb : MSGNO -005 SERVICE PARAMETRO: El servicio de parámetros no responde");
      }
    }
    else {
      //log3.debug(null, "MSGNO -003 No encontro el archivo de claves", "1");
    	System.out.println("BNWeb : MSGNO -003 No encontro el archivo de claves");
    }
    ConstanteParametros.BN_LIST_PARAMETRO = parametros;
    showString();
  }

  public static String getString(String pKey)
  {
    getInstance();
    //System.out.println(""+(String)ConstanteParametros.BN_LIST_PARAMETRO.get(pKey));
    return ((String)ConstanteParametros.BN_LIST_PARAMETRO.get(pKey));
  }

  private static void showString()
  {
    HashMap parametros = ConstanteParametros.BN_LIST_PARAMETRO;
    

    //if (!(log.getLoggerPrintDebugNivel_2().equals("true"))) return;

    StringBuffer sb = new StringBuffer("");
    sb.append("\n*********** Parameter Load ***********\n");

    if (parametros != null)
    {
      Set s = parametros.entrySet();
      Iterator it = s.iterator();

      while (it.hasNext())
      {
        Map.Entry m = (Map.Entry)(Map.Entry)it.next();
        String key = (String)m.getKey();
        if (key != null) {
          String value = (String)m.getValue();
          String temp = (key.length() < 30) ? key + repeat(" ", 30 - key.length()) : key.substring(0, 30);
          sb.append(temp + ":\t" + value.length() + "\t" + "[" + value + "]\t\n");
        }
      }
    }
    //log3.debug(null, sb.toString(), "1");
  }
	public static String repeat(String s, int n)
	  {
	    if (s == null) {
	      return null;
	    }
	    String sb = "";
	    for (int i = 0; i < n; ++i) {
	      sb = sb + s;
	    }
	    return sb;
	  }
	
public static void cargaKeyGateway(){
		
	ResourceBundle rb = ResourceBundle.getBundle("parametro");
		
	  try
	        {


			String keyPath = rb.getString("bn.claveSegura.llave.keyPath");
			String keyName = rb.getString("bn.claveSegura.llave.keyName");
			String semillaKey = rb.getString("bn.claveSegura.llave.semillaKey");


		    String path = keyPath + "/" + keyName;
		    byte[] clave = (byte[])null;
		    byte[] bytesEncoded = (byte[])null;
		  
		    clave = BNClaveSegura.encrypt(path, semillaKey);
		     
		    File file = new File(path);
		    byte[] fileContent = Files.readAllBytes(file.toPath());
		      
		    pe.com.bn.common.Constante.BN_KEY_WS_GATEWAY = fileContent;
		  			
		    } catch (Exception e) {
			//log3.error("cargaKeyContext",e,"","");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	  
		
	}
	public static byte[] archivoToByte(String filePath)throws Exception {

        File file = new File(filePath);
        FileInputStream fileInputStream;
        byte[] data = null;
        byte[] finalData = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
           fileInputStream = new FileInputStream(file);
           data = new byte[(int)file.length()];
           finalData = new byte[(int)file.length()];
           byteArrayOutputStream = new ByteArrayOutputStream();

           fileInputStream.read(data);
           byteArrayOutputStream.write(data);
           finalData = byteArrayOutputStream.toByteArray();

           fileInputStream.close(); 

       } catch (FileNotFoundException e) {
    	  
          e.printStackTrace();
       } catch (IOException e) {
    	   e.printStackTrace();
       }

       return finalData;

   }
}