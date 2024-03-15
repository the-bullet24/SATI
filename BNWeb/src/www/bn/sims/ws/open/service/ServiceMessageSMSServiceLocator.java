/**
 * ServiceMessageSMSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.service;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class ServiceMessageSMSServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, www.bn.sims.ws.open.service.ServiceMessageSMSService {

    public ServiceMessageSMSServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://service.open.ws.sims.bn.www",
           "ServiceMessageSMSService"));

        context.setLocatorName("www.bn.sims.ws.open.service.ServiceMessageSMSServiceLocator");
    }

    public ServiceMessageSMSServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("www.bn.sims.ws.open.service.ServiceMessageSMSServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para serviceMessageSMS
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_SIMS);
    
    private final java.lang.String serviceMessageSMS_address = url;

    
    public java.lang.String getServiceMessageSMSAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return serviceMessageSMS_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ServiceMessageSMS");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return serviceMessageSMS_address;
        }
    }

    private java.lang.String serviceMessageSMSPortName = "ServiceMessageSMS";

    // The WSDD port name defaults to the port name.
    private java.lang.String serviceMessageSMSWSDDPortName = "ServiceMessageSMS";

    public java.lang.String getServiceMessageSMSWSDDPortName() {
        return serviceMessageSMSWSDDPortName;
    }

    public void setServiceMessageSMSWSDDPortName(java.lang.String name) {
        serviceMessageSMSWSDDPortName = name;
    }

    public www.bn.sims.ws.open.service.ServiceMessageSMS getServiceMessageSMS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getServiceMessageSMSAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getServiceMessageSMS(endpoint);
    }

    public www.bn.sims.ws.open.service.ServiceMessageSMS getServiceMessageSMS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        www.bn.sims.ws.open.service.ServiceMessageSMS _stub =
            (www.bn.sims.ws.open.service.ServiceMessageSMS) getStub(
                serviceMessageSMSPortName,
                (String) getPort2NamespaceMap().get(serviceMessageSMSPortName),
                www.bn.sims.ws.open.service.ServiceMessageSMS.class,
                "www.bn.sims.ws.open.service.ServiceMessageSMSSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(serviceMessageSMSWSDDPortName);
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
            if (www.bn.sims.ws.open.service.ServiceMessageSMS.class.isAssignableFrom(serviceEndpointInterface)) {
                return getServiceMessageSMS();
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
        if ("ServiceMessageSMS".equals(inputPortName)) {
            return getServiceMessageSMS();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        serviceMessageSMSWSDDPortName = prefix + "/" + serviceMessageSMSPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.sims.bn.www", "ServiceMessageSMSService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ServiceMessageSMS",
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
        if  (portName.getLocalPart().equals("ServiceMessageSMS")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "sendMessage", "sendMessageRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
