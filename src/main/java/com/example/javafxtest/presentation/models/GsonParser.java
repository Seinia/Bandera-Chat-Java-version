package com.example.javafxtest.presentation.models;

import com.example.javafxtest.data.local.models.auth.UserData;
import com.example.javafxtest.data.local.models.chats.ChatData;
import com.example.javafxtest.data.local.models.chats.ChatDetail;
import com.google.gson.Gson;

public class GsonParser {
    public UserData parse(String info) {
        Gson gson = new Gson();
        try {
            UserData data1 = gson.fromJson(info, UserData.class);
            return data1;
        }catch (Exception e) {
            System.out.println("Parsing error " + e.toString());
        }
        return null;
    }

    public ChatData parse1(String info) {
        Gson gson = new Gson();
        try {
            ChatData data1 = gson.fromJson(info, ChatData.class);
            return data1;
        } catch (Exception e) {
            System.out.println("Parsing error " + e.toString());
        }
        return null;
    }

    public ChatDetail parse2(String info) {
        Gson gson = new Gson();
        try {
            ChatDetail data1 = gson.fromJson(info, ChatDetail.class);
            return data1;
        } catch (Exception e) {
            System.out.println("Parsing error " + e.toString());
        }
        return null;
    }
}
