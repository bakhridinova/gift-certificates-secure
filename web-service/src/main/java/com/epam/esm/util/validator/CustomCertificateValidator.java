package com.epam.esm.util.validator;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ValidationException;
import com.epam.esm.util.enums.field.CertificateField;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Utility class validating certificates
 *
 * @author bakhridinova
 */

@UtilityClass
public class CustomCertificateValidator {
    /**
     * Validates certificate to ensure that it's parameters are valid
     *
     * @param certificate to validate
     * @param includeId whether id should be included or not
     * @throws ValidationException if any of certificate parameters are not valid
     */
    public void validate(Certificate certificate, boolean includeId) {
        if (includeId) {
            CustomValidator.validateId(CertificateField.ID, certificate.getId());
        }
        validateName(certificate.getName());
        validateDescription(certificate.getDescription());
        validatePrice(certificate.getPrice());
        validateDuration(certificate.getDuration());
        validateTags(certificate.getTags(), includeId);
    }

    /**
     * Validates certificate name to ensure that it's not null, empty or blank,
     * includes required number of characters and does not include special characters
     *
     * @param name String name to validate
     * @throws ValidationException if name is not valid
     */
    void validateName(String name) {
        CustomValidator.notNull(CertificateField.NAME, name);
        CustomValidator.notBlank(CertificateField.NAME, name);
        CustomValidator.notTooShortOrLong(CertificateField.NAME, name, 4, 40);
        CustomValidator.onlyLettersAndSpaces(CertificateField.NAME, name);
    }

    void validateDescription(String description) {
        CustomValidator.notNull(CertificateField.DESCRIPTION, description);
        CustomValidator.notBlank(CertificateField.DESCRIPTION, description);
        CustomValidator.notTooShortOrLong(CertificateField.DESCRIPTION, description, 4, 100);
        CustomValidator.onlyLettersAndSpaces(CertificateField.DESCRIPTION, description);
    }

    public void validatePrice(Double price) {
        CustomValidator.notNull(CertificateField.PRICE, price);
        CustomValidator.notNegative(CertificateField.PRICE, price);
        CustomValidator.notTooLowOrHigh(CertificateField.PRICE, price, 10, 100);
    }

    void validateDuration(Integer duration) {
        CustomValidator.notNull(CertificateField.DURATION, duration);
        CustomValidator.notNegative(CertificateField.DURATION, Double.valueOf(duration));
        CustomValidator.notTooLowOrHigh(CertificateField.DURATION, Double.valueOf(duration), 10, 90);
    }

    void validateTags(List<Tag> tags, boolean includeId) {
        CustomTagValidator.validate(tags, includeId);
    }
}
