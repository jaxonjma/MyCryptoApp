package com.unosquare.mycryptoapp.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;

@Getter @Setter
@AllArgsConstructor
public class ResponseDTO<T> extends HttpEntity<T> {

    private Boolean success;

    private String message;

    private T data;

    public ResponseDTO() {
        this.success = Boolean.TRUE;
    }

    public ResponseDTO(Boolean success, T data) {
        this(success, null, data);
    }

    public ResponseDTO(Boolean success, String message) {
        this(success, message, null);
    }

}
