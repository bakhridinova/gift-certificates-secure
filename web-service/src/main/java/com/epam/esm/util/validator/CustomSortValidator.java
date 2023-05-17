package com.epam.esm.util.validator;

import com.epam.esm.exception.CustomValidationException;
import com.epam.esm.util.enums.field.SortField;
import lombok.experimental.UtilityClass;

/**
 * utility class validating sorting details
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomSortValidator {
    private final String ASC_OR_DESC = "(asc|desc)";
    private final String ID_NAME_CREATE_DATE_OR_LAST_UPDATE_DATE = "(id|name|price|duration|createdAt|lastUpdatedAt)";

    /**
     * validates the sorting parameters to ensure that they are not null,
     * empty or blank and are equal to any of required values
     *
     * @param property String sort property to validate
     * @param order String sort order to validate
     * @throws CustomValidationException if any of sorting parameters are not valid
     */
    public void validate(String property, String order) {
        validateProperty(property);
        validateOrder(order);
    }

    void validateOrder(String order) {
        CustomValidator.notNull(SortField.ORDER, order);
        CustomValidator.notBlank(SortField.ORDER, order);

        if (!order.matches(ASC_OR_DESC)) {
            throw new CustomValidationException(SortField.ORDER.getName() + " must be either asc or desc");
        }
    }

    void validateProperty(String type) {
        CustomValidator.notNull(SortField.TYPE, type);
        CustomValidator.notBlank(SortField.TYPE, type);

        if (!type.matches(ID_NAME_CREATE_DATE_OR_LAST_UPDATE_DATE)) {
            throw new CustomValidationException(SortField.TYPE.getName() + " must be either name, price, duration, createdAt or lastUpdatedAt");
        }
    }
}
