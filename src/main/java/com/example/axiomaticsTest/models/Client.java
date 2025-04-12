package com.example.axiomaticsTest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "ФИО обязательно")
    @Size(min = 2, max = 100, message = "ФИО должно быть от 2 до 100 символов")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Паспорт обязателен")
    @Pattern(regexp = "\\d{10}", message = "Паспорт должен содержать 10 цифр")
    private String passport;

    @Pattern(regexp = "Мужской|Женский", message = "Пол должен быть 'Мужской' или 'Женский'")
    private String gender;

    @Size(max = 50, message = "Семейное положение не должно превышать 50 символов")
    @Pattern(regexp = "Холост|Не замужем|Женат|Гражданский брак|Замужем|Разведен|Разведена|Вдовец|Вдова", message = "Введите семейное положение")
    @Column(name = "marital_status")
    private String maritalStatus;

    @Size(max = 255, message = "Адрес не должен превышать 255 символов")
    @Column(name = "residence_address")
    private String residenceAddress;

    @Size(max = 255, message = "Адрес не должен превышать 255 символов")
    @Column(name = "registration_address")
    private String registrationAddress;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "\\+7\\d{10}", message = "Телефон должен начинаться с '+7' и содержать  10 цифр")
    private String phone;

    @Min(value = 0, message = "Стаж не может быть отрицательным")
    @Column(name = "employment_period")
    private Integer employmentPeriod;

    @Size(max = 100, message = "Должность не должна превышать 100 символов")
    private String position;

    @Size(max = 100, message = "Название организации не должно превышать 100 символов")
    @Column(name = "organization_name")
    private String organizationName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<LoanApplication> applications = new ArrayList<>();
}