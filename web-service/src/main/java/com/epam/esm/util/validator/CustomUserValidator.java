package com.epam.esm.util.validator;

import com.epam.esm.dto.UserDto;
import com.epam.esm.exception.CustomValidationException;
import com.epam.esm.util.enums.field.UserField;
import lombok.experimental.UtilityClass;

/**
 * utility class validating users
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomUserValidator {
    /**
     * validates user to ensure that it's parameters are valid
     *
     * @param user to validate
     * @throws CustomValidationException if any of user parameters are not valid
     */
    public void validate(UserDto user) {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
    }

    private void validateUsername(String name) {
        CustomValidator.notNull(UserField.USERNAME, name);
        CustomValidator.notBlank(UserField.USERNAME, name);
        CustomValidator.notTooShortOrLong(UserField.USERNAME, name, 3, 30);
        CustomValidator.onlyLetters(UserField.USERNAME, name);
    }

    private void validatePassword(String password) {
        CustomValidator.notNull(UserField.PASSWORD, password);
        CustomValidator.notBlank(UserField.PASSWORD, password);
        CustomValidator.notTooShortOrLong(UserField.PASSWORD, password, 8, 20);
        CustomValidator.atLeastOneUppercaseLetterLowercaseLetterNumberAndSpecialCharacter(UserField.PASSWORD, password);
    }
}
