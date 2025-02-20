package com.insurance_management.portfolio_project.repositories;

import com.insurance_management.portfolio_project.model.InsuranceCard;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceCardRepositories extends CrudRepository<InsuranceCard, Integer> {
}
