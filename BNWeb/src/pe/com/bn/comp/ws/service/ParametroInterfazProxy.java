package pe.com.bn.comp.ws.service;

public class ParametroInterfazProxy implements pe.com.bn.comp.ws.service.ParametroInterfaz {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private pe.com.bn.comp.ws.service.ParametroInterfaz __parametroInterfaz = null;
  
  public ParametroInterfazProxy() {
    _initParametroInterfazProxy();
  }
  
  private void _initParametroInterfazProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __parametroInterfaz = ((pe.com.bn.comp.ws.service.ParametroInterfazService)ctx.lookup("java:comp/env/service/ParametroInterfazService")).getParametroInterfaz();
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
    if (__parametroInterfaz == null && !_useJNDIOnly) {
      try {
        __parametroInterfaz = (new pe.com.bn.comp.ws.service.ParametroInterfazServiceLocator()).getParametroInterfaz();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__parametroInterfaz != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__parametroInterfaz)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__parametroInterfaz)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __parametroInterfaz = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __parametroInterfaz = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__parametroInterfaz == null)
      _initParametroInterfazProxy();
    if (__parametroInterfaz != null)
      ((javax.xml.rpc.Stub)__parametroInterfaz)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.com.bn.comp.ws.bean.SistemaParametro datoParametroService(java.lang.String sistema, java.lang.String cuenta, byte[] clave, java.lang.String idUsuario) throws java.rmi.RemoteException{
    if (__parametroInterfaz == null)
      _initParametroInterfazProxy();
    return __parametroInterfaz.datoParametroService(sistema, cuenta, clave, idUsuario);
  }
  
  
  public pe.com.bn.comp.ws.service.ParametroInterfaz getParametroInterfaz() {
    if (__parametroInterfaz == null)
      _initParametroInterfazProxy();
    return __parametroInterfaz;
  }
  
}