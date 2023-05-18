package com.example.javafxtest.data.local.models.chats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatData {
    private boolean status;
    private List<Chats> chats;

    private HashMap<String,String> data;


    public ArrayList<HashMap<String,String>> getChatData() {
        ArrayList <HashMap<String,String>> data1 = new ArrayList<>();
        for (Chats chatData: chats){
             data = chatData.getHashMap();
             data1.add(data);
        }
        return data1;
    }

}
