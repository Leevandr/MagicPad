package org.example.repository;


import org.example.model.CreatedTest;

import java.util.*;


public class CreatedTestRepositoryImpl implements CreatedTestRepository{

    private final Map<Long, CreatedTest> createdTests = new HashMap<>();
    @Override
    public void add(CreatedTest createdTest) {
        createdTests.put(createdTest.getId(), createdTest);
    }

    @Override
    public Optional<CreatedTest> getById(long id) {
        return Optional.ofNullable(createdTests.get(id));
        // Не позволяет возвращать null, но я не знаю правильно ли это
    }

    @Override
    public List<CreatedTest> getAll() {
        return new ArrayList<>(createdTests.values());
    }

    @Override
    public void update(CreatedTest createdTest) {
        createdTests.put(createdTest.getId(), createdTest);
    }

    @Override
    public void delete(long id) {
        createdTests.remove(id);
    }
}
