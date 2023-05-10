package com.books.project.controller;


import com.books.project.exception.InformationExistException;
import com.books.project.model.Author;
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

//    @Autowired
//    public void setAuthorRepository(AuthorRepository authorRepositoryRepository){
//        this.authorRepository = authorRepository;
//    }

    static HashMap<String, Object> message = new HashMap<>();

    @GetMapping(path = "/hello-world/")
    public String helloWorld() {
        return "hello World";
    }

//    @GetMapping(path = "/books/")
//    public ResponseEntity<?> getAllAuthors() {
//        List<Author> bookList = authorRepository.getAllAuthors(); (bookList.isEmpty()) {
//            message.put("message", "cannot find any books ");
//            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//        } else {
//            message.put("message", "success");
//            message.put("data", bookList);
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }

//    @PostMapping(path = "/authors/")
//    public Author createAuthor(@RequestBody Author authorObject) {
//        System.out.println("service calling createAuthor ==>");
//
//        Author category = authorRepository.findByName(authorObject.getName());
//        if (category != null) {
//            throw new InformationExistException("category with name " + category.getName() + " already exists");
//        } else {
//            return authorRepository.save(authorObject);
//        }
//    }

    @PostMapping("/author/")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        Author newAuthor = authorService.createAuthor(author);
        if (newAuthor != null) {
           message.put("message", "success");
            message.put("data", newAuthor);
           return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "unable to create a book at this time");
            return new ResponseEntity<>(message, HttpStatus.OK);
//        }
        }

    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable(value = "authorId") Long authorId){
        Optional<Author> author = authorService.getAuthorById(authorId);
//        if(author.isPresent()){
//            message.put("message", "success");
////            message.put("data", author.get());
//            return new ResponseEntity<>(message, HttpStatus.OK);



//            message.put("message", "cannot find book with id " + bookId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }


    }





    //@GetMapping(path ="/")

