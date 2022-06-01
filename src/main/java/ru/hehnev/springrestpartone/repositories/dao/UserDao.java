package ru.hehnev.springrestpartone.repositories.dao;

import org.springframework.data.repository.CrudRepository;
import ru.hehnev.springrestpartone.model.entitys.User;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
