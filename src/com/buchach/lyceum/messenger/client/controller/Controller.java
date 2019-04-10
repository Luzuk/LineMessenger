package com.buchach.lyceum.messenger.client.controller;

import com.buchach.lyceum.messenger.client.LineMessenger;

public abstract class Controller {

    protected LineMessenger mainApp;
    public void setMain(LineMessenger lineMessenger){
        mainApp = lineMessenger;
    }
}
