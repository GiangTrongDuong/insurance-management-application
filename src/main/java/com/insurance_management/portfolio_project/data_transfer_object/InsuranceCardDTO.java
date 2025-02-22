package com.insurance_management.portfolio_project.data_transfer_object;

import lombok.Data;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.sql.Date;

@Data
public class InsuranceCardDTO {

    @NonNull
    private String cardHolderId;

    @NonNull
    private Date expirationDate;

    @NonNull
    private String policyOwnerId;
}
