package com.delveye.gatewarden.domain.user;

import com.delveye.gatewarden.domain.common.BaseModel;
import com.delveye.gatewarden.domain.tenant.Tenant;
import io.quarkus.cache.CacheResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * Represents a user in the system.
 *
 * <p>Contains authentication info, tenant association, roles, and system admin flag.
 *
 * @author Slobodan Zivanovic (slobodan.zivanovic@delveye.com)
 * @since 1.0.0
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tenant tenant;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(nullable = false)
    private boolean systemAdmin;
}
