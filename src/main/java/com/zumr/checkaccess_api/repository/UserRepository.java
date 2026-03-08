package com.zumr.checkaccess_api.repository;

import com.zumr.checkaccess_api.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
