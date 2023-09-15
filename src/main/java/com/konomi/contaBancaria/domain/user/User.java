package com.konomi.contaBancaria.domain.user;

import com.konomi.contaBancaria.payload.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "tab_users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

//    public User(UserDto userDto) {
//        firstName = userDto.firstName();
//        lastName = userDto.lastName();
//        document = userDto.document();
//        email = userDto.email();
//        password = userDto.email();
//        balance = userDto.balance();
//        userType = userDto.userType();
//    }

}
