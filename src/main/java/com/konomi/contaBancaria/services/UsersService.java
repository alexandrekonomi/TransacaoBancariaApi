package com.konomi.contaBancaria.services;

import com.konomi.contaBancaria.domain.user.User;
import com.konomi.contaBancaria.domain.user.UserType;
import com.konomi.contaBancaria.payload.UserDto;
import com.konomi.contaBancaria.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a fazer uma transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Slado insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDto userDto) {
        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList;

    }
}
