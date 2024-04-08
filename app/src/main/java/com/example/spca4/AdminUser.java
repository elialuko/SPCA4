package com.example.spca4;

public class AdminUser {

    public String email, name, password, number, code;

    public AdminUser(){

    }

    public AdminUser(String email, String name, String password, String number, String code) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.number = number;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
