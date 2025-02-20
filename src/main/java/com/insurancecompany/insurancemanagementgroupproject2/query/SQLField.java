package com.insurancecompany.insurancemanagementgroupproject2.query;

public class SQLField {
    public static final String ID = "id";
    public static final String FULL_NAME = "full_name";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ADDRESS = "address";
    public static final String ROLE = "role_id";

    private SQLField() {
        throw new IllegalArgumentException("Constant SQL Field");
    }
}
