package com.epizy.krishjay.repository;

import com.epizy.krishjay.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users,Long> {

}
