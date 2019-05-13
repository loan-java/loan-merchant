/**
 * BalanceResponseBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.kuaiqian.schema.ma.mbrinfo.balance;

import com.mod.loan.kuaiqian.schema.ma.mbrinfo.MaSealPkiDataType;

public class BalanceResponseBody  implements java.io.Serializable {
    private BalanceApiResponseType balanceApiResponseType;

    private MaSealPkiDataType maSealPkiDataType;

    public BalanceResponseBody() {
    }

    public BalanceResponseBody(
           BalanceApiResponseType balanceApiResponseType,
           MaSealPkiDataType maSealPkiDataType) {
           this.balanceApiResponseType = balanceApiResponseType;
           this.maSealPkiDataType = maSealPkiDataType;
    }


    /**
     * Gets the balanceApiResponseType value for this BalanceResponseBody.
     * 
     * @return balanceApiResponseType
     */
    public BalanceApiResponseType getBalanceApiResponseType() {
        return balanceApiResponseType;
    }


    /**
     * Sets the balanceApiResponseType value for this BalanceResponseBody.
     * 
     * @param balanceApiResponseType
     */
    public void setBalanceApiResponseType(BalanceApiResponseType balanceApiResponseType) {
        this.balanceApiResponseType = balanceApiResponseType;
    }


    /**
     * Gets the maSealPkiDataType value for this BalanceResponseBody.
     * 
     * @return maSealPkiDataType
     */
    public MaSealPkiDataType getMaSealPkiDataType() {
        return maSealPkiDataType;
    }


    /**
     * Sets the maSealPkiDataType value for this BalanceResponseBody.
     * 
     * @param maSealPkiDataType
     */
    public void setMaSealPkiDataType(MaSealPkiDataType maSealPkiDataType) {
        this.maSealPkiDataType = maSealPkiDataType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BalanceResponseBody)) return false;
        BalanceResponseBody other = (BalanceResponseBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.balanceApiResponseType==null && other.getBalanceApiResponseType()==null) ||
             (this.balanceApiResponseType!=null &&
              this.balanceApiResponseType.equals(other.getBalanceApiResponseType()))) &&
            ((this.maSealPkiDataType==null && other.getMaSealPkiDataType()==null) ||
             (this.maSealPkiDataType!=null &&
              this.maSealPkiDataType.equals(other.getMaSealPkiDataType())));
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
        if (getBalanceApiResponseType() != null) {
            _hashCode += getBalanceApiResponseType().hashCode();
        }
        if (getMaSealPkiDataType() != null) {
            _hashCode += getMaSealPkiDataType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalanceResponseBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balance-response-body"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balanceApiResponseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceApiResponseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceApiResponseType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maSealPkiDataType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "maSealPkiDataType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "maSealPkiDataType"));
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
