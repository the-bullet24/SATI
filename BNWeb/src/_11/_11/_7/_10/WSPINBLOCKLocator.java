/**
 * WSPINBLOCKLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package _11._11._7._10;

public class WSPINBLOCKLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, _11._11._7._10.WSPINBLOCK {

    public WSPINBLOCKLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK",
           "WSPINBLOCK"));

        context.setLocatorName("_11._11._7._10.WSPINBLOCKLocator");
    }

    public WSPINBLOCKLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("_11._11._7._10.WSPINBLOCKLocator");
    }

    // Utilizar para obtener la clase de proxy para WSPINBLOCKSoap
    private final java.lang.String WSPINBLOCKSoap_address = "http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK.asmx";

    public java.lang.String getWSPINBLOCKSoapAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return WSPINBLOCKSoap_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("WSPINBLOCKSoap");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return WSPINBLOCKSoap_address;
        }
    }

    private java.lang.String WSPINBLOCKSoapPortName = "WSPINBLOCKSoap";

    // The WSDD port name defaults to the port name.
    private java.lang.String WSPINBLOCKSoapWSDDPortName = "WSPINBLOCKSoap";

    public java.lang.String getWSPINBLOCKSoapWSDDPortName() {
        return WSPINBLOCKSoapWSDDPortName;
    }

    public void setWSPINBLOCKSoapWSDDPortName(java.lang.String name) {
        WSPINBLOCKSoapWSDDPortName = name;
    }

    public _11._11._7._10.WSPINBLOCKSoap getWSPINBLOCKSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getWSPINBLOCKSoapAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getWSPINBLOCKSoap(endpoint);
    }

    public _11._11._7._10.WSPINBLOCKSoap getWSPINBLOCKSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        _11._11._7._10.WSPINBLOCKSoap _stub =
            (_11._11._7._10.WSPINBLOCKSoap) getStub(
                WSPINBLOCKSoapPortName,
                (String) getPort2NamespaceMap().get(WSPINBLOCKSoapPortName),
                _11._11._7._10.WSPINBLOCKSoap.class,
                "_11._11._7._10.WSPINBLOCKSoapStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(WSPINBLOCKSoapWSDDPortName);
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
            if (_11._11._7._10.WSPINBLOCKSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                return getWSPINBLOCKSoap();
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
        if ("WSPINBLOCKSoap".equals(inputPortName)) {
            return getWSPINBLOCKSoap();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        WSPINBLOCKSoapWSDDPortName = prefix + "/" + WSPINBLOCKSoapPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "WSPINBLOCK");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "WSPINBLOCKSoap",
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
        if  (portName.getLocalPart().equals("WSPINBLOCKSoap")) {
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
