/**
 * CustomerAuthentication.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.app.sarawebmanager.ws.bean;

public interface CustomerAuthentication extends java.rmi.Remote {
    public java.lang.String autenticarUsuarios(java.lang.String trama) throws java.rmi.RemoteException;
    public java.lang.String retornaCajeroPorUsuario(java.lang.String codUsuario, java.lang.String codModulo) throws java.rmi.RemoteException;
}
