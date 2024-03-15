package pe.cosapi.system.services;

import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.soap.SOAPException;

import pe.cosapi.common.ObjectUtil;

import com.ibm.bn.InterfazServicios_PortTypeProxy;
import com.ibm.websphere.ActivitySession.TimeoutOutOfRangeException;

public class UtilWebServices
{
    private static UtilWebServices unico = null;

    public static UtilWebServices getInstance()
    {
        if (unico == null)
            unico = new UtilWebServices();
        return unico;    }

    /**
     * *************************** INICIO DEL NUEVO LLAMADO AL WEBSERVICE
     * **************************************************
     */
    public Map callNewSendHost(String trama) throws Exception
    {
        System.out.println("************************* Begin New CallSendHost ***********************");

        String value = "";
        Map output = new HashMap();        

        /**
         * ********************************** DEFINICION DE PARAMETROS DE SALIDA
         * ***********************************
         */
        javax.xml.rpc.holders.StringHolder stringHolder = new StringHolder();
        javax.xml.rpc.holders.IntHolder intHolder = new IntHolder();

        /**
         * ********************************** CREANDO UN EJEMPLAR DE LA CLASE
         * PROXY ***********************************
         */

        try
        {
            /**
             * *************************** LLAMANDO AL METODO DEL PROXY
             * ***********************************
             */
            InterfazServicios_PortTypeProxy proxy = new InterfazServicios_PortTypeProxy();
            //log.warn("Antes de llamar al webservices");
            //log.warn("trama Envia al Webservices trama:"+trama);
            String codigoWEBSERVICE = ObjectUtil.getCodigoTRX();
          

            proxy.enviarTramaConsulta(codigoWEBSERVICE, trama, stringHolder, intHolder);
            value = stringHolder.value;
                    
      
            //System.out.println("entregando los parametros de salida");
            if (intHolder.value != 0)
            {
                //log.warn("en valor del int value es cero
                // intHolder.value="+intHolder.value);
                throw new SOAPException(String.valueOf(intHolder.value), String.valueOf(intHolder.value));
            }
            output.put("tramaRespuesta", value);
            output.put("codResp", String.valueOf(intHolder.value));
        } catch (Exception e)
        {
            output.put("tramaRespuesta", "");
            output.put("codResp", "");
            if (e instanceof SOAPException)
            {
                System.out.println("trouble with Web Service SOAPException");
                System.out.println("trouble with Web Service SOAPException: \n" + e + "\n");
                e.printStackTrace();
               // throw new SOAPException(String.valueOf(intHolder.value), String.valueOf(intHolder.value));
            } else if (e instanceof RemoteException)
            {
                System.out.println("trouble with Web Service RemoteException");
                System.out.println("trouble with Web Service RemoteException: \n" + e);
                e.printStackTrace();
               // throw new SOAPException(String.valueOf(intHolder.value), String.valueOf(intHolder.value));
            } else if (e instanceof TimeoutOutOfRangeException)
            {
                System.out.println("trouble with Web Service TimeoutOutOfRangeException");
                System.out.println("trouble with Web Service TimeoutOutOfRangeException: \n" + e);
                e.printStackTrace();
              //  throw new SOAPException(String.valueOf(intHolder.value), String.valueOf(intHolder.value));
            } else if (e instanceof SocketTimeoutException)
            {
                System.out.println("trouble with Web Service TimeoutException");
                System.out.println("trouble with Web Service TimeoutException: \n" + e);
                e.printStackTrace();
              //  throw new SOAPException(String.valueOf(intHolder.value), String.valueOf(intHolder.value));
            }else{
            	System.out.println("Exception: \n"+ e);
            	e.printStackTrace();
            }
            	
            return output;
        }
        return output;
    }
    /*
     * public String[] callWebServiceValidatorCardAhorro(String
     * nroDocumento,String wPINEncript,String wPINBLOCKNew,String
     * wPINBLOCKConf)throws Exception{ String wcOperation =
     * Constante.WCOPERATION_VALID_KEY6; return
     * callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * 
     * public String[] callWebServiceGenerationKey6DigitsAhorro(String
     * nroDocumento,String wPINEncript,String wPINBLOCKNew,String
     * wPINBLOCKConf)throws Exception{ String wcOperation =
     * Constante.WCOPERATION_GENERATION_KEY6; return
     * callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * 
     * public String[] callWebServiceChangeKey6DigitsAhorro(String
     * nroDocumento,String wPINEncript,String wPINBLOCKNew,String
     * wPINBLOCKConf)throws Exception{ String wcOperation =
     * Constante.WCOPERATION_CHANGE_KEY6; return
     * callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * 
     * public String[] callWebServiceValidatorCardCorriente(String
     * wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String
     * wPINBLOCKConf)throws Exception{ String wcOperation =
     * Constante.WCOPERATION_VALID_KEY6; return
     * callPinWebServices(wcOperation,wTipDoc,nroDocumento,1,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * 
     * public String[] callWebServiceGenerationKey6DigitsCorriente(String
     * wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String
     * wPINBLOCKConf)throws Exception{ String wcOperation =
     * Constante.WCOPERATION_GENERATION_KEY6; return
     * callPinWebServices(wcOperation,wTipDoc,nroDocumento,1,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * 
     * public String[] callWebServiceChangeKey6DigitsCorriente(String
     * wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String
     * wPINBLOCKConf)throws Exception{ String wcOperation =
     * Constante.WCOPERATION_CHANGE_KEY6; return
     * callPinWebServices(wcOperation,wTipDoc,nroDocumento,1,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     */
    /*
     * private String[] callPinWebServices(String wcOperation,String
     * wTipDoc,String nroDocumento,int opt,String wPINEncript,String
     * wPINBLOCKNew,String wPINBLOCKConf)throws Exception{ String respuesta =
     * ""; WSPINBLOCKSoapProxy proxy_ = new WSPINBLOCKSoapProxy(); String[] rst =
     * null; try { if (opt==0){
     * System.out.println("wcOperation:"+wcOperation+"|wTipDoc="+wTipDoc+"|nroDocumento="+nroDocumento+"|wPINEncript="+wPINEncript+"|wPINBLOCKNew="+wPINBLOCKNew+"|wPINBLOCKConf="+wPINBLOCKConf);
     * respuesta =
     * proxy_.operacionesPINBLOCKAhorros(wcOperation,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * else if (opt==1){
     * System.out.println("wcOperation:"+wcOperation+"|wTipDoc="+wTipDoc+"|nroDocumento="+nroDocumento+"|wPINEncript="+wPINEncript+"|wPINBLOCKNew="+wPINBLOCKNew+"|wPINBLOCKConf="+wPINBLOCKConf);
     * respuesta =
     * proxy_.operacionesPINBLOCKCtasCtes(wcOperation,wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf); }
     * System.out.println("Web Services respuesta="+respuesta); if
     * (!ObjectUtil.isStringBlank(respuesta)){ rst =
     * ObjectUtil.getArrayStrings(respuesta,"|"); }else{
     * System.out.println("Error de Mela..."); throw new
     * ArrayRuleException(ConstanteError.GENERICO,"Error de Data del
     * Webservice."); } } catch (Exception e) { e.printStackTrace(); if (e
     * instanceof ArrayRuleException){ System.out.println("lanzando la
     * ArrayRuleException"); throw e; }
     * 
     * if (e instanceof RemoteException){ System.out.println("trouble with Web Service
     * RemoteException"); System.out.println("",e); throw new SOAPException("",""); }
     *  } return rst; }
     */
    /** ***************************************************************************** */

    /**
     * *************************** INICIO WEBSERVICE DATOS CUENTA AHORROS
     * **************************************************
     */
    /*
     * public String[] callOperacionesDatosCuenta(String numeroCuenta)throws
     * Exception{ String[] arr = null; try { WSDatosCuentaSoapProxy proxy_ = new
     * WSDatosCuentaSoapProxy(); String result =
     * proxy_.operacionesDatosCuenta(numeroCuenta); if
     * (!ObjectUtil.isStringBlank(result)){ arr =
     * ObjectUtil.getArrayStrings(result,"|"); if
     * (!Constante.COD_RESP.equals(arr[0])){ throw new
     * ArrayRuleException(ConstanteError.GENERICO,arr[1]); } }else{
     * System.out.println("Error: resultado nulo del Webservice
     * callOperacionesDatosCuenta."); throw new SOAPException("",""); } }catch
     * (Exception e) { if (e instanceof ArrayRuleException){ System.out.println("lanzando
     * la ArrayRuleException"); throw e; }
     * 
     * if (e instanceof RemoteException){ System.out.println("trouble with Web Service
     * RemoteException"); System.out.println("",e); throw new SOAPException("",""); } }
     * return arr; }
     */
    /**
     * *************************** FIN WEBSERVICE DATOS CUENTA AHORROS
     * **************************************************
     */

}