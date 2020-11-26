package ru.itis.dto;

import lombok.Data;

@Data
public class SignUpForm {
    private String lastName;
    private String firstName;
    private String username;
    private String email;
    private String password;
}
