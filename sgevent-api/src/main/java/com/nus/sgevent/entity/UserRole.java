package com.nus.sgevent.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_role") 
@Data
@NoArgsConstructor
public class UserRole {

    @Id
    private int roleId; 

    private String roleName;
    private String permission;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

    
    
}
