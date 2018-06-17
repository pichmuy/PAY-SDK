package com.teamresearch.apt50.processor.tsys;

import com.google.gson.annotations.SerializedName;
import com.teamresearch.apt50.utils.Const;


public class AuthMiddleRequest {

    @SerializedName(Const.AUTH)
    private AuthMiddleRequest.Auth Auth;

    public class Auth {
        @SerializedName(Const.DEVICE_ID)
        public String deviceID;
        @SerializedName(Const.TRANSACTION_KEY)
        public String transactionKey;
        @SerializedName(Const.CARD_DATA_SOURCE)
        String cardDataSource;
        @SerializedName(Const.TRANSACTION_AMOUNT)
        public String transactionAmount;
        @SerializedName(Const.TIP)
        public String tip;
        @SerializedName(Const.CURRENCY_CODE)
        String currencyCode;
        @SerializedName(Const.CARD_NUMBER)
        String cardNumber;
        @SerializedName(Const.EXPIRATION_DATE)
        public String expirationDate;
        @SerializedName(Const.CVV2)
        public String cvv2;
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
        @SerializedName(Const.SECURITY_PROTOCOL)
        String securityProtocol;
        @SerializedName(Const.UCAF_COLLECTION_INDICATOR)
        String ucafCollectionIndicator;
        @SerializedName(Const.TOKEN_REQUIRED)
        String tokenRequired;
        @SerializedName(Const.TRACK2DATA)
        String track2Data;
        @SerializedName(Const.TRACK1DATA)
        String track1Data;


        Auth(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
             String cardNumber, String expirationDate, String cvv2, String addressLine1, String zip, String orderNumber, String firstName,
             String lastName, String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
             String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.cardDataSource = cardDataSource;
            this.transactionAmount = transactionAmount;
            this.tip = tip;
            this.currencyCode = currencyCode;
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.cvv2 = cvv2;
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

        }

        Auth(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
             String track2Data, String track1Data, String securityProtocol, String ucafCollectionIndicator, String orderNumber, String tokenRequired,
             String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
             String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.cardDataSource = cardDataSource;
            this.transactionAmount = transactionAmount;
            this.tip = tip;
            this.currencyCode = currencyCode;
            this.track2Data = track2Data;
            this.track1Data = track1Data;
            this.securityProtocol = securityProtocol;
            this.ucafCollectionIndicator = ucafCollectionIndicator;
            this.orderNumber = orderNumber;
            this.tokenRequired = tokenRequired;
            this.terminalCapability = terminalCapability;
            this.terminalOperatingEnvironment = terminalOperatingEnvironment;
            this.cardholderAuthenticationMethod = cardholderAuthenticationMethod;
            this.terminalAuthenticationCapability = terminalAuthenticationCapability;
            this.terminalOutputCapability = terminalOutputCapability;
            this.maxPinLength = maxPinLength;

        }

    }

    public AuthMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
                             String cardNumber, String expirationDate, String cvv2, String addressLine1, String zip, String orderNumber, String firstName,
                             String lastName, String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                             String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {

        this.Auth = new Auth(deviceID, transactionKey, cardDataSource, transactionAmount, tip, currencyCode, cardNumber, expirationDate, cvv2,
                addressLine1, zip, orderNumber, firstName, lastName, terminalCapability, terminalOperatingEnvironment, cardholderAuthenticationMethod,
                terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
    }

    public AuthMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
                             String track2Data, String track1Data, String securityProtocol, String ucafCollectionIndicator, String orderNumber, String tokenRequired,
                             String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                             String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {

        this.Auth = new Auth(deviceID, transactionKey, cardDataSource, transactionAmount, tip, currencyCode, track2Data, track1Data, securityProtocol,
                ucafCollectionIndicator, orderNumber, tokenRequired, terminalCapability, terminalOperatingEnvironment,
                cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
    }

    public AuthMiddleRequest.Auth getAuth() {
        return this.Auth;
    }

    public String getDeviceID() {
        return this.Auth.deviceID;
    }

    public String getTransactionKey() {
        return this.Auth.transactionKey;
    }

    public String getCardDataSource() {
        return this.Auth.cardDataSource;
    }

    public String getTransactionAmount() {
        return this.Auth.transactionAmount;
    }

    public String getTip() {
        return this.Auth.tip;
    }

    public String getCurrencyCode() {
        return this.Auth.currencyCode;
    }

    public String getCardNumber() {
        return this.Auth.cardNumber;
    }

    public String getExpirationDate() {
        return this.Auth.expirationDate;
    }

    public String getCvv2() {
        return this.Auth.cvv2;
    }

    public String getAddressLine1() {
        return this.Auth.addressLine1;
    }

    public String getZip() {
        return this.Auth.zip;
    }

    public String getOrderNumber() {
        return this.Auth.orderNumber;
    }

    public String getFirstName() {
        return this.Auth.firstName;
    }

    public String getLastName() {
        return this.Auth.lastName;
    }

    public String getTerminalCapability() {
        return this.Auth.terminalCapability;
    }

    public String getTerminalOperatingEnvironment() {
        return this.Auth.terminalOperatingEnvironment;
    }

    public String getCardholderAuthenticationMethod() {
        return this.Auth.cardholderAuthenticationMethod;
    }

    public String getTerminalAuthenticationCapability() {
        return this.Auth.terminalAuthenticationCapability;
    }

    public String getTerminalOutputCapability() {
        return this.Auth.terminalOutputCapability;
    }

    public String getMaxPinLength() {
        return this.Auth.maxPinLength;
    }

    public String getTrack1Data() {
        return this.Auth.track1Data;
    }

    public String getTrack2Data() {
        return this.Auth.track2Data;
    }

    public String getSecurityProtocol() {
        return this.Auth.securityProtocol;
    }

    public String getUcafCollectionIndicator() {
        return this.Auth.ucafCollectionIndicator;
    }

    public String getTokenRequired() {
        return this.Auth.tokenRequired;
    }
}
