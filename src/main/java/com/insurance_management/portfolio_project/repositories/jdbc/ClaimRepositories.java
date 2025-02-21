package com.insurance_management.portfolio_project.repositories.jdbc;

import com.insurance_management.portfolio_project.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepositories extends JpaRepository<Claim, Integer> {
}
