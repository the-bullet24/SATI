package pe.bn.service.interfaz;

public class GatewayInterfaceProxy implements pe.bn.service.interfaz.GatewayInterface {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private pe.bn.service.interfaz.GatewayInterface __gatewayInterface = null;
  
  public GatewayInterfaceProxy() {
    _initGatewayInterfaceProxy();
  }
  
  private void _initGatewayInterfaceProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __gatewayInterface = ((pe.bn.service.interfaz.GatewayInterfaceService)ctx.lookup("java:comp/env/service/GatewayInterfaceService")).getGatewayInterface();
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
    if (__gatewayInterface == null && !_useJNDIOnly) {
      try {
        __gatewayInterface = (new pe.bn.service.interfaz.GatewayInterfaceServiceLocator()).getGatewayInterface();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__gatewayInterface != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__gatewayInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__gatewayInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __gatewayInterface = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __gatewayInterface = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__gatewayInterface == null)
      _initGatewayInterfaceProxy();
    if (__gatewayInterface != null)
      ((javax.xml.rpc.Stub)__gatewayInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pe.bn.service.bean.ResponseGateway enviarTramaConsulta(byte[] clave, pe.bn.service.bean.RequestGateway request) throws java.rmi.RemoteException{
    if (__gatewayInterface == null)
      _initGatewayInterfaceProxy();
    return __gatewayInterface.enviarTramaConsulta(clave, request);
  }
  
  
  public pe.bn.service.interfaz.GatewayInterface getGatewayInterface() {
    if (__gatewayInterface == null)
      _initGatewayInterfaceProxy();
    return __gatewayInterface;
  }
  
}