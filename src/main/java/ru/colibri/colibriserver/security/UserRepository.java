package ru.colibri.colibriserver.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.colibri.colibriserver.security.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}