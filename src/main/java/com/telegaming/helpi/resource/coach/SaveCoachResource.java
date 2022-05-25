package com.telegaming.helpi.resource.coach;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class SaveCoachResource {

    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String email;
    @Size(max = 50)
    private String password;
    private String field;
    private LocalDate birthDate;

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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
