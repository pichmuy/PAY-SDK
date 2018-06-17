package com.teamresearch.apt50;

import com.teamresearch.apt50.model.AuthorizeResponse;
import com.teamresearch.apt50.model.CaptureResponse;
import com.teamresearch.apt50.model.KeyResponse;
import com.teamresearch.apt50.model.PrintResponse;
import com.teamresearch.apt50.model.ReportResponse;
import com.teamresearch.apt50.model.ReturnResponse;
import com.teamresearch.apt50.model.SaleResponse;
import com.teamresearch.apt50.model.SearchResponse;
import com.teamresearch.apt50.model.StatusResponse;
import com.teamresearch.apt50.model.TipAdjustResponse;
import com.teamresearch.apt50.model.VoidResponse;

import java.io.Serializable;

public interface TRPAYSDKMethods extends Serializable {

    AuthorizeResponse authorize(String files, Boolean server_status);

    void authorizeSwipe(String files, Boolean server_status);

    CaptureResponse capture(String files);

    VoidResponse void_(String files);

    SaleResponse sale(String files, Boolean server_status);

    void saleSwipe(String files);

    TipAdjustResponse tip(String files);

    ReturnResponse return_(String files, Boolean server_status);

    void returnSwipe(String files, Boolean server_status);

    PrintResponse print(String files);

    StatusResponse status(String files);

    SearchResponse search(String files);

    ReportResponse report(String files);

    KeyResponse genKey(String files);
}
