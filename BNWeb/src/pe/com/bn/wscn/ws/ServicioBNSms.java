/**
 * ServicioBNSms.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public interface ServicioBNSms extends java.rmi.Remote {
    public pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse opGenerarSmsAfiliacion(pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse opValidarSMSAfliacion(pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest request) throws java.rmi.RemoteException;
}
