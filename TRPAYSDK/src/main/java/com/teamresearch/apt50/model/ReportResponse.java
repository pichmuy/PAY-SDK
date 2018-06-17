package com.teamresearch.apt50.model;

import com.teamresearch.apt50.processor.tsys.ReportMiddleResponse.ReportItem;

import java.util.ArrayList;

/**
 * Created by Me on 4/18/2018.
 */

public class ReportResponse {

    public GetReportDataresponse getReportDataResponse;

    public class GetReportDataresponse {
        public String error;
        public String errorMsg;
        public ArrayList<ReportItem> reportData;

        public GetReportDataresponse(String error, String errorMsg, ArrayList<ReportItem> reportData) {
            this.error = error;
            this.errorMsg = errorMsg;
            this.reportData = reportData;
        }
    }

    public ReportResponse(String error, String errorMsg, ArrayList<ReportItem> reportdata) {
        this.getReportDataResponse = new GetReportDataresponse(error, errorMsg, reportdata);
    }

    public ReportResponse() {

    }
}
