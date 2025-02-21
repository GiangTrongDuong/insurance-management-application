package com.insurance_management.portfolio_project.utils;
/**
 * @author team 5
 */

import com.insurance_management.portfolio_project.utils.constant.DebuggingConstant;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@Component
public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    private final DataSource dataSource;

    public DatabaseConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(DebuggingConstant.CONNECT_FAIL, e);
        }
    }

    public void testConnection() {
        try (Connection conn = getConnection()) {
            logger.info(DebuggingConstant.CONNECT_SUCCESS);
        } catch (Exception e) {
            logger.info(DebuggingConstant.CONNECT_FAIL + e.getMessage());
        }
    }
}