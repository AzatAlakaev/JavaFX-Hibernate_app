package com.demoJavaFX.controlers.controllerHelper;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerHelper {


    private static double xOffset = 0;
    private static double yOffset = 0;

    public static void close(MouseEvent event) {
        ((Label)event.getSource()).getScene().getWindow().hide();
    }

    public static void minimize(MouseEvent event) {
        Stage stage = (Stage)((Label)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public static void switchScene(JFXButton button, String fxml, String title, Class clazz) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(clazz.getResource(fxml));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.initStyle(StageStyle.TRANSPARENT);

        //для перетаскивания окна
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        //для перетаскивания окна
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.setScene(new Scene(root));
        stage.show();
    }

}
