/**
 * GatewayInterfaceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.bn.service.interfaz;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class GatewayInterfaceServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, pe.bn.service.interfaz.GatewayInterfaceService {

    public GatewayInterfaceServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://interfaz.service.bn.pe",
           "GatewayInterfaceService"));

        context.setLocatorName("pe.bn.service.interfaz.GatewayInterfaceServiceLocator");
    }

    public GatewayInterfaceServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("pe.bn.service.interfaz.GatewayInterfaceServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para gatewayInterface
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_GATEWAY_HOST_SATI);
    
    private final java.lang.String gatewayInterface_address = url;

    public java.lang.String getGatewayInterfaceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return gatewayInterface_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("GatewayInterface");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return gatewayInterface_address;
        }
    }

    private java.lang.String gatewayInterfacePortName = "GatewayInterface";

    // The WSDD port name defaults to the port name.
    private java.lang.String gatewayInterfaceWSDDPortName = "GatewayInterface";

    public java.lang.String getGatewayInterfaceWSDDPortName() {
        return gatewayInterfaceWSDDPortName;
    }

    public void setGatewayInterfaceWSDDPortName(java.lang.String name) {
        gatewayInterfaceWSDDPortName = name;
    }

    public pe.bn.service.interfaz.GatewayInterface getGatewayInterface() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getGatewayInterfaceAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getGatewayInterface(endpoint);
    }

    public pe.bn.service.interfaz.GatewayInterface getGatewayInterface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        pe.bn.service.interfaz.GatewayInterface _stub =
            (pe.bn.service.interfaz.GatewayInterface) getStub(
                gatewayInterfacePortName,
                (String) getPort2NamespaceMap().get(gatewayInterfacePortName),
                pe.bn.service.interfaz.GatewayInterface.class,
                "pe.bn.service.interfaz.GatewayInterfaceSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(gatewayInterfaceWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (pe.bn.service.interfaz.GatewayInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                return getGatewayInterface();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: No hay ninguna implementación de apéndice para la interfaz:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("GatewayInterface".equals(inputPortName)) {
            return getGatewayInterface();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        gatewayInterfaceWSDDPortName = prefix + "/" + gatewayInterfacePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "GatewayInterfaceService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "GatewayInterface",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
        if  (portName.getLocalPart().equals("GatewayInterface")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "enviarTramaConsulta", "enviarTramaConsultaRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
