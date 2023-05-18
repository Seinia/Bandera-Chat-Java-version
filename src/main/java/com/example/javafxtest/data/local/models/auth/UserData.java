package com.example.javafxtest.data.local.models.auth;

public class UserData {
    private String status;
    private String access_token;

    public String returnStatus() {return status;}

    public String returnToken() {return access_token;}


    @Override
    public String toString()
    {
        return String.format("Status = %s, Token = %s",status,access_token);
    }
}
