package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.model.CreatedTest;
import org.example.model.Question;
import org.example.model.Student;
import org.example.model.TypeAnswer;
import org.example.model.TypeQuestion;
import org.example.module.BindModule;
import org.example.service.CreatedTestService;

import java.time.LocalTime;
import java.util.*;

public class Main {

    private static Injector injector;

    public static void main(String[] args) {

        injector = Guice.createInjector(new BindModule());

        // Создаем сервис и репозиторий для тестов
        CreatedTestService createdTestService = injector.getInstance(CreatedTestService.class);


        //Создаем вопросы
        Question question1 = new Question(TypeQuestion.CHECKBOX_QUESTIONS);

        // Создаем тест
        CreatedTest createdTest = new CreatedTest(1,question1(), "Test", "Test", true, LocalTime.of(1, 30), 1);

        }


        public static Question question1() {
        Question question1 = new Question(TypeQuestion.CHECKBOX_QUESTIONS);
        question1.setContent("Какие из этих языков являются статически типизированными?");
            Map<Integer, String> answers1 = new HashMap<>(){{
                put(1, "Java");
                put(2, "Python");
                put(3, "C#");
                put(4, "C++");
            }};
        return question1;
        }
    public static Question question2() {
        Question question1 = new Question(TypeQuestion.CHECKBOX_QUESTIONS);
        question1.setContent("Какие из этих языков являются статически типизированными?");
        Map<Integer, String> answers1 = new HashMap<>(){{
            put(1, "Java");
            put(2, "Python");
            put(3, "C#");
            put(4, "C++");
        }};
        return question1;
    }

    }
}
