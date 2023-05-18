module com.example.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javax.websocket.api;
    requires Java.WebSocket;


    exports com.example.javafxtest.data.local.models.auth;
    opens com.example.javafxtest.data.local.models.auth;
    exports com.example.javafxtest.data.remote.Api;
    opens com.example.javafxtest.data.remote.Api;
    exports com.example.javafxtest.presentation.models;
    opens com.example.javafxtest.presentation.models;
    exports com.example.javafxtest;
    opens com.example.javafxtest;
    exports com.example.javafxtest.data.local.models.chats;
    opens com.example.javafxtest.data.local.models.chats;


}