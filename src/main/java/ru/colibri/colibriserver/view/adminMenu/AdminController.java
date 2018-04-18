package ru.colibri.colibriserver.view.adminMenu;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.colibri.colibriserver.security.*;
import ru.colibri.colibriserver.security.model.Role;
import ru.colibri.colibriserver.security.model.User;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
@Transactional
@Slf4j
public class AdminController {

    private final ChangeUserValidator changeUserValidator;

    private final NewUserValidator newUserValidator;

    private final CustomUserDetailsService customUserDetailsService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ChangePasswordValidator changePasswordValidator;


    @Autowired
    public AdminController(ChangeUserValidator changeUserValidator,
                           NewUserValidator newUserValidator, CustomUserDetailsService customUserDetailsService,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           ChangePasswordValidator changePasswordValidator) {

        this.changeUserValidator = changeUserValidator;
        this.newUserValidator = newUserValidator;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.changePasswordValidator = changePasswordValidator;
    }

    //Выводит галвное меню администратора
    @RequestMapping("/admin_menu")
    public String showAdminMenu(Model model) {

        return "admin/admin_menu";
    }


    //Выводит список всех пользователей
    @RequestMapping(value = "user_list", method = RequestMethod.GET)
    public String listUsers(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userRepository.findAllByOrderById());

        return "admin/users/user_list";
    }


    //Удаляет пользователя
    @RequestMapping(value = "remove_user/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id,
                             @RequestParam(value = "user_name", required = false) String user_name,
                             Model model) {

        User user = userRepository.findById(id);

        if (user != null) {
            log.debug("Remove user: " + user);
            userRepository.removeById(id);
        } else {
            user_name = null;
        }

        model.addAttribute("listUsers", this.userRepository.findAllByOrderById());
        model.addAttribute("successRemove", user_name);

        return "admin/users/user_list";
    }




    //Выводит форму изменения пароля
    @RequestMapping("user_edit_password/{id}")
    public String formEditPassword(@PathVariable("id") int id, Model model) {

        ChangePasswordForm changePasswordForm = new ChangePasswordForm();
        changePasswordForm.setUserId(id);
        model.addAttribute("changePasswordForm", changePasswordForm);

        return "admin/users/user_edit_password";
    }



    //Сохраняет новый пароль
    @RequestMapping(value = "user_edit/save_change_password", method = RequestMethod.POST)
    public String saveChangePassword(ChangePasswordForm changePasswordForm,
                                     BindingResult result) {

        changePasswordValidator.validate(changePasswordForm, result);

        if (result.hasErrors()) {
            log.debug("Error changing user password: " + result.toString());
            //возваращаемся обратно с ошибкой
            return "admin/users/user_edit_password";
        }

        User user = userRepository.findById(changePasswordForm.getUserId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(changePasswordForm.getNewPassword()));
        userRepository.save(user);

        log.info("Change password for user" + user.toString());
        return "redirect:../user_edit/" + changePasswordForm.getUserId() + "?successChangePassword";
    }



    //Выводит форму добавления нового пользователя
    @Transactional
    @RequestMapping("user_edit/new_user")
    public String formNewUser(Model model) {

        User user = new User();
        user.setEnabled(true);
        Set<Role> customRoles = new HashSet<>();
        customRoles.add(roleRepository.findByRole("ROLE_USER"));
        user.setRoles(customRoles);

        Set<Role> roles = roleRepository.findAllByOrderById();

        model.addAttribute("user", user);
        model.addAttribute("userRoles", roles);

        return "admin/users/user_new";
    }



    //Сохраняет форму с новым пользователем
    @RequestMapping(value = "/user_edit/save_new", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model, BindingResult result) {

        //Валидация полученных данных
        newUserValidator.validate(user, result);

        if (result.hasErrors()) {
            log.debug("Error field of User: " + result.toString());
            Set<Role> roles = roleRepository.findAllByOrderById();
            model.addAttribute("userRoles", roles);
            //возваращаемся обратно с ошибко
            return "admin/users/user_new";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        log.debug("Add new user: " + user.toString());

        Set<Role> roles = new HashSet<>();
        for (Role r : user.getRoles()) {
            roles.add(roleRepository.findByRole(r.getRole()));
            user.setRoles(roles);
            customUserDetailsService.saveUser(user);
        }

        log.debug("Edit user: " + user.toString());
        this.userRepository.save(user);
        return "redirect:../user_list";

    }



    //Выводит форму изменения пользователя
    @Transactional
    @RequestMapping("user_edit/{id}")
    public String formEditUser(@PathVariable("id") int id, Model model,
                               @RequestParam(value = "successChangePassword", required = false) String successChangePassword) {

        User user = userRepository.findById(id);

        Set<Role> roles = roleRepository.findAllByOrderById();

        model.addAttribute("user", user);
        model.addAttribute("userRoles", roles);
        model.addAttribute("successChangePassword", successChangePassword);

        return "admin/users/user_edit";
    }




    //Сохраняет форму с измененным пользователем
    @RequestMapping(value = "/user_edit/save_changes", method = RequestMethod.POST)
    public String editUser(User user, Model model, BindingResult result) {

        //т.к. пароль на форму не отправлялся - берем старый из базы
        user.setPassword(userRepository.findById(user.getId()).getPassword());
        //Валидация полученных данных
        changeUserValidator.validate(user, result);

        if (result.hasErrors()) {
            log.debug("Error field of User: " + result.toString());
            Set<Role> roles = roleRepository.findAllByOrderById();
            model.addAttribute("userRoles", roles);
            //возваращаемся обратно с ошибкой
            return "admin/users/user_edit";
        }

        log.debug("Edit user: " + user.toString());
        this.userRepository.save(user);
        return "redirect:../user_list";
    }



}
