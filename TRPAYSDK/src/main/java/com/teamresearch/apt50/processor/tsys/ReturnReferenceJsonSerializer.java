package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class ReturnReferenceJsonSerializer implements JsonSerializer<ReturnMiddleRequest> {

    @Override
    public JsonElement serialize(ReturnMiddleRequest returnMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();

        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(returnMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(returnMiddleRequest.getTransactionKey()));
        object1.add(Const.TRANSACTION_AMOUNT, jsonSerializationContext.serialize(returnMiddleRequest.getTransactionAmount()));
        object1.add(Const.TRANSACTION_ID, jsonSerializationContext.serialize(returnMiddleRequest.getTransactionID()));
        object.add(Const.RETURN, object1);

        return object;

    }
}
