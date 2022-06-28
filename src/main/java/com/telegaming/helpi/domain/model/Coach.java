package com.telegaming.helpi.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;
    private String name;

    @NotNull
    private String email;
    
    @NotNull
    private String password;

    private String field;

    private LocalDate birthDate;

    private String coachProfilePicture;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "coach")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TrainingMaterial> createdTrainings = new HashSet<>();

    @Transient
    private Integer age;

    public Coach() {
    }


    public Coach(String name, String email, String password, String field, LocalDate birthDate, String coachProfilePicture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.field = field;
        this.birthDate = birthDate;
        this.coachProfilePicture = coachProfilePicture;
    }

    public Long getId() {
        return coachId;
    }

    public Coach setId(Long coachId) {
        this.coachId = coachId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Coach setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Coach setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Coach setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getField() {
        return field;
    }

    public Coach setField(String field) {
        this.field = field;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Coach setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public Coach setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getCoachProfilePicture() {
        return coachProfilePicture;
    }

    public void setCoachProfilePicture(String coachProfilePicture) {
        this.coachProfilePicture = coachProfilePicture;
    }

    public Set<TrainingMaterial> getCreatedTrainings() {
        return createdTrainings;
    }
}
