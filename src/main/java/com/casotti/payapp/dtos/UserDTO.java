package com.casotti.payapp.dtos;

import com.casotti.payapp.entities.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType
) {
}
