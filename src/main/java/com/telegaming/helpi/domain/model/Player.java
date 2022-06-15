package com.telegaming.helpi.domain.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

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
    private Double balance;
    @Transient
    private Integer age;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "ownerPlayers")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TrainingMaterial> ownedTrainingMaterials = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "memberPlayers")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Community> belongCommunities = new HashSet<>();

    public Player() {
    }

    public Player(String name, String email, String password, LocalDate birthDate, Double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.balance = balance;
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

    public Set<TrainingMaterial> getOwnedTrainingMaterials() {
        return ownedTrainingMaterials;
    }

    public void purchaseTraining(TrainingMaterial trainingMaterial){
        ownedTrainingMaterials.add(trainingMaterial);
    }

    public Set<Community> getBelongCommunities() {
        return belongCommunities;
    }

    public void joinCommunity(Community community){
        belongCommunities.add(community);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
