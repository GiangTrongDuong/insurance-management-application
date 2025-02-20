package com.insurance_management.portfolio_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("insurance_card")
public class InsuranceCard {

    @Column("card_number")
    private String cardNumber;

    @Column("card_holder_id")
    private String cardHolderId;

    @Column("expiration_date")
    private Date expirationDate;

    @Column("policy_holder_id")
    private String policyOwnerId;
}