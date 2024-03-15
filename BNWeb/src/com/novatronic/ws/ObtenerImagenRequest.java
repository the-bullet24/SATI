/**
 * ObtenerImagenRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerImagenRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.Integer codificacion;

    private java.lang.String identificadorImagen;

    private java.lang.Integer tipoElementoSeguridad;

    public ObtenerImagenRequest() {
    }

    public ObtenerImagenRequest(
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
           java.lang.Integer codificacion,
           java.lang.String identificadorImagen,
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
        this.codificacion = codificacion;
        this.identificadorImagen = identificadorImagen;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the codificacion value for this ObtenerImagenRequest.
     * 
     * @return codificacion
     */
    public java.lang.Integer getCodificacion() {
        return codificacion;
    }


    /**
     * Sets the codificacion value for this ObtenerImagenRequest.
     * 
     * @param codificacion
     */
    public void setCodificacion(java.lang.Integer codificacion) {
        this.codificacion = codificacion;
    }


    /**
     * Gets the identificadorImagen value for this ObtenerImagenRequest.
     * 
     * @return identificadorImagen
     */
    public java.lang.String getIdentificadorImagen() {
        return identificadorImagen;
    }


    /**
     * Sets the identificadorImagen value for this ObtenerImagenRequest.
     * 
     * @param identificadorImagen
     */
    public void setIdentificadorImagen(java.lang.String identificadorImagen) {
        this.identificadorImagen = identificadorImagen;
    }


    /**
     * Gets the tipoElementoSeguridad value for this ObtenerImagenRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this ObtenerImagenRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerImagenRequest)) return false;
        ObtenerImagenRequest other = (ObtenerImagenRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codificacion==null && other.getCodificacion()==null) || 
             (this.codificacion!=null &&
              this.codificacion.equals(other.getCodificacion()))) &&
            ((this.identificadorImagen==null && other.getIdentificadorImagen()==null) || 
             (this.identificadorImagen!=null &&
              this.identificadorImagen.equals(other.getIdentificadorImagen()))) &&
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
        if (getCodificacion() != null) {
            _hashCode += getCodificacion().hashCode();
        }
        if (getIdentificadorImagen() != null) {
            _hashCode += getIdentificadorImagen().hashCode();
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerImagenRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagenRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
