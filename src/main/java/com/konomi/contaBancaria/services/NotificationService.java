package com.konomi.contaBancaria.services;

import com.konomi.contaBancaria.domain.user.User;
import com.konomi.contaBancaria.payload.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDto notificationRequest = new NotificationDto(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            log.error("Serviço de notificação está fora do ar");
            throw new Exception("Serviço de notificação está fora do ar");
        }
    }
}
