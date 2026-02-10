package com.example.Day3SMS.repository;

import com.example.Day3SMS.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel,String> {
    Optional<UserModel> findByEmail(String email);

}
