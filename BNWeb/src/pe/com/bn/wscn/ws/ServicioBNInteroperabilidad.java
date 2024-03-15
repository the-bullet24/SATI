/**
 * ServicioBNInteroperabilidad.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public interface ServicioBNInteroperabilidad extends java.rmi.Remote {
    public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opEliminarCliente(pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opActualizarCliente(pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse opConsultaAfilicion(pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opRegistroContacto(pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse opBarridoClientes(pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse opObtenerMotivDesaInteroperabilidad() throws java.rmi.RemoteException;
}
