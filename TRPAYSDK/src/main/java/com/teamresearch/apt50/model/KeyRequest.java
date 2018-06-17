package com.teamresearch.apt50.model;

public class KeyRequest {

    public GenerateKey generateKey;

    public class GenerateKey {
        String mid;
        String userID;
        String password;

        GenerateKey(String mid, String userID, String password) {
            this.mid = mid;
            this.userID = userID;
            this.password = password;
        }
    }

    public KeyRequest(String mid, String userID, String password) {
        this.generateKey = new GenerateKey(mid, userID, password);
    }

    public KeyRequest() {

    }

    public String getMid() {
        return this.generateKey.mid;
    }

    public String getUserID() {
        return this.generateKey.userID;
    }

    public String getPassword() {
        return this.generateKey.password;
    }
}