package com.casotti.payapp.services;

import com.casotti.payapp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.Notification;
import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;;

    public boolean sendNotification(){
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", Map.class);

        if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            Boolean sucess = (Boolean) response.getBody().get("message");
            return sucess != null && sucess;
        } else {
            return false;
        }
    }



}
