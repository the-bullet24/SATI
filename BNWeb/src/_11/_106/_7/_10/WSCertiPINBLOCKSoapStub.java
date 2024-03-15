/**
 * WSCertiPINBLOCKSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package _11._106._7._10;

public class WSCertiPINBLOCKSoapStub extends com.ibm.ws.webservices.engine.client.Stub implements _11._106._7._10.WSCertiPINBLOCKSoap {
    public WSCertiPINBLOCKSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[2];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("_11._106._7._10.WSCertiPINBLOCKSoap")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","_11._106._7._10.WSCertiPINBLOCKSoap");
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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _operacionesPINBLOCKAhorrosOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getoperacionesPINBLOCKAhorrosOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wcOperacion"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "NroTarjeta"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wPINEncrypt"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wPINBLOCKNew"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wPINBLOCKConf"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        _params0[2].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("inputPosition","2");
        _params0[3].setOption("partName","string");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("inputPosition","3");
        _params0[4].setOption("partName","string");
        _params0[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("inputPosition","4");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKAhorrosResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _operacionesPINBLOCKAhorrosOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("operacionesPINBLOCKAhorros", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKAhorros"), _params0, _returnDesc0, _faults0, "http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK/OperacionesPINBLOCKAhorros");
        _operacionesPINBLOCKAhorrosOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "WSCertiPINBLOCKSoap"));
        _operacionesPINBLOCKAhorrosOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKAhorrosSoapOut"));
        _operacionesPINBLOCKAhorrosOperation0.setOption("ResponseLocalPart","OperacionesPINBLOCKAhorrosResponse");
        _operacionesPINBLOCKAhorrosOperation0.setOption("targetNamespace","http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK");
        _operacionesPINBLOCKAhorrosOperation0.setOption("ResponseNamespace","http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK");
        _operacionesPINBLOCKAhorrosOperation0.setOption("buildNum","cf222226.01");
        _operacionesPINBLOCKAhorrosOperation0.setOption("inoutOrderingReq","false");
        _operacionesPINBLOCKAhorrosOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "WSCertiPINBLOCK"));
        _operacionesPINBLOCKAhorrosOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKAhorrosSoapIn"));
        _operacionesPINBLOCKAhorrosOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _operacionesPINBLOCKAhorrosOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _operacionesPINBLOCKAhorrosOperation0;

    }

    private int _operacionesPINBLOCKAhorrosIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getoperacionesPINBLOCKAhorrosInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_operacionesPINBLOCKAhorrosIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(WSCertiPINBLOCKSoapStub._operacionesPINBLOCKAhorrosOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK/OperacionesPINBLOCKAhorros");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_operacionesPINBLOCKAhorrosIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String operacionesPINBLOCKAhorros(java.lang.String wcOperacion, java.lang.String nroTarjeta, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getoperacionesPINBLOCKAhorrosInvoke0(new java.lang.Object[] {wcOperacion, nroTarjeta, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _operacionesPINBLOCKCtasCtesOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getoperacionesPINBLOCKCtasCtesOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wcOperacion"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wTipoDocu"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wNroDocu"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wPINEncrypt"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wPINBLOCKNew"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "wPINBLOCKConf"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params1[0].setOption("partName","string");
        _params1[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[0].setOption("inputPosition","0");
        _params1[1].setOption("partName","string");
        _params1[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[1].setOption("inputPosition","1");
        _params1[2].setOption("partName","string");
        _params1[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[2].setOption("inputPosition","2");
        _params1[3].setOption("partName","string");
        _params1[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[3].setOption("inputPosition","3");
        _params1[4].setOption("partName","string");
        _params1[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[4].setOption("inputPosition","4");
        _params1[5].setOption("partName","string");
        _params1[5].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[5].setOption("inputPosition","5");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKCtasCtesResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc1.setOption("partName","string");
        _returnDesc1.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc1.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _operacionesPINBLOCKCtasCtesOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("operacionesPINBLOCKCtasCtes", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKCtasCtes"), _params1, _returnDesc1, _faults1, "http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK/OperacionesPINBLOCKCtasCtes");
        _operacionesPINBLOCKCtasCtesOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "WSCertiPINBLOCKSoap"));
        _operacionesPINBLOCKCtasCtesOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKCtasCtesSoapOut"));
        _operacionesPINBLOCKCtasCtesOperation1.setOption("ResponseLocalPart","OperacionesPINBLOCKCtasCtesResponse");
        _operacionesPINBLOCKCtasCtesOperation1.setOption("targetNamespace","http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK");
        _operacionesPINBLOCKCtasCtesOperation1.setOption("ResponseNamespace","http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK");
        _operacionesPINBLOCKCtasCtesOperation1.setOption("buildNum","cf222226.01");
        _operacionesPINBLOCKCtasCtesOperation1.setOption("inoutOrderingReq","false");
        _operacionesPINBLOCKCtasCtesOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "WSCertiPINBLOCK"));
        _operacionesPINBLOCKCtasCtesOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK", "OperacionesPINBLOCKCtasCtesSoapIn"));
        _operacionesPINBLOCKCtasCtesOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _operacionesPINBLOCKCtasCtesOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _operacionesPINBLOCKCtasCtesOperation1;

    }

    private int _operacionesPINBLOCKCtasCtesIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getoperacionesPINBLOCKCtasCtesInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_operacionesPINBLOCKCtasCtesIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(WSCertiPINBLOCKSoapStub._operacionesPINBLOCKCtasCtesOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("http://10.7.106.11/WSCertiPINBLOCK/WSCertiPINBLOCK/OperacionesPINBLOCKCtasCtes");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_operacionesPINBLOCKCtasCtesIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String operacionesPINBLOCKCtasCtes(java.lang.String wcOperacion, java.lang.String wTipoDocu, java.lang.String wNroDocu, java.lang.String wPINEncrypt, java.lang.String wPINBLOCKNew, java.lang.String wPINBLOCKConf) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getoperacionesPINBLOCKCtasCtesInvoke1(new java.lang.Object[] {wcOperacion, wTipoDocu, wNroDocu, wPINEncrypt, wPINBLOCKNew, wPINBLOCKConf}).invoke();

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
        _operacionesPINBLOCKCtasCtesOperation1 = _getoperacionesPINBLOCKCtasCtesOperation1();
        _operacionesPINBLOCKAhorrosOperation0 = _getoperacionesPINBLOCKAhorrosOperation0();
    }

    static {
       _staticInit();
    }
}
