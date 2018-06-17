package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;


public class AuthJsonSerializer implements JsonSerializer<AuthMiddleRequest> {


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
        object1.add(Const.CARD_NUMBER, jsonSerializationContext.serialize(authMiddleRequest.getCardNumber()));
        object1.add(Const.EXPIRATION_DATE, jsonSerializationContext.serialize(authMiddleRequest.getExpirationDate()));
        object1.add(Const.CVV2, jsonSerializationContext.serialize(authMiddleRequest.getCvv2()));
        object1.add(Const.ADDRESS_LINE_1, jsonSerializationContext.serialize(authMiddleRequest.getAddressLine1()));
        object1.add(Const.ZIP, jsonSerializationContext.serialize(authMiddleRequest.getZip()));
        object1.add(Const.ORDER_NUMBER, jsonSerializationContext.serialize(authMiddleRequest.getOrderNumber()));
        object1.add(Const.FIRST_NAME, jsonSerializationContext.serialize(authMiddleRequest.getFirstName()));
        object1.add(Const.LAST_NAME, jsonSerializationContext.serialize(authMiddleRequest.getLastName()));
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
