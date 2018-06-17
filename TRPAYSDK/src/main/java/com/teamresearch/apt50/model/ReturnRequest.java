package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class ReturnRequest implements ModelSubConst {

    @SerializedName(RETURN)
    public Return return_;

    public class Return {
        @SerializedName(AMOUNT)
        public String amount;
        @SerializedName(CARD_DATA_SOURCE)
        public String cardDataSource;
        @SerializedName(KSN)
        public String ksn;
        @SerializedName(CARD_NUMBER)
        public String cardNumber;
        @SerializedName(FIRST_NAME)
        public String firstName;
        @SerializedName(LAST_NAME)
        public String lastName;
        @SerializedName(EXPIRATION_DATE)
        public String expirationDate;
        @SerializedName(CVV2)
        public String cvv2;
        @SerializedName(TRACK1DATA)
        public String track1Data;
        @SerializedName(TRACK2DATA)
        public String track2Data;
        @SerializedName(TRACK3DATA)
        public String track3Data;
        @SerializedName(TRANSACTION_ID)
        public String transactionID;

        public Return() {
            this.amount = "";
            this.cardDataSource = "";
            this.ksn = "";
            this.cardNumber = "";
            this.firstName = "";
            this.lastName = "";
            this.track1Data = "";
            this.track2Data = "";
            this.track3Data = "";
            this.transactionID = "";
        }

        public Return(String amount, String cardDataSource, String ksn, String cardNumber, String firstName, String lastName,
                      String track2Data, String transactionID) {
            this.amount = amount;
            this.cardDataSource = cardDataSource;
            this.ksn = ksn;
            this.cardDataSource = cardNumber;
            this.firstName = firstName;
            this.lastName = lastName;
            this.track2Data = track2Data;
            this.transactionID = transactionID;
        }
    }

    public ReturnRequest(String amount, String cardDataSource, String ksn, String cardNumber, String firstName, String lastName,
                         String track2Data, String transactionID) {
        this.return_ = new Return(amount, cardDataSource, ksn, cardNumber, firstName, lastName, track2Data, transactionID);
    }

    public ReturnRequest() {
        this.return_ = new Return();
    }

    public String getCardDataSource() {
        this.return_.cardDataSource = (this.return_.cardDataSource == null ? "NULL" : this.return_.cardDataSource);
        return this.return_.cardDataSource;
    }

    public String getAmount() {
        this.return_.amount = (this.return_.amount == null ? "0.00" : this.return_.amount);
        return this.return_.amount;
    }

}
