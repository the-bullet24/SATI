package _211._50._1._10;

public class WSPRDPINBLOCKSoapProxy implements _211._50._1._10.WSPRDPINBLOCKSoap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private _211._50._1._10.WSPRDPINBLOCKSoap wSPRDPINBLOCKSoap = null;
  
  public WSPRDPINBLOCKSoapProxy() {
    _initWSPRDPINBLOCKSoapProxy();
  }
  
  private void _initWSPRDPINBLOCKSoapProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wSPRDPINBLOCKSoap = ((_211._50._1._10.WSPRDPINBLOCK)ctx.lookup("java:comp/env/service/WSPRDPINBLOCK")).getWSPRDPINBLOCKSoap();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSPRDPINBLOCKSoap == null) {
    try{
      wSPRDPINBLOCKSoap = (new _211._50._1._10.WSPRDPINBLOCKLocator()).getWSPRDPINBLOCKSoap();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSPRDPINBLOCKSoap != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wSPRDPINBLOCKSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wSPRDPINBLOCKSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wSPRDPINBLOCKSoap = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wSPRDPINBLOCKSoap != null)
    ((javax.xml.rpc.Stub)wSPRDPINBLOCKSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public _211._50._1._10.WSPRDPINBLOCKSoap getWSPRDPINBLOCKSoap() {
  if (wSPRDPINBLOCKSoap == null)
    _initWSPRDPINBLOCKSoapProxy();
  return wSPRDPINBLOCKSoap;
}

public java.lang.String operacionesPINBLOCKAhorros(java.lang.String wcOperacion, java.lang.String nroTarjeta, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException{
  if (wSPRDPINBLOCKSoap == null)
    _initWSPRDPINBLOCKSoapProxy();
  return wSPRDPINBLOCKSoap.operacionesPINBLOCKAhorros(wcOperacion, nroTarjeta, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf);
}

public java.lang.String operacionesPINBLOCKCtasCtes(java.lang.String wcOperacion, java.lang.String wTipoDocu, java.lang.String wNroDocu, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException{
  if (wSPRDPINBLOCKSoap == null)
    _initWSPRDPINBLOCKSoapProxy();
  return wSPRDPINBLOCKSoap.operacionesPINBLOCKCtasCtes(wcOperacion, wTipoDocu, wNroDocu, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf);
}


}