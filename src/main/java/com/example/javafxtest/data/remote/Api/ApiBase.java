package com.example.javafxtest.data.remote.Api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.example.javafxtest.data.local.models.chats.ChatData;
import com.example.javafxtest.data.local.models.chats.ChatDetail;
import com.example.javafxtest.data.local.models.chats.Chats;
import com.example.javafxtest.presentation.models.GsonParser;
import com.example.javafxtest.data.local.models.auth.UserData;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ApiBase {
    URL baseurl = new URL("http://34.120.190.133/");

    private String status;

    private String token;

    private ArrayList<HashMap<String,String>> data3;

    private HashMap<String, Object> data4;

    private String typedata;
    public ApiBase(String RequestType, String modificator, String... Data1) throws IOException {
        URL baseurlMod = new URL(baseurl+modificator);

        HttpURLConnection conn = (HttpURLConnection) baseurlMod.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod(RequestType);
        conn.setRequestProperty("Content-Type", "application/json; utf-8");

        String jsonInputString = null;

        if (RequestType.equals("POST") & Data1.length == 2) {
            jsonInputString = String.format("{\"username\": \"%s\",\"password\": \"%s\"}",Data1);
        } else if (RequestType.equals("POST") & Data1.length == 4) {
            jsonInputString = String.format("{\"username\": \"%s\",\"email\": \"%s\",\"password\": \"%s\",\"passwordConfirm\": \"%s\"}",Data1);
        }


        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        StringBuilder responseBuilder = new StringBuilder();


        if (conn.getResponseCode() != 200 & conn.getResponseCode() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        else{
            String output;
            while ((output = br.readLine()) != null) {
                responseBuilder.append(output);
            }
            GsonParser parser = new GsonParser();
            UserData data2 = parser.parse(responseBuilder.toString()); // Token
            System.out.println(data2.toString());
            status = data2.returnStatus();
            this.token = data2.returnToken();
        }

    }



    public ApiBase (String RequestType, String modificator, String token, String typeData, int s) throws IOException {
        URL baseurlMod = new URL(baseurl+modificator);
        HttpURLConnection connection = (HttpURLConnection) baseurlMod.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestMethod(RequestType);
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            GsonParser parser = new GsonParser();
            if (typeData.equals("ChatData")) {
                ChatData data2 = parser.parse1(response.toString());
                this.data3 = data2.getChatData();
            } else if (typeData.equals("ChatDetail")){
                ChatDetail data2 = parser.parse2(response.toString());
                this.data4 = data2.getChatData();
            }



        } else {
            System.out.println("Failed : HTTP error code: " + responseCode);
        }
    }


    public ArrayList<HashMap<String,String>> backuserData(){
        return data3;
    }

    public HashMap<String,Object> backmessageData(){
        return data4;
    }
    public String getStatus(){
        return status;
    }

    public String getToken(){
        return token;
    }

}
