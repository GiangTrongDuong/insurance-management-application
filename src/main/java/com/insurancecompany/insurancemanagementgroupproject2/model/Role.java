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
public class Role {
    private int id;
    private String roleName;

    public Role(int id, String roleName) {
    }
}
