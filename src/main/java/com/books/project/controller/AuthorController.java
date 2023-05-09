package com.books.project.controller;


import com.books.project.model.Author;
import com.books.project.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepositoryRepository){
        this.authorRepository = authorRepository;
    }

    static HashMap<String, Object> message = new HashMap<>();

    @GetMapping(path = "/hello-world/")
    public String helloWorld() {
        return "hello World";
    }

//    @GetMapping(path = "/books/")
//    public ResponseEntity<?> getAllBooks() {
//        List<Author> bookList = authorRepository.getAllBooks(); (bookList.isEmpty()) {
//            message.put("message", "cannot find any books ");
//            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//        } else {
//            message.put("message", "success");
//            message.put("data", bookList);
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }
    }


    //@GetMapping(path ="/")

