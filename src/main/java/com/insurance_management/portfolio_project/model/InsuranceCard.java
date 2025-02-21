package com.insurance_management.portfolio_project.model;

import com.insurance_management.portfolio_project.model.id.CardNumberId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@Data
@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="insurance_card")
public class InsuranceCard {

    @Id
    @CardNumberId
    @Column(name="card_number")
    private Long cardNumber;

    @Column(name="card_holder_id")
    private String cardHolderId;

    @Column(name="expiration_date")
    private Date expirationDate;

    @Column(name="policy_owner_id")
    private String policyOwnerId;

    public InsuranceCard(String cardHolderId, Date expirationDate, String policyOwnerId) {
        this.cardHolderId = cardHolderId;
        this.expirationDate = expirationDate;
        this.policyOwnerId = policyOwnerId;
    }
}