package com.buchach.lyceum.messenger.client.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MessageBuffer {
    private ObservableList<String> messages;
    private static MessageBuffer instance;

    private MessageBuffer() {
        messages = FXCollections.observableArrayList();
    }

    public static MessageBuffer getInstance() {
        if (instance == null) {
            instance = new MessageBuffer();
        }
        return instance;
    }

    public ObservableList<String> getMessages() {
        return messages;
    }

    public void insertMessage(String message){
        messages.add(message);
    }
}
