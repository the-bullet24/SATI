/**
 * VerificarDisponibilidadElementoSeguridadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class VerificarDisponibilidadElementoSeguridadRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String codigoPrimarioElementoSeguridad;

    private java.lang.String codigoSecundarioElementoSeguridad;

    private java.lang.Integer tipoElementoSeguridad;

    public VerificarDisponibilidadElementoSeguridadRequest() {
    }

    public VerificarDisponibilidadElementoSeguridadRequest(
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
           java.lang.String codigoPrimarioElementoSeguridad,
           java.lang.String codigoSecundarioElementoSeguridad,
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
        this.codigoPrimarioElementoSeguridad = codigoPrimarioElementoSeguridad;
        this.codigoSecundarioElementoSeguridad = codigoSecundarioElementoSeguridad;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the codigoPrimarioElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadRequest.
     * 
     * @return codigoPrimarioElementoSeguridad
     */
    public java.lang.String getCodigoPrimarioElementoSeguridad() {
        return codigoPrimarioElementoSeguridad;
    }


    /**
     * Sets the codigoPrimarioElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadRequest.
     * 
     * @param codigoPrimarioElementoSeguridad
     */
    public void setCodigoPrimarioElementoSeguridad(java.lang.String codigoPrimarioElementoSeguridad) {
        this.codigoPrimarioElementoSeguridad = codigoPrimarioElementoSeguridad;
    }


    /**
     * Gets the codigoSecundarioElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadRequest.
     * 
     * @return codigoSecundarioElementoSeguridad
     */
    public java.lang.String getCodigoSecundarioElementoSeguridad() {
        return codigoSecundarioElementoSeguridad;
    }


    /**
     * Sets the codigoSecundarioElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadRequest.
     * 
     * @param codigoSecundarioElementoSeguridad
     */
    public void setCodigoSecundarioElementoSeguridad(java.lang.String codigoSecundarioElementoSeguridad) {
        this.codigoSecundarioElementoSeguridad = codigoSecundarioElementoSeguridad;
    }


    /**
     * Gets the tipoElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarDisponibilidadElementoSeguridadRequest)) return false;
        VerificarDisponibilidadElementoSeguridadRequest other = (VerificarDisponibilidadElementoSeguridadRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoPrimarioElementoSeguridad==null && other.getCodigoPrimarioElementoSeguridad()==null) || 
             (this.codigoPrimarioElementoSeguridad!=null &&
              this.codigoPrimarioElementoSeguridad.equals(other.getCodigoPrimarioElementoSeguridad()))) &&
            ((this.codigoSecundarioElementoSeguridad==null && other.getCodigoSecundarioElementoSeguridad()==null) || 
             (this.codigoSecundarioElementoSeguridad!=null &&
              this.codigoSecundarioElementoSeguridad.equals(other.getCodigoSecundarioElementoSeguridad()))) &&
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
        if (getCodigoPrimarioElementoSeguridad() != null) {
            _hashCode += getCodigoPrimarioElementoSeguridad().hashCode();
        }
        if (getCodigoSecundarioElementoSeguridad() != null) {
            _hashCode += getCodigoSecundarioElementoSeguridad().hashCode();
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarDisponibilidadElementoSeguridadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPrimarioElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPrimarioElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSecundarioElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSecundarioElementoSeguridad"));
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
