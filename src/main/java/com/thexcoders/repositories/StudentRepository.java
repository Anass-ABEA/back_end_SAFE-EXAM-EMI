package com.thexcoders.repositories;

import com.thexcoders.holders.StudentHolder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentHolder,String> {}
