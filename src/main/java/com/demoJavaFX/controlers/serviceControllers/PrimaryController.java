package com.demoJavaFX.controlers.serviceControllers;

import com.demoJavaFX.animation.Shake;
import com.demoJavaFX.dao.UserDAO;
import com.demoJavaFX.model.User;
import com.demoJavaFX.service.PasswordUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaryController {

    private UserDAO dao=new UserDAO();

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private JFXButton signUpButton;

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            switchScene(signUpButton, "/fxml/signUp.fxml", "Sign up");
        });

        signInButton.setOnAction(actionEvent -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            if (!login.isEmpty() && !password.isEmpty()) {
                signIn(login, password);
            }
            else {
                new Shake(signInButton);
                System.out.println("Wrong login or password");
                }
        });

        passwordField.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.ENTER))
            {
                String login = loginField.getText().trim();
                String password = passwordField.getText().trim();
                if (!login.isEmpty() && !password.isEmpty()) {
                    signIn(login, password);
                }
                else {
                    new Shake(signInButton);
                    System.out.println("Wrong login or password");
                }
            }
        });

        loginField.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.ENTER))
            {
                String login = loginField.getText().trim();
                String password = passwordField.getText().trim();
                if (!login.isEmpty() && !password.isEmpty()) {
                    signIn(login, password);
                }
                else {
                    new Shake(signInButton);
                    System.out.println("Wrong login or password");
                }
            }
        });
    }

    private void signIn(String login, String password) {

        boolean isLogin = dao.findAll().stream().anyMatch(user1 -> user1.getLogin().equals(login));
        if (isLogin) {
            User user = dao.findAll().stream().filter(user1 -> user1.getLogin().equals(login)).findFirst().get();
            if (PasswordUtils.verifyPassword(password, user.getPassword())) {
                WelcomeController.welcomeText = user.getFirstName() + " " + user.getLastName(); //имя фамилия для привествия
                switchScene(signInButton, "/fxml/welcome.fxml", "welcome");
            }
            else {
                new Shake(signInButton);
                System.out.println("Wrong login or password");
            }
        }
        else {
            new Shake(signInButton);
            System.out.println("Wrong login or password");
        }
    }

    //выделил отдельно повторяющийся код в метод, который переключает при нажатии на JFXButton button
    // на другое окно String fxml
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