package org.example.model;

public enum TypeQuestion {

    CHECKBOX_QUESTIONS, // Единственный правильный вопрос
    STRING_QUESTIONS, // Когда нужно ввести строчный вопрос
    MULTIPLE_QUESTIONS, // Когда можно поставить галочки(больше одной) что бы указать правильный вопрос
    REVIEWED_QUESTIONS; // Вопрос который учитель должен проверить самостоятельно


}


