package store.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import store.entity.User;
import store.service.UserService;

@Component
public class CustomerValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.getOneByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Пользователь с таким email уже существует");
        }
        if (userService.getOneByPhone(user.getPhone()) != null) {
            errors.rejectValue("phone", "", "Данный номер телефона уже занят!");
        }
        if (user.getPassword().length() < 7 || user.getPassword().length() > 20){
            errors.rejectValue("password","","Недостаточно символов: min = 6, max = 20");
        }
        if (user.getPassword2().isEmpty()){
            errors.rejectValue("password2","","Данное поле должно быть заполнено!");
        }
        if (!user.getPassword2().isEmpty() && !user.getPassword().equals(user.getPassword2())) {
            errors.rejectValue("password2", "", "Пароли должны совпадать!");
        }
    }
}
