package _11._11._7._10;

public class WSPINBLOCKSoapProxy implements _11._11._7._10.WSPINBLOCKSoap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private _11._11._7._10.WSPINBLOCKSoap wSPINBLOCKSoap = null;
  
  public WSPINBLOCKSoapProxy() {
    _initWSPINBLOCKSoapProxy();
  }
  
  private void _initWSPINBLOCKSoapProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wSPINBLOCKSoap = ((_11._11._7._10.WSPINBLOCK)ctx.lookup("java:comp/env/service/WSPINBLOCK")).getWSPINBLOCKSoap();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSPINBLOCKSoap == null) {
    try{
      wSPINBLOCKSoap = (new _11._11._7._10.WSPINBLOCKLocator()).getWSPINBLOCKSoap();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSPINBLOCKSoap != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wSPINBLOCKSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wSPINBLOCKSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wSPINBLOCKSoap = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wSPINBLOCKSoap != null)
    ((javax.xml.rpc.Stub)wSPINBLOCKSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public _11._11._7._10.WSPINBLOCKSoap getWSPINBLOCKSoap() {
  if (wSPINBLOCKSoap == null)
    _initWSPINBLOCKSoapProxy();
  return wSPINBLOCKSoap;
}

public java.lang.String operacionesPINBLOCKAhorros(java.lang.String wcOperacion, java.lang.String nroTarjeta, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException{
  if (wSPINBLOCKSoap == null)
    _initWSPINBLOCKSoapProxy();
  return wSPINBLOCKSoap.operacionesPINBLOCKAhorros(wcOperacion, nroTarjeta, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf);
}

public java.lang.String operacionesPINBLOCKCtasCtes(java.lang.String wcOperacion, java.lang.String wTipoDocu, java.lang.String wNroDocu, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException{
  if (wSPINBLOCKSoap == null)
    _initWSPINBLOCKSoapProxy();
  return wSPINBLOCKSoap.operacionesPINBLOCKCtasCtes(wcOperacion, wTipoDocu, wNroDocu, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf);
}


}