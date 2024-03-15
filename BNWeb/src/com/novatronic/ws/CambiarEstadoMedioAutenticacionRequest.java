/**
 * CambiarEstadoMedioAutenticacionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class CambiarEstadoMedioAutenticacionRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.String estadoMedioAutenticacion;

    private java.lang.String tipoBloqueo;

    public CambiarEstadoMedioAutenticacionRequest() {
    }

    public CambiarEstadoMedioAutenticacionRequest(
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
           java.lang.String codigoMedioAutenticacion,
           java.lang.String estadoMedioAutenticacion,
           java.lang.String tipoBloqueo) {
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
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
        this.estadoMedioAutenticacion = estadoMedioAutenticacion;
        this.tipoBloqueo = tipoBloqueo;
    }


    /**
     * Gets the binMedioAutenticacion value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the estadoMedioAutenticacion value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @return estadoMedioAutenticacion
     */
    public java.lang.String getEstadoMedioAutenticacion() {
        return estadoMedioAutenticacion;
    }


    /**
     * Sets the estadoMedioAutenticacion value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @param estadoMedioAutenticacion
     */
    public void setEstadoMedioAutenticacion(java.lang.String estadoMedioAutenticacion) {
        this.estadoMedioAutenticacion = estadoMedioAutenticacion;
    }


    /**
     * Gets the tipoBloqueo value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @return tipoBloqueo
     */
    public java.lang.String getTipoBloqueo() {
        return tipoBloqueo;
    }


    /**
     * Sets the tipoBloqueo value for this CambiarEstadoMedioAutenticacionRequest.
     * 
     * @param tipoBloqueo
     */
    public void setTipoBloqueo(java.lang.String tipoBloqueo) {
        this.tipoBloqueo = tipoBloqueo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CambiarEstadoMedioAutenticacionRequest)) return false;
        CambiarEstadoMedioAutenticacionRequest other = (CambiarEstadoMedioAutenticacionRequest) obj;
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
            ((this.codigoMedioAutenticacion==null && other.getCodigoMedioAutenticacion()==null) || 
             (this.codigoMedioAutenticacion!=null &&
              this.codigoMedioAutenticacion.equals(other.getCodigoMedioAutenticacion()))) &&
            ((this.estadoMedioAutenticacion==null && other.getEstadoMedioAutenticacion()==null) || 
             (this.estadoMedioAutenticacion!=null &&
              this.estadoMedioAutenticacion.equals(other.getEstadoMedioAutenticacion()))) &&
            ((this.tipoBloqueo==null && other.getTipoBloqueo()==null) || 
             (this.tipoBloqueo!=null &&
              this.tipoBloqueo.equals(other.getTipoBloqueo())));
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
        if (getCodigoMedioAutenticacion() != null) {
            _hashCode += getCodigoMedioAutenticacion().hashCode();
        }
        if (getEstadoMedioAutenticacion() != null) {
            _hashCode += getEstadoMedioAutenticacion().hashCode();
        }
        if (getTipoBloqueo() != null) {
            _hashCode += getTipoBloqueo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CambiarEstadoMedioAutenticacionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binMedioAutenticacion"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoBloqueo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoBloqueo"));
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
