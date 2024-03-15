/**
 * WSDatosCuentaSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package _11._11._7._10;

public class WSDatosCuentaSoapStub extends com.ibm.ws.webservices.engine.client.Stub implements _11._11._7._10.WSDatosCuentaSoap {
    public WSDatosCuentaSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("_11._11._7._10.WSDatosCuentaSoap")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","_11._11._7._10.WSDatosCuentaSoap");
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
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _operacionesDatosCuentaOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getoperacionesDatosCuentaOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "wNroCuenta"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "OperacionesDatosCuentaResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _operacionesDatosCuentaOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("operacionesDatosCuenta", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "OperacionesDatosCuenta"), _params0, _returnDesc0, _faults0, "http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta/OperacionesDatosCuenta");
        _operacionesDatosCuentaOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "WSDatosCuentaSoap"));
        _operacionesDatosCuentaOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "OperacionesDatosCuentaSoapOut"));
        _operacionesDatosCuentaOperation0.setOption("ResponseLocalPart","OperacionesDatosCuentaResponse");
        _operacionesDatosCuentaOperation0.setOption("targetNamespace","http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta");
        _operacionesDatosCuentaOperation0.setOption("ResponseNamespace","http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta");
        _operacionesDatosCuentaOperation0.setOption("buildNum","cf222226.01");
        _operacionesDatosCuentaOperation0.setOption("inoutOrderingReq","false");
        _operacionesDatosCuentaOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "WSDatosCuenta"));
        _operacionesDatosCuentaOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta", "OperacionesDatosCuentaSoapIn"));
        _operacionesDatosCuentaOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _operacionesDatosCuentaOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _operacionesDatosCuentaOperation0;

    }

    private int _operacionesDatosCuentaIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getoperacionesDatosCuentaInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_operacionesDatosCuentaIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(WSDatosCuentaSoapStub._operacionesDatosCuentaOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("http://10.7.11.11/WSDesaDatosCuenta/WSDatosCuenta/OperacionesDatosCuenta");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_operacionesDatosCuentaIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String operacionesDatosCuenta(java.lang.String wNroCuenta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getoperacionesDatosCuentaInvoke0(new java.lang.Object[] {wNroCuenta}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static void _staticInit() {
        _operacionesDatosCuentaOperation0 = _getoperacionesDatosCuentaOperation0();
    }

    static {
       _staticInit();
    }
}
