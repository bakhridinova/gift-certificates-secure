package com.epam.esm.util.validator;

import com.epam.esm.exception.ValidationException;
import com.epam.esm.util.enums.field.PageField;
import lombok.experimental.UtilityClass;

/**
 * Utility class validating pagination details
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomPageValidator {
    /**
     * Validates pagination parameters for a search operation to ensure that
     * they contain only digits, are not negative and are between upper and lower bounds
     *
     * @param page int page number to validate
     * @param size int page size to validate
     * @throws ValidationException if any of pagination parameters are not valid
     */
    public void validate(int page, int size) {
        validatePage(page);
        validateSize(size);
    }

    void validatePage(int page) {
        CustomValidator.notNegative(PageField.NUMBER, (double) page);
        CustomValidator.notTooLowOrHigh(PageField.NUMBER, (double) page, 0, 10000);
    }

    void validateSize(int size) {
        CustomValidator.notNegative(PageField.SIZE, (double) size);
        CustomValidator.notTooLowOrHigh(PageField.SIZE, (double) size, 0, 100);
    }
}
