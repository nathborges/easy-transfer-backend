package com.natborges.easytransfer.dto;

import javax.validation.constraints.NotBlank;

public class CreateAccountDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String cpf;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
