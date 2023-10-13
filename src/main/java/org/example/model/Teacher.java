package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Teacher {
    private final long id;
    private final List<CreatedTest> createdTests;

}
