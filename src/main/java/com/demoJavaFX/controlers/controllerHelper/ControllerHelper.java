package com.demoJavaFX.controlers.controllerHelper;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

public class ControllerHelper {

    private static double startMoveX = -1, startMoveY = -1;
    private static Boolean dragging = false;
    private static Rectangle moveTrackingRect;
    private static Popup moveTrackingPopup;

    public static void close(MouseEvent event) {
        ((Label)event.getSource()).getScene().getWindow().hide();
    }

    public static void minimize(MouseEvent event) {
        Stage stage = (Stage)((Label)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public static void moveWindow(MouseEvent event, VBox vbox) {
        if (dragging) {

            double endMoveX = event.getScreenX();
            double endMoveY = event.getScreenY();

            Window w = vbox.getScene().getWindow();

            double stageX = w.getX();
            double stageY = w.getY();

            moveTrackingPopup.setX(stageX + (endMoveX - startMoveX));
            moveTrackingPopup.setY(stageY + (endMoveY - startMoveY));
        }
    }

    public static void startMoveWindow(MouseEvent event, VBox vbox) {
        startMoveX = event.getScreenX();
        startMoveY = event.getScreenY();
        dragging = true;

        moveTrackingRect = new Rectangle();
        moveTrackingRect.setWidth(vbox.getWidth());
        moveTrackingRect.setHeight(vbox.getHeight());

        moveTrackingPopup = new Popup();
        moveTrackingPopup.getContent().add(moveTrackingRect);
        moveTrackingPopup.show(vbox.getScene().getWindow());
        moveTrackingPopup.setOnHidden( (e) -> resetMoveOperation());
    }

    static private void resetMoveOperation() {
        startMoveX = 0;
        startMoveY = 0;
        dragging = false;
        moveTrackingRect = null;
    }

    public static void endMoveWindow(MouseEvent event, VBox vbox) {
        if (dragging) {
            double endMoveX = event.getScreenX();
            double endMoveY = event.getScreenY();

            Window w = vbox.getScene().getWindow();

            double stageX = w.getX();
            double stageY = w.getY();

            w.setX(stageX + (endMoveX - startMoveX));
            w.setY(stageY + (endMoveY - startMoveY));

            if (moveTrackingPopup != null) {
                moveTrackingPopup.hide();
                moveTrackingPopup = null;
            }
        }

        resetMoveOperation();
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
        stage.setScene(new Scene(root));
        stage.show();
    }

}
