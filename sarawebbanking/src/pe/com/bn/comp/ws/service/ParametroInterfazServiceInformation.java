/**
 * ParametroInterfazServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.service;

public class ParametroInterfazServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("datoParametroService", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc datoParametroService0Op = _datoParametroService0Op();
        list0.add(datoParametroService0Op);

        operationDescriptions.put("ParametroInterfaz",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _datoParametroService0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc datoParametroService0Op = null;
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
        datoParametroService0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("datoParametroService", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "datoParametroService"), _params0, _returnDesc0, _faults0, null);
        datoParametroService0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ParametroInterfaz"));
        datoParametroService0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "datoParametroServiceResponse"));
        datoParametroService0Op.setOption("ResponseLocalPart","datoParametroServiceResponse");
        datoParametroService0Op.setOption("targetNamespace","http://service.ws.comp.bn.com.pe");
        datoParametroService0Op.setOption("outputName","datoParametroServiceResponse");
        datoParametroService0Op.setOption("ResponseNamespace","http://service.ws.comp.bn.com.pe");
        datoParametroService0Op.setOption("buildNum","cf222226.01");
        datoParametroService0Op.setOption("usingAddressing","false");
        datoParametroService0Op.setOption("inoutOrderingReq","false");
        datoParametroService0Op.setOption("inputName","datoParametroServiceRequest");
        datoParametroService0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "ParametroInterfazService"));
        datoParametroService0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.ws.comp.bn.com.pe", "datoParametroServiceRequest"));
        datoParametroService0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return datoParametroService0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "SistemaParametro"),
                         pe.com.bn.comp.ws.bean.SistemaParametro.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "ArrayOfGrupoParametro"),
                         pe.com.bn.comp.ws.bean.GrupoParametro[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "ReturnProceso"),
                         pe.com.bn.comp.ws.bean.ReturnProceso.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "GrupoParametro"),
                         pe.com.bn.comp.ws.bean.GrupoParametro.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "ArrayOfParametro"),
                         pe.com.bn.comp.ws.bean.Parametro[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "Parametro"),
                         pe.com.bn.comp.ws.bean.Parametro.class);

        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
