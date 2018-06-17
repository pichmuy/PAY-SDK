package com.teamresearch.apt50.model;

public class ForcedAuthResponse {

    public ForcedAuthResponse_ forcedAuthResponse;
    public class ForcedAuthResponse_
    {
        public String error;
        public String errorMsg;
        public String status;
        public String authCode;
        public String taskID;
        public String transactionID;
        public String transactionTimestamp;
        public String transactionAmount;
        public String cardType;
        public String maskedCardNumber;

        public ForcedAuthResponse_(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
        }

        public ForcedAuthResponse_(String error, String errorMsg, String status, String authCode, String taskID,
                                   String transactionID, String transactionTimestamp, String transactionAmount,
                                   String cardType, String maskedCardNumber)
        {
            this.error = error;
            this.errorMsg = errorMsg;
            this.status = status;
            this.authCode = authCode;
            this.taskID = taskID;
            this.transactionID = transactionID;
            this.transactionTimestamp = transactionTimestamp;
            this.cardType = cardType;
            this.maskedCardNumber = maskedCardNumber;
        }
    }

    public ForcedAuthResponse(String error, String errorMsg) {
        this.forcedAuthResponse = new ForcedAuthResponse_(error, errorMsg);
    }

    public ForcedAuthResponse(String error, String errorMsg, String status, String authCode, String taskID,
                              String transactionID, String transactionTimestamp, String transactionAmount,
                              String cardType, String maskedCardNumber)
    {
        this.forcedAuthResponse = new ForcedAuthResponse_(error,errorMsg,status,authCode,taskID,transactionID,
                                    transactionTimestamp,transactionAmount,cardType,maskedCardNumber);
    }
}
