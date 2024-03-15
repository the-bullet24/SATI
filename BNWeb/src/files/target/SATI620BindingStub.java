/**
 * SATI620BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package files.target;

public class SATI620BindingStub extends com.ibm.ws.webservices.engine.client.Stub implements files.target.SATI620PortType {
    public SATI620BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", ">TCOMMAREA>trama");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.BCDDTP00I.www.TCOMMAREA.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", "TCOMMAREA");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.Short.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", ">TCOMMAREA>codres");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "short");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = short.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", ">TCOMMAREA>codres");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "short");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", ">TCOMMAREA>trama");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.BCDDTP00O.www.TCOMMAREA.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", "TCOMMAREA");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _SATI620OperationOperation0;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", "TCOMMAREA"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", "TCOMMAREA"), com.BCDDTP00I.www.TCOMMAREA.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", "TCOMMAREA"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", "TCOMMAREA"), com.BCDDTP00O.www.TCOMMAREA.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _SATI620OperationOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("SATI620Operation", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SATI620Operation"), _params0, _returnDesc0, _faults0, "urn:SATI620");
        if (_SATI620OperationOperation0 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("targetNamespace","file://target.files");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("file://target.files", "SATI620PortType"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("file://target.files", "SATI620OperationResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("file://target.files", "SATI620OperationRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("outputName","SATI620OperationResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("inputName","SATI620OperationRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_SATI620OperationOperation0).setOption("buildNum","o0444.10");
        }
        _SATI620OperationOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _SATI620OperationOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.DOCUMENT);
    }

    private int _SATI620OperationIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getSATI620OperationInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_SATI620OperationIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(SATI620BindingStub._SATI620OperationOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("urn:SATI620");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_SATI620OperationIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public com.BCDDTP00O.www.TCOMMAREA SATI620Operation(com.BCDDTP00I.www.TCOMMAREA TCOMMAREAPart) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getSATI620OperationInvoke0(new java.lang.Object[] {TCOMMAREAPart}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (com.BCDDTP00O.www.TCOMMAREA) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (com.BCDDTP00O.www.TCOMMAREA) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), com.BCDDTP00O.www.TCOMMAREA.class);
        }
    }

}
