package com.epam.esm.util.validator;

import com.epam.esm.exception.ValidationException;
import com.epam.esm.util.enums.field.SortField;
import lombok.experimental.UtilityClass;

/**
 * Utility class validating sorting details
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomSortValidator {
    private final String ASC_OR_DESC = "(asc|desc)";
    private final String ID_NAME_CREATE_DATE_OR_LAST_UPDATE_DATE = "(id|name|price|duration|createdAt|lastUpdatedAt)";

    /**
     * Validates the sorting parameters to ensure that they are not null,
     * empty or blank and are equal to any of required values
     *
     * @param property String sort property to validate
     * @param order String sort order to validate
     * @throws ValidationException if any of sorting parameters are not valid
     */
    public void validate(String property, String order) {
        validateProperty(property);
        validateOrder(order);
    }

    void validateOrder(String order) {
        CustomValidator.notNull(SortField.ORDER, order);
        CustomValidator.notBlank(SortField.ORDER, order);

        if (!order.matches(ASC_OR_DESC)) {
            throw new ValidationException(SortField.ORDER.getName() + " must be either asc or desc");
        }
    }

    void validateProperty(String type) {
        CustomValidator.notNull(SortField.PROPERTY, type);
        CustomValidator.notBlank(SortField.PROPERTY, type);

        if (!type.matches(ID_NAME_CREATE_DATE_OR_LAST_UPDATE_DATE)) {
            throw new ValidationException(SortField.PROPERTY.getName() + " must be either name, price, duration, createdAt or lastUpdatedAt");
        }
    }
}
