package ru.itis.repositories;

import ru.itis.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> getUserByEmail(String email);
    Optional<Object> getUserByID(Long id);
}
