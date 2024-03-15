package files.target;

public class SATI620PortTypeProxy implements files.target.SATI620PortType {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private files.target.SATI620PortType __sATI620PortType = null;
  
  public SATI620PortTypeProxy() {
    _initSATI620PortTypeProxy();
  }
  
  private void _initSATI620PortTypeProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      __sATI620PortType = ((files.target.SATI620Service)ctx.lookup("java:comp/env/service/SATI620Service")).getSATI620Port();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__sATI620PortType == null) {
    try{
      __sATI620PortType = (new files.target.SATI620ServiceLocator()).getSATI620Port();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__sATI620PortType != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)__sATI620PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)__sATI620PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  __sATI620PortType = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (__sATI620PortType != null)
    ((javax.xml.rpc.Stub)__sATI620PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public files.target.SATI620PortType getSATI620PortType() {
  if (__sATI620PortType == null)
    _initSATI620PortTypeProxy();
  return __sATI620PortType;
}

public com.BCDDTP00O.www.TCOMMAREA SATI620Operation(com.BCDDTP00I.www.TCOMMAREA TCOMMAREAPart) throws java.rmi.RemoteException{
  if (__sATI620PortType == null)
    _initSATI620PortTypeProxy();
  return __sATI620PortType.SATI620Operation(TCOMMAREAPart);
}


}