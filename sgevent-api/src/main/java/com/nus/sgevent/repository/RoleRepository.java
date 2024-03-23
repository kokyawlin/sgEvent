package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nus.sgevent.entity.UserRole;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoleRepository extends CrudRepository<UserRole, Integer> {

	@Query(value = "SELECT user_role.* FROM user_role where role_id=?1",nativeQuery = true)
	UserRole SearchUserRole(int RoleId);
}
