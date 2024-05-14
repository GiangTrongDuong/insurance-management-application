package com.insurancecompany.insurancemanagementgroupproject2.controller;

import com.insurancecompany.insurancemanagementgroupproject2.DatabaseConnection;
import com.insurancecompany.insurancemanagementgroupproject2.HelloApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import com.insurancecompany.insurancemanagementgroupproject2.model.Claim;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class ClaimController {
    @FXML
    private Pane addClaimPane;

    @FXML
    private TextField bankNameField;

    @FXML
    private TextField bankNumberField;

    @FXML
    private TextField bankUserNameField;

    @FXML
    private Button btnUploadDocuments;



    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private TextField claimAmountInput;

    @FXML
    private Button confirmAddButton;

    @FXML
    private TextField insuredPersonInput;

    @FXML
    private Label validationMessage;


    private String currentClaimId;

    private List<String> uploadedDocumentNames = new ArrayList<>();




    @FXML
    void confirmAddClaim(ActionEvent event)  {
        if (currentClaimId == null || currentClaimId.isEmpty()) {
            currentClaimId = generateRandomClaimID();
        }
        btnUploadDocuments.setDisable(false);
        System.out.println("Current claim ID: " + currentClaimId);
        String cardNumber = cardNumberInput.getText();
        String claimAmountText = claimAmountInput.getText();
        String insuredPerson = insuredPersonInput.getText();
        String bankName = bankNameField.getText();
        String bankUserName = bankUserNameField.getText();
        String bankNumber = bankNumberField.getText();

        if (cardNumber.isEmpty() || claimAmountText.isEmpty() || insuredPerson.isEmpty() || bankName.isEmpty() || bankUserName.isEmpty() || bankNumber.isEmpty()) {
            System.out.println("Debug: cardNumber: " + cardNumber);
            System.out.println("Debug: claimAmountText: " + claimAmountText);
            System.out.println("Debug: insuredPerson: " + insuredPerson);
            validationMessage.setText("Please fill in all fields");
            return;

        }
        double claimAmount = Double.parseDouble(claimAmountText);
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection()) {
            String insertQuery = "INSERT INTO public.claims (claim_id, insured_person, card_number, exam_date, claim_date, claim_amount, status, bank_name, bank_user_name, bank_number) " +
                    "VALUES (?, ?, ?, NULL, NULL, ?, 'NEW', ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.currentClaimId);
            preparedStatement.setString(2, insuredPerson);
            preparedStatement.setString(3, cardNumber);
            preparedStatement.setDouble(4, claimAmount);
            preparedStatement.setString(5, bankName);
            preparedStatement.setString(6, bankUserName);
            preparedStatement.setString(7, bankNumber);

            preparedStatement.executeUpdate();
            for (String documentName: uploadedDocumentNames) {
                saveDocumentDetails(currentClaimId, documentName);
            }
            showAlert(Alert.AlertType.INFORMATION, "Claim and associated documents added successfully.");
            clearInputFields();
            uploadedDocumentNames.clear();
        } catch (SQLException e) {
            validationMessage.setText("Error: Unable to add claim. Please try again.");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void backToHomePage(ActionEvent event) {
       Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       currentStage.close();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message);
        alert.showAndWait();
    }


    private void clearInputFields() {
        cardNumberInput.clear();
        claimAmountInput.clear();
        insuredPersonInput.clear();
        bankNameField.clear();
        bankNumberField.clear();
        bankUserNameField.clear();
        validationMessage.setText("");
    }
    private String generateRandomClaimID() {
        StringBuilder claimId = new StringBuilder("F");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            claimId.append(random.nextInt(10));
        }
        return claimId.toString();
    }

    @FXML
    void uploadMultipleFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);


        List<File> files = fileChooser.showOpenMultipleDialog(((Node) event.getSource()).getScene().getWindow());
        if (files != null && !files.isEmpty()) {
            for (File file : files) {
                String renamedFile = renameAndSaveFile(file);
                if (renamedFile != null) {
                    uploadedDocumentNames.add(renamedFile);
                } else {
                    System.out.println("Skipping file due to renaming error.");
                }
            }
        } else {
            System.out.println("No files were selected.");
        }
    }

    private String renameAndSaveFile(File originalFile) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);


        String originalFileName = originalFile.getName();
        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String extension = getFileExtension(originalFile);

        String newFileName = formattedDate + "_" + baseName + extension;;
        System.out.println("File renamed to: " + newFileName);
        return newFileName;

    }

    private void saveDocumentDetails(String claimId, String documentName) throws SQLException {
        String insertQuery = "INSERT INTO documents (claim_id, document_name) VALUES (?, ?)";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, claimId);
            preparedStatement.setString(2, documentName);
            preparedStatement.executeUpdate();
        }
    }


    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");

        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

        public static List<Claim> fetchClaim() {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            List<Claim> claimList = new ArrayList<Claim>();
            try {
                String getClaimsQuery = "SELECT * FROM claims";
                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(getClaimsQuery);
                while (queryResult.next()) {
                    Claim claim = new Claim();
                    claim.setId(queryResult.getString("claim_id"));
                    claim.setInsuredPerson(queryResult.getString("insured_person"));
                    claim.setCardNumber(queryResult.getString("card_number"));
                    claim.setExamDate(queryResult.getDate("exam_date"));
                    claim.setClaimDate(queryResult.getDate("claim_date"));
                    claim.setClaimAmount(queryResult.getFloat("claim_amount"));
                    claim.setStatus(queryResult.getString("status"));
                    claim.setBankName(queryResult.getString("bank_name"));
                    claim.setBankUserName(queryResult.getString("bank_user_name"));
                    claim.setBankNumber(queryResult.getString("bank_number"));
                    claimList.add(claim);
                }
                System.out.println("Fetch data from database.claim successfully!");
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQL error: " + e);

            }
            return claimList;
        }

        public static boolean proposeClaim (String claimID){
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            try {
                String proposeClaim = "UPDATE claims SET status = 'PROCESSING', exam_date = CURRENT_DATE WHERE claim_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(proposeClaim);
                preparedStatement.setString(1, claimID);
                preparedStatement.execute();
                System.out.println("Successfully propose claim " + claimID);
                connection.close();
                return true;
            } catch (SQLException e) {
                System.out.println("Error in SQL function proposeClaim: " + e);
                return false;
            }
        }


    public static boolean resubmitClaim(String claimID){
        //Database connection
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        try{
            String resubmitClaim = "UPDATE claims SET status = 'RESUBMIT', exam_date = NULL  WHERE claim_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(resubmitClaim);
            preparedStatement.setString(1,claimID);
            preparedStatement.execute();
            System.out.println("Successfully resubmit claim " + claimID);
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in SQL function resubmitClaim: " + e);
            return false;
        }
    }

    public static boolean rejectClaim(String claimID){
        //Database connection
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        try{
            String rejectClaim = "UPDATE claims SET status = 'REJECT', claim_date = CURRENT_DATE WHERE claim_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(rejectClaim);
            preparedStatement.setString(1,claimID);
            preparedStatement.execute();
            System.out.println("Successfully reject claim " + claimID);
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in SQL function rejectClaim: " + e);
            return false;
        }
    }

    public static boolean approveClaim(String claimID){
        //Database connection
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        try{
            String approveClaim = "UPDATE claims SET status = 'DONE', claim_date = CURRENT_DATE WHERE claim_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(approveClaim);
            preparedStatement.setString(1,claimID);
            preparedStatement.execute();
            System.out.println("Successfully approve claim " + claimID);
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in SQL function approveClaim: " + e);
            return false;
        }
    }

}
