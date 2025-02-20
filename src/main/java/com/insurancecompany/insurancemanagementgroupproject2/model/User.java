package com.insurancecompany.insurancemanagementgroupproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author team 5
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private String id;

    private String fullName;

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;

    private int roleId;
}