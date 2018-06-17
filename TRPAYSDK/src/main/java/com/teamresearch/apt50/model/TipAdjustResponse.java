package com.teamresearch.apt50.model;

public class TipAdjustResponse {

    public TipAdjustResponse_ tipAdjustResponse;

    public class TipAdjustResponse_ {

        public String error;
        public String errorMsg;
        public String totalAmount;
        public String tip;
        public String transactionID;
        public String transactionTimeStamp;

        public TipAdjustResponse_(String error, String errorMsg, String totalAmount, String tip, String transactionID,
                                  String transactionTimeStamp) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.totalAmount = totalAmount;
            this.tip = tip;
            this.transactionID = transactionID;
            this.transactionTimeStamp = transactionTimeStamp;
        }

        public TipAdjustResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public TipAdjustResponse(String error, String errorMsg, String totalAmount, String tip, String transactionID,
                             String transactionTimeStamp) {
        this.tipAdjustResponse = new TipAdjustResponse_(error, errorMsg, totalAmount, tip, transactionID, transactionTimeStamp);
    }

    public TipAdjustResponse(String error, String errorMsg) {
        this.tipAdjustResponse = new TipAdjustResponse_(error, errorMsg);
    }
}
