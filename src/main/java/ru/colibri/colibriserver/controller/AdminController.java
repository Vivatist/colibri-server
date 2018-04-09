package ru.colibri.colibriserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.colibri.colibriserver.domain.TestClass;
import ru.colibri.colibriserver.security.CustomUserDetailsService;
import ru.colibri.colibriserver.security.RoleRepository;
import ru.colibri.colibriserver.security.UserValidator;
import ru.colibri.colibriserver.security.UserRepository;
import ru.colibri.colibriserver.security.model.Role;
import ru.colibri.colibriserver.security.model.User;

import java.util.HashSet;
import java.util.Set;


import static ru.colibri.colibriserver.controller.test.UserController.encryptePassword;

@Controller
@RequestMapping("/admin")
@Transactional
public class AdminController {



    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    TestClass testClass;

    @RequestMapping("/admin_menu")
    public String showAdminMenu(Model model) {

        return "admin_menu";
    }


    @RequestMapping(value = "user_list", method = RequestMethod.GET)
    public String listUsers(Model model) {

        System.err.println(testClass.getI());

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userRepository.findAllByOrderById());
        return "user_list";
    }


    @RequestMapping(value = "remove_user/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id,
                             @RequestParam(value = "user_name", required = false) String user_name,
                             Model model) {

        User user = userRepository.findById(id);

        if (user != null){
            log.debug("Remove user: " + user);
            userRepository.removeById(id);
        } else {
            user_name = null;
        }

        //  model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userRepository.findAllByOrderById());
        model.addAttribute("successRemove", user_name);


        return "user_list";
    }

    @Transactional
    @RequestMapping("user_edit/{id}")
    public String userData(@PathVariable("id") int id, Model model) {



        User user;

        if (id == 0) {
            user = new User();
            user.setEnabled(true);
            Set<Role> customRoles = new HashSet<>();
            customRoles.add(roleRepository.findByRole("ROLE_USER"));
            user.setRoles(customRoles);

        } else {
            user = userRepository.findById(id);
        }


        Set<Role> roles = roleRepository.findAllByOrderById();


        model.addAttribute("user", user);
        model.addAttribute("userRoles", roles);

        return "user_edit";
    }




    @RequestMapping(value = "/user_edit/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model, BindingResult result) {

        //Валидация полученных данных
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            log.debug("Error field of User: " + result.toString());
            Set<Role> roles = roleRepository.findAllByOrderById();
            model.addAttribute("userRoles", roles);
            model.addAttribute("user", user);
            //возваращаемся обратно с ошибкой
            return "user_edit";
        }


        if (user.getId() == null) {
            user.setPassword(encryptePassword(user.getPassword()));
            log.debug("Add new user: " + user.toString());
            Set<Role> roles = new HashSet<>();
            for (Role r : user.getRoles()) {
                roles.add(roleRepository.findByRole(r.getRole()));
            }
            user.setRoles(roles);
            customUserDetailsService.saveUser(user);
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
