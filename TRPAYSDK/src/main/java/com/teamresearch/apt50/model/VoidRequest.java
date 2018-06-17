package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class VoidRequest implements ModelSubConst {

    @SerializedName(VOID)
    public VoidParam void_;

    public class VoidParam {
        @SerializedName(AMOUNT)
        public String amount;
        @SerializedName(TRANSACTION_ID)
        public String transactionID;
        @SerializedName(REASON_CODE)
        public int reasonCode;

        public VoidParam(String amount, String transactionID, int reasonCode) {
            this.amount = amount;
            this.transactionID = transactionID;
            this.reasonCode = reasonCode;
        }
    }

    public VoidRequest(String amount, String transactionID, int reasonCode) {
        this.void_ = new VoidParam(amount, transactionID, reasonCode);
    }

    public VoidRequest() {

    }

    public String getAmount() {
        this.void_.amount = (this.void_.amount == null ? "0" : this.void_.amount);
        return this.void_.amount;
    }

}
