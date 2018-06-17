package com.teamresearch.apt50.processor.tsys;


import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamresearch.apt50.BuildConfig;
import com.teamresearch.apt50.TRPAY;
import com.teamresearch.apt50.database.Configuration;
import com.teamresearch.apt50.database.DBHelper;
import com.teamresearch.apt50.device.Settings;
import com.teamresearch.apt50.model.AuthorizeRequest;
import com.teamresearch.apt50.model.AuthorizeResponse;
import com.teamresearch.apt50.model.CaptureRequest;
import com.teamresearch.apt50.model.CaptureResponse;
import com.teamresearch.apt50.model.CardAuthRequest;
import com.teamresearch.apt50.model.CardAuthResponse;
import com.teamresearch.apt50.model.ForcedAuthRequest;
import com.teamresearch.apt50.model.ForcedAuthResponse;
import com.teamresearch.apt50.model.KeyRequest;
import com.teamresearch.apt50.model.KeyResponse;
import com.teamresearch.apt50.model.ReportRequest;
import com.teamresearch.apt50.model.ReportResponse;
import com.teamresearch.apt50.model.ReturnRequest;
import com.teamresearch.apt50.model.ReturnResponse;
import com.teamresearch.apt50.model.SaleRequest;
import com.teamresearch.apt50.model.SaleResponse;
import com.teamresearch.apt50.model.TipAdjustRequest;
import com.teamresearch.apt50.model.TipAdjustResponse;
import com.teamresearch.apt50.model.VoidRequest;
import com.teamresearch.apt50.model.VoidResponse;
import com.teamresearch.apt50.processor.Processor;
import com.teamresearch.apt50.processor.tsys.ReportMiddleResponse.ReportItem;
import com.teamresearch.apt50.processor.tsys.util.TLVList;
import com.teamresearch.apt50.utils.Const;
import com.teamresearch.apt50.utils.ToolsUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.CertificatePinner;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sdk4.wangpos.libemvbinder.CAPK;
import sdk4.wangpos.libemvbinder.EmvAppList;
import sdk4.wangpos.libemvbinder.EmvCore;
import wangpos.sdk4.libbasebinder.BankCard;
import wangpos.sdk4.libbasebinder.Core;
import wangpos.sdk4.libbasebinder.Printer;

/**
 * @version 1.0.0;
 * @overview Tsys Payment Interface
 * Application Layer that communicates with TSYS
 */

public final class TSYS extends Processor {

    private static final String TAG = TSYS.class.getCanonicalName();

    private static final String CURRENCY_CODE = "USD";
    private static final String TERMINAL_CAPABILITY = "KEYED_ENTRY_ONLY";
    private static final String TERMINAL_OPERATING_ENVIRONMENT = "NO_TERMINAL";
    private static final String CARDHOLDER_AUTHENTICATION_METHOD = "MANUAL_SIGNATURE";
    private static final String TERMINAL_AUTHENTICATION_CAPABILITY = "NO_CAPABILITY";
    private static final String TERMINAL_OUTPUT_CAPABILITY = "NONE";
    private static final String MAX_PIN_LENGTH = "NOT_SUPPORTED";
    private static final String DEVELOPER_ID = "002975";
    private static final String ORDER_NOTES = "FUNDS CAPTURE";
    private static final String TRANSACTION_APPROVED = "TRANSACTION APPROVED";
    private static final String TRANSACTION_DECLINED = "TRANSACTION DECLINED";
    private static final String TRANSACTION_INITIATED = "Transaction initiated";
    private static final String AMOUNT = "Amount";
    private static final String REF = "Ref";
    private static final String SWIPE_YOUR_CC_NOW = "Swipe Your card now";
    private static String[] CAPK_DATA = {
            "9F0605A0000003339F220180DF05083230333031323330DF060101DF070101DF028180CCDBA686E2EFB84CE2EA01209EEB53BEF21AB6D353274FF8391D7035D76E2156CAEDD07510E07DAFCACABB7CCB0950BA2F0A3CEC313C52EE6CD09EF00401A3D6CC5F68CA5FCD0AC6132141FAFD1CFA36A2692D02DDC27EDA4CD5BEA6FF21913B513CE78BF33E6877AA5B605BC69A534F3777CBED6376BA649C72516A7E16AF85DF0403010001DF0314A5E44BB0E1FA4F96A11709186670D0835057D35E",
            "9F0605A0000003339F220183DF05083230333031323330DF060101DF070101DF028190E46C9D054471D24A3DAEEA13875ECFB92C34D309106092E6AF57BD612C18E4E2BB3FBBC9E14F86D8660A065848B821347D04521578D4B789FD57231185DF92F45C5733C7912C291D7B13E649B094B33B1B75151C0E4E71E45CCDFD5217DC9F3EF39C3D324CA460DDC40C45CC27B2E421A2B409A47FAAEFD65F8A7F58A269B38CFD9C18210856A493A6624141677F5E95DF040103DF03141CC9BA05BC70F3D049F817404051122E35AC9683",
            "9F0605A0000003339F220184DF05083230333031323330DF060101DF070101DF0281B0F9EA5503CFE43038596C720645A94E0154793DE73AE5A935D1FB9D0FE77286B61261E3BB1D3DFEC547449992E2037C01FF4EFB88DA8A82F30FEA3198D5D16754247A1626E9CFFB4CD9E31399990E43FCA77C744A93685A260A20E6A607F3EE3FAE2ABBE99678C9F19DFD2D8EA76789239D13369D7D2D56AF3F2793068950B5BD808C462571662D4364B30A2582959DB238333BADACB442F9516B5C336C8A613FE014B7D773581AE10FDF7BDB2669012DDF040103DF03144D4E6D415F2CF8C394D40C49FB2459110578CF22",
            "9F0605A0000003339F220185DF05083230333031323330DF060101DF070101DF0281F8CD026B3E11A7234EFC24FB5976D9F51F7188A1598861AA8A6CA8D9A55300C6E6C39ED97E128973306E7D15DF603823A2C0C2E4C01C5AC0D4E71127DFEC69F2B17DAB12F2E8A84CD30AFC791AE71CD6D69D1B7E7648B2F0BB2140791C585E9CAC6642230B13C81A66E52E927681594EC08CFB30E10658F4199B8BF48B55F140925DEEEF4341E2C6C91E039944A5C44DD72379C2227F02105F462C0E977A2E79D2841143941EB4B4BC1ADAC274E3B0129DE7FDCC77C75BBC29A2861DCE7F748EBEE1E69339348667B729C2900EC6A6D43881622555FA8F8B85E18BD2B8B6F56EBD47643181FF7039D883CB5D723D9DEBD073A5A0CD7B980F0DDF040103DF031496C22F92B7644934F03B4065F1C37BC9DBEA45B0",
    };

    private static String[] AID_DATA = {
            "9F0607A0000000031010DF0101009F0802008CDF1105D84000A800DF1205D84004F800DF130500100000009F1B0431303030DF150435303030DF160100DF170100DF14029F37DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000032010DF0101009F0802008CDF1105D84000A800DF1205D84004F800DF130500100000009F1B0431303030DF150435303030DF160100DF170100DF14029F37DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000033010DF0101009F0802008CDF1105D84000A800DF1205D84004F800DF130500100000009F1B0431303030DF150435303030DF160100DF170100DF14029F37DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000038010DF0101009F0802008CDF1105D84000A800DF1205D84004F800DF130500100000009F1B0431303030DF150435303030DF160100DF170100DF14029F37DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0608A000000333010101DF0101009F0802008CDF1105D84000A800DF1205D84004F800DF130500100000009F1B0431303030DF150435303030DF160100DF170100DF14029F37DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0608A000000003999910DF0101009F0802008CDF1105D84000A800DF1205D84004F800DF130500100000009F1B0431303030DF150435303030DF160100DF170100DF14029F37DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000041010DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000043060DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000046000DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000046010DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000042000DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000042010DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000000043000DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0607A0000003330101DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000DF2106000000100000",
            "9F0607A0000000048010DF0101019F08020002DF1105FC50ACA000DF1205F850ACF800DF130504000000009F1B0431303030DF150435303030DF160100DF170100DF140B9F37049F47018F019F3201DF1801019F7B06000000000100DF1906000000100000DF2006000000100000",
            "9F0605A0000000659F2201099F1B0431303030DF05083230303931323331DF060101DF070101DF028180B72A8FEF5B27F2B550398FDCC256F714BAD497FF56094B7408328CB626AA6F0E6A9DF8388EB9887BC930170BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE93FC998A721705091F18BC7C98241CADC15A2B9DA7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFADCF9C012366BDDA0455C1564A68810D7127676D493890BDDF040103DF03144410C6D51C2F83ADFD92528FA6E38A32DF048D0ADF1906000000100000DF2006000000100000",
            "9F0605A0000000659F2201109F1B0431303030DF05083230313231323331DF060101DF070101DF02819099B63464EE0B4957E4FD23BF923D12B61469B8FFF8814346B2ED6A780F8988EA9CF0433BC1E655F05EFA66D0C98098F25B659D7A25B8478A36E489760D071F54CDF7416948ED733D816349DA2AADDA227EE45936203CBF628CD033AABA5E5A6E4AE37FBACB4611B4113ED427529C636F6C3304F8ABDD6D9AD660516AE87F7F2DDF1D2FA44C164727E56BBC9BA23C0285DF040103DF0314C75E5210CBE6E8F0594A0F1911B07418CADB5BABDF1906000000100000DF2006000000100000",
            "9F0605A0000000659F2201129F1B0431303030DF05083230313431323331DF060101DF070101DF0281B0ADF05CD4C5B490B087C3467B0F3043750438848461288BFEFD6198DD576DC3AD7A7CFA07DBA128C247A8EAB30DC3A30B02FCD7F1C8167965463626FEFF8AB1AA61A4B9AEF09EE12B009842A1ABA01ADB4A2B170668781EC92B60F605FD12B2B2A6F1FE734BE510F60DC5D189E401451B62B4E06851EC20EBFF4522AACC2E9CDC89BC5D8CDE5D633CFD77220FF6BBD4A9B441473CC3C6FEFC8D13E57C3DE97E1269FA19F655215B23563ED1D1860D8681DF040103DF0314874B379B7F607DC1CAF87A19E400B6A9E25163E8DF1906000000100000DF2006000000100000"
    };

    private static final String URL = "https://stagegw.transnox.com/servlets/TransNox_API_Server/";
    private static final String CERT_PATTERN = "stagegw.transnox.com";
    private static final String[] CERT_PINS = new String[]{
            "sha256/EjcbKhwfndYnPsHnfnXt9f10DzII9Eup2iGyhi/PN1Y=",
            "sha256/njN4rRG+22dNXAi+yb8e3UMypgzPUPHlv4+foULwl1g=",
            "sha256/i7WTqTvh0OioIruIfFR4kMPnBqrS2rdiVPl/s2uC/CY="
    };

    private static final String TSYS = "TSYS";
    private static final String EMV = "EMV";
    private static final String MANUAL = "MANUAL";
    private static final String SWIPE = "SWIPE";
    private static final String APT50 = "APT50";
    private static final String PASS = "PASS";
    private static final String PENDING = "PENDING";
    private static final String UNKNOWN = "UNKNOWN";
    private static final String Y = "Y";
    private static final String _21 = "21";
    private static final String _5 = "5";
    private static final String ON_MERCHANT_PREMISES_ATTENDED = "ON_MERCHANT_PREMISES_ATTENDED";
    private static final String MANUAL_SIGNATURE = "MANUAL_SIGNATURE";

    private static final String ERROR_CODE_900 = "900";
    private static final String ERROR_CODE_901 = "901";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private AuthMiddleResponse authMiddleResponse = new AuthMiddleResponse();
    private AuthorizeRequest authorizeRequest = new AuthorizeRequest();
    private AuthorizeResponse authorizeResponse = new AuthorizeResponse();
    private SaleRequest saleRequest = new SaleRequest();
    private SaleResponse saleResponse = new SaleResponse();
    private ReturnRequest returnRequest = new ReturnRequest();
    private ReturnResponse returnResponse = new ReturnResponse();

    private BankCard mBankCard;
    private Core mCore;
    private ReadMagTask mReadMagTask;
    private EmvCore emvCore;

    private double subtotal = 0;
    private double tax = 0;
    private Printer printer;

    public TSYS(TRPAY trpay) {
        super(trpay);
    }

    /**
     * Performs an Auth on a provided
     * Credit Card
     */
    @Override
    public AuthorizeResponse authorize(String files, final Context context, Boolean server_status) {
        String errorcheck = Settings.getProfileInstance(context).error;

        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            authorizeResponse = new AuthorizeResponse(errorcheck, errormsg);
            return authorizeResponse;
        }

        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        if (trans_key == null || deviceID == null) {
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            authorizeResponse = new AuthorizeResponse(ERROR_CODE_901, errormsg);
            return authorizeResponse;
        }

        String command = Const.AUTH;
        authorizeRequest = new AuthorizeRequest();
        authorizeResponse = new AuthorizeResponse();

        new Thread() {
            @Override
            public void run() {
                printer = new Printer(context);
            }
        }.start();

        try {
            authorizeRequest = new Gson().fromJson(files, AuthorizeRequest.class);
            subtotal = Double.parseDouble(authorizeRequest.getSubtotal());
            tax = Double.parseDouble(authorizeRequest.getTax());
        } catch (Exception e) {
            authorizeResponse = new AuthorizeResponse("1", "Invalid parameter");
            return authorizeResponse;
        }

        if (TextUtils.isEmpty(authorizeRequest.Authorize.orderID)) {
            authorizeResponse = new AuthorizeResponse("8", "orderID should not be null");
            return authorizeResponse;
        }

        if (BuildConfig.DEBUG) {
            Log.d("data", files);
        }

        stopTask(context);

        switch (authorizeRequest.getCardDataSource()) {
            case SWIPE: {
                String nullString = "";

                /* Record the transaction */
                int ref = Logging.transaction(command, authorizeRequest.Authorize.amount, nullString, nullString, TSYS, "", nullString, PENDING, nullString, nullString, nullString,
                        command, authorizeRequest.getTip(), Double.parseDouble(authorizeRequest.getSubtotal()), Double.parseDouble(authorizeRequest.getTax()), Integer.parseInt(authorizeRequest.getQty()), context);
                new Thread() {
                    @Override
                    public void run() {
                        mCore = new Core(context);
                        mBankCard = new BankCard(context);
                    }
                }.start();

                startTask(context, command, server_status, authorizeRequest.Authorize.amount, ref);
                authorizeResponse = new AuthorizeResponse("0", TRANSACTION_INITIATED, command, String.valueOf(ref),
                        Double.parseDouble(authorizeRequest.Authorize.amount), Double.parseDouble(authorizeRequest.Authorize.tip));
                return authorizeResponse;
            }
            case EMV:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        emvCore = new EmvCore(context);
                    }
                }).start();
                /* NOTE: FOR FUTURE USE, NOT INCLUDED IN THIS RELEASE */
                InitiateEMV(context);
                return null; // Exit this method

            case MANUAL: {
                /* Extract first name/last name from full name */
                final int ref;
                String firstname = null;
                String lastname = null;

                try {
                    String[] name = authorizeRequest.Authorize.cardHolderName.split(" ");
                    firstname = name[0];
                    lastname = name[1];
                } catch (Exception e) {
                    authorizeResponse = new AuthorizeResponse("12", "Invalid CardHolderName");
                    return authorizeResponse;
                }

                AuthMiddleRequest authMiddleRequest = new AuthMiddleRequest(
                        deviceID, trans_key,
                        authorizeRequest.Authorize.cardDataSource,
                        authorizeRequest.Authorize.amount, authorizeRequest.Authorize.tip, CURRENCY_CODE,
                        authorizeRequest.Authorize.cardNumber, authorizeRequest.Authorize.expirationDate,
                        authorizeRequest.Authorize.cvv2, authorizeRequest.Authorize.addressLine1, authorizeRequest.Authorize.zip,
                        authorizeRequest.Authorize.orderID, firstname, lastname, TERMINAL_CAPABILITY, TERMINAL_OPERATING_ENVIRONMENT,
                        CARDHOLDER_AUTHENTICATION_METHOD, TERMINAL_AUTHENTICATION_CAPABILITY, TERMINAL_OUTPUT_CAPABILITY, MAX_PIN_LENGTH
                );

                Gson gson = new GsonBuilder().registerTypeAdapter(AuthMiddleRequest.class, new AuthJsonSerializer()).create();

                String responsedata = null;
                RequestBody body = RequestBody.create(JSON, gson.toJson(authMiddleRequest));

                /* Post request/get response */
                responsedata = post(body);

                if (BuildConfig.DEBUG) {
                    Log.d("responsedata", responsedata);
                }

                authMiddleResponse = new AuthMiddleResponse();
                authMiddleResponse = gson.fromJson(responsedata, AuthMiddleResponse.class);

                if (BuildConfig.DEBUG) {
                    Log.d("authmiddleresponse", new Gson().toJson(authMiddleResponse));
                }

                String error = null;
                if (authMiddleResponse.getStatus().equals(PASS)) {

                    error = "0";
                    ref = Logging.transaction(command, authorizeRequest.getAmount(), authMiddleResponse.getAuthCode(), authMiddleResponse.getCardType(),
                            TSYS, authMiddleResponse.getResponseCode(), authMiddleResponse.getResponseMessage(),
                            authMiddleResponse.getStatus(), authMiddleResponse.getTransactionID(), authMiddleResponse.getTransactionTimestamp(),
                            command, authorizeRequest.Authorize.orderID, authorizeRequest.getTip(), subtotal, tax, authorizeRequest.Authorize.qty, context);
                    if (server_status) {
                        Intent intent = new Intent(TRANSACTION_APPROVED);
                        intent.putExtra(AMOUNT, authMiddleResponse.getTotalAmount());
                        intent.putExtra(REF, String.valueOf(ref));
                        context.sendBroadcast(intent);
                    }
                    DBHelper dbHelper = new DBHelper(context);
                    Configuration configuration;
                    try {
                        configuration = dbHelper.getById(Configuration.class, 1);
                        if (configuration.device_type.equals(APT50) && authorizeRequest.getPrint().equals(Y)) {
                            new PrintThread(ref, context, printer).start();
                        }
                    } catch (SQLException e) {
                        Log.e(TAG, "error print thread", e);
                    }
                } else {
                    if (server_status) {
                        Intent intnet = new Intent(TRANSACTION_DECLINED);
                        intnet.putExtra("ErrorMsg", authMiddleResponse.getResponseMessage());
                        context.sendBroadcast(intnet);
                    }
                    ref = Logging.transaction(command, authorizeRequest.getAmount(), authMiddleResponse.getAuthCode(), authMiddleResponse.getCardType(),
                            TSYS, authMiddleResponse.getResponseCode(), authMiddleResponse.getResponseMessage(),
                            authMiddleResponse.getStatus(), authMiddleResponse.getTransactionID(), authMiddleResponse.getTransactionTimestamp(),
                            command, authorizeRequest.Authorize.orderID, authorizeRequest.getTip(), subtotal, tax, authorizeRequest.Authorize.qty, context);
                    error = authMiddleResponse.getResponseCode();
                }

                String errorMsg = authMiddleResponse.getResponseMessage();
                String transactionID = authMiddleResponse.getTransactionID();
                String transactionTimeStamp = authMiddleResponse.getTransactionTimestamp();
                String status = authMiddleResponse.getStatus();
                String authcode = authMiddleResponse.getAuthCode();
                double totalamount = Double.parseDouble(authMiddleResponse.getTotalAmount());
                double tip = Double.parseDouble(authMiddleResponse.getTip());
                String addressverificationcode = authMiddleResponse.getAddressVerificationCode();
                String cvvverificationcode = authMiddleResponse.getCvvVerificationCode();
                String cardtype = authMiddleResponse.getCardType();
                String maskedcardnumber = authMiddleResponse.getMaskedCardNumber();
                String firstName = authMiddleResponse.getFirstName();
                String lastName = authMiddleResponse.getLastName();
                String expireDate = authMiddleResponse.getExpirationDate();

                return new AuthorizeResponse(error, errorMsg, String.valueOf(ref), transactionID,
                        transactionTimeStamp, status, authcode, totalamount, tip, addressverificationcode,
                        cvvverificationcode, cardtype, maskedcardnumber, "Card Token", expireDate,
                        firstName, lastName);

            }
        }
        authorizeResponse = new AuthorizeResponse("10", "Invalid CardDataSource");
        return authorizeResponse;
    }

    /**
     * Performs a Swipe/MSR Auth on a provided
     * Credit Card
     */
    @Override
    public void authorizeSwipe(String files, final Context context, Boolean server_status) {

        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        String terminalCapability = "MAGSTRIPE_KEYED_ENTRY_ONLY";
        String terminalOutputCapability = "PRINT_AND_DISPLAY";
        String command = Const.AUTH;

        authorizeRequest = new AuthorizeRequest();
        authorizeRequest = new Gson().fromJson(files, AuthorizeRequest.class);

        if (BuildConfig.DEBUG) {
            Log.d("authorizeRequestPRE", new Gson().toJson(authorizeRequest));
        }

        AuthMiddleRequest authMiddleRequest = new AuthMiddleRequest(deviceID, trans_key,
                authorizeRequest.Authorize.cardDataSource, authorizeRequest.Authorize.amount,
                authorizeRequest.Authorize.tip, CURRENCY_CODE, authorizeRequest.Authorize.track2Data,
                authorizeRequest.Authorize.track1Data, _21, _5,
                authorizeRequest.Authorize.orderID, Y, terminalCapability,
                ON_MERCHANT_PREMISES_ATTENDED, MANUAL_SIGNATURE,
                TERMINAL_AUTHENTICATION_CAPABILITY, terminalOutputCapability, UNKNOWN);

        if (BuildConfig.DEBUG) {
            Log.d("authorizeRequestPOST", new Gson().toJson(authMiddleRequest));
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(AuthMiddleRequest.class, new AuthSwipeJsonSerializer()).create();

        if (BuildConfig.DEBUG) {
            Log.d("ResponseGson", gson.toJson(authMiddleRequest));
        }

        String responsedata = "";

        RequestBody swipebody = RequestBody.create(JSON, gson.toJson(authMiddleRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        authMiddleResponse = new AuthMiddleResponse();
        authMiddleResponse = gson.fromJson(responsedata, AuthMiddleResponse.class);

        if (authMiddleResponse.getStatus().equals(PASS)) {
            Logging.updateTransaction(authorizeResponse.authorizeResponse.ref, command,
                    authorizeRequest.getAmount(), authMiddleResponse.getAuthCode(),
                    authMiddleResponse.getCardType(), TSYS,
                    authMiddleResponse.getResponseCode(), authMiddleResponse.getResponseMessage(),
                    authMiddleResponse.getStatus(), authMiddleResponse.getTransactionID(), authMiddleResponse.getTransactionTimestamp(),
                    command, authorizeRequest.Authorize.orderID, authorizeRequest.getTip(), subtotal, tax, authorizeRequest.Authorize.qty, context);
            DBHelper dbHelper = new DBHelper(context);
            Configuration configuration;
            try {
                configuration = dbHelper.getById(Configuration.class, 1);
                if (configuration.device_type.equals(APT50) && authorizeRequest.getPrint().equals(Y)) {
                    new PrintThread(Integer.parseInt(authorizeResponse.authorizeResponse.ref), context, printer).start();
                }
            } catch (SQLException e) {
                Log.e(TAG, "error print thread", e);
            }

        } else {
            Logging.updateTransaction(authorizeResponse.authorizeResponse.ref, command,
                    authorizeRequest.getAmount(), authMiddleResponse.getAuthCode(), authMiddleResponse.getCardType(),
                    TSYS, authMiddleResponse.getResponseCode(), authMiddleResponse.getResponseMessage(),
                    authMiddleResponse.getStatus(), authMiddleResponse.getTransactionID(), authMiddleResponse.getTransactionTimestamp(),
                    command, authorizeRequest.Authorize.orderID, authorizeRequest.getTip(), subtotal, tax, authorizeRequest.Authorize.qty, context);
        }
    }

    @Override
    public CardAuthResponse cardauthentication(String files, Context context) {
        String errorcheck = Settings.getProfileInstance(context).error;
        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            return new CardAuthResponse(errorcheck, errormsg);
        }

        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;

        if (trans_key == null || deviceID == null) {
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            return new CardAuthResponse(errorcheck, errormsg);
        }

        String command = "CardAuthentication";
        CardAuthRequest cardAuthRequest;
        CardAuthResponse cardAuthResponse;
        try {
            cardAuthRequest = new Gson().fromJson(files, CardAuthRequest.class);
        } catch (Exception e) {
            cardAuthResponse = new CardAuthResponse("1", "Invalid Parameter");
            return cardAuthResponse;
        }

        if (BuildConfig.DEBUG) {
            Log.d("CardAuthRequest", new Gson().toJson(cardAuthRequest));
        }
        String firstname;
        String lastname;
        try {
            String[] name = cardAuthRequest.cardAuthRequest.cardHolderName.split(" ");
            firstname = name[0];
            lastname = name[1];
        } catch (Exception e) {
            cardAuthResponse = new CardAuthResponse("12", "Invalid CardHolderName");
            return cardAuthResponse;
        }
        CardAuthMiddleRequest cardAuthMiddleRequest = new CardAuthMiddleRequest(deviceID, trans_key,cardAuthRequest.cardAuthRequest.cardDataSource,
                                                    cardAuthRequest.cardAuthRequest.cardNumber, cardAuthRequest.cardAuthRequest.expirationDate,
                                                    cardAuthRequest.cardAuthRequest.cvv2,cardAuthRequest.cardAuthRequest.cardHolderName,cardAuthRequest.cardAuthRequest.addressLine1,
                                                    cardAuthRequest.cardAuthRequest.zip,cardAuthRequest.cardAuthRequest.orderNumber,firstname,lastname,
                                                    TERMINAL_CAPABILITY, TERMINAL_OPERATING_ENVIRONMENT,CARDHOLDER_AUTHENTICATION_METHOD, TERMINAL_AUTHENTICATION_CAPABILITY,
                                                    TERMINAL_OUTPUT_CAPABILITY, MAX_PIN_LENGTH,DEVELOPER_ID);

        Gson gson = new GsonBuilder().registerTypeAdapter(CardAuthMiddleRequest.class, new CardAuthJsonSerializer()).create();
        String responsedata = null;
        RequestBody body = RequestBody.create(JSON, gson.toJson(cardAuthMiddleRequest));
        responsedata = post(body);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        CaptureMiddleResponse captureMiddleResponse = new CaptureMiddleResponse();
        captureMiddleResponse = gson.fromJson(responsedata, CaptureMiddleResponse.class);

        String error;
        return null;
    }

    @Override
    public ForcedAuthResponse forcedauth(String files, Context context) {

        String errorcheck = Settings.getProfileInstance(context).error;
        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            return new ForcedAuthResponse(errorcheck, errormsg);
        }

        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;

        if (trans_key == null || deviceID == null) {
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            return new ForcedAuthResponse(errorcheck, errormsg);
        }

        String command = "Capture";
        ForcedAuthRequest forcedAuthRequest;
        ForcedAuthResponse forcedAuthResponse;
        try {
            forcedAuthRequest = new Gson().fromJson(files, ForcedAuthRequest.class);
        } catch (Exception e) {
            forcedAuthResponse = new ForcedAuthResponse("1", "Invalid Parameter");
            return forcedAuthResponse;
        }

        if (BuildConfig.DEBUG) {
            Log.d("CaptureRequest", new Gson().toJson(forcedAuthRequest));
        }

        ForcedAuthMiddleRequest forcedAuthMiddleRequest = new ForcedAuthMiddleRequest(deviceID, trans_key,forcedAuthRequest.forcedAuth.cardDataSource,
                forcedAuthRequest.forcedAuth.amount, forcedAuthRequest.forcedAuth.cardNumber, forcedAuthRequest.forcedAuth.expirationDate,
                forcedAuthRequest.forcedAuth.authTimeStamp, forcedAuthRequest.forcedAuth.authCode, forcedAuthRequest.forcedAuth.developerID);

        Gson gson = new GsonBuilder().registerTypeAdapter(ForcedAuthMiddleRequest.class, new ForcedAuthJsonSerializer()).create();
        String responsedata = null;
        RequestBody body = RequestBody.create(JSON, gson.toJson(forcedAuthMiddleRequest));
        responsedata = post(body);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        AuthMiddleResponse authMiddleResponse = new AuthMiddleResponse();
        authMiddleResponse = gson.fromJson(responsedata, AuthMiddleResponse.class);

        String error;
        if (authMiddleResponse.getStatus().equals(PASS)) {
            error = "0";
        } else {
            error = authMiddleResponse.getResponseCode();
        }
        Logging.transaction(command, authMiddleResponse.getTransactionAmount(), "", "",
                TSYS, authMiddleResponse.getResponseCode(), authMiddleResponse.getResponseMessage(),
                authMiddleResponse.getStatus(), authMiddleResponse.getTransactionID(), authMiddleResponse.getTransactionTimestamp(),
                command, "", authMiddleResponse.getTip(), 0.00, 0.00, 0, context);
        forcedAuthResponse = new ForcedAuthResponse(error, authMiddleResponse.getResponseMessage(),
                authMiddleResponse.getStatus(),authMiddleResponse.getAuthCode(),authMiddleResponse.getTaskID(),
                authMiddleResponse.getTransactionAmount(),authMiddleResponse.getTransactionTimestamp(),
                authMiddleResponse.getTransactionAmount(),authMiddleResponse.getCardType(),authMiddleResponse.getMaskedCardNumber());

        if (BuildConfig.DEBUG) {
            Log.d("ForcedAuth_Response", new Gson().toJson(forcedAuthResponse));
        }

        return forcedAuthResponse;
    }


    /**
     * Captures the funds on a previously authorized
     * or a sale transaction
     */
    @Override
    public CaptureResponse capture(String files, final Context context) {
        String errorcheck = Settings.getProfileInstance(context).error;
        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            return new CaptureResponse(errorcheck, errormsg);
        }

        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;

        if (trans_key == null || deviceID == null) {
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            return new CaptureResponse(errorcheck, errormsg);
        }

        String command = "Capture";
        CaptureRequest captureRequest;
        CaptureResponse captureResponse;
        try {
            captureRequest = new Gson().fromJson(files, CaptureRequest.class);
        } catch (Exception e) {
            captureResponse = new CaptureResponse("1", "Invalid Parameter");
            return captureResponse;
        }

        if (BuildConfig.DEBUG) {
            Log.d("CaptureRequest", new Gson().toJson(captureRequest));
        }

        CaptureMiddleRequest captureMiddleRequest = new CaptureMiddleRequest(deviceID, trans_key,
                captureRequest.capture.amount, "20", captureRequest.capture.transactionID, ORDER_NOTES);

        Gson gson = new GsonBuilder().registerTypeAdapter(CaptureMiddleRequest.class, new CaptureJsonSerializer()).create();
        String responsedata = null;
        RequestBody body = RequestBody.create(JSON, gson.toJson(captureMiddleRequest));
        responsedata = post(body);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        CaptureMiddleResponse captureMiddleResponse = new CaptureMiddleResponse();
        captureMiddleResponse = gson.fromJson(responsedata, CaptureMiddleResponse.class);

        String error;
        if (captureMiddleResponse.getStatus().equals(PASS)) {
            error = "0";
        } else {
            error = captureMiddleResponse.getResponseCode();
        }
        Logging.transaction(command, captureRequest.getAmount(), "", "",
                TSYS, captureMiddleResponse.getResponseCode(), captureMiddleResponse.getResponseMessage(),
                captureMiddleResponse.getStatus(), captureMiddleResponse.getTransactionID(), captureMiddleResponse.getTransactionTimestamp(),
                command, "", captureMiddleResponse.getTip(), 0.00, 0.00, 0, context);
        captureResponse = new CaptureResponse(error, captureMiddleResponse.getResponseMessage(),
                captureMiddleResponse.getTransactionID(), captureMiddleResponse.getTransactionTimestamp());

        if (BuildConfig.DEBUG) {
            Log.d("Capture_Response", new Gson().toJson(captureResponse));
        }

        return captureResponse;
    }


    /**
     * Voids a previously authorized
     * or a sale transaction
     */
    @Override
    public VoidResponse void_(String files, Context context) {
        String errorcheck = Settings.getProfileInstance(context).error;
        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            return new VoidResponse(errorcheck, errormsg);
        }
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        if (trans_key == null || deviceID == null) {
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            return new VoidResponse(errorcheck, errormsg);
        }
        String command = "Void";
        VoidRequest voidRequest = new VoidRequest();
        try {
            voidRequest = new Gson().fromJson(files, VoidRequest.class);
        } catch (Exception e) {
            return new VoidResponse("1", "Invalid Parameter");
        }

        if (BuildConfig.DEBUG) {
            Log.d("VoidRequest", new Gson().toJson(voidRequest));
        }

        VoidMiddleRequest voidMiddleRequest = new VoidMiddleRequest(deviceID, trans_key,
                voidRequest.void_.transactionID);

        Gson gson = new GsonBuilder().registerTypeAdapter(VoidMiddleRequest.class, new VoidJsonSerializer()).create();
        String responsedata = null;
        RequestBody body = RequestBody.create(JSON, gson.toJson(voidMiddleRequest));
        responsedata = post(body);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        VoidMiddleResponse voidMiddleResponse;
        voidMiddleResponse = gson.fromJson(responsedata, VoidMiddleResponse.class);

        String error;
        if (voidMiddleResponse.getStatus().equals(PASS)) {
            error = "0";
        } else {
            error = voidMiddleResponse.getResponseCode();
        }

        Logging.transaction(command, voidRequest.getAmount(), "", "",
                TSYS, voidMiddleResponse.getResponseCode(), voidMiddleResponse.getResponseMessage(),
                voidMiddleResponse.getStatus(), voidMiddleResponse.getTransactionID(), voidMiddleResponse.getTransactionTimestamp(),
                command, "", "0", 0.00, 0.00, 0, context);

        VoidResponse voidResponse = new VoidResponse(error, voidMiddleResponse.getResponseMessage(), voidMiddleResponse.getVoidedAmount(),
                voidMiddleResponse.getTransactionID(), voidMiddleResponse.getTransactionTimestamp());

        if (BuildConfig.DEBUG) {
            Log.d("Void_Response", new Gson().toJson(voidResponse));
        }

        return voidResponse;
    }

    /**
     * Performs a Sale on a provided
     * Credit Card
     */
    @Override
    public SaleResponse sale(String files, final Context context, Boolean server_status) {
        String errorcheck = Settings.getProfileInstance(context).error;
        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            return new SaleResponse(errorcheck, errormsg);
        }
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        if (trans_key == null || deviceID == null) {
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            return new SaleResponse(errorcheck, errormsg);
        }

        String command = Const.SALE;
        String nullString = "";

        new Thread() {
            @Override
            public void run() {
                printer = new Printer(context);
            }
        }.start();
        saleRequest = new SaleRequest();
        try {
            saleRequest = new Gson().fromJson(files, SaleRequest.class);
            subtotal = Double.parseDouble(saleRequest.sale.subtotal);
            tax = Double.parseDouble(saleRequest.sale.tax);
        } catch (Exception e) {
            saleResponse = new SaleResponse("1", "Invalid Parameter");
            return saleResponse;
        }
        if (TextUtils.isEmpty(saleRequest.sale.orderID)) {
            saleResponse = new SaleResponse("8", "orderID should not be null");
            return saleResponse;
        }
        saleResponse = new SaleResponse();
        switch (saleRequest.getCardDataSource()) {
            case SWIPE: {
                int ref = Logging.transaction(command, saleRequest.getAmount(), nullString, nullString, TSYS, "",
                        nullString, PENDING, nullString, nullString, command, "", "0", 0.00, 0.00, 0, context);

                Thread startCoreThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mCore = new Core(context);
                        mBankCard = new BankCard(context);
                    }
                });
                startCoreThread.start();

                startTask(context, command, server_status, String.valueOf(saleRequest.sale.amount), ref);
                saleResponse = new SaleResponse("0", TRANSACTION_INITIATED, command, String.valueOf(ref),
                        Double.parseDouble(saleRequest.getAmount()), Double.parseDouble(saleRequest.getTip()));
                return saleResponse;
            }
            case EMV:
                return null;
            case MANUAL: {
                int ref;

                if (BuildConfig.DEBUG) {
                    Log.d("SaleRequest", new Gson().toJson(saleRequest));
                }

                String firstname = null;
                String lastname = null;
                try {
                    String[] name = saleRequest.sale.cardHolderName.split(" ");
                    firstname = name[0];
                    lastname = name[1];
                } catch (Exception e) {
                    saleResponse = new SaleResponse("12", "Invalid CardHolderName");
                    return saleResponse;
                }
                SaleMiddleRequest saleMiddleRequest = new SaleMiddleRequest(deviceID, trans_key, saleRequest.sale.cardDataSource,
                        saleRequest.sale.amount, saleRequest.sale.tip, CURRENCY_CODE, saleRequest.sale.cardNumber,
                        saleRequest.sale.expirationDate, saleRequest.sale.cvv2, saleRequest.sale.addressLine1, saleRequest.sale.zip,
                        saleRequest.sale.orderID, firstname, lastname, TERMINAL_CAPABILITY, TERMINAL_OPERATING_ENVIRONMENT,
                        CARDHOLDER_AUTHENTICATION_METHOD, TERMINAL_AUTHENTICATION_CAPABILITY, TERMINAL_OUTPUT_CAPABILITY, MAX_PIN_LENGTH);

                Gson gson = new GsonBuilder().registerTypeAdapter(SaleMiddleRequest.class, new SaleJsonSerializer()).create();
                String responsedata = null;
                RequestBody body = RequestBody.create(JSON, gson.toJson(saleMiddleRequest));
                responsedata = post(body);

                if (BuildConfig.DEBUG) {
                    Log.d("responsedata", responsedata);
                }

                SaleMiddleResponse saleMiddleResponse = new SaleMiddleResponse();
                saleMiddleResponse = gson.fromJson(responsedata, SaleMiddleResponse.class);
                double totalAmount = Double.parseDouble(saleMiddleResponse.gettotalAmount());
                double tip = Double.parseDouble(saleMiddleResponse.gettip());

                String error;
                if (saleMiddleResponse.getstatus().equals(PASS)) {

                    error = "0";
                    ref = Logging.transaction(command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                            TSYS, saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                            saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                            command, saleRequest.sale.orderID, saleRequest.getTip(), subtotal, tax, saleRequest.sale.qty, context);
                    if (server_status) {
                        Intent intnet = new Intent(TRANSACTION_APPROVED);
                        intnet.putExtra(AMOUNT, saleMiddleResponse.gettotalAmount());
                        intnet.putExtra(REF, String.valueOf(ref));
                        context.sendBroadcast(intnet);
                    }
                    DBHelper dbHelper = new DBHelper(context);
                    Configuration configuration = new Configuration();
                    try {
                        configuration = dbHelper.getById(Configuration.class, 1);
                        if (configuration.device_type.equals(APT50) && saleRequest.getPrint().equals(Y)) {
                            new PrintThread(ref, context, printer).start();
                        }
                    } catch (SQLException e) {
                        Log.e(TAG, "error make config", e);
                    }

                } else {
                    if (server_status) {
                        Intent intnet = new Intent(TRANSACTION_DECLINED);
                        intnet.putExtra("ErrorMsg", saleMiddleResponse.getresponseMessage());
                        context.sendBroadcast(intnet);
                    }
                    ref = Logging.transaction(command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                            TSYS, saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                            saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                            command, saleRequest.sale.orderID, saleRequest.getTip(), subtotal, tax, saleRequest.sale.qty, context);
                    error = saleMiddleResponse.getresponseCode();
                }

                return new SaleResponse(error, saleMiddleResponse.getresponseMessage(), String.valueOf(ref),
                        saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                        saleMiddleResponse.getstatus(), saleMiddleResponse.getauthCode(), totalAmount,
                        tip, saleMiddleResponse.getaddressVerificationCode(),
                        saleMiddleResponse.getcvvVerificationCode(), saleMiddleResponse.getcardType(),
                        saleMiddleResponse.getmaskedCardNumber(), "CardToken", saleMiddleResponse.getexpirationDate(),
                        saleMiddleResponse.getfirstName(), saleMiddleResponse.getlastName());
            }
        }
        saleResponse = new SaleResponse("10", "Invalid CardDataSource");
        return saleResponse;
    }

    /**
     * Performs a Swiped/MSR Sale on a provided
     * Credit Card
     */
    @Override
    public void saleSwipe(String files, final Context context) {
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        String command = Const.SALE;
        saleRequest = new SaleRequest();
        saleRequest = new Gson().fromJson(files, SaleRequest.class);

        if (BuildConfig.DEBUG) {
            Log.d("saleRequest", new Gson().toJson(saleRequest));
        }

        SaleMiddleRequest saleMiddleRequest = new SaleMiddleRequest(deviceID, trans_key,
                saleRequest.sale.cardDataSource, saleRequest.sale.amount,
                saleRequest.sale.tip, CURRENCY_CODE, saleRequest.sale.track2Data, saleRequest.sale.track1Data, _21, _5,
                saleRequest.sale.orderID, Y, UNKNOWN, ON_MERCHANT_PREMISES_ATTENDED,
                MANUAL_SIGNATURE, TERMINAL_AUTHENTICATION_CAPABILITY, UNKNOWN, UNKNOWN);

        if (BuildConfig.DEBUG) {
            Log.d("salemiddleRequest", new Gson().toJson(saleMiddleRequest));
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(SaleMiddleRequest.class, new SaleSwipeJsonSerializer()).create();

        if (BuildConfig.DEBUG) {
            Log.d("testinggson", gson.toJson(saleMiddleRequest));
        }

        String responsedata = "";

        RequestBody swipebody = RequestBody.create(JSON, gson.toJson(saleMiddleRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        SaleMiddleResponse saleMiddleResponse = new SaleMiddleResponse();
        saleMiddleResponse = gson.fromJson(responsedata, SaleMiddleResponse.class);

        if (saleMiddleResponse.getstatus().equals(PASS)) {
            Logging.updateTransaction(saleResponse.saleResponse.ref, command, saleRequest.getAmount(),
                    saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                    TSYS, saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                    saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                    command, saleRequest.sale.orderID, saleRequest.getTip(), subtotal, tax, saleRequest.sale.qty, context);
            DBHelper dbHelper = new DBHelper(context);
            Configuration configuration;
            try {
                configuration = dbHelper.getById(Configuration.class, 1);
                if (configuration.device_type.equals(APT50) && saleRequest.getPrint().equals(Y)) {
                    new PrintThread(Integer.parseInt(saleResponse.saleResponse.ref), context, printer).start();
                }
            } catch (SQLException e) {
                Log.e(TAG, "error print thread", e);
            }
        } else {
            Logging.updateTransaction(saleResponse.saleResponse.ref, command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                    TSYS, saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                    saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                    command, saleRequest.sale.orderID, saleRequest.getTip(), subtotal, tax, saleRequest.sale.qty, context);
        }
        Thread r = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "error sleep thread", e);
                }
            }
        };
        r.start();
    }

    /**
     * Performs a tip adjustment on a prior
     * Sale or Authorization
     */
    @Override
    public TipAdjustResponse tip(String files, Context context) {
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        String command = "TipAdjust";

        TipAdjustRequest tipAdjustRequest = new TipAdjustRequest();
        try {
            tipAdjustRequest = new Gson().fromJson(files, TipAdjustRequest.class);
        } catch (Exception e) {
            return new TipAdjustResponse("1", "Invalid Parameter");
        }

        TipAdjustMiddleRequest tipAdjustMiddleRequest = new TipAdjustMiddleRequest(deviceID, trans_key,
                tipAdjustRequest.tipAdjust.tip, tipAdjustRequest.tipAdjust.transactionID, DEVELOPER_ID);
        Gson gson = new GsonBuilder().registerTypeAdapter(TipAdjustMiddleRequest.class, new TipAdjustJsonSerializer()).create();

        String responsedata = null;
        RequestBody body = RequestBody.create(JSON, gson.toJson(tipAdjustMiddleRequest));

        responsedata = post(body);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        TipAdjustMiddleResponse tipAdjustMiddleResponse = new TipAdjustMiddleResponse();
        tipAdjustMiddleResponse = gson.fromJson(responsedata, TipAdjustMiddleResponse.class);

        String error;
        if (tipAdjustMiddleResponse.getStatus().equals(PASS)) {
            error = "0";
        } else error = tipAdjustMiddleResponse.getResponseCode();

        Logging.transaction(command, tipAdjustMiddleResponse.getTotalAmount(), "", "",
                TSYS, tipAdjustMiddleResponse.getResponseCode(), tipAdjustMiddleResponse.getResponseMessage(),
                tipAdjustMiddleResponse.getStatus(), tipAdjustMiddleResponse.getTransactionID(), tipAdjustMiddleResponse.getTransactionTimestamp(),
                command, "", tipAdjustMiddleResponse.getTip(), 0.00, 0.00, 0, context);

        TipAdjustResponse tipAdjustResponse = new TipAdjustResponse(error, tipAdjustMiddleResponse.getResponseMessage(),
                tipAdjustMiddleResponse.getTotalAmount(), tipAdjustMiddleResponse.getTip(), tipAdjustMiddleResponse.getTransactionID(),
                tipAdjustMiddleResponse.getTransactionTimestamp());

        if (BuildConfig.DEBUG) {
            Log.d("TipAdjust_Response", new Gson().toJson(tipAdjustResponse));
        }

        return tipAdjustResponse;
    }

    /**
     * Returns the funds on a prior Sale or Authorization
     */
    @Override
    public ReturnResponse return_(String files, final Context context, Boolean server_status) {

        String errorcheck = Settings.getProfileInstance(context).error;

        if (errorcheck.equals(ERROR_CODE_900)) {
            String errormsg = Settings.getProfileInstance(context).errorMsg;
            return new ReturnResponse(errorcheck, errormsg);
        }
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;

        if (trans_key == null || deviceID == null) {
            String error = ERROR_CODE_901;
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            return new ReturnResponse(errorcheck, errormsg);
        }
        String command = Const.RETURN;
        String nullString = "";
        new Thread() {
            @Override
            public void run() {
                printer = new Printer(context);
            }
        }.start();
        returnRequest = new ReturnRequest();

        try {
            returnRequest = new Gson().fromJson(files, ReturnRequest.class);

        } catch (Exception e) {
            returnResponse = new ReturnResponse("1", "Invalid Parameter");
            return returnResponse;
        }

        returnResponse = new ReturnResponse();

        if (BuildConfig.DEBUG) {
            Log.d("returnRequest", new Gson().toJson(returnRequest));
        }

        switch (returnRequest.getCardDataSource()) {
            case SWIPE: {
                int ref = Logging.transaction(command, returnRequest.getAmount(), nullString, nullString, TSYS, "",
                        nullString, PENDING, nullString, nullString, command, "", "0", 0.00, 0.00, 0, context);

                startTask(context, command, server_status, returnRequest.return_.amount, ref);
                returnResponse = new ReturnResponse("0", TRANSACTION_INITIATED, command, String.valueOf(ref),
                        Double.parseDouble(returnRequest.getAmount()));
                return returnResponse;
            }
            case EMV:
                return null;
            case MANUAL: {
                int ref;
                RequestBody body;
                String responsedata = null;
                if (returnRequest.return_.transactionID != null) {
                    ReturnMiddleRequest returnMiddleRequest = new ReturnMiddleRequest(deviceID, trans_key, returnRequest.getAmount(),
                            returnRequest.return_.transactionID);

                    if (BuildConfig.DEBUG) {
                        Log.d("transactionID", new Gson().toJson(returnMiddleRequest));
                    }

                    Gson gson = new GsonBuilder().registerTypeAdapter(ReturnMiddleRequest.class, new ReturnReferenceJsonSerializer()).create();
                    body = RequestBody.create(JSON, gson.toJson(returnMiddleRequest));
                } else {
                    ReturnMiddleRequest returnMiddleRequest = new ReturnMiddleRequest(deviceID, trans_key, MANUAL,
                            returnRequest.getAmount(), returnRequest.return_.cardNumber, returnRequest.return_.expirationDate,
                            returnRequest.return_.cvv2, TERMINAL_CAPABILITY, TERMINAL_OPERATING_ENVIRONMENT, CARDHOLDER_AUTHENTICATION_METHOD,
                            TERMINAL_AUTHENTICATION_CAPABILITY, TERMINAL_OPERATING_ENVIRONMENT, MAX_PIN_LENGTH, DEVELOPER_ID);
                    Gson gson = new GsonBuilder().registerTypeAdapter(ReturnMiddleRequest.class, new ReturnJsonSerializer()).create();
                    body = RequestBody.create(JSON, gson.toJson(returnMiddleRequest));
                }
                responsedata = post(body);

                if (BuildConfig.DEBUG) {
                    Log.d("responsedata", responsedata);
                }

                ReturnMiddleResponse returnMiddleResponse = new ReturnMiddleResponse();
                returnMiddleResponse = new Gson().fromJson(responsedata, ReturnMiddleResponse.class);

                String error;

                if (returnMiddleResponse.getStatus().equals(PASS)) {

                    error = "0";
                    ref = Logging.transaction(command, returnRequest.getAmount(), returnMiddleResponse.getAuthCode(), returnMiddleResponse.getCardType(),
                            TSYS, returnMiddleResponse.getResponseCode(), returnMiddleResponse.getResponseMessage(),
                            returnMiddleResponse.getStatus(), returnMiddleResponse.getTransactionID(), returnMiddleResponse.getTransactionTimestamp(),
                            command, "", "0", 0, 0, 0, context);
                    if (server_status) {
                        Intent intnet = new Intent(TRANSACTION_APPROVED);
                        intnet.putExtra(AMOUNT, returnMiddleResponse.getReturnedAmount());
                        intnet.putExtra(REF, String.valueOf(ref));
                        context.sendBroadcast(intnet);
                    }
                    DBHelper dbHelper = new DBHelper(context);
                    Configuration configuration = new Configuration();
                    try {
                        configuration = dbHelper.getById(Configuration.class, 1);
                        if (configuration.device_type.equals(APT50) && saleRequest.getPrint().equals(Y)) {
                            new PrintThread(ref, context, printer).start();
                        }
                    } catch (SQLException e) {
                        Log.e(TAG, "error check device type", e);
                    }
                } else {
                    if (server_status) {
                        Intent intnet = new Intent(TRANSACTION_DECLINED);
                        intnet.putExtra("ErrorMsg", returnMiddleResponse.getResponseMessage());
                        context.sendBroadcast(intnet);
                    }
                    ref = Logging.transaction(command, returnRequest.getAmount(), returnMiddleResponse.getAuthCode(), returnMiddleResponse.getCardType(),
                            TSYS, returnMiddleResponse.getResponseCode(), returnMiddleResponse.getResponseMessage(),
                            returnMiddleResponse.getStatus(), returnMiddleResponse.getTransactionID(), returnMiddleResponse.getTransactionTimestamp(),
                            command, "", "0", 0, 0, 0, context);
                    error = returnMiddleResponse.getResponseCode();

                }

                ReturnResponse returnResponse = new ReturnResponse(returnMiddleResponse.getCardType(), error, returnMiddleResponse.getResponseMessage(),
                        String.valueOf(ref), returnMiddleResponse.getExpirationDate(), returnMiddleResponse.getMaskedCardNumber(),
                        Double.parseDouble(returnMiddleResponse.getReturnedAmount()),
                        returnMiddleResponse.getTransactionID(), returnMiddleResponse.getTransactionTimestamp());

                if (BuildConfig.DEBUG) {
                    Log.d("Return_Response", new Gson().toJson(returnResponse));
                }

                return returnResponse;
            }
        }

        return null;
    }

    /**
     * Performs a Return on a swipe transaction
     * This is return with no reference
     */
    @Override
    public void returnSwipe(String files, final Context context, Boolean server_status) {
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;
        String command = Const.RETURN;
        returnRequest = new ReturnRequest();

        returnRequest = new Gson().fromJson(files, ReturnRequest.class);

        if (BuildConfig.DEBUG) {
            Log.d("returnRequest", new Gson().toJson(returnRequest));
        }

        ReturnMiddleRequest returnMiddleRequest = new ReturnMiddleRequest(deviceID, trans_key, returnRequest.return_.cardDataSource,
                returnRequest.getAmount(), CURRENCY_CODE, returnRequest.return_.track2Data,
                _21, _5, "", Y, UNKNOWN, ON_MERCHANT_PREMISES_ATTENDED, MANUAL_SIGNATURE,
                TERMINAL_AUTHENTICATION_CAPABILITY, UNKNOWN, UNKNOWN);

        if (BuildConfig.DEBUG) {
            Log.d("returnRequest", new Gson().toJson(returnMiddleRequest));
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(ReturnMiddleRequest.class, new ReturnSwipeJsonSerializer()).create();

        if (BuildConfig.DEBUG) {
            Log.d("testinggson", gson.toJson(returnMiddleRequest));
        }

        String responsedata = "";

        RequestBody swipebody = RequestBody.create(JSON, gson.toJson(returnMiddleRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        ReturnMiddleResponse returnMiddleResponse = new ReturnMiddleResponse();
        returnMiddleResponse = gson.fromJson(responsedata, ReturnMiddleResponse.class);

        if (returnMiddleResponse.getStatus().equals(PASS)) {
            Logging.updateTransaction(returnResponse.returnResponse.ref, command, returnRequest.getAmount(), returnMiddleResponse.getAuthCode(), returnMiddleResponse.getCardType(),
                    TSYS, returnMiddleResponse.getResponseCode(), returnMiddleResponse.getResponseMessage(),
                    returnMiddleResponse.getStatus(), returnMiddleResponse.getTransactionID(), returnMiddleResponse.getTransactionTimestamp(),
                    command, "", "0", 0, 0, 0, context);
            DBHelper dbHelper = new DBHelper(context);
            Configuration configuration = new Configuration();
            try {
                configuration = dbHelper.getById(Configuration.class, 1);
                if (configuration.device_type.equals(APT50) && saleRequest.getPrint().equals(Y)) {
                    new PrintThread(Integer.parseInt(returnResponse.returnResponse.ref), context, printer).start();
                }
            } catch (SQLException e) {
                Log.e(TAG, "error print thread", e);
            }

        } else {
            Logging.updateTransaction(returnResponse.returnResponse.ref, command, returnRequest.getAmount(),
                    returnMiddleResponse.getAuthCode(), returnMiddleResponse.getCardType(),
                    TSYS, returnMiddleResponse.getResponseCode(), returnMiddleResponse.getResponseMessage(),
                    returnMiddleResponse.getStatus(), returnMiddleResponse.getTransactionID(), returnMiddleResponse.getTransactionTimestamp(),
                    command, "", "0", 0, 0, 0, context);
        }
    }

    /**
     * Parses a track into Track1/Track2
     */
    private String[] parseTrack(String track) {
        String[] trackdata = track.split("\\|"); // Split off tracks one and two
        String track1 = trackdata[0];
        String track2 = trackdata[1];  // Strip off the ';', which is the second track's leading sentinel.
        return new String[]{track1, track2};
    }

    @Override
    public ReportResponse report(String files, Context context) {
        String deviceID = Settings.getProfileInstance(context).device.deviceid;
        String trans_key = Settings.getProfileInstance(context).device.trans_key;

        ReportRequest reportRequest = new ReportRequest();
        ReportResponse reportResponse = new ReportResponse();
        try {
            reportRequest = new Gson().fromJson(files, ReportRequest.class);

            if (BuildConfig.DEBUG) {
                Log.d("Report_Request", new Gson().toJson(reportRequest));
            }

        } catch (Exception e) {
            reportResponse = new ReportResponse("1", "Invalid Parameter", null);
            return reportResponse;
        }

        ReportMiddleRequest reportMiddleRequest = new ReportMiddleRequest(deviceID, trans_key, reportRequest.getReportData.reportName);
        Gson gson = new GsonBuilder().registerTypeAdapter(ReportMiddleRequest.class, new ReportJsonSerializer()).create();

        String responsedata = null;
        RequestBody body = RequestBody.create(JSON, gson.toJson(reportMiddleRequest));

        responsedata = post(body);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        ReportMiddleResponse reportMiddleResponse = new ReportMiddleResponse();
        reportMiddleResponse = gson.fromJson(responsedata, ReportMiddleResponse.class);

        if (BuildConfig.DEBUG) {
            Log.d("Report_MiddleResponse", new Gson().toJson(reportMiddleResponse));
        }

        String start_date = reportRequest.getReportData.start_date;
        String end_date = reportRequest.getReportData.end_date;
        String error;
        if (reportMiddleResponse.getStatus().equals(PASS)) {
            error = "0";
            ArrayList<ReportItem> row = reportMiddleResponse.GetReportDataResponse.reportData.ROW;
            ArrayList<ReportItem> reportdata = new ArrayList<>();
            if (TextUtils.isEmpty(start_date)) {
                start_date = "2000-01-01";
            }
            if (TextUtils.isEmpty(end_date)) {
                end_date = "2050-01-01";
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.US);

            for (int i = 0; i < row.size(); i++) {
                String transdate = row.get(i).batchDate;
                try {
                    Date str_startdate = sdf.parse(start_date);
                    Date str_enddate = sdf.parse(end_date);
                    Date str_transdate = sdf.parse(transdate);

                    if (BuildConfig.DEBUG) {
                        Log.d("str_transdate", String.valueOf(str_transdate) + "   " + String.valueOf(str_transdate.getTime()));
                        Log.d("str_startdate", String.valueOf(str_startdate) + "   " + String.valueOf(str_startdate.getTime()));
                        Log.d("str_enddate", String.valueOf(str_enddate) + "   " + String.valueOf(str_enddate.getTime()));
                    }

                    if ((str_startdate.getTime() - 86440000) < str_transdate.getTime() && str_transdate.getTime() < (str_enddate.getTime() + 86440000)) {
                        reportdata.add(row.get(i));
                    }

                } catch (ParseException e) {
                    Log.e(TAG, "parse error", e);
                }
            }
            reportResponse = new ReportResponse(error, reportMiddleResponse.getResponseMessage(), reportdata);
            return reportResponse;
        } else {
            error = reportMiddleResponse.getResponseCode();
            reportResponse = new ReportResponse(error, reportMiddleResponse.getResponseMessage(), null);
            return reportResponse;
        }
    }

    /**
     * Posts a supplied transaction to TSYS
     * Returns the response:
     * NOTE: Prior to going live this must be set to production end point
     */
    private String post(RequestBody requestBody) {
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(CERT_PATTERN, CERT_PINS[0])
                .add(CERT_PATTERN, CERT_PINS[1])
                .add(CERT_PATTERN, CERT_PINS[2])
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner).build();

        Request request = new Request.Builder()
                .header("content-type", "application/json")
                .url(URL)
                .post(requestBody)
                .build();

        Response response = null;
        String respon = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (body != null) {
                respon = body.string();
            }
        } catch (IOException e) {
            Log.e(TAG, "io error", e);
            sendErrorMessage(String.valueOf(e.getMessage()));
        }
        return respon;
    }

    /**
     * Generates a new trans_key with TSYS
     */

    public class PrintThread extends Thread {

        Context mcontext;
        int mref;
        Printer mPrinter;

        public PrintThread(int ref, Context context, Printer printer) {
            this.mcontext = context;
            this.mref = ref;
            this.mPrinter = printer;
        }

        @Override
        public void run() {
            int result = ToolsUtil.printNormal(mref, mcontext, mPrinter);
            String result_str = null;

            switch (result) {
                case 1:
                    result_str = "Parameter Error";
                    break;
                case 6:
                    result_str = "Not available";
                    break;
                case 138:
                    result_str = "Out of Paper";
                    break;
                case 139:
                    result_str = "Overheat";
                    break;
            }

            if (result_str != null) {
                Intent intnet = new Intent("Printer Status");
                intnet.putExtra("Status", result_str);
                mcontext.sendBroadcast(intnet);
            }
        }
    }

    @Override
    public KeyResponse genKey(String files) {
        KeyRequest keyRequest;
        KeyResponse keyResponse;

        try {
            keyRequest = new Gson().fromJson(files, KeyRequest.class);
        } catch (Exception e) {
            keyResponse = new KeyResponse("FAIL", "1", "Invalid Parameter");
            return keyResponse;
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(KeyRequest.class, new KeyJsonSerializer()).create();

        if (BuildConfig.DEBUG) {
            Log.d("testinggson", gson.toJson(keyRequest));
        }

        String responsedata = "";
        RequestBody swipebody = RequestBody.create(JSON, gson.toJson(keyRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        if (BuildConfig.DEBUG) {
            Log.d("responsedata", responsedata);
        }

        keyResponse = gson.fromJson(responsedata, KeyResponse.class);

        return keyResponse;
    }

    class ReadMagTask extends Thread {

        private Context context;
        private String command;
        private Boolean ser_status;

        private ReadMagTask(Context context, String command, Boolean status) {
            this.context = context;
            this.command = command;
            this.ser_status = status;
        }

        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "thread sleep error", e);
            }

            String decodeData = getMagData(context);

            if (decodeData != null && decodeData.length() != 0) {
                if (BuildConfig.DEBUG) {
                    Log.d("DECODE", decodeData);
                }

                updateLogInfo(decodeData, context, command, ser_status);
            } else {
                updateLogInfo("", context, command, ser_status);
            }
//            updateLogInfo("B4418409417562981^TEAM RESEARCH INC/TEST T^2103201007400000000000583000000|4418409417562981=210320100000583|null",context,command,true);
        }
    }

    public String getMagData(Context context) {
        byte[] respdata = new byte[1024];
        int[] resplen = new int[1];
        int retvalue = -1;
        byte[] dataOutStr1 = new byte[77];
        int[] dataOut1len = new int[1];
        byte[] dataOutStr2 = new byte[38];
        int[] dataOut2len = new int[1];
        byte[] dataOutStr3 = new byte[115];
        int[] dataOut3len = new int[1];

        try {
            if (BuildConfig.DEBUG) {
                Log.v(TAG, "CardReader, getMagData(), buzzer--->>>");
            }
            mCore.buzzer();

        } catch (RemoteException e) {
            Log.e(TAG, "remote exception", e);
        }

        try {
            retvalue = mBankCard.readCard(BankCard.CARD_TYPE_NORMAL, BankCard.CARD_MODE_MAG, 60, respdata, resplen, "app1");
        } catch (RemoteException e) {
            Log.e(TAG, "remote exception", e);
        }

        switch (respdata[0]) {
            case 0x03:
                Intent intnet = new Intent(SWIPE_YOUR_CC_NOW);
                intnet.putExtra("ErrorMsg", "Error, your swipe was incomplete. Please try again");
                context.sendBroadcast(intnet);
                return "";
            case 0x04:
                intnet = new Intent(SWIPE_YOUR_CC_NOW);
                intnet.putExtra("ErrorMsg", "Error, User cancelled credit card swipe! Please try again");
                context.sendBroadcast(intnet);
                return "";
            case 0x00:
                mBankCard.parseMagnetic(respdata, resplen[0], dataOutStr1, dataOut1len, dataOutStr2, dataOut2len, dataOutStr3, dataOut3len);

                byte[] data1 = new byte[dataOut1len[0]];
                System.arraycopy(dataOutStr1, 0, data1, 0, dataOut1len[0]);
                String parsedata1 = new String(data1);

                byte[] data2 = new byte[dataOut2len[0]];
                System.arraycopy(dataOutStr2, 0, data2, 0, dataOut2len[0]);
                String parsedata2 = new String(data2);

                byte[] data3 = new byte[dataOut3len[0]];
                System.arraycopy(dataOutStr3, 0, data3, 0, dataOut3len[0]);
                String parsedata3 = new String(data3);
                String finaldata = parsedata1 + "|" + parsedata2 + "|" + parsedata3;

                if (BuildConfig.DEBUG) {
                    Log.v(TAG, "CardReader, getMagData, final data = " + finaldata);
                }

                if (retvalue == 0) {
                    return finaldata;
                } else {
                    return "";
                }
            default:
                return "";
        }
    }

    private void startTask(final Context context, String command, Boolean
            server_status, String amount, int ref) {
            mReadMagTask = new ReadMagTask(context, command, server_status);
            mReadMagTask.start();
            if (server_status) {
                Intent swipeintnet = new Intent("PLEASE SWIPE YOUR CREDIT CARD");
                swipeintnet.putExtra(AMOUNT, amount);
                swipeintnet.putExtra(REF, ref);
                context.sendBroadcast(swipeintnet);
            }
    }

    private void stopTask(final Context context) {

        if (mReadMagTask != null) {
            mReadMagTask.interrupt();
            mReadMagTask = null;
        }
    }

    /**
     * Captures Magnetic card data
     * And passes it onto callback
     */
    private void updateLogInfo(String msg, final Context context, String command, Boolean status) {
        /**
         * MSR Card Read events, action based on
         * Transaction Type
         */
        if (BuildConfig.DEBUG) {
            Log.d("CardReaderResult", msg);
        }

        if (!msg.equals("")) {
            Intent intnet = new Intent("Authorizing Card");
            context.sendBroadcast(intnet);

            if (BuildConfig.DEBUG) {
                Log.d("Broadcast success", "True");
            }

            switch (command) {
                case Const.AUTH: {

                    String[] trackdata = parseTrack(msg);

                    if (BuildConfig.DEBUG) {
                        Log.d("track1", trackdata[0]);
                        Log.d("track2", trackdata[1]);
                    }

                    if (trackdata[0] != null && trackdata[0].length() > 0) {
                        authorizeRequest.Authorize.track1Data = trackdata[0];
                    } else if (trackdata[1] != null && trackdata[1].length() > 0) {
                        authorizeRequest.Authorize.track2Data = trackdata[1];
                    }

                    //authorizeRequest.Authorize.Track2Data = msg;
                    String files = new Gson().toJson(authorizeRequest);
                    authorizeSwipe(files, context, status);

                    break;
                }
                case Const.SALE: {
                    String[] trackdata = parseTrack(msg);

                    if (BuildConfig.DEBUG) {
                        Log.d("track1", trackdata[0]);
                        Log.d("track2", trackdata[1]);
                    }

                    if (trackdata[0] != null && trackdata[0].length() > 0) {
                        saleRequest.sale.track1Data = trackdata[0];
                    } else if (trackdata[1] != null && trackdata[1].length() > 0) {
                        saleRequest.sale.track2Data = trackdata[1];
                    }

                    //authorizeRequest.Authorize.Track2Data = msg;
                    String files = new Gson().toJson(saleRequest);
                    saleSwipe(files, context);
                    break;
                }
                case Const.RETURN: {
                    returnRequest.return_.track2Data = msg;
                    String files = new Gson().toJson(returnRequest);
                    returnSwipe(files, context, status);
                    break;
                }
            }
        }
    }

    private void InitiateEMV(final Context context)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                setAID(context);
            }
        }).start();
    }

    private void setCAPK(Context context) {
        try {
            emvCore.delAllCAPK();//delete capk
        }catch (RemoteException ex){
            ex.printStackTrace();
        }
        for (int i=0; i<CAPK_DATA.length; i++){
            TLVList tlvList = TLVList.fromBinary(CAPK_DATA[i]);
            try {
                CAPK capk = new CAPK(context);

                capk.setRID(tlvList.getTLV("9F06").getValue());// rid
                capk.setKeyID(tlvList.getTLV("9F22").getValue());//认证中心公钥索引(CA Public Key Index)

                if (tlvList.getTLV("DF05") != null) {
                    capk.setExpDate(tlvList.getTLV("DF05").getValue());//认证中心公钥有效期(CA Public Key period of validity)
                    Log.e("acpkData", "ExpDate--->" + capk.getExpDate() + "\ntag-->" + tlvList.getTLV("DF05").getValue());
                }
                if (tlvList.getTLV("DF06") != null) {
                    capk.setHashInd(tlvList.getTLV("DF06").getValue());//认证中心公钥哈什算法标识(CA Public Key Hash algorithm identification)
                    Log.e("acpkData", "HashInd--->" + capk.getHashInd() + "\ntag-->" + tlvList.getTLV("DF06").getValue());
                }
                if (tlvList.getTLV("DF07") != null) {
                    capk.setArithInd(tlvList.getTLV("DF07").getValue());//认证中心公钥算法标识(CA Public Key Algorithm identification)
                    Log.e("acpkData", "ArithInd--->" + capk.getArithInd() + "\ntag-->" + tlvList.getTLV("DF07").getValue());
                }
                if (tlvList.getTLV("DF02") != null) {
                    capk.setModul(tlvList.getTLV("DF02").getValue());//认证中心公钥模(CA Public Key module)
                    Log.e("acpkData", "tModul--->" + capk.getModul() + "\ntag-->" + tlvList.getTLV("DF02").getValue());
                }
                if (tlvList.getTLV("DF04") != null) {
                    capk.setExponent(tlvList.getTLV("DF04").getValue());//认证中心公钥指数(CA Public Key exponent)
                    Log.e("acpkData", "Exponent--->" + capk.getExponent() + "\ntag-->" + tlvList.getTLV("DF04").getValue());
                }
                if (tlvList.getTLV("DF03") != null) {
                    capk.setCheckSum(tlvList.getTLV("DF03").getValue().substring(0, 40));//认证中心公钥校验值(CA Public Key Check value)
                    Log.e("acpkData", tlvList.getTLV("DF03").getLength() + "----" + tlvList.getTLV("DF03").getTLLength() + "CheckSum--->" + capk.getCheckSum() + "\ntag-->" + tlvList.getTLV("DF03").getValue() + "\ndataSize" + capk.toByteArray().length);
                }
                Log.e("addCapk", tlvList.toString() + "\n" + "capkSize-->" + capk.toByteArray().length + "\n" + capk.print());
                byte[] capkByte = capk.toByteArray();
                int result = emvCore.addCAPK(capkByte);
                Log.e("addCapk_Result", result + "");
            } catch (RemoteException ex){
                ex.printStackTrace();
            }
        }
    }

    private void setAID(Context context) {
        try {
            emvCore.delAllAID();//delete Aid
        }catch (RemoteException ex){
            ex.printStackTrace();
        }
        for (int i=0; i<AID_DATA.length; i++){
            TLVList tlvList =TLVList.fromBinary(AID_DATA[i]);
            try {
                EmvAppList emvAppList  =new EmvAppList(context);
                emvAppList.setAID(tlvList.getTLV("9F06").getValue());//aid
                if (tlvList.getTLV("DF01") != null) {
                    emvAppList.setSelFlag(tlvList.getTLV("DF01").getValue());//选择应用标识
                }
                if (tlvList.getTLV("9F09") != null) {
                    emvAppList.setVersion(tlvList.getTLV("9F09").getValue());//应用版本
                }
                if (tlvList.getTLV("DF11") != null) {
                    emvAppList.setTACDefault(tlvList.getTLV("DF11").getValue());//TAC－缺省
                }
                if (tlvList.getTLV("DF12") != null) {
                    emvAppList.setTACOnline(tlvList.getTLV("DF12").getValue());//TAC－联机
                }
                if (tlvList.getTLV("DF13") != null) {
                    emvAppList.setTACDenial(tlvList.getTLV("DF13").getValue());//TAC－拒绝
                }
                if (tlvList.getTLV("9F1B") != null) {
                    emvAppList.setFloorLimit(Long.parseLong(tlvList.getTLV("9F1B").getValue()));//最低限额
                }
                if (tlvList.getTLV("DF15") != null) {
                    emvAppList.setThreshold(Long.parseLong(tlvList.getTLV("DF15").getValue()));//偏置随机选择的阈值
                }
                if (tlvList.getTLV("DF16") != null) {
                    emvAppList.setMaxTargetPer(Integer.parseInt(tlvList.getTLV("DF16").getValue()));//偏置随机选择的最大目标百分数
                }
                if (tlvList.getTLV("DF17") != null) {
                    emvAppList.setTargetPer(Integer.parseInt(tlvList.getTLV("DF17").getValue()));//随机选择的目标百分数
                }
                if (tlvList.getTLV("DF14") != null) {
                    emvAppList.setDDOL(tlvList.getTLV("DF14").getValue());//缺省DDOL
                }
                if (tlvList.getTLV("DF18") != null) {
                    emvAppList.setBOnlinePin(Integer.parseInt(tlvList.getTLV("DF18").getValue()));
                }
                if (tlvList.getTLV("9F7B") != null) {
                    emvAppList.setEC_TermLimit(Long.parseLong(tlvList.getTLV("9F7B").getValue()));//终端电子现金交易限额
                }
                if (tlvList.getTLV("DF19") != null) {
                    emvAppList.setCL_FloorLimit(Long.parseLong(tlvList.getTLV("DF19").getValue()));//非接触读写器脱机最低限额
                }
                if (tlvList.getTLV("DF20") != null) {
                    emvAppList.setCL_TransLimit(Long.parseLong(tlvList.getTLV("DF20").getValue()));//非接触读写器交易限额
                }
                if (tlvList.getTLV("DF21") != null) {
                    emvAppList.setCL_CVMLimit(Long.parseLong(tlvList.getTLV("DF21").getValue())); //非接触终端CVM限额
                }
                emvCore.addAID(emvAppList.toByteArray());
                setCAPK(context);
            } catch (RemoteException ex){
                ex.printStackTrace();
            }
        }
    }

}
