package org.example.service;

import com.google.inject.Inject;
import org.example.model.CreatedTest;
import org.example.model.Question;
import org.example.model.Student;
import org.example.model.TypeQuestion;
import org.example.repository.CreatedTestRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreatedTestServiceImpl implements CreatedTestService {

    private final CreatedTestRepository createdTestRepository;

    @Inject
    public CreatedTestServiceImpl(CreatedTestRepository createdTestRepository) {
        this.createdTestRepository = createdTestRepository;
    }

    @Override
    public void createTest(CreatedTest createdTest) {
        validateName(createdTest.getName());
        validateDescription(createdTest.getDescription());
        validateLink(createdTest.getLink());
        validateQuestions(createdTest.getQuestions());
        validateStudent(createdTest.getStudent());
        validateTimeDuration(createdTest.getTimeDuration());
        validateQuestionsContent(createdTest.getQuestions());
        validateAnswers(createdTest.getQuestions().stream()
                .flatMap(question -> question.getAnswer().stream())
                .collect(Collectors.toList()));
        validateQuestionType(createdTest.getQuestions());

        createdTestRepository.add(createdTest);
    }

    private void validateName(String name) {
        Optional.ofNullable(name)
                .filter(n -> !n.isBlank() && n.length() >= 3 && n.length() <= 255)
                .orElseThrow(() -> new IllegalArgumentException("Имя не может быть пустым или содержать больше 255 символов и меньше 3"));
    }

    private void validateDescription(String description) {
        Optional.ofNullable(description)
                .filter(desc -> !desc.isBlank() && desc.length() >= 3 && desc.length() <= 255)
                .orElseThrow(() -> new IllegalArgumentException("Описание не может содержать больше 255 символов и меньше 3"));
    }

    private String validateLink(String link) {
        if (link == null || link.isBlank()) {
            link = generateRandomLink();
        } else if (link.length() < 3 || link.length() > 20) {
            throw new IllegalArgumentException("Ссылка не может содержать больше 20 символов и меньше 3");
        }
        return link; // Тут получается ссылка на тест
    }

    private void validateQuestions(List<Question> questionsList) {
        Optional.ofNullable(questionsList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Тест должен содержать хотя бы один вопрос"));
        for (Question question : questionsList) {
            Optional.ofNullable(question.getQuestions())
                    .filter(list -> !list.isEmpty())
                    .orElseThrow(() -> new IllegalArgumentException("Каждый вопрос должен содержать хотя бы один пункт"));
        }
    }

    private void validateStudent(List<Student> students) {
        Optional.ofNullable(students)
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Тест должен содержать хотя бы одного студента, так как общедоступных тестов нет"));
    } //Тут сомневаюсь, перепроверить
    private void validateTimeDuration(LocalTime timeDuration) {
        Optional.ofNullable(timeDuration)
                .filter(time -> !time.isBefore(LocalTime.of(0, 1)))
                .orElseThrow(() -> new IllegalArgumentException("Тест должен длиться хотя бы 1 минуту"));
    }

    private Optional<IllegalArgumentException> validateQuestionType(List<Question> questionsList) {
        try {
            questionsList.forEach(question -> {
                if (question.getTypeQuestion() != TypeQuestion.CHECKBOX_QUESTIONS &&
                        question.getTypeQuestion() != TypeQuestion.STRING_QUESTIONS &&
                        question.getTypeQuestion() != TypeQuestion.REVIEWED_QUESTIONS) {
                    throw new IllegalArgumentException("Unknown typeQuestion: " + question.getTypeQuestion());
                }
            });
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.of(e);
        }
    }

    private void validateQuestionsContent(List<Question> questionsList) {
        for (Question question : questionsList) {
            if (question.getTypeQuestion() == TypeQuestion.REVIEWED_QUESTIONS) {
                question.getQuestions().forEach(q -> validateQuestionContent(q, 15000));
            } else {
                question.getQuestions().forEach(q -> validateQuestionContent(q, 255));
            }
        }
    }

    private void validateQuestionContent(String content, int maxLength) {
        Optional.ofNullable(content)
                .filter(c -> !c.isBlank() && c.length() >= 3 && c.length() <= maxLength)
                .orElseThrow(() -> new IllegalArgumentException("Вопрос не может содержать больше " + maxLength +" символов и меньше 3"));
    }

    private void validateAnswers(List<String> answers) {
        for (String answer : answers) {
            Optional.ofNullable(answer)
                    .filter(a -> !a.isBlank() && a.length() >= 3 && a.length() <= 255)
                    .orElseThrow(() -> new IllegalArgumentException("Ответ не может содержать больше 255 символов и меньше 3"));
        }
    }



    @Override
    public Optional<CreatedTest> getTestById(long id, long teacherId) {
        return createdTestRepository.getById(id)
                .filter(test -> test.getTeacherId() == teacherId);
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


    private String generateRandomLink() {
        LocalDateTime now = LocalDateTime.now();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return now.toString() + "-" + uuid;
    }
}
