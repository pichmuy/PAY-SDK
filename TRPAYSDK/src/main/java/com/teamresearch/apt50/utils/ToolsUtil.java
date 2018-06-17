package com.teamresearch.apt50.utils;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.teamresearch.apt50.database.DBHelper;
import com.teamresearch.apt50.database.Merchant;
import com.teamresearch.apt50.database.Transaction;

import java.sql.SQLException;

import wangpos.sdk4.libbasebinder.Printer;


public class ToolsUtil {

    private static final String TAG = ToolsUtil.class.getCanonicalName();

    private static final int MEDIUM_SIZE = 18 * 2;
    private static final int FONT_SIZE = 25;

    /**
     * @param context
     * @param printer
     */
    public static int printNormal(int ref, Context context, Printer printer) {
        int[] status = new int[1];
        try {
            printer.getPrinterStatus(status);
        } catch (RemoteException e) {
            Log.e(TAG, "get printer status", e);
        }

        if (status[0] != 0) {
            return status[0];
        } else {
            try {
                printer.printInit();
                printer.clearPrintDataCache();
            } catch (RemoteException e) {
                Log.e(TAG, "print init", e);
            }
            //clear print cache

            DBHelper dbHelper = new DBHelper(context);
            Merchant merchant = new Merchant();
            Transaction transaction = new Transaction();

            try {
                merchant = dbHelper.getById(Merchant.class, 1);
                transaction = dbHelper.getById(Transaction.class, ref);
            } catch (SQLException e) {
                Log.e(TAG, "error get ids from db", e);
            }

            String Body = transaction.command;
            StringBuilder mediumSpline = new StringBuilder();
            for (int i = 0; i < MEDIUM_SIZE - 5; i++) {
                mediumSpline.append("-");
            }

            try {

                // Set fixed length
                mediumSpline = new StringBuilder("-----------------------------------------------");

                printer.printString(merchant.name, FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString(merchant.address, FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString(merchant.city + "," + merchant.state + " " + merchant.zipcode, FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString((merchant.phone == null ? "" : merchant.phone) + "\n\n", FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString(">>" + Body + " Receipt<<\n\n\n\n", FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString(mediumSpline + "\n\n", FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString("Order Number: " + transaction.orderID + "\nReference: " + ref + "\nOrder Time:"
                        + transaction.trans_date, FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString(mediumSpline + "\n", FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString("D E T A I L S\n", FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString(mediumSpline + "\n\n", FONT_SIZE, Printer.Align.LEFT, true, false);

                String sbTable = ("Qty: " + transaction.qty + "\n") +
                        "Amount: " + (transaction.amount == null ? "0.00" : transaction.amount) + "\n" +
                        "Card Type: " + (transaction.card_payment_type == null ? "" : transaction.card_payment_type) + "\n" +
                        "Transaction ID #: " + (transaction.getAuth_code() == null ? "" : transaction.getAuth_code()) + "\n\n";

                printer.printString(sbTable, FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString(mediumSpline + "\n\n", FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString("I agree to pay the above total amount according to the card issuer agreement.\n\n\n\n\n\n\n\n", FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printString("Signature:\n\n", FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString("___________________________\n\n\n\n\n\n\n\n\n", FONT_SIZE, Printer.Align.LEFT, true, false);
                printer.printString("\n\nwww.teamsable.com\n\n\n\n\n", FONT_SIZE, Printer.Align.CENTER, true, false);
                printer.printPaper(100);
                printer.printFinish();


            } catch (RemoteException e) {
                Log.e(TAG, "error fill printer", e);
            }
            return 0;
        }
    }

    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (char aC : c) {
            len++;
            if (!isLetter(aC)) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0;
    }


}
