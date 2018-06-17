package com.teamresearch.apt50.processor.tsys;

public class TipAdjustMiddleRequest {

    public TipAdjustment TipAdjustment;

    public class TipAdjustment {

        public String deviceID;
        public String transactionKey;
        public String tip;
        public String transactionID;
        String developerID;

        TipAdjustment(String deviceID, String transactionKey, String tip, String transactionID, String developerID) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.tip = tip;
            this.transactionID = transactionID;
            this.developerID = developerID;
        }
    }

    TipAdjustMiddleRequest(String deviceID, String transactionKey, String tip, String transactionID, String developerID) {
        this.TipAdjustment = new TipAdjustment(deviceID, transactionKey, tip, transactionID, developerID);
    }

    public String getDeviceID() {
        return this.TipAdjustment.deviceID;
    }

    public String getTransactionKey() {
        return this.TipAdjustment.transactionKey;
    }

    public String getTip() {
        this.TipAdjustment.tip = (this.TipAdjustment.tip == null ? "0" : this.TipAdjustment.tip);
        return this.TipAdjustment.tip;
    }

    public String getTransactionID() {
        return this.TipAdjustment.transactionID;
    }

    public String getDeveloperID() {
        return this.TipAdjustment.developerID;
    }
}
