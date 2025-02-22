package com.insurance_management.portfolio_project.service;

import com.insurance_management.portfolio_project.data_transfer_object.InsuranceCardDTO;
import com.insurance_management.portfolio_project.model.InsuranceCard;

import java.sql.Date;
import java.util.List;

public interface InsuranceCardService {
    // POST
    InsuranceCard saveInsuranceCard(InsuranceCardDTO insuranceCard);
    // GET
    List<InsuranceCard> fetchAllInsuranceCardList();
    // GET (by card number)
    InsuranceCard fetchByCardNumber(Long cardNumber);
    // GET (by cardholder id)
    List<InsuranceCard> fetchByCardholderId(String cardHolderId);
    // GET (by expiration date)
    List<InsuranceCard> fetchByExpirationDate(Date expirationDate);
    // GET (by policyholder id)
    List<InsuranceCard> fetchByPolicyHolderId(String policyHolderId);
    // Update
    InsuranceCard updateInsuranceCard (InsuranceCard insuranceCard, Long cardNumber);
    //Delete
    InsuranceCard deleteInsuranceCard (Long cardNumber);
}
