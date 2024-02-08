package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nus.sgevent.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}

