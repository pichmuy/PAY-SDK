package com.teamresearch.apt50.model;

public class ReportRequest {

    public GetReportdata getReportData;

    public class GetReportdata {
        public String reportName;
        public String start_date;
        public String end_date;
    }
}
