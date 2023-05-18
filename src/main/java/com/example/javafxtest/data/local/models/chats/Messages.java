package com.example.javafxtest.data.local.models.chats;

import java.util.HashMap;
import java.util.Objects;

public class Messages {
    private UserShort user;

    private String message;
    private int id;
    private String created_at;
    private String updated_at;
    private String type;

    public HashMap<String, Object> getHashMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("user", user);
        map.put("message", message);
        map.put("type", type);
        map.put("id", String.valueOf(id));
        return map;
    }

}
