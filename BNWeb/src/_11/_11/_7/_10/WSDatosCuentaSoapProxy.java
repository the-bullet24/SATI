package _11._11._7._10;

public class WSDatosCuentaSoapProxy implements _11._11._7._10.WSDatosCuentaSoap {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private _11._11._7._10.WSDatosCuentaSoap wSDatosCuentaSoap = null;
  
  public WSDatosCuentaSoapProxy() {
    _initWSDatosCuentaSoapProxy();
  }
  
  private void _initWSDatosCuentaSoapProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wSDatosCuentaSoap = ((_11._11._7._10.WSDatosCuenta)ctx.lookup("java:comp/env/service/WSDatosCuenta")).getWSDatosCuentaSoap();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSDatosCuentaSoap == null) {
    try{
      wSDatosCuentaSoap = (new _11._11._7._10.WSDatosCuentaLocator()).getWSDatosCuentaSoap();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wSDatosCuentaSoap != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wSDatosCuentaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wSDatosCuentaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wSDatosCuentaSoap = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wSDatosCuentaSoap != null)
    ((javax.xml.rpc.Stub)wSDatosCuentaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public _11._11._7._10.WSDatosCuentaSoap getWSDatosCuentaSoap() {
  if (wSDatosCuentaSoap == null)
    _initWSDatosCuentaSoapProxy();
  return wSDatosCuentaSoap;
}

public java.lang.String operacionesDatosCuenta(java.lang.String wNroCuenta) throws java.rmi.RemoteException{
  if (wSDatosCuentaSoap == null)
    _initWSDatosCuentaSoapProxy();
  return wSDatosCuentaSoap.operacionesDatosCuenta(wNroCuenta);
}


}