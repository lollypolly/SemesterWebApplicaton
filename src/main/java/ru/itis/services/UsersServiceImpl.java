package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<UserDto> getUserByCookie(String value) {
      //  if(usersRepository.getUserByID(id).isPresent()){
       //     User user = (User) usersRepository.getUserByID(id).get();
       //     UserDto userDto = UserDto.builder().username(user.getUsername())
      //              .email(user.getEmail());

       ////     return Optional.of(userDto);
      //  }

        return Optional.empty();
    }
}
