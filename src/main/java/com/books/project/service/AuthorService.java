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


    /**
     * Retrieves all the authors in the database.
     * @return A list of all the authors
     */
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    /**
     * Gets the author by their id
     * @param id The id of the author
     * @return An author
     */
    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

    /**
     * Creates an author
     * @param author An author that the user wants to create
     * @return The created author
     */
    public Author createAuthor(Author author){
        authorRepository.save(author);
        return author;
    }

    /**
     * Updates the author's information
     * @param authorId The id of the author the user wants to update
     * @param authorObject The new author object
     * @return The update author object
     * @throws InformationNotFoundException
     */
    public Optional<Author> updateAuthor(Long authorId, Author authorObject) throws InformationNotFoundException {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            author.get().setFirstname(authorObject.getFirstname());
            author.get().setLastname(authorObject.getLastname());
            authorRepository.save(author.get());
            return author;
        } else {
            throw new InformationNotFoundException("author with id " + authorId + " not found");
        }
    }

    /**
     * Deletes the author's information
     * @param authorId The id of the author that the user wants to delete
     * @return The deleted author
     * @throws InformationNotFoundException
     */
    public Optional<Author> deleteAuthor(Long authorId) throws InformationNotFoundException {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            authorRepository.deleteById(authorId);
            return author;
        } else {
            throw new InformationNotFoundException("author with id " + authorId + " not found");
        }
    }

    /**
     * Creates a book for an author
     * @param authorId The id of the author
     * @param bookObject The book object
     * @return The new book
     */
    public Book createAuthorBook(Long authorId, Book bookObject) {
        try {
            Optional author = authorRepository.findById(authorId);
            bookObject.setAuthor((Author) author.get());
            bookRepository.save(bookObject);
            return bookObject;
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("author with id " + authorId + " not found");
        }
    }

    /**
     * Gets the book from an author
     * @param authorId The id of the author
     * @param bookId The id of the book
     * @return The book from the specified author
     */
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

    /**
     * Updates the book of an author
     * @param authorId The id of the author
     * @param bookId The id of the book
     * @param bookobject The book object
     * @return The newly updated book object
     */
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

    /**
     * Deletes the book of an author
     * @param authorId The id of the author
     * @param bookId The id of the book
     */
    public void deleteAuthorBook(Long authorId, Long bookId){
        try {
            Book book = (bookRepository.findByAuthorId(authorId).stream().filter(p -> p.getId().equals(bookId)).findFirst()).get();
            bookRepository.deleteById(book.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book or author not found");
        }
    }


}
