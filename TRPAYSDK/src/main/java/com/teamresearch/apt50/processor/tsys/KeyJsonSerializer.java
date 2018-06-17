package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.model.KeyRequest;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;


public class KeyJsonSerializer implements JsonSerializer<KeyRequest> {

    @Override
    public JsonElement serialize(KeyRequest keyRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add(Const.MID, jsonSerializationContext.serialize(keyRequest.getMid()));
        object1.add(Const.USER_ID, jsonSerializationContext.serialize(keyRequest.getUserID()));
        object1.add(Const.PASSWORD, jsonSerializationContext.serialize(keyRequest.getPassword()));
        object.add(Const.GENERATE_KEY, object1);
        return object;
    }
}

