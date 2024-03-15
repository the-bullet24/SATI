/**
 * ServicioBNInteroperabilidadServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public class ServicioBNInteroperabilidadServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, pe.com.bn.wscn.ws.ServicioBNInteroperabilidadService {

    public ServicioBNInteroperabilidadServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://ws.wscn.bn.com.pe",
           "ServicioBNInteroperabilidadService"));

        context.setLocatorName("pe.com.bn.wscn.ws.ServicioBNInteroperabilidadServiceLocator");
    }

    public ServicioBNInteroperabilidadServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("pe.com.bn.wscn.ws.ServicioBNInteroperabilidadServiceLocator");
    }

    // Utilizar para obtener la clase de proxy para servicioBNInteroperabilidad
    private final java.lang.String servicioBNInteroperabilidad_address = "http://10.7.12.129:9080/WSBNConfiguracion/services/ServicioBNInteroperabilidad";

    public java.lang.String getServicioBNInteroperabilidadAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return servicioBNInteroperabilidad_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ServicioBNInteroperabilidad");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return servicioBNInteroperabilidad_address;
        }
    }

    private java.lang.String servicioBNInteroperabilidadPortName = "ServicioBNInteroperabilidad";

    // The WSDD port name defaults to the port name.
    private java.lang.String servicioBNInteroperabilidadWSDDPortName = "ServicioBNInteroperabilidad";

    public java.lang.String getServicioBNInteroperabilidadWSDDPortName() {
        return servicioBNInteroperabilidadWSDDPortName;
    }

    public void setServicioBNInteroperabilidadWSDDPortName(java.lang.String name) {
        servicioBNInteroperabilidadWSDDPortName = name;
    }

    public pe.com.bn.wscn.ws.ServicioBNInteroperabilidad getServicioBNInteroperabilidad() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getServicioBNInteroperabilidadAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // es poco probable ya que URL se ha validado en WSDL2Java
        }
        return getServicioBNInteroperabilidad(endpoint);
    }

    public pe.com.bn.wscn.ws.ServicioBNInteroperabilidad getServicioBNInteroperabilidad(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        pe.com.bn.wscn.ws.ServicioBNInteroperabilidad _stub =
            (pe.com.bn.wscn.ws.ServicioBNInteroperabilidad) getStub(
                servicioBNInteroperabilidadPortName,
                (String) getPort2NamespaceMap().get(servicioBNInteroperabilidadPortName),
                pe.com.bn.wscn.ws.ServicioBNInteroperabilidad.class,
                "pe.com.bn.wscn.ws.ServicioBNInteroperabilidadSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(servicioBNInteroperabilidadWSDDPortName);
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
            if (pe.com.bn.wscn.ws.ServicioBNInteroperabilidad.class.isAssignableFrom(serviceEndpointInterface)) {
                return getServicioBNInteroperabilidad();
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
        if ("ServicioBNInteroperabilidad".equals(inputPortName)) {
            return getServicioBNInteroperabilidad();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        servicioBNInteroperabilidadWSDDPortName = prefix + "/" + servicioBNInteroperabilidadPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ServicioBNInteroperabilidad",
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
        if  (portName.getLocalPart().equals("ServicioBNInteroperabilidad")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "opEliminarCliente", "opEliminarClienteRequest"),
                createCall(portName, "opActualizarCliente", "opActualizarClienteRequest"),
                createCall(portName, "opConsultaAfilicion", "opConsultaAfilicionRequest"),
                createCall(portName, "opRegistroContacto", "opRegistroContactoRequest"),
                createCall(portName, "opBarridoClientes", "opBarridoClientesRequest"),
                createCall(portName, "opObtenerMotivDesaInteroperabilidad", "opObtenerMotivDesaInteroperabilidadRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName no debe ser nulo.");
        }
    }
}
