/**
 * InterfazServicios_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package com.ibm.bn;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class InterfazServicios_ServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.ibm.bn.InterfazServicios_Service {

    public InterfazServicios_ServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://bn.ibm.com/InterfazServicios/",
           "InterfazServicios"));

        context.setLocatorName("com.ibm.bn.InterfazServicios_ServiceLocator");
    }

    public InterfazServicios_ServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.ibm.bn.InterfazServicios_ServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para interfazServiciosSOAP
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_INTERFAZ_SERVICIOS);
    
    private final java.lang.String interfazServiciosSOAP_address = url;

   
    
    public java.lang.String getInterfazServiciosSOAPAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return interfazServiciosSOAP_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("InterfazServiciosSOAP");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return interfazServiciosSOAP_address;
        }
    }

    private java.lang.String interfazServiciosSOAPPortName = "InterfazServiciosSOAP";

    // The WSDD port name defaults to the port name.
    private java.lang.String interfazServiciosSOAPWSDDPortName = "InterfazServiciosSOAP";

    public java.lang.String getInterfazServiciosSOAPWSDDPortName() {
        return interfazServiciosSOAPWSDDPortName;
    }

    public void setInterfazServiciosSOAPWSDDPortName(java.lang.String name) {
        interfazServiciosSOAPWSDDPortName = name;
    }

    public com.ibm.bn.InterfazServicios_PortType getInterfazServiciosSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getInterfazServiciosSOAPAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getInterfazServiciosSOAP(endpoint);
    }

    public com.ibm.bn.InterfazServicios_PortType getInterfazServiciosSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.ibm.bn.InterfazServicios_PortType _stub =
            (com.ibm.bn.InterfazServicios_PortType) getStub(
                interfazServiciosSOAPPortName,
                (String) getPort2NamespaceMap().get(interfazServiciosSOAPPortName),
                com.ibm.bn.InterfazServicios_PortType.class,
                "com.ibm.bn.InterfazServiciosSOAPStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(interfazServiciosSOAPWSDDPortName);
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
            if (com.ibm.bn.InterfazServicios_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                return getInterfazServiciosSOAP();
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
        if ("InterfazServiciosSOAP".equals(inputPortName)) {
            return getInterfazServiciosSOAP();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        interfazServiciosSOAPWSDDPortName = prefix + "/" + interfazServiciosSOAPPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "InterfazServicios");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "InterfazServiciosSOAP",
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
        if  (portName.getLocalPart().equals("InterfazServiciosSOAP")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "enviarTramaConsulta", "null"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
