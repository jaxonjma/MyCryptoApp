package com.unosquare.mycryptoapp.domain;

import com.unosquare.mycryptoapp.domain.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String zipCode;

    private LocalDate birthDate;

    private String country;

    @Email
    //@Column(unique = true)
    private String email;

    private String password;

    //@Enumerated(value = EnumType.STRING)
    private Status status;
}
