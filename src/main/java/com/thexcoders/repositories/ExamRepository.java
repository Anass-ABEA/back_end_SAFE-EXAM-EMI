package com.thexcoders.repositories;

import com.thexcoders.holders.ExamHolder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<ExamHolder,String> {}
