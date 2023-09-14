package com.konomi.contaBancaria.services;

import com.konomi.contaBancaria.domain.transaction.Transaction;
import com.konomi.contaBancaria.domain.user.User;
import com.konomi.contaBancaria.payload.TransactionDto;
import com.konomi.contaBancaria.repositories.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private final UsersService usersService;
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final RestTemplate restTemplate;

    public TransactionService(UsersService usersService, TransactionRepository transactionRepository, RestTemplate restTemplate) {
        this.usersService = usersService;
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    public void createTransaction(TransactionDto transactionDto) throws Exception {
        User sender = usersService.findUserById(transactionDto.senderId());
        User receiver = usersService.findUserById(transactionDto.receiverId());

        usersService.validateTransaction(sender, transactionDto.value());

        boolean isAuthorized = authorizeTransaction(sender, transactionDto.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        transactionRepository.save(transaction);
        usersService.saveUser(sender);
        usersService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
        if (authorizeResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizeResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;

    }
}
