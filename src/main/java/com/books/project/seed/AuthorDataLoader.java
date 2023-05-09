package com.books.project.seed;

import com.books.project.repository.AuthorRepository;
import com.books.project.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDataLoader {

    @Autowired
    AuthorRepository authorRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        loadUserData();
//    }

    private void loadUserData() {
        if (authorRepository.count() == 0) {
            Author author1 = new Author("Paul Bunyon", "Some description");
            Author author2 = new Author("Ernest Hemingway", "Some description");
            Author author3 = new Author("Mark Twain", "Some description");
            Author author4 = new Author("Edgar Allan Poe", "Some description");
            Author author5 = new Author("Dr. Seuss", "Some description");
            authorRepository.save(author1);
            authorRepository.save(author2);
            authorRepository.save(author3);
            authorRepository.save(author4);
            authorRepository.save(author5);
        }
    }
}
