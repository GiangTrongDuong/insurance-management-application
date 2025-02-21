package com.insurance_management.portfolio_project.controller;

import com.insurance_management.portfolio_project.model.Claim;
import com.insurance_management.portfolio_project.repositories.jdbc.ClaimRepositories;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClaimController {

    private final ClaimRepositories claimRepositories;

    @GetMapping("/claim/all")
    public Iterable<Claim> fetchAllClaim() {return claimRepositories.findAll();}
}
