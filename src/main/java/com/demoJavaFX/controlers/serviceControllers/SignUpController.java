package com.demoJavaFX.controlers.serviceControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.demoJavaFX.animation.Shake;
import com.demoJavaFX.dao.UserDAO;
import com.demoJavaFX.model.User;
import com.demoJavaFX.service.PasswordUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button signUpButton;

    @FXML
    private RadioButton radioStudentButton;

    @FXML
    private RadioButton radioPrepodButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(actionEvent -> {
            if (signUp()) {
                switchScene(signUpButton, "/com/demoJavaFX/primary.fxml");
            }
            else {
                Shake shake = new Shake(signUpButton);
                System.out.println("No way");
            }
        });

        backButton.setOnAction(actionEvent -> {
            switchScene(backButton, "/com/demoJavaFX/primary.fxml");
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
            Shake shake = new Shake(signUpButton);
            System.out.println("No way");
        }
        return isSignUp;
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
