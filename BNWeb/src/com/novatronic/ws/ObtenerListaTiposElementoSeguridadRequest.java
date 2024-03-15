/**
 * ObtenerListaTiposElementoSeguridadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaTiposElementoSeguridadRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binOriginalTipoElementoSeguridad;

    private java.lang.Integer codigoTipoElementoSeguridad;

    private java.lang.String nombreTipoElementoSeguridad;

    public ObtenerListaTiposElementoSeguridadRequest() {
    }

    public ObtenerListaTiposElementoSeguridadRequest(
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
           java.lang.String binOriginalTipoElementoSeguridad,
           java.lang.Integer codigoTipoElementoSeguridad,
           java.lang.String nombreTipoElementoSeguridad) {
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
        this.binOriginalTipoElementoSeguridad = binOriginalTipoElementoSeguridad;
        this.codigoTipoElementoSeguridad = codigoTipoElementoSeguridad;
        this.nombreTipoElementoSeguridad = nombreTipoElementoSeguridad;
    }


    /**
     * Gets the binOriginalTipoElementoSeguridad value for this ObtenerListaTiposElementoSeguridadRequest.
     * 
     * @return binOriginalTipoElementoSeguridad
     */
    public java.lang.String getBinOriginalTipoElementoSeguridad() {
        return binOriginalTipoElementoSeguridad;
    }


    /**
     * Sets the binOriginalTipoElementoSeguridad value for this ObtenerListaTiposElementoSeguridadRequest.
     * 
     * @param binOriginalTipoElementoSeguridad
     */
    public void setBinOriginalTipoElementoSeguridad(java.lang.String binOriginalTipoElementoSeguridad) {
        this.binOriginalTipoElementoSeguridad = binOriginalTipoElementoSeguridad;
    }


    /**
     * Gets the codigoTipoElementoSeguridad value for this ObtenerListaTiposElementoSeguridadRequest.
     * 
     * @return codigoTipoElementoSeguridad
     */
    public java.lang.Integer getCodigoTipoElementoSeguridad() {
        return codigoTipoElementoSeguridad;
    }


    /**
     * Sets the codigoTipoElementoSeguridad value for this ObtenerListaTiposElementoSeguridadRequest.
     * 
     * @param codigoTipoElementoSeguridad
     */
    public void setCodigoTipoElementoSeguridad(java.lang.Integer codigoTipoElementoSeguridad) {
        this.codigoTipoElementoSeguridad = codigoTipoElementoSeguridad;
    }


    /**
     * Gets the nombreTipoElementoSeguridad value for this ObtenerListaTiposElementoSeguridadRequest.
     * 
     * @return nombreTipoElementoSeguridad
     */
    public java.lang.String getNombreTipoElementoSeguridad() {
        return nombreTipoElementoSeguridad;
    }


    /**
     * Sets the nombreTipoElementoSeguridad value for this ObtenerListaTiposElementoSeguridadRequest.
     * 
     * @param nombreTipoElementoSeguridad
     */
    public void setNombreTipoElementoSeguridad(java.lang.String nombreTipoElementoSeguridad) {
        this.nombreTipoElementoSeguridad = nombreTipoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaTiposElementoSeguridadRequest)) return false;
        ObtenerListaTiposElementoSeguridadRequest other = (ObtenerListaTiposElementoSeguridadRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.binOriginalTipoElementoSeguridad==null && other.getBinOriginalTipoElementoSeguridad()==null) || 
             (this.binOriginalTipoElementoSeguridad!=null &&
              this.binOriginalTipoElementoSeguridad.equals(other.getBinOriginalTipoElementoSeguridad()))) &&
            ((this.codigoTipoElementoSeguridad==null && other.getCodigoTipoElementoSeguridad()==null) || 
             (this.codigoTipoElementoSeguridad!=null &&
              this.codigoTipoElementoSeguridad.equals(other.getCodigoTipoElementoSeguridad()))) &&
            ((this.nombreTipoElementoSeguridad==null && other.getNombreTipoElementoSeguridad()==null) || 
             (this.nombreTipoElementoSeguridad!=null &&
              this.nombreTipoElementoSeguridad.equals(other.getNombreTipoElementoSeguridad())));
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
        if (getBinOriginalTipoElementoSeguridad() != null) {
            _hashCode += getBinOriginalTipoElementoSeguridad().hashCode();
        }
        if (getCodigoTipoElementoSeguridad() != null) {
            _hashCode += getCodigoTipoElementoSeguridad().hashCode();
        }
        if (getNombreTipoElementoSeguridad() != null) {
            _hashCode += getNombreTipoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaTiposElementoSeguridadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binOriginalTipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binOriginalTipoElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreTipoElementoSeguridad"));
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
