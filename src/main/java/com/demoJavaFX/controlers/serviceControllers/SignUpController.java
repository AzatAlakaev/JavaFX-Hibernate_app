package com.demoJavaFX.controlers.serviceControllers;

import com.demoJavaFX.animation.Shake;
import com.demoJavaFX.dao.UserDAO;
import com.demoJavaFX.model.User;
import com.demoJavaFX.service.PasswordUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private JFXRadioButton radioStudentButton;

    @FXML
    private ToggleGroup group;

    @FXML
    private JFXRadioButton radioPrepodButton;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXTextField firstNameField;

    @FXML
    private JFXTextField lastNameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    void initialize() {

        signUpButton.setOnAction(actionEvent -> {
            if (signUp()) {
                switchScene(signUpButton, "/fxml/primary.fxml", "Sign in");
            }
            else {
                new Shake(signUpButton);
                System.out.println("No way");
            }
        });

        backButton.setOnAction(actionEvent -> {
            switchScene(backButton, "/fxml/primary.fxml", "Sign in");
        });

    }

    private boolean signUp() {
        UserDAO dao=new UserDAO();
        boolean isSignUp=false;
        String firstName=firstNameField.getText().trim(); //.trim() чтобы убрать не нужные пробелы
        String lastName=lastNameField.getText().trim();
        String login=loginField.getText().trim();
        String password=passwordField.getText().trim();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !login.isEmpty() && !password.isEmpty()) {
            boolean isLogin = dao.findAll().stream().anyMatch(user1 -> user1.getLogin().equals(login));
            if (!isLogin) {
                boolean isStudent = radioStudentButton.isSelected();
                int type=0;
                if (isStudent) {
                    type=1;
                }
                else type=2;

                User user=new User(firstName, lastName, login, PasswordUtils.hashPassword(password), type);
                dao.save(user);
                isSignUp=true;
            }
        }
        else {
            new Shake(signUpButton);
            System.out.println("No way");
        }
        return isSignUp;
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
