package com.teamresearch.apt50.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Language")
public class Language extends BaseDatabaseModel {

    @DatabaseField(columnName = DESCRIPTION)
    public String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
