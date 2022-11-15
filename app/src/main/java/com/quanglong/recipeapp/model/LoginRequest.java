package com.quanglong.recipeapp.model;

public class LoginRequest {
    private String loginUser;
    private String password;
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public LoginRequest(String loginUser, String password, String deviceName) {
        this.loginUser = loginUser;
        this.password = password;
        this.deviceName = deviceName;
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

    public LoginRequest() {
    }
}
