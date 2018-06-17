package com.teamresearch.apt50.processor.tsys;


public class ReportMiddleRequest {

    private GetReportData GetReportData;

    public class GetReportData {
        public String deviceID;
        public String transactionKey;
        String reportName;

        GetReportData(String deviceID, String transactionKey, String reportName) {
            this.deviceID = deviceID;
            this.transactionKey = transactionKey;
            this.reportName = reportName;
        }
    }

    ReportMiddleRequest(String deviceID, String transactionKey, String reportName) {
        this.GetReportData = new GetReportData(deviceID, transactionKey, reportName);
    }

    public String getDeviceID() {
        return this.GetReportData.deviceID;
    }

    public String getTransactionKey() {
        return this.GetReportData.transactionKey;
    }

    public String getReportName() {
        return this.GetReportData.reportName;
    }
}
