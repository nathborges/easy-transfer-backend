package com.natborges.easytransfer.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CreateTransferDto {

    @NotBlank
    private String senderAccountNumber;

    @NotBlank
    private String recipientAccountNumber;

    @NotBlank
    private Double amount;

    @NotBlank
    private String scheduledDate;

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
