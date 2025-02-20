package com.insurancecompany.insurancemanagementgroupproject2.query;

public class SQLQueries {
    // Login
    public static final String FETCH_LOGIN = "SELECT password, role_id FROM users WHERE user_name = ?";

    // Constant for the claim query
    public static final String QUERY_ALL_CLAIMS = "SELECT * FROM claims";

    // Constant for the surveyor query
    public static final String GET_SURVEYOR_QUERY = "SELECT * FROM USER WHERE role_id = 3";
    public static final String INSERT_SURVEYOR = "INSERT INTO users VALUES (?,?,?,?, 3,?,?,?)";
    public static final String UPDATE_SURVEYOR= "UPDATE users SET full_name = ?, email = ?, phone_number = ?, address = ? WHERE id = ?";
    public static final String DELETE_SURVEYOR = "DELETE FROM users WHERE id = ?";
    // Private constructor to prevent instantiation
    private SQLQueries() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}