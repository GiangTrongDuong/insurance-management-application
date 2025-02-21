package com.insurance_management.portfolio_project.repositories.jdbc;

import com.insurance_management.portfolio_project.model.InsuranceCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InsuranceCardRepository extends JpaRepository<InsuranceCard, Integer> {
    Optional<InsuranceCard> findByCardNumber(Long cardNumber);

    List<InsuranceCard> findByCardHolderId(String cardHolderId);

    List<InsuranceCard> findByExpirationDate(Date expirationDate);

    List<InsuranceCard> findByPolicyOwnerId(String policyOwnerId);
}
