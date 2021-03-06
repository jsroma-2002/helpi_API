package com.telegaming.helpi.resource.player;

import java.time.LocalDate;
import java.time.Period;

public class PlayerResource {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Float balance;
    private String playerProfilePicture;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {return Period.between(this.birthDate, LocalDate.now()).getYears(); }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getPlayerProfilePicture() {
        return playerProfilePicture;
    }

    public void setPlayerProfilePicture(String playerProfilePicture) {
        this.playerProfilePicture = playerProfilePicture;
    }
}
