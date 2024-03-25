package com.nus.sgevent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nus.sgevent.entity.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<EventUser, Integer> {

	@Query(value = "select event_user.* from event_user where user_id=?1 and password=?2 ",nativeQuery = true)
	List<EventUser> checkUserLogin(String UserId,String Password);
	
	@Query(value = "Update event_user set password=?2,email_address=?3,user_role=?4 where user_id=?1",nativeQuery = true)
	boolean UpdateUser(String UserId,String Password, String UserName, String EmailAddress, int UserRole);
	
	@Query(value = "select event_user.* from event_user where email_address=?1",nativeQuery = true)
	EventUser SearchEventUser(String EmailAddress);
	
	@Query(value = "select event_user.* from event_user where user_name=?1",nativeQuery = true)
	EventUser SearchEventUserName(String UserName);
	
}
