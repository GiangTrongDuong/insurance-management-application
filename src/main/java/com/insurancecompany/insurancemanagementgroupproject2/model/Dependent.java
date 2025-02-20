package com.insurancecompany.insurancemanagementgroupproject2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author team 5
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dependent extends User{
    private String policyHolderName;

    public Dependent(String id, String fullName, String userName, String password, String email, String phoneNumber, String address, int roleId) {
    }
}
