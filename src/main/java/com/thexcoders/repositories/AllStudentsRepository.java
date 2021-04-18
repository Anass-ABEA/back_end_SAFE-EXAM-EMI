package com.thexcoders.repositories;

import com.thexcoders.holders.AllStudentsHolder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AllStudentsRepository extends MongoRepository<AllStudentsHolder,String> {}
