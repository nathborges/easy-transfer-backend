package com.natborges.easytransfer.controller;

import com.natborges.easytransfer.dto.CreateAccountDto;
import com.natborges.easytransfer.dto.DepositDto;
import com.natborges.easytransfer.entity.AccountEntity;
import com.natborges.easytransfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
// @TODO: put frontend default url
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<Page<AccountEntity>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<AccountEntity> getByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(service.findByUserCpf(cpf));
    }

    @PostMapping()
    public ResponseEntity<AccountEntity> create(@RequestBody CreateAccountDto createAccountDto){
        AccountEntity account = new AccountEntity();
        account.setCpf(createAccountDto.getCpf());
        account.setFirstName(createAccountDto.getFirstName());
        return ResponseEntity.ok(service.create(account));
    }

    @PutMapping(value = "/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositDto depositDto){
        boolean success = service.ammountDeposit(depositDto.getAccountNumber(), depositDto.getValue());
        if (success) {
            return ResponseEntity.ok("Deposit done with success.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deposit error. Please, try again.");
    }
}