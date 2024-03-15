/**
 * ServiceSixSecurityAdvanceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class ServiceSixSecurityAdvanceServiceLocator extends org.apache.axis.client.Service implements com.novatronic.ws.ServiceSixSecurityAdvanceService {

    public ServiceSixSecurityAdvanceServiceLocator() {
    }


    public ServiceSixSecurityAdvanceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceSixSecurityAdvanceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    
    // Use to get a proxy class for ServiceSixSecurityAdvancePort
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_CLAVE_DINAMICA);
    
    private java.lang.String ServiceSixSecurityAdvancePort_address = url;
    
    public java.lang.String getServiceSixSecurityAdvancePortAddress() {
        return ServiceSixSecurityAdvancePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceSixSecurityAdvancePortWSDDServiceName = "ServiceSixSecurityAdvancePort";

    public java.lang.String getServiceSixSecurityAdvancePortWSDDServiceName() {
        return ServiceSixSecurityAdvancePortWSDDServiceName;
    }

    public void setServiceSixSecurityAdvancePortWSDDServiceName(java.lang.String name) {
        ServiceSixSecurityAdvancePortWSDDServiceName = name;
    }

    public com.novatronic.ws.ServiceSixSecurityAdvanceDelegate getServiceSixSecurityAdvancePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceSixSecurityAdvancePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceSixSecurityAdvancePort(endpoint);
    }

    public com.novatronic.ws.ServiceSixSecurityAdvanceDelegate getServiceSixSecurityAdvancePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.novatronic.ws.ServiceSixSecurityAdvancePortBindingStub _stub = new com.novatronic.ws.ServiceSixSecurityAdvancePortBindingStub(portAddress, this);
            _stub.setPortName(getServiceSixSecurityAdvancePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceSixSecurityAdvancePortEndpointAddress(java.lang.String address) {
        ServiceSixSecurityAdvancePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.novatronic.ws.ServiceSixSecurityAdvanceDelegate.class.isAssignableFrom(serviceEndpointInterface)) {
                com.novatronic.ws.ServiceSixSecurityAdvancePortBindingStub _stub = new com.novatronic.ws.ServiceSixSecurityAdvancePortBindingStub(new java.net.URL(ServiceSixSecurityAdvancePort_address), this);
                _stub.setPortName(getServiceSixSecurityAdvancePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServiceSixSecurityAdvancePort".equals(inputPortName)) {
            return getServiceSixSecurityAdvancePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.novatronic.com/", "ServiceSixSecurityAdvanceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.novatronic.com/", "ServiceSixSecurityAdvancePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceSixSecurityAdvancePort".equals(portName)) {
            setServiceSixSecurityAdvancePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
