package ru.colibri.colibriserver.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.colibri.colibriserver.model.Book;

import java.util.List;


@Transactional
public interface BookDao extends CrudRepository<Book, Long> {

    List<Book> findAllByOrderById();

    List<Book> findByAuthor(String author);

    Book findFirstByAuthor(String author);

    Book findById(int id);

    void deleteById(int id);


    // пример кастомного запроса
    @Query("SELECT u FROM Book u WHERE u.author = :param_author")
    List<Book> findByAuthorReturnList(@Param("param_author") String author);

}
