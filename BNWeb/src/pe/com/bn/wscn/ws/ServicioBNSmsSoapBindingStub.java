/**
 * ServicioBNSmsSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public class ServicioBNSmsSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements pe.com.bn.wscn.ws.ServicioBNSms {
    public ServicioBNSmsSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[2];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("pe.com.bn.wscn.ws.ServicioBNSmsSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","pe.com.bn.wscn.ws.ServicioBNSmsSoapBinding");
        }
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.configuracion.DataSms.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "DataSms");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.configuracion.DataValidacion.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "DataValidacion");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opGenerarSmsAfiliacionOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopGenerarSmsAfiliacionOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionRequest"), pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","GeneraSmsAfiliacionRequest");
        _params0[0].setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}GeneraSmsAfiliacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opGenerarSmsAfiliacionReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionResponse"), pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","GeneraSmsAfiliacionResponse");
        _returnDesc0.setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}GeneraSmsAfiliacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opGenerarSmsAfiliacionOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("opGenerarSmsAfiliacion", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opGenerarSmsAfiliacion"), _params0, _returnDesc0, _faults0, "opGenerarSmsAfiliacion");
        _opGenerarSmsAfiliacionOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSms"));
        _opGenerarSmsAfiliacionOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opGenerarSmsAfiliacionResponse"));
        _opGenerarSmsAfiliacionOperation0.setOption("ResponseLocalPart","opGenerarSmsAfiliacionResponse");
        _opGenerarSmsAfiliacionOperation0.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opGenerarSmsAfiliacionOperation0.setOption("outputName","opGenerarSmsAfiliacionResponse");
        _opGenerarSmsAfiliacionOperation0.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opGenerarSmsAfiliacionOperation0.setOption("buildNum","cf222226.01");
        _opGenerarSmsAfiliacionOperation0.setOption("usingAddressing","false");
        _opGenerarSmsAfiliacionOperation0.setOption("inoutOrderingReq","false");
        _opGenerarSmsAfiliacionOperation0.setOption("inputName","opGenerarSmsAfiliacionRequest");
        _opGenerarSmsAfiliacionOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSmsService"));
        _opGenerarSmsAfiliacionOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opGenerarSmsAfiliacionRequest"));
        _opGenerarSmsAfiliacionOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opGenerarSmsAfiliacionOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opGenerarSmsAfiliacionOperation0;

    }

    private int _opGenerarSmsAfiliacionIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopGenerarSmsAfiliacionInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opGenerarSmsAfiliacionIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNSmsSoapBindingStub._opGenerarSmsAfiliacionOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opGenerarSmsAfiliacion");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opGenerarSmsAfiliacionIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse opGenerarSmsAfiliacion(pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopGenerarSmsAfiliacionInvoke0(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opValidarSMSAfliacionOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopValidarSMSAfliacionOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionRequest"), pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest.class, false, false, false, false, true, false), 
          };
        _params1[0].setOption("partName","ValidaSmsAfiliacionRequest");
        _params1[0].setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}ValidaSmsAfiliacionRequest");
        _params1[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opValidarSMSAfliacionReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionResponse"), pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse.class, true, false, false, false, true, false); 
        _returnDesc1.setOption("partName","ValidaSmsAfiliacionResponse");
        _returnDesc1.setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}ValidaSmsAfiliacionResponse");
        _returnDesc1.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opValidarSMSAfliacionOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("opValidarSMSAfliacion", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opValidarSMSAfliacion"), _params1, _returnDesc1, _faults1, "opValidarSMSAfliacion");
        _opValidarSMSAfliacionOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSms"));
        _opValidarSMSAfliacionOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opValidarSMSAfliacionResponse"));
        _opValidarSMSAfliacionOperation1.setOption("ResponseLocalPart","opValidarSMSAfliacionResponse");
        _opValidarSMSAfliacionOperation1.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opValidarSMSAfliacionOperation1.setOption("outputName","opValidarSMSAfliacionResponse");
        _opValidarSMSAfliacionOperation1.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opValidarSMSAfliacionOperation1.setOption("buildNum","cf222226.01");
        _opValidarSMSAfliacionOperation1.setOption("usingAddressing","false");
        _opValidarSMSAfliacionOperation1.setOption("inoutOrderingReq","false");
        _opValidarSMSAfliacionOperation1.setOption("inputName","opValidarSMSAfliacionRequest");
        _opValidarSMSAfliacionOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSmsService"));
        _opValidarSMSAfliacionOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opValidarSMSAfliacionRequest"));
        _opValidarSMSAfliacionOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opValidarSMSAfliacionOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opValidarSMSAfliacionOperation1;

    }

    private int _opValidarSMSAfliacionIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopValidarSMSAfliacionInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opValidarSMSAfliacionIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNSmsSoapBindingStub._opValidarSMSAfliacionOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opValidarSMSAfliacion");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opValidarSMSAfliacionIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse opValidarSMSAfliacion(pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopValidarSMSAfliacionInvoke1(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse.class);
        }
    }

    private static void _staticInit() {
        _opGenerarSmsAfiliacionOperation0 = _getopGenerarSmsAfiliacionOperation0();
        _opValidarSMSAfliacionOperation1 = _getopValidarSMSAfliacionOperation1();
    }

    static {
       _staticInit();
    }
}
