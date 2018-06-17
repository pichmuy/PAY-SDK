package com.teamresearch.apt50.model;

public class KeyResponse {

    public GenerateKeyResponse GenerateKeyResponse;

    public class GenerateKeyResponse {
        public String status;
        public String responseCode;
        public String responseMessage;
        public String transactionKey;

        public GenerateKeyResponse(String status, String responseCode, String responseMessage) {
            this.status = status;
            this.responseCode = responseCode;
            this.responseMessage = responseMessage;
        }
    }

    public KeyResponse(String status, String responseCode, String responseMessage) {
        this.GenerateKeyResponse = new GenerateKeyResponse(status, responseCode, responseMessage);
    }

    public KeyResponse() {
    }
}
