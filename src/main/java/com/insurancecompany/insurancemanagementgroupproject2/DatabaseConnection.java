package com.insurancecompany.insurancemanagementgroupproject2;
/**
 * @author team 5
 */
import com.insurancecompany.insurancemanagementgroupproject2.constant.URLConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection getConnection() {
        Connection databaseLink = null;
        // Catching error
        try {
            databaseLink = DriverManager.getConnection(URLConstant.DATABASE_URL);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to the database", e);
        }
        return databaseLink;
    }
}