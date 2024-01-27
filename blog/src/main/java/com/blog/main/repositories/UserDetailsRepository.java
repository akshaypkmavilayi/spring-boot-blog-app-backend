package com.blog.main.repositories;

import com.blog.main.entities.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity,Long> {
    Optional<UserDetailsEntity> findByUserName(String s);
}
