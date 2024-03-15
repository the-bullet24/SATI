
package pe.com.bn.bnws.ws.interfaz;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import pe.com.bn.bnws.ws.bean.RequestGateway;
import pe.com.bn.bnws.ws.bean.ResponseGateway;

@WebService(name = "GatewayInterface", targetNamespace = "http://interfaz.ws.bnws.bn.com.pe")
@XmlSeeAlso({
    pe.com.bn.bnws.ws.bean.ObjectFactory.class,
    pe.com.bn.bnws.ws.interfaz.ObjectFactory.class
})
public interface GatewayInterface {


    /**
     * 
     */
    @WebMethod(action = "recargaParametro")
    @RequestWrapper(localName = "recargaParametro", targetNamespace = "http://interfaz.ws.bnws.bn.com.pe", className = "pe.com.bn.bnws.ws.interfaz.RecargaParametro")
    @ResponseWrapper(localName = "recargaParametroResponse", targetNamespace = "http://interfaz.ws.bnws.bn.com.pe", className = "pe.com.bn.bnws.ws.interfaz.RecargaParametroResponse")
    public void recargaParametro();

    /**
     * 
     * @param request
     * @param clave
     * @return
     *     returns pe.com.bn.bnws.ws.bean.ResponseGateway
     */
    @WebMethod(action = "enviarTramaConsulta")
    @WebResult(name = "enviarTramaConsultaReturn", targetNamespace = "")
    @RequestWrapper(localName = "enviarTramaConsulta", targetNamespace = "http://interfaz.ws.bnws.bn.com.pe", className = "pe.com.bn.bnws.ws.interfaz.EnviarTramaConsulta")
    @ResponseWrapper(localName = "enviarTramaConsultaResponse", targetNamespace = "http://interfaz.ws.bnws.bn.com.pe", className = "pe.com.bn.bnws.ws.interfaz.EnviarTramaConsultaResponse")
    public ResponseGateway enviarTramaConsulta(
        @WebParam(name = "clave", targetNamespace = "")
        byte[] clave,
        @WebParam(name = "request", targetNamespace = "")
        RequestGateway request);

}
