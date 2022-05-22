package com.telegaming.helpi.resource.coach;

import java.time.LocalDate;

public class CoachResource {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String field;
    private LocalDate birthDate;
    private Integer age;

    public Long getId() {
        return id;
    }

    public CoachResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoachResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CoachResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CoachResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getField() {
        return field;
    }

    public CoachResource setField(String field) {
        this.field = field;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CoachResource setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CoachResource setAge(Integer age) {
        this.age = age;
        return this;
    }
}
