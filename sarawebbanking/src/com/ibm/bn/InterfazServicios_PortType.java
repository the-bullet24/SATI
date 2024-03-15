/**
 * InterfazServicios_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.ibm.bn;

public interface InterfazServicios_PortType extends java.rmi.Remote {
    public void enviarTramaConsulta(java.lang.String codTrans, java.lang.String tramaConsulta, javax.xml.rpc.holders.StringHolder tramaRespuesta, javax.xml.rpc.holders.IntHolder codRes) throws java.rmi.RemoteException;
}
