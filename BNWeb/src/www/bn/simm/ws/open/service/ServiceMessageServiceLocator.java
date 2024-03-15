/**
 * ServiceMessageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.simm.ws.open.service;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class ServiceMessageServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, www.bn.simm.ws.open.service.ServiceMessageService {

    public ServiceMessageServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://service.open.ws.simm.bn.www",
           "ServiceMessageService"));

        context.setLocatorName("www.bn.simm.ws.open.service.ServiceMessageServiceLocator");
    }

    public ServiceMessageServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("www.bn.simm.ws.open.service.ServiceMessageServiceLocator");
    }

    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_SIMM);
    
    private final java.lang.String serviceMessage_address = url;
    
    public java.lang.String getServiceMessageAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return serviceMessage_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ServiceMessage");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return serviceMessage_address;
        }
    }

    private java.lang.String serviceMessagePortName = "ServiceMessage";

    // The WSDD port name defaults to the port name.
    private java.lang.String serviceMessageWSDDPortName = "ServiceMessage";

    public java.lang.String getServiceMessageWSDDPortName() {
        return serviceMessageWSDDPortName;
    }

    public void setServiceMessageWSDDPortName(java.lang.String name) {
        serviceMessageWSDDPortName = name;
    }

    public www.bn.simm.ws.open.service.ServiceMessage getServiceMessage() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getServiceMessageAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getServiceMessage(endpoint);
    }

    public www.bn.simm.ws.open.service.ServiceMessage getServiceMessage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        www.bn.simm.ws.open.service.ServiceMessage _stub =
            (www.bn.simm.ws.open.service.ServiceMessage) getStub(
                serviceMessagePortName,
                (String) getPort2NamespaceMap().get(serviceMessagePortName),
                www.bn.simm.ws.open.service.ServiceMessage.class,
                "www.bn.simm.ws.open.service.ServiceMessageSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(serviceMessageWSDDPortName);
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
            if (www.bn.simm.ws.open.service.ServiceMessage.class.isAssignableFrom(serviceEndpointInterface)) {
                return getServiceMessage();
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
        if ("ServiceMessage".equals(inputPortName)) {
            return getServiceMessage();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        serviceMessageWSDDPortName = prefix + "/" + serviceMessagePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ServiceMessageService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ServiceMessage",
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
        if  (portName.getLocalPart().equals("ServiceMessage")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "sendMessage", "sendMessageRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
