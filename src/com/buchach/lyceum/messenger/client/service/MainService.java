package com.buchach.lyceum.messenger.client.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainService {
    private static MainService instance;

    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter out;
    private static boolean connected;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        connected = true;
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        connected = false;
    }

    public void closeConnection() throws IOException {
        out.close();
        reader.close();
        clientSocket.close();
    }

    public boolean isConnected() {
        return connected;
    }

    public BufferedReader getReader() {
        return reader;
    }

    private MainService() {

    }

    public static MainService getInstance() {
        if (instance == null) {
            instance = new MainService();
        }
        return instance;
    }
}

