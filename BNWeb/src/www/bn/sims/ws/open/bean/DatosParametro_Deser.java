/**
 * DatosParametro_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class DatosParametro_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public DatosParametro_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new DatosParametro();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_3_40) {
          ((DatosParametro)value).setParametro1(strValue);
          return true;}
        else if (qName==QName_3_41) {
          ((DatosParametro)value).setParametro10(strValue);
          return true;}
        else if (qName==QName_3_42) {
          ((DatosParametro)value).setParametro11(strValue);
          return true;}
        else if (qName==QName_3_43) {
          ((DatosParametro)value).setParametro12(strValue);
          return true;}
        else if (qName==QName_3_44) {
          ((DatosParametro)value).setParametro13(strValue);
          return true;}
        else if (qName==QName_3_45) {
          ((DatosParametro)value).setParametro14(strValue);
          return true;}
        else if (qName==QName_3_46) {
          ((DatosParametro)value).setParametro15(strValue);
          return true;}
        else if (qName==QName_3_47) {
          ((DatosParametro)value).setParametro16(strValue);
          return true;}
        else if (qName==QName_3_48) {
          ((DatosParametro)value).setParametro17(strValue);
          return true;}
        else if (qName==QName_3_49) {
          ((DatosParametro)value).setParametro18(strValue);
          return true;}
        else if (qName==QName_3_50) {
          ((DatosParametro)value).setParametro19(strValue);
          return true;}
        else if (qName==QName_3_51) {
          ((DatosParametro)value).setParametro2(strValue);
          return true;}
        else if (qName==QName_3_52) {
          ((DatosParametro)value).setParametro20(strValue);
          return true;}
        else if (qName==QName_3_53) {
          ((DatosParametro)value).setParametro3(strValue);
          return true;}
        else if (qName==QName_3_54) {
          ((DatosParametro)value).setParametro4(strValue);
          return true;}
        else if (qName==QName_3_55) {
          ((DatosParametro)value).setParametro5(strValue);
          return true;}
        else if (qName==QName_3_56) {
          ((DatosParametro)value).setParametro6(strValue);
          return true;}
        else if (qName==QName_3_57) {
          ((DatosParametro)value).setParametro7(strValue);
          return true;}
        else if (qName==QName_3_58) {
          ((DatosParametro)value).setParametro8(strValue);
          return true;}
        else if (qName==QName_3_59) {
          ((DatosParametro)value).setParametro9(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_3_53 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro3");
    private final static javax.xml.namespace.QName QName_3_51 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro2");
    private final static javax.xml.namespace.QName QName_3_40 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro1");
    private final static javax.xml.namespace.QName QName_3_50 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro19");
    private final static javax.xml.namespace.QName QName_3_52 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro20");
    private final static javax.xml.namespace.QName QName_3_49 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro18");
    private final static javax.xml.namespace.QName QName_3_48 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro17");
    private final static javax.xml.namespace.QName QName_3_47 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro16");
    private final static javax.xml.namespace.QName QName_3_46 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro15");
    private final static javax.xml.namespace.QName QName_3_45 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro14");
    private final static javax.xml.namespace.QName QName_3_44 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro13");
    private final static javax.xml.namespace.QName QName_3_43 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro12");
    private final static javax.xml.namespace.QName QName_3_42 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro11");
    private final static javax.xml.namespace.QName QName_3_41 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro10");
    private final static javax.xml.namespace.QName QName_3_59 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro9");
    private final static javax.xml.namespace.QName QName_3_58 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro8");
    private final static javax.xml.namespace.QName QName_3_57 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro7");
    private final static javax.xml.namespace.QName QName_3_56 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro6");
    private final static javax.xml.namespace.QName QName_3_55 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro5");
    private final static javax.xml.namespace.QName QName_3_54 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "parametro4");
}
