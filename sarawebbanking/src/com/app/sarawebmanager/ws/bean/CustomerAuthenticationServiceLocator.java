/**
 * CustomerAuthenticationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.app.sarawebmanager.ws.bean;

import pe.bn.listener.Constante;
import pe.bn.listener.Parametro;

public class CustomerAuthenticationServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.app.sarawebmanager.ws.bean.CustomerAuthenticationService {

    public CustomerAuthenticationServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://bean.ws.sarawebmanager.app.com",
           "CustomerAuthenticationService"));

        context.setLocatorName("com.app.sarawebmanager.ws.bean.CustomerAuthenticationServiceLocator");
    }

    public CustomerAuthenticationServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.app.sarawebmanager.ws.bean.CustomerAuthenticationServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para customerAuthentication
    String url = Parametro.getString(Constante.BN_PARAM_WS_SARAWEBBANKING_AUTENTICACION);
    
    private final java.lang.String customerAuthentication_address = url;

    public java.lang.String getCustomerAuthenticationAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return customerAuthentication_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("CustomerAuthentication");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return customerAuthentication_address;
        }
    }

    private java.lang.String customerAuthenticationPortName = "CustomerAuthentication";

    // The WSDD port name defaults to the port name.
    private java.lang.String customerAuthenticationWSDDPortName = "CustomerAuthentication";

    public java.lang.String getCustomerAuthenticationWSDDPortName() {
        return customerAuthenticationWSDDPortName;
    }

    public void setCustomerAuthenticationWSDDPortName(java.lang.String name) {
        customerAuthenticationWSDDPortName = name;
    }

    public com.app.sarawebmanager.ws.bean.CustomerAuthentication getCustomerAuthentication() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getCustomerAuthenticationAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getCustomerAuthentication(endpoint);
    }

    public com.app.sarawebmanager.ws.bean.CustomerAuthentication getCustomerAuthentication(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.app.sarawebmanager.ws.bean.CustomerAuthentication _stub =
            (com.app.sarawebmanager.ws.bean.CustomerAuthentication) getStub(
                customerAuthenticationPortName,
                (String) getPort2NamespaceMap().get(customerAuthenticationPortName),
                com.app.sarawebmanager.ws.bean.CustomerAuthentication.class,
                "com.app.sarawebmanager.ws.bean.CustomerAuthenticationSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(customerAuthenticationWSDDPortName);
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
            if (com.app.sarawebmanager.ws.bean.CustomerAuthentication.class.isAssignableFrom(serviceEndpointInterface)) {
                return getCustomerAuthentication();
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
        if ("CustomerAuthentication".equals(inputPortName)) {
            return getCustomerAuthentication();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        customerAuthenticationWSDDPortName = prefix + "/" + customerAuthenticationPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthenticationService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "CustomerAuthentication",
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
        if  (portName.getLocalPart().equals("CustomerAuthentication")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "autenticarUsuarios", "autenticarUsuariosRequest"),
                createCall(portName, "retornaCajeroPorUsuario", "retornaCajeroPorUsuarioRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
