package ru.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.springcourse.dao.PersonDAO;
import ru.springcourse.models.Person;

import javax.validation.constraints.NotNull;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    //поддержка валидируемых классов (только Person)
    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        boolean isExistingId = personDAO.getPerson(person.getId()).isPresent();
        boolean isExistingEmail = personDAO.getPersonByEmail(person.getEmail()).isPresent();
        if (!isExistingId && isExistingEmail) {
            // поле, код ошибки, сообщение ошибки
            errors.rejectValue("email", "", "Этот email уже используется");
        }

        // Проверяем, что у человека имя начинается с заглавной буквы
        if (person.getName().length() > 0 && !Character.isUpperCase(person.getName().codePointAt(0)))
            errors.rejectValue("name", "", "Имя должно начинаться с заглавной буквы");
        // Проверяем, что у человека имя начинается с заглавной буквы
        if (person.getPatronymic().length() > 0 && !Character.isUpperCase(person.getPatronymic().codePointAt(0)))
            errors.rejectValue("patronymic", "", "Отчество должно начинаться с заглавной буквы");
        // Проверяем, что у человека имя начинается с заглавной буквы
        if (person.getSurname().length() > 0 && !Character.isUpperCase(person.getSurname().codePointAt(0)))
            errors.rejectValue("surname", "", "Фамилия должна начинаться с заглавной буквы");
    }
}