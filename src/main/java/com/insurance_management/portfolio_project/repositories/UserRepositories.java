package com.insurance_management.portfolio_project.repositories;

import com.insurance_management.portfolio_project.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositories extends CrudRepository<User, Integer> {
}
