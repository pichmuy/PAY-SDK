package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class ReturnJsonSerializer implements JsonSerializer<ReturnMiddleRequest> {

    @Override
    public JsonElement serialize(ReturnMiddleRequest returnMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();

        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(returnMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(returnMiddleRequest.getTransactionKey()));
        object1.add(Const.CARD_DATA_SOURCE, jsonSerializationContext.serialize(returnMiddleRequest.getCardDataSource()));
        object1.add(Const.TRANSACTION_AMOUNT, jsonSerializationContext.serialize(returnMiddleRequest.getTransactionAmount()));
        object1.add(Const.CARD_NUMBER, jsonSerializationContext.serialize(returnMiddleRequest.getCardNumber()));
        object1.add(Const.EXPIRATION_DATE, jsonSerializationContext.serialize(returnMiddleRequest.getExpirationDate()));
        object1.add(Const.CVV2, jsonSerializationContext.serialize(returnMiddleRequest.getCvv2()));
        object1.add(Const.TERMINAL_CAPABILITY, jsonSerializationContext.serialize(returnMiddleRequest.getTerminalCapability()));
        object1.add(Const.TERMINAL_OPERATING_ENVIRONMENT, jsonSerializationContext.serialize(returnMiddleRequest.getTerminalOperatingEnvironment()));
        object1.add(Const.CARDHOLDER_AUTHENTICATION_METHOD, jsonSerializationContext.serialize(returnMiddleRequest.getCardholderAuthenticationMethod()));
        object1.add(Const.TERMINAL_AUTHENTICATION_CAPABILITY, jsonSerializationContext.serialize(returnMiddleRequest.getTerminalAuthenticationCapability()));
        object1.add(Const.TERMINAL_OUTPUT_CAPABILITY, jsonSerializationContext.serialize(returnMiddleRequest.getTerminalOutputCapability()));
        object1.add(Const.MAX_PIN_LENGTH, jsonSerializationContext.serialize(returnMiddleRequest.getMaxPinLength()));
        object1.add(Const.DEVELOPER_ID, jsonSerializationContext.serialize(returnMiddleRequest.getDeveloperID()));
        object.add(Const.RETURN, object1);

        return object;

    }
}
