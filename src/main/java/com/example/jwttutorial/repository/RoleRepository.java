package com.example.jwttutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jwttutorial.model.Role;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
