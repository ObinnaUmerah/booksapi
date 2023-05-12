package com.books.project.repository;

import com.books.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String bookName);
    Book findByNameAndIdIsNot(String bookName, Long authorId);
    List<Book> findByAuthorId(Long bookId);
}
