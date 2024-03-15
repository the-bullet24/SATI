/**
 * CustomerAuthenticationService.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.app.sarawebmanager.ws.bean;

public interface CustomerAuthenticationService extends javax.xml.rpc.Service {
    public com.app.sarawebmanager.ws.bean.CustomerAuthentication getCustomerAuthentication() throws javax.xml.rpc.ServiceException;

    public java.lang.String getCustomerAuthenticationAddress();

    public com.app.sarawebmanager.ws.bean.CustomerAuthentication getCustomerAuthentication(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
