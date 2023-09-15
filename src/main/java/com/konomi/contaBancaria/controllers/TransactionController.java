package com.konomi.contaBancaria.controllers;

import com.konomi.contaBancaria.domain.transaction.Transaction;
import com.konomi.contaBancaria.payload.TransactionDto;
import com.konomi.contaBancaria.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
    Transaction newTransaction = transactionService.createTransaction(transactionDto);
    return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

}
