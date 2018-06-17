package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class PrintRequest {

    @SerializedName(ModelSubConst.PRINT)
    public Print print;

    public class Print {
        @SerializedName(ModelSubConst.REF)
        public int ref;

        public Print(int ref) {
            this.ref = ref;
        }
    }

    public PrintRequest(int ref) {
        this.print = new Print(ref);
    }

    public PrintRequest() {

    }
}
