package com.epam.esm.util.validator;

import com.epam.esm.entity.AbstractEntity;
import com.epam.esm.exception.CustomValidationException;
import com.epam.esm.util.enums.field.EntityField;
import lombok.experimental.UtilityClass;

/**
 * utility class validating user input details
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomValidator {
    private final String ONLY_LETTERS = "^[a-zA-Z]*$";
    private final String ONLY_LETTERS_AND_SPACES = "^[a-zA-Z ]*$";
    private final String AT_LEAST_ONE_UPPERCASE_LETTER_LOWERCASE_LETTER_NUMBER_AND_SPECIAL_CHARACTER = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    /**
     * validates ID parameter to ensure that it is not null and is positive
     *
     * @param entityField field to validate
     * @param id Long ID to validate
     * @throws CustomValidationException if ID parameter is not valid
     */
    public <T extends AbstractEntity> void validateId(EntityField<T> entityField, Long id) {
        CustomValidator.notNull(entityField, id);

        if (id <= 0) {
            throw new CustomValidationException(entityField.getName() + " must be positive");
        }
    }

    <T> void notNull(EntityField<T> entityField, Object value) {
        if (value == null) {
            throw new CustomValidationException(entityField.getName() + " should not be null");
        }
    }

    <T> void notBlank(EntityField<T> entityField, String value) {
        if (value.isBlank()) {
            throw new CustomValidationException(entityField.getName() + " should not be empty or blank");
        }
    }

    <T> void onlyLetters(EntityField<T> entityField, String value) {
        if (!value.matches(ONLY_LETTERS)) {
            throw new CustomValidationException(entityField.getName() + " must include only letters");
        }
    }

    <T> void onlyLettersAndSpaces(EntityField<T> entityField, String value) {
        if (!value.matches(ONLY_LETTERS_AND_SPACES)) {
            throw new CustomValidationException(entityField.getName() + " must include only letters and spaces");
        }
    }

    @SuppressWarnings("all")
    <T> void atLeastOneUppercaseLetterLowercaseLetterNumberAndSpecialCharacter(EntityField<T> entityField, String value) {
        if (!value.matches(AT_LEAST_ONE_UPPERCASE_LETTER_LOWERCASE_LETTER_NUMBER_AND_SPECIAL_CHARACTER)) {
            throw new CustomValidationException(entityField.getName() + " must include at least one uppercase letter (A-Z), one lowercase letter (a-z), one number (0-9) and one special character (#?!@$%^&*-)");
        }
    }

    <T> void notTooShortOrLong(EntityField<T> entityField, String value, int lower, int upper) {
        if (value.length() < lower || value.length() > upper) {
            throw new CustomValidationException(entityField.getName() + " must be between " + lower + " and " + upper + " characters");
        }
    }

    <T> void notTooLowOrHigh(EntityField<T> entityField, Double value, int lower, int upper) {
        if (value < lower || value > upper) {
            throw new CustomValidationException(entityField.getName() + " must be between " + lower + " and " + upper );
        }
    }

    <T> void notNegative(EntityField<T> entityField, Double value) {
        if (value < 0) {
            throw new CustomValidationException(entityField.getName() + " should not be negative");
        }
    }
}
