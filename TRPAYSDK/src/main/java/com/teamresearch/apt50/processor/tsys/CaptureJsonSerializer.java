package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;


public class CaptureJsonSerializer implements JsonSerializer<CaptureMiddleRequest> {

    @Override
    public JsonElement serialize(CaptureMiddleRequest captureMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(captureMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(captureMiddleRequest.getTransactionKey()));
        object1.add(Const.TRANSACTION_AMOUNT, jsonSerializationContext.serialize(captureMiddleRequest.getTransactionAmount()));
        object1.add(Const.TIP, jsonSerializationContext.serialize(captureMiddleRequest.getTip()));
        object1.add(Const.TRANSACTION_ID, jsonSerializationContext.serialize(captureMiddleRequest.getTransactionID()));
        object1.add(Const.ORDER_NOTES, jsonSerializationContext.serialize(captureMiddleRequest.getOrderNotes()));
        object.add(Const.CAPTURE, object1);
        return object;
    }
}
