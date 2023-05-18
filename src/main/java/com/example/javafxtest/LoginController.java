package com.example.javafxtest;

import com.example.javafxtest.data.remote.Api.ApiBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


import java.net.URISyntaxException;

public class LoginController {

    @FXML
    private Button LoginBTN;

    @FXML
    private Button LoginToRegisterBTN;

    @FXML
    private CheckBox LoginCHECK;

    @FXML
    private TextField LoginEMAIL;

    @FXML
    private TextField LoginPASSWORD;

    @FXML
    // Перехід на Register.fxml
    void LogintoRegister() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) (LoginToRegisterBTN.getScene().getWindow());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
        // Перехід на Messenger.fxml
    void LoginAction() throws IOException {
        ApiBase post = new ApiBase("POST", "auth/login" , returnLogin(), returnPassword());

        System.out.println(post.getToken());

        if (post.getStatus().equals("true"))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Messenger.fxml"));
            Parent root = loader.load();
            MessengerController messengerController = loader.getController();
            messengerController.setToken(post.getToken());
            Scene scene = new Scene(root);
            Stage stage = (Stage) (LoginToRegisterBTN.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }




    }

    public String returnLogin() {return "Jack99"; /*LoginEMAIL.getText();*/}

    public String returnPassword() {return "Qwerty1H^"; /*LoginPASSWORD.getText();*/}


}
