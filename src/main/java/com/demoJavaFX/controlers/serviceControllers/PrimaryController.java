package com.demoJavaFX.controlers.serviceControllers;

import com.demoJavaFX.animation.Shake;
import com.demoJavaFX.dao.UserDAO;
import com.demoJavaFX.model.User;
import com.demoJavaFX.service.PasswordUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController {

    private UserDAO dao=new UserDAO();

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button signInButton;

        @FXML
        private Label AuthLabel;

        @FXML
        private TextField loginField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private Button signUpButton;

        @FXML
        void initialize() {
            signUpButton.setOnAction(actionEvent -> {
                switchScene(signUpButton, "/com/demoJavaFX/signUp.fxml");
            });
            signInButton.setOnAction(actionEvent -> {
                String login = loginField.getText().trim();
                String password = passwordField.getText().trim();
                if (!login.isEmpty() && !password.isEmpty()) {
                    signIn(login, password);
                }
                else {
                    Shake shake = new Shake(signInButton);
                    System.out.println("Wrong login or password");
                }
            });
        }

    private void signIn(String login, String password) {

        boolean isLogin = dao.findAll().stream().anyMatch(user1 -> user1.getLogin().equals(login));
        if (isLogin) {
            User user = dao.findAll().stream().filter(user1 -> user1.getLogin().equals(login)).findFirst().get();
            if (PasswordUtils.verifyPassword(password, user.getPassword())) {
                WelcomeController.welcomeText = user.getFirstName() + " " + user.getLastName(); //имя фамилия для привествия
                switchScene(signInButton, "/com/demoJavaFX/welcome.fxml");
            }
            else {
                Shake shake = new Shake(signInButton);
                System.out.println("Wrong login or password");
            }
        }
        else {
            Shake shake = new Shake(signInButton);
            System.out.println("Wrong login or password");
        }
    }

    //выделил отдельно повторяющийся код в метод, который переключает при нажатии на Button button
    // на другое окно String fxml
    private void switchScene(Button button, String fxml) {
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
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}