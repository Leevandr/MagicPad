package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.model.CreatedTest;
import org.example.module.BindModule;
import org.example.service.CreatedTestService;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class Main {

    private static Injector injector;

    public static void main(String[] args) {

        injector = Guice.createInjector(new BindModule());

        // Создаем сервис и репозиторий для тестов
        CreatedTestService createdTestService = injector.getInstance(CreatedTestService.class);

        // Создаем тесты
        CreatedTest test1 = new CreatedTest(1, null, null, "What is the capital of France?", "Paris", true, LocalTime.of(0, 5), "Geography Quiz", "geography-quiz", "Test your knowledge of world capitals.", 1);
        CreatedTest test2 = new CreatedTest(2, null, null, "What is the square root of 16?", "4", true, LocalTime.of(0, 10), "Math Quiz", "math-quiz", "Test your math skills.", 2);

        // Добавляем тесты
        createdTestService.createTest(test1);
        createdTestService.createTest(test2);

        // Получаем все тесты и выводим информацию о них
        List<CreatedTest> allTests = createdTestService.getAllTests(1);
        for (CreatedTest test : allTests) {
            System.out.println(test);
        }

        // Получаем тест по ID и выводим информацию о нем
        Optional<CreatedTest> testById = createdTestService.getTestById(1, 1);
        testById.ifPresent(System.out::println);

        // Обновляем тест
        CreatedTest updatedTest = new CreatedTest(1, null, null, "What is the capital of France?", "Paris", true, LocalTime.of(0, 15), "Updated Geography Quiz", "updated-geography-quiz", "Updated description.", 1);
        createdTestService.updateTest(updatedTest, 1);

        // Повторно получаем тест по ID и выводим информацию о нем
        testById = createdTestService.getTestById(1, 1);
        testById.ifPresent(System.out::println);

        // Удаляем тест по ID
        createdTestService.deleteTest(2, 2);

        // Повторно получаем все тесты и выводим информацию о них
        allTests = createdTestService.getAllTests(1);
        for (CreatedTest test : allTests) {
            System.out.println(test);
        }
    }
}
