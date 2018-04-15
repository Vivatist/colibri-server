package ru.colibri.colibriserver.testclasses.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.colibri.colibriserver.testclasses.dao.BookDao;
import ru.colibri.colibriserver.testclasses.model.Book;

@Controller
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookDao.findAllByOrderById());

        return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
        System.out.println(book.toString());
        System.out.println(book.getId());
        if(book.getId() == 0){
            this.bookDao.save(book);
        }else {
            this.bookDao.save(book);
        }

        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
        System.out.println("remove");
        this.bookDao.deleteById(id);
        return "redirect:/books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookDao.findById(id));
        model.addAttribute("listBooks", this.bookDao.findAllByOrderById());

        return "books";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookDao.findById(id));

        return "bookdata";
    }
}
