package com.insurancecompany.insurancemanagementgroupproject2.controller;

import com.insurancecompany.insurancemanagementgroupproject2.DatabaseConnection;
import com.insurancecompany.insurancemanagementgroupproject2.model.Dependent;
import com.insurancecompany.insurancemanagementgroupproject2.model.LoginData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class PolicyHolderDependentController {

    @FXML
    private TextField addAddressField;

    @FXML
    private MenuItem addDependent;

    @FXML
    private TextField addEmailField;

    @FXML
    private TextField addFullNameField;

    @FXML
    private TextField addPassWordField;

    @FXML
    private TextField addPhoneNumField;

    @FXML
    private TextField addUserNameField;


    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableView<Dependent> dependentTable;

    @FXML
    private Button clearInputButton;

    @FXML
    private Button clearAddInputBtn;

    @FXML
    private Button confirmAddBtn;

    @FXML
    private TableColumn<?, ?> dependentIDCol;

    @FXML
    private TableColumn<?, ?> emailCol;


    @FXML
    private TableColumn<?, ?> fullNameCol;


    @FXML
    private TableColumn<?, ?> userNameCol;

    @FXML
    private TableColumn<?, ?> passwordCol;


    @FXML
    private TableColumn<?, ?> phoneNumberCol;


    @FXML
    private MenuItem exitBtn;

    @FXML
    private TextField inputUserName;;

    @FXML
    private MenuItem openClaimBtn;


    @FXML
    private MenuItem updateDependent;

    private String userName;

    BcryptPassword bcryptPassword = new BcryptPassword();

    @FXML
    private void initialize() {
        dependentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        dependentTable.widthProperty().addListener((observable, oldValue, newValue) -> {
            double tableWidth = dependentTable.getWidth();
            userNameCol.setPrefWidth(tableWidth * 0.15);
            fullNameCol.setPrefWidth(tableWidth * 0.2);
            dependentIDCol.setPrefWidth(tableWidth * 0.1);
            passwordCol.setPrefWidth(tableWidth * 0.15);
            phoneNumberCol.setPrefWidth(tableWidth * 0.15);
            emailCol.setPrefWidth(tableWidth * 0.15);

        });
        this.userName = LoginData.usernameLogin;
        fetchDependentData();
    }

    @FXML
    void clearInputData(ActionEvent event) {
        addFullNameField.clear();
        addFullNameField.clear();
        addPassWordField.clear();
        addEmailField.clear();
        addPhoneNumField.clear();
        addAddressField.clear();

        fetchDependentData();
    }

    @FXML
    void exitProgram(ActionEvent event) {
        inputUserName.clear();
        fetchDependentData();
    }

    @FXML
    void findDependentUserName(ActionEvent event) {
        String dependentUserName = inputUserName.getText();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        try {
            String findQuery = "SELECT id, full_name, user_name, password, email, phone_number, address " +
                    "FROM users " +
                    "WHERE user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
            preparedStatement.setString(1, dependentUserName);
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<Dependent> foundDependents = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Dependent dependent = new Dependent();
                dependent.setId(resultSet.getString("id"));
                dependent.setFullName(resultSet.getString("full_name"));
                dependent.setUserName(resultSet.getString("user_name"));
                dependent.setPassword(resultSet.getString("password"));
                dependent.setEmail(resultSet.getString("email"));
                dependent.setPhoneNumber(resultSet.getString("phone_number"));
                dependent.setAddress(resultSet.getString("address"));
                foundDependents.add(dependent);

            }
            if(foundDependents.isEmpty()) {
                System.out.println("No dependents found.");  // Debug output
            }

            dependentTable.setItems(foundDependents);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @FXML
    void clearAddFields(ActionEvent event) {
        addUserNameField.clear();
        addAddressField.clear();
        addEmailField.clear();
        addFullNameField.clear();
        addPhoneNumField.clear();
        addPassWordField.clear();
        addAddressField.clear();
    }


    @FXML
    void openAddDependentModal(ActionEvent event) {

    }

    @FXML
    void openClaimModal(ActionEvent event) {

    }

    @FXML
    void updateDependent(ActionEvent event) {

    }
    @FXML
    void confirmAddClaim(ActionEvent event) {
        String dependentId = generatedRandomUserId();

        String userName = addUserNameField.getText();
        String fullName = addFullNameField.getText();
        String passwordPlainText = addPassWordField.getText();
        String phoneNumber = addPhoneNumField.getText();
        String email = addEmailField.getText();

        String address = addAddressField.getText();

        int roleId = 6;


        if (userName.isEmpty() || fullName.isEmpty() || passwordPlainText.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        }
        String passWordHashed = bcryptPassword.hashBcryptPassword(passwordPlainText);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()){
            String policyHolderQuery = "SELECT id FROM users WHERE user_name = ?";
            PreparedStatement policyHolderStatement = connection.prepareStatement(policyHolderQuery);
            policyHolderStatement.setString(1, this.userName);
            ResultSet rs = policyHolderStatement.executeQuery();
            String policyHolderId = null;

            if (rs.next()) {
                policyHolderId = rs.getString("id");
            }
            if (policyHolderId == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Policy holder not found.");
                alert.showAndWait();
                return;
            }


            String insertQuery = "INSERT INTO public.users (id, full_name, user_name, password, role_id, email, phone_number, address)" +
                    "VALUES (?, ?, ?, ?, ? , ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, dependentId);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, passWordHashed);
            preparedStatement.setInt(5, roleId);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, address);

            preparedStatement.executeUpdate();

            String insertRelationQuery = "INSERT INTO dependent (dependent_id, policy_holder_id) VALUES (?, ?)";
            PreparedStatement relationStatement = connection.prepareStatement(insertRelationQuery);
            relationStatement.setString(1, dependentId);
            relationStatement.setString(2, policyHolderId);
            relationStatement.executeUpdate();

            fetchDependentData();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Dependent add successfully");
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add dependent: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void fetchDependentData() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        ObservableList<Dependent> dependentData = FXCollections.observableArrayList();

        try {
            String getDependentQuery = "SELECT d.id, d.full_name, d.user_name,d.password, d.email, d.phone_number, d.address " +
                    "FROM users d " +
                    "JOIN dependent dep " +
                    "ON d.id = dep.dependent_id " +
                    "JOIN users p " +
                    "ON p.id = dep.policy_holder_id " +
                    "WHERE p.user_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(getDependentQuery);
            preparedStatement.setString(1, userName);
            ResultSet queryResult = preparedStatement.executeQuery();
            while (queryResult.next()) {
                Dependent dependent = new Dependent();
                dependent.setId(queryResult.getString("id"));
                dependent.setFullName(queryResult.getString("full_name"));
                dependent.setUserName(queryResult.getString("user_name"));
                dependent.setPassword(queryResult.getString("password"));
                dependent.setEmail(queryResult.getString("email"));
                dependent.setPhoneNumber(queryResult.getString("phone_number"));
                dependent.setAddress(queryResult.getString("address"));

                dependentData.add(dependent);
            }
            dependentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
            fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));


            dependentTable.setItems(dependentData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    private String generatedRandomUserId() {
        StringBuilder dependentID = new StringBuilder("C");
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            dependentID.append(random.nextInt(10));
        }
        return dependentID.toString();
    }

}

