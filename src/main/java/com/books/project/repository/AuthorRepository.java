package com.books.project.repository;

import com.books.project.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

    /**
     * Finds the author by their name
     * @param name The author's name
     * @return The desired author
     */
    Author findByName(String name);

    /**
     * Finds the author by their id
     * @param id The author's id
     * @return The desired author
     */
    Optional<Author> findById(Long id);
}
