package org.example.service;


import org.example.model.CreatedTest;
import org.example.repository.CreatedTestRepository;

import java.util.List;

public class CreatedTestServiceImpl implements CreatedTestService {


    private final CreatedTestRepository createdTestRepository;

    public CreatedTestServiceImpl(CreatedTestRepository createdTestRepository) {
        this.createdTestRepository = createdTestRepository;
    }


    @Override
    public void createTest(CreatedTest createdTest) {

    }

    @Override
    public CreatedTest getTestById(long id) {
        return null;
    }

    @Override
    public List<CreatedTest> getAllTests() {
        return null;
    }

    @Override
    public void updateTest(CreatedTest createdTest) {

    }

    @Override
    public void deleteTest(long id) {

    }
}
