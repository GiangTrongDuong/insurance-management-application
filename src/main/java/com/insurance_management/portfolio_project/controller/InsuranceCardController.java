package com.insurance_management.portfolio_project.controller;

import com.insurance_management.portfolio_project.model.InsuranceCard;
import com.insurance_management.portfolio_project.repositories.InsuranceCardRepositories;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InsuranceCardController {

    private final InsuranceCardRepositories insuranceCardRepositories;

    @GetMapping("/insurance-card/all")
    public Iterable<InsuranceCard> fetchAllInsuranceCard() {return insuranceCardRepositories.findAll();}
}
