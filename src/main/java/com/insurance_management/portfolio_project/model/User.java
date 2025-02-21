package com.insurance_management.portfolio_project.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @Generated
    @Column(name="id")
    private String id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="role_id")
    private int roleId;
}
