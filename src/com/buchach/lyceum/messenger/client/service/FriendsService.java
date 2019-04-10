package com.buchach.lyceum.messenger.client.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class FriendsService {
    public ObservableList<String> getFriends() throws IOException, InterruptedException {
        MainService.getInstance().sendMessage("/getFriends");
        Thread.sleep(1000);

        String response = MessageBuffer.getInstance().getMessages()
                .stream()
                .filter(message -> message.startsWith("Friends:"))
                .collect(Collectors.toList()).get(0);
        return parseFriends(response);
    }

    private ObservableList<String> parseFriends(String friends) {
        ObservableList<String> result = FXCollections.observableArrayList();
        if (friends.length() > 10) {
            String friend = "";
            while (friends.length() != 0) {
                friend = friends.substring(friends.indexOf('[') + 1, friends.indexOf(']'));
                result.add(friend);
                friends = friends.substring(friends.indexOf(']') + 1);
            }
        }
        return result;
    }

    public boolean addFriend(String friendName) throws IOException, InterruptedException {
        MainService.getInstance().sendMessage("/addFriend@" + friendName);
        Thread.sleep(1000);
        return MessageBuffer.getInstance().getMessages()
                .stream()
                .anyMatch(message -> message.equals("Friend added"));
    }
}
