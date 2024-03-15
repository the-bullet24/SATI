/**
 * SATI620ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package files.target;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class SATI620ServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, files.target.SATI620Service {

    public SATI620ServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "file://target.files",
           "SATI620Service"));

        context.setLocatorName("files.target.SATI620ServiceLocator");
    }

    public SATI620ServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("files.target.SATI620ServiceLocator");
    }
    
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_6201);
    
    private final java.lang.String SATI620Port_address = url;
     
 
    
    public java.lang.String getSATI620PortAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return SATI620Port_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("SATI620Port");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return SATI620Port_address;
        }
    }

    private java.lang.String SATI620PortPortName = "SATI620Port";

    // The WSDD port name defaults to the port name.
    private java.lang.String SATI620PortWSDDPortName = "SATI620Port";

    public java.lang.String getSATI620PortWSDDPortName() {
        return SATI620PortWSDDPortName;
    }

    public void setSATI620PortWSDDPortName(java.lang.String name) {
        SATI620PortWSDDPortName = name;
    }

    public files.target.SATI620PortType getSATI620Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getSATI620PortAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getSATI620Port(endpoint);
    }

    public files.target.SATI620PortType getSATI620Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        files.target.SATI620PortType _stub =
            (files.target.SATI620PortType) getStub(
                SATI620PortPortName,
                (String) getPort2NamespaceMap().get(SATI620PortPortName),
                files.target.SATI620PortType.class,
                "files.target.SATI620BindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(SATI620PortWSDDPortName);
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
            if (files.target.SATI620PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                return getSATI620Port();
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
        if ("SATI620Port".equals(inputPortName)) {
            return getSATI620Port();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        SATI620PortWSDDPortName = prefix + "/" + SATI620PortPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return super.getServiceName();
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "SATI620Port",
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
        if  (portName.getLocalPart().equals("SATI620Port")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "SATI620Operation", "SATI620OperationRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
