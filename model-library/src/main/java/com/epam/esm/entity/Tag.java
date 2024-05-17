package com.epam.esm.entity;

import com.epam.esm.listener.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Class representing tag entity
 *
 * @author bakhridinova
 */

@Getter
@Setter
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

    @ManyToMany(
            mappedBy = "tags",
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private List<Certificate> certificates;

    @Builder
    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }
}
