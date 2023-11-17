package com.example.jwttutorial.repository;

import com.example.jwttutorial.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository <Privilege, Long> {
    Optional <Privilege> findByName (String name);
}
