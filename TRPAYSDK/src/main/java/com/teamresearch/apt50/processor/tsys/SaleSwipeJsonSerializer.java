package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;


public class SaleSwipeJsonSerializer implements JsonSerializer<SaleMiddleRequest> {

    @Override
    public JsonElement serialize(SaleMiddleRequest saleMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();

        object1.add(Const.DEVICE_ID, jsonSerializationContext.serialize(saleMiddleRequest.getDeviceID()));
        object1.add(Const.TRANSACTION_KEY, jsonSerializationContext.serialize(saleMiddleRequest.getTransactionKey()));
        object1.add(Const.CARD_DATA_SOURCE, jsonSerializationContext.serialize(saleMiddleRequest.getCardDataSource()));
        object1.add(Const.TRANSACTION_AMOUNT, jsonSerializationContext.serialize(saleMiddleRequest.getTransactionAmount()));
        object1.add(Const.TIP, jsonSerializationContext.serialize(saleMiddleRequest.getTip()));
        object1.add(Const.CURRENCY_CODE, jsonSerializationContext.serialize(saleMiddleRequest.getCurrencyCode()));

        if (saleMiddleRequest.getTrack1Data() != null && saleMiddleRequest.getTrack1Data().length() > 0) {
            object1.add(Const.TRACK1DATA, jsonSerializationContext.serialize(saleMiddleRequest.getTrack1Data()));
        } else {
            object1.add(Const.TRACK2DATA, jsonSerializationContext.serialize(saleMiddleRequest.getTrack2Data()));
        }

        object1.add(Const.SECURITY_PROTOCOL, jsonSerializationContext.serialize(saleMiddleRequest.getSecurityProtocol()));
        object1.add(Const.UCAF_COLLECTION_INDICATOR, jsonSerializationContext.serialize(saleMiddleRequest.getUcafCollectionIndicator()));
        object1.add(Const.ORDER_NUMBER, jsonSerializationContext.serialize(saleMiddleRequest.getOrderNumber()));
        object1.add(Const.TOKEN_REQUIRED, jsonSerializationContext.serialize(saleMiddleRequest.getTokenRequired()));
        object1.add(Const.TERMINAL_CAPABILITY, jsonSerializationContext.serialize(saleMiddleRequest.getTerminalCapability()));
        object1.add(Const.TERMINAL_OPERATING_ENVIRONMENT, jsonSerializationContext.serialize(saleMiddleRequest.getTerminalOperatingEnvironment()));
        object1.add(Const.CARDHOLDER_AUTHENTICATION_METHOD, jsonSerializationContext.serialize(saleMiddleRequest.getCardholderAuthenticationMethod()));
        object1.add(Const.TERMINAL_AUTHENTICATION_CAPABILITY, jsonSerializationContext.serialize(saleMiddleRequest.getTerminalAuthenticationCapability()));
        object1.add(Const.TERMINAL_OUTPUT_CAPABILITY, jsonSerializationContext.serialize(saleMiddleRequest.getTerminalOutputCapability()));
        object1.add(Const.MAX_PIN_LENGTH, jsonSerializationContext.serialize(saleMiddleRequest.getMaxPinLength()));
        object.add(Const.SALE, object1);

        return object;

    }
}
