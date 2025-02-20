package com.insurance_management.portfolio_project.repositories;

import com.insurance_management.portfolio_project.model.Claim;
import org.springframework.data.repository.CrudRepository;

public interface ClaimRepositories extends CrudRepository<Claim, Integer> {
}
