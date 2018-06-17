package com.teamresearch.apt50.processor.tsys;

import com.google.gson.annotations.SerializedName;
import com.teamresearch.apt50.utils.Const;

public class CaptureMiddleRequest {

    private Capture Capture;

    public class Capture {

        @SerializedName(Const.DEVICE_ID)
        public String deviceID;
        @SerializedName(Const.TRANSACTION_KEY)
        public String transactionKey;
        @SerializedName(Const.TRANSACTION_AMOUNT)
        public String transactionAmount;
        @SerializedName(Const.TIP)
        public String tip;
        public String transactionID;
        String orderNotes;

        Capture(String deviceID, String transactionKey, String transactionAmount,
                String tip, String transactionID, String orderNotes) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.transactionAmount = transactionAmount;
            this.tip = tip;
            this.transactionID = transactionID;
            this.orderNotes = orderNotes;
        }
    }

    CaptureMiddleRequest(String deviceID, String transactionKey, String transactionAmount,
                         String tip, String transactionID, String orderNotes) {
        this.Capture = new Capture(deviceID, transactionKey, transactionAmount, tip, transactionID, orderNotes);
    }

    public String getDeviceID() {
        return this.Capture.deviceID;
    }

    public String getTransactionKey() {
        return this.Capture.transactionKey;
    }

    public String getTransactionAmount() {
        return this.Capture.transactionAmount;
    }

    public String getTip() {
        return this.Capture.tip;
    }

    public String getTransactionID() {
        return this.Capture.transactionID;
    }

    public String getOrderNotes() {
        return this.Capture.orderNotes;
    }
}
