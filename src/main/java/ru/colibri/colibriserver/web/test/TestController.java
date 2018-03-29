package ru.colibri.colibriserver.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.colibri.colibriserver.dao.BookDao;
import ru.colibri.colibriserver.dao.RoleDao;
import ru.colibri.colibriserver.dao.UserDao;
import ru.colibri.colibriserver.domain.Role;
import ru.colibri.colibriserver.model.Book;

import java.util.List;

@Controller
public class TestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private BookDao bookDao;

    @Autowired
    public TestController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(value = "/test_json", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {

        Book book = new Book();
        book.setAuthor("Кто");
        book.setTitle("Там");
        book.setId(10);

        List<Book> books  = bookDao.findByAuthorReturnList("Гагарин"); // working

        log.info(books.toString());
        log.debug("debug!");
        return books;
    }


    @RequestMapping("/test")
    public String showHello(Model model) {
        System.out.println("Servlet HELLO-WORLD");
        model.addAttribute("message", "ТЕСТ");

        //Role role = new Role();

        //role.setRole("Admin");

        return "test";
    }

}
