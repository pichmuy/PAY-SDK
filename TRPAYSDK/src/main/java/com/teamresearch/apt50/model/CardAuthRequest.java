package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class CardAuthRequest implements ModelSubConst{

    @SerializedName(CARDAUTHENTICATION)
    public CardAuth cardAuthRequest;
    public class CardAuth
    {
        @SerializedName(ADDRESS_LINE_1)
        public String addressLine1;
        @SerializedName(CARD_DATA_SOURCE)
        public String cardDataSource;
        @SerializedName(CARD_NUMBER)
        public String cardNumber;
        @SerializedName(CVV2)
        public String cvv2;
        @SerializedName(CARD_HOLDER_NAME)
        public String cardHolderName;
        @SerializedName(EXPIRATION_DATE)
        public String expirationDate;
        @SerializedName(ORDER_ID)
        public String orderNumber;
        @SerializedName(ZIP)
        public String zip;
    }
}
