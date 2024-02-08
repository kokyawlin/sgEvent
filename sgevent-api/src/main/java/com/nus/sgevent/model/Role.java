package com.nus.sgevent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private String roleId;
    private String roleName;
    private String permission;

    // public Role() {}

    // public Role(String roleId, String roleName, String permission) {
    //     this.roleId = roleId;
    //     this.roleName = roleName;
    //     this.permission = permission;
    // }

    // // Getters and Setters
    // public String getRoleId() { return roleId; }
    // public void setRoleId(String roleId) { this.roleId = roleId; }
    // public String getRoleName() { return roleName; }
    // public void setRoleName(String roleName) { this.roleName = roleName; }
    // public String getPermission() { return permission; }
    // public void setPermission(String permission) { this.permission = permission; }
    
}
