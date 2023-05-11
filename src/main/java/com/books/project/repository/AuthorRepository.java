package com.books.project.repository;

import com.books.project.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

    Author findByName(String name);
    Optional<Author> findById(Long id);
}
