package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class SaleRequest implements ModelSubConst {

    @SerializedName(SALE)
    public Sale sale;

    public class Sale {
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

        public Sale() {
            this.tip = "";
            this.addressLine1 = "";
            this.amount = "";
            this.cardDataSource = "";
            this.city = "";
            this.cardNumber = "";
            this.cvv2 = "";
            this.cardDataSource = "";
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
            this.subtotal = "0.00";
            this.tax = "0.00";
            this.print = "Y";
        }

        public Sale(String tip, String addressLine1, String amount,
                    String cardDataSource, String city, String cardNumber,
                    String cvv2, String cardHolderName, String expirationDate, String ksn,
                    String tokenRequired, String orderID,
                    String state, String zip, String track2Data,
                    int qty, String subtotal, String tax) {
            this.tip = tip;
            this.addressLine1 = addressLine1;
            this.amount = amount;
            this.cardDataSource = cardDataSource;
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
            this.track2Data = track2Data;
            this.qty = qty;
            this.subtotal = subtotal;
            this.tax = tax;
        }
    }

    public SaleRequest(String tip, String addressLine1, String amount, String cardDataSource, String city, String cardNumber,
                       String cvv2, String cardHolderName, String expirationDate, String ksn, String tokenRequired, String orderID,
                       String state, String zip, String track2Data, int qty, String subtotal, String tax) {
        this.sale = new Sale(tip, addressLine1, amount, cardDataSource, city, cardNumber, cvv2, cardHolderName, expirationDate, ksn,
                tokenRequired, orderID, state, zip, track2Data, qty, subtotal, tax);
    }

    public SaleRequest() {
        this.sale = new Sale();
    }

    public String getCardDataSource() {
        this.sale.cardDataSource = (this.sale.cardDataSource == null ? "NULL" : this.sale.cardDataSource);
        return this.sale.cardDataSource;
    }

    public String getTip() {
        this.sale.tip = (this.sale.tip == null ? "0.00" : this.sale.tip);
        return this.sale.tip;
    }

    public String getAmount() {
        this.sale.amount = (this.sale.amount == null ? "0.00" : this.sale.amount);
        return this.sale.amount;
    }

    public String getPrint() {
        this.sale.print = (this.sale.print == null ? "Y" : this.sale.print);
        return this.sale.print;
    }
}
