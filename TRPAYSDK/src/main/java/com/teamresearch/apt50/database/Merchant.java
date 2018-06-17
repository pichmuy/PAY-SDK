package com.teamresearch.apt50.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Merchant")
public class Merchant extends BaseDatabaseModel {

    @DatabaseField(columnName = NAME)
    public String name;
    @DatabaseField(columnName = ADDRESS)
    public String address;
    @DatabaseField(columnName = CITY)
    public String city;
    @DatabaseField(columnName = STATE)
    public String state;
    @DatabaseField(columnName = ZIPCODE)
    public String zipcode;
    @DatabaseField(columnName = PHONE)
    public String phone;
    @DatabaseField(columnName = LANGUAGE)
    public int language;
    @DatabaseField(columnName = CURRENCY)
    public int currency;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getCurrency() {
        return this.currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
