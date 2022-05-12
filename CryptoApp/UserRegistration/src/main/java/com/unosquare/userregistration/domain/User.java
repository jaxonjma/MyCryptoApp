package com.unosquare.userregistration.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String zipCode;

    private LocalDate birthDate;

    private String country;

    private String email;

    private String password;

    private String status;
}
