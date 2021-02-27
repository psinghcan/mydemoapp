package org.application.service.security;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.application.domain.security.RolePermission;
import org.application.domain.security.UserRole;

@Singleton
@Startup
public class RolePermissionsPublisher {

    private static final Logger logger = Logger.getLogger(RolePermissionsPublisher.class.getName());
    
    @Inject
    private RolePermissionsService rolePermissionService;
    
    @PostConstruct
    public void postConstruct() {

        if (rolePermissionService.countAllEntries() == 0) {

            rolePermissionService.save(new RolePermission(UserRole.Administrator, "division:create"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "division:read"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "division:update"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "division:delete"));
            
            rolePermissionService.save(new RolePermission(UserRole.User, "division:read"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "memoFees:create"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "memoFees:read"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "memoFees:update"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "memoFees:delete"));
            
            rolePermissionService.save(new RolePermission(UserRole.User, "memoFees:read"));
            
            rolePermissionService.save(new RolePermission(UserRole.Administrator, "user:*"));
            
            logger.info("Successfully created permissions for user roles.");
        }
    }
}
