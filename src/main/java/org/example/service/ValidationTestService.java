package org.example.service;

import org.example.model.Question;

import java.util.List;

public interface ValidationTestService {

    void validate(String content) throws IllegalArgumentException;

    void validateQuestionsContent(List<Question> questionsList, int maxLength);

    void validateQuestionContent(String content, int maxLength);

    boolean validateReviewedQuestions(String content);

    boolean validateOtherQuestions(String content);
}
