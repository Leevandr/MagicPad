package org.example.model;


import lombok.AllArgsConstructor;


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
