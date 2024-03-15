/**
 * ConsultarCoordenadasRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ConsultarCoordenadasRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String identificadorTDC;

    private java.lang.Integer numeroCoordenadas;

    private java.lang.Integer tipoElementoSeguridad;

    public ConsultarCoordenadasRequest() {
    }

    public ConsultarCoordenadasRequest(
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
           java.lang.String identificadorTDC,
           java.lang.Integer numeroCoordenadas,
           java.lang.Integer tipoElementoSeguridad) {
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
        this.identificadorTDC = identificadorTDC;
        this.numeroCoordenadas = numeroCoordenadas;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the identificadorTDC value for this ConsultarCoordenadasRequest.
     * 
     * @return identificadorTDC
     */
    public java.lang.String getIdentificadorTDC() {
        return identificadorTDC;
    }


    /**
     * Sets the identificadorTDC value for this ConsultarCoordenadasRequest.
     * 
     * @param identificadorTDC
     */
    public void setIdentificadorTDC(java.lang.String identificadorTDC) {
        this.identificadorTDC = identificadorTDC;
    }


    /**
     * Gets the numeroCoordenadas value for this ConsultarCoordenadasRequest.
     * 
     * @return numeroCoordenadas
     */
    public java.lang.Integer getNumeroCoordenadas() {
        return numeroCoordenadas;
    }


    /**
     * Sets the numeroCoordenadas value for this ConsultarCoordenadasRequest.
     * 
     * @param numeroCoordenadas
     */
    public void setNumeroCoordenadas(java.lang.Integer numeroCoordenadas) {
        this.numeroCoordenadas = numeroCoordenadas;
    }


    /**
     * Gets the tipoElementoSeguridad value for this ConsultarCoordenadasRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this ConsultarCoordenadasRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarCoordenadasRequest)) return false;
        ConsultarCoordenadasRequest other = (ConsultarCoordenadasRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.identificadorTDC==null && other.getIdentificadorTDC()==null) || 
             (this.identificadorTDC!=null &&
              this.identificadorTDC.equals(other.getIdentificadorTDC()))) &&
            ((this.numeroCoordenadas==null && other.getNumeroCoordenadas()==null) || 
             (this.numeroCoordenadas!=null &&
              this.numeroCoordenadas.equals(other.getNumeroCoordenadas()))) &&
            ((this.tipoElementoSeguridad==null && other.getTipoElementoSeguridad()==null) || 
             (this.tipoElementoSeguridad!=null &&
              this.tipoElementoSeguridad.equals(other.getTipoElementoSeguridad())));
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
        if (getIdentificadorTDC() != null) {
            _hashCode += getIdentificadorTDC().hashCode();
        }
        if (getNumeroCoordenadas() != null) {
            _hashCode += getNumeroCoordenadas().hashCode();
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarCoordenadasRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorTDC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorTDC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCoordenadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCoordenadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
