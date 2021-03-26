package com.thexcoders.repositories;

import com.thexcoders.holders.QuestionHolder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository  extends MongoRepository<QuestionHolder,String> {
}
