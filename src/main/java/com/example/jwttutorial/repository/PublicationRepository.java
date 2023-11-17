package com.example.jwttutorial.repository;

import com.example.jwttutorial.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
