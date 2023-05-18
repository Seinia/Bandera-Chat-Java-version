package com.example.javafxtest.data.local.models.chats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chats {
    private String title;
    private boolean is_direct;
    private String photo;
    private String created_at;
    private String updated_at;
    private int id;

    private ArrayList<Participants> participants;
    private ArrayList<Participants> moderators;

    public String getTitle(){
        return title;
    }

    public String getPhoto() {return photo;}

    public HashMap<String,String> getHashMap(){
        HashMap<String,String> map = new HashMap<>();

        map.put("title",title);
        map.put("photo", photo);
        map.put("id", String.valueOf(id));
        return map;
    }
}
