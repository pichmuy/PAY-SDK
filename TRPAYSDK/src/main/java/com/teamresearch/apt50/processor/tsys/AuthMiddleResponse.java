package com.teamresearch.apt50.processor.tsys;


public class AuthMiddleResponse {

    private AuthMiddleResponse.AuthResponse AuthResponse;

    public class AuthResponse {
        public String status;
        String responseCode;
        String responseMessage;
        String authCode;
        String hostReferenceNumber;
        String taskID;
        public String transactionID;
        String transactionTimestamp;
        public String transactionAmount;
        String processedAmount;
        String totalAmount;
        public String tip;
        String addressVerificationCode;
        String cvvVerificationCode;
        String cardType;
        public String maskedCardNumber;
        public String token;
        public String expirationDate;
        String commercialCard;
        public String firstName;
        public String lastName;
        String customerReceipt;
        String merchantReceipt;
    }

    public String getStatus() {
        return this.AuthResponse.status;
    }

    public String getResponseCode() {
        return this.AuthResponse.responseCode;
    }

    public String getResponseMessage() {
        return this.AuthResponse.responseMessage;
    }

    public String getAuthCode() {
        return this.AuthResponse.authCode;
    }

    public String getHostReferenceNumber() {
        return this.AuthResponse.hostReferenceNumber;
    }

    public String getTaskID() {
        return this.AuthResponse.taskID;
    }

    public String getTransactionID() {
        return this.AuthResponse.transactionID;
    }

    public String getTransactionTimestamp() {
        return this.AuthResponse.transactionTimestamp;
    }

    public String getTransactionAmount() {
        return this.AuthResponse.transactionAmount;
    }

    public String getProcessedAmount() {
        return this.AuthResponse.processedAmount;
    }

    public String getTotalAmount() {
        this.AuthResponse.totalAmount = (this.AuthResponse.totalAmount == null ? "0.00" : this.AuthResponse.totalAmount);
        return this.AuthResponse.totalAmount;
    }

    public String getTip() {
        this.AuthResponse.tip = (this.AuthResponse.tip == null ? "0.00" : this.AuthResponse.tip);
        return this.AuthResponse.tip;
    }

    public String getAddressVerificationCode() {
        return this.AuthResponse.addressVerificationCode;
    }

    public String getCvvVerificationCode() {
        return this.AuthResponse.cvvVerificationCode;
    }

    public String getCardType() {
        return this.AuthResponse.cardType;
    }

    public String getMaskedCardNumber() {
        return this.AuthResponse.maskedCardNumber;
    }

    public String getCommercialCard() {
        return this.AuthResponse.commercialCard;
    }

    public String getFirstName() {
        return this.AuthResponse.firstName;
    }

    public String getLastName() {
        return this.AuthResponse.lastName;
    }

    public String getCustomerReceipt() {
        return this.AuthResponse.customerReceipt;
    }

    public String getMerchantReceipt() {
        return this.AuthResponse.merchantReceipt;
    }

    public String getExpirationDate() {
        return this.AuthResponse.expirationDate;
    }
}
