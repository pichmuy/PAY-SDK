package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class TipAdjustRequest implements ModelSubConst {

    @SerializedName(TIP_ADJUST)
    public TipAdjust tipAdjust;

    public class TipAdjust {

        @SerializedName(TIP)
        public String tip;
        @SerializedName(TRANSACTION_ID)
        public String transactionID;

        public TipAdjust(String tip, String transactionID) {
            this.tip = tip;
            this.transactionID = transactionID;
        }
    }

    public TipAdjustRequest(String tip, String transactionID) {
        this.tipAdjust = new TipAdjust(tip, transactionID);
    }

    public TipAdjustRequest() {

    }
}
