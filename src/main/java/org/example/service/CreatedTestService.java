package org.example.service;

import org.example.model.CreatedTest;

import java.util.List;
import java.util.Optional;

public interface CreatedTestService {
    void createTest(CreatedTest createdTest);
    Optional<CreatedTest> getTestById(long id, long teacherId);
    List<CreatedTest> getAllTests(long teacherId);
    void updateTest(CreatedTest createdTest, long teacherId);
    void deleteTest(long id, long teacherId);

}
