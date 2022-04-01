package com.unosquare.mycryptoapp.repository;

import com.unosquare.mycryptoapp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findOneByEmail(final String email);
}
