package com.demoJavaFX.controlers.businessControllers;

import com.demoJavaFX.dao.CaseDAO;
import com.demoJavaFX.dao.SkillDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Page1Controller {

    @FXML
    private Label skillField;

    @FXML
    private Label descriptionField;

    @FXML
    private Label taskFileld;

    @FXML
    private Label descriptionField1;

    @FXML
    private JFXComboBox<String> skillBox;

    @FXML
    private JFXTextArea textOfSkill;

    @FXML
    private JFXComboBox<String> caseBox;

    @FXML
    private JFXTextArea textOfCase;

    @FXML
    private JFXButton signInButton;

    @FXML
    private JFXToggleButton typeButton;

    @FXML
    void initialize() {
        SkillDAO skillDAO=new SkillDAO();
        ObservableList<String> listOfSkillNames = FXCollections.observableArrayList(skillDAO.findAll().stream()
                                                    .flatMap(skill -> Stream.of(skill.getName()))
                                                    .collect(Collectors.toList()));
        skillBox.setItems(listOfSkillNames);
        skillBox.getSelectionModel().select(0);
        textOfSkill.setText(skillBox.getValue());
        textOfSkill.setText(skillDAO.findByName(skillBox.getValue()).getNote());
        textOfSkill.setEditable(false);
        skillBox.setOnAction(actionEvent -> textOfSkill.setText(skillDAO.findByName(skillBox.getValue()).getNote()));

        CaseDAO caseDAO = new CaseDAO();
        ObservableList<String> listOfCaseNames = FXCollections.observableArrayList(caseDAO.findAll().stream()
                .flatMap(aCase -> Stream.of(aCase.getName()))
                .collect(Collectors.toList()));
        caseBox.setItems(listOfCaseNames);
        caseBox.getSelectionModel().select(0);
        textOfCase.setText(caseBox.getValue());
        textOfCase.setText(caseDAO.findByName(caseBox.getValue()).getNote());
        textOfCase.setEditable(false);
        caseBox.setOnAction(actionEvent -> textOfCase.setText(caseDAO.findByName(caseBox.getValue()).getNote()));

        signInButton.setOnAction(actionEvent -> {
            switchScene(signInButton, "/fxml/primary.fxml");
        });

        typeButton.setOnAction(actionEvent -> {
            if (typeButton.isSelected()) typeButton.setText("Тренажер");
            else typeButton.setText("Диагностика");
        });
    }

    private void switchScene(JFXButton button, String fxml) {
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
        stage.setScene(new Scene(root));
        stage.show();
    }
}