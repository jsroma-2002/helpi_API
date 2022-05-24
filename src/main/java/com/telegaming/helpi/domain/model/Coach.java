package com.telegaming.helpi.domain.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @Transient
    private Integer age;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "game_id", referencedColumnName = "gameId")
    private Game game;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "coach")
    private Set<TrainingMaterial> trainingMaterials = new HashSet<>();

    public Coach() {
    }

    public Coach(String name, String email, String password, String field, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.field = field;
        this.birthDate = birthDate;
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
        return age;
    }

    public Coach setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<TrainingMaterial> getTrainingMaterials() {
        return trainingMaterials;
    }
}
