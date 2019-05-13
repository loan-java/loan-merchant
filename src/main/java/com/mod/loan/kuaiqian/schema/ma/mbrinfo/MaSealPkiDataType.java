/**
 * MaSealPkiDataType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.kuaiqian.schema.ma.mbrinfo;

public class MaSealPkiDataType  implements java.io.Serializable {
    /* 原始报文 */
    private String originalData;

    /* 签名数据 */
    private String signedData;

    /* 加密数据 */
    private String encryptedData;

    /* 数字信封 */
    private String digitalEnvelope;

    public MaSealPkiDataType() {
    }

    public MaSealPkiDataType(
           String originalData,
           String signedData,
           String encryptedData,
           String digitalEnvelope) {
           this.originalData = originalData;
           this.signedData = signedData;
           this.encryptedData = encryptedData;
           this.digitalEnvelope = digitalEnvelope;
    }


    /**
     * Gets the originalData value for this MaSealPkiDataType.
     *
     * @return originalData   * 原始报文
     */
    public String getOriginalData() {
        return originalData;
    }


    /**
     * Sets the originalData value for this MaSealPkiDataType.
     *
     * @param originalData   * 原始报文
     */
    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }


    /**
     * Gets the signedData value for this MaSealPkiDataType.
     *
     * @return signedData   * 签名数据
     */
    public String getSignedData() {
        return signedData;
    }


    /**
     * Sets the signedData value for this MaSealPkiDataType.
     *
     * @param signedData   * 签名数据
     */
    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }


    /**
     * Gets the encryptedData value for this MaSealPkiDataType.
     *
     * @return encryptedData   * 加密数据
     */
    public String getEncryptedData() {
        return encryptedData;
    }


    /**
     * Sets the encryptedData value for this MaSealPkiDataType.
     *
     * @param encryptedData   * 加密数据
     */
    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }


    /**
     * Gets the digitalEnvelope value for this MaSealPkiDataType.
     *
     * @return digitalEnvelope   * 数字信封
     */
    public String getDigitalEnvelope() {
        return digitalEnvelope;
    }


    /**
     * Sets the digitalEnvelope value for this MaSealPkiDataType.
     *
     * @param digitalEnvelope   * 数字信封
     */
    public void setDigitalEnvelope(String digitalEnvelope) {
        this.digitalEnvelope = digitalEnvelope;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof MaSealPkiDataType)) return false;
        MaSealPkiDataType other = (MaSealPkiDataType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.originalData==null && other.getOriginalData()==null) ||
             (this.originalData!=null &&
              this.originalData.equals(other.getOriginalData()))) &&
            ((this.signedData==null && other.getSignedData()==null) ||
             (this.signedData!=null &&
              this.signedData.equals(other.getSignedData()))) &&
            ((this.encryptedData==null && other.getEncryptedData()==null) ||
             (this.encryptedData!=null &&
              this.encryptedData.equals(other.getEncryptedData()))) &&
            ((this.digitalEnvelope==null && other.getDigitalEnvelope()==null) ||
             (this.digitalEnvelope!=null &&
              this.digitalEnvelope.equals(other.getDigitalEnvelope())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getOriginalData() != null) {
            _hashCode += getOriginalData().hashCode();
        }
        if (getSignedData() != null) {
            _hashCode += getSignedData().hashCode();
        }
        if (getEncryptedData() != null) {
            _hashCode += getEncryptedData().hashCode();
        }
        if (getDigitalEnvelope() != null) {
            _hashCode += getDigitalEnvelope().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MaSealPkiDataType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "maSealPkiDataType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "originalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signedData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "signedData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encryptedData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "encryptedData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digitalEnvelope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "digitalEnvelope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
