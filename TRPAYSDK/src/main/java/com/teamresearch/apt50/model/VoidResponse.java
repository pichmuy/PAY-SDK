package com.teamresearch.apt50.model;

public class VoidResponse {

    public VoidResponse_ voidResponse;

    public class VoidResponse_ {
        public String error;
        public String errorMsg;
        public String voidedAmount;
        public String transactionID;
        public String transactionTimeStamp;

        public VoidResponse_(String error, String errorMsg, String voidedAmount, String transactionID, String transactionTimeStamp) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.voidedAmount = voidedAmount;
            this.transactionID = transactionID;
            this.transactionTimeStamp = transactionTimeStamp;
        }

        public VoidResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public VoidResponse(String error, String errormsg, String voidedAmount, String transactionID, String transactionTimeStamp) {
        this.voidResponse = new VoidResponse_(error, errormsg, voidedAmount, transactionID, transactionTimeStamp);
    }

    public VoidResponse(String error, String errormsg) {
        this.voidResponse = new VoidResponse_(error, errormsg);
    }

}
