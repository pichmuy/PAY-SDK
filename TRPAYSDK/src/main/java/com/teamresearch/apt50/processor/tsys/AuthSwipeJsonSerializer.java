package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;


public class AuthSwipeJsonSerializer implements JsonSerializer<AuthMiddleRequest> {

    @Override
    public JsonElement serialize(AuthMiddleRequest authMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();

        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(authMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(authMiddleRequest.getTransactionKey()));
        object1.add(Const.CARD_DATA_SOURCE, jsonSerializationContext.serialize(authMiddleRequest.getCardDataSource()));
        object1.add(Const.TRANSACTION_AMOUNT, jsonSerializationContext.serialize(authMiddleRequest.getTransactionAmount()));
        object1.add(Const.TIP, jsonSerializationContext.serialize(authMiddleRequest.getTip()));
        object1.add(Const.CURRENCY_CODE, jsonSerializationContext.serialize(authMiddleRequest.getCurrencyCode()));

        if (authMiddleRequest.getTrack1Data() != null && authMiddleRequest.getTrack1Data().length() > 0) {
            object1.add(Const.TRACK1DATA, jsonSerializationContext.serialize(authMiddleRequest.getTrack1Data()));
        } else {
            object1.add(Const.TRACK2DATA, jsonSerializationContext.serialize(authMiddleRequest.getTrack2Data()));
        }

        object1.add(Const.SECURITY_PROTOCOL, jsonSerializationContext.serialize(authMiddleRequest.getSecurityProtocol()));
        object1.add(Const.UCAF_COLLECTION_INDICATOR, jsonSerializationContext.serialize(authMiddleRequest.getUcafCollectionIndicator()));
        object1.add(Const.ORDER_NUMBER, jsonSerializationContext.serialize(authMiddleRequest.getOrderNumber()));
        object1.add(Const.TOKEN_REQUIRED, jsonSerializationContext.serialize(authMiddleRequest.getTokenRequired()));
        object1.add(Const.TERMINAL_CAPABILITY, jsonSerializationContext.serialize(authMiddleRequest.getTerminalCapability()));
        object1.add(Const.TERMINAL_OPERATING_ENVIRONMENT, jsonSerializationContext.serialize(authMiddleRequest.getTerminalOperatingEnvironment()));
        object1.add(Const.CARDHOLDER_AUTHENTICATION_METHOD, jsonSerializationContext.serialize(authMiddleRequest.getCardholderAuthenticationMethod()));
        object1.add(Const.TERMINAL_AUTHENTICATION_CAPABILITY, jsonSerializationContext.serialize(authMiddleRequest.getTerminalAuthenticationCapability()));
        object1.add(Const.TERMINAL_OUTPUT_CAPABILITY, jsonSerializationContext.serialize(authMiddleRequest.getTerminalOutputCapability()));
        object1.add(Const.MAX_PIN_LENGTH, jsonSerializationContext.serialize(authMiddleRequest.getMaxPinLength()));
        object.add(Const.AUTH, object1);

        return object;

    }
}
