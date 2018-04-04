package ru.colibri.colibriserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.colibri.colibriserver.security.CustomUserDetailsService;
import ru.colibri.colibriserver.security.RoleRepository;
import ru.colibri.colibriserver.security.UserRepository;
import ru.colibri.colibriserver.security.model.Role;
import ru.colibri.colibriserver.security.model.User;

import java.util.Set;


import static ru.colibri.colibriserver.controller.UserController.encryptePassword;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/admin_menu")
    public String showAdminMenu(Model model) {

        return "admin_menu";
    }


    @RequestMapping(value = "user_list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userRepository.findAllByOrderById());

        return "user_list";
    }


    @RequestMapping("user_edit/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id);
        Set<Role> roles = roleRepository.findAllByOrderById();

//        User customUser = new User("User2", "user2", encryptePassword("password"), true);
//        Set<Role> roles1 = new HashSet<>();
//        roles1.add(new Role("ROLE_USER2"));
//        customUser.setRoles(roles1);
//        customUserDetailsService.saveUser(customUser);

       // user.setPassword("");

        model.addAttribute("user", user);
        model.addAttribute("userRoles", roles);

        log.debug("/user_edit " + user.toString());
        log.debug("/user_edit " + roles.toString());
        return "user_edit";
    }


    @RequestMapping(value = "/user_edit/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {



        if (user.getId() == 0) {
            log.debug("Add new user: " + user.toString());
            this.userRepository.save(user);
        } else {
            String newPassword = user.getPassword();
            if (newPassword.isEmpty()) { //если со страницы пришел пустой пароль, то сохраняем старый пароль
                user.setPassword(userRepository.findById(user.getId()).getPassword());
            } else {
                user.setPassword(encryptePassword(user.getPassword()));
            }
            log.debug("Edit user: " + user.toString());
            this.userRepository.save(user);
        }

        return "redirect:../user_list";
    }
}
