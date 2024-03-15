/**
 * GenerateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class GenerateResponse  implements java.io.Serializable {
    private java.lang.String msg;

    private java.lang.String pinEncryptBlock1;

    private java.lang.String pinEncryptBlock2;

    public GenerateResponse() {
    }

    public GenerateResponse(
           java.lang.String msg,
           java.lang.String pinEncryptBlock1,
           java.lang.String pinEncryptBlock2) {
           this.msg = msg;
           this.pinEncryptBlock1 = pinEncryptBlock1;
           this.pinEncryptBlock2 = pinEncryptBlock2;
    }


    /**
     * Gets the msg value for this GenerateResponse.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this GenerateResponse.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }


    /**
     * Gets the pinEncryptBlock1 value for this GenerateResponse.
     * 
     * @return pinEncryptBlock1
     */
    public java.lang.String getPinEncryptBlock1() {
        return pinEncryptBlock1;
    }


    /**
     * Sets the pinEncryptBlock1 value for this GenerateResponse.
     * 
     * @param pinEncryptBlock1
     */
    public void setPinEncryptBlock1(java.lang.String pinEncryptBlock1) {
        this.pinEncryptBlock1 = pinEncryptBlock1;
    }


    /**
     * Gets the pinEncryptBlock2 value for this GenerateResponse.
     * 
     * @return pinEncryptBlock2
     */
    public java.lang.String getPinEncryptBlock2() {
        return pinEncryptBlock2;
    }


    /**
     * Sets the pinEncryptBlock2 value for this GenerateResponse.
     * 
     * @param pinEncryptBlock2
     */
    public void setPinEncryptBlock2(java.lang.String pinEncryptBlock2) {
        this.pinEncryptBlock2 = pinEncryptBlock2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerateResponse)) return false;
        GenerateResponse other = (GenerateResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.msg==null && other.getMsg()==null) || 
             (this.msg!=null &&
              this.msg.equals(other.getMsg()))) &&
            ((this.pinEncryptBlock1==null && other.getPinEncryptBlock1()==null) || 
             (this.pinEncryptBlock1!=null &&
              this.pinEncryptBlock1.equals(other.getPinEncryptBlock1()))) &&
            ((this.pinEncryptBlock2==null && other.getPinEncryptBlock2()==null) || 
             (this.pinEncryptBlock2!=null &&
              this.pinEncryptBlock2.equals(other.getPinEncryptBlock2())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMsg() != null) {
            _hashCode += getMsg().hashCode();
        }
        if (getPinEncryptBlock1() != null) {
            _hashCode += getPinEncryptBlock1().hashCode();
        }
        if (getPinEncryptBlock2() != null) {
            _hashCode += getPinEncryptBlock2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "GenerateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "msg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pinEncryptBlock1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "pinEncryptBlock1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pinEncryptBlock2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "pinEncryptBlock2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
