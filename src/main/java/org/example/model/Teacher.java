package org.example.model;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Teacher {

    private final long id;
    private final List<CreatedTest> createdTests;

}
