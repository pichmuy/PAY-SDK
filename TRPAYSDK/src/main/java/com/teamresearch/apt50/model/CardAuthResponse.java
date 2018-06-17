package com.teamresearch.apt50.model;

public class CardAuthResponse {

    public CardAuthResponse_ cardAuthResponse;
    public class CardAuthResponse_
    {
        public String error;
        public String errorMsg;

        public CardAuthResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public CardAuthResponse(String error, String errorMsg) {
        this.cardAuthResponse = new CardAuthResponse_(error, errorMsg);
    }
}
