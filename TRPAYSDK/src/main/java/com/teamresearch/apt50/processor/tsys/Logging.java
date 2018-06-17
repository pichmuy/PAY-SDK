package com.teamresearch.apt50.processor.tsys;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.teamresearch.apt50.BuildConfig;
import com.teamresearch.apt50.database.DBHelper;
import com.teamresearch.apt50.database.Transaction;

import java.sql.SQLException;
import java.util.List;


public class Logging {

    private static final String TAG = Logging.class.getCanonicalName();

    public static int transaction(String command, String amount, String auth_code, String card_type,
                                  String processor, String response, String response_message, String status,
                                  String trans_id, String trans_date, String trans_type, String orderid, String tip, double subtotal,
                                  double tax, int qty, Context context) {
        int transID = 0;

        DBHelper dbHelper = new DBHelper(context);
        Transaction transaction = new Transaction();
        transaction.setCommand(command);
        transaction.setAmount(amount);
        transaction.setAuth_code(auth_code);
        transaction.setCard_payment_type(card_type);
        transaction.setProcessor(processor);
        transaction.setResponse(response);
        transaction.setResponse_message(response_message);
        transaction.setStatus(status);
        transaction.setTrans_id(trans_id);
        transaction.setTrans_date(trans_date);
        transaction.setTrans_type(trans_type);
        transaction.setOrderID(orderid);
        transaction.setTip(tip);
        transaction.setSubtotal(subtotal);
        transaction.setTax(tax);
        transaction.setQty(qty);

        try {
            dbHelper.createOrUpdate(transaction);
        } catch (SQLException e) {
            Log.e(TAG, "error create or update transaction object in db", e);
        }

        try {
            List<Transaction> alltrans = dbHelper.getAll(Transaction.class);
            transID = alltrans.size();
        } catch (SQLException e) {
            Log.e(TAG, "error get all transaction object from db", e);
        }
        return transID;
    }

    public static void updateTransaction(String ref, String command, String amount, String auth_code, String card_type,
                                         String processor, String response, String response_message, String status,
                                         String trans_id, String trans_date, String trans_type, String orderid, String tip, double subtotal,
                                         double tax, int qty, Context context) {
        DBHelper dbHelper = new DBHelper(context);
        Transaction transaction = new Transaction();
        transaction.setId(Integer.parseInt(ref));
        transaction.setCommand(command);
        transaction.setAmount(amount);
        transaction.setAuth_code(auth_code);
        transaction.setCard_payment_type(card_type);
        transaction.setProcessor(processor);
        transaction.setResponse(response);
        transaction.setResponse_message(response_message);
        transaction.setStatus(status);
        transaction.setTrans_id(trans_id);
        transaction.setTrans_date(trans_date);
        transaction.setTrans_type(trans_type);
        transaction.setOrderID(orderid);
        transaction.setTip(tip);
        transaction.setSubtotal(subtotal);
        transaction.setTax(tax);
        transaction.setQty(qty);

        try {
            dbHelper.createOrUpdate(transaction);

            if (BuildConfig.DEBUG) {
                Log.d("transaction", new Gson().toJson(transaction));
            }
        } catch (SQLException e) {
            Log.e(TAG, "error create or update transaction object in db", e);
        }
    }
}
