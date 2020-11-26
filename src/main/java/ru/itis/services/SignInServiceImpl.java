package ru.itis.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User signIn(SignInForm form) {
        String email = form.getEmail();
        String password = form.getPassword();


        Optional<User> user;
        if ((user = usersRepository.getUserByEmail(email)).isPresent() && passwordEncoder.matches(password, user.get().getHashPassword())) {
            return user.get();
        } else {
            return null;
        }

    }

}
