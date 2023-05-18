package com.example.javafxtest;

import com.example.javafxtest.data.remote.Api.ApiBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MessengerController {

    @FXML
    private Button ChatBTN;

    @FXML
    private ImageView ChatIMG;

    @FXML
    private Button EditBTN;

    @FXML
    private Button LogOutBTN;

    @FXML
    private AnchorPane MessengerPane;


    @FXML
    private Button SettingsBTN;

    @FXML
    private Pane ChatPane;
    @FXML
    private Button StarBTN;

    @FXML
    private Button UserBTN;

    private String token;

    public MessengerController(String token) {
        this.token = token;
    }


    public MessengerController() {}


    @FXML
    public void setToken(String token){
        this.token = token;
    }


    @FXML
    void initialize()  throws IOException{
        if (ChatPane != null) {
            ChatPane.setOnMouseClicked(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Chats.fxml"));
                    Parent root2 = loader.load();

                    root2.setLayoutX(79);
                    root2.setLayoutY(20);

                    MessengerPane.getChildren().add(root2);


                    ListView<HashMap<String, String>> listView = new ListView<>();

                    listView.setLayoutX(92); // Установите желаемые координаты и размеры ListView
                    listView.setLayoutY(119);
                    listView.setPrefSize(175, 613);

                    ArrayList<HashMap<String, String>> listView1 = new ArrayList<>();

                    ApiBase getreq = new ApiBase("GET", "chats", token, "ChatData", 5);


                    listView1 = getreq.backuserData();
                    MessengerPane.getChildren().add(listView);

                    for (HashMap<String, String> chats : listView1) {
                        listView.getItems().add(chats);
                    }

                    listView.setCellFactory(param -> new ListCell<HashMap<String, String>>() {
                        @Override
                        protected void updateItem(HashMap<String,String> item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                try {
                                    // Загрузка FXML файла для элемента списка
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat_item.fxml"));
                                    ChatItemController controller = new ChatItemController();
                                    loader.setController(controller);
                                    setGraphic(loader.load());

                                    // Установка текста для элемента списка
                                    controller.setText(item);
                                    setOnMouseClicked(mouseEvent -> {
                                        try {
                                            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Chat.fxml"));
                                            Parent root3 = loader1.load();

                                            ChatController chatController = loader1.getController();
                                            chatController.setData(token,item.get("id"));
                                            loader1.setController(chatController);
                                            chatController.initialize();



                                            root3.setLayoutX(295);

                                            MessengerPane.getChildren().add(root3);




                                        } catch (IOException e){
                                            e.printStackTrace();
                                        }
                                    });

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });





                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }



    }




}
