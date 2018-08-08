package com.zipportal.zipportal.Repository;

import com.zipportal.zipportal.Model.Assignment;
import com.zipportal.zipportal.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);
}
