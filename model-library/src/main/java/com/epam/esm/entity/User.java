package com.epam.esm.entity;

import com.epam.esm.entity.listener.AuditListener;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * class representing user entity
 *
 * @author bakhridinova
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractEntity {
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(
            name = "username",
            unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Builder
    public User(Long id, Role role, String username, String password, String firstName, String lastName, String emailAddress, Date birthDate) {
        super(id);
        this.role = role;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
    }

    public enum Role {
        GUEST,
        USER,
        ADMIN
    }
}
