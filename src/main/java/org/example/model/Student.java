package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Student {
    private final long id;
    private final String name;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
