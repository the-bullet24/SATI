/**
 * GatewayInterface.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.bn.service.interfaz;

public interface GatewayInterface extends java.rmi.Remote {
    public pe.bn.service.bean.ResponseGateway enviarTramaConsulta(byte[] clave, pe.bn.service.bean.RequestGateway request) throws java.rmi.RemoteException;
}
