package com.demoJavaFX.controlers.serviceControllers;

import com.demoJavaFX.controlers.controllerHelper.ControllerHelper;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WelcomeController {

    @FXML
    private Label welcomeField;

    @FXML
    private Text textField;

    @FXML
    private JFXButton helloButton;

    @FXML
    VBox vbox;


    public static String welcomeText; //имя фамилия для привествия

    @FXML
    public void moveWindow(MouseEvent event) {
        ControllerHelper.moveWindow(event, vbox);
    }

    @FXML
    public void startMoveWindow(MouseEvent event) {
        ControllerHelper.startMoveWindow(event, vbox);
    }

    @FXML
    public void endMoveWindow(MouseEvent event) {
        ControllerHelper.endMoveWindow(event, vbox);
    }

    @FXML
    void initialize() {
        textField.setText(welcomeText); //имя фамилия для привествия
        helloButton.setOnAction(actionEvent -> {
            ControllerHelper.switchScene(helloButton, "/fxml/page1.fxml", "Конструктор", WelcomeController.class);
        });

        textField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                ControllerHelper.switchScene(helloButton, "/fxml/page1.fxml", "Конструктор", WelcomeController.class);
            }
        });
    }

}
