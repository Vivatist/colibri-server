package ru.colibri.colibriserver.view.adminMenu;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.colibri.colibriserver.security.UserRepository;
import ru.colibri.colibriserver.security.model.User;


@Component
public class NewUserValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }



    public void validate(Object target,  Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Имя пользователя не может быть пустым");
        String username = user.getUsername();
        if ((username.length() > 16)||(username.length() < 3)) {
            errors.rejectValue("username", "username.tooLongOrShort", "Имя пользователя не должно быть короче 3 или длинее 16 символов");
        }

        if (userRepository.findByUsername(username)!=null){
            errors.rejectValue("username", "username.double", "Пользователь с таким именем уже существует");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Пароль не может быть пустым");


        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Неверный формат e-Mail адреса");
        }

        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "roles.empty", "Необходимо назначить хотя бы одну роль");
        }
    }
}