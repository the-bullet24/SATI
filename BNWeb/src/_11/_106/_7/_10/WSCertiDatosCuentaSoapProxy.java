package _11._106._7._10;

public class WSCertiDatosCuentaSoapProxy implements _11._106._7._10.WSCertiDatosCuentaSoap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private _11._106._7._10.WSCertiDatosCuentaSoap wSCertiDatosCuentaSoap = null;
  
  public WSCertiDatosCuentaSoapProxy() {
    _initWSCertiDatosCuentaSoapProxy();
  }
  
  private void _initWSCertiDatosCuentaSoapProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wSCertiDatosCuentaSoap = ((_11._106._7._10.WSCertiDatosCuenta)ctx.lookup("java:comp/env/service/WSCertiDatosCuenta")).getWSCertiDatosCuentaSoap();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSCertiDatosCuentaSoap == null) {
    try{
      wSCertiDatosCuentaSoap = (new _11._106._7._10.WSCertiDatosCuentaLocator()).getWSCertiDatosCuentaSoap();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSCertiDatosCuentaSoap != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wSCertiDatosCuentaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wSCertiDatosCuentaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wSCertiDatosCuentaSoap = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wSCertiDatosCuentaSoap != null)
    ((javax.xml.rpc.Stub)wSCertiDatosCuentaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public _11._106._7._10.WSCertiDatosCuentaSoap getWSCertiDatosCuentaSoap() {
  if (wSCertiDatosCuentaSoap == null)
    _initWSCertiDatosCuentaSoapProxy();
  return wSCertiDatosCuentaSoap;
}

public java.lang.String operacionesDatosCuenta(java.lang.String wNroCuenta) throws java.rmi.RemoteException{
  if (wSCertiDatosCuentaSoap == null)
    _initWSCertiDatosCuentaSoapProxy();
  return wSCertiDatosCuentaSoap.operacionesDatosCuenta(wNroCuenta);
}


}