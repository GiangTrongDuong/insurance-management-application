package com.insurancecompany.insurancemanagementgroupproject2.model;

public class Dependent extends Customer{

    public Dependent(String id, String full_name, String user_name, String email, String phone_number, String address) {
        super(id, full_name, user_name, email, phone_number, address);
    }

    public Dependent() {
        super();
    }
}
