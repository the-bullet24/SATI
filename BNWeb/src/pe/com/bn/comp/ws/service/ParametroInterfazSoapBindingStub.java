/**
 * ParametroInterfazSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.service;

public class ParametroInterfazSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements pe.com.bn.comp.ws.service.ParametroInterfaz {
    public ParametroInterfazSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("pe.com.bn.comp.ws.service.ParametroInterfazSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","pe.com.bn.comp.ws.service.ParametroInterfazSoapBinding");
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
        javaType = pe.com.bn.comp.ws.bean.SistemaParametro.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "SistemaParametro");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.comp.ws.bean.Parametro[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ArrayOf_tns2_nillable_Parametro");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Parametro");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "Parametro");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.comp.ws.bean.Parametro.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "Parametro");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.comp.ws.bean.GrupoParametro[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ArrayOf_tns2_nillable_GrupoParametro");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "GrupoParametro");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "GrupoParametro");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.comp.ws.bean.GrupoParametro.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "GrupoParametro");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.comp.ws.bean.ReturnProceso.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "ReturnProceso");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _datoParametroServiceOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getdatoParametroServiceOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "sistema"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "cuenta"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "clave"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "idUsuario"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        _params0[2].setOption("partName","base64Binary");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}base64Binary");
        _params0[2].setOption("inputPosition","2");
        _params0[3].setOption("partName","string");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("inputPosition","3");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "datoParametroServiceReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "SistemaParametro"), pe.com.bn.comp.ws.bean.SistemaParametro.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","SistemaParametro");
        _returnDesc0.setOption("partQNameString","{http://bean.ws.comp.bn.com.pe}SistemaParametro");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _datoParametroServiceOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("datoParametroService", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "datoParametroService"), _params0, _returnDesc0, _faults0, "datoParametroService");
        _datoParametroServiceOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ParametroInterfaz"));
        _datoParametroServiceOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "datoParametroServiceResponse"));
        _datoParametroServiceOperation0.setOption("ResponseLocalPart","datoParametroServiceResponse");
        _datoParametroServiceOperation0.setOption("targetNamespace","http://service.ws.comp.bn.com.pe");
        _datoParametroServiceOperation0.setOption("outputName","datoParametroServiceResponse");
        _datoParametroServiceOperation0.setOption("ResponseNamespace","http://service.ws.comp.bn.com.pe");
        _datoParametroServiceOperation0.setOption("buildNum","cf222226.01");
        _datoParametroServiceOperation0.setOption("usingAddressing","false");
        _datoParametroServiceOperation0.setOption("inoutOrderingReq","false");
        _datoParametroServiceOperation0.setOption("inputName","datoParametroServiceRequest");
        _datoParametroServiceOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ParametroInterfazService"));
        _datoParametroServiceOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "datoParametroServiceRequest"));
        _datoParametroServiceOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _datoParametroServiceOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _datoParametroServiceOperation0;

    }

    private int _datoParametroServiceIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getdatoParametroServiceInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_datoParametroServiceIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ParametroInterfazSoapBindingStub._datoParametroServiceOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("datoParametroService");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_datoParametroServiceIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.comp.ws.bean.SistemaParametro datoParametroService(java.lang.String sistema, java.lang.String cuenta, byte[] clave, java.lang.String idUsuario) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getdatoParametroServiceInvoke0(new java.lang.Object[] {sistema, cuenta, clave, idUsuario}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.comp.ws.bean.SistemaParametro) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.comp.ws.bean.SistemaParametro) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.comp.ws.bean.SistemaParametro.class);
        }
    }

    private static void _staticInit() {
        _datoParametroServiceOperation0 = _getdatoParametroServiceOperation0();
    }

    static {
       _staticInit();
    }
}
