package com.natborges.easytransfer.service;

import com.natborges.easytransfer.dto.CreateTransferDto;
import com.natborges.easytransfer.entity.AccountEntity;
import com.natborges.easytransfer.entity.TransferEntity;
import com.natborges.easytransfer.repository.TransferRepository;
import com.natborges.easytransfer.utils.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class TransferService {

    @Autowired
    AccountService accountService;

    @Autowired
    TransferRepository transferRepository;

    public Page<TransferEntity> findAll(Pageable pageable) {
        return transferRepository.findAll(pageable);
    }

    @Transactional
    public TransferEntity create(CreateTransferDto dto){
        TransferEntity transfer = new TransferEntity();

        AccountEntity accountRecipient = accountService.findByAccountNumber(dto.getRecipientAccountNumber());
        AccountEntity accountSender = accountService.findByAccountNumber(dto.getSenderAccountNumber());

        if (accountSender.equals(null) || accountRecipient.equals(null)) {
            return null;
        }

        transfer.setRecipient(accountRecipient);
        transfer.setSender(accountSender);

        LocalDate scheduledTime = LocalDate.parse(dto.getScheduledDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (scheduledTime.isBefore(LocalDate.now())) {
            return null;
        }

        transfer.setScheduledAt(scheduledTime);

        long daysToTransfer = ChronoUnit.DAYS.between(LocalDate.now(), scheduledTime);
        Double totalTaxes = TaxCalculator.calculate(daysToTransfer, dto.getAmount());

        transfer.setTax(totalTaxes);
        transfer.setAmount(dto.getAmount() + totalTaxes);

        if (transfer.getAmount() > accountSender.getBalance()) {
            return null;
        }

        Boolean successToTransferValues = accountService.transferAmountBetweenAccounts(accountSender, accountRecipient, transfer.getAmount());

        if (successToTransferValues.equals(false)) {
            return null;
        }

        return transferRepository.save(transfer);
    }
}
