package pe.com.bn.sati.domain;

import pe.bn.servlet.ServletController;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;
import pe.com.bn.sati.domain.BnwsParametro.ParamUrl;
import pe.cosapi.common.Constante;



public class BnContextParametro
{
    private static LoggerSati log3 = LoggerSati.getInstance(ServletController.class.getName());
	    

   
    public static ParamUrl getParamServiceCorreo()
    {
        try
        {

            if (Constante.BN_PARAM_URL == null)
            {
                Constante.BN_PARAM_URL = Funciones.invokeServiceUrl();
            }
            if (Constante.BN_PARAM_URL == null)
                throw new Exception("PARAMETRO NO DEFINIDO getParamServiceCorreo");

        }
        catch (Exception e)
        {
            log3.error(e, "5002", "");
        }
        return Constante.BN_PARAM_URL;
    }

   
}
