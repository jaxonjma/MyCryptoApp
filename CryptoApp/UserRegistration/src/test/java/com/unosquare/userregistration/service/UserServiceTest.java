package com.unosquare.userregistration.service;

import com.unosquare.userregistration.domain.User;
import com.unosquare.userregistration.repository.UserRepository;
import com.unosquare.userregistration.rest.dtos.ResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String TEST_EMAIL = "myEmail@dominio.com";
    private static final String EMPTY_EMAIL_EXCEPTION = "Email is required";
    private static final String EMPTY_STRING = "";

    @InjectMocks
    UserService userService = new UserService();

    @Mock
    UserRepository userRepository;

    @DisplayName("Find Item - Successfully test")
    @Test
    void testFindItemSuccessTest() {
        Optional<User> optionalUser = Optional.of(new User());
        Mockito.when(userRepository.findItemByEmail(anyString())).thenReturn(optionalUser);
        assertEquals(userService.getUserByEmail(TEST_EMAIL),optionalUser);
    }

    @DisplayName("Find Item - Controlled exception")
    @Test
    void testFindItemFailureTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->userService.getUserByEmail(EMPTY_STRING),
                EMPTY_EMAIL_EXCEPTION
        );
        assertTrue(thrown.getMessage().contains(EMPTY_EMAIL_EXCEPTION));
    }

    @DisplayName("Save User - Validation success and persist")
    @Test
    void testSaveUserSuccessTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Mockito.when(userRepository.save(any(User.class))).thenReturn(getCompleteUser());
        ResponseEntity<ResponseDTO<User>> response = userService.saveUser(getCompleteUser());
        Assertions.assertAll(
                () -> assertTrue(response.getStatusCode().equals(HttpStatus.OK)),
                () -> assertTrue(response.getBody().getSuccess().equals(Boolean.TRUE)),
                () -> assertTrue(response.getBody().getMessage().equals("User added successfully"))
        );
    }

    @DisplayName("Save User - Validation success and error in persist")
    @Test
    void testSaveUserFailTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Mockito.when(userRepository.save(any(User.class))).thenReturn(null);
        ResponseEntity<ResponseDTO<User>> response = userService.saveUser(getCompleteUser());
        Assertions.assertAll(
                () -> assertTrue(response.getStatusCode().equals(HttpStatus.OK)),
                () -> assertTrue(response.getBody().getSuccess().equals(Boolean.FALSE)),
                () -> assertTrue(response.getBody().getMessage().equals("Failed to add user"))
        );
    }

    @DisplayName("Save User - Validation fail by combinations")
    @ParameterizedTest
    @MethodSource("provideBadUsersForTesting")
    void testSaveUserFailureTest(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ResponseEntity<ResponseDTO<User>> response = userService.saveUser(new User());
        Assertions.assertAll(
                () -> assertTrue(response.getStatusCode().equals(HttpStatus.OK)),
                () -> assertTrue(response.getBody().getSuccess().equals(Boolean.FALSE))
        );
    }

    private static Stream<User> provideBadUsersForTesting(){
        return Stream.of(
                new User(),
                new User(null,null,null,null,null,null,null,null,null,null,null),
                new User(null,"Jaxon",null,null,null,null,null,null,null,null,null),
                new User(null,"Jaxon","Muñoz",null,null,null,null,null,null,null,null),
                new User(null,"Jaxon","Muñoz","Cll 1 #1-1",null,null,null,null,null,null,null),
                new User(null,"Jaxon","Muñoz","Cll 1 #1-1","3222222222",null,null,null,null,null,null),
                new User(null,"Jaxon","Muñoz","Cll 1 #1-1","3222222222","507051",null,null,"jaxon@test.com",null,null),
                new User(null,"Jaxon","Muñoz","Cll 1 #1-1","3222222222","507051",null,null,"jaxon@test.com","myStrongPass",null),
                new User(null,"Jaxon","Muñoz","Cll 1 #1-1","3222222222","507051",null,null,"jaxon@test.com","myStrongPass","CONFIRM")
        );
    }

    private User getCompleteUser(){
        return new User(null,"Jaxon","Muñoz","Cll 1 #1-1","3222222222","507051", LocalDate.of(2020, 4, 5),"Colombia","jaxon@test.com","myStrongPass","CONFIRMED");
    }

    //SecurityUtil.generateStrongPasswordHash(password)
    @DisplayName("Update User - Correct assign of password and persist")
    @Test
    void testUpdateUserPasswordTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Mockito.when(userRepository.save(any(User.class))).thenReturn(getCompleteUser());
        ResponseEntity<ResponseDTO<User>> response = userService.updateUserPassword(getCompleteUser(),"myNewStrongPassword");
        Assertions.assertAll(
                () -> assertTrue(response.getStatusCode().equals(HttpStatus.OK)),
                () -> assertTrue(response.getBody().getSuccess().equals(Boolean.TRUE)),
                () -> assertTrue(response.getBody().getMessage().equals("User added successfully"))
        );
    }

    @DisplayName("Update User - Correct assign of password and error in persist")
    @Test
    void testErrorUpdateUserPasswordTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Mockito.when(userRepository.save(any(User.class))).thenReturn(null);
        ResponseEntity<ResponseDTO<User>> response = userService.updateUserPassword(getCompleteUser(),"myNewStrongPassword");
        Assertions.assertAll(
                () -> assertTrue(response.getStatusCode().equals(HttpStatus.OK)),
                () -> assertTrue(response.getBody().getSuccess().equals(Boolean.FALSE)),
                () -> assertTrue(response.getBody().getMessage().equals("Failed to add user"))
        );
    }
}
