package ru.hehnev.springrestpartone.repositories.dao;

import org.springframework.data.repository.CrudRepository;
import ru.hehnev.springrestpartone.model.entitys.Role;

public interface RoleDao extends CrudRepository<Role, Long> {

}
