package com.teamresearch.apt50.model;

/**
 * Created by Me on 2/4/2018.
 */

public class SaleResponse {

    public SaleResponse_ saleResponse;

    public class SaleResponse_ {
        public String error;
        public String errorMsg;
        public String transactionID;
        public String transactionTimeStamp;
        public String status;
        public String authCode;
        public double totalAmount;
        public double tip;
        public String addressVerificationCode;
        public String cvvVerificationCode;
        public String cardType;
        public String maskedCardNumber;
        public String token;
        public String expireDate;
        public String firstName;
        public String lastName;
        public String command;
        public String ref;

        public SaleResponse_(String error, String errorMsg, String ref, String transactionID, String transactionTimeStamp,
                             String status, String authCode, double totalAmount, double tip, String addressVerificationCode,
                             String cvvVerificationCode, String cardType, String maskedCardNumber, String token,
                             String expireDate, String firstName, String lastName) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.ref = ref;
            this.transactionID = transactionID;
            this.transactionTimeStamp = transactionTimeStamp;
            this.status = status;
            this.authCode = authCode;
            this.totalAmount = totalAmount;
            this.tip = tip;
            this.addressVerificationCode = addressVerificationCode;
            this.cvvVerificationCode = cvvVerificationCode;
            this.cardType = cardType;
            this.maskedCardNumber = maskedCardNumber;
            this.token = token;
            this.expireDate = expireDate;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public SaleResponse_(String error, String errorMsg, String command, String ref, double totalAmount, double tip) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.command = command;
            this.ref = ref;
            this.totalAmount = totalAmount;
            this.tip = tip;
        }

        public SaleResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }

        public SaleResponse_(String status) {
            this.status = status;
        }
    }

    public SaleResponse(String error, String errorMsg, String ref, String transactionID, String transactionTimeStamp,
                        String status, String authCode, double totalAmount, double tip, String addressVerificationCode,
                        String cvvVerificationCode, String cardType, String maskedCardNumber, String token,
                        String expireDate, String firstName, String lastName) {
        this.saleResponse = new SaleResponse_(error, errorMsg, ref, transactionID, transactionTimeStamp, status, authCode,
                totalAmount, tip, addressVerificationCode, cvvVerificationCode, cardType, maskedCardNumber,
                token, expireDate, firstName, lastName);
    }

    public SaleResponse(String error, String errorMsg, String command, String ref, double totalAmount, double tip) {
        this.saleResponse = new SaleResponse_(error, errorMsg, command, ref, totalAmount, tip);
    }

    public SaleResponse(String error, String errorMsg) {
        this.saleResponse = new SaleResponse_(error, errorMsg);
    }

    public SaleResponse() {
        this.saleResponse = new SaleResponse_("Initiated");
    }
}
