/**
 * ObtenerListaBinesTipoElementoSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaBinesTipoElementoSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String[][] listaBines;

    private java.lang.Integer numeroBines;

    public ObtenerListaBinesTipoElementoSeguridadResponse() {
    }

    public ObtenerListaBinesTipoElementoSeguridadResponse(
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
           java.lang.String[][] listaBines,
           java.lang.Integer numeroBines) {
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
        this.listaBines = listaBines;
        this.numeroBines = numeroBines;
    }


    /**
     * Gets the listaBines value for this ObtenerListaBinesTipoElementoSeguridadResponse.
     * 
     * @return listaBines
     */
    public java.lang.String[][] getListaBines() {
        return listaBines;
    }


    /**
     * Sets the listaBines value for this ObtenerListaBinesTipoElementoSeguridadResponse.
     * 
     * @param listaBines
     */
    public void setListaBines(java.lang.String[][] listaBines) {
        this.listaBines = listaBines;
    }

    public java.lang.String[] getListaBines(int i) {
        return this.listaBines[i];
    }

    public void setListaBines(int i, java.lang.String[] _value) {
        this.listaBines[i] = _value;
    }


    /**
     * Gets the numeroBines value for this ObtenerListaBinesTipoElementoSeguridadResponse.
     * 
     * @return numeroBines
     */
    public java.lang.Integer getNumeroBines() {
        return numeroBines;
    }


    /**
     * Sets the numeroBines value for this ObtenerListaBinesTipoElementoSeguridadResponse.
     * 
     * @param numeroBines
     */
    public void setNumeroBines(java.lang.Integer numeroBines) {
        this.numeroBines = numeroBines;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaBinesTipoElementoSeguridadResponse)) return false;
        ObtenerListaBinesTipoElementoSeguridadResponse other = (ObtenerListaBinesTipoElementoSeguridadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaBines==null && other.getListaBines()==null) || 
             (this.listaBines!=null &&
              java.util.Arrays.equals(this.listaBines, other.getListaBines()))) &&
            ((this.numeroBines==null && other.getNumeroBines()==null) || 
             (this.numeroBines!=null &&
              this.numeroBines.equals(other.getNumeroBines())));
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
        if (getListaBines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaBines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaBines(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroBines() != null) {
            _hashCode += getNumeroBines().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaBinesTipoElementoSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaBines");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaBines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroBines");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroBines"));
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
