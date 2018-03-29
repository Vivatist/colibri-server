package ru.colibri.colibriserver.dao;


import ru.colibri.colibriserver.domain.UserAdapter;

//@Transactional
//public interface UserDao extends CrudRepository <UserAdapter, Long>{
public interface UserDao {

    UserAdapter findByUsername(String username);


    void deleteUserByUsername(String username);
}
