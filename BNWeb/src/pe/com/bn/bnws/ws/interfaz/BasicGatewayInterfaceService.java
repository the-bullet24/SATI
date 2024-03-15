
package pe.com.bn.bnws.ws.interfaz;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "BasicGatewayInterfaceService", targetNamespace = "http://interfaz.ws.bnws.bn.com.pe", wsdlLocation = "WEB-INF/wsdl/ws2bnorq.wsdl")
public class BasicGatewayInterfaceService
    extends Service
{

    private final static URL BASICGATEWAYINTERFACESERVICE_WSDL_LOCATION;
    private final static WebServiceException BASICGATEWAYINTERFACESERVICE_EXCEPTION;
    private final static QName BASICGATEWAYINTERFACESERVICE_QNAME = new QName("http://interfaz.ws.bnws.bn.com.pe", "BasicGatewayInterfaceService");

    static {
            BASICGATEWAYINTERFACESERVICE_WSDL_LOCATION = pe.com.bn.bnws.ws.interfaz.BasicGatewayInterfaceService.class.getResource("/WEB-INF/wsdl/ws2bnorq.wsdl");
        System.out.println(BASICGATEWAYINTERFACESERVICE_WSDL_LOCATION);
            WebServiceException e = null;
        if (BASICGATEWAYINTERFACESERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/ws2bnorq.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        BASICGATEWAYINTERFACESERVICE_EXCEPTION = e;
    }

    public BasicGatewayInterfaceService() {
        super(__getWsdlLocation(), BASICGATEWAYINTERFACESERVICE_QNAME);
    }

    public BasicGatewayInterfaceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BASICGATEWAYINTERFACESERVICE_QNAME, features);
    }

    public BasicGatewayInterfaceService(URL wsdlLocation) {
        super(wsdlLocation, BASICGATEWAYINTERFACESERVICE_QNAME);
    }

    public BasicGatewayInterfaceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BASICGATEWAYINTERFACESERVICE_QNAME, features);
    }

    public BasicGatewayInterfaceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BasicGatewayInterfaceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GatewayInterface
     */
    @WebEndpoint(name = "GatewayInterfacePort")
    public GatewayInterface getGatewayInterfacePort() {
        return super.getPort(new QName("http://interfaz.ws.bnws.bn.com.pe", "GatewayInterfacePort"), GatewayInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GatewayInterface
     */
    @WebEndpoint(name = "GatewayInterfacePort")
    public GatewayInterface getGatewayInterfacePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://interfaz.ws.bnws.bn.com.pe", "GatewayInterfacePort"), GatewayInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BASICGATEWAYINTERFACESERVICE_EXCEPTION!= null) {
            throw BASICGATEWAYINTERFACESERVICE_EXCEPTION;
        }
        return BASICGATEWAYINTERFACESERVICE_WSDL_LOCATION;
    }

}
