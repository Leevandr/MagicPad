package org.example.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Question {
    private TypeQuestion typeQuestion;
    private TypeAnswer typeAnswer;

    private String content;
    private Map<Integer, String> answers;

    public Question(TypeQuestion typeQuestion) {
        this.typeQuestion = typeQuestion;
        switch (typeQuestion) {
            case CHECKBOX_QUESTIONS:
                this.typeAnswer = TypeAnswer.SINGLE;
                break;
            case STRING_QUESTIONS, REVIEWED_QUESTIONS:
                this.typeAnswer = TypeAnswer.STRING;
                break;
            case MULTIPLE_QUESTIONS:
                this.typeAnswer = TypeAnswer.MULTIPLE;
                break;
            default:
                throw new IllegalArgumentException("Unknown typeQuestion: " + typeQuestion);
        }
    }

    public String toString() {
        return "Question{" +
                "typeQuestion=" + typeQuestion +
                ", typeAnswer=" + typeAnswer +
                ", questions=" + content +
                ", answer=" + answers.values() +
                '}';
    }
}
