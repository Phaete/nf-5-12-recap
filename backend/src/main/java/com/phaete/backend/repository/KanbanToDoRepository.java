package com.phaete.backend.repository;

import com.phaete.backend.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KanbanToDoRepository extends MongoRepository<Todo, String> {
}
