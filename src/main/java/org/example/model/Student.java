package org.example.model;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Student {
    private final long id;

    private final List<PassedTest> passedTests;

}
