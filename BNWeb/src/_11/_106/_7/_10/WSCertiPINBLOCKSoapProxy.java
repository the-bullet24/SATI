package _11._106._7._10;

public class WSCertiPINBLOCKSoapProxy implements _11._106._7._10.WSCertiPINBLOCKSoap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private _11._106._7._10.WSCertiPINBLOCKSoap wSCertiPINBLOCKSoap = null;
  
  public WSCertiPINBLOCKSoapProxy() {
    _initWSCertiPINBLOCKSoapProxy();
  }
  
  private void _initWSCertiPINBLOCKSoapProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wSCertiPINBLOCKSoap = ((_11._106._7._10.WSCertiPINBLOCK)ctx.lookup("java:comp/env/service/WSCertiPINBLOCK")).getWSCertiPINBLOCKSoap();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSCertiPINBLOCKSoap == null) {
    try{
      wSCertiPINBLOCKSoap = (new _11._106._7._10.WSCertiPINBLOCKLocator()).getWSCertiPINBLOCKSoap();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSCertiPINBLOCKSoap != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wSCertiPINBLOCKSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wSCertiPINBLOCKSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wSCertiPINBLOCKSoap = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wSCertiPINBLOCKSoap != null)
    ((javax.xml.rpc.Stub)wSCertiPINBLOCKSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public _11._106._7._10.WSCertiPINBLOCKSoap getWSCertiPINBLOCKSoap() {
  if (wSCertiPINBLOCKSoap == null)
    _initWSCertiPINBLOCKSoapProxy();
  return wSCertiPINBLOCKSoap;
}

public java.lang.String operacionesPINBLOCKAhorros(java.lang.String wcOperacion, java.lang.String nroTarjeta, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException{
  if (wSCertiPINBLOCKSoap == null)
    _initWSCertiPINBLOCKSoapProxy();
  return wSCertiPINBLOCKSoap.operacionesPINBLOCKAhorros(wcOperacion, nroTarjeta, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf);
}

public java.lang.String operacionesPINBLOCKCtasCtes(java.lang.String wcOperacion, java.lang.String wTipoDocu, java.lang.String wNroDocu, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException{
  if (wSCertiPINBLOCKSoap == null)
    _initWSCertiPINBLOCKSoapProxy();
  return wSCertiPINBLOCKSoap.operacionesPINBLOCKCtasCtes(wcOperacion, wTipoDocu, wNroDocu, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf);
}


}