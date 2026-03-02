package com.zumr.checkacess_api.repository;

import com.zumr.checkacess_api.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
