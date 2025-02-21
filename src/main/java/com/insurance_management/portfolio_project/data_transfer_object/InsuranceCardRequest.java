package com.insurance_management.portfolio_project.data_transfer_object;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Data
public class InsuranceCardRequest {
    @NotNull
    private String cardHolderId;

    @NotNull
    private Date expirationDate;

    @NotNull
    private String policyOwnerId;
}
