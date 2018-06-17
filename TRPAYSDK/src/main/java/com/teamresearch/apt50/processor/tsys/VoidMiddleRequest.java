package com.teamresearch.apt50.processor.tsys;


public class VoidMiddleRequest {

    public Voids Void;

    public class Voids {
        public String deviceID;
        public String transactionKey;
        public String transactionID;

        Voids(String deviceID, String transactionKey, String transactionID) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.transactionID = transactionID;
        }
    }

    VoidMiddleRequest(String deviceID, String transactionKey, String transactionID) {
        this.Void = new Voids(deviceID, transactionKey, transactionID);
    }

    public String getdeviceID() {
        return this.Void.deviceID;
    }

    public String gettransactionKey() {
        return this.Void.transactionKey;
    }

    public String gettransactionID() {
        return this.Void.transactionID;
    }
}
