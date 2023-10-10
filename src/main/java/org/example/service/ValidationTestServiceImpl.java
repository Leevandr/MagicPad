package org.example.service;

import org.example.model.Question;
import org.example.model.TypeAnswer;
import org.example.model.TypeQuestion;

import java.util.List;

public class ValidationTestServiceImpl implements ValidationTestService {

    @Override
    public void validate(String content) throws IllegalArgumentException {
        // Implement your validation logic here
    }

    @Override
    public void validateQuestionsContent(List<Question> questionsList, int maxLength) {
        for (Question question : questionsList) {
            validateQuestionContent(question.getContent(), maxLength);
        }
    }

    @Override
    public void validateQuestionContent(String content, int maxLength) {
        if (content == null || content.isBlank() || content.length() < 3 || content.length() > maxLength) {
            throw new IllegalArgumentException("Вопрос не может содержать больше " + maxLength + " символов и меньше 3");
        }
    }

    @Override
    public boolean validateReviewedQuestions(String content) {
        return content.length() >= 3 && content.length() <= 15000;
    }

    @Override
    public boolean validateOtherQuestions(String content) {
        return content.length() >= 3 && content.length() <= 255;
    }

    public static void main(String[] args) {
        ValidationTestService service = new ValidationTestServiceImpl();

        // Пример использования:
        List<Question> questions = List.of(
                new Question(TypeQuestion.STRING_QUESTIONS, TypeAnswer.STRING, "What is your name?", List.of("John")),
                new Question(TypeQuestion.REVIEWED_QUESTIONS, TypeAnswer.STRING, "Write a detailed review.", List.of("Great product!"))
        );

        service.validateQuestionsContent(questions, 255);
    }
}
