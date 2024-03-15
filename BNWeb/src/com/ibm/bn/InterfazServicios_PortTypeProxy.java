package com.ibm.bn;

public class InterfazServicios_PortTypeProxy implements com.ibm.bn.InterfazServicios_PortType {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.ibm.bn.InterfazServicios_PortType interfazServicios_PortType = null;
  
  public InterfazServicios_PortTypeProxy() {
    _initInterfazServicios_PortTypeProxy();
  }
  
  private void _initInterfazServicios_PortTypeProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      interfazServicios_PortType = ((com.ibm.bn.InterfazServicios_Service)ctx.lookup("java:comp/env/service/InterfazServicios")).getInterfazServiciosSOAP();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (interfazServicios_PortType == null) {
    try{
      interfazServicios_PortType = (new com.ibm.bn.InterfazServicios_ServiceLocator()).getInterfazServiciosSOAP();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (interfazServicios_PortType != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)interfazServicios_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)interfazServicios_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  interfazServicios_PortType = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (interfazServicios_PortType != null)
    ((javax.xml.rpc.Stub)interfazServicios_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public com.ibm.bn.InterfazServicios_PortType getInterfazServicios_PortType() {
  if (interfazServicios_PortType == null)
    _initInterfazServicios_PortTypeProxy();
  return interfazServicios_PortType;
}

public void enviarTramaConsulta(java.lang.String codTrans, java.lang.String tramaConsulta, javax.xml.rpc.holders.StringHolder tramaRespuesta, javax.xml.rpc.holders.IntHolder codRes) throws java.rmi.RemoteException{
  if (interfazServicios_PortType == null)
    _initInterfazServicios_PortTypeProxy();
  interfazServicios_PortType.enviarTramaConsulta(codTrans, tramaConsulta, tramaRespuesta, codRes);
}


}