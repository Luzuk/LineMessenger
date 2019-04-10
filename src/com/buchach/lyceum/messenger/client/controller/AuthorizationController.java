package com.buchach.lyceum.messenger.client.controller;

import com.buchach.lyceum.messenger.client.service.AuthorizationService;
import com.buchach.lyceum.messenger.client.service.MainUser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AuthorizationController extends Controller {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    private AuthorizationService authorizationService;

    public AuthorizationController() {
        authorizationService = new AuthorizationService();
    }

    @FXML
    public void handleRegister() {

    }

    @FXML
    public void handleLogin() {
        try {
            if (authorizationService.login(loginField.getText(), passwordField.getText())) {
                MainUser.getInstance().setName(loginField.getText());
                mainApp.showMainPage();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
