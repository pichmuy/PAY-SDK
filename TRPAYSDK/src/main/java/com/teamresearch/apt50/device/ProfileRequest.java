package com.teamresearch.apt50.device;

public class ProfileRequest {

    public Merchant merchant;
    public Device device;

    public class Merchant {
        public String name;
        public String address;
        public String city;
        public String state;
        public String zipcode;
        public String phone;
    }

    public class Device {
        public long deviceid;
        public String device_type;
        public String merchant_id;
        public int status;
        public String trans_key;
        public String processor;
    }
}
