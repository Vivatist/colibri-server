package ru.colibri.colibriserver.view.adminMenu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.colibri.colibriserver.security.UserRepository;

@Component
public class ChangePasswordValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    public boolean supports(Class<?> clazz) {
        return ChangePasswordForm.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {

        ChangePasswordForm changePasswordForm = (ChangePasswordForm) target;

        //TODO если необходимо включить проверку старого пароля, то раскомментировать
        //TODO не забыть раскомментировать также в user_edit_password.html
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "oldPassword.empty", "Пароль не может быть пустым");
//        User user = userRepository.findById(changePasswordForm.getUserId());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(changePasswordForm.getOldPassword());
//        System.out.println(user.getPassword());
//        if (!encoder.matches(changePasswordForm.getOldPassword(),user.getPassword())) {
//            errors.rejectValue("oldPassword", "oldPassword.notEquals", "Неверный пароль");
//        }


        if (changePasswordForm.getNewPassword().length() < 4) {
            errors.rejectValue("newPassword", "newPassword.tooShort", "Пароль не может быть меньше 4-х символов");
        }

        if (!(changePasswordForm.getNewPassword()).equals(changePasswordForm.getNewPasswordConfirm())) {
            errors.rejectValue("newPasswordConfirm", "confirmPassword.passwordDontMatch", "Пароли не совпадают");
        }

    }
}