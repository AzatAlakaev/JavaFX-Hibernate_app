package com.demoJavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/primary.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.getRoot());

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       launch();
    }

    //этот метод можно использовать если Stage пропсан в fxml файле
//     @Override
//     public void start(Stage stage) {
//     FXMLLoader loader = new FXMLLoader(App.class.getResource("primary" + ".fxml"));
//     try {
//     loader.load();
//     } catch (IOException e) {
//     System.out.println("fxml не удалось загрузить");
//     e.printStackTrace();
//     }
//     stage = loader.getRoot();
//     stage.show();
//     }

}