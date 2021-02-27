package org.application.domain.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ROLE_PERMISSIONS")
public class RolePermission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;
    
    @Column(name = "permission")
    private String permission;
    
    public RolePermission() {
        super();
    }
    
    public RolePermission(UserRole role, String permission) {
        super();
        this.role = role;
        this.permission = permission;
    }
}
