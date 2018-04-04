package ru.colibri.colibriserver.security;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.colibri.colibriserver.security.model.Role;
import ru.colibri.colibriserver.security.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {


    public Set<Role> findAllByOrderById();

    public Role findById(Integer id) throws DataAccessException;
}