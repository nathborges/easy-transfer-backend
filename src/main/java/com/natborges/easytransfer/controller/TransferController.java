package com.natborges.easytransfer.controller;

import com.natborges.easytransfer.dto.CreateTransferDto;
import com.natborges.easytransfer.entity.AccountEntity;
import com.natborges.easytransfer.entity.TransferEntity;
import com.natborges.easytransfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transfer")
public class TransferController {

    @Autowired
    private TransferService service;

    @GetMapping
    public ResponseEntity<Page<TransferEntity>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody CreateTransferDto transferDto){
        TransferEntity transferCreated = service.create(transferDto);

        if (transferCreated != null ) {
            return ResponseEntity.status(HttpStatus.OK).body(transferCreated.toString());
        }
        return ResponseEntity.internalServerError().body("Error to created, check the recipient and sender data.");
    }
}