package com.example.axiomaticsTest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String full_name;
    private String passport;
    private String gender;
    private String marital_status;
    private String address_residence;
    private String address_registration;
    private String phone;
    private Integer employment_period;
    private String job_title;
    private String organization_name;
}
