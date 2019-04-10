package com.buchach.lyceum.messenger.client;

import com.buchach.lyceum.messenger.client.controller.AuthorizationController;
import com.buchach.lyceum.messenger.client.controller.MainPageController;
import com.buchach.lyceum.messenger.client.service.MainService;
import com.buchach.lyceum.messenger.client.service.MainUser;
import com.buchach.lyceum.messenger.client.service.OutputThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LineMessenger extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LineMessenger.class.getResource("view/authorization.fxml"));
        BorderPane page = (BorderPane) loader.load();
        this.primaryStage.setTitle("LineMessenger");
        this.primaryStage.setScene(new Scene(page));
        AuthorizationController controller = loader.getController();
        controller.setMain(this);
        this.primaryStage.show();


        MainService.getInstance().startConnection("localhost", 6666);
        if (MainService.getInstance().isConnected()) {
            new Thread(new OutputThread(MainService.getInstance().getReader())).start();
        }else{
            MainService.getInstance().closeConnection();
        }
    }

    public Stage getStage(){
       return primaryStage;
    }

    public void showMainPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LineMessenger.class.getResource("view/menu.fxml"));
            BorderPane root = (BorderPane) loader.load();
            this.primaryStage.setTitle("LineMessenger");
            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.show();

            MainPageController controller = loader.getController();
            controller.setMain(this);
            controller.setUsernameLabel(MainUser.getInstance().getName());
            controller.setMessages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
