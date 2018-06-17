package com.teamresearch.apt50.database;

import com.j256.ormlite.field.DatabaseField;

abstract class BaseDatabaseModel {

    public static final String ID = "id";
    public static final String TOKEN = "token";
    public static final String SECRET = "secret";
    public static final String ACTIVATION_DATE = "activation_date";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_TYPE = "device_type";
    public static final String MERCHANT_ID = "merchant_id";
    public static final String PROCESSOR = "processor";
    public static final String STATUS = "status";
    public static final String TRANS_KEY = "trans_key";
    public static final String DESCRIPTION = "description";
    public static final String ERROR = "error";
    public static final String IP_ADDRESS = "ipaddress";
    public static final String LOG_DATE = "log_date";
    public static final String MESSAGE = "message";
    public static final String SOURCE = "source";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String ZIPCODE = "zipcode";
    public static final String PHONE = "phone";
    public static final String LANGUAGE = "language";
    public static final String CURRENCY = "currency";
    public static final String COMMAND = "command";
    public static final String AMOUNT = "amount";
    public static final String AUTH_CODE = "auth_code";
    public static final String CARD_PAYMENT_TYPE = "card_payment_type";
    public static final String RESPONSE = "response";
    public static final String RESPONSE_MESSAGE = "response_message";
    public static final String SSTATUS = "Status";
    public static final String TRANS_ID = "trans_id";
    public static final String TRANS_DATE = "trans_date";
    public static final String TRANS_TYPE = "trans_type";
    public static final String ORDER_ID = "orderID";
    public static final String TIP = "tip";
    public static final String SUBTOTAL = "subtotal";
    public static final String TAX = "tax";
    public static final String QTY = "qty";

    @DatabaseField(columnName = ID, generatedId = true)
    public int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
