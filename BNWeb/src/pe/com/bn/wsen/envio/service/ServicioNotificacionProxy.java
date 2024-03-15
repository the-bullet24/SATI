package pe.com.bn.wsen.envio.service;

public class ServicioNotificacionProxy implements pe.com.bn.wsen.envio.service.ServicioNotificacion {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private pe.com.bn.wsen.envio.service.ServicioNotificacion __servicioNotificacion = null;
  
  public ServicioNotificacionProxy() {
    _initServicioNotificacionProxy();
  }
  
  private void _initServicioNotificacionProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      __servicioNotificacion = ((pe.com.bn.wsen.envio.service.ServicioNotificacionService)ctx.lookup("java:comp/env/service/ServicioNotificacionService")).getServicioNotificacion();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__servicioNotificacion == null) {
    try{
      __servicioNotificacion = (new pe.com.bn.wsen.envio.service.ServicioNotificacionServiceLocator()).getServicioNotificacion();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__servicioNotificacion != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)__servicioNotificacion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)__servicioNotificacion)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  __servicioNotificacion = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (__servicioNotificacion != null)
    ((javax.xml.rpc.Stub)__servicioNotificacion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public pe.com.bn.wsen.envio.service.ServicioNotificacion getServicioNotificacion() {
  if (__servicioNotificacion == null)
    _initServicioNotificacionProxy();
  return __servicioNotificacion;
}

public pe.com.bn.wsen.envio.response.EnvioNotificacionResponse opEnviarNotificacionDoc(pe.com.bn.wsen.envio.request.EnvioNotificacionRequest request) throws java.rmi.RemoteException{
  if (__servicioNotificacion == null)
    _initServicioNotificacionProxy();
  return __servicioNotificacion.opEnviarNotificacionDoc(request);
}

public pe.com.bn.wsen.envio.response.AcuseReciboResponse opAcuseRecibo(pe.com.bn.wsen.envio.request.AcuseReciboRequest request) throws java.rmi.RemoteException{
  if (__servicioNotificacion == null)
    _initServicioNotificacionProxy();
  return __servicioNotificacion.opAcuseRecibo(request);
}

public pe.com.bn.wsen.envio.response.ImagenResponse opLogo() throws java.rmi.RemoteException{
  if (__servicioNotificacion == null)
    _initServicioNotificacionProxy();
  return __servicioNotificacion.opLogo();
}


}