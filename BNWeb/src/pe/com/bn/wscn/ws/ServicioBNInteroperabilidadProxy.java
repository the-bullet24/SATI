package pe.com.bn.wscn.ws;

public class ServicioBNInteroperabilidadProxy implements pe.com.bn.wscn.ws.ServicioBNInteroperabilidad {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private pe.com.bn.wscn.ws.ServicioBNInteroperabilidad __servicioBNInteroperabilidad = null;
  
  public ServicioBNInteroperabilidadProxy() {
    _initServicioBNInteroperabilidadProxy();
  }
  
  private void _initServicioBNInteroperabilidadProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __servicioBNInteroperabilidad = ((pe.com.bn.wscn.ws.ServicioBNInteroperabilidadService)ctx.lookup("java:comp/env/service/ServicioBNInteroperabilidadService")).getServicioBNInteroperabilidad();
      }
      catch (javax.naming.NamingException namingException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("JNDI lookup failure: javax.naming.NamingException: " + namingException.getMessage());
          namingException.printStackTrace(System.out);
        }
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__servicioBNInteroperabilidad == null && !_useJNDIOnly) {
      try {
        __servicioBNInteroperabilidad = (new pe.com.bn.wscn.ws.ServicioBNInteroperabilidadServiceLocator()).getServicioBNInteroperabilidad();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__servicioBNInteroperabilidad != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__servicioBNInteroperabilidad)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__servicioBNInteroperabilidad)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __servicioBNInteroperabilidad = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __servicioBNInteroperabilidad = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    if (__servicioBNInteroperabilidad != null)
      ((javax.xml.rpc.Stub)__servicioBNInteroperabilidad)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opEliminarCliente(pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest request) throws java.rmi.RemoteException{
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad.opEliminarCliente(request);
  }
  
  public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opActualizarCliente(pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest request) throws java.rmi.RemoteException{
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad.opActualizarCliente(request);
  }
  
  public pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse opConsultaAfilicion(pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest request) throws java.rmi.RemoteException{
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad.opConsultaAfilicion(request);
  }
  
  public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opRegistroContacto(pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest request) throws java.rmi.RemoteException{
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad.opRegistroContacto(request);
  }
  
  public pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse opBarridoClientes(pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest request) throws java.rmi.RemoteException{
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad.opBarridoClientes(request);
  }
  
  public pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse opObtenerMotivDesaInteroperabilidad() throws java.rmi.RemoteException{
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad.opObtenerMotivDesaInteroperabilidad();
  }
  
  
  public pe.com.bn.wscn.ws.ServicioBNInteroperabilidad getServicioBNInteroperabilidad() {
    if (__servicioBNInteroperabilidad == null)
      _initServicioBNInteroperabilidadProxy();
    return __servicioBNInteroperabilidad;
  }
  
}