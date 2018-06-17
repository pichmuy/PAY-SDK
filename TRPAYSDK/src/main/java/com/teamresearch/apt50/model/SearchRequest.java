package com.teamresearch.apt50.model;

import com.google.gson.annotations.SerializedName;

public class SearchRequest {

    @SerializedName(ModelSubConst.SEARCH)
    public Search search;

    public class Search {
        public String trans_id;
        public String trans_type;
        public String start_date;
        public String end_date;

        public Search(String trans_id, String trans_type, String start_date, String end_date) {
            this.trans_id = trans_id;
            this.trans_type = trans_type;
            this.start_date = start_date;
            this.end_date = end_date;
        }
    }

    public SearchRequest(String trans_id, String trans_type, String start_date, String end_date) {
        this.search = new Search(trans_id, trans_type, start_date, end_date);
    }

    public SearchRequest() {

    }
}
