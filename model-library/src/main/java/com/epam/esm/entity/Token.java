package com.epam.esm.entity;

import com.epam.esm.enums.TokenType;
import com.epam.esm.listener.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

@Data
@Entity
@Table(name = "tokens")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "token_id"))
public class Token extends AbstractEntity {

    @Column(name = "access_token", unique = true)
    public String accessToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public TokenType type;

    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "expired")
    public boolean expired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;

    @Builder
    public Token(Long id, String accessToken, TokenType type, User user) {
        super(id);
        this.accessToken = accessToken;
        this.type = type;
        this.user = user;
    }
}
