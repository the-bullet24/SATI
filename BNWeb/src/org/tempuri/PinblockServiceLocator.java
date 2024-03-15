/**
 * PinblockServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class PinblockServiceLocator extends org.apache.axis.client.Service implements org.tempuri.PinblockService {

    public PinblockServiceLocator() {
    }


    public PinblockServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PinblockServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PinblockServiceSoap
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_PINBLOCK_V2);
    
    
    private java.lang.String PinblockServiceSoap_address = url;

    public java.lang.String getPinblockServiceSoapAddress() {
        return PinblockServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PinblockServiceSoapWSDDServiceName = "PinblockServiceSoap";

    public java.lang.String getPinblockServiceSoapWSDDServiceName() {
        return PinblockServiceSoapWSDDServiceName;
    }

    public void setPinblockServiceSoapWSDDServiceName(java.lang.String name) {
        PinblockServiceSoapWSDDServiceName = name;
    }

    public org.tempuri.PinblockServiceSoap getPinblockServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PinblockServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPinblockServiceSoap(endpoint);
    }

    public org.tempuri.PinblockServiceSoap getPinblockServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.PinblockServiceSoapStub _stub = new org.tempuri.PinblockServiceSoapStub(portAddress, this);
            _stub.setPortName(getPinblockServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPinblockServiceSoapEndpointAddress(java.lang.String address) {
        PinblockServiceSoap_address = address;
    }


    // Use to get a proxy class for PinblockServiceSoap12
    private java.lang.String PinblockServiceSoap12_address =url;

    public java.lang.String getPinblockServiceSoap12Address() {
        return PinblockServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PinblockServiceSoap12WSDDServiceName = "PinblockServiceSoap12";

    public java.lang.String getPinblockServiceSoap12WSDDServiceName() {
        return PinblockServiceSoap12WSDDServiceName;
    }

    public void setPinblockServiceSoap12WSDDServiceName(java.lang.String name) {
        PinblockServiceSoap12WSDDServiceName = name;
    }

    public org.tempuri.PinblockServiceSoap getPinblockServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PinblockServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPinblockServiceSoap12(endpoint);
    }

    public org.tempuri.PinblockServiceSoap getPinblockServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.PinblockServiceSoap12Stub _stub = new org.tempuri.PinblockServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getPinblockServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPinblockServiceSoap12EndpointAddress(java.lang.String address) {
        PinblockServiceSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.PinblockServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.PinblockServiceSoapStub _stub = new org.tempuri.PinblockServiceSoapStub(new java.net.URL(PinblockServiceSoap_address), this);
                _stub.setPortName(getPinblockServiceSoapWSDDServiceName());
                return _stub;
            }
            if (org.tempuri.PinblockServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.PinblockServiceSoap12Stub _stub = new org.tempuri.PinblockServiceSoap12Stub(new java.net.URL(PinblockServiceSoap12_address), this);
                _stub.setPortName(getPinblockServiceSoap12WSDDServiceName());
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
        if ("PinblockServiceSoap".equals(inputPortName)) {
            return getPinblockServiceSoap();
        }
        else if ("PinblockServiceSoap12".equals(inputPortName)) {
            return getPinblockServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "PinblockService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "PinblockServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "PinblockServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PinblockServiceSoap".equals(portName)) {
            setPinblockServiceSoapEndpointAddress(address);
        }
        else 
if ("PinblockServiceSoap12".equals(portName)) {
            setPinblockServiceSoap12EndpointAddress(address);
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
