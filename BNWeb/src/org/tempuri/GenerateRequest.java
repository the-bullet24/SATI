/**
 * GenerateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class GenerateRequest  implements java.io.Serializable {
    private java.lang.String cardNumber;

    private java.lang.String pinNew;

    private java.lang.String pinConfirm;

    public GenerateRequest() {
    }

    public GenerateRequest(
           java.lang.String cardNumber,
           java.lang.String pinNew,
           java.lang.String pinConfirm) {
           this.cardNumber = cardNumber;
           this.pinNew = pinNew;
           this.pinConfirm = pinConfirm;
    }


    /**
     * Gets the cardNumber value for this GenerateRequest.
     * 
     * @return cardNumber
     */
    public java.lang.String getCardNumber() {
        return cardNumber;
    }


    /**
     * Sets the cardNumber value for this GenerateRequest.
     * 
     * @param cardNumber
     */
    public void setCardNumber(java.lang.String cardNumber) {
        this.cardNumber = cardNumber;
    }


    /**
     * Gets the pinNew value for this GenerateRequest.
     * 
     * @return pinNew
     */
    public java.lang.String getPinNew() {
        return pinNew;
    }


    /**
     * Sets the pinNew value for this GenerateRequest.
     * 
     * @param pinNew
     */
    public void setPinNew(java.lang.String pinNew) {
        this.pinNew = pinNew;
    }


    /**
     * Gets the pinConfirm value for this GenerateRequest.
     * 
     * @return pinConfirm
     */
    public java.lang.String getPinConfirm() {
        return pinConfirm;
    }


    /**
     * Sets the pinConfirm value for this GenerateRequest.
     * 
     * @param pinConfirm
     */
    public void setPinConfirm(java.lang.String pinConfirm) {
        this.pinConfirm = pinConfirm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerateRequest)) return false;
        GenerateRequest other = (GenerateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cardNumber==null && other.getCardNumber()==null) || 
             (this.cardNumber!=null &&
              this.cardNumber.equals(other.getCardNumber()))) &&
            ((this.pinNew==null && other.getPinNew()==null) || 
             (this.pinNew!=null &&
              this.pinNew.equals(other.getPinNew()))) &&
            ((this.pinConfirm==null && other.getPinConfirm()==null) || 
             (this.pinConfirm!=null &&
              this.pinConfirm.equals(other.getPinConfirm())));
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
        if (getCardNumber() != null) {
            _hashCode += getCardNumber().hashCode();
        }
        if (getPinNew() != null) {
            _hashCode += getPinNew().hashCode();
        }
        if (getPinConfirm() != null) {
            _hashCode += getPinConfirm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "GenerateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "cardNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pinNew");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "pinNew"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pinConfirm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "pinConfirm"));
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
