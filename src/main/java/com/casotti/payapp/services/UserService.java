package com.casotti.payapp.services;

import com.casotti.payapp.dtos.UserDTO;
import com.casotti.payapp.entities.UserType;
import com.casotti.payapp.entities.Users;
import com.casotti.payapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveUser(Users users){
        this.userRepository.save(users);
    }

    public Users createUsers(UserDTO userDTO){
        Users newUser = new Users(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public List<Users> allUsers(){
        return this.userRepository.findAll();
    }

    public Users getUserById(Long id) throws Exception{
        if (id == null){
            throw  new IllegalArgumentException("ID do usuário não encontrado");
        }

        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void validateUser(Users payer, BigDecimal amount) throws Exception{
        if(payer.getUserType() == UserType.MERCHANT){
            throw new Exception("Lojistas não podem realizar transações");
        }

    }
}
