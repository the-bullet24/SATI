/**
 * GenerarMedioAutenticacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class GenerarMedioAutenticacionResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoAutorizacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.String estadoMedioAutenticacion;

    public GenerarMedioAutenticacionResponse() {
    }

    public GenerarMedioAutenticacionResponse(
           java.lang.Integer tipoFormato,
           java.lang.String codigoComando,
           java.lang.String codigoEmpresa,
           java.lang.String tipoAplicacionOrigen,
           java.lang.String codigoAplicacionOrigen,
           java.util.Calendar fechaDate,
           java.lang.Integer fecha,
           java.lang.Integer hora,
           java.lang.String usuario,
           java.lang.Integer numeroSecuencia,
           java.lang.String codigoComercio,
           java.lang.Integer tipoTerminal,
           java.lang.String numeroTerminal,
           java.lang.String codigoZonaIntercambio,
           java.lang.String filler,
           java.lang.String origen,
           java.lang.Integer codigoRespuestaPrincipal,
           java.lang.String mensajeError,
           java.lang.String codigoRespuestaExtendido,
           java.lang.String binMedioAutenticacion,
           java.lang.String codigoAutorizacion,
           java.lang.String codigoMedioAutenticacion,
           java.lang.String estadoMedioAutenticacion) {
        super(
            tipoFormato,
            codigoComando,
            codigoEmpresa,
            tipoAplicacionOrigen,
            codigoAplicacionOrigen,
            fechaDate,
            fecha,
            hora,
            usuario,
            numeroSecuencia,
            codigoComercio,
            tipoTerminal,
            numeroTerminal,
            codigoZonaIntercambio,
            filler,
            origen,
            codigoRespuestaPrincipal,
            mensajeError,
            codigoRespuestaExtendido);
        this.binMedioAutenticacion = binMedioAutenticacion;
        this.codigoAutorizacion = codigoAutorizacion;
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
        this.estadoMedioAutenticacion = estadoMedioAutenticacion;
    }


    /**
     * Gets the binMedioAutenticacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoAutorizacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @return codigoAutorizacion
     */
    public java.lang.String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }


    /**
     * Sets the codigoAutorizacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @param codigoAutorizacion
     */
    public void setCodigoAutorizacion(java.lang.String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the estadoMedioAutenticacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @return estadoMedioAutenticacion
     */
    public java.lang.String getEstadoMedioAutenticacion() {
        return estadoMedioAutenticacion;
    }


    /**
     * Sets the estadoMedioAutenticacion value for this GenerarMedioAutenticacionResponse.
     * 
     * @param estadoMedioAutenticacion
     */
    public void setEstadoMedioAutenticacion(java.lang.String estadoMedioAutenticacion) {
        this.estadoMedioAutenticacion = estadoMedioAutenticacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerarMedioAutenticacionResponse)) return false;
        GenerarMedioAutenticacionResponse other = (GenerarMedioAutenticacionResponse) obj;
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
            ((this.codigoAutorizacion==null && other.getCodigoAutorizacion()==null) || 
             (this.codigoAutorizacion!=null &&
              this.codigoAutorizacion.equals(other.getCodigoAutorizacion()))) &&
            ((this.codigoMedioAutenticacion==null && other.getCodigoMedioAutenticacion()==null) || 
             (this.codigoMedioAutenticacion!=null &&
              this.codigoMedioAutenticacion.equals(other.getCodigoMedioAutenticacion()))) &&
            ((this.estadoMedioAutenticacion==null && other.getEstadoMedioAutenticacion()==null) || 
             (this.estadoMedioAutenticacion!=null &&
              this.estadoMedioAutenticacion.equals(other.getEstadoMedioAutenticacion())));
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
        if (getCodigoAutorizacion() != null) {
            _hashCode += getCodigoAutorizacion().hashCode();
        }
        if (getCodigoMedioAutenticacion() != null) {
            _hashCode += getCodigoMedioAutenticacion().hashCode();
        }
        if (getEstadoMedioAutenticacion() != null) {
            _hashCode += getEstadoMedioAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerarMedioAutenticacionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAutorizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAutorizacion"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoMedioAutenticacion"));
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
