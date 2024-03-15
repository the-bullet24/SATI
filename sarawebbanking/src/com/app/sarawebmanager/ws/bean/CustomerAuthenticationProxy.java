package com.app.sarawebmanager.ws.bean;

public class CustomerAuthenticationProxy implements com.app.sarawebmanager.ws.bean.CustomerAuthentication {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private com.app.sarawebmanager.ws.bean.CustomerAuthentication __customerAuthentication = null;
  
  public CustomerAuthenticationProxy() {
    _initCustomerAuthenticationProxy();
  }
  
  private void _initCustomerAuthenticationProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __customerAuthentication = ((com.app.sarawebmanager.ws.bean.CustomerAuthenticationService)ctx.lookup("java:comp/env/service/CustomerAuthenticationService")).getCustomerAuthentication();
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
    if (__customerAuthentication == null && !_useJNDIOnly) {
      try {
        __customerAuthentication = (new com.app.sarawebmanager.ws.bean.CustomerAuthenticationServiceLocator()).getCustomerAuthentication();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__customerAuthentication != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__customerAuthentication)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__customerAuthentication)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __customerAuthentication = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __customerAuthentication = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__customerAuthentication == null)
      _initCustomerAuthenticationProxy();
    if (__customerAuthentication != null)
      ((javax.xml.rpc.Stub)__customerAuthentication)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public java.lang.String autenticarUsuarios(java.lang.String trama) throws java.rmi.RemoteException{
    if (__customerAuthentication == null)
      _initCustomerAuthenticationProxy();
    return __customerAuthentication.autenticarUsuarios(trama);
  }
  
  public java.lang.String retornaCajeroPorUsuario(java.lang.String codUsuario, java.lang.String codModulo) throws java.rmi.RemoteException{
    if (__customerAuthentication == null)
      _initCustomerAuthenticationProxy();
    return __customerAuthentication.retornaCajeroPorUsuario(codUsuario, codModulo);
  }
  
  
  public com.app.sarawebmanager.ws.bean.CustomerAuthentication getCustomerAuthentication() {
    if (__customerAuthentication == null)
      _initCustomerAuthenticationProxy();
    return __customerAuthentication;
  }
  
}