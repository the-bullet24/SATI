package org.tempuri;

public class PinblockServiceSoapProxy implements org.tempuri.PinblockServiceSoap {
  private String _endpoint = null;
  private org.tempuri.PinblockServiceSoap pinblockServiceSoap = null;
  
  public PinblockServiceSoapProxy() {
    _initPinblockServiceSoapProxy();
  }
  
  public PinblockServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initPinblockServiceSoapProxy();
  }
  
  private void _initPinblockServiceSoapProxy() {
    try {
      pinblockServiceSoap = (new org.tempuri.PinblockServiceLocator()).getPinblockServiceSoap();
      if (pinblockServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pinblockServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pinblockServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (pinblockServiceSoap != null)
      ((javax.xml.rpc.Stub)pinblockServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.PinblockServiceSoap getPinblockServiceSoap() {
    if (pinblockServiceSoap == null)
      _initPinblockServiceSoapProxy();
    return pinblockServiceSoap;
  }
  
  public org.tempuri.GenerateResponse generate(org.tempuri.GenerateRequest request) throws java.rmi.RemoteException{
    if (pinblockServiceSoap == null)
      _initPinblockServiceSoapProxy();
    return pinblockServiceSoap.generate(request);
  }
  
  
}