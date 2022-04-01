package com.unosquare.mycryptoapp.service;

import com.unosquare.mycryptoapp.domain.User;
import com.unosquare.mycryptoapp.domain.enums.Status;
import com.unosquare.mycryptoapp.repository.UserRepository;
import com.unosquare.mycryptoapp.rest.dtos.ResponseDTO;
import com.unosquare.mycryptoapp.service.util.SecurityUtil;
import com.unosquare.mycryptoapp.service.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private static final String FAILED_TO_ADD_USER = "Failed to add user";

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(final String email) {
        if(StringUtils.isEmpty(email))
            throw new IllegalArgumentException(email);
        return userRepository.findItemByEmail(email);
    }

    @Transactional
    public ResponseEntity<ResponseDTO<User>> saveUser(final User user) throws InvalidKeySpecException, NoSuchAlgorithmException {

        ResponseDTO<User> validator = UserValidator.validateUser(user);
        if(!validator.getSuccess()){
            return new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,validator.getMessage()),HttpStatus.OK);
        }
        assignStrongPasswordHash(user,user.getPassword());
        user.setStatus(user.getStatus().toUpperCase());
        return Optional.ofNullable(userRepository.save(user)).map(u -> new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,"User added successfully",u), HttpStatus.OK))
                .orElse((new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,FAILED_TO_ADD_USER),HttpStatus.OK)));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<User>> updateUserPassword(final User user, final String newPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {
        assignStrongPasswordHash(user,newPassword);
        return Optional.ofNullable(userRepository.save(user)).map(u -> new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,"User added successfully",u), HttpStatus.OK))
                .orElse((new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,FAILED_TO_ADD_USER),HttpStatus.OK)));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<User>> updateStatus(final User user, final String status) {
        if(StringUtils.isEmpty(status) || !EnumUtils.isValidEnum(Status.class, status.toUpperCase())){
            return new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,"Invalid status"),HttpStatus.OK);
        }
        user.setStatus(status.toUpperCase());
        return Optional.ofNullable(userRepository.save(user))
                .map(u -> new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,"Status updated successfully",u),HttpStatus.OK))
                .orElse((new ResponseEntity<>(new ResponseDTO<>(Boolean.FALSE,"Failed to update status"),HttpStatus.OK)));
    }

    @Transactional
    public void deleteUser(final User user) {
        userRepository.delete(user);
    }

    private void assignStrongPasswordHash(User user,String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
            user.setPassword(SecurityUtil.generateStrongPasswordHash(password));
    }

}
