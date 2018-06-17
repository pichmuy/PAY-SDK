package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class StatusRequest {

    @SerializedName(ModelSubConst.REQUEST)
    public Request request;

    public class Request {
        @SerializedName(ModelSubConst.REF)
        public int ref;

        public Request(int ref) {
            this.ref = ref;
        }
    }

    public StatusRequest(int ref) {
        this.request = new Request(ref);
    }

    public StatusRequest() {

    }
}
