package ru.colibri.colibriserver.testclasses.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.colibri.colibriserver.testclasses.colibri.Colibri;
import ru.colibri.colibriserver.testclasses.dao.BookDao;
import ru.colibri.colibriserver.testclasses.domain.TestClass;
import ru.colibri.colibriserver.testclasses.model.Book;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    TestClass testClass;


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

        List<Book> books = bookDao.findByAuthorReturnList("Гагарин"); // working

        return books;
    }


    @RequestMapping("/test")
    public String showHello(Model model) {
        model.addAttribute("message", "ТЕСТ Ок");
        model.addAttribute("date", testClass.getDate());
        System.err.println(testClass.getI());

        Colibri colibri = new Colibri();
        colibri.start();

        return "test";
    }


}
