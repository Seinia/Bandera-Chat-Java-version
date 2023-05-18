package com.example.javafxtest;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class WebsocketBase extends WebSocketClient {
    public WebsocketBase(String serverUri) throws URISyntaxException {
        super(new URI(serverUri));
    }
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("З'єднання з websocket встановлене");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Отримане повідомлення: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {System.out.println("З'єднання з websocket втрачено" + reason);}

    @Override
    public void onError(Exception ex) {
        System.out.println("Помилка вебсокету: " + ex.getMessage());
    }

}