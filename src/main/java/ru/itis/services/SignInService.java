package ru.itis.services;

import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;

public interface SignInService {
    User signIn(SignInForm form);
}
