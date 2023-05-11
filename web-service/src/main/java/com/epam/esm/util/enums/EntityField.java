package com.epam.esm.util.enums;

/**
 * marker interface for different entity field names
 *
 * @author bakhridinova
 */

public interface EntityField<T> {
    String getName();
    Class<T> type();
}
