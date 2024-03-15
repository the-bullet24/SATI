/**
 * ServicioNotificacionSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.com.bn.wsen.envio.service;

public class ServicioNotificacionSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements pe.com.bn.wsen.envio.service.ServicioNotificacion {
    public ServicioNotificacionSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[3];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("pe.com.bn.wsen.envio.service.ServicioNotificacionSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","pe.com.bn.wsen.envio.service.ServicioNotificacionSoapBinding");
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
        javaType = pe.com.bn.wsen.envio.request.EnvioNotificacionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "EnvioNotificacionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wsen.envio.request.AcuseReciboRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "AcuseReciboRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wsen.envio.response.EnvioNotificacionResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "EnvioNotificacionResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wsen.envio.response.AcuseReciboResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "AcuseReciboResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wsen.envio.response.ImagenResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "ImagenResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opEnviarNotificacionDocOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopEnviarNotificacionDocOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "EnvioNotificacionRequest"), pe.com.bn.wsen.envio.request.EnvioNotificacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","EnvioNotificacionRequest");
        _params0[0].setOption("partQNameString","{http://request.envio.wsen.bn.com.pe}EnvioNotificacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opEnviarNotificacionDocReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "EnvioNotificacionResponse"), pe.com.bn.wsen.envio.response.EnvioNotificacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","EnvioNotificacionResponse");
        _returnDesc0.setOption("partQNameString","{http://response.envio.wsen.bn.com.pe}EnvioNotificacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opEnviarNotificacionDocOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("opEnviarNotificacionDoc", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opEnviarNotificacionDoc"), _params0, _returnDesc0, _faults0, "opEnviarNotificacionDoc");
        _opEnviarNotificacionDocOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacion"));
        _opEnviarNotificacionDocOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opEnviarNotificacionDocResponse"));
        _opEnviarNotificacionDocOperation0.setOption("ResponseLocalPart","opEnviarNotificacionDocResponse");
        _opEnviarNotificacionDocOperation0.setOption("targetNamespace","http://service.envio.wsen.bn.com.pe");
        _opEnviarNotificacionDocOperation0.setOption("outputName","opEnviarNotificacionDocResponse");
        _opEnviarNotificacionDocOperation0.setOption("ResponseNamespace","http://service.envio.wsen.bn.com.pe");
        _opEnviarNotificacionDocOperation0.setOption("buildNum","cf192102.03");
        _opEnviarNotificacionDocOperation0.setOption("usingAddressing","false");
        _opEnviarNotificacionDocOperation0.setOption("inoutOrderingReq","false");
        _opEnviarNotificacionDocOperation0.setOption("inputName","opEnviarNotificacionDocRequest");
        _opEnviarNotificacionDocOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService"));
        _opEnviarNotificacionDocOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opEnviarNotificacionDocRequest"));
        _opEnviarNotificacionDocOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opEnviarNotificacionDocOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opEnviarNotificacionDocOperation0;

    }

    private int _opEnviarNotificacionDocIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopEnviarNotificacionDocInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opEnviarNotificacionDocIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioNotificacionSoapBindingStub._opEnviarNotificacionDocOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opEnviarNotificacionDoc");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opEnviarNotificacionDocIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wsen.envio.response.EnvioNotificacionResponse opEnviarNotificacionDoc(pe.com.bn.wsen.envio.request.EnvioNotificacionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopEnviarNotificacionDocInvoke0(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wsen.envio.response.EnvioNotificacionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wsen.envio.response.EnvioNotificacionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wsen.envio.response.EnvioNotificacionResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opAcuseReciboOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopAcuseReciboOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "AcuseReciboRequest"), pe.com.bn.wsen.envio.request.AcuseReciboRequest.class, false, false, false, false, true, false), 
          };
        _params1[0].setOption("partName","AcuseReciboRequest");
        _params1[0].setOption("partQNameString","{http://request.envio.wsen.bn.com.pe}AcuseReciboRequest");
        _params1[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opAcuseReciboReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "AcuseReciboResponse"), pe.com.bn.wsen.envio.response.AcuseReciboResponse.class, true, false, false, false, true, false); 
        _returnDesc1.setOption("partName","AcuseReciboResponse");
        _returnDesc1.setOption("partQNameString","{http://response.envio.wsen.bn.com.pe}AcuseReciboResponse");
        _returnDesc1.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opAcuseReciboOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("opAcuseRecibo", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opAcuseRecibo"), _params1, _returnDesc1, _faults1, "opAcuseRecibo");
        _opAcuseReciboOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacion"));
        _opAcuseReciboOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opAcuseReciboResponse"));
        _opAcuseReciboOperation1.setOption("ResponseLocalPart","opAcuseReciboResponse");
        _opAcuseReciboOperation1.setOption("targetNamespace","http://service.envio.wsen.bn.com.pe");
        _opAcuseReciboOperation1.setOption("outputName","opAcuseReciboResponse");
        _opAcuseReciboOperation1.setOption("ResponseNamespace","http://service.envio.wsen.bn.com.pe");
        _opAcuseReciboOperation1.setOption("buildNum","cf192102.03");
        _opAcuseReciboOperation1.setOption("usingAddressing","false");
        _opAcuseReciboOperation1.setOption("inoutOrderingReq","false");
        _opAcuseReciboOperation1.setOption("inputName","opAcuseReciboRequest");
        _opAcuseReciboOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService"));
        _opAcuseReciboOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opAcuseReciboRequest"));
        _opAcuseReciboOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opAcuseReciboOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opAcuseReciboOperation1;

    }

    private int _opAcuseReciboIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopAcuseReciboInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opAcuseReciboIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioNotificacionSoapBindingStub._opAcuseReciboOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opAcuseRecibo");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opAcuseReciboIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wsen.envio.response.AcuseReciboResponse opAcuseRecibo(pe.com.bn.wsen.envio.request.AcuseReciboRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopAcuseReciboInvoke1(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wsen.envio.response.AcuseReciboResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wsen.envio.response.AcuseReciboResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wsen.envio.response.AcuseReciboResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opLogoOperation2 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopLogoOperation2() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opLogoReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "ImagenResponse"), pe.com.bn.wsen.envio.response.ImagenResponse.class, true, false, false, false, true, false); 
        _returnDesc2.setOption("partName","ImagenResponse");
        _returnDesc2.setOption("partQNameString","{http://response.envio.wsen.bn.com.pe}ImagenResponse");
        _returnDesc2.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opLogoOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("opLogo", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opLogo"), _params2, _returnDesc2, _faults2, "opLogo");
        _opLogoOperation2.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacion"));
        _opLogoOperation2.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opLogoResponse"));
        _opLogoOperation2.setOption("ResponseLocalPart","opLogoResponse");
        _opLogoOperation2.setOption("targetNamespace","http://service.envio.wsen.bn.com.pe");
        _opLogoOperation2.setOption("outputName","opLogoResponse");
        _opLogoOperation2.setOption("ResponseNamespace","http://service.envio.wsen.bn.com.pe");
        _opLogoOperation2.setOption("buildNum","cf192102.03");
        _opLogoOperation2.setOption("usingAddressing","false");
        _opLogoOperation2.setOption("inputName","opLogoRequest");
        _opLogoOperation2.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService"));
        _opLogoOperation2.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opLogoRequest"));
        _opLogoOperation2.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opLogoOperation2.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opLogoOperation2;

    }

    private int _opLogoIndex2 = 2;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopLogoInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opLogoIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioNotificacionSoapBindingStub._opLogoOperation2);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opLogo");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opLogoIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wsen.envio.response.ImagenResponse opLogo() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopLogoInvoke2(new java.lang.Object[] {}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wsen.envio.response.ImagenResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wsen.envio.response.ImagenResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wsen.envio.response.ImagenResponse.class);
        }
    }

    private static void _staticInit() {
        _opLogoOperation2 = _getopLogoOperation2();
        _opAcuseReciboOperation1 = _getopAcuseReciboOperation1();
        _opEnviarNotificacionDocOperation0 = _getopEnviarNotificacionDocOperation0();
    }

    static {
       _staticInit();
    }
}
