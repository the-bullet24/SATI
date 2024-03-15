/**
 * ServicioNotificacion.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf10631.06 v81706232132
 */

package pe.com.bn.wsen.envio.service;

public interface ServicioNotificacion extends java.rmi.Remote {
    public pe.com.bn.wsen.envio.response.EnvioNotificacionResponse opEnviarNotificacionDoc(pe.com.bn.wsen.envio.request.EnvioNotificacionRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wsen.envio.response.AcuseReciboResponse opAcuseRecibo(pe.com.bn.wsen.envio.request.AcuseReciboRequest request) throws java.rmi.RemoteException;
    public pe.com.bn.wsen.envio.response.ImagenResponse opLogo() throws java.rmi.RemoteException;
}
