package com.unosquare.mycryptoapp.rest.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ChangePasswordDTO implements Serializable {

    @Email
    private String email;

    private String newPassword;
}
