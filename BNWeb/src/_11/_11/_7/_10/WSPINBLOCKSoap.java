/**
 * WSPINBLOCKSoap.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package _11._11._7._10;

public interface WSPINBLOCKSoap extends java.rmi.Remote {
    public java.lang.String operacionesPINBLOCKAhorros(java.lang.String wcOperacion, java.lang.String nroTarjeta, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException;
    public java.lang.String operacionesPINBLOCKCtasCtes(java.lang.String wcOperacion, java.lang.String wTipoDocu, java.lang.String wNroDocu, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException;
}
