package com.insurance_management.portfolio_project.controller;

import com.insurance_management.portfolio_project.model.User;
import com.insurance_management.portfolio_project.repositories.jdbc.UserRepositories;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepositories userRepositories;

    @GetMapping("/user")
    public Iterable<User> fetchAllUser() {return userRepositories.findAll();}
}
