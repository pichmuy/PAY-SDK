package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class TipAdjustJsonSerializer implements JsonSerializer<TipAdjustMiddleRequest> {

    @Override
    public JsonElement serialize(TipAdjustMiddleRequest tipAdjustMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();

        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(tipAdjustMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(tipAdjustMiddleRequest.getTransactionKey()));
        object1.add(Const.TIP, jsonSerializationContext.serialize(tipAdjustMiddleRequest.getTip()));
        object1.add(Const.TRANSACTION_ID, jsonSerializationContext.serialize(tipAdjustMiddleRequest.getTransactionID()));
        object1.add(Const.DEVELOPER_ID, jsonSerializationContext.serialize(tipAdjustMiddleRequest.getDeveloperID()));
        object.add(Const.TIP_ADJUSTMENT, object1);

        return object;
    }
}
