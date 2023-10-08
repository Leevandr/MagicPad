package org.example.repository;

import org.example.model.CreatedTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreatedTestRepositoryImpl implements CreatedTestRepository{

    private final Map<Long, CreatedTest> createdTests = new HashMap<>();
    @Override
    public void add(CreatedTest createdTest) {

    }

    @Override
    public Optional<CreatedTest> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<CreatedTest> getAll() {
        return null;
    }

    @Override
    public void update(CreatedTest createdTest) {

    }

    @Override
    public void delete(long id) {

    }
}
