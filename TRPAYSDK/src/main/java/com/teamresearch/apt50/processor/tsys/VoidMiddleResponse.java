package com.teamresearch.apt50.processor.tsys;

public class VoidMiddleResponse {

    public VoidResponse VoidResponse;

    public class VoidResponse {
        public String status;
        String responseCode;
        String responseMessage;
        String authCode;
        String hostReferenceNumber;
        String taskID;
        public String transactionID;
        String transactionTimestamp;
        String orderNumber;
        String externalReferenceID;
        String voidedAmount;
        String cardType;
        public String maskedCardNumber;
        String customerReceipt;
        String merchantReceipt;
    }

    public String getStatus() {
        return this.VoidResponse.status;
    }

    public String getResponseCode() {
        return this.VoidResponse.responseCode;
    }

    public String getResponseMessage() {
        return this.VoidResponse.responseMessage;
    }

    public String getAuthCode() {
        return this.VoidResponse.authCode;
    }

    public String getHostReferenceNumber() {
        return this.VoidResponse.hostReferenceNumber;
    }

    public String getTaskID() {
        return this.VoidResponse.taskID;
    }

    public String getTransactionID() {
        return this.VoidResponse.transactionID;
    }

    public String getTransactionTimestamp() {
        return this.VoidResponse.transactionTimestamp;
    }

    public String getOrderNumber() {
        return this.VoidResponse.orderNumber;
    }

    public String getExternalReferenceID() {
        return this.VoidResponse.externalReferenceID;
    }

    public String getVoidedAmount() {
        this.VoidResponse.voidedAmount = (this.VoidResponse.voidedAmount == null ? "0" : this.VoidResponse.voidedAmount);
        return this.VoidResponse.voidedAmount;
    }

    public String getCardType() {
        return this.VoidResponse.cardType;
    }

    public String getMaskedCardNumber() {
        return this.VoidResponse.maskedCardNumber;
    }

    public String getCustomerReceipt() {
        return this.VoidResponse.customerReceipt;
    }

    public String getMerchantReceipt() {
        return this.VoidResponse.merchantReceipt;
    }
}
