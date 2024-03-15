package _211._50._1._10;

public class WSPRDDatosCuentaSoapProxy implements _211._50._1._10.WSPRDDatosCuentaSoap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private _211._50._1._10.WSPRDDatosCuentaSoap wSPRDDatosCuentaSoap = null;
  
  public WSPRDDatosCuentaSoapProxy() {
    _initWSPRDDatosCuentaSoapProxy();
  }
  
  private void _initWSPRDDatosCuentaSoapProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wSPRDDatosCuentaSoap = ((_211._50._1._10.WSPRDDatosCuenta)ctx.lookup("java:comp/env/service/WSPRDDatosCuenta")).getWSPRDDatosCuentaSoap();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSPRDDatosCuentaSoap == null) {
    try{
      wSPRDDatosCuentaSoap = (new _211._50._1._10.WSPRDDatosCuentaLocator()).getWSPRDDatosCuentaSoap();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSPRDDatosCuentaSoap != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wSPRDDatosCuentaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wSPRDDatosCuentaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wSPRDDatosCuentaSoap = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wSPRDDatosCuentaSoap != null)
    ((javax.xml.rpc.Stub)wSPRDDatosCuentaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public _211._50._1._10.WSPRDDatosCuentaSoap getWSPRDDatosCuentaSoap() {
  if (wSPRDDatosCuentaSoap == null)
    _initWSPRDDatosCuentaSoapProxy();
  return wSPRDDatosCuentaSoap;
}

public java.lang.String operacionesDatosCuenta(java.lang.String wNroCuenta) throws java.rmi.RemoteException{
  if (wSPRDDatosCuentaSoap == null)
    _initWSPRDDatosCuentaSoapProxy();
  return wSPRDDatosCuentaSoap.operacionesDatosCuenta(wNroCuenta);
}


}