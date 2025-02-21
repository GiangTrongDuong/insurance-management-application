package com.insurance_management.portfolio_project.utils.constant;

public class SQLQuery {
    // Check if the generated ID already exists in the database
    public static final String CHECK_CARD_NUMBER_UNIQUE = "SELECT COUNT(*) FROM insurance_card WHERE card_number = ?";

    private SQLQuery(){}
}
