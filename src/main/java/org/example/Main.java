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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static Injector injector;

    public static void main(String[] args) {

        injector = Guice.createInjector(new BindModule());

        // Создаем сервис и репозиторий для тестов
        CreatedTestService createdTestService = injector.getInstance(CreatedTestService.class);

        // Создаем тесты
        List<Question> questions = new ArrayList<>();
        List<String> question1Content = List.of("What is the capital of France?");
        List<String> question1Answer = List.of("Paris");
        questions.add(new Question(TypeQuestion.STRING_QUESTIONS, TypeAnswer.STRING, question1Content, question1Answer));

        List<Student> students = List.of(new Student(1, "John Doe"));

        CreatedTest test1 = new CreatedTest(
                1, questions, students, false, LocalTime.of(0, 1),
                "Geography Quiz", "geography-quiz", "Test your knowledge of world capitals.", 1
        );

        // Добавляем тесты
        createdTestService.createTest(test1);

        // Получаем все тесты и выводим информацию о них
        List<CreatedTest> allTests = createdTestService.getAllTests(1);
        System.out.println("Список всех тестов:");
        for (CreatedTest test : allTests) {
            System.out.println(test);
        }

        // Получаем тест по ID и выводим информацию о нем
        Optional<CreatedTest> testById = createdTestService.getTestById(1, 1);
        System.out.println("\nТест с ID 1:");
        testById.ifPresent(System.out::println);

        // Обновляем тест
        List<String> updatedQuestionContent = List.of("What is the capital of Spain?");
        List<String> updatedQuestionAnswer = List.of("Madrid");
        List<Question> updatedQuestions = new ArrayList<>();
        updatedQuestions.add(new Question(TypeQuestion.STRING_QUESTIONS, TypeAnswer.STRING, updatedQuestionContent, updatedQuestionAnswer));

        CreatedTest updatedTest = new CreatedTest(
                1, updatedQuestions, students, true, LocalTime.of(0, 15),
                "Geography Quiz 2.0", "geography-quiz-2", "Updated description.", 1
        );
        createdTestService.updateTest(updatedTest, 1);

        // Повторно получаем тест по ID и выводим информацию о нем
        testById = createdTestService.getTestById(1, 1);
        System.out.println("\nОбновленный тест с ID 1:");
        testById.ifPresent(System.out::println);

        // Удаляем тест по ID
        createdTestService.deleteTest(1, 1);

        // Повторно получаем все тесты и выводим информацию о них
        allTests = createdTestService.getAllTests(1);
        System.out.println("\nСписок всех тестов после удаления:");
        for (CreatedTest test : allTests) {
            System.out.println(test);

        }
    }
}
