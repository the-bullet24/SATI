/**
 * ServicioNotificacionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.com.bn.wsen.envio.service;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;

public class ServicioNotificacionServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, pe.com.bn.wsen.envio.service.ServicioNotificacionService {

    public ServicioNotificacionServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://service.envio.wsen.bn.com.pe",
           "ServicioNotificacionService"));

        context.setLocatorName("pe.com.bn.wsen.envio.service.ServicioNotificacionServiceLocator");
    }

    public ServicioNotificacionServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("pe.com.bn.wsen.envio.service.ServicioNotificacionServiceLocator");
    }

 // Utilizar para obtener la clase de proxy para servicioNotificacion
    String url = Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_NOTIFICACION);
    
    private final java.lang.String servicioNotificacion_address = url;
    
    public java.lang.String getServicioNotificacionAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return servicioNotificacion_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ServicioNotificacion");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return servicioNotificacion_address;
        }
    }

    private java.lang.String servicioNotificacionPortName = "ServicioNotificacion";

    // The WSDD port name defaults to the port name.
    private java.lang.String servicioNotificacionWSDDPortName = "ServicioNotificacion";

    public java.lang.String getServicioNotificacionWSDDPortName() {
        return servicioNotificacionWSDDPortName;
    }

    public void setServicioNotificacionWSDDPortName(java.lang.String name) {
        servicioNotificacionWSDDPortName = name;
    }

    public pe.com.bn.wsen.envio.service.ServicioNotificacion getServicioNotificacion() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getServicioNotificacionAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getServicioNotificacion(endpoint);
    }

    public pe.com.bn.wsen.envio.service.ServicioNotificacion getServicioNotificacion(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        pe.com.bn.wsen.envio.service.ServicioNotificacion _stub =
            (pe.com.bn.wsen.envio.service.ServicioNotificacion) getStub(
                servicioNotificacionPortName,
                (String) getPort2NamespaceMap().get(servicioNotificacionPortName),
                pe.com.bn.wsen.envio.service.ServicioNotificacion.class,
                "pe.com.bn.wsen.envio.service.ServicioNotificacionSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(servicioNotificacionWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (pe.com.bn.wsen.envio.service.ServicioNotificacion.class.isAssignableFrom(serviceEndpointInterface)) {
                return getServicioNotificacion();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: No hay ninguna implementación de apéndice para la interfaz:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("ServicioNotificacion".equals(inputPortName)) {
            return getServicioNotificacion();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        servicioNotificacionWSDDPortName = prefix + "/" + servicioNotificacionPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ServicioNotificacion",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
        if  (portName.getLocalPart().equals("ServicioNotificacion")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "opEnviarNotificacionDoc", "opEnviarNotificacionDocRequest"),
                createCall(portName, "opAcuseRecibo", "opAcuseReciboRequest"),
                createCall(portName, "opLogo", "opLogoRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
