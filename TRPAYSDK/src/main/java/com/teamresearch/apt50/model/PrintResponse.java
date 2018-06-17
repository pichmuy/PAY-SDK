package com.teamresearch.apt50.model;

public class PrintResponse {

    public Printresponse printResponse;

    public class Printresponse {

        public String error;
        public String errorMsg;

        public Printresponse(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public PrintResponse(String error, String errormsg) {
        this.printResponse = new Printresponse(error, errormsg);
    }

    public PrintResponse() {

    }
}
