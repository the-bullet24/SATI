/**
 * ServicioBNSmsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public class ServicioBNSmsServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, pe.com.bn.wscn.ws.ServicioBNSmsService {

    public ServicioBNSmsServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://ws.wscn.bn.com.pe",
           "ServicioBNSmsService"));

        context.setLocatorName("pe.com.bn.wscn.ws.ServicioBNSmsServiceLocator");
    }

    public ServicioBNSmsServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("pe.com.bn.wscn.ws.ServicioBNSmsServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para servicioBNSms
    private final java.lang.String servicioBNSms_address = "http://10.7.12.129:9080/WSBNConfiguracion/services/ServicioBNSms";

    public java.lang.String getServicioBNSmsAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return servicioBNSms_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ServicioBNSms");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return servicioBNSms_address;
        }
    }

    private java.lang.String servicioBNSmsPortName = "ServicioBNSms";

    // The WSDD port name defaults to the port name.
    private java.lang.String servicioBNSmsWSDDPortName = "ServicioBNSms";

    public java.lang.String getServicioBNSmsWSDDPortName() {
        return servicioBNSmsWSDDPortName;
    }

    public void setServicioBNSmsWSDDPortName(java.lang.String name) {
        servicioBNSmsWSDDPortName = name;
    }

    public pe.com.bn.wscn.ws.ServicioBNSms getServicioBNSms() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getServicioBNSmsAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getServicioBNSms(endpoint);
    }

    public pe.com.bn.wscn.ws.ServicioBNSms getServicioBNSms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        pe.com.bn.wscn.ws.ServicioBNSms _stub =
            (pe.com.bn.wscn.ws.ServicioBNSms) getStub(
                servicioBNSmsPortName,
                (String) getPort2NamespaceMap().get(servicioBNSmsPortName),
                pe.com.bn.wscn.ws.ServicioBNSms.class,
                "pe.com.bn.wscn.ws.ServicioBNSmsSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(servicioBNSmsWSDDPortName);
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
            if (pe.com.bn.wscn.ws.ServicioBNSms.class.isAssignableFrom(serviceEndpointInterface)) {
                return getServicioBNSms();
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
        if ("ServicioBNSms".equals(inputPortName)) {
            return getServicioBNSms();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        servicioBNSmsWSDDPortName = prefix + "/" + servicioBNSmsPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSmsService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ServicioBNSms",
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
        if  (portName.getLocalPart().equals("ServicioBNSms")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "opGenerarSmsAfiliacion", "opGenerarSmsAfiliacionRequest"),
                createCall(portName, "opValidarSMSAfliacion", "opValidarSMSAfliacionRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
