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

    public void SendNotification(Users user, String message){

        boolean sendNotification = verifyNotification();

        if(!sendNotification){
            throw new RuntimeException("Serviço de mensagens fora do ar");
        }

        String email = user.getEmail();
        System.out.println(email + " " + message);
    }

    public boolean verifyNotification(){

        var notification = restTemplate.getForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", Map.class);

        if(notification.getStatusCode() == HttpStatus.OK){
            String message = (String) notification.getBody().get("message");
            return true;
        } else {
            return false;
        }
    }
}
