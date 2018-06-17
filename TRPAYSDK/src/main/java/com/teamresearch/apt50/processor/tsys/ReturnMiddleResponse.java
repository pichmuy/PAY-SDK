package com.teamresearch.apt50.processor.tsys;


public class ReturnMiddleResponse {

    private ReturnResponse ReturnResponse;

    public class ReturnResponse {
        public String status;
        String responseCode;
        String responseMessage;
        String authCode;
        String hostReferenceNumber;
        String taskID;
        public String transactionID;
        String transactionTimestamp;
        public String transactionAmount;
        String returnedAmount;
        String cardType;
        public String maskedCardNumber;
        String customerReceipt;
        String merchantReceipt;
        public String expirationDate;
    }

    public String getStatus() {
        return this.ReturnResponse.status;
    }

    public String getResponseCode() {
        return this.ReturnResponse.responseCode;
    }

    public String getResponseMessage() {
        return this.ReturnResponse.responseMessage;
    }

    public String getAuthCode() {
        return this.ReturnResponse.authCode;
    }

    public String getHostReferenceNumber() {
        return this.ReturnResponse.hostReferenceNumber;
    }

    public String getTaskID() {
        return this.ReturnResponse.taskID;
    }

    public String getTransactionID() {
        return this.ReturnResponse.transactionID;
    }

    public String getTransactionTimestamp() {
        return this.ReturnResponse.transactionTimestamp;
    }

    public String getTransactionAmount() {
        return this.ReturnResponse.transactionAmount;
    }

    public String getReturnedAmount() {
        this.ReturnResponse.returnedAmount = (this.ReturnResponse.returnedAmount == null ? "0.00" : this.ReturnResponse.returnedAmount);
        return this.ReturnResponse.returnedAmount;
    }

    public String getCardType() {
        return this.ReturnResponse.cardType;
    }

    public String getMaskedCardNumber() {
        return this.ReturnResponse.maskedCardNumber;
    }

    public String getCustomerReceipt() {
        return this.ReturnResponse.customerReceipt;
    }

    public String getMerchantReceipt() {
        return this.ReturnResponse.merchantReceipt;
    }

    public String getExpirationDate() {
        return this.ReturnResponse.expirationDate;
    }

}
