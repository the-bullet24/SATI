/**
 * GenerarMedioAutenticacionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class GenerarMedioAutenticacionRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoCliente;

    private java.lang.String codigoMedioAutenticacion;

    public GenerarMedioAutenticacionRequest() {
    }

    public GenerarMedioAutenticacionRequest(
           java.lang.String codigoAplicacionOrigen,
           java.lang.String codigoComercio,
           java.lang.String codigoEmpresa,
           java.lang.String codigoZonaIntercambio,
           java.lang.String numeroTerminal,
           java.lang.String tipoAplicacionOrigen,
           java.lang.Integer tipoTerminal,
           java.lang.String usuario,
           java.util.Calendar fechaDate,
           java.lang.Integer numeroSecuencia,
           java.lang.String binMedioAutenticacion,
           java.lang.String codigoCliente,
           java.lang.String codigoMedioAutenticacion) {
        super(
            codigoAplicacionOrigen,
            codigoComercio,
            codigoEmpresa,
            codigoZonaIntercambio,
            numeroTerminal,
            tipoAplicacionOrigen,
            tipoTerminal,
            usuario,
            fechaDate,
            numeroSecuencia);
        this.binMedioAutenticacion = binMedioAutenticacion;
        this.codigoCliente = codigoCliente;
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the binMedioAutenticacion value for this GenerarMedioAutenticacionRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this GenerarMedioAutenticacionRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoCliente value for this GenerarMedioAutenticacionRequest.
     * 
     * @return codigoCliente
     */
    public java.lang.String getCodigoCliente() {
        return codigoCliente;
    }


    /**
     * Sets the codigoCliente value for this GenerarMedioAutenticacionRequest.
     * 
     * @param codigoCliente
     */
    public void setCodigoCliente(java.lang.String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this GenerarMedioAutenticacionRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this GenerarMedioAutenticacionRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerarMedioAutenticacionRequest)) return false;
        GenerarMedioAutenticacionRequest other = (GenerarMedioAutenticacionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.binMedioAutenticacion==null && other.getBinMedioAutenticacion()==null) || 
             (this.binMedioAutenticacion!=null &&
              this.binMedioAutenticacion.equals(other.getBinMedioAutenticacion()))) &&
            ((this.codigoCliente==null && other.getCodigoCliente()==null) || 
             (this.codigoCliente!=null &&
              this.codigoCliente.equals(other.getCodigoCliente()))) &&
            ((this.codigoMedioAutenticacion==null && other.getCodigoMedioAutenticacion()==null) || 
             (this.codigoMedioAutenticacion!=null &&
              this.codigoMedioAutenticacion.equals(other.getCodigoMedioAutenticacion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getBinMedioAutenticacion() != null) {
            _hashCode += getBinMedioAutenticacion().hashCode();
        }
        if (getCodigoCliente() != null) {
            _hashCode += getCodigoCliente().hashCode();
        }
        if (getCodigoMedioAutenticacion() != null) {
            _hashCode += getCodigoMedioAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerarMedioAutenticacionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMedioAutenticacion"));
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
