package com.casotti.payapp.dtos;

import com.casotti.payapp.entities.Users;

import java.math.BigDecimal;

public record TransactionDTO(
        Long payer,
        Long payee,
        BigDecimal  amount
        ) {
}
