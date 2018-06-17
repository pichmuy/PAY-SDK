package com.teamresearch.apt50.processor.tsys;

public class CaptureMiddleResponse {

    public CaptureResponse CaptureResponse;

    public class CaptureResponse {

        public String status;
        String responseCode;
        String responseMessage;
        String taskID;
        public String transactionID;
        String transactionTimestamp;
        public String transactionAmount;
        String totalAmount;
        public String tip;
        String customerReceipt;
        String merchantReceipt;
    }

    public String getStatus() {
        return this.CaptureResponse.status;
    }

    public String getResponseCode() {
        return this.CaptureResponse.responseCode;
    }

    public String getResponseMessage() {
        return this.CaptureResponse.responseMessage;
    }

    public String getTaskID() {
        return this.CaptureResponse.taskID;
    }

    public String getTransactionID() {
        return this.CaptureResponse.transactionID;
    }

    public String getTransactionTimestamp() {
        return this.CaptureResponse.transactionTimestamp;
    }

    public String getTransactionAmount() {
        return this.CaptureResponse.transactionAmount;
    }

    public String getTotalAmount() {
        return this.CaptureResponse.totalAmount;
    }

    public String getTip() {
        this.CaptureResponse.tip = (this.CaptureResponse.tip == null ? "0" : this.CaptureResponse.tip);
        return this.CaptureResponse.tip;
    }

    public String getCustomerReceipt() {
        return this.CaptureResponse.customerReceipt;
    }

    public String getMerchantReceipt() {
        return this.CaptureResponse.merchantReceipt;
    }

}
