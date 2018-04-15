package ru.colibri.colibriserver.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.colibri.colibriserver.security.model.Role;
import ru.colibri.colibriserver.security.model.User;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    Set<User> findAllByOrderById();

    User findById(int id);

    void removeById(int id);

    Set<User> findByRolesAndEnabledIsTrue(Set<Role> roles);
}