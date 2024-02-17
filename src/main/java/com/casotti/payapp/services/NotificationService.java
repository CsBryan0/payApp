package com.casotti.payapp.services;

import com.casotti.payapp.entities.Users;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void SendNotification(Users user, String message){
        String email = user.getEmail();
        System.out.println(email + message);
    }
}
