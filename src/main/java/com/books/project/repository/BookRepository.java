package com.books.project.repository;

import com.books.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Finds a book by its name.
     * @param bookName The book's name.
     * @return The desired book
     */
    Book findByName(String bookName);

    /**
     *
     * @param bookName
     * @param authorId
     * @return
     */
    Book findByNameAndIdIsNot(String bookName, Long authorId);
    List<Book> findByAuthorId(Long bookId);
}
