package com.insurancecompany.insurancemanagementgroupproject2.controller;
/**
 * @author team 5
 */
import com.insurancecompany.insurancemanagementgroupproject2.DatabaseConnection;
import com.insurancecompany.insurancemanagementgroupproject2.query.SQLField;
import com.insurancecompany.insurancemanagementgroupproject2.query.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    // Create an instance of BcryptPassword class object
    BcryptPassword bcryptPassword = new BcryptPassword();
    // Method to validate field and allow user to login/showing login error
    public int validateLogin(String usernameTextField, String passwordField) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        // Perform logic to very file input, Catching exception
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.FETCH_LOGIN);
            preparedStatement.setString(1, usernameTextField);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Check value of password
            if (resultSet.next()) {
                String hashedPasswordFromDB = resultSet.getString(SQLField.PASSWORD);
                int roleId = resultSet.getInt(SQLField.ROLE);
                // Boolean for logic check
                boolean passwordMatch = bcryptPassword.verifyHashedPassword(hashedPasswordFromDB, passwordField);
                // Verify password
                if (passwordMatch) {
                    return roleId;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
