package com.teamresearch.apt50.processor.tsys;

public class TipAdjustMiddleResponse {

    public TipAdjustResponse TipAdjustmentResponse;

    public class TipAdjustResponse {
        public String status;
        String responseCode;
        String responseMessage;
        String taskID;
        public String transactionID;
        String transactionTimestamp;
        String totalAmount;
        public String tip;
        String customerReceipt;
        String merchantReceipt;
    }

    public String getStatus() {
        return this.TipAdjustmentResponse.status;
    }

    public String getResponseCode() {
        return this.TipAdjustmentResponse.responseCode;
    }

    public String getResponseMessage() {
        return this.TipAdjustmentResponse.responseMessage;
    }

    public String getTaskID() {
        return this.TipAdjustmentResponse.taskID;
    }

    public String getTransactionID() {
        return this.TipAdjustmentResponse.transactionID;
    }

    public String getTransactionTimestamp() {
        return this.TipAdjustmentResponse.transactionTimestamp;
    }

    public String getTotalAmount() {
        return this.TipAdjustmentResponse.totalAmount;
    }

    public String getTip() {
        this.TipAdjustmentResponse.tip = (this.TipAdjustmentResponse.tip == null ? "0" : this.TipAdjustmentResponse.tip);
        return this.TipAdjustmentResponse.tip;
    }

    public String getCustomerReceipt() {
        return this.TipAdjustmentResponse.customerReceipt;
    }

    public String getMerchantReceipt() {
        return this.TipAdjustmentResponse.merchantReceipt;
    }

}
