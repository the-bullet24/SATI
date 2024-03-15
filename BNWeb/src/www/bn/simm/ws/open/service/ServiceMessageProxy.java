package www.bn.simm.ws.open.service;

public class ServiceMessageProxy implements www.bn.simm.ws.open.service.ServiceMessage {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private www.bn.simm.ws.open.service.ServiceMessage __serviceMessage = null;
  
  public ServiceMessageProxy() {
    _initServiceMessageProxy();
  }
  
  private void _initServiceMessageProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      __serviceMessage = ((www.bn.simm.ws.open.service.ServiceMessageService)ctx.lookup("java:comp/env/service/ServiceMessageService")).getServiceMessage();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__serviceMessage == null) {
    try{
      __serviceMessage = (new www.bn.simm.ws.open.service.ServiceMessageServiceLocator()).getServiceMessage();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__serviceMessage != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)__serviceMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)__serviceMessage)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  __serviceMessage = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (__serviceMessage != null)
    ((javax.xml.rpc.Stub)__serviceMessage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public www.bn.simm.ws.open.service.ServiceMessage getServiceMessage() {
  if (__serviceMessage == null)
    _initServiceMessageProxy();
  return __serviceMessage;
}

public www.bn.simm.ws.open.bean.ResponseMessage sendMessage(www.bn.simm.ws.open.bean.RequestMessage requestMessage) throws java.rmi.RemoteException{
  if (__serviceMessage == null)
    _initServiceMessageProxy();
  return __serviceMessage.sendMessage(requestMessage);
}


}