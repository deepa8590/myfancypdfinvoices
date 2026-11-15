package com.marcobehler.myfancypdfinvoices.web.Forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//here loginform is a backing bean , controller adds a LoginForm instance to the model, and Spring binds submitted values to it and applies its validation annotations
public class LoginForm {

    @NotBlank 
    @Size (min = 5, max = 20)
    private String username, password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}