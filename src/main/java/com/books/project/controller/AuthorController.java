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

    /**
     * Contains the endpoint of the post request for the authors' table.
     * @param author An author object that will be added to the authors' table.
     * @return A status code that shows if the post request was successful or not.
     */
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

    /**
     * Contains the endpoint of the get request for the authors' table.
     * @param authorId The index of the author that the user wants to retrieve.
     * @return A status code that shows if the get request was successful or not.
     */
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


    /**
     * Contains the endpoint of the put request for the authors' table.
     * @param authorId The id of the author you want to update.
     * @param authorObject The author object that will be replacing another author in the table.
     * @return A status code that shows if the put request was successful or not.
     * @throws InformationNotFoundException An exception that is thrown is no author is found for the passed in
     * authorId.
     */
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

    /**
     * Contains the endpoint of the delete request for the authors' table.
     * @param authorId The id of the author you want to delete from the table.
     * @return A status code that shows if the delete request was successful or not.
     */
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

    /**
     * Contains the endpoint of the post request for the books' table.
     * @param authorId The id of the author that the user wants to add a book to.
     * @param bookObject The book that is being added to authors' list
     * @return A status code that shows if the post request was successful or not.
     */
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

    /**
     * Contains the endpoint of the get  request for the books' table.
     * @param authorId The id of the author that the user want to access.
     * @param bookId The id of the book that the user wants to retrieve.
     * @return A status code that shows if the get request was successful or not.
     */
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

    /**
     * Contains the endpoint of the post request for the books' table.
     * @param authorId The id of author whose book the user wants to edit
     * @param bookId The id of the book the user wants to edit
     * @param bookObject The book object containing the information that will replace the current one.
     * @return A status code that shows if the put request was successful or not.
     */
    @PutMapping("/authors/{authorId}/books/{bookId}")
    public ResponseEntity<?> updateAuthorBook(
             @PathVariable(value = "authorId") Long authorId,
             @PathVariable(value = "bookId") Long bookId,
             @RequestBody Book bookObject) {
        Optional<Book> book = Optional.ofNullable(authorService.updateAuthorBook(authorId, bookId, bookObject));
        if (book.isEmpty()) {
            message.put("message", "cannot find book or author with id " + authorId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "book with id " + authorId + " has been successfully updated");
            message.put("data", book.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    /**
     * Contains the endpoint of the delete request for the books' table.
     * @param authorId The id of the author holding a book that will be deleted.
     * @param bookId The id of the book to be deleted.
     * @return A status code that shows if the delete request was successful or not.
     */
       @DeleteMapping("/authors/{authorId}/books/{bookId}")
        public ResponseEntity<HashMap<String, String>> deleteAuthorBook(
                @PathVariable(value = "authorId") Long authorId, @PathVariable(value = "bookId") Long bookId){
                authorService.deleteAuthorBook(authorId, bookId);
                HashMap<String, String> responseMessage = new HashMap<>();
                responseMessage.put("status", "book with id: " + bookId + " was successfully deleted.");
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);

        }

    }




