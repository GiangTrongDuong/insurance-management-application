package com.insurance_management.portfolio_project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table("users")
    public class User {

        @Id
        @Generated
        @Column("id")
        private String id;

        @Column("full_name")
        private String fullName;

        @Column("user_name")
        private String userName;

        @Column("password")
        private String password;

        @Column("email")
        private String email;

        @Column("phone_number")
        private String phoneNumber;

        @Column("address")
        private String address;

        @Column("role_id")
        private int roleId;
}
