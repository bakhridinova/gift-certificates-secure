package com.epam.esm.entity;

import com.epam.esm.enums.OrderStatus;
import com.epam.esm.listener.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * class representing order entity
 *
 * @author bakhridinova
 */

@Data
@Entity
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order extends AbstractEntity {
    @Column(
            name = "price",
            nullable = false)
    private Double price;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @Builder
    public Order(Long id, Double price, OrderStatus status, User user, Certificate certificate) {
        super(id);
        this.price = price;
        this.status = status;
        this.user = user;
        this.certificate = certificate;
    }
}
