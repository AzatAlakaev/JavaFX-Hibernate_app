package com.demoJavaFX.controlers.serviceControllers;

import com.demoJavaFX.animation.Shake;
import com.demoJavaFX.controlers.controllerHelper.ControllerHelper;
import com.demoJavaFX.dao.UserDAO;
import com.demoJavaFX.model.User;
import com.demoJavaFX.service.PasswordUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PrimaryController {

    private UserDAO dao=new UserDAO();

    @FXML
    VBox vbox;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private JFXButton signUpButton;

    @FXML
    public void close(MouseEvent event) {
        ControllerHelper.close(event);
    }

    @FXML
    public void minimize(MouseEvent event) {
        ControllerHelper.minimize(event);
    }

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            ControllerHelper.switchScene(signUpButton, "/fxml/signUp.fxml", "Sign up", PrimaryController.class);
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
                ControllerHelper.switchScene(signInButton, "/fxml/welcome.fxml", "welcome", PrimaryController.class);
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

}