package ru.colibri.colibriserver.security;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.colibri.colibriserver.security.model.User;


@Component
public class ChangePasswordValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    public boolean supports(Class<?> clazz) {
        return ChangePasswordForm.class.isAssignableFrom(clazz);
    }


    public void validate(Object target,  Errors errors) {

        ChangePasswordForm changePasswordForm = (ChangePasswordForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "oldPassword.empty", "Пароль не может быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "newPassword.empty", "Пароль не может быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPasswordConfirm", "newPasswordConfirm.empty", "Пароль не может быть пустым");


   //        if (!(user.getPassword()).equals(user
//                .getPassword())) {
//            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
//        }

    }
}