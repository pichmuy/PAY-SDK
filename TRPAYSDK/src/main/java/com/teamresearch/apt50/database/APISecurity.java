package com.teamresearch.apt50.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "APISecurity")
public class APISecurity extends BaseDatabaseModel {

    @DatabaseField(columnName = TOKEN)
    private String token;
    @DatabaseField(columnName = SECRET)
    private String secret;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
