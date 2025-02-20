package com.insurance_management.portfolio_project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table("claims")
public class Claim {

    @Generated
    @Id
    @Column("claim_id")
    private String id;

    @Column("insured_person")
    private String insuredPerson;

    @Column("card_number")
    private String cardNumber;

    @Column("exam_date")
    private Date examDate;

    @Column("claim_date")
    private Date claimDate;

    @Column("claim_amount")
    private double claimAmount;

    @Column("status")
    private String status;

    @Column("bank_name")
    private String bankName;

    @Column("bank_user_name")
    private String bankUserName;

    @Column("bank_number")
    private String bankNumber;
}