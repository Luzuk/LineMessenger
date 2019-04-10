package com.buchach.lyceum.messenger.client.service;

import java.io.BufferedReader;
import java.io.IOException;

public class OutputThread implements Runnable {
    private BufferedReader bufferedReader;

    public OutputThread(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        try {
            while (true) {
                MessageBuffer.getInstance().insertMessage(receiveMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() throws IOException {
        return bufferedReader.readLine();
    }
}