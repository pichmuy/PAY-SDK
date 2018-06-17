package com.teamresearch.apt50.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Configuration")
public class Configuration extends BaseDatabaseModel {

    @DatabaseField(columnName = ACTIVATION_DATE)
    private Date activation_date;
    @DatabaseField(columnName = DEVICE_ID)
    public long device_id;
    @DatabaseField(columnName = DEVICE_TYPE)
    public String device_type;
    @DatabaseField(columnName = MERCHANT_ID)
    public String merchant_id;
    @DatabaseField(columnName = PROCESSOR)
    public String processor;
    @DatabaseField(columnName = STATUS)
    public int status;
    @DatabaseField(columnName = TRANS_KEY)
    public String trans_key;

    public Date getActivation_date() {
        return this.activation_date;
    }

    public void setActivation_date(Date activation_date) {
        this.activation_date = activation_date;
    }

    public long getDevice_id() {
        return this.device_id;
    }

    public void setDevice_id(long device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return this.device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getMerchant_id() {
        return this.merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getProcessor() {
        return this.processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTrans_key() {
        return this.trans_key;
    }

    public void setTrans_key(String trans_key) {
        this.trans_key = trans_key;
    }
}
