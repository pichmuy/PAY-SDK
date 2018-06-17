package com.teamresearch.apt50;

import android.content.Context;

import com.teamresearch.apt50.model.AuthorizeResponse;
import com.teamresearch.apt50.model.CaptureResponse;
import com.teamresearch.apt50.model.CardAuthResponse;
import com.teamresearch.apt50.model.ForcedAuthResponse;
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

public interface TRPAYProcessorsMethods extends Serializable {

    AuthorizeResponse authorize(String files, final Context context, Boolean server_status);

    void authorizeSwipe(String files, final Context context, Boolean server_status);

    CardAuthResponse cardauthentication(String files, final Context context);

    ForcedAuthResponse forcedauth(String files, final Context context);

    CaptureResponse capture(String files, final Context context);

    VoidResponse void_(String files, Context context);

    SaleResponse sale(String files, final Context context, Boolean server_status);

    void saleSwipe(String files, final Context context);

    TipAdjustResponse tip(String files, Context context);

    ReturnResponse return_(String files, final Context context, Boolean server_status);

    void returnSwipe(String files, final Context context, Boolean server_status);

    PrintResponse print(String files, Context context);

    StatusResponse status(String files, Context context);

    SearchResponse search(String files, Context context);

    ReportResponse report(String files, Context context);

    KeyResponse genKey(String files);
}
