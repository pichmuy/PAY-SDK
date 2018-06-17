package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class CaptureRequest implements ModelSubConst {

    @SerializedName(CAPTURE)
    public Capture capture;

    public class Capture {
        @SerializedName(AMOUNT)
        public String amount;
        @SerializedName(TRANSACTION_ID)
        public String transactionID;

        public Capture(String amount, String transactionID) {
            this.amount = amount;
            this.transactionID = transactionID;
        }
    }

    public CaptureRequest() {

    }

    public CaptureRequest(String amount, String transactionID) {
        this.capture = new Capture(amount, transactionID);
    }

    public String getAmount() {
        this.capture.amount = (this.capture.amount == null ? "0" : this.capture.amount);
        return this.capture.amount;
    }
}
