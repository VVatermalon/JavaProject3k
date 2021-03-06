package by.osadchuk.students_base.validator;


import by.osadchuk.students_base.form.RegistrationStudentModel;
import by.osadchuk.students_base.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationStudentModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationStudentModel user = (RegistrationStudentModel) o;

        if(userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "This username is already in use");
        }

        if(userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "", "This Email is already in use");
        }

    }
}
