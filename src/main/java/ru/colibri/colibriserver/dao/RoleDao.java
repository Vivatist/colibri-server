package ru.colibri.colibriserver.dao;


import ru.colibri.colibriserver.domain.Role;

//@Transactional
//public interface RoleDao extends CrudRepository <Role, Long>{
public interface RoleDao {

    Role findByRolename(String rolename);

    void deleteRoleByRolename(String rolename);

}
