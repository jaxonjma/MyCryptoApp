package com.unosquare.mycryptoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.unosquare.mycryptoapp.domain.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String zipCode;

    private LocalDate birthDate;

    private String country;

    @Email
    private String email;

    private String password;

    private String status;
}
