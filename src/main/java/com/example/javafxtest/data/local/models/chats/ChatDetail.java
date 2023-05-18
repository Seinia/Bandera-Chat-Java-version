package com.example.javafxtest.data.local.models.chats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatDetail {

    private boolean status;
    private Chats chat;

    private List<Messages> messages;

    private HashMap<String,Object> data;

    public HashMap<String,Object> getChatData() {
       HashMap<String,Object> data1 = new HashMap<>();
       ArrayList<HashMap<String,Object>> mes = new ArrayList<>();
        for (Messages messages: messages){
            data = messages.getHashMap();
            mes.add(data);
        }
        data1.put("messages", mes);
        data1.put("chat", chat);
        return data1;
    }

}
