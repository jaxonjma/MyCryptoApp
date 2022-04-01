package com.unosquare.mycryptoapp.rest.dtos;

import com.unosquare.mycryptoapp.domain.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ChangeStatusDTO implements Serializable {

    @Email
    private String email;

    private String newStatus;
}
