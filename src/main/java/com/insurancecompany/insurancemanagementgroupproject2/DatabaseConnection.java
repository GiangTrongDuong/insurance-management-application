package com.insurancecompany.insurancemanagementgroupproject2;
/**
 * @author team 5
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public DatabaseConnection() {
        throw new IllegalStateException("Utility class");
    }

    public Connection getConnection() {
        Connection databaseLink = null;
        String url = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.kdvbunduidmibrvamrpj&password=Group5PasswordHardToGuess";
        // Catching error
        try {
            databaseLink = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to the database", e);
        }
        return databaseLink;
    }
}