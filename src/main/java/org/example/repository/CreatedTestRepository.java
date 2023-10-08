package org.example.repository;

import org.example.model.CreatedTest;

import java.util.List;
import java.util.Optional;

public interface CreatedTestRepository {
    void add(CreatedTest createdTest);
    Optional<CreatedTest> getById(long id);
    List<CreatedTest> getAll();
    void update(CreatedTest createdTest);
    void delete(long id);
}
