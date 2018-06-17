package com.teamresearch.apt50.processor.tsys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.utils.Const;

import java.lang.reflect.Type;

public class SaleJsonSerializer implements JsonSerializer<SaleMiddleRequest> {

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
        object1.add(Const.CARD_NUMBER, jsonSerializationContext.serialize(saleMiddleRequest.getCardNumber()));
        object1.add(Const.EXPIRATION_DATE, jsonSerializationContext.serialize(saleMiddleRequest.getExpirationDate()));
        object1.add(Const.CVV2, jsonSerializationContext.serialize(saleMiddleRequest.getCvv2()));
        object1.add(Const.ADDRESS_LINE_1, jsonSerializationContext.serialize(saleMiddleRequest.getAddressLine1()));
        object1.add(Const.ZIP, jsonSerializationContext.serialize(saleMiddleRequest.getZip()));
        object1.add(Const.ORDER_NUMBER, jsonSerializationContext.serialize(saleMiddleRequest.getOrderNumber()));
        object1.add(Const.FIRST_NAME, jsonSerializationContext.serialize(saleMiddleRequest.getFirstName()));
        object1.add(Const.LAST_NAME, jsonSerializationContext.serialize(saleMiddleRequest.getLastName()));
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
