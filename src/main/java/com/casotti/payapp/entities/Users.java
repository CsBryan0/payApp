package com.casotti.payapp.entities;

import com.casotti.payapp.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    private BigDecimal balance;
    private UserType userType;

    public Users(UserDTO userDTO) {
        this.name = userDTO.name();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.balance = userDTO.balance();
        this.userType = userDTO.userType();
    }
}
