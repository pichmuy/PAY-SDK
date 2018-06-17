package com.teamresearch.apt50.model;

public class ReturnResponse {

    public ReturnResponse_ returnResponse;

    public class ReturnResponse_ {

        public String cardType;
        public String error;
        public String errorMsg;
        public String status;
        public String expirationDate;
        public String maskedCardNumber;
        public double returnAmount;
        public String transactionID;
        public String transactionTimeStamp;
        public String command;
        public String ref;

        public ReturnResponse_(String cardType, String error, String errorMsg, String ref, String expirationDate, String maskedCardNumber,
                               double returnAmount, String transactionID, String transactionTimeStamp) {
            this.cardType = cardType;
            this.error = error;
            this.errorMsg = errorMsg;
            this.expirationDate = expirationDate;
            this.maskedCardNumber = maskedCardNumber;
            this.returnAmount = returnAmount;
            this.transactionID = transactionID;
            this.transactionTimeStamp = transactionTimeStamp;
            this.ref = ref;
        }

        public ReturnResponse_(String error, String errorMsg) {

            this.error = error;
            this.errorMsg = errorMsg;

        }

        public ReturnResponse_(String status) {
            this.status = status;
        }

        public ReturnResponse_(String error, String errorMsg, String command, String ref, double totalAmount) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.command = command;
            this.ref = ref;
            this.returnAmount = totalAmount;
        }
    }

    public ReturnResponse(String cardType, String error, String errorMsg, String ref, String expirationDate, String maskedCardNumber,
                          double returnAmount, String transactionID, String transactionTimeStamp) {
        this.returnResponse = new ReturnResponse_(cardType, error, errorMsg, ref, expirationDate, maskedCardNumber,
                returnAmount, transactionID, transactionTimeStamp);
    }

    public ReturnResponse(String error, String errorMsg) {
        this.returnResponse = new ReturnResponse_(error, errorMsg);
    }

    public ReturnResponse() {
        this.returnResponse = new ReturnResponse_("Initiated");
    }

    public ReturnResponse(String error, String errorMsg, String command, String ref, double returnAmount) {
        this.returnResponse = new ReturnResponse_(error, errorMsg, command, ref, returnAmount);
    }
}
