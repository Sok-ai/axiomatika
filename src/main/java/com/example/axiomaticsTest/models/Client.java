package com.example.axiomaticsTest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "full_name")
    private String fullName;

    @NotBlank
    @Column(unique = true)
    private String passport;

    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "residence_address")
    private String residenceAddress;

    @Column(name = "registration_address")
    private String registrationAddress;

    @NotBlank
    private String phone;

    @Column(name = "employment_period")
    private String employmentPeriod;

    private String position;

    @Column(name = "organization_name")
    private String organizationName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<LoanApplication> applications = new ArrayList<>();
}