package com.insurancecompany.insurancemanagementgroupproject2.query;

public class SQLQueries {

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