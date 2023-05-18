package com.example.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.HashMap;

public class MessageController {

    @FXML
    private Text Message;


    private HashMap<String,Object> data;

    @FXML
    void initialize(){
        if (data != null) {
            Message.setText(data.get("message").toString());
        }else {
            System.out.println("біда");
        }
    }
    public MessageController(){}

    @FXML
    public void setData(HashMap<String,Object> data){
        this.data = data;
    }

    public MessageController(HashMap<String,Object> data){
        this.data = data;
    }
}
