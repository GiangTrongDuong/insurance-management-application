package com.insurance_management.portfolio_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="claims")
@Entity
public class Claim {

    @Id
    @Column(name="claim_id")
    private String id;

    @Column(name="insured_person")
    private String insuredPerson;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="exam_date")
    private Date examDate;

    @Column(name="claim_date")
    private Date claimDate;

    @Column(name="claim_amount")
    private double claimAmount;

    @Column(name="status")
    private String status;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="bank_user_name")
    private String bankUserName;

    @Column(name="bank_number")
    private String bankNumber;
}