package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class CreatedTest {

    private long id;
    private List<Question> questions; // От сюда берем типы ответов и вопросов
    private List<Student> student; // Студенты, которые могут пройти тест
    private String question;
    private String answer;
    private boolean isOpen;
    private LocalTime timeDuration;
    private String name;
    private String link;
    private String description;
    private final long teacherId;



}

