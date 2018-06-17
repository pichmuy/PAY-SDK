package com.teamresearch.apt50.model;

public class CaptureResponse {

    public CaptureResponse_ captureResponse;

    public class CaptureResponse_ {
        public String error;
        public String errorMsg;
        public String transactionID;
        public String transactionTimeStamp;

        public CaptureResponse_(String error, String errorMsg, String transactionID, String transactionTimeStamp) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.transactionID = transactionID;
            this.transactionTimeStamp = transactionTimeStamp;
        }

        public CaptureResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public CaptureResponse(String error, String errorMsg, String transactionID, String transactionTimeStamp) {
        this.captureResponse = new CaptureResponse_(error, errorMsg, transactionID, transactionTimeStamp);
    }

    public CaptureResponse(String error, String errorMsg) {
        this.captureResponse = new CaptureResponse_(error, errorMsg);
    }

    public CaptureResponse() {

    }
}
