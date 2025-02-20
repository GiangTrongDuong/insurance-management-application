package com.insurance_management.portfolio_project.utils.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public enum Role {
    SYSTEM_ADMIN(1, "system admin"),
    INSURANCE_MANAGER(2, "insurance manager"),
    INSURANCE_SURVEYOR(3, "insurance surveyor"),
    POLICY_OWNER(4, "policy owner"),
    POLICY_HOLDER(5, "policy holder"),
    DEPENDENT(6, "dependent");

    private final Integer id;
    private final String roleName;
}
