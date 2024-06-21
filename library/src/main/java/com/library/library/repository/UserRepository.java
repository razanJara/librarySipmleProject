package com.library.library.repository;

import com.library.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.name = :username ")
    User findByName(@Param("username")String username);
}
