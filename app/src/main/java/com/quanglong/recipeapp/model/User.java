package com.quanglong.recipeapp.model;

import java.util.List;

public class User {
    private int id;
    private String userName;
    private String displayName;
    private int sex;
    private String address;
    private String phoneNumber;
    private String password;
    private String email;
    private String job;
    private int role;
    private List<String> imageInput;
    private String description;
    private int status;
    private int updateUser;
    private int createUser;

    public User() {
    }

    public User(int id, String userName, String displayName, int sex, String address, String phoneNumber,
                String password, String email, String job, int role, List<String> imageInput,
                String description, int status, int updateUser, int createUser) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.job = job;
        this.role = role;
        this.imageInput = imageInput;
        this.description = description;
        this.status = status;
        this.updateUser = updateUser;
        this.createUser = createUser;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<String> getAvatar() {
        return imageInput;
    }

    public void setImageInput(List<String> imageInput) {
        this.imageInput = imageInput;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }
}
