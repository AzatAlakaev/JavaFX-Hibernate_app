package com.demoJavaFX;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("/fxml/primary.fxml"));

        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
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