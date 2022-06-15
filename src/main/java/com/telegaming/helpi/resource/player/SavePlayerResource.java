package com.telegaming.helpi.resource.player;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class SavePlayerResource {

    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String email;
    @Size(max = 50)
    private String password;
    private LocalDate birthDate;
    private Double balance;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
