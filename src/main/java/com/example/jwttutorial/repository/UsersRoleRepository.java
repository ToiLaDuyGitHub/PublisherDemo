package com.example.jwttutorial.repository;

import com.example.jwttutorial.model.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

    Optional<UsersRole> findById(Long role_id);

}
