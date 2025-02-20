package com.insurance_management.portfolio_project;

import com.insurance_management.portfolio_project.utils.DatabaseConnection;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioProjectApplication {
	private final DatabaseConnection databaseConnection;

	public PortfolioProjectApplication(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public static void main(String[] args) {
		SpringApplication.run(PortfolioProjectApplication.class, args);
	}

	@PostConstruct
	public void init() {
		databaseConnection.testConnection(); // ✅ Will print success/failure
	}
}
