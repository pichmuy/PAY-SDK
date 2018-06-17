package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class ForcedAuthRequest implements ModelSubConst {

    @SerializedName(FORCEDAUTH)
    public  ForcedAuth forcedAuth;
    public class ForcedAuth
    {
        @SerializedName(AMOUNT)
        public String amount;
        @SerializedName(CARD_DATA_SOURCE)
        public String cardDataSource;
        @SerializedName(CARD_NUMBER)
        public String cardNumber;
        @SerializedName(EXPIRATION_DATE)
        public String expirationDate;
        @SerializedName(AUTHCODE)
        public String authCode;
        @SerializedName(AUTHTIMESTAMP)
        public String authTimeStamp;
        public String developerID;
    }

}
