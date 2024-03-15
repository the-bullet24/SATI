package pe.bn.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import pe.com.bn.comp.cryto.service.BNClaveSegura;
import pe.com.bn.comp.ws.bean.GrupoParametro;
import pe.com.bn.comp.ws.bean.ReturnProceso;
import pe.com.bn.comp.ws.bean.SistemaParametro;
import pe.com.bn.comp.ws.service.ParametroInterfazProxy;

public class Parametro
{
  

  public static synchronized HashMap getInstance() {
    if ((Constante.BN_LIST_PARAMETRO_SARAWEBBABKING == null) || 
      (Constante.BN_LIST_PARAMETRO_SARAWEBBABKING.size() < 1)) {
      makeInstance();
    }
    Object obj = Constante.BN_LIST_PARAMETRO_SARAWEBBABKING.get("HAST_DATA");
    if (obj == null) {
      makeInstance();
    }
    String proceso = (String)obj;
    if (!(proceso.equals(Constante.BN_RESULT_PROCESO_OK))) {
      makeInstance();
    }
    return Constante.BN_LIST_PARAMETRO_SARAWEBBABKING;
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
    try
    {
      clave = BNClaveSegura.encrypt(path, semillaKey);
    } catch (Exception e) {
      //log3.error(e, null, "", "");
    	e.printStackTrace();
    }

    if (clave != null)
    {
      try {
        sistemaParametro = proxi.datoParametroService(sistema, cuenta, clave, usuario);
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
            
              if (valor == null) valor = "";
             
              parametros.put(param, valor);
            }
          }
        }
        parametros.put("HAST_DATA", Constante.BN_RESULT_PROCESO_OK);
      }
      else if (sistemaParametro != null) {
    	  System.out.println();
        //log3.debug(null, "MSGNO -004 SERVICE PARAMETRO:" + sistemaParametro.getProceso().getDescripcion(), "1");
      }
      else {
        //log3.debug(null, "MSGNO -005 SERVICE PARAMETRO: El servicio de parámetros no responde", "1");
    	  System.out.println("sarawebbanking : MSGNO -005 SERVICE PARAMETRO: El servicio de parámetros no responde");
      }
    }
    else {
    	System.out.println("sarawebbanking : MSGNO -003 No encontro el archivo de claves");
      //log3.debug(null, "MSGNO -003 No encontro el archivo de claves", "1");
    }
    Constante.BN_LIST_PARAMETRO_SARAWEBBABKING = parametros;
    showString();
  }

  public static String getString(String pKey)
  {
    getInstance();
    System.out.println("URL-SARAWEBBANKING:"+(String)Constante.BN_LIST_PARAMETRO_SARAWEBBABKING.get(pKey));
    return ((String)Constante.BN_LIST_PARAMETRO_SARAWEBBABKING.get(pKey));
  }

  private static void showString()
  {
    HashMap parametros = Constante.BN_LIST_PARAMETRO_SARAWEBBABKING;
    

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
}