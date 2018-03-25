package ru.colibri.colibriserver.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.colibri.colibriserver.dao.BookDao;
import ru.colibri.colibriserver.model.Book;

import java.util.List;

@Controller
public class TestJsonServlet {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private BookDao bookDao;

    @Autowired
    public TestJsonServlet(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(value = "/test_json", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {

        Book book = new Book();
        book.setAuthor("Кто");
        book.setTitle("Там");
        book.setId(10);

        //    book = bookDao.findFirstByAuthor("Автор хуявтор"); //working
        //    book =  bookDao.findById(10); //работает!!!
        //    List<Book> books = bookDao.findByAuthor("Автор хуявтор"); //working
        List<Book> books  = bookDao.findByAuthorReturnList("Гагарин"); // working

        log.info(books.toString());
        log.debug("debug!");



        return books;
    }


}
