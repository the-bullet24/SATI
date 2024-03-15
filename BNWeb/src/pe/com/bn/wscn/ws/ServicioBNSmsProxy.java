package pe.com.bn.wscn.ws;

public class ServicioBNSmsProxy implements pe.com.bn.wscn.ws.ServicioBNSms {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private pe.com.bn.wscn.ws.ServicioBNSms __servicioBNSms = null;
  
  public ServicioBNSmsProxy() {
    _initServicioBNSmsProxy();
  }
  
  private void _initServicioBNSmsProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __servicioBNSms = ((pe.com.bn.wscn.ws.ServicioBNSmsService)ctx.lookup("java:comp/env/service/ServicioBNSmsService")).getServicioBNSms();
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
    if (__servicioBNSms == null && !_useJNDIOnly) {
      try {
        __servicioBNSms = (new pe.com.bn.wscn.ws.ServicioBNSmsServiceLocator()).getServicioBNSms();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__servicioBNSms != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__servicioBNSms)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__servicioBNSms)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __servicioBNSms = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __servicioBNSms = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__servicioBNSms == null)
      _initServicioBNSmsProxy();
    if (__servicioBNSms != null)
      ((javax.xml.rpc.Stub)__servicioBNSms)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse opGenerarSmsAfiliacion(pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest request) throws java.rmi.RemoteException{
    if (__servicioBNSms == null)
      _initServicioBNSmsProxy();
    return __servicioBNSms.opGenerarSmsAfiliacion(request);
  }
  
  public pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse opValidarSMSAfliacion(pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest request) throws java.rmi.RemoteException{
    if (__servicioBNSms == null)
      _initServicioBNSmsProxy();
    return __servicioBNSms.opValidarSMSAfliacion(request);
  }
  
  
  public pe.com.bn.wscn.ws.ServicioBNSms getServicioBNSms() {
    if (__servicioBNSms == null)
      _initServicioBNSmsProxy();
    return __servicioBNSms;
  }
  
}