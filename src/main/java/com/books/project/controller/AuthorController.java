package com.books.project.controller;


import com.books.project.exception.InformationExistException;
import com.books.project.exception.InformationNotFoundException;
import com.books.project.model.Author;
import com.books.project.model.Book;
import com.books.project.repository.AuthorRepository;
import com.books.project.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    static HashMap<String, Object> message = new HashMap<>();



    @GetMapping(path = "/hello-world/")
    public String helloWorld() {
        return "hello World";
    }


    @PostMapping("/authors/")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        Author newAuthor = authorService.createAuthor(author);
        if (newAuthor != null) {
            message.put("message", "success");
            message.put("data", newAuthor);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "unable to create a book at this time");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable(value = "authorId") Long authorId) {
        Optional<Author> author = authorService.getAuthorById(authorId);
        if (author.isPresent()) {
            message.put("message", "success");
            message.put("data", author.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "cannot find book with id " + authorId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }


    // http://localhost:8080
    @PutMapping("/authors/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable(value = "authorId") Long authorId, @RequestBody Author authorObject) throws InformationNotFoundException {
        Optional<Author> bookToUpdate = authorService.updateAuthor(authorId, authorObject);
        if (bookToUpdate.isEmpty()) {
            message.put("message", "cannot find book with id " + authorId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "book with id " + authorId + " has been successfully updated");
            message.put("data", bookToUpdate.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "authorId") Long authorId) {
        Optional<Author> authorToDelete = authorService.deleteAuthor(authorId);
        if (authorToDelete.isEmpty()) {
            message.put("message", "cannot find book with id " + authorId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "book with id " + authorId + " has been successfully deleted");
            message.put("data", authorToDelete.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<?> createAuthorBook(
            @PathVariable(value = "authorId") Long authorId, @RequestBody Book bookObject) {
        Book newBook = authorService.createAuthorBook(authorId, bookObject);
        if (newBook != null) {
            message.put("message", "success");
            message.put("data", newBook);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "cannot find book with id " + authorId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/authors/{authorId}/books/{bookId}")
    public ResponseEntity<?> getAuthorBook(
            @PathVariable(value = "authorId") Long authorId, @PathVariable(value = "bookId") Long bookId) {
       Optional<Book> book = Optional.ofNullable(authorService.getAuthorBook(authorId, bookId));
        if(book.isPresent()) {
            message.put("message", "success");
            message.put("data", book);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        else {
            message.put("message", "cannot find book with id " + authorId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }




}



