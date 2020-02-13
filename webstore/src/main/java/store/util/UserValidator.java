package store.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import store.models.Customer;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
