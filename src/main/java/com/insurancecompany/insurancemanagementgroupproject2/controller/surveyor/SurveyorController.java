package com.insurancecompany.insurancemanagementgroupproject2.controller.surveyor;
/**
 * @author team 5
 */
import com.insurancecompany.insurancemanagementgroupproject2.DatabaseConnection;
import com.insurancecompany.insurancemanagementgroupproject2.constant.ErrorMessage;
import com.insurancecompany.insurancemanagementgroupproject2.constant.SQLField;
import com.insurancecompany.insurancemanagementgroupproject2.model.Surveyor;
import com.insurancecompany.insurancemanagementgroupproject2.query.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyorController {
    private final DatabaseConnection databaseConnection;
    // Constructor to SurveyorController
    public SurveyorController(DatabaseConnection databaseConnection) {
         this.databaseConnection = databaseConnection;
    }
    // Method to fetch surveyor information from user table from database
    public List<Surveyor> fetchSurveyor() throws SQLException {
        Connection connection = null;
        Statement statement;
        List<Surveyor> surveyorList = new ArrayList<>();
        try {
            connection = databaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(SQLQueries.GET_SURVEYOR_QUERY);
            while (queryResult.next()){
                Surveyor provider = new Surveyor(
                        queryResult.getString(SQLField.ID),
                        queryResult.getString(SQLField.FULL_NAME),
                        queryResult.getString(SQLField.USER_NAME),
                        queryResult.getString(SQLField.PASSWORD),
                        queryResult.getString(SQLField.EMAIL),
                        queryResult.getString(SQLField.PHONE_NUMBER),
                        queryResult.getString(SQLField.ADDRESS),
                        queryResult.getInt(SQLField.ROLE)
                );
                surveyorList.add(provider);
            }
        } catch (SQLException e){
            throw new IllegalArgumentException(ErrorMessage.SQL_ERROR_OPERATION, e);
        } finally {
            assert connection != null;
            connection.close();
        }
        return surveyorList;
    }
    // Method to create new surveyor inside user database table
    public boolean createNewSurveyor(String id,
                                     String fullName,
                                     String userName,
                                     String password,
                                     String email,
                                     String phoneNumber,
                                     String address) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try{
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQLQueries.INSERT_SURVEYOR);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,fullName);
            preparedStatement.setString(3,userName);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,email);
            preparedStatement.setString(6,phoneNumber);
            preparedStatement.setString(7,address);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            throw new IllegalArgumentException(ErrorMessage.SQL_ERROR_OPERATION, e);
        } finally {
            assert connection != null;
            connection.close();
        }
    }
    // Method to update user information for surveyor existing
    public boolean updateSurveyorInformation(String id,String fullName, String email, String phoneNumber, String address) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try{
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_SURVEYOR);
            preparedStatement.setString(1,fullName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,phoneNumber);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,id);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            throw new IllegalArgumentException(ErrorMessage.SQL_ERROR_OPERATION, e);
        } finally {
            assert connection != null;
            connection.close();
        }
    }
    // Method to remove a surveyor from user database table
    public boolean removeSurveyor(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = databaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQLQueries.DELETE_SURVEYOR);
            preparedStatement.setString(1,id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e){
            throw new IllegalArgumentException(ErrorMessage.SQL_ERROR_OPERATION, e);
        } finally {
            assert connection != null;
            connection.close();
        }
    }
}
