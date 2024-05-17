package com.natborges.easytransfer.dto;

import javax.validation.constraints.NotBlank;

public class CreateUserDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}