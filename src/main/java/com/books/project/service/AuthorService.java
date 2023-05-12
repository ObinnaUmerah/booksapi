package com.books.project.service;

import com.books.project.model.Author;
import com.books.project.model.Book;
import com.books.project.repository.AuthorRepository;
import com.books.project.exception.InformationNotFoundException;
import com.books.project.model.Author;
import com.books.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
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

    public Book createAuthorBook(Long authorId, Book bookObject) {
        System.out.println("service calling createCategoryRecipe ==>");
        try {
            Optional author = authorRepository.findById(authorId);
            bookObject.setAuthor((Author) author.get());
            bookRepository.save(bookObject);
            return bookObject;
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("author with id " + authorId + " not found");
        }
    }


}
