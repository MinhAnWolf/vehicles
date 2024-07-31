package com.example.business.repository;

import com.example.business.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT COUNT(*) FROM USERS WHERE username = :username", nativeQuery = true)
    Integer countUsersByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM USERS WHERE username = :username", nativeQuery = true)
    Users getUserByUsername(@Param("username") String username);
}
