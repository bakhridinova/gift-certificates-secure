package com.epam.esm.entity;

import com.epam.esm.listener.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

/**
 * class representing certificate entity
 *
 * @author bakhridinova
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "certificates")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "certificate_id"))
public class Certificate extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer duration;

    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "certificate_tag",
            joinColumns = { @JoinColumn(name = "tag_id") },
            inverseJoinColumns = { @JoinColumn(name = "certificate_id") }
    )
    private List<Tag> tags;

    @Builder
    public Certificate(Long id, String name, String description, Double price, Integer duration, Date createdAt, Date lastUpdatedAt, User user, List<Tag> tags) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.user = user;
        this.tags = tags;
    }
}
