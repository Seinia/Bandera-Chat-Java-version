package com.example.javafxtest;

import com.example.javafxtest.data.local.models.chats.Chats;
import com.example.javafxtest.data.local.models.chats.Messages;
import com.example.javafxtest.data.remote.Api.ApiBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatController {

    @FXML
    private AnchorPane ChatPane;

    @FXML
    private ScrollPane ChatScrollPane;

    @FXML
    private TextField MessageTextField;

    @FXML
    private Text OnlineTEXT;

    @FXML
    private ImageView UserIMG;

    @FXML
    private Text UsernameTEXT;


    private String token;

    private String id;


    private ArrayList<HashMap<String,String>> messages;

    @FXML
    public void initialize() {
        if (token != null) {
            try {
                ApiBase chatDetailRequest = new ApiBase("GET", "chats/" + id, token, "ChatDetail", 5);

                HashMap<String, Object> messageData = new HashMap<>();

                messageData = chatDetailRequest.backmessageData();


                System.out.println(messageData);

                Chats chatmap = (Chats) messageData.get("chat");

                VBox root = new VBox();
                root.setSpacing(10);
                root.setStyle("-fx-background-color: #e6e6e6;");

                ObservableList<Node> children = root.getChildren();

                ArrayList<HashMap<String,Object>> messageslist = (ArrayList<HashMap<String, Object>>) messageData.get("messages");

                for (HashMap<String,Object> message: messageslist){
                    FXMLLoader loader1 = new FXMLLoader();
                    if (message.get("type").toString().equals("own")) {
                        loader1 = new FXMLLoader(getClass().getResource("Message_item_own.fxml"));

                    }else {
                        loader1 = new FXMLLoader(getClass().getResource("Message_item_other.fxml"));
                    }
                    Parent child1 = loader1.load();
                    MessageController messageController = loader1.getController();
                    messageController.setData(message);
                    loader1.setController(messageController);
                    messageController.initialize();

                    children.add(child1);




                }

                System.out.println(messageslist);

                System.out.println(chatmap.getTitle());


                UsernameTEXT.setText(chatmap.getTitle());








                ChatScrollPane.setContent(root);






            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            // Обработка ошибки, если messageData равно null
        }
    }

    @FXML
    public void setData(String token, String id){
        this.token = token;
        this.id = id;
    }

    public void websocketconnect(){
        String serverUri = "ws://34.120.190.133/chats/" + id + "/ws?token=" + token; // Замените на ваш реальный адрес сервера
        try {
            WebsocketBase client = new WebsocketBase(serverUri);
            client.connect();
        } catch (URISyntaxException e) {
            System.out.println("Invalid server URI: " + serverUri);
            e.printStackTrace();
        }
    }

    public void setTitles(String username){
        this.UsernameTEXT.setText(username);
    }

    public ChatController(){}

    public ChatController(String token, String id){
        this.token = token;
        this.id = id;
    }

}
