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

    public Book getAuthorBook(Long authorId, Long bookId){
        System.out.println("Service calling getAuthorRecipe ===>");
        Optional<Author> author = authorRepository.findById(authorId);
        if(author.isPresent()) {
            Optional<Book> book = bookRepository.findByAuthorId(authorId).stream().filter(p -> p.getId().equals(bookId)).findFirst();
            if(book.isEmpty()) {
                throw new InformationNotFoundException("book with id " + bookId + " not found");
            }else {
                return book.get();
            }
        } else {
            throw new InformationNotFoundException("author with id " + authorId + " not found");
        }
    }

    public Book updateAuthorBook(Long authorId, Long bookId, Book bookobject){
        try {
            Book book = (bookRepository.findByAuthorId(authorId).stream().filter(p -> p.getId().equals(bookId)).findFirst().get());
            book.setName(bookobject.getName());
            book.setDescription(bookobject.getDescription());
            book.setIsbn(bookobject.getIsbn());
            return bookRepository.save(book);
        } catch(NoSuchElementException e) {
            throw new InformationNotFoundException("book or author not found");
        }
    }

    public void deleteAuthorBook(Long authorId, Long bookId){
        try {
            Book book = (bookRepository.findByAuthorId(authorId).stream().filter(p -> p.getId().equals(bookId)).findFirst()).get();
            bookRepository.deleteById(book.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book or author not found");
        }
    }


}
