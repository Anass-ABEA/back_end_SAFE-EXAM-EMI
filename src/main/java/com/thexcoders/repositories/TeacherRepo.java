package com.thexcoders.repositories;

import com.thexcoders.holders.TeacherHolder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends MongoRepository<TeacherHolder,String> {
}
