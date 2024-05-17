package com.natborges.easytransfer.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.Date;

import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "user_first_name", nullable = false)
    private String firstName;

    @Column(name = "user_cpf", unique = true, nullable = false)
    @Size(min = 11, max = 11)
    private String cpf;

    @Column(name = "account_number", unique = true, nullable = false)
    @Size(min = 7, max = 7)
    private String accountNumber;

    @Column(name = "balance", columnDefinition = "BIGINT DEFAULT 0")
    private Double balance = 0.0;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        int number = new SecureRandom().nextInt(9000000) + 1000000;
        this.accountNumber = String.valueOf(number);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}