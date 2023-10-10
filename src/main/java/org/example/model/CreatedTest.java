package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class CreatedTest implements Comparable<CreatedTest> {

    private long id;
    private List<Question> questions; // От сюда берем типы ответов и вопросов
    private List<Student> student; // Студенты, которые могут пройти тест
    private boolean isOpen;
    private LocalTime timeDuration;
    private String name;
    private String link;
    private String description;
    private final long teacherId;


    @Override
    public String toString() {
        return "CreatedTest{" +
                "id=" + id +
                ", questions=" + questions +
                ", student=" + student +
                ", isOpen=" + isOpen +
                ", timeDuration=" + timeDuration +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }

    //compareTo method for .sorted
    //поменять на сортировку по дате создания
    @Override
    public int compareTo(CreatedTest o) {
        return this.getName().compareTo(o.getName());
    }

}

