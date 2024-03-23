package com.nus.sgevent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nus.sgevent.entity.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Transactional
public interface UserRepository extends CrudRepository<EventUser, Integer> {

	@Query(value = "select event_user.* from event_user where user_id=?1 and password=?2 ",nativeQuery = true)
	List<EventUser> checkUserLogin(String UserId,String Password);
	
	@Modifying
	@Query(value = "update event_user set user_name=?1, email_address=?2, role_id=?3 where user_id=?4",nativeQuery = true)
	int UpdateUser( String UserName, String EmailAddress, int UserRole,String UserId);
	
	@Query(value = "select event_user.* from event_user where user_id=?1",nativeQuery = true)
	EventUser SearchEventUser(String UserId);
	
}
