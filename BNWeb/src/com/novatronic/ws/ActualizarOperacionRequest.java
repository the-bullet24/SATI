/**
 * ActualizarOperacionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ActualizarOperacionRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String identificadorOperacion;

    private java.lang.Integer resultadoValidacion;

    private java.lang.Integer tipoElementoSeguridad;

    public ActualizarOperacionRequest() {
    }

    public ActualizarOperacionRequest(
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
           java.lang.String identificadorOperacion,
           java.lang.Integer resultadoValidacion,
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
        this.identificadorOperacion = identificadorOperacion;
        this.resultadoValidacion = resultadoValidacion;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the identificadorOperacion value for this ActualizarOperacionRequest.
     * 
     * @return identificadorOperacion
     */
    public java.lang.String getIdentificadorOperacion() {
        return identificadorOperacion;
    }


    /**
     * Sets the identificadorOperacion value for this ActualizarOperacionRequest.
     * 
     * @param identificadorOperacion
     */
    public void setIdentificadorOperacion(java.lang.String identificadorOperacion) {
        this.identificadorOperacion = identificadorOperacion;
    }


    /**
     * Gets the resultadoValidacion value for this ActualizarOperacionRequest.
     * 
     * @return resultadoValidacion
     */
    public java.lang.Integer getResultadoValidacion() {
        return resultadoValidacion;
    }


    /**
     * Sets the resultadoValidacion value for this ActualizarOperacionRequest.
     * 
     * @param resultadoValidacion
     */
    public void setResultadoValidacion(java.lang.Integer resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }


    /**
     * Gets the tipoElementoSeguridad value for this ActualizarOperacionRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this ActualizarOperacionRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActualizarOperacionRequest)) return false;
        ActualizarOperacionRequest other = (ActualizarOperacionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.identificadorOperacion==null && other.getIdentificadorOperacion()==null) || 
             (this.identificadorOperacion!=null &&
              this.identificadorOperacion.equals(other.getIdentificadorOperacion()))) &&
            ((this.resultadoValidacion==null && other.getResultadoValidacion()==null) || 
             (this.resultadoValidacion!=null &&
              this.resultadoValidacion.equals(other.getResultadoValidacion()))) &&
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
        if (getIdentificadorOperacion() != null) {
            _hashCode += getIdentificadorOperacion().hashCode();
        }
        if (getResultadoValidacion() != null) {
            _hashCode += getResultadoValidacion().hashCode();
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActualizarOperacionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoValidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultadoValidacion"));
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
