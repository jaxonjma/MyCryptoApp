package com.unosquare.mycryptoapp.service;

import com.unosquare.mycryptoapp.domain.User;
import com.unosquare.mycryptoapp.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
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

    private static final String TEST_EMAIL = "myEmail@dominio.com";
    private static final String EMPTY_EMAIL_EXCEPTION = "Email is required";
    private static final String EMPTY_STRING = "";

    @InjectMocks
    UserService userService = new UserService();

    @Mock
    UserRepository userRepository;

    @DisplayName("Successfully save test")
    @Test
    void testFindItemSuccessTest() {
        Optional<User> optionalUser = Optional.of(new User());
        Mockito.when(userRepository.findItemByEmail(anyString())).thenReturn(optionalUser);
        assertEquals(userService.getUserByEmail(TEST_EMAIL),optionalUser);
    }

    @DisplayName("Controlled exception")
    @Test
    void testFindItemFailureTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->userService.getUserByEmail(EMPTY_STRING),
                EMPTY_EMAIL_EXCEPTION
        );
        assertTrue(thrown.getMessage().contains(EMPTY_EMAIL_EXCEPTION));
    }
}
