package org.example.service;

import com.google.inject.Inject;
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

    @Inject
    public CreatedTestServiceImpl(CreatedTestRepository createdTestRepository) {
        this.createdTestRepository = createdTestRepository;
    }

    @Override
    public void createTest(CreatedTest createdTest) {
        Optional.ofNullable(createdTest.getName())
                .filter(name -> !name.isBlank() && name.length() >= 3 && name.length() <= 255)
                .orElseThrow(() -> new IllegalArgumentException("Имя не может быть пустым или содержать больше 255 символов и меньше 3"));

        Optional.ofNullable(createdTest.getDescription())
                .filter(desc -> desc.length() >= 3 && desc.length() <= 255)
                .orElseThrow(() -> new IllegalArgumentException("Описание не может содержать больше 255 символов и меньше 3"));

        Optional.ofNullable(createdTest.getLink())
                .filter(link -> link.length() >= 3 && link.length() <= 20)
                .orElseThrow(() -> new IllegalArgumentException("Ссылка не может содержать больше 20 символов и меньше 3"));

        Optional.ofNullable(createdTest.getQuestions())
                .filter(questions -> !questions.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Тест должен содержать хотя бы один вопрос"));

        Optional.ofNullable(createdTest.getStudent())
                .filter(student -> !student.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Тест должен содержать хотя бы одного студента так как общедоступных тестов нет"));

        Optional.ofNullable(createdTest.getTimeDuration())
                .filter(time -> !time.isBefore(LocalTime.of(0, 1)))
                .orElseThrow(() -> new IllegalArgumentException("Тест должен длиться хотя бы 1 минуту"));

        for (Question question : createdTest.getQuestions()) {
            if (question.getTypeQuestion() == TypeQuestion.REVIEWED_QUESTIONS) {
                Optional.ofNullable(createdTest.getQuestion())
                        .filter(content -> !content.isEmpty() && !content.isBlank() && content.length() <= 15000)
                        .orElseThrow(() -> new IllegalArgumentException("Вопрос не может содержать больше 15000 символов и должен содержать хотя бы один символ"));
            } else {
                Optional.ofNullable(createdTest.getQuestion())
                        .filter(content -> !content.isBlank() && content.length() >= 3 && content.length() <= 255)
                        .orElseThrow(() -> new IllegalArgumentException("Вопрос не может содержать больше 255 символов и меньше 3"));
            }
        }

        Optional.ofNullable(createdTest.getAnswer())
                .filter(answer -> !answer.isBlank() && answer.length() >= 3 && answer.length() <= 255)
                .orElseThrow(() -> new IllegalArgumentException("Ответ не может содержать больше 255 символов и меньше 3"));

        createdTestRepository.add(createdTest);
    }

    @Override
    public Optional<CreatedTest> getTestById(long id, long teacherId) {
        Optional<CreatedTest> testOptional = createdTestRepository.getById(id);

        return testOptional.filter(test -> test.getTeacherId() == teacherId);
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

        testOptional.ifPresent(existingTest -> {
            if (existingTest.getTeacherId() == teacherId) {
                createdTestRepository.update(createdTest);
            } else {
                throw new IllegalArgumentException("Вы не имеете прав доступа к этому тесту");
            }
        });
    }

    @Override
    public void deleteTest(long id, long teacherId) {
        Optional<CreatedTest> testOptional = createdTestRepository.getById(id);

        testOptional.ifPresent(existingTest -> {
            if (existingTest.getTeacherId() == teacherId) {
                createdTestRepository.delete(id);
            } else {
                throw new IllegalArgumentException("Вы не имеете прав доступа к этому тесту");
            }
        });
    }
}
