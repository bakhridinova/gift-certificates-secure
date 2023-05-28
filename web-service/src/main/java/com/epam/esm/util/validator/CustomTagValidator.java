package com.epam.esm.util.validator;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ValidationException;
import com.epam.esm.util.enums.field.TagField;
import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * Utility class validating tags
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomTagValidator {
    /**
     * Validates tag to ensure that it's parameters are valid
     *
     * @param tag to validate
     * @param includeId whether id should be included or not
     * @throws ValidationException if any of tag parameters are not valid
     */
    public void validate(Tag tag, boolean includeId) {
        if (includeId) {
            CustomValidator.validateId(TagField.ID, tag.getId());
        }
        validateName(tag.getName());
    }

    public void validate(Collection<Tag> tags, boolean includeId) {
        tags.forEach(tag -> CustomTagValidator.validate(tag, includeId));
    }

    private void validateName(String name) {
        CustomValidator.notNull(TagField.NAME, name);
        CustomValidator.notBlank(TagField.NAME, name);
        CustomValidator.notTooShortOrLong(TagField.NAME, name, 3, 30);
        CustomValidator.onlyLetters(TagField.NAME, name);
    }
}
