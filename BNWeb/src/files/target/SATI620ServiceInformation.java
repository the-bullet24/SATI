/**
 * SATI620ServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package files.target;

public class SATI620ServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("SATI620Operation", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc SATI620Operation0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", "TCOMMAREA"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", "TCOMMAREA"), com.BCDDTP00I.www.TCOMMAREA.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", "TCOMMAREA"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", "TCOMMAREA"), com.BCDDTP00O.www.TCOMMAREA.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        SATI620Operation0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("SATI620Operation", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SATI620Operation"), _params0, _returnDesc0, _faults0, null);
        if (SATI620Operation0Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("targetNamespace","file://target.files");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("file://target.files", "SATI620PortType"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("file://target.files", "SATI620OperationResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("file://target.files", "SATI620OperationRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("outputName","SATI620OperationResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("inputName","SATI620OperationRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)SATI620Operation0Op).setOption("buildNum","o0444.10");
        }
        SATI620Operation0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.DOCUMENT);
        list0.add(SATI620Operation0Op);

        operationDescriptions.put("SATI620Port",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", ">TCOMMAREA>trama"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", ">TCOMMAREA>codres"),
                         short.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", ">TCOMMAREA>trama"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00I.com/schemas/BCDDTP00IInterface", "TCOMMAREA"),
                         com.BCDDTP00I.www.TCOMMAREA.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface", "TCOMMAREA"),
                         com.BCDDTP00O.www.TCOMMAREA.class);

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
