package com.teamresearch.apt50.model;

public class AuthorizeResponse {

    public AuthorizeResponse_ authorizeResponse;

    public class AuthorizeResponse_ {
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

        public AuthorizeResponse_(String error, String errorMsg, String ref, String transactionID, String transactionTimeStamp, String status,
                                  String authCode, double totalAmount, double tip, String addressVerificationCode, String cvvVerificationCode,
                                  String cardType, String maskedCardNumber, String token, String expireDate, String firstName, String lastName) {
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

        public AuthorizeResponse_(String error, String errorMsg, String command, String ref, double totalAmount, double tip) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.command = command;
            this.ref = ref;
            this.totalAmount = totalAmount;
            this.tip = tip;
        }

        public AuthorizeResponse_(String status, String responseCode, String responseMessage) {
            this.status = status;
            this.error = responseCode;
            this.errorMsg = responseMessage;
        }

        public AuthorizeResponse_(String status) {
            this.status = status;
        }

        public AuthorizeResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public AuthorizeResponse(String error, String errorMsg, String ref, String transactionID, String transactionTimeStamp, String status,
                             String authCode, double totalAmount, double tip, String addressVerificationCode, String cvvVerificationCode,
                             String cardType, String maskedCardNumber, String token, String expireDate, String firstName, String lastName) {
        this.authorizeResponse = new AuthorizeResponse_(error, errorMsg, ref, transactionID, transactionTimeStamp, status, authCode,
                totalAmount, tip, addressVerificationCode, cvvVerificationCode, cardType, maskedCardNumber,
                token, expireDate, firstName, lastName);
    }

    public AuthorizeResponse(String error, String errorMsg, String command, String ref, double totalAmount, double tip) {
        this.authorizeResponse = new AuthorizeResponse_(error, errorMsg, command, ref, totalAmount, tip);
    }

    public AuthorizeResponse(String error, String errorMsg) {
        this.authorizeResponse = new AuthorizeResponse_(error, errorMsg);
    }

    public AuthorizeResponse(String status, String responseCode, String responseMessage) {
        this.authorizeResponse = new AuthorizeResponse_(status, responseCode, responseMessage);
    }

    public AuthorizeResponse() {
        this.authorizeResponse = new AuthorizeResponse_("Initiated");
    }
}
