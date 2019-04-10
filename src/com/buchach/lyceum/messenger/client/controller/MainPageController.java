package com.buchach.lyceum.messenger.client.controller;

import com.buchach.lyceum.messenger.client.service.FriendsService;
import com.buchach.lyceum.messenger.client.service.MessageBuffer;
import com.buchach.lyceum.messenger.client.service.MessageSenderService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class MainPageController extends Controller {
    @FXML
    private ListView<String> messeges;
    @FXML
    private ListView<String> friends;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField addFriendTextField;
    @FXML
    private TextArea messageArea;

    private FriendsService friendsService;
    private MessageSenderService messageSenderService;

    public MainPageController() {
        friendsService = new FriendsService();
        messageSenderService = new MessageSenderService();
    }

    public void setMessages() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messeges.setItems(MessageBuffer.getInstance().getMessages());
            }
        });
    }
    public void setUsernameLabel(String text) {
        usernameLabel.setText(text);
    }

    @FXML
    public void loadFriends() throws IOException, InterruptedException {
        friends.setItems(friendsService.getFriends());
    }

    @FXML
    public void handleAddFriend() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (addFriendTextField.getText().trim().length() != 0) {
            try {
                if (friendsService.addFriend(addFriendTextField.getText())) {
                    alert.setContentText("Successful");
                } else {
                    alert.setContentText("Failed");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            alert.setContentText("Write friend name, please!");
        }
    }

    @FXML
    public void sendGlobal() throws IOException {
        if (messageArea.getText().trim().length() != 0) {
            messageSenderService.sendToGlobalChat(messageArea.getText());
            messageArea.clear();
        }
    }
}
