package org.example.model;


import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User {
    private final long id;
    private final String password;
    private final String login;
    private String name;
    private String surname;
    private Boolean isTeacher;


}