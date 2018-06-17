package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;
import com.teamresearch.apt50.utils.Const;

public class AuthorizeRequest implements ModelSubConst {

    @SerializedName(Const.AUTHORIZE)
    public Authorize Authorize;

    public class Authorize {
        @SerializedName(TIP)
        public String tip;
        @SerializedName(ADDRESS_LINE_1)
        public String addressLine1;
        @SerializedName(AMOUNT)
        public String amount;
        @SerializedName(CARD_DATA_SOURCE)
        public String cardDataSource;
        @SerializedName(CITY)
        public String city;
        @SerializedName(CARD_NUMBER)
        public String cardNumber;
        @SerializedName(CVV2)
        public String cvv2;
        @SerializedName(CARD_HOLDER_NAME)
        public String cardHolderName;
        @SerializedName(EXPIRATION_DATE)
        public String expirationDate;
        @SerializedName(KSN)
        public String ksn;
        @SerializedName(TOKEN_REQUIRED)
        public String tokenRequired;
        @SerializedName(ORDER_ID)
        public String orderID;
        @SerializedName(STATE)
        public String state;
        @SerializedName(ZIP)
        public String zip;
        @SerializedName(TRACK1DATA)
        public String track1Data;
        @SerializedName(TRACK2DATA)
        public String track2Data;
        @SerializedName(TRACK3DATA)
        public String track3Data;
        public int qty;
        public String subtotal;
        public String tax;
        public String print;

        public Authorize() {
            this.tip = "";
            this.addressLine1 = "";
            this.amount = "";
            this.city = "";
            this.cardNumber = "";
            this.cvv2 = "";
            this.cardHolderName = "";
            this.expirationDate = "";
            this.ksn = "";
            this.tokenRequired = "";
            this.orderID = "";
            this.state = "";
            this.zip = "";
            this.track1Data = "";
            this.track2Data = "";
            this.track3Data = "";
            this.qty = 0;
            this.subtotal = "";
            this.tax = "";
            this.print = "Y";
        }

        public Authorize(String tip, String addressLine1, String amount, String city, String cardNumber, String cvv2,
                         String cardHolderName, String expirationDate, String ksn, String tokenRequired, String orderID,
                         String state, String zip, String track2Data, int qty, String subtotal, String tax) {
            this.tip = tip;
            this.addressLine1 = addressLine1;
            this.amount = amount;
            this.city = city;
            this.cardNumber = cardNumber;
            this.cvv2 = cvv2;
            this.cardHolderName = cardHolderName;
            this.expirationDate = expirationDate;
            this.ksn = ksn;
            this.tokenRequired = tokenRequired;
            this.orderID = orderID;
            this.state = state;
            this.zip = zip;
            this.track1Data = "";
            this.track2Data = track2Data;
            this.track3Data = "";
            this.qty = qty;
            this.subtotal = subtotal;
            this.tax = tax;
        }
    }

    public AuthorizeRequest() {
        this.Authorize = new Authorize();
    }

    public AuthorizeRequest(String tip, String addressLine1, String amount, String city, String cardNumber, String cvv2,
                            String cardHolderName, String expirationDate, String ksn, String tokenRequired, String orderID,
                            String state, String zip, String track2Data, int qty, String subtotal, String tax) {
        this.Authorize = new Authorize(tip, addressLine1, amount, city, cardNumber, cvv2, cardHolderName,
                expirationDate, ksn, tokenRequired, orderID, state, zip, track2Data, qty, subtotal, tax);
    }

    public String getCardDataSource() {
        this.Authorize.cardDataSource = (this.Authorize.cardDataSource == null ? "NULL" : this.Authorize.cardDataSource);
        return this.Authorize.cardDataSource;
    }

    public String getTip() {
        this.Authorize.tip = (this.Authorize.tip == null ? "0" : this.Authorize.tip);
        return this.Authorize.tip;
    }

    public String getAmount() {
        this.Authorize.amount = (this.Authorize.amount == null ? "0.00" : this.Authorize.amount);
        return this.Authorize.amount;
    }

    public String getSubtotal() {
        this.Authorize.subtotal = (this.Authorize.subtotal == null ? "0.00" : this.Authorize.subtotal);
        return this.Authorize.subtotal;
    }

    public String getTax() {
        this.Authorize.tax = (this.Authorize.tax == null ? "0.00" : this.Authorize.tax);
        return this.Authorize.tax;
    }

    public String getPrint() {
        this.Authorize.print = (this.Authorize.print == null ? "Y" : this.Authorize.print);
        return this.Authorize.print;
    }

    public String getQty() {
        return String.valueOf(this.Authorize.qty);
    }

}
