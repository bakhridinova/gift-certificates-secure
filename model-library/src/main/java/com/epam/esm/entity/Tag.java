package com.epam.esm.entity;

import com.epam.esm.listener.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * class representing tag entity
 *
 * @author bakhridinova
 */

@Data
@Entity
@Table(name = "tags")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends AbstractEntity {
    @Column(
            name = "name",
            nullable = false,
            unique = true)
    private String name;

    @Builder
    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }
}
