package com.delveye.gatewarden.domain.tenant;

import com.delveye.gatewarden.domain.common.BaseModelLong;
import com.delveye.gatewarden.domain.user.Role;
import com.delveye.gatewarden.domain.user.User;
import io.quarkus.cache.CacheResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * Represents a tenant in the system.
 *
 * <p>A tenant groups users and can define tenant-specific roles.
 *
 * @author Slobodan Zivanovic (slobodan.zivanovic@delveye.com)
 * @since 1.0.0
 */
@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BaseModelLong {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Role> roles;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<User> users;
}
