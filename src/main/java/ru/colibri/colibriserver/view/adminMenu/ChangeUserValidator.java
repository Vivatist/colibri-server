package ru.colibri.colibriserver.view.adminMenu;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.colibri.colibriserver.security.RoleRepository;
import ru.colibri.colibriserver.security.UserRepository;
import ru.colibri.colibriserver.security.model.Role;
import ru.colibri.colibriserver.security.model.User;

import java.util.HashSet;
import java.util.Set;


@Component
public class ChangeUserValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Имя пользователя не может быть пустым");
        String username = user.getUsername();
        if ((username.length() > 16) || (username.length() < 3)) {
            errors.rejectValue("username", "username.tooLongOrShort", "Имя пользователя не должно быть короче 3 или длинее 16 символов");
        }

        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Неверный формат e-Mail адреса");
        }


        //TO
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRole("ROLE_ADMIN"));
        Set<User> users = userRepository.findByRolesAndEnabledIsTrue(roles);
        System.err.println(users.size());
        if ((!user.isEnabled()) && (users.size() == 1)) {
            errors.rejectValue("enabled", "enabled.lastAdminEnabled", "Нельзя деактивировать единственного администратора");
        }


        Role role = roleRepository.findByRole("ROLE_ADMIN");
        boolean isAdmin = user.getRoles().contains(role);
        users = userRepository.findByRolesAndEnabledIsTrue(roles);
        if ((!isAdmin) && (users.size() == 1)) {
            errors.rejectValue("roles", "enabled.lastAdminRole", "Нельзя убрать роль 'Администратор' у единственного администратора");
        }

        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "roles.empty", "Необходимо назначить хотя бы одну роль");
        }
    }
}