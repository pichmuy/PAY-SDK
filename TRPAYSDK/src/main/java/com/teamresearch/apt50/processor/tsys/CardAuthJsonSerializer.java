package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class CardAuthJsonSerializer implements JsonSerializer<CardAuthMiddleRequest> {

    @Override
    public JsonElement serialize(CardAuthMiddleRequest cardAuthMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(cardAuthMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(cardAuthMiddleRequest.getTransactionKey()));
        object1.add(Const.CARD_DATA_SOURCE, jsonSerializationContext.serialize(cardAuthMiddleRequest.getCardDataSource()));
        object1.add(Const.CARD_NUMBER, jsonSerializationContext.serialize(cardAuthMiddleRequest.getCardNumber()));
        object1.add(Const.EXPIRATION_DATE, jsonSerializationContext.serialize(cardAuthMiddleRequest.getExpirationDate()));
        object1.add(Const.CVV2, jsonSerializationContext.serialize(cardAuthMiddleRequest.getCvv2()));
        object1.add(Const.CARD_HOLDER_NAME, jsonSerializationContext.serialize(cardAuthMiddleRequest.getCardHolderName()));
        object1.add(Const.ADDRESS_LINE_1, jsonSerializationContext.serialize(cardAuthMiddleRequest.getAddressLine1()));
        object1.add(Const.ZIP, jsonSerializationContext.serialize(cardAuthMiddleRequest.getZip()));
        object1.add(Const.ORDER_NUMBER, jsonSerializationContext.serialize(cardAuthMiddleRequest.getOrderNumber()));
        object1.add(Const.FIRST_NAME, jsonSerializationContext.serialize(cardAuthMiddleRequest.getFirstName()));
        object1.add(Const.LAST_NAME, jsonSerializationContext.serialize(cardAuthMiddleRequest.getLastName()));
        object1.add(Const.TERMINAL_CAPABILITY, jsonSerializationContext.serialize(cardAuthMiddleRequest.getTerminalCapability()));
        object1.add(Const.TERMINAL_OPERATING_ENVIRONMENT, jsonSerializationContext.serialize(cardAuthMiddleRequest.getTerminalOperatingEnvironment()));
        object1.add(Const.CARDHOLDER_AUTHENTICATION_METHOD, jsonSerializationContext.serialize(cardAuthMiddleRequest.getCardholderAuthenticationMethod()));
        object1.add(Const.TERMINAL_AUTHENTICATION_CAPABILITY, jsonSerializationContext.serialize(cardAuthMiddleRequest.getTerminalAuthenticationCapability()));
        object1.add(Const.TERMINAL_OUTPUT_CAPABILITY, jsonSerializationContext.serialize(cardAuthMiddleRequest.getTerminalOutputCapability()));
        object1.add(Const.MAX_PIN_LENGTH, jsonSerializationContext.serialize(cardAuthMiddleRequest.getMaxPinLength()));
        object1.add(Const.DEVELOPER_ID, jsonSerializationContext.serialize(cardAuthMiddleRequest.getDeveloperID()));

        object.add(Const.CARDAUTHENTICATION, object1);
        return object;
    }
}
