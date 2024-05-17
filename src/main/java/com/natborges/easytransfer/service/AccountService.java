package com.natborges.easytransfer.service;

import com.natborges.easytransfer.entity.AccountEntity;
import com.natborges.easytransfer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountEntity findById(Long id){
        Optional<AccountEntity> account = accountRepository.findById(id);

        return account.orElse(null);
    }

    public AccountEntity findByAccountNumber(String number){
        Optional<AccountEntity> account = accountRepository.findByAccountNumber(number);

        return account.orElse(null);
    }

    public AccountEntity findByUserCpf(String cpf){
        Optional<AccountEntity> account = accountRepository.findByCpf(cpf);

        return account.orElse(null);
    }

    public Page<AccountEntity> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Transactional
    public AccountEntity create(AccountEntity accountEntity){
        return accountRepository.save(accountEntity);
    }

    public boolean updateAccount(AccountEntity account) {
            Optional<AccountEntity> accountFromDbExists = accountRepository.findById(account.getId());

            if (accountFromDbExists.isPresent()) {
                AccountEntity accountFromDb = accountFromDbExists.get();
                accountFromDb.setBalance(account.getBalance());
                accountRepository.save(accountFromDb);
                return true;
            }

            return false;
    }

    public boolean transferAmountBetweenAccounts(AccountEntity senderAccount, AccountEntity recipientAccount, Double valueToTransfer) {
        Double senderAccountOldBalance = senderAccount.getBalance();
        Double senderAccountNewBalance = senderAccountOldBalance - valueToTransfer;
        Double recipientAccountOldBalance = recipientAccount.getBalance();
        Double recipientAccountNewBalance = recipientAccountOldBalance + valueToTransfer;
        if (senderAccountNewBalance > 0) {
            senderAccount.setBalance(senderAccountNewBalance);
            recipientAccount.setBalance(recipientAccountNewBalance);

            return this.updateAccount(senderAccount) && this.updateAccount(recipientAccount);
        }

        return false;
    }

    public boolean ammountDeposit(String accountNumber, Double valueToDeposit) {
        AccountEntity account = this.findByAccountNumber(accountNumber);

        if (account != null) {
            Double accountBalance = account.getBalance();
            Double accountNewBalance = accountBalance + valueToDeposit;
            account.setBalance(accountNewBalance);
            return this.updateAccount(account);
        }

        return false;
    }

}
