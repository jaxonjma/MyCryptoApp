package com.unosquare.mycryptoapp.service;

import com.unosquare.mycryptoapp.domain.User;
import com.unosquare.mycryptoapp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService = new UserService();

    @Mock
    UserRepository userRepository;

    @DisplayName("Successfully save test")
    @Test
    void testSingleSuccessTest() {
        Optional<User> optionalUser = Optional.of(new User());
        Mockito.when(userRepository.findItemByEmail(anyString())).thenReturn(optionalUser);
        assertEquals(userService.getUserByEmail("myEmail@dominio.com"),optionalUser);
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }
}
