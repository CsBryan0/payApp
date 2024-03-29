package com.casotti.payapp.services;

import com.casotti.payapp.dtos.TransactionDTO;
import com.casotti.payapp.entities.Transaction;
import com.casotti.payapp.entities.Users;
import com.casotti.payapp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws  Exception{
        Users payer = this.userService.getUserById(transactionDTO.payer());
        Users payee = this.userService.getUserById(transactionDTO.payee());

        userService.validateUser(payer, transactionDTO.amount());

        boolean isAuthorize = authorizedTransaction();

        if(!isAuthorize){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTimeStamp(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDTO.amount()));
        payee.setBalance(payee.getBalance().add(transactionDTO.amount()));

        if(!isSufficientBalance(payer, transactionDTO.amount())){
            throw new Exception("Saldo Insuficiente");
        }

       try {
           boolean notificationSent = notificationService.sendNotification();
           if (!notificationSent){
               System.out.println("Falha no envio de notificação");
           }
       } catch (Exception e){
           System.out.println("Erro ao enviar a notificação" + e.getMessage());
       }

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(payee);

        return newTransaction;
    }

    public List<Transaction> getAllTransactions(){
        return this.transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id)  throws Exception {
        if (id == null) {
            throw new Exception("O id não corresponde a nenhuma transação");
        }

        return this.transactionRepository.findById(id).orElseThrow( () -> new RuntimeException("Transação encontrada!"));
    }

    public boolean authorizedTransaction(){
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else {
            return false;
        }
    }

    public boolean isSufficientBalance(Users payer, BigDecimal amount){
        BigDecimal balanceAfterTransaction = payer.getBalance().subtract(amount);
        return balanceAfterTransaction.compareTo(BigDecimal.ZERO) >= 0;
    }

}
