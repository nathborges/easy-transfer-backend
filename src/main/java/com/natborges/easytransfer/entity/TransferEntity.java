package com.natborges.easytransfer.entity;

import com.natborges.easytransfer.constants.TransferStatusEnum;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_transfer")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", referencedColumnName = "id")
    private AccountEntity sender;

    @ManyToOne
    @JoinColumn(name = "recipient_account_id", referencedColumnName = "id")
    private AccountEntity recipient;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "scheduled_at")
    private LocalDate scheduledAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransferStatusEnum status;

    @Column(name = "tax")
    private Double tax;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getSender() {
        return sender;
    }

    public void setSender(AccountEntity sender) {
        this.sender = sender;
    }

    public AccountEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(AccountEntity recipient) {
        this.recipient = recipient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDate scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransferStatusEnum status) {
        this.status = status;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}