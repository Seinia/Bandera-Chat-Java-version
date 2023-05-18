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

public class RegisterController {

    @FXML
    private Button RegisterBTN;

    @FXML
    private CheckBox RegisterCHECK;

    @FXML
    private TextField RegisterConfirm;

    @FXML
    private TextField RegisterEmail;

    @FXML
    private TextField RegisterLogin;

    @FXML
    private TextField RegisterPassword;

    @FXML
    private Button RegisterToLoginBTN;

    @FXML
        // Перехід на Login.fxml
    void RegisterToLogin() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) (RegisterToLoginBTN.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
        // Перехід на Register.fxml
    void RegisterAction() throws IOException {
        ApiBase post = new ApiBase("POST", "auth/register" , returnLogin(), returnEmail(), returnPassword(), returnPasswordConfirm());

        if (post.getStatus().equals("success"))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) (RegisterBTN.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
    }

    public String returnEmail() {return RegisterEmail.getText();}

    public String returnLogin() {return RegisterLogin.getText();}

    public String returnPassword() {return RegisterPassword.getText();}

    public String returnPasswordConfirm() {return RegisterConfirm.getText();}

}
