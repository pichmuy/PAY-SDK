package com.teamresearch.apt50.device;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.teamresearch.apt50.BuildConfig;
import com.teamresearch.apt50.database.Configuration;
import com.teamresearch.apt50.database.DBHelper;
import com.teamresearch.apt50.database.Merchant;

import java.sql.SQLException;

public class Settings {

    private static final String TAG = Settings.class.getCanonicalName();

    public static ProfileResponse getProfileInstance(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        Merchant merchant = new Merchant();
        Configuration configuration = new Configuration();

        String error = "";
        String errorMsg = "";
        try {
            merchant = dbHelper.getById(Merchant.class, 1);
            configuration = dbHelper.getById(Configuration.class, 1);
            error = "0";
            errorMsg = "Success";
        } catch (SQLException e) {
            Log.e(TAG, "error get values from db", e);
        }

        if (merchant == null || configuration == null) {
            errorMsg = "Key configuration is missing, please run the /api/deviceset command in order to config the device";
            error = "900";
            return new ProfileResponse(error, errorMsg);
        } else {
            return new ProfileResponse(error,errorMsg,merchant.name,merchant.address,merchant.city,
                    merchant.state,merchant.zipcode,merchant.phone,merchant.language,merchant.currency,
                    String.valueOf(configuration.device_id),configuration.device_type,configuration.merchant_id,
                    configuration.processor,configuration.status,configuration.trans_key);
        }
    }

    public static ProfileResponse getProfileInstance(String files, Context context) {
        ProfileRequest profileRequest;

        try {
            profileRequest = new Gson().fromJson(files, ProfileRequest.class);
        } catch (Exception e) {
            return new ProfileResponse("1", "Invalid Request Parameter");
        }

        if (BuildConfig.DEBUG) {
            Log.d("profile", new Gson().toJson(profileRequest));
        }

        if (profileRequest.device.device_type.equals("APT40")
                || profileRequest.device.device_type.equals("APT50")
                || profileRequest.device.device_type.equals("APT120")) {

            if (profileRequest.device.status == 0 || profileRequest.device.status == 1) {

                if (profileRequest.device.processor.equals("TSYS")) {
                    Configuration configuration = new Configuration();
                    Merchant merchant = new Merchant();
                    merchant.setName(profileRequest.merchant.name);
                    merchant.setAddress(profileRequest.merchant.address);
                    merchant.setCity(profileRequest.merchant.city);
                    merchant.setState(profileRequest.merchant.state);
                    merchant.setZipcode(profileRequest.merchant.zipcode);
                    merchant.setPhone(profileRequest.merchant.phone);
                    merchant.setId(1);
                    configuration.setDevice_id(profileRequest.device.deviceid);
                    configuration.setDevice_type(profileRequest.device.device_type);
                    configuration.setMerchant_id(profileRequest.device.merchant_id);
                    configuration.setProcessor(profileRequest.device.processor);
                    configuration.setStatus(profileRequest.device.status);

                    if (profileRequest.device.trans_key != null) {
                        configuration.setTrans_key(profileRequest.device.trans_key);
                    }

                    configuration.setId(1);
                    DBHelper dbHelper = new DBHelper(context);

                    try {
                        dbHelper.createOrUpdate(merchant);
                        dbHelper.createOrUpdate(configuration);
                        return new ProfileResponse("0", "Success");
                    } catch (SQLException e) {
                        Log.e(TAG, "error create item in db", e);
                    }
                }
            }
        }
        return new ProfileResponse("1", "Failed");
    }
}
