/**
 * VerificarDisponibilidadElementoSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class VerificarDisponibilidadElementoSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String estadoElementoSeguridad;

    public VerificarDisponibilidadElementoSeguridadResponse() {
    }

    public VerificarDisponibilidadElementoSeguridadResponse(
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
           java.lang.String estadoElementoSeguridad) {
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
        this.estadoElementoSeguridad = estadoElementoSeguridad;
    }


    /**
     * Gets the estadoElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadResponse.
     * 
     * @return estadoElementoSeguridad
     */
    public java.lang.String getEstadoElementoSeguridad() {
        return estadoElementoSeguridad;
    }


    /**
     * Sets the estadoElementoSeguridad value for this VerificarDisponibilidadElementoSeguridadResponse.
     * 
     * @param estadoElementoSeguridad
     */
    public void setEstadoElementoSeguridad(java.lang.String estadoElementoSeguridad) {
        this.estadoElementoSeguridad = estadoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarDisponibilidadElementoSeguridadResponse)) return false;
        VerificarDisponibilidadElementoSeguridadResponse other = (VerificarDisponibilidadElementoSeguridadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.estadoElementoSeguridad==null && other.getEstadoElementoSeguridad()==null) || 
             (this.estadoElementoSeguridad!=null &&
              this.estadoElementoSeguridad.equals(other.getEstadoElementoSeguridad())));
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
        if (getEstadoElementoSeguridad() != null) {
            _hashCode += getEstadoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarDisponibilidadElementoSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoElementoSeguridad"));
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
