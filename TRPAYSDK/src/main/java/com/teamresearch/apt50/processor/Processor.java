package com.teamresearch.apt50.processor;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.teamresearch.apt50.BuildConfig;
import com.teamresearch.apt50.TRPAY;
import com.teamresearch.apt50.TRPAYProcessorsMethods;
import com.teamresearch.apt50.database.DBHelper;
import com.teamresearch.apt50.database.Transaction;
import com.teamresearch.apt50.model.PrintRequest;
import com.teamresearch.apt50.model.PrintResponse;
import com.teamresearch.apt50.model.SearchRequest;
import com.teamresearch.apt50.model.SearchResponse;
import com.teamresearch.apt50.model.StatusRequest;
import com.teamresearch.apt50.model.StatusResponse;
import com.teamresearch.apt50.utils.Const;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class Processor implements TRPAYProcessorsMethods {

    protected static final String TAG = Processor.class.getCanonicalName();

    protected TRPAY trpay;

    public Processor(TRPAY trpay) {
        this.trpay = trpay;
    }

    private Processor() {
    }

    protected void sendErrorMessage(String error) {
        Intent errorIntent = new Intent(Const.ERROR_CATCH_FILTER);
        errorIntent.putExtra(Const.ERROR, error);
        trpay.config.context.sendBroadcast(errorIntent);
    }

    /**
     * Prints a prior Ref from the
     * Transaction Table
     */
    @Override
    public PrintResponse print(String files, Context context) {
        PrintResponse printResponse = new PrintResponse();
        try {
            new Gson().fromJson(files, PrintRequest.class);
        } catch (Exception e) {
            printResponse = new PrintResponse("1", "Invalid Parameter");
            return printResponse;
        }
        return printResponse;
    }

    /**
     * Returns the status on a prior Transaction
     */
    @Override
    public StatusResponse status(String files, Context context) {
        StatusRequest statusRequest;

        try {
            statusRequest = new Gson().fromJson(files, StatusRequest.class);
        } catch (Exception e) {
            return new StatusResponse("1", "Invalid Parameter");
        }

        DBHelper dbHelper = new DBHelper(context);
        Transaction transaction = new Transaction();

        if (BuildConfig.DEBUG) {
            Log.d("StatusRef", String.valueOf(statusRequest.request.ref));
        }

        try {
            transaction = dbHelper.getById(Transaction.class, statusRequest.request.ref);
        } catch (SQLException e) {
            StatusResponse statusResponse;
            statusResponse = new StatusResponse("2", "Can't find data");
            return statusResponse;
        }

        if (BuildConfig.DEBUG) {
            Log.d("Transaction_class", new Gson().toJson(transaction));
//        String qty=(String.valueOf(transaction.qty)==null?"0":String.valueOf(transaction.qty));
            Log.d("Transaction qty", String.valueOf(transaction.qty));
            Log.d("Transaction subtotal", String.valueOf(transaction.subtotal));
            Log.d("Transaction tax", String.valueOf(transaction.tax));
        }

        return new StatusResponse(transaction.command, transaction.amount, transaction.auth_code,
                transaction.card_payment_type, transaction.processor, transaction.response,
                transaction.response_message, transaction.status, transaction.trans_id,
                transaction.trans_date, transaction.trans_type, String.valueOf(transaction.qty),
                String.valueOf(transaction.subtotal), String.valueOf(transaction.tax));

    }

    /**
     * Searches against the Transaction Database
     * For matching records
     */
    @Override
    public SearchResponse search(String files, Context context) {
        SearchRequest searchRequest;
        SearchResponse searchResponse = new SearchResponse();

        try {
            searchRequest = new Gson().fromJson(files, SearchRequest.class);
        } catch (Exception e) {
            searchResponse = new SearchResponse("1", "Invalid Parameter");
            return searchResponse;
        }

        if (BuildConfig.DEBUG) {
            Log.d("searchRequest", new Gson().toJson(searchRequest));
        }

        String trans_id = searchRequest.search.trans_id;
        String trans_type = searchRequest.search.trans_type;
        String start_date = searchRequest.search.start_date;
        String end_date = searchRequest.search.end_date;
        ArrayList<Transaction> response = null;
        DBHelper dbHelper = new DBHelper(context);
        if (trans_id != null && !trans_id.equals("")) {
            response = new ArrayList<>();

            try {
                List<Transaction> alltransactions = dbHelper.getAll(Transaction.class);
                for (int i = 0; i < alltransactions.size(); i++) {
                    String transid = alltransactions.get(i).getTrans_id();
                    if (transid.equals(trans_id)) response.add(alltransactions.get(i));
                }

                if (BuildConfig.DEBUG) {
                    Log.d("Search_Response", new Gson().toJson(response));
                }

                searchResponse = new SearchResponse("0", "Success", response);
            } catch (SQLException e) {
                Log.e(TAG, "sql error", e);
                searchResponse = new SearchResponse("1", "Invalid TransactionID Parameter");
            }
            return searchResponse;
        } else {
            if (trans_type != null && !trans_type.equals("")) {
                response = new ArrayList<>();

                if (BuildConfig.DEBUG) {
                    Log.d("trans_type", "Success");
                }

                try {
                    List<Transaction> alltransactions = dbHelper.getAll(Transaction.class);
                    for (int i = 0; i < alltransactions.size(); i++) {
                        String transtype = alltransactions.get(i).getTrans_type();
                        if (trans_type.equals(transtype)) response.add(alltransactions.get(i));
                    }
                    searchResponse = new SearchResponse("0", "Success", response);
                } catch (SQLException e) {
                    Log.e(TAG, "sql error", e);
                    searchResponse = new SearchResponse("1", "Invalid TransType Parameter");
                }
            }
            if ((start_date != null && !start_date.equals("")) | (end_date != null && !end_date.equals(""))) {
                List<Transaction> alltransactions = new ArrayList<>();

                if (response == null | (response != null ? response.size() : 0) < 1) {
                    try {
                        response = new ArrayList<>();
                        alltransactions = dbHelper.getAll(Transaction.class);
                    } catch (SQLException e) {
                        Log.e(TAG, "sql error", e);
                    }
                } else {
                    alltransactions = response;
                    response = new ArrayList<>();
                }

                for (int i = 0; i < alltransactions.size(); i++) {
                    String[] transdatearray;
                    if (!TextUtils.isEmpty(alltransactions.get(i).getTrans_date())) {
                        transdatearray = alltransactions.get(i).getTrans_date().split("T");
                        String transdate = transdatearray[0];

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
                        try {

                            if (TextUtils.isEmpty(start_date)) {
                                start_date = "2000-01-01";
                            }
                            if (TextUtils.isEmpty(end_date)) {
                                end_date = "2050-01-01";
                            }

                            if (BuildConfig.DEBUG) {
                                Log.d("trans_string", transdate);
                                Log.d("start_date", start_date);
                                Log.d("start_date", start_date);
                            }

                            Date str_startdate = sdf.parse(start_date);
                            Date str_enddate = sdf.parse(end_date);
                            Date str_transdate = sdf.parse(transdate);

                            if (BuildConfig.DEBUG) {
                                Log.d("str_transdate", String.valueOf(str_transdate) + "   " + String.valueOf(str_transdate.getTime()));
                                Log.d("str_startdate", String.valueOf(str_startdate) + "   " + String.valueOf(str_startdate.getTime()));
                                Log.d("str_enddate", String.valueOf(str_enddate) + "   " + String.valueOf(str_enddate.getTime()));
                            }

                            if ((str_startdate.getTime() - 86440000) < str_transdate.getTime() && str_transdate.getTime() < (str_enddate.getTime() + 86440000)) {
                                response.add(alltransactions.get(i));
                            }

                        } catch (ParseException e) {
                            Log.e(TAG, "parse exception", e);
                        }
                    }
                }

                searchResponse = new SearchResponse("0", "Success", response);
                return searchResponse;
            } else {
                if (trans_type != null) {
                    return searchResponse;
                } else {
                    searchResponse = new SearchResponse("1", "No Found", null);
                    return searchResponse;
                }
            }
        }
    }


}
