package ru.itis.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String hashPassword;

}
