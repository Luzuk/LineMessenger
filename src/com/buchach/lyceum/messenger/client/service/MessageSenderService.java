package com.buchach.lyceum.messenger.client.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class MessageSenderService {

    public void sendToGlobalChat(String message) throws IOException {
        MainService.getInstance().sendMessage(message);
    }

    public ObservableList<String> getGlobalChatMessages() {
        ObservableList<String> result = FXCollections.observableArrayList();
        result.addAll(MessageBuffer.getInstance().getMessages());
        return result;
    }
}
