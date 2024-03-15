/**
 * ConsultarCoordenadasResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ConsultarCoordenadasResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String[][] listaCoordenadas;

    private java.lang.Integer numeroCoordenadas;

    private java.lang.String[] panVirtual;

    public ConsultarCoordenadasResponse() {
    }

    public ConsultarCoordenadasResponse(
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
           java.lang.String[][] listaCoordenadas,
           java.lang.Integer numeroCoordenadas,
           java.lang.String[] panVirtual) {
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
        this.listaCoordenadas = listaCoordenadas;
        this.numeroCoordenadas = numeroCoordenadas;
        this.panVirtual = panVirtual;
    }


    /**
     * Gets the listaCoordenadas value for this ConsultarCoordenadasResponse.
     * 
     * @return listaCoordenadas
     */
    public java.lang.String[][] getListaCoordenadas() {
        return listaCoordenadas;
    }


    /**
     * Sets the listaCoordenadas value for this ConsultarCoordenadasResponse.
     * 
     * @param listaCoordenadas
     */
    public void setListaCoordenadas(java.lang.String[][] listaCoordenadas) {
        this.listaCoordenadas = listaCoordenadas;
    }

    public java.lang.String[] getListaCoordenadas(int i) {
        return this.listaCoordenadas[i];
    }

    public void setListaCoordenadas(int i, java.lang.String[] _value) {
        this.listaCoordenadas[i] = _value;
    }


    /**
     * Gets the numeroCoordenadas value for this ConsultarCoordenadasResponse.
     * 
     * @return numeroCoordenadas
     */
    public java.lang.Integer getNumeroCoordenadas() {
        return numeroCoordenadas;
    }


    /**
     * Sets the numeroCoordenadas value for this ConsultarCoordenadasResponse.
     * 
     * @param numeroCoordenadas
     */
    public void setNumeroCoordenadas(java.lang.Integer numeroCoordenadas) {
        this.numeroCoordenadas = numeroCoordenadas;
    }


    /**
     * Gets the panVirtual value for this ConsultarCoordenadasResponse.
     * 
     * @return panVirtual
     */
    public java.lang.String[] getPanVirtual() {
        return panVirtual;
    }


    /**
     * Sets the panVirtual value for this ConsultarCoordenadasResponse.
     * 
     * @param panVirtual
     */
    public void setPanVirtual(java.lang.String[] panVirtual) {
        this.panVirtual = panVirtual;
    }

    public java.lang.String getPanVirtual(int i) {
        return this.panVirtual[i];
    }

    public void setPanVirtual(int i, java.lang.String _value) {
        this.panVirtual[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarCoordenadasResponse)) return false;
        ConsultarCoordenadasResponse other = (ConsultarCoordenadasResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaCoordenadas==null && other.getListaCoordenadas()==null) || 
             (this.listaCoordenadas!=null &&
              java.util.Arrays.equals(this.listaCoordenadas, other.getListaCoordenadas()))) &&
            ((this.numeroCoordenadas==null && other.getNumeroCoordenadas()==null) || 
             (this.numeroCoordenadas!=null &&
              this.numeroCoordenadas.equals(other.getNumeroCoordenadas()))) &&
            ((this.panVirtual==null && other.getPanVirtual()==null) || 
             (this.panVirtual!=null &&
              java.util.Arrays.equals(this.panVirtual, other.getPanVirtual())));
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
        if (getListaCoordenadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCoordenadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCoordenadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroCoordenadas() != null) {
            _hashCode += getNumeroCoordenadas().hashCode();
        }
        if (getPanVirtual() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPanVirtual());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPanVirtual(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarCoordenadasResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCoordenadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaCoordenadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCoordenadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCoordenadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("panVirtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "panVirtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
