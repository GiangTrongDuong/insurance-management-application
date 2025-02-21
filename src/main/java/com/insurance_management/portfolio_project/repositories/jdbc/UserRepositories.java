package com.insurance_management.portfolio_project.repositories.jdbc;

import com.insurance_management.portfolio_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, Integer> {
}
