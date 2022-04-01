package com.unosquare.mycryptoapp.rest;

import com.unosquare.mycryptoapp.domain.User;
import com.unosquare.mycryptoapp.rest.dtos.ChangePasswordDTO;
import com.unosquare.mycryptoapp.rest.dtos.ChangeStatusDTO;
import com.unosquare.mycryptoapp.rest.dtos.ResponseDTO;
import com.unosquare.mycryptoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/registration/")
public class UserResource {

    private static Logger logger = Logger.getLogger(UserResource.class.getName());
    private static final String USER_NOT_FOUND = "User not found";
    private static final String FAILED_TO_ADD_USER = "Failed to add user";

    @Autowired
    private UserService userService;

    @GetMapping("getUserByEmail/{email}")
    public ResponseEntity<ResponseDTO<User>> getUserByEmail(@PathVariable("email") @Valid final String email) {
        if(StringUtils.isEmpty(email)) {
            new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,"Email required"),HttpStatus.OK);
        }
        return userService.getUserByEmail(email)
                .map(u -> new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,u),HttpStatus.OK))
                .orElse((new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,"No user found"),HttpStatus.OK)));
    }

    @PostMapping("saveUser")
    public ResponseEntity<ResponseDTO<User>> saveUser(@RequestBody @Valid final User user) {
        try {
            return userService.saveUser(user);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE,e.getCause().toString());
            logger.log(Level.SEVERE,e.getMessage());
            return new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,FAILED_TO_ADD_USER),HttpStatus.OK);
        }
    }

    @PatchMapping("updatePasswordByEmail")
    public ResponseEntity<ResponseDTO<User>> updatePasswordByEmail(@RequestBody @Valid final ChangePasswordDTO dto) {
        final Optional<User> userByEmail = userService.getUserByEmail(dto.getEmail());
        if(userByEmail.isEmpty())
            return new ResponseEntity<>((new ResponseDTO<>(Boolean.FALSE,USER_NOT_FOUND)),HttpStatus.OK);
        try {
            return userService.updateUserPassword(userByEmail.get(), dto.getNewPassword());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE,e.getCause().toString());
            logger.log(Level.SEVERE,e.getMessage());
            return new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,FAILED_TO_ADD_USER),HttpStatus.OK);
        }
    }

    @PatchMapping("updateStatus")
    public ResponseEntity<ResponseDTO<User>> updateStatusByEmail(@RequestBody @Valid final ChangeStatusDTO dto) {
        final Optional<User> userByEmail = userService.getUserByEmail(dto.getEmail());
        if(userByEmail.isEmpty())
            return new ResponseEntity<>((new ResponseDTO<>(Boolean.FALSE,USER_NOT_FOUND)),HttpStatus.OK);
        return userService.updateStatus(userByEmail.get(), dto.getNewStatus());
    }

    @DeleteMapping("deleteUserByEmail/{email}")
    public ResponseEntity<ResponseDTO<Void>> deleteUserByEmail(@PathVariable("email") @Valid final String email) {
        final Optional<User> userByEmail = userService.getUserByEmail(email);
        if(userByEmail.isEmpty())
            return new ResponseEntity<>((new ResponseDTO<>(Boolean.FALSE,USER_NOT_FOUND)),HttpStatus.OK);
        userService.deleteUser(userByEmail.get());
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,"User deleted successfully",null),HttpStatus.OK);
    }
}
