package com.buchach.lyceum.messenger.client.service;

public class MainUser {
    private static MainUser instance;

    private String name;

    private MainUser() {

    }

    public static MainUser getInstance() {
        if (instance == null) {
            instance = new MainUser();
        }
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
