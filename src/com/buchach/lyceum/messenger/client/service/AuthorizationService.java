package com.buchach.lyceum.messenger.client.service;

import java.io.IOException;

public class AuthorizationService {
    public boolean login(String login, String password) throws IOException, InterruptedException {
        String auth = "/auth@" + login + "%" + password;
        MainService.getInstance().sendMessage(auth);
        Thread.sleep(1000);
        return MessageBuffer.getInstance().getMessages().contains("Login Successful");
    }
}
