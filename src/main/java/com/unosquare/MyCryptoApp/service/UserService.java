package com.unosquare.mycryptoapp.service;

import com.unosquare.mycryptoapp.domain.User;
import com.unosquare.mycryptoapp.domain.enums.Status;
import com.unosquare.mycryptoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserByEmail(final String email) {
        if(StringUtils.isEmpty(email))
            throw new IllegalArgumentException(email);
        return userRepository.findOneByEmail(email);
    }

    @Transactional
    public Optional<User> saveUser(final User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Transactional
    public Optional<User> updateUserPassword(final User user, final String newPassword) {
        user.setPassword(newPassword);
        return Optional.ofNullable(userRepository.save(user));
    }

    @Transactional
    public Optional<User> updateStatus(final User user, final Status status) {
        user.setStatus(status);
        return Optional.ofNullable(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(final User user) {
        userRepository.delete(user);
    }
}
