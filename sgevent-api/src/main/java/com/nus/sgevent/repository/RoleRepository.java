package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nus.sgevent.model.Role;


public interface RoleRepository extends JpaRepository<Role, String> {
}
