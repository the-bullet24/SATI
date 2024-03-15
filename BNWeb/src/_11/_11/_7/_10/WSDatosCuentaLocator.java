/**
 * WSDatosCuentaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package _11._11._7._10;

public class WSDatosCuentaLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, _11._11._7._10.WSDatosCuenta {

    public WSDatosCuentaLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta",
           "WSDatosCuenta"));

        context.setLocatorName("_11._11._7._10.WSDatosCuentaLocator");
    }

    public WSDatosCuentaLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("_11._11._7._10.WSDatosCuentaLocator");
    }

    // Utilizar para obtener la clase de proxy para WSDatosCuentaSoap
    private final java.lang.String WSDatosCuentaSoap_address = "http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta.asmx";

    public java.lang.String getWSDatosCuentaSoapAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return WSDatosCuentaSoap_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("WSDatosCuentaSoap");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return WSDatosCuentaSoap_address;
        }
    }

    private java.lang.String WSDatosCuentaSoapPortName = "WSDatosCuentaSoap";

    // The WSDD port name defaults to the port name.
    private java.lang.String WSDatosCuentaSoapWSDDPortName = "WSDatosCuentaSoap";

    public java.lang.String getWSDatosCuentaSoapWSDDPortName() {
        return WSDatosCuentaSoapWSDDPortName;
    }

    public void setWSDatosCuentaSoapWSDDPortName(java.lang.String name) {
        WSDatosCuentaSoapWSDDPortName = name;
    }

    public _11._11._7._10.WSDatosCuentaSoap getWSDatosCuentaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getWSDatosCuentaSoapAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getWSDatosCuentaSoap(endpoint);
    }

    public _11._11._7._10.WSDatosCuentaSoap getWSDatosCuentaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        _11._11._7._10.WSDatosCuentaSoap _stub =
            (_11._11._7._10.WSDatosCuentaSoap) getStub(
                WSDatosCuentaSoapPortName,
                (String) getPort2NamespaceMap().get(WSDatosCuentaSoapPortName),
                _11._11._7._10.WSDatosCuentaSoap.class,
                "_11._11._7._10.WSDatosCuentaSoapStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(WSDatosCuentaSoapWSDDPortName);
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
            if (_11._11._7._10.WSDatosCuentaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                return getWSDatosCuentaSoap();
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
        if ("WSDatosCuentaSoap".equals(inputPortName)) {
            return getWSDatosCuentaSoap();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        WSDatosCuentaSoapWSDDPortName = prefix + "/" + WSDatosCuentaSoapPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "WSDatosCuenta");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "WSDatosCuentaSoap",
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
        if  (portName.getLocalPart().equals("WSDatosCuentaSoap")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "OperacionesDatosCuenta", "null"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
