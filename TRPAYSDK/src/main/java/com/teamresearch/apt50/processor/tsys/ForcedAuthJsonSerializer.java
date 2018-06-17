package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class ForcedAuthJsonSerializer implements JsonSerializer<ForcedAuthMiddleRequest> {

    @Override
    public JsonElement serialize(ForcedAuthMiddleRequest forcedAuthMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getTransactionKey()));
        object1.add(Const.CARD_DATA_SOURCE, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getCardDataSource()));
        object1.add(Const.TRANSACTION_AMOUNT, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getTransactionAmount()));
        object1.add(Const.CARD_NUMBER, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getCardNumber()));
        object1.add(Const.EXPIRATION_DATE, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getExpirationDate()));
        object1.add(Const.AUTH_TIME_STAMP, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getAuthTimeStamp()));
        object1.add(Const.AUTH_CODE, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getAuthCode()));
        object1.add(Const.DEVELOPER_ID, jsonSerializationContext.serialize(forcedAuthMiddleRequest.getDeveloperID()));
        object.add(Const.FORCEDAUTH, object1);
        return object;
    }
}
