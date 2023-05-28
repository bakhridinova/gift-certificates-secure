package com.epam.esm.util.enums.field;

/**
 * Marker interface for different entity field names
 *
 * @author bakhridinova
 */

public interface EntityField<T> {
    String getName();
    Class<T> type();
}
