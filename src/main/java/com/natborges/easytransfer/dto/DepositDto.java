package com.natborges.easytransfer.dto;

import javax.validation.constraints.NotBlank;

public class DepositDto {
    @NotBlank
    private String accountNumber;

    @NotBlank
    private Double value;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}