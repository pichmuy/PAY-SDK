package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class VoidJsonSerializer implements JsonSerializer<VoidMiddleRequest> {

    @Override
    public JsonElement serialize(VoidMiddleRequest voidMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(voidMiddleRequest.getdeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(voidMiddleRequest.gettransactionKey()));
        object1.add(Const.TRANSACTION_ID, jsonSerializationContext.serialize(voidMiddleRequest.gettransactionID()));
        object.add(Const.VOID, object1);
        return object;
    }
}
