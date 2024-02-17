package com.casotti.payapp.repositories;


import com.casotti.payapp.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
}
