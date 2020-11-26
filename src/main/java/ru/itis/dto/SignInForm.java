package ru.itis.dto;

import lombok.Data;

@Data
public class SignInForm {
    private String username;
    private String email;
    private String password;
}