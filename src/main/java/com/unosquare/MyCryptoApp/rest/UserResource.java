package com.unosquare.mycryptoapp.rest;

import com.unosquare.mycryptoapp.domain.User;
import com.unosquare.mycryptoapp.rest.dtos.ChangePasswordDTO;
import com.unosquare.mycryptoapp.rest.dtos.ChangeStatusDTO;
import com.unosquare.mycryptoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/registration/")
public class UserResource {

    private final UserService userService;

    @GetMapping("getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody @Valid final String email) {
        if(StringUtils.isEmpty(email))
            throw new IllegalArgumentException(email);
        return userService.getUserByEmail(email).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElseThrow();
    }

    @PostMapping("saveUser")
    public ResponseEntity<User> saveUser(@RequestBody @Valid final User user) {
        return userService.saveUser(user).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElseThrow();
    }

    @PatchMapping("updatePasswordByEmail")
    public ResponseEntity<User> updatePasswordByEmail(@RequestBody @Valid final ChangePasswordDTO dto) {
        final Optional<User> userByEmail = userService.getUserByEmail(dto.getEmail());
        if(userByEmail.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return userService.updateUserPassword(userByEmail.get(), dto.getNewPassword()).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElseThrow();
    }

    @PatchMapping("updateStatus")
    public ResponseEntity<User> updateStatusByEmail(@RequestBody @Valid final ChangeStatusDTO dto) {
        final Optional<User> userByEmail = userService.getUserByEmail(dto.getEmail());
        if(userByEmail.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return userService.updateStatus(userByEmail.get(), dto.getNewStatus()).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElseThrow();
    }

    @DeleteMapping("deleteUserByEmail")
    public ResponseEntity<Void> deleteUserByEmail(@RequestBody @Valid final String email) {
        final Optional<User> userByEmail = userService.getUserByEmail(email);
        if(userByEmail.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        userService.deleteUser(userByEmail.get());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
