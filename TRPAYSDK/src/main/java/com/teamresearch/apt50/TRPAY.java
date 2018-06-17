package com.teamresearch.apt50;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

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
import com.teamresearch.apt50.processor.tsys.TSYS;
import com.teamresearch.apt50.utils.Const;

public final class TRPAY implements TRPAYSDKMethods {

    private volatile static TRPAY instance;

    private boolean isInitialize;

    public TRPAYConfig config;
    private TSYS tsys;

    private TRPAY() {
        isInitialize = false;
    }

    public static TRPAY getInstance() {
        if (instance == null) {
            synchronized (TRPAY.class) {
                if (instance == null) {
                    instance = new TRPAY();
                }
            }
        }
        return instance;
    }

    public synchronized boolean isInitialized() {
        return isInitialize;
    }

    public void init(TRPAYConfig config) {
        if (config == null) {
            throw new RuntimeException("config should be not null, please use TRPAYConfigBuilder");
        }

        this.isInitialize = true;

        this.config = config;

        if (config.isHttpServerEnabled) {
            config.context.startService(new Intent(config.context, HttpsServer.class));
        }
        Log.d("TRPAYConfig","TRPAYConfigBuilder initialized");
    }

    @Override
    public AuthorizeResponse authorize(String files, Boolean server_status) {
        isInitOrThrow();
        return provideSDKProcessor().authorize(files, config.context, server_status);
    }

    @Override
    public void authorizeSwipe(String files, Boolean server_status) {
        isInitOrThrow();
        provideSDKProcessor().authorizeSwipe(files, config.context, server_status);
    }

    @Override
    public CaptureResponse capture(String files) {
        isInitOrThrow();
        return provideSDKProcessor().capture(files, config.context);
    }

    @Override
    public VoidResponse void_(String files) {
        isInitOrThrow();
        return provideSDKProcessor().void_(files, config.context);
    }

    @Override
    public SaleResponse sale(String files, Boolean server_status) {
        isInitOrThrow();
        return provideSDKProcessor().sale(files, config.context, server_status);
    }

    @Override
    public void saleSwipe(String files) {
        isInitOrThrow();
        provideSDKProcessor().saleSwipe(files, config.context);
    }

    @Override
    public TipAdjustResponse tip(String files) {
        isInitOrThrow();
        return provideSDKProcessor().tip(files, config.context);
    }

    @Override
    public ReturnResponse return_(String files, Boolean server_status) {
        isInitOrThrow();
        return provideSDKProcessor().return_(files, config.context, server_status);
    }

    @Override
    public void returnSwipe(String files, Boolean server_status) {
        isInitOrThrow();
        provideSDKProcessor().returnSwipe(files, config.context, server_status);
    }

    @Override
    public PrintResponse print(String files) {
        isInitOrThrow();
        return provideSDKProcessor().print(files, config.context);
    }

    @Override
    public StatusResponse status(String files) {
        isInitOrThrow();
        return provideSDKProcessor().status(files, config.context);
    }

    @Override
    public SearchResponse search(String files) {
        isInitOrThrow();
        return provideSDKProcessor().search(files, config.context);
    }

    @Override
    public ReportResponse report(String files) {
        isInitOrThrow();
        return provideSDKProcessor().report(files, config.context);
    }

    @Override
    public KeyResponse genKey(String files) {
        return provideSDKProcessor().genKey(files);
    }

    private void isInitOrThrow() {
        if (!isInitialized()) {
            throw new RuntimeException("Should call init(?) first.");
        }
    }

    private TRPAYProcessorsMethods provideSDKProcessor() {
        //todo depends of logic choose processor, need create and provide processor class

        if (tsys == null) {
            synchronized (TRPAY.class) {
                if (tsys == null) {
                    tsys = new TSYS(this);
                }
            }
        }

        return tsys;
    }

    public static IntentFilter getErrorCatchIntentFilter() {
        return new IntentFilter(Const.ERROR_CATCH_FILTER);
    }

    public final static class TRPAYConfigBuilder {

        private TRPAYConfig trpayConfig;

        private TRPAYConfigBuilder() {
            trpayConfig = new TRPAYConfig();
        }

        public static TRPAYConfigBuilder newBuilder(Context context) {
            if (context == null) {
                throw new RuntimeException("context should be not null");
            }

            TRPAYConfigBuilder builder = new TRPAYConfigBuilder();
            builder.trpayConfig.context = context;
            Log.d("TRPAYConfigBuilder","TRPAYConfigBuilder initialized");
            return builder;
        }

        public TRPAYConfigBuilder isHttpServerEnabled(boolean mode) {
            this.trpayConfig.isHttpServerEnabled = mode;
            return this;
        }

        public TRPAYConfig build() {
            Log.d("TRPAYConfigBuilder","TRPAYConfigBuilder built");
            return trpayConfig;
        }
    }

    public final static class TRPAYConfig {

        public boolean isHttpServerEnabled;
        public Context context;

    }
}
