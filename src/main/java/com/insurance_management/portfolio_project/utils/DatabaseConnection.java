package com.insurance_management.portfolio_project.utils;
/**
 * @author team 5
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnection {
    private final DataSource dataSource;

    public DatabaseConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection(); // ✅ Uses Spring-managed connection
        } catch (SQLException e) {
            throw new IllegalStateException("❌ Error connecting to the database", e);
        }
    }

    public void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Successfully connected to the database!");
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
        }
    }
}