package org.example.service;


import org.example.model.CreatedTest;
import org.example.model.Question;
import org.example.model.TypeQuestion;
import org.example.repository.CreatedTestRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreatedTestServiceImpl implements CreatedTestService {


    private final CreatedTestRepository createdTestRepository;

    public CreatedTestServiceImpl(CreatedTestRepository createdTestRepository) {
        this.createdTestRepository = createdTestRepository;
    }


    @Override
    public void createTest(CreatedTest createdTest) {
        if (createdTest.getName() == null || createdTest.getName().isEmpty() || createdTest.getName().isBlank()
                || createdTest.getName().length() > 255 || createdTest.getName().length() < 3) {
            throw new IllegalArgumentException("Имя не может быть пустым или содержать больше 255 символов и меньше 3");
        }
        if (createdTest.getDescription().length() > 255 || createdTest.getDescription().length() < 3) {
            throw new IllegalArgumentException("Описание не может содержать больше 255 символов и меньше 3");
        }
        if (createdTest.getLink().length() > 20 || createdTest.getLink().length() < 3) {
            throw new IllegalArgumentException("Ссылка не может содержать больше 20 символов и меньше 3");
        }//если не вводит ни какую ссылку она генерируется автоматически но тут 3 символа пофиксить крч надо
        if (createdTest.getQuestions().isEmpty()) {
            throw new IllegalArgumentException("Тест должен содержать хотя бы один вопрос");
        }
        if (createdTest.getStudent().isEmpty()) {
            throw new IllegalArgumentException("Тест должен содержать хотя бы одного студента так как общедоступных тестов нет");
        }
        if (createdTest.getTimeDuration() == null || createdTest.getTimeDuration().isBefore(LocalTime.of(0, 1))) {
            throw new IllegalArgumentException("Тест должен длиться хотя бы 1 минуту");
        }
        for (Question question : createdTest.getQuestions()) {
            if (question.getTypeQuestion() == TypeQuestion.REVIEWED_QUESTIONS) {
                if (createdTest.getQuestion().length() > 15000 || createdTest.getQuestion().isEmpty()
                        || createdTest.getQuestion().isBlank()) {
                    throw new IllegalArgumentException("Вопрос не может содержать больше 15000 " +
                            "символов и должен содержать хотя бы один символ");
                }
            } else {
                if (createdTest.getQuestion().length() > 255 || createdTest.getQuestion().length() < 3) {
                    throw new IllegalArgumentException("Вопрос не может содержать больше 255 символов и меньше 3");
                }
            }
        }
        if (createdTest.getAnswer().length() > 255 || createdTest.getAnswer().length() < 3) {
            throw new IllegalArgumentException("Ответ не может содержать больше 255 символов и меньше 3");
        }
        createdTestRepository.add(createdTest); // Ура наконец!
    }



    @Override
    public Optional<CreatedTest> getTestById(long id, long teacherId) {
        Optional<CreatedTest> testOptional = createdTestRepository.getById(id);

        if (testOptional.isPresent()) {
            CreatedTest test = testOptional.get();
            if (test.getTeacherId() == teacherId) {
                return Optional.of(test);
            } else {
                throw new IllegalArgumentException("Вы не имеете прав доступа к этому тесту");
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<CreatedTest> getAllTests(long teacherId) {
        return createdTestRepository.getAll().stream()
                .filter(test -> test.getTeacherId() == teacherId)
                .collect(Collectors.toList());
    }


    @Override
    public void updateTest(CreatedTest createdTest, long teacherId) {
        Optional<CreatedTest> testOptional = createdTestRepository.getById(createdTest.getId());

        if (testOptional.isPresent()) {
            CreatedTest existingTest = testOptional.get();
            if (existingTest.getTeacherId() == teacherId) {
                createdTestRepository.update(createdTest);
            } else {
                throw new IllegalArgumentException("Вы не имеете прав доступа к этому тесту");
            }
        } else {
            throw new IllegalArgumentException("Теста с таким id не существует");
        }
    }

    @Override
    public void deleteTest(long id, long teacherId) {
        Optional<CreatedTest> testOptional = createdTestRepository.getById(id);

        if (testOptional.isPresent()) {
            CreatedTest existingTest = testOptional.get();
            if (existingTest.getTeacherId() == teacherId) {
                createdTestRepository.delete(id);
            } else {
                throw new IllegalArgumentException("Вы не имеете прав доступа к этому тесту");
            }
        } else {
            throw new IllegalArgumentException("Тест с таким id не существует");
        }
    }
}
