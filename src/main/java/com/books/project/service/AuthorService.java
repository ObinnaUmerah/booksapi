package com.books.project.service;

import com.books.project.model.Author;
import com.books.project.repository.AuthorRepository;
import com.books.project.exception.InformationNotFoundException;
import com.books.project.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author){
        authorRepository.save(author);
        return author;
    }

    public Optional<Author> updateAuthor(Long authorId, Author authorObject) throws InformationNotFoundException {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            author.get().setName(authorObject.getName());
            author.get().setDescription(authorObject.getDescription());
            authorRepository.save(author.get());
            return author;
        } else {
            throw new InformationNotFoundException("author with id " + authorId + " not found");
        }
    }

    public Optional<Author> deleteAuthor(Long authorId) throws InformationNotFoundException {
        Optional<Author> book = authorRepository.findById(authorId);
        if (book.isPresent()) {
            authorRepository.deleteById(authorId);
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + authorId + " not found");
        }
    }


}
