/**
 * WSCertiPINBLOCKLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package _11._106._7._10;

public class WSCertiPINBLOCKLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, _11._106._7._10.WSCertiPINBLOCK {

    public WSCertiPINBLOCKLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK",
           "WSCertiPINBLOCK"));

        context.setLocatorName("_11._106._7._10.WSCertiPINBLOCKLocator");
    }

    public WSCertiPINBLOCKLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("_11._106._7._10.WSCertiPINBLOCKLocator");
    }

    // Utilizar para obtener la clase de proxy para WSCertiPINBLOCKSoap
    private final java.lang.String WSCertiPINBLOCKSoap_address = "http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK.asmx";

    public java.lang.String getWSCertiPINBLOCKSoapAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return WSCertiPINBLOCKSoap_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("WSCertiPINBLOCKSoap");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return WSCertiPINBLOCKSoap_address;
        }
    }

    private java.lang.String WSCertiPINBLOCKSoapPortName = "WSCertiPINBLOCKSoap";

    // The WSDD port name defaults to the port name.
    private java.lang.String WSCertiPINBLOCKSoapWSDDPortName = "WSCertiPINBLOCKSoap";

    public java.lang.String getWSCertiPINBLOCKSoapWSDDPortName() {
        return WSCertiPINBLOCKSoapWSDDPortName;
    }

    public void setWSCertiPINBLOCKSoapWSDDPortName(java.lang.String name) {
        WSCertiPINBLOCKSoapWSDDPortName = name;
    }

    public _11._106._7._10.WSCertiPINBLOCKSoap getWSCertiPINBLOCKSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getWSCertiPINBLOCKSoapAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getWSCertiPINBLOCKSoap(endpoint);
    }

    public _11._106._7._10.WSCertiPINBLOCKSoap getWSCertiPINBLOCKSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        _11._106._7._10.WSCertiPINBLOCKSoap _stub =
            (_11._106._7._10.WSCertiPINBLOCKSoap) getStub(
                WSCertiPINBLOCKSoapPortName,
                (String) getPort2NamespaceMap().get(WSCertiPINBLOCKSoapPortName),
                _11._106._7._10.WSCertiPINBLOCKSoap.class,
                "_11._106._7._10.WSCertiPINBLOCKSoapStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(WSCertiPINBLOCKSoapWSDDPortName);
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
            if (_11._106._7._10.WSCertiPINBLOCKSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                return getWSCertiPINBLOCKSoap();
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
        if ("WSCertiPINBLOCKSoap".equals(inputPortName)) {
            return getWSCertiPINBLOCKSoap();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        WSCertiPINBLOCKSoapWSDDPortName = prefix + "/" + WSCertiPINBLOCKSoapPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "WSCertiPINBLOCK");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "WSCertiPINBLOCKSoap",
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
        if  (portName.getLocalPart().equals("WSCertiPINBLOCKSoap")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "OperacionesPINBLOCKAhorros", "null"),
                createCall(portName, "OperacionesPINBLOCKCtasCtes", "null"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
