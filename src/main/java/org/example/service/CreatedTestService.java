package org.example.service;

import org.example.model.CreatedTest;

import java.util.List;

public interface CreatedTestService {
    void createTest(CreatedTest createdTest);
    CreatedTest getTestById(long id);
    List<CreatedTest> getAllTests();
    void updateTest(CreatedTest createdTest);
    void deleteTest(long id);

}
