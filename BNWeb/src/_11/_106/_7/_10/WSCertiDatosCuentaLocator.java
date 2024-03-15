/**
 * WSCertiDatosCuentaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package _11._106._7._10;

public class WSCertiDatosCuentaLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, _11._106._7._10.WSCertiDatosCuenta {

    public WSCertiDatosCuentaLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta",
           "WSCertiDatosCuenta"));

        context.setLocatorName("_11._106._7._10.WSCertiDatosCuentaLocator");
    }

    public WSCertiDatosCuentaLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("_11._106._7._10.WSCertiDatosCuentaLocator");
    }

    // Utilizar para obtener la clase de proxy para WSCertiDatosCuentaSoap
    private final java.lang.String WSCertiDatosCuentaSoap_address = "http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta.asmx";

    public java.lang.String getWSCertiDatosCuentaSoapAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return WSCertiDatosCuentaSoap_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("WSCertiDatosCuentaSoap");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return WSCertiDatosCuentaSoap_address;
        }
    }

    private java.lang.String WSCertiDatosCuentaSoapPortName = "WSCertiDatosCuentaSoap";

    // The WSDD port name defaults to the port name.
    private java.lang.String WSCertiDatosCuentaSoapWSDDPortName = "WSCertiDatosCuentaSoap";

    public java.lang.String getWSCertiDatosCuentaSoapWSDDPortName() {
        return WSCertiDatosCuentaSoapWSDDPortName;
    }

    public void setWSCertiDatosCuentaSoapWSDDPortName(java.lang.String name) {
        WSCertiDatosCuentaSoapWSDDPortName = name;
    }

    public _11._106._7._10.WSCertiDatosCuentaSoap getWSCertiDatosCuentaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getWSCertiDatosCuentaSoapAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getWSCertiDatosCuentaSoap(endpoint);
    }

    public _11._106._7._10.WSCertiDatosCuentaSoap getWSCertiDatosCuentaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        _11._106._7._10.WSCertiDatosCuentaSoap _stub =
            (_11._106._7._10.WSCertiDatosCuentaSoap) getStub(
                WSCertiDatosCuentaSoapPortName,
                (String) getPort2NamespaceMap().get(WSCertiDatosCuentaSoapPortName),
                _11._106._7._10.WSCertiDatosCuentaSoap.class,
                "_11._106._7._10.WSCertiDatosCuentaSoapStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(WSCertiDatosCuentaSoapWSDDPortName);
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
            if (_11._106._7._10.WSCertiDatosCuentaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                return getWSCertiDatosCuentaSoap();
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
        if ("WSCertiDatosCuentaSoap".equals(inputPortName)) {
            return getWSCertiDatosCuentaSoap();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        WSCertiDatosCuentaSoapWSDDPortName = prefix + "/" + WSCertiDatosCuentaSoapPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "WSCertiDatosCuenta");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "WSCertiDatosCuentaSoap",
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
        if  (portName.getLocalPart().equals("WSCertiDatosCuentaSoap")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "OperacionesDatosCuenta", "null"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
