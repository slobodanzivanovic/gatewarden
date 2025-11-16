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
 * Represents a role in the system.
 *
 * <p>Can be system-wide (tenant = null) or tenant-specific.
 *
 * @author Slobodan Zivanovic (slobodan.zivanovic@delveye.com)
 * @since 1.0.0
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant; // null = system role

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;
}
