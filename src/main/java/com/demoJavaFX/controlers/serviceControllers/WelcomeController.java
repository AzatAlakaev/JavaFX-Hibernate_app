package com.demoJavaFX.controlers.serviceControllers;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Label welcomeField;

    @FXML
    private Text textField;

    @FXML
    private JFXButton helloButton;

    public static String welcomeText; //имя фамилия для привествия

    @FXML
    void initialize() {
        textField.setText(welcomeText); //имя фамилия для привествия
        helloButton.setOnAction(actionEvent -> {
            switchScene(helloButton, "/fxml/page1.fxml", "Конструктор");
        });

        textField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                switchScene(helloButton, "/fxml/page1.fxml", "Конструктор");
            }
        });
    }

    private void switchScene(JFXButton button, String fxml, String title) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
