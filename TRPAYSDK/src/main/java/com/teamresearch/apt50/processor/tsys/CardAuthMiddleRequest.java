package com.teamresearch.apt50.processor.tsys;

import com.google.gson.annotations.SerializedName;
import com.teamresearch.apt50.utils.Const;

public class CardAuthMiddleRequest {
    @SerializedName(Const.CARDAUTHENTICATION)
    private CardAuthentication CardAuth;

    public class CardAuthentication {
        @SerializedName(Const.DEVICE_ID)
        public String deviceID;
        @SerializedName(Const.TRANSACTION_KEY)
        public String transactionKey;
        @SerializedName(Const.CARD_DATA_SOURCE)
        String cardDataSource;
        @SerializedName(Const.CARD_NUMBER)
        String cardNumber;
        @SerializedName(Const.EXPIRATION_DATE)
        public String expirationDate;
        @SerializedName(Const.CVV2)
        public String cvv2;
        @SerializedName(Const.CARD_HOLDER_NAME)
        public String cardHolderName;
        @SerializedName(Const.ADDRESS_LINE_1)
        String addressLine1;
        @SerializedName(Const.ZIP)
        String zip;
        @SerializedName(Const.ORDER_NUMBER)
        String orderNumber;
        @SerializedName(Const.FIRST_NAME)
        public String firstName;
        @SerializedName(Const.LAST_NAME)
        public String lastName;
        @SerializedName(Const.TERMINAL_CAPABILITY)
        String terminalCapability;
        @SerializedName(Const.TERMINAL_OPERATING_ENVIRONMENT)
        String terminalOperatingEnvironment;
        @SerializedName(Const.CARDHOLDER_AUTHENTICATION_METHOD)
        String cardholderAuthenticationMethod;
        @SerializedName(Const.TERMINAL_AUTHENTICATION_CAPABILITY)
        String terminalAuthenticationCapability;
        @SerializedName(Const.TERMINAL_OUTPUT_CAPABILITY)
        String terminalOutputCapability;
        @SerializedName(Const.MAX_PIN_LENGTH)
        String maxPinLength;
        @SerializedName(Const.DEVELOPER_ID)
        String developerID;


        CardAuthentication(String deviceID, String transactionKey, String cardDataSource, String cardNumber,String expirationDate, String cvv2,
                           String cardHolderName,  String addressLine1, String zip, String orderNumber, String firstName, String lastName,
                           String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                           String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength,String developerID) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.cardDataSource = cardDataSource;
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.cvv2 = cvv2;
            this.cardHolderName = cardHolderName;
            this.addressLine1 = addressLine1;
            this.zip = zip;
            this.orderNumber = orderNumber;
            this.firstName = firstName;
            this.lastName = lastName;
            this.terminalCapability = terminalCapability;
            this.terminalOperatingEnvironment = terminalOperatingEnvironment;
            this.cardholderAuthenticationMethod = cardholderAuthenticationMethod;
            this.terminalAuthenticationCapability = terminalAuthenticationCapability;
            this.terminalOutputCapability = terminalOutputCapability;
            this.maxPinLength = maxPinLength;
            this.developerID = developerID;
        }

    }

    public CardAuthMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String cardNumber,String expirationDate, String cvv2,
                                 String cardHolderName,  String addressLine1, String zip, String orderNumber, String firstName, String lastName,
                                 String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                                 String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength,String developerID) {

        this.CardAuth = new CardAuthentication(deviceID, transactionKey, cardDataSource, cardNumber, expirationDate, cvv2, cardHolderName,
                addressLine1, zip, orderNumber, firstName, lastName, terminalCapability, terminalOperatingEnvironment, cardholderAuthenticationMethod,
                terminalAuthenticationCapability, terminalOutputCapability, maxPinLength, developerID);
    }

    public CardAuthentication getCardAuth() {
        return this.CardAuth;
    }

    public String getDeviceID() {
        return this.CardAuth.deviceID;
    }

    public String getTransactionKey() {
        return this.CardAuth.transactionKey;
    }

    public String getCardDataSource() {
        return this.CardAuth.cardDataSource;
    }

    public String getCardNumber() {
        return this.CardAuth.cardNumber;
    }

    public String getExpirationDate() {
        return this.CardAuth.expirationDate;
    }

    public String getCvv2() {
        return this.CardAuth.cvv2;
    }

    public String getCardHolderName()   { return this.CardAuth.cardHolderName;}

    public String getAddressLine1() {
        return this.CardAuth.addressLine1;
    }

    public String getZip() {
        return this.CardAuth.zip;
    }

    public String getOrderNumber() {
        return this.CardAuth.orderNumber;
    }

    public String getFirstName() {
        return this.CardAuth.firstName;
    }

    public String getLastName() {
        return this.CardAuth.lastName;
    }

    public String getTerminalCapability() {
        return this.CardAuth.terminalCapability;
    }

    public String getTerminalOperatingEnvironment() {
        return this.CardAuth.terminalOperatingEnvironment;
    }

    public String getCardholderAuthenticationMethod() {
        return this.CardAuth.cardholderAuthenticationMethod;
    }

    public String getTerminalAuthenticationCapability() {
        return this.CardAuth.terminalAuthenticationCapability;
    }

    public String getTerminalOutputCapability() {
        return this.CardAuth.terminalOutputCapability;
    }

    public String getMaxPinLength() {
        return this.CardAuth.maxPinLength;
    }

    public String getDeveloperID()  { return this.CardAuth.developerID;}
}
