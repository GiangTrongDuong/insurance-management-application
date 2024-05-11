package com.insurancecompany.insurancemanagementgroupproject2.controller;

import com.insurancecompany.insurancemanagementgroupproject2.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateSurveyorController {
    @FXML
    public TextField full_name;
    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    @FXML
    public TextField email;
    @FXML
    public TextField phone_number;
    @FXML
    public TextField address;
    @FXML
    public Button submitSurveyor;
    @FXML
    private Label errorLabel;
    private SurveyorController surveyorController;
    private String id;
    @FXML
    private void initialize(){
        surveyorController = new SurveyorController();
        submitSurveyor.setOnAction(ActionEvent -> createSurveyor());
        ManagerPageController managerPageController = new ManagerPageController();
        id = managerPageController.createSurveyorID();
    }

    private void createSurveyor(){
        if (full_name.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty() || email.getText().isEmpty()
                || phone_number.getText().isEmpty() || address.getText().isEmpty()) {
            errorLabel.setText("Please fill in all fields");
            return;
        }

        if (!ValidateInput.isValidEmail(email.getText())) {
            errorLabel.setText("Invalid email format");
            return;
        }

        // Validate phone number
        if (!ValidateInput.isValidPhoneNumber(phone_number.getText())) {
            errorLabel.setText("Invalid phone number format");
            return;
        }

        surveyorController.createNewSurveyor(
                id,full_name.getText(),username.getText(),password.getText(),email.getText(),
                phone_number.getText(),address.getText());

        System.out.println("Created surveyor " + full_name.getText() + " successfully!");
        SceneLoader.loadSceneWithInput("fxml/manager-homepage.fxml",thisStage(),944,709);
    }

    private Stage thisStage(){
        Stage thisStage = (Stage) submitSurveyor.getScene().getWindow();
        return thisStage;
    }
}
