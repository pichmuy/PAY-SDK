package com.teamresearch.apt50.processor.tsys;

import com.google.gson.annotations.SerializedName;
import com.teamresearch.apt50.utils.Const;

public class ForcedAuthMiddleRequest {
    private ForcedAuth_ ForcedAuth;

    public class ForcedAuth_ {

        @SerializedName(Const.DEVICE_ID)
        public String deviceID;
        @SerializedName(Const.TRANSACTION_KEY)
        public String transactionKey;
        @SerializedName(Const.CARD_DATA_SOURCE)
        String cardDataSource;
        @SerializedName(Const.TRANSACTION_AMOUNT)
        public String transactionAmount;
        @SerializedName(Const.CARD_NUMBER)
        String cardNumber;
        @SerializedName(Const.EXPIRATION_DATE)
        public String expirationDate;
        public String authTimestamp;
        public String authCode;
        public String developerID;


        ForcedAuth_(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String cardNumber,
                 String expirationDate, String authTimestamp, String authCode, String developerID) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.cardDataSource = cardDataSource;
            this.transactionAmount = transactionAmount;
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.authTimestamp = authTimestamp;
            this.authCode = authCode;
            this.developerID = developerID;
        }
    }

    ForcedAuthMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String cardNumber,
                            String expirationDate, String authTimestamp, String authCode, String developerID) {
        this.ForcedAuth = new ForcedAuth_(deviceID, transactionKey, cardDataSource,transactionAmount,cardNumber,expirationDate,
                                authTimestamp,authCode,developerID);
    }

    public String getDeviceID() {
        return this.ForcedAuth.deviceID;
    }

    public String getTransactionKey() {
        return this.ForcedAuth.transactionKey;
    }

    public String getCardDataSource() { return  this.ForcedAuth.cardDataSource;}

    public String getTransactionAmount() {
        return this.ForcedAuth.transactionAmount;
    }

    public String getCardNumber()       {return  this.ForcedAuth.cardNumber;}

    public String getExpirationDate()   {return this.ForcedAuth.expirationDate;}

    public String getAuthTimeStamp()    {return this.ForcedAuth.authTimestamp;}

    public String getAuthCode()         {return this.ForcedAuth.authCode;}

    public String getDeveloperID()      {return this.ForcedAuth.developerID;}
}
