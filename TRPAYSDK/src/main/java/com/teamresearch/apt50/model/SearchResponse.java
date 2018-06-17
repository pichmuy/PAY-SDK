package com.teamresearch.apt50.model;

import com.teamresearch.apt50.database.Transaction;

import java.util.ArrayList;

public class SearchResponse {

    public SearchResponse_ searchResponse;

    public class SearchResponse_ {
        public String error;
        public String errorMsg;
        public ArrayList<Transaction> response;

        SearchResponse_(String error, String errormsg, ArrayList<Transaction> response) {
            this.error = error;
            this.errorMsg = errormsg;
            this.response = response;
        }

        SearchResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }
    }

    public SearchResponse(String error, String errormsg, ArrayList<Transaction> response) {
        this.searchResponse = new SearchResponse_(error, errormsg, response);
    }

    public SearchResponse() {

    }

    public SearchResponse(String error, String errorMsg) {
        this.searchResponse = new SearchResponse_(error, errorMsg);
    }
}
