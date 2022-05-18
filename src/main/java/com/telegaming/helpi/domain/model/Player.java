package com.telegaming.helpi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private LocalDate birthDate;
    @Transient
    private Integer age;

    public Player() {
    }

    public Player(String name, String email, String password, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {return Period.between(this.birthDate, LocalDate.now()).getYears(); }

    public void setAge(Integer age) {
        this.age = age;
    }
}
