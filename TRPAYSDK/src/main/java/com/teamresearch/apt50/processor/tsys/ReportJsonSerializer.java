package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class ReportJsonSerializer implements JsonSerializer<ReportMiddleRequest> {

    @Override
    public JsonElement serialize(ReportMiddleRequest reportMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(reportMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(reportMiddleRequest.getTransactionKey()));
        object1.add(Const.REPORT_NAME, jsonSerializationContext.serialize(reportMiddleRequest.getReportName()));
        object.add(Const.GET_REPORT_DATA, object1);

        return object;
    }
}
