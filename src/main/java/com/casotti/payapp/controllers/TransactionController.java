package com.casotti.payapp.controllers;

import com.casotti.payapp.dtos.TransactionDTO;
import com.casotti.payapp.entities.Transaction;
import com.casotti.payapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception{
        Transaction newTransaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        var transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable (value = "id") Long id) throws Exception {
            var transactions = this.transactionService.getTransactionById(id);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
