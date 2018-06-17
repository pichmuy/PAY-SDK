package com.teamresearch.apt50;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;
import com.teamresearch.apt50.device.Settings;
import com.teamresearch.apt50.utils.Const;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;


public class HttpsServer extends Service {

    private static final String TAG = HttpsServer.class.getCanonicalName();

    private final int PORT = 8000;
    private static final String PATH_TO_KEYS = "src/main/resources/";
    private static final String KEY_FILE = "keyhigh.jks";
    private static final String POST_DATA = "postData";
    private static final String PASSWORD = "password";

    public static final String API_PATH = "/api/";
    public static final String AUTHORIZE = API_PATH + "authorize";
    public static final String CARDAUTHENTICATION = API_PATH + "cardauthentication";
    public static final String FORCEDAUTH = API_PATH + "forcedauth";
    public static final String STATUS = API_PATH + "status";
    public static final String DEVICE = API_PATH + "device";
    public static final String SET = API_PATH + DEVICE + "/set";
    public static final String PRINT = API_PATH + "print";
    public static final String SEARCH = API_PATH + "search";
    public static final String CAPTURE = API_PATH + "capture";
    public static final String TIP_ADJUST = API_PATH + "tipadjust";
    public static final String VOID = API_PATH + "void";
    public static final String SALE = API_PATH + "sale";
    public static final String GEN_KEY = API_PATH + "genKey";
    public static final String RETURN = API_PATH + "return";
    public static final String REFERENCE = API_PATH + RETURN + "/reference";
    public static final String REPORT = API_PATH + "report";


    public HttpsServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Service is not available yet!");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createServer(getApplicationContext());
    }

    public void createServer(Context context) {
        WebServer secureAppServer;
        secureAppServer = new WebServer(context);

        File f;
        f = new File(PATH_TO_KEYS, KEY_FILE);
        System.setProperty("javax.net.ssl.trustStore", f.getAbsolutePath());

        try {
            secureAppServer.setServerSocketFactory(new NanoHTTPD.SecureServerSocketFactory
                    (NanoHTTPD.makeSSLSocketFactory("/" + f.getName(),
                            PASSWORD.toCharArray()), null));
        } catch (IOException e) {
            Log.e(TAG, "set server socket factory", e);
        }

        try {
            secureAppServer.start();
        } catch (IOException e) {
            Log.e(TAG, "start webserver", e);
        }
    }

    public class WebServer extends NanoHTTPD {

        public Context context;

        private TRPAY trpay;

        public WebServer(Context context) {
            super(PORT);
            this.context = context;
            this.trpay = TRPAY.getInstance();

            if (!this.trpay.isInitialized()) {
                this.trpay.init(TRPAY.TRPAYConfigBuilder.newBuilder(context).build());
            }
        }

        @Override
        public Response serve(IHTTPSession session) {
            String uri = session.getUri();
            Method method = session.getMethod();
            Map<String, String> headers = session.getHeaders();
            Map<String, String> files = session.getParms();

            if (method.equals(Method.POST) && headers.get("content-type").equals(Const.JSON_CONTENT_TYPE)) {
                if (uri.equals(AUTHORIZE)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.authorize(files.get(POST_DATA), true)));
                }
                if (uri.equals(CARDAUTHENTICATION)){
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.authorize(files.get(POST_DATA), true)));
                }
                if (uri.equals(FORCEDAUTH)){
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.authorize(files.get(POST_DATA), true)));
                }
                if (uri.equals(STATUS)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.status(files.get(POST_DATA))));
                }
                if (uri.equals(DEVICE)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(Settings.getProfileInstance(context)));
                }
                if (uri.equals(SET)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(Settings.getProfileInstance(files.get(POST_DATA), context)));
                }
                if (uri.equals(PRINT)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.print(files.get(POST_DATA))));
                }
                if (uri.equals(SEARCH)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.search(files.get(POST_DATA))));
                }
                if (uri.equals(CAPTURE)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.capture(files.get(POST_DATA))));
                }
                if (uri.equals(TIP_ADJUST)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.tip(files.get(POST_DATA))));
                }
                if (uri.equals(VOID)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.void_(files.get(POST_DATA))));
                }
                if (uri.equals(SALE)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.sale(files.get(POST_DATA), true)));
                }
                if (uri.equals(GEN_KEY)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.genKey(files.get(POST_DATA))));
                }
                if (uri.equals(RETURN)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.return_(files.get(POST_DATA), true)));
                }
                if (uri.equals(REFERENCE)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.return_(files.get(POST_DATA), true)));
                }
                if (uri.equals(REPORT)) {
                    return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, new Gson().toJson(trpay.report(files.get(POST_DATA))));
                }
            }
            return newFixedLengthResponse(Response.Status.OK, Const.JSON_CONTENT_TYPE, "Not Found");
        }
    }
}
