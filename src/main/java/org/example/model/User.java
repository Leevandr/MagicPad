package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {


    private final long id;
    private final String password;
    private final String login;
    private String name;
    private String surname;
    private Boolean isTeacher;


}