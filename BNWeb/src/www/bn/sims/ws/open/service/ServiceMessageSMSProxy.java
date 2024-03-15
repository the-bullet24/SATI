package www.bn.sims.ws.open.service;

public class ServiceMessageSMSProxy implements www.bn.sims.ws.open.service.ServiceMessageSMS {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private www.bn.sims.ws.open.service.ServiceMessageSMS __serviceMessageSMS = null;
  
  public ServiceMessageSMSProxy() {
    _initServiceMessageSMSProxy();
  }
  
  private void _initServiceMessageSMSProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      __serviceMessageSMS = ((www.bn.sims.ws.open.service.ServiceMessageSMSService)ctx.lookup("java:comp/env/service/ServiceMessageSMSService")).getServiceMessageSMS();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__serviceMessageSMS == null) {
    try{
      __serviceMessageSMS = (new www.bn.sims.ws.open.service.ServiceMessageSMSServiceLocator()).getServiceMessageSMS();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__serviceMessageSMS != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)__serviceMessageSMS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)__serviceMessageSMS)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  __serviceMessageSMS = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (__serviceMessageSMS != null)
    ((javax.xml.rpc.Stub)__serviceMessageSMS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public www.bn.sims.ws.open.service.ServiceMessageSMS getServiceMessageSMS() {
  if (__serviceMessageSMS == null)
    _initServiceMessageSMSProxy();
  return __serviceMessageSMS;
}

public www.bn.sims.ws.open.bean.ResponseMessage sendMessage(www.bn.sims.ws.open.bean.RequestMessage requestMessage) throws java.rmi.RemoteException{
  if (__serviceMessageSMS == null)
    _initServiceMessageSMSProxy();
  return __serviceMessageSMS.sendMessage(requestMessage);
}


}