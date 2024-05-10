package com.insurancecompany.insurancemanagementgroupproject2.controller;

import com.insurancecompany.insurancemanagementgroupproject2.model.Claim;
import com.insurancecompany.insurancemanagementgroupproject2.model.LoginData;
import com.insurancecompany.insurancemanagementgroupproject2.model.Surveyor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

public class ManagerHomepage extends SurveyorHomepage{
    @FXML
    public TableView<Surveyor> surveyorTable;
    @FXML
    public Label managerName;
    @FXML
    private Button fetchAllClaimButton;
    @FXML
    private Button fetchProposableClaimButton;
    @FXML
    private Button fetchSingleClaimButton;
    @FXML
    private Button refreshData;
    @FXML
    private Label errorLabel;
    @FXML
    private ChoiceBox<String> claimChoiceBox;
    @FXML
    private Button sortPerson;
    @FXML
    private Button sortCard;
    @FXML
    private TableView<Claim> claimTable;
    @FXML
    private TableColumn<?, ?> claimID;
    @FXML
    private TableColumn<?, ?> cardNumber;
    @FXML
    private TableColumn<?, ?> claimAmount;
    @FXML
    private TableColumn<?, ?> claimDate;
    @FXML
    private TableColumn<?, ?> bankName;
    @FXML
    private TableColumn<?, ?> bankNumber;
    @FXML
    private TableColumn<?, ?> bankUserName;
    @FXML
    private TableColumn<?, ?> examDate;
    @FXML
    private TableColumn<?, ?> insuredPerson;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private TableColumn<?, ?> surveyorID;
    @FXML
    private TableColumn<?, ?> surveyorFullName;
    @FXML
    private TableColumn<?, ?> surveyorUserName;
    @FXML
    private TableColumn<?, ?> surveyorEmail;
    @FXML
    private TableColumn<?, ?> surveyorPhoneNumber;
    private List<Claim> claimList = new ArrayList<Claim>();
    private SurveyorController surveyorController;
    @FXML
    private void initialize() {
        surveyorController = new SurveyorController();
        // Set up column widths and cell value factories
        claimTable.widthProperty().addListener((observable, oldValue, newValue) -> {
            double tableWidth = claimTable.getWidth();
            // Set preferred widths for columns based on table width
            // This ensures that columns resize dynamically
            claimID.setPrefWidth(tableWidth * 0.1);
            insuredPerson.setPrefWidth(tableWidth * 0.2);
            cardNumber.setPrefWidth(tableWidth * 0.15);
            examDate.setPrefWidth(tableWidth * 0.1);
            claimDate.setPrefWidth(tableWidth * 0.1);
            claimAmount.setPrefWidth(tableWidth * 0.1);
            status.setPrefWidth(tableWidth * 0.1);
            bankName.setPrefWidth(tableWidth * 0.15);
            bankUserName.setPrefWidth(tableWidth * 0.15);
            bankNumber.setPrefWidth(tableWidth * 0.15);

            claimID.setCellValueFactory(new PropertyValueFactory<>("id"));
            insuredPerson.setCellValueFactory(new PropertyValueFactory<>("insuredPerson"));
            cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
            examDate.setCellValueFactory(new PropertyValueFactory<>("examDate"));
            claimDate.setCellValueFactory(new PropertyValueFactory<>("claimDate"));
            claimAmount.setCellValueFactory(new PropertyValueFactory<>("claimAmount"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            bankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
            bankUserName.setCellValueFactory(new PropertyValueFactory<>("bankUserName"));
            bankNumber.setCellValueFactory(new PropertyValueFactory<>("bankNumber"));
        });
        surveyorTable.widthProperty().addListener((observable, oldValue, newValue) -> {
                    surveyorID.setCellValueFactory(new PropertyValueFactory<>("id"));
                    surveyorFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
                    surveyorUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
                    surveyorEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                    surveyorPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        });
        // Set event handlers for buttons
        fetchAllClaimButton.setOnAction(fetchAllClick);
        fetchProposableClaimButton.setOnAction(fetchProposalClick);
        fetchSingleClaimButton.setOnAction(approveClaimClick);
        sortPerson.setOnAction(sortByPerson);
        sortCard.setOnAction(sortByCard);
        refreshData.setOnAction(refreshClaimData);
        logout.setOnAction(logoutClick);
        managerName.setText("Welcome Insurance Manager " + LoginData.usernameLogin);
        //Call API to fetch claim data from database
        claimList = fetchClaimData();
        fetchSurveyorData();
    }
    EventHandler<ActionEvent> refreshClaimData = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            claimList = fetchClaimData();
            fetchSurveyorData();
        }
    };
    EventHandler<ActionEvent> approveClaimClick = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (claimChoiceBox.getValue() == null) {
                errorLabel.setText("Cannot approve empty claim!");
            } else {
                fetchSingleClaim(claimChoiceBox.getValue());
            }
        }
    };
    public void fetchSurveyorData(){
        ObservableList<Surveyor> surveyorObservableList = FXCollections.observableArrayList();
        surveyorObservableList.addAll(surveyorController.fetchSurveyor());
        surveyorTable.setItems(surveyorObservableList);
    }

    @Override
    public List<Claim> fetchClaimData() {
        //Create ObservableList for TableView
        ObservableList<Claim> claimData = FXCollections.observableArrayList();
        ObservableList<String> processingClaimID = FXCollections.observableArrayList();
        claimList = ClaimController.fetchClaim();
        //Handling SQL exception by surrounding try catch
        claimData.addAll(claimList);
        //Set view table
        claimTable.setItems(claimData);
        for (Claim claim : claimList){
            if(claim.getStatus().equals("PROCESSING")){
                processingClaimID.add(claim.getId());
            }
        }
        claimChoiceBox.setItems(processingClaimID);
        return claimList;
    }

    @Override
    public void fetchSingleClaim(String claimID) {
        ObservableList<Claim> claimData = FXCollections.observableArrayList();
        boolean statusCheck = false;
        for(Claim claim : claimList){
            if(claim.getId().equals(claimID)){
                claimData.add(claim);
                if(claim.getStatus().equals("PROCESSING")){
                    statusCheck = true;
                }
                break;
            }
        }
        claimTable.setItems(claimData);
        if(statusCheck){createProposeAlert(claimID);
        }
    }

    @Override
    public void createProposeAlert(String claimID) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Approval confirmation");
        alert.setContentText("Approving " + claimID + " status?");
        // Define custom buttons, set it to the alert
        ButtonType approveButton = new ButtonType("Approve claim");
        ButtonType denyButton = new ButtonType("Deny claim");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        alert.getButtonTypes().setAll(approveButton,denyButton,cancelButton);
        //Logic to handle operation of button
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == approveButton) {
            //Handle proposing a claim to manager
            boolean success = ClaimController.approveClaim(claimID);
            System.out.println("Claim " + claimID + " approved successfully: " + success);
            claimChoiceBox.setValue(null);
            fetchClaimData();
        } else if (result.get() == denyButton) {
            // Handle requesting more information from a claim
            boolean success = ClaimController.rejectClaim(claimID);
            System.out.println("Claim " + claimID + " denied: " + success);
            claimChoiceBox.setValue(null);
            fetchClaimData();
        }else {
            //Handle cancellation of operation
            claimChoiceBox.setValue(null);
            System.out.println("Claim proposing cancelled.");
            fetchAllClaimData();
        }
    }
}
