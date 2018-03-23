package ru.colibri.colibriserver.web.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestServlet {


    private String message = "Thymeleaf работает!";

    @RequestMapping("/test")
    public String showHello(Model model) {

        System.out.println("Servlet HELLO-WORLD");
        model.addAttribute("message", message);
        return "test";

    }
}
