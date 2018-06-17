package com.teamresearch.apt50.processor.tsys;

public class ReturnMiddleRequest {

    private ReturnRequest Return;

    class ReturnRequest {

        public String deviceID;
        public String transactionKey;
        String cardDataSource;
        public String transactionAmount;
        String currencyCode;
        String orderNumber;
        String cardNumber;
        public String expirationDate;
        public String cvv2;
        String terminalCapability;
        String terminalOperatingEnvironment;
        String cardholderAuthenticationMethod;
        String terminalAuthenticationCapability;
        String terminalOutputCapability;
        String maxPinLength;
        String developerID;
        String securityProtocol;
        String ucafCollectionIndicator;
        String tokenRequired;
        String track2Data;
        String transactionID;

        ReturnRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String cardNumber,
                      String expirationDate, String cvv2, String terminalCapability, String terminalOperatingEnvironment,
                      String cardholderAuthenticationMethod, String terminalAuthenticationCapability, String terminalOutputCapability,
                      String maxPinLength, String developerID) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.cardDataSource = cardDataSource;
            this.transactionAmount = transactionAmount;
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.cvv2 = cvv2;
            this.terminalCapability = terminalCapability;
            this.terminalOperatingEnvironment = terminalOperatingEnvironment;
            this.cardholderAuthenticationMethod = cardholderAuthenticationMethod;
            this.terminalAuthenticationCapability = terminalAuthenticationCapability;
            this.terminalOutputCapability = terminalOutputCapability;
            this.maxPinLength = maxPinLength;
            this.developerID = developerID;
        }

        ReturnRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String currencyCode,
                      String track2Data, String securityProtocol, String ucafCollectionIndicator, String orderNumber, String tokenRequired,
                      String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                      String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.cardDataSource = cardDataSource;
            this.transactionAmount = transactionAmount;
            this.currencyCode = currencyCode;
            this.track2Data = track2Data;
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

        ReturnRequest(String deviceID, String transactionKey, String transactionAmount, String transactionID) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.transactionAmount = transactionAmount;
            this.transactionID = transactionID;
        }
    }

    ReturnMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String cardNumber,
                        String expirationDate, String cvv2, String terminalCapability, String terminalOperatingEnvironment,
                        String cardholderAuthenticationMethod, String terminalAuthenticationCapability, String terminalOutputCapability,
                        String maxPinLength, String developerID) {
        this.Return = new ReturnRequest(deviceID, transactionKey, cardDataSource, transactionAmount, cardNumber,
                expirationDate, cvv2, terminalCapability, terminalOperatingEnvironment, cardholderAuthenticationMethod,
                terminalAuthenticationCapability, terminalOutputCapability, maxPinLength, developerID);
    }

    ReturnMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String currencyCode,
                        String track2Data, String securityProtocol, String ucafCollectionIndicator, String orderNumber, String tokenRequired,
                        String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                        String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength) {

        this.Return = new ReturnRequest(deviceID, transactionKey, cardDataSource, transactionAmount, currencyCode, track2Data, securityProtocol,
                ucafCollectionIndicator, orderNumber, tokenRequired, terminalCapability, terminalOperatingEnvironment,
                cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
    }

    ReturnMiddleRequest(String deviceID, String transactionKey, String transactionAmount, String transactionID) {
        this.Return = new ReturnRequest(deviceID, transactionKey, transactionAmount, transactionID);
    }

    public String getDeviceID() {
        return this.Return.deviceID;
    }

    public String getTransactionKey() {
        return this.Return.transactionKey;
    }

    public String getCardDataSource() {
        return this.Return.cardDataSource;
    }

    public String getTransactionAmount() {
        return this.Return.transactionAmount;
    }

    public String getOrderNumber() {
        return this.Return.orderNumber;
    }

    public String getCurrencyCode() {
        return this.Return.currencyCode;
    }

    public String getCardNumber() {
        return this.Return.cardNumber;
    }

    public String getExpirationDate() {
        return this.Return.expirationDate;
    }

    public String getCvv2() {
        return this.Return.cvv2;
    }

    public String getTerminalCapability() {
        return this.Return.terminalCapability;
    }

    public String getTerminalOperatingEnvironment() {
        return this.Return.terminalOperatingEnvironment;
    }

    public String getTerminalOutputCapability() {
        return this.Return.terminalOutputCapability;
    }

    public String getCardholderAuthenticationMethod() {
        return this.Return.cardholderAuthenticationMethod;
    }

    public String getTerminalAuthenticationCapability() {
        return this.Return.terminalAuthenticationCapability;
    }

    public String getMaxPinLength() {
        return this.Return.maxPinLength;
    }

    public String getDeveloperID() {
        return this.Return.developerID;
    }

    public String getTrack2Data() {
        return this.Return.track2Data;
    }

    public String getSecurityProtocol() {
        return this.Return.securityProtocol;
    }

    public String getUcafCollectionIndicator() {
        return this.Return.ucafCollectionIndicator;
    }

    public String getTokenRequired() {
        return this.Return.tokenRequired;
    }

    public String getTransactionID() {
        return this.Return.transactionID;
    }

}
