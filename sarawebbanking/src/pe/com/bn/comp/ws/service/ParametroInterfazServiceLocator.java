/**
 * ParametroInterfazServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.service;

public class ParametroInterfazServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, pe.com.bn.comp.ws.service.ParametroInterfazService {

    public ParametroInterfazServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://service.ws.comp.bn.com.pe",
           "ParametroInterfazService"));

        context.setLocatorName("pe.com.bn.comp.ws.service.ParametroInterfazServiceLocator");
    }

    public ParametroInterfazServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("pe.com.bn.comp.ws.service.ParametroInterfazServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para parametroInterfaz
    private final java.lang.String parametroInterfaz_address = "http://10.7.12.129:9080/WSParamService12/services/ParametroInterfaz";

    public java.lang.String getParametroInterfazAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return parametroInterfaz_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ParametroInterfaz");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return parametroInterfaz_address;
        }
    }

    private java.lang.String parametroInterfazPortName = "ParametroInterfaz";

    // The WSDD port name defaults to the port name.
    private java.lang.String parametroInterfazWSDDPortName = "ParametroInterfaz";

    public java.lang.String getParametroInterfazWSDDPortName() {
        return parametroInterfazWSDDPortName;
    }

    public void setParametroInterfazWSDDPortName(java.lang.String name) {
        parametroInterfazWSDDPortName = name;
    }

    public pe.com.bn.comp.ws.service.ParametroInterfaz getParametroInterfaz() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getParametroInterfazAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getParametroInterfaz(endpoint);
    }

    public pe.com.bn.comp.ws.service.ParametroInterfaz getParametroInterfaz(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        pe.com.bn.comp.ws.service.ParametroInterfaz _stub =
            (pe.com.bn.comp.ws.service.ParametroInterfaz) getStub(
                parametroInterfazPortName,
                (String) getPort2NamespaceMap().get(parametroInterfazPortName),
                pe.com.bn.comp.ws.service.ParametroInterfaz.class,
                "pe.com.bn.comp.ws.service.ParametroInterfazSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(parametroInterfazWSDDPortName);
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
            if (pe.com.bn.comp.ws.service.ParametroInterfaz.class.isAssignableFrom(serviceEndpointInterface)) {
                return getParametroInterfaz();
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
        if ("ParametroInterfaz".equals(inputPortName)) {
            return getParametroInterfaz();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        parametroInterfazWSDDPortName = prefix + "/" + parametroInterfazPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ParametroInterfazService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ParametroInterfaz",
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
        if  (portName.getLocalPart().equals("ParametroInterfaz")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "datoParametroService", "datoParametroServiceRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
