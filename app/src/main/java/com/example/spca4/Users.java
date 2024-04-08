package com.example.spca4;

public class Users {
    public String email, name, password, number;

    public Users(){

    }
    public Users(String textEmail, String textName, String textPassword, String textNumber) {
        this.email = textEmail;
        this.name = textName;
        this.password = textPassword;
        this.number = textNumber;
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
}
