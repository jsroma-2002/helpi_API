package com.telegaming.helpi.resource.player;

import javax.validation.constraints.Size;

public class SaveLoginResource {

    @Size(max = 50)
    private String email;
    @Size(max = 50)
    private String password;

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
}
