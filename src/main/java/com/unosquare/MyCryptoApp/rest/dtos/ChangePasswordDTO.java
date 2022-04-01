package com.unosquare.mycryptoapp.rest.dtos;

import lombok.*;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO implements Serializable {

    @Email
    private String email;

    private String newPassword;
}
