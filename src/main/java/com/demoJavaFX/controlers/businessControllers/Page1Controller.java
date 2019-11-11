package com.demoJavaFX.controlers.businessControllers;

import com.demoJavaFX.controlers.controllerHelper.ControllerHelper;
import com.demoJavaFX.dao.CaseDAO;
import com.demoJavaFX.dao.SkillDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Page1Controller {

    @FXML
    VBox vbox;

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
    public void close(MouseEvent event) {
        ControllerHelper.close(event);
    }

    @FXML
    public void minimize(MouseEvent event) {
        ControllerHelper.minimize(event);
    }

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
            ControllerHelper.switchScene(signInButton, "/fxml/primary.fxml", "Sign in", Page1Controller.class);
        });

        typeButton.setOnAction(actionEvent -> {
            if (typeButton.isSelected()) typeButton.setText("Тренажер");
            else typeButton.setText("Диагностика");
        });
    }
}