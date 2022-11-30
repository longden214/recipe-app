package com.quanglong.recipeapp.model;

public class LoginRequest {
    private String loginUser;
    private String password;
    private String deviceName;
    private String tokenDevice;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public LoginRequest(String loginUser, String password, String deviceName, String tokenDevice) {
        this.loginUser = loginUser;
        this.password = password;
        this.deviceName = deviceName;
        this.tokenDevice = tokenDevice;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenDevice() {
        return tokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }

    public LoginRequest() {
    }
}
