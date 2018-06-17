package com.teamresearch.apt50.processor.tsys;

import com.google.gson.annotations.SerializedName;
import com.teamresearch.apt50.utils.Const;


public class SaleMiddleRequest {

    @SerializedName(Const.SALE)
    private Sale sale;

    class Sale {
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
        String firstName;
        @SerializedName(Const.LAST_NAME)
        String lastName;
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

        Sale(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
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

        Sale(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
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

    SaleMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
                      String cardNumber, String expirationDate, String cvv2, String addressLine1, String zip, String orderNumber, String firstName,
                      String lastName, String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                      String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {

        this.sale = new Sale(deviceID, transactionKey, cardDataSource, transactionAmount, tip, currencyCode, cardNumber, expirationDate, cvv2,
                addressLine1, zip, orderNumber, firstName, lastName, terminalCapability, terminalOperatingEnvironment, cardholderAuthenticationMethod,
                terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
    }

    SaleMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
                      String track2Data, String track1Data, String securityProtocol, String ucafCollectionIndicator, String orderNumber, String tokenRequired,
                      String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                      String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {

        this.sale = new Sale(deviceID, transactionKey, cardDataSource, transactionAmount, tip, currencyCode, track2Data, track1Data, securityProtocol,
                ucafCollectionIndicator, orderNumber, tokenRequired, terminalCapability, terminalOperatingEnvironment,
                cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
    }

    public Sale getAuth() {
        return this.sale;
    }

    public String getDeviceID() {
        return this.sale.deviceID;
    }

    public String getTransactionKey() {
        return this.sale.transactionKey;
    }

    public String getCardDataSource() {
        return this.sale.cardDataSource;
    }

    public String getTransactionAmount() {
        return this.sale.transactionAmount;
    }

    public String getTip() {
        return this.sale.tip;
    }

    public String getCurrencyCode() {
        return this.sale.currencyCode;
    }

    public String getCardNumber() {
        return this.sale.cardNumber;
    }

    public String getExpirationDate() {
        return this.sale.expirationDate;
    }

    public String getCvv2() {
        return this.sale.cvv2;
    }

    public String getAddressLine1() {
        return this.sale.addressLine1;
    }

    public String getZip() {
        return this.sale.zip;
    }

    public String getOrderNumber() {
        return this.sale.orderNumber;
    }

    public String getFirstName() {
        return this.sale.firstName;
    }

    public String getLastName() {
        return this.sale.lastName;
    }

    public String getTerminalCapability() {
        return this.sale.terminalCapability;
    }

    public String getTerminalOperatingEnvironment() {
        return this.sale.terminalOperatingEnvironment;
    }

    public String getCardholderAuthenticationMethod() {
        return this.sale.cardholderAuthenticationMethod;
    }

    public String getTerminalAuthenticationCapability() {
        return this.sale.terminalAuthenticationCapability;
    }

    public String getTerminalOutputCapability() {
        return this.sale.terminalOutputCapability;
    }

    public String getMaxPinLength() {
        return this.sale.maxPinLength;
    }

    public String getTrack2Data() {
        return this.sale.track2Data;
    }

    public String getTrack1Data() {
        return this.sale.track1Data;
    }

    public String getSecurityProtocol() {
        return this.sale.securityProtocol;
    }

    public String getUcafCollectionIndicator() {
        return this.sale.ucafCollectionIndicator;
    }

    public String getTokenRequired() {
        return this.sale.tokenRequired;
    }
}
