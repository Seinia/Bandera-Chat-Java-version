package com.example.javafxtest;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.HashMap;

public class ChatItemController {


    @FXML
    private Text NameTEXT;

    @FXML
    private Text messageTEXT;

    @FXML
    private ImageView UserIMG;


    public void setText(HashMap<String,String> data) {
        NameTEXT.setText(data.get("title"));
        if (!data.get("photo").isBlank()) {
            Image image = new Image(data.get("photo"));
            UserIMG.setImage(image);
        }
    }

}
